/*
 * Descripcion: Modelo para Detalles ventas
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Modelo;

import static Modelo.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class MDL_DetallesVentas {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public DefaultTableModel consultar() {
        try {
            conn = getConnection();
            String query = "SELECT `ventas`.`idVenta`, `empleado`.`usuario`, `cliente`.`nombreCompleto`, `ventas`.`fecha`, `ventas`.`total`" +
                           "FROM `ventas` LEFT JOIN `empleado` ON `ventas`.`idEmpleado` = `empleado`.`idEmpleado`" +
                           "LEFT JOIN `cliente` ON `ventas`.`idCliente` = `cliente`.`idCliente`;";
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
    
    public DefaultTableModel consultarDetalles(int id) {
        try {
            conn = getConnection();
            
            String query = "SELECT `ventas_detalle`.`idServicio`, `servicios`.`servicio` FROM `ventas_detalle` LEFT JOIN `servicios` ON `ventas_detalle`.`idServicio` = `servicios`.`idServicio` WHERE ventas_detalle.idVentas = "+id+";";
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
