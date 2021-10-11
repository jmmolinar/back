package cl.blackgps.back.threads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QueryThread implements Runnable {

    // Paso 1 - Cadena de conexión a mysql
    String url = "jdbc:mysql://localhost:3306/modulo_mantenimiento?useSSL=false&serverTimezone=UTC";

    String consultarActivos = "SELECT " 
            + "activo.id_activo, " 
            + "activo.id_vehiculo, "
            + "activo.area_id_area, "
            + "activo.bodega_activos_id_bodega_activos, " 
            + "bodega_activos.nombre AS 'BODEGA', "
            + "activo.tipo_activo_id_tipo_activo, " 
            + "tipo_activo.nombre AS 'TIPO', " 
            + "activo.anio AS anio, "
            + "plan_mantenimiento.id_plan_mantenimiento, " 
            + "plan_mantenimiento.nombre, "
            + "plan_mantenimiento.por_hora, " 
            + "plan_mantenimiento.por_km, " 
            + "plan_mantenimiento.por_periodo, "
            + "plan_mantenimiento_has_categoria_servicio.periodo_fecha, "
            + "plan_mantenimiento_has_categoria_servicio.periodo_cada, "
            + "plan_mantenimiento_has_categoria_servicio.periodo_frecuencia, "
            + "categoria_servicio.id_categoria_servicio, " 
            + "categoria_servicio.nombre, " 
            + "activo.dado_de_baja "
            + "FROM " 
            + "activo " 
            + "LEFT JOIN "
            + "tipo_activo ON activo.tipo_activo_id_tipo_activo = tipo_activo.id_tipo_activo " 
            + "LEFT JOIN "
            + "bodega_activos ON activo.bodega_activos_id_bodega_activos = bodega_activos.id_bodega_activos "
            + "LEFT JOIN "
            + "activo_has_plan_mantenimiento ON activo.id_activo = activo_has_plan_mantenimiento.activo_id_activo "
            + "LEFT JOIN "
            + "plan_mantenimiento ON activo_has_plan_mantenimiento.plan_mantenimiento_id_plan_mantenimiento = plan_mantenimiento.id_plan_mantenimiento "
            + "INNER JOIN "
            + "plan_mantenimiento_has_categoria_servicio ON plan_mantenimiento.id_plan_mantenimiento = plan_mantenimiento_has_categoria_servicio.plan_mantenimiento_id_plan_mantenimiento "
            + "INNER JOIN "
            + "categoria_servicio ON plan_mantenimiento_has_categoria_servicio.categoria_servicio_id_categoria_servicio = categoria_servicio.id_categoria_servicio "
            + "ORDER BY activo.id_activo DESC";

    String crearOrden = "INSERT INTO orden "
        + "(fecha_creacion, "
        + "fecha_inicial, "
        + "fecha_final, "
        + "tipo_orden_id_tipo_orden, "
        + "activo_id_activo, "
        + "taller_servicio_id_taller_servicio, "
        + "observaciones, "
        + "ruta_adjunto_completado, "
        + "fecha_ruta_completado) "
        + "VALUES "
        + "(?," // --1? fecha_creacion
        + "null, "
        + "null, "
        + "1, " // 1 es el Tipo Preventivo
        + "?, " // --2? id_activo
        + "null, "
        + "null, "
        + "null, "
        + "null) ";

        String guardarUltimoIdOrden = "SET @last_id_orden := LAST_INSERT_ID()";
        String guardarUltimaFechaCreacionOrden = "SET @last_fecha_creacion_orden "
        + ":= (SELECT orden.fecha_creacion FROM orden WHERE orden.id_orden = @last_id_orden)";

        String obtenerUltimoIdOrden = "SELECT @last_id_orden";
        String obtenerUltimaFechaCreacionOrden = "SELECT @last_fecha_creacion_orden";
        
        String crearOrdenHasEstado = "INSERT INTO orden_has_estado "
        + "(orden_id_orden,estado_id_estado,id_usuario, fecha_asignado) " 
        + "VALUES "
        + "(LAST_INSERT_ID(),1,2,?) "; // --1? fecha_asignado // LAST_INSERT_ID() toma el id_orden recien creado

        String crearOrdeHasCategoria = "INSERT INTO orden_has_categoria_servicio "
        + "(orden_id_orden,categoria_servicio_id_categoria_servicio,costo,fecha_categoria_asignada,observacion_categoria) "
        + "VALUES "
        + "(LAST_INSERT_ID(),?,null,?,null) "; // --1? categoria_servicio_id_categoria_servicio
        // --2? fecha_categoria_asignada


    private String command;

    public QueryThread(String command) {
        this.command = command;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " Inicio = " + command);

        //Para saber cuántos registros han sido insertados
        int registro = 0;
        int registroOrdenEstado = 0;
        int registroOrdenCategoria = 0;

        try {

            // Paso 2 - Creamos el objeto de conexión a la base de datos
            Connection conexion = DriverManager.getConnection(url, "root", "123456");
            // Paso 3 - Creamos un objeto Statement y el objetos PreparedStatement
            Statement instruccionActivos = conexion.createStatement();
            PreparedStatement instruccionOrden = conexion.prepareStatement(crearOrden);
            PreparedStatement instruccionOrdenHasEstado = conexion.prepareStatement(crearOrdenHasEstado);
            PreparedStatement instruccionOrdenHasCategoria = conexion.prepareStatement(crearOrdeHasCategoria);
            // Paso 4 - Creamos el query
            // Paso 5 - Ejecución del query
            ResultSet resultado = instruccionActivos.executeQuery(consultarActivos);
            
            // Paso 6 - Procesamos el resultado

            int bandera = 0; //Para controlar la creación de una orden por activo
            
            //Para controlar la creación de ordenes según los datos de las categorías
            //que están en planes por período
            String fechaInicialCategoria = "";
            int cadaCategoria = 0;
            String frecuenciaCategoria = "";
            //int incrementoDias = 0;

            LocalDate fechaInicialCategoriaParseDate = LocalDate.now();
            LocalDate fechaActual = LocalDate.now();

            while (resultado.next()) { // while para procesar cada registro de consultarActivos

                //CREACIÓN AUTOMÁTICA DE ÓRDENES POR PERIODOS
                //EJECUTANDO ESTE RUN UNA VEZ POR DÍA PARA EVITAR REPETICIÓN DE CREACIÓN DE ÓRDENES
                //Si es por periodo - indice 13 && si no esta dado de baja - indice 19
                if (resultado.getBoolean(13) && !resultado.getBoolean(19)) {

                    //Verificando fecha inicial de la categoría
                    fechaInicialCategoria = resultado.getString(14); //El indice 14 de la consulta
                    fechaInicialCategoria = fechaInicialCategoria.substring(0, 10);

                    cadaCategoria = resultado.getInt(15);
                    frecuenciaCategoria = resultado.getString(16);
                    fechaInicialCategoriaParseDate = LocalDate.parse(fechaInicialCategoria);
                    fechaActual = LocalDate.parse(currentDate().substring(0, 10));

                    if(frecuenciaCategoria.equals("Días")){
                        //incrementoDias = cadaCategoria;

                        System.out.println("");
                        System.out.println("POR DÍAS");

                        for (LocalDate date = fechaInicialCategoriaParseDate; date.isBefore(fechaActual) || date.equals(fechaActual); date = date.plusDays(cadaCategoria)){

                            //System.out.println("Entre al FOR en Días");

                            if(date.isEqual(fechaActual)){

                                //Impresión de algunos datos para verificar
                                printInfo(resultado.getInt(1), resultado.getInt(2), resultado.getString(10), 
                                    resultado.getString(18), resultado.getString(14), resultado.getInt(15), resultado.getString(16));

                                //Crear orden y orden_has_categoria
                                metodoCrearOrdenYOrdenHasEstado(bandera, resultado.getInt(1), instruccionOrden,
                                    instruccionOrdenHasEstado, registro, registroOrdenEstado);

                                //Crear orden_has_categoria_servicio
                                metodoCrearOrdenHasCategoria(instruccionOrdenHasCategoria, resultado.getInt(17), 
                                    registroOrdenCategoria);

                            }

                            System.out.println("Iteración en FOR - CADA:" + cadaCategoria + " DÍAS - FECHA: " + date);

                        }

                    }

                        
                    if(frecuenciaCategoria.equals("Semanas")){
                        //incrementoDias = cadaCategoria * 7;

                        System.out.println("");
                        System.out.println("POR SEMANAS");

                        for (LocalDate date = fechaInicialCategoriaParseDate; date.isBefore(fechaActual) || date.equals(fechaActual); date = date.plusWeeks(cadaCategoria)){

                            //System.out.println("Entre al FOR en SEMANAS");

                            if(date.isEqual(fechaActual)){

                                //Impresión de algunos datos para verificar
                                printInfo(resultado.getInt(1), resultado.getInt(2), resultado.getString(10), 
                                resultado.getString(18), resultado.getString(14), resultado.getInt(15), resultado.getString(16));

                                //Crear orden y orden_has_categoria
                                metodoCrearOrdenYOrdenHasEstado(bandera, resultado.getInt(1), instruccionOrden,
                                    instruccionOrdenHasEstado, registro, registroOrdenEstado);

                                //Crear orden_has_categoria_servicio
                                metodoCrearOrdenHasCategoria(instruccionOrdenHasCategoria, resultado.getInt(17), 
                                    registroOrdenCategoria);

                            }

                            System.out.println("Iteración en FOR - CADA:" + cadaCategoria + " SEMANAS - FECHA: " + date);

                        }

                    }

                    if(frecuenciaCategoria.equals("Meses")){
                        //incrementoDias = cadaCategoria * 30;

                        System.out.println("");
                        System.out.println("POR MESES");

                        for (LocalDate date = fechaInicialCategoriaParseDate; date.isBefore(fechaActual) || date.equals(fechaActual); date = date.plusMonths(cadaCategoria)){

                            //System.out.println("Entre al FOR en MESES");

                            if(date.isEqual(fechaActual)){

                                //Impresión de algunos datos para verificar
                                printInfo(resultado.getInt(1), resultado.getInt(2), resultado.getString(10), 
                                resultado.getString(18), resultado.getString(14), resultado.getInt(15), resultado.getString(16));

                                //Crear orden y orden_has_categoria
                                metodoCrearOrdenYOrdenHasEstado(bandera, resultado.getInt(1), instruccionOrden,
                                    instruccionOrdenHasEstado, registro, registroOrdenEstado);

                                //Crear orden_has_categoria_servicio
                                metodoCrearOrdenHasCategoria(instruccionOrdenHasCategoria, resultado.getInt(17), 
                                    registroOrdenCategoria);

                            }

                            System.out.println("Iteración en FOR - CADA:" + cadaCategoria + " MESES - FECHA: " + date);

                        }

                    }

                    if(frecuenciaCategoria.equals("Años")){
                        //incrementoDias = cadaCategoria * 365;

                        System.out.println("");
                        System.out.println("POR AÑOS");

                        for (LocalDate date = fechaInicialCategoriaParseDate; date.isBefore(fechaActual) || date.equals(fechaActual); date = date.plusYears(cadaCategoria)){

                            //System.out.println("Entre al FOR en AÑOS");

                            if(date.isEqual(fechaActual)){

                                //Impresión de algunos datos para verificar
                                printInfo(resultado.getInt(1), resultado.getInt(2), resultado.getString(10), 
                                resultado.getString(18), resultado.getString(14), resultado.getInt(15), resultado.getString(16));

                                //Crear orden y orden_has_categoria
                                metodoCrearOrdenYOrdenHasEstado(bandera, resultado.getInt(1), instruccionOrden,
                                    instruccionOrdenHasEstado, registro, registroOrdenEstado);

                                //Crear orden_has_categoria_servicio
                                metodoCrearOrdenHasCategoria(instruccionOrdenHasCategoria, resultado.getInt(17), 
                                    registroOrdenCategoria);

                            }

                            System.out.println("Iteración en FOR - CADA:" + cadaCategoria + " AÑOS - FECHA: " + date);

                        }

                    }

                }

            }

            // Cerramos cada objeto que hemos utilizado
            resultado.close();
            instruccionActivos.close();
            instruccionOrden.close();
            instruccionOrdenHasEstado.close();
            instruccionOrdenHasCategoria.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        processCommand();
        System.out.println(Thread.currentThread().getName() + " Fin");

    }

    //Lapso de tiempo antes de la siguiente ejecución del Scheduled, solo a modo de prueba
    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Obtener la fecha actual como String
    private String currentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //System.out.println("yyyy-MM-dd HH:mm:ss-> "+dtf.format(LocalDateTime.now()));
        return dtf.format(LocalDateTime.now());
    }

    //Impresión de algunos datos para verificar
    private void printInfo(int a, int b, String c, String d, String e, int f, String g){
        System.out.println(
            "ACTIVO: " + a + " | " + 
            "VEHÍCULO: " + b + " | " + 
            "PLAN: " + c + " | " + 
            "CATEGORIA: " + d + " | " + 
            "INICIO: " + e + " | " + 
            "CADA: " + f + " | " + 
            "FRECUENCIA: " + g
        );

        
        /*System.out.println(
            "ACTIVO: " + resultado.getInt(1) + " | " + 
            "VEHÍCULO: " + resultado.getInt(2) + " | " + 
            "PLAN: " + resultado.getString(10) + " | " + 
            "CATEGORIA: " + resultado.getString(18) + " | " + 
            "INICIO: " + resultado.getString(14) + " | " + 
            "CADA: " + resultado.getInt(15) + " | " + 
            "FRECUENCIA: " + resultado.getString(16)
        );*/
    }

    //Creación de Orden y Orden_has_estado
    private void metodoCrearOrdenYOrdenHasEstado(int bandera, int activo, PreparedStatement instruccionOrden,
                        PreparedStatement instruccionOrdenHasEstado, int registro, int registroOrdenEstado) throws SQLException{

        //Si la bandera es distinto del Id del activo actual crea la orden y la orden_has_estado
        if(bandera != activo){

            //Creación orden
            instruccionOrden.setString(1, currentDate()); //Asigno la fecha actual al índice 1 del INSERT
            instruccionOrden.setInt(2, activo); //Asigno el id_activo del SELECT al indice 2 del INSERT
            
            //Creación orden_has_estado
            //orden_id_orden para registrar en orden_has_estado se obtienen de la consulta con LAST_INSERT_ID()
            //LAST_INSERT_ID() toma el último id creado que es el de la orden
            instruccionOrdenHasEstado.setString(1, currentDate()); //Asigno la fecha actual al índice 1 del INSERT de orden has estado
            
            //Ejecución de INSERT de orden y orden_has_estado
            registro = instruccionOrden.executeUpdate(); // Ejecuto el INSERT de una nueva órden
            registroOrdenEstado = instruccionOrdenHasEstado.executeUpdate(); // Ejecuto el INSERT de la relación en orden_has_estado

            System.out.println(registro + " Nuevo registro creado en la tabla orden");
            System.out.println(registroOrdenEstado + " Nuevo registro en Orden Has Estado");

            //Asigno el valor del activo_id_activo actual a la bandera para que no repita
            //la creación de órdenes por un mismo activo
            bandera = activo;

        }

    };

    //Creacion de Orden_Has_Categoria_Servicio
    private void metodoCrearOrdenHasCategoria(PreparedStatement instruccionOrdenHasCategoria, int categoria, int registroOrdenCategoria) throws SQLException{

        //Cración de orden_has_categoria_servicio
        //Acá si se crean en cada iteración del WHILE de resultado.next() dado que la sentencia de consultarActivos
        //obtiene todas las categorías
        //orden_id_orden para registrar en orden_has_categoria_servicio se obtiene de la consulta con LAST_INSERT_ID()
        //LAST_INSERT_ID() toma el último id creado que es el de la orden
        instruccionOrdenHasCategoria.setInt(1, categoria); //Asigno el id_categoria al indice 1 del INSERT de orden has categoria
        instruccionOrdenHasCategoria.setString(2, currentDate()); //Asigno la fecha actual al índice 2 del INSERT de orden has categoria
                    
        //Ejecución de INSERT de orden_has_categoria_servicio
        registroOrdenCategoria = instruccionOrdenHasCategoria.executeUpdate(); // Ejecuto el INSERT de la relación en orden has categoria
                    
        System.out.println(registroOrdenCategoria + " Nuevo registro en Orden Has Categoria");

    }

                    

                    

}
