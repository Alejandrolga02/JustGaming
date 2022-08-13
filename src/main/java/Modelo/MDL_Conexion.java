/*
 * Descripcion: MDL_Conexion con la base de datos usando JDBC
 * Autor: Alejandro Ivan Lizarraga Rojas
 * Fecha: 11 08 2022
 */
package Modelo;

import java.sql.*;

public class MDL_Conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/justgaming";

    public static Connection getConnection(String user, String pass) throws SQLException {
        return DriverManager.getConnection(JDBC_URL, user, pass);
    }
    
    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }
    
    public static void close(Statement stmt) throws SQLException{
        stmt.close();
    }
    
    public static void close(PreparedStatement stmt) throws SQLException{
        stmt.close();
    }
    
    public static void close(Connection conn) throws SQLException {
        conn.close();
    }
    
    /*public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/justgaming?useSSL=false&useTimezone=true&serverTimezone=GMT-6&allowPublicKeyRetrieval=true";
        try {
            Connection conexion = DriverManager.getConnection(url,"root","");
            Statement instruccion = conexion.createStatement();
            String query = "SELECT * FROM roles";
            ResultSet resultado = instruccion.executeQuery(query);
            while(resultado.next()) {
                System.out.println("Id Rol: " + resultado.getInt("idRol"));
                System.out.println("Rol: " + resultado.getString("nombre"));
            }
            resultado.close();
            instruccion.close();
            conexion.close();
        } catch(SQLException ex) {
            ex.printStackTrace(System.out);
        }        
    }*/
}
