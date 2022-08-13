/*
 * Descripcion: Modelo para Inicio de Sesion
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Modelo;

import static Modelo.Conexion.getConnection;
import java.sql.*;
import javax.swing.JOptionPane;

public class MDL_InicioSesion {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public void login(String user, String pass) {
        try {
            conn = getConnection(user, pass);
            String query = "SELECT usuario, contraseña FROM empleado WHERE usuario=\""+user+"\"";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                System.out.println("Usuario: " + rs.getString("usuario"));
                System.out.println("Contraseña: " + rs.getString("contraseña"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Datos erroneos");
            ex.printStackTrace(System.out);
            conn = null;
        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }        
    }
}