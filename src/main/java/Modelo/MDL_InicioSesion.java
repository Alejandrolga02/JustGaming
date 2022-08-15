/*
 * Descripcion: Modelo para Inicio de Sesion
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Modelo;

import static Modelo.Conexion.getConnection;
import java.sql.*;

public class MDL_InicioSesion {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public boolean login() {
        try {
            conn = getConnection();
            String query = "SELECT idEmpleado, usuario FROM empleado WHERE usuario=\""+Conexion.getJDBC_USER()+"\"";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Conexion.setUSER_ID(rs.getInt("idEmpleado"));
            }
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
            
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            conn = null;
            return false;
        }      
    }
}