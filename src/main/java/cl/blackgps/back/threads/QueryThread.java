package cl.blackgps.back.threads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryThread implements Runnable {

    //Paso 1 - Cadena de conexió a mysql
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
    + "ORDER BY activo.id_activo DESC";

    private String command;

    public QueryThread(String command){
        this.command = command;
    }

    @Override
    public void run(){

        System.out.println(Thread.currentThread().getName() + " Inicio - Command = " + command);

        try {
            //Paso 2 - Creamos el objeto de conexión a la base de datos
            Connection conexion = DriverManager.getConnection(url, "root", "123456");
            //Paso 3 - Creamos un objeto Statement
            Statement instruccion = conexion.createStatement();
            //Paso 4 - Creamos el query
            //String sql = "SELECT id_activo, id_vehiculo FROM activo";
            String sql = consultarActivos;
            //Paso 5 - Ejecución del query
            ResultSet resultado = instruccion.executeQuery(sql);
            //Paso 6 - Procesamos el resultado
            while(resultado.next()){ // while para procesar cada registro
                //System.out.print("id_activo: " + resultado.getInt(1)); //getInt(1) para solicitadr el índice -> id_activo es el 1, id_vehiculo el 2 ...
                //System.out.println(" id_vehiculo: " + resultado.getInt(2));

                //System.out.println("id_activo: " + resultado.getInt(1) + " id_vehiculo: " + resultado.getInt(2));
                System.out.println(
                    "activo.id_activo: " + resultado.getInt(1) +  " | " +
                    " activo.id_vehiculo: " + resultado.getInt(2) +  " | " +
                    " activo.area_id_area: " + resultado.getInt(3) +  " | " + 
                    " activo.bodega_activos_id_bodega_activos: " + resultado.getInt(4) +  " | " + 
                    " BODEGA: " + resultado.getString(5) +  " | " +  
                    " activo.tipo_activo_id_tipo_activo: " + resultado.getInt(6) +  " | " + 
                    " TIPO: " + resultado.getString(7) +  " | " +  
                    " activo.anio: " + resultado.getInt(8) +  " | " +  
                    " plan_mantenimiento.id_plan_mantenimiento: " + resultado.getInt(9) +  " | " + 
                    " plan_mantenimiento.nombre: " + resultado.getString(10) +  " | " +  
                    " activo.dado_de_baja: " + resultado.getBoolean(11));

            }
            //Cerramos cada objeto que hemos utilizado
            resultado.close();
            instruccion.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        processCommand();
        System.out.println(Thread.currentThread().getName() + " Fin");




    }

    private void processCommand() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
