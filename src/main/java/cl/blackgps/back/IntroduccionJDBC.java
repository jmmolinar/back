package cl.blackgps.back;

import java.sql.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntroduccionJDBC {
    public static void mani(String[] args){

        SpringApplication.run(IntroduccionJDBC.class, args);

        //Paso 1 - Creamos la cadena de conexió a mysql
        String url = "jdbc:mysql://localhost:3306/modulo_mantenimiento?useSSL=false&serverTimezone=UTC";

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
                System.out.print("id_activo: " + resultado.getInt(1)); //getInt(1) para solicitadr el índice -> id_activo es el 1, id_vehiculo el 2 ...
                System.out.println(" id_vehiculo: " + resultado.getInt(2));
            }
            //Cerramos cada objeto que hemos utilizado
            resultado.close();
            instruccion.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
