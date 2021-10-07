package cl.blackgps.back.threads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        + "(?," // --1?
        + "null, "
        + "null, "
        + "1, " // 1 es el Tipo Preventivo
        + "?, " // --2?
        + "null, "
        + "null, "
        + "null, "
        + "null)"; // Agregar relaciones con estado y categorías

    private String command;

    public QueryThread(String command) {
        this.command = command;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " Inicio = " + command);

        //Para saber cuántos registros han sido insertados
        int rows = 0;

        try {

            // Paso 2 - Creamos el objeto de conexión a la base de datos
            Connection conexion = DriverManager.getConnection(url, "root", "123456");
            // Paso 3 - Creamos un objeto Statement y el objeto PreparedStatement
            Statement instruccion = conexion.createStatement();
            PreparedStatement stmt = conexion.prepareStatement(crearOrden);
            // Paso 4 - Creamos el query
            // String sql = "SELECT id_activo, id_vehiculo FROM activo";
            String sql = consultarActivos;
            // Paso 5 - Ejecución del query
            ResultSet resultado = instruccion.executeQuery(sql);
            // Paso 6 - Procesamos el resultado

            while (resultado.next()) { // while para procesar cada registro

                if (resultado.getBoolean(13) && !resultado.getBoolean(19)) {

                    System.out.println(
                        "ACTIVO: " + resultado.getInt(1) + " | " + 
                        "VEHÍCULO: " + resultado.getInt(2) + " | " + 
                        "PLAN: " + resultado.getString(10) + " | " + 
                        "CATEGORIA: " + resultado.getString(18) + " | " + 
                        "INICIO: " + resultado.getString(14) + " | " + 
                        "CADA: " + resultado.getString(15) + " | " + 
                        "FRECUENCIA: " + resultado.getString(16) + " | " + 
                        "FECHA ACTUAL: " + currentDate()
                    );

                    
                    stmt.setString(1, currentDate()); //Asigno la fecha actual al índice 1 del INSERT
                    stmt.setInt(2, resultado.getInt(1)); //Asigno el id_activo del SELECT al indice 2 del INSERT

                    /*System.out.println("Ejecutando query INSERT: " + crearOrden);
                    System.out.println("FECHA: " + currentDate());
                    System.out.println("ID-ACTIVO: " + resultado.getInt(1));*/

                    rows = stmt.executeUpdate(); // Ejecuto el INSERT de una nueva órden
                    System.out.println(rows + " Registro creado en la tabla orden");

                }

                /*
                 * System.out.println( "activo.id_activo: " + resultado.getInt(1) + " | " +
                 * " activo.id_vehiculo: " + resultado.getInt(2) + " | " +
                 * " activo.area_id_area: " + resultado.getInt(3) + " | " +
                 * " activo.bodega_activos_id_bodega_activos: " + resultado.getInt(4) + " | " +
                 * " BODEGA: " + resultado.getString(5) + " | " +
                 * " activo.tipo_activo_id_tipo_activo: " + resultado.getInt(6) + " | " +
                 * " TIPO: " + resultado.getString(7) + " | " + " activo.anio: " +
                 * resultado.getInt(8) + " | " + " plan_mantenimiento.id_plan_mantenimiento: " +
                 * resultado.getInt(9) + " | " + " plan_mantenimiento.nombre: " +
                 * resultado.getString(10) + " | " + " plan_mantenimiento.por_hora: " +
                 * resultado.getBoolean(11) + " | " + " plan_mantenimiento.por_km: " +
                 * resultado.getBoolean(12) + " | " + " plan_mantenimiento.por_periodo: " +
                 * resultado.getBoolean(13) + " | " +
                 * " plan_mantenimiento_has_categoria_servicio.periodo_fecha: " +
                 * resultado.getString(14) + " | " +
                 * " plan_mantenimiento_has_categoria_servicio.periodo_cada: " +
                 * resultado.getInt(15) + " | " +
                 * " plan_mantenimiento_has_categoria_servicio.periodo_frecuencia: " +
                 * resultado.getString(16) + " | " +
                 * " categoria_servicio.id_categoria_servicio: " + resultado.getInt(17) + " | "
                 * + " categoria_servicio.nombre: " + resultado.getString(18) + " | " +
                 * " activo.dado_de_baja: " + resultado.getBoolean(19));
                 */

            }

            // Cerramos cada objeto que hemos utilizado
            resultado.close();
            instruccion.close();
            stmt.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        processCommand();
        System.out.println(Thread.currentThread().getName() + " Fin");

    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String currentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //System.out.println("yyyy-MM-dd HH:mm:ss-> "+dtf.format(LocalDateTime.now()));
        return dtf.format(LocalDateTime.now());
    }

}
