package cl.blackgps.back.threads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryThread implements Runnable {

    //Paso 1 - Cadena de conexió a mysql
    String url = "jdbc:mysql://localhost:3306/modulo_mantenimiento?useSSL=false&serverTimezone=UTC";

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
            String sql = "SELECT id_activo, id_vehiculo FROM activo";
            //Paso 5 - Ejecución del query
            ResultSet resultado = instruccion.executeQuery(sql);
            //Paso 6 - Procesamos el resultado
            while(resultado.next()){ // while para procesar cada registro
                //System.out.print("id_activo: " + resultado.getInt(1)); //getInt(1) para solicitadr el índice -> id_activo es el 1, id_vehiculo el 2 ...
                //System.out.println(" id_vehiculo: " + resultado.getInt(2));

                System.out.println("id_activo: " + resultado.getInt(1) + " id_vehiculo: " + resultado.getInt(2));
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
