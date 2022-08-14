/*
 * Descripcion: Modelo para MDL_Servicios
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Modelo;

import static Modelo.Conexion.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class MDL_Servicios {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public boolean ingresar(String nombre, float precio) {
        try {
            conn = getConnection();
            String query = "INSERT INTO servicios(`servicio`, `precio`) VALUES ('"+nombre+"',"+precio+")";
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
    
    public boolean actualizar(String nombre, float precio, int id) {
        try {
            conn = getConnection();
            String query = "UPDATE servicios SET servicio='"+nombre+"', precio="+precio+" WHERE idServicio = "+id+"";
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
            String query = "UPDATE servicios SET estatus=0 WHERE idServicio = "+id+"";
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
            String query = "SELECT idServicio, servicio, precio FROM servicios WHERE estatus = 1;";
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
