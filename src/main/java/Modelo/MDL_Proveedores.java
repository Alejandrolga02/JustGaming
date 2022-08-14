/*
 * Descripcion: Modelo para Inicio de Sesion
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Modelo;

import static Modelo.Conexion.getConnection;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class MDL_Proveedores {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public boolean ingresar(String nombre, String telefono, String direccion) {
        try {
            conn = getConnection();
            String query = "INSERT INTO proveedores(`nombre`, `telefono`, `direccion`) VALUES ('"+nombre+"', '"+telefono+"', '"+direccion+"')";
            stmt = conn.prepareStatement(query);
            int result = stmt.executeUpdate(query);
                        
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
    
    public boolean actualizar(String nombre, String telefono, String direccion, int id) {
        try {
            conn = getConnection();
            String query = "UPDATE proveedores SET nombre='"+nombre+"', telefono='"+telefono+"', direccion='"+direccion+"' WHERE idProveedores = "+id+"";
            stmt = conn.prepareStatement(query);
            int result = stmt.executeUpdate(query);
                        
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
    
    public boolean borrar(int id) {
        try {
            conn = getConnection();
            String query = "UPDATE proveedores SET estatus=0 WHERE idProveedores = "+id+"";
            stmt = conn.prepareStatement(query);
            int result = stmt.executeUpdate(query);
                        
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
    
    public DefaultTableModel consultar() {
        try {
            conn = getConnection();
            String query = "SELECT idProveedores, nombre, telefono, direccion FROM proveedores WHERE estatus = 1;";
            stmt = conn.prepareStatement(query);
            
            rs = stmt.executeQuery();
            ResultSetMetaData rsMd =  rs.getMetaData();
            DefaultTableModel dtm = new DefaultTableModel();    
            
            int columnas = rsMd.getColumnCount();
            for(int i=1; i <= columnas; i++){  // sirve para obtener los nombres de cada columna (encabezado)
                dtm.addColumn(rsMd.getColumnLabel(i));
            }
            
            while(rs.next()){
                Object[] fila = new Object[columnas];
                for(int i=0; i< columnas; i++){
                    fila[i] = rs.getObject(i+1);
                }
                dtm.addRow(fila);
            }
            
            return dtm;
        } catch (SQLException e) {
            return null;
        }
    }    
}
