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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MDL_DetallesVentas {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public DefaultTableModel consultar(int id, String empleado, String cliente, String fecha) {
        try {
            conn = getConnection();
            String query = null;
            
            switch (id) {
                case 0:
                    // TODO VACIO
                    query = "SELECT ventas.idVenta as ID, empleado.usuario as EMPLEADO, cliente.nombreCompleto as CLIENTE, ventas.fecha as FECHA, ventas.hora as HORA, ventas.total as TOTAL FROM ventas LEFT JOIN empleado ON ventas.idEmpleado = empleado.idEmpleado LEFT JOIN cliente ON ventas.idCliente = cliente.idCliente;";
                    break;

                case 1:
                    // FECHA
                    query = "SELECT ventas.idVenta as ID, empleado.usuario as EMPLEADO, cliente.nombreCompleto as CLIENTE, ventas.fecha as FECHA, ventas.hora as HORA, ventas.total as TOTAL FROM ventas LEFT JOIN empleado ON ventas.idEmpleado = empleado.idEmpleado LEFT JOIN cliente ON ventas.idCliente = cliente.idCliente WHERE ventas.fecha LIKE '%"+fecha+"%';";
                    break;

                case 2:
                    // CLIENTE
                    query = "SELECT ventas.idVenta as ID, empleado.usuario as EMPLEADO, cliente.nombreCompleto as CLIENTE, ventas.fecha as FECHA, ventas.hora as HORA, ventas.total as TOTAL FROM ventas LEFT JOIN empleado ON ventas.idEmpleado = empleado.idEmpleado LEFT JOIN cliente ON ventas.idCliente = cliente.idCliente WHERE LOWER(cliente.nombreCompleto) LIKE LOWER('%"+cliente+"%');";
                    break;
            
                case 3:
                    // FECHA CLIENTE
                    query = "SELECT ventas.idVenta as ID, empleado.usuario as EMPLEADO, cliente.nombreCompleto as CLIENTE, ventas.fecha as FECHA, ventas.hora as HORA, ventas.total as TOTAL FROM ventas LEFT JOIN empleado ON ventas.idEmpleado = empleado.idEmpleado LEFT JOIN cliente ON ventas.idCliente = cliente.idCliente WHERE LOWER(cliente.nombreCompleto) LIKE LOWER('%"+cliente+"%') AND ventas.fecha LIKE '%"+fecha+"%';";                    
                    break;
            
                case 4:
                    // EMPLEADO
                    query = "SELECT ventas.idVenta as ID, empleado.usuario as EMPLEADO, cliente.nombreCompleto as CLIENTE, ventas.fecha as FECHA, ventas.hora as HORA, ventas.total as TOTAL FROM ventas LEFT JOIN empleado ON ventas.idEmpleado = empleado.idEmpleado LEFT JOIN cliente ON ventas.idCliente = cliente.idCliente WHERE LOWER(empleado.usuario) LIKE LOWER('%"+empleado+"%');";
                    break; 
            
                case 5:
                    // EMPLEADO FECHA
                    query = "SELECT ventas.idVenta as ID, empleado.usuario as EMPLEADO, cliente.nombreCompleto as CLIENTE, ventas.fecha as FECHA, ventas.hora as HORA, ventas.total as TOTAL FROM ventas LEFT JOIN empleado ON ventas.idEmpleado = empleado.idEmpleado LEFT JOIN cliente ON ventas.idCliente = cliente.idCliente WHERE LOWER(empleado.usuario) LIKE LOWER('%"+empleado+"%') AND ventas.fecha LIKE '%"+fecha+"%';";
                    break;
            
                case 6:
                    // EMPLEADO CLIENTE
                    query = "SELECT ventas.idVenta as ID, empleado.usuario as EMPLEADO, cliente.nombreCompleto as CLIENTE, ventas.fecha as FECHA, ventas.hora as HORA, ventas.total as TOTAL FROM ventas LEFT JOIN empleado ON ventas.idEmpleado = empleado.idEmpleado LEFT JOIN cliente ON ventas.idCliente = cliente.idCliente WHERE LOWER(cliente.nombreCompleto) LIKE LOWER('%"+cliente+"%') AND LOWER(empleado.usuario) LIKE LOWER('%"+empleado+"%');";
                    break;
            
                case 7:
                    // EMPLEADO CLIENTE FECHA
                    query = "SELECT ventas.idVenta as ID, empleado.usuario as EMPLEADO, cliente.nombreCompleto as CLIENTE, ventas.fecha as FECHA, ventas.hora as HORA, ventas.total as TOTAL FROM ventas LEFT JOIN empleado ON ventas.idEmpleado = empleado.idEmpleado LEFT JOIN cliente ON ventas.idCliente = cliente.idCliente WHERE LOWER(cliente.nombreCompleto) LIKE LOWER('%"+cliente+"%') AND ventas.fecha LIKE '%"+fecha+"%' AND LOWER(empleado.usuario) LIKE LOWER('%"+empleado+"%');";                    
                    break;
                default:
                    break;
            }

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
                        
            if (dtm.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No hay resultados");
                consultarDetalles(0);
                return consultar(0, "", "", "");
            }
            
            return dtm;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    
    public DefaultTableModel consultarDetalles(int id) {
        try {
            conn = getConnection();
            
            String query = "SELECT ventas_detalle.idServicio AS ID, servicios.servicio AS INSUMO, ventas_detalle.cantidad AS CANTIDAD FROM ventas_detalle LEFT JOIN servicios ON ventas_detalle.idServicio = servicios.idServicio WHERE ventas_detalle.idVentas = "+id+";";
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
