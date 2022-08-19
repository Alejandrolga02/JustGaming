/*
 * Descripcion: Conexion con la base de datos usando JDBC
 * Autor: Alejandro Ivan Lizarraga Rojas
 * Fecha: 11 08 2022
 */
package Modelo;

import java.sql.*;

public class Conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/justgaming?useSSL=false&useTimezone=true&serverTimezone=GMT-6&allowPublicKeyRetrieval=true";
    private static String USER;
    private static String USER_PASS;
    private static int USER_ID;
    private static int USER_ROL;


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, "root", "");
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
    
    public static void setUSER(String USER, String USER_PASS) {
        Conexion.USER = USER;
        Conexion.USER_PASS = USER_PASS;
    }
    
    public static String getUSER() {
        return USER;
    }

    public static String getUSER_PASS() {
        return USER_PASS;
    }
    
    public static int getUSER_ID() {
        return USER_ID;
    }

    public static void setUSER_ID(int USER_ID) {
        Conexion.USER_ID = USER_ID;
    }

    public static int getUSER_ROL() {
        return USER_ROL;
    }

    public static void setUSER_ROL(int USER_ROL) {
        Conexion.USER_ROL = USER_ROL;
    }
}
