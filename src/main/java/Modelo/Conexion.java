/*
 * Descripcion: Conexion con la base de datos usando JDBC
 * Autor: Alejandro Ivan Lizarraga Rojas
 * Fecha: 11 08 2022
 */
package Modelo;

import java.sql.*;

public class Conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/justgaming?useSSL=false&useTimezone=true&serverTimezone=GMT-6&allowPublicKeyRetrieval=true";
    private static String JDBC_USER;
    private static String JDBC_PASS;
    private static int USER_ID;


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
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
    
    public static void setJDBC(String JDBC_USER, String JDBC_PASS) {
        Conexion.JDBC_USER = JDBC_USER;
        Conexion.JDBC_PASS = JDBC_PASS;
    }
    
    public static String getJDBC_USER() {
        return JDBC_USER;
    }
    
    public static int getUSER_ID() {
        return USER_ID;
    }

    public static void setUSER_ID(int USER_ID) {
        Conexion.USER_ID = USER_ID;
    }
}
