/*
Descripción: Modelo de la vista de Detalles ventas
Nombre: López Robles Jesús Daniel
Fecha: 16-agosto-2022
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

public class MDL_DetallesCompras {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public DefaultTableModel consultar(int id, String empleado, String proveedor, String fecha) {
        try {
            conn = getConnection();
            String query = null;
            
            switch (id) {
                case 0:
                    // TODO VACIO
                    query = "SELECT compras.idcompras as ID, empleado.usuario as EMPLEADO, proveedores.nombre as PROVEEDOR, compras.fecha as FECHA, compras.hora as HORA, compras.total as TOTAL FROM compras LEFT JOIN empleado ON compras.idEmpleado = empleado.idEmpleado LEFT JOIN proveedores ON compras.idProveedor = proveedores.idProveedores;";
                    break;

                case 1:
                    // FECHA
                    query = "SELECT compras.idcompras as ID, empleado.usuario as EMPLEADO, proveedores.nombre as PROVEEDOR, compras.fecha as FECHA, compras.hora as HORA, compras.total as TOTAL FROM compras LEFT JOIN empleado ON compras.idEmpleado = empleado.idEmpleado LEFT JOIN proveedores ON compras.idProveedor = proveedores.idProveedores WHERE compras.fecha LIKE '%"+fecha+"%';";
                    break;

                case 2:
                    // CLIENTE
                    query = "SELECT compras.idcompras as ID, empleado.usuario as EMPLEADO, proveedores.nombre as PROVEEDOR, compras.fecha as FECHA, compras.hora as HORA, compras.total as TOTAL FROM compras LEFT JOIN empleado ON compras.idEmpleado = empleado.idEmpleado LEFT JOIN proveedores ON compras.idProveedor = proveedores.idProveedores WHERE LOWER(proveedores.nombre) LIKE LOWER('%"+proveedor+"%');";
                    break;
            
                case 3:
                    // FECHA CLIENTE
                    query = "SELECT compras.idcompras as ID, empleado.usuario as EMPLEADO, proveedores.nombre as PROVEEDOR, compras.fecha as FECHA, compras.hora as HORA, compras.total as TOTAL FROM compras LEFT JOIN empleado ON compras.idEmpleado = empleado.idEmpleado LEFT JOIN proveedores ON compras.idProveedor = proveedores.idProveedores WHERE LOWER(proveedores.nombre) LIKE LOWER('%"+proveedor+"%') AND compras.fecha LIKE '%"+fecha+"%';";                    
                    break;
            
                case 4:
                    // EMPLEADO
                    query = "SELECT compras.idcompras as ID, empleado.usuario as EMPLEADO, proveedores.nombre as PROVEEDOR, compras.fecha as FECHA, compras.hora as HORA, compras.total as TOTAL FROM compras LEFT JOIN empleado ON compras.idEmpleado = empleado.idEmpleado LEFT JOIN proveedores ON compras.idProveedor = proveedores.idProveedores WHERE LOWER(empleado.usuario) LIKE LOWER('%"+empleado+"%');";
                    break; 
            
                case 5:
                    // EMPLEADO FECHA
                    query = "SELECT compras.idcompras as ID, empleado.usuario as EMPLEADO, proveedores.nombre as PROVEEDOR, compras.fecha as FECHA, compras.hora as HORA, compras.total as TOTAL FROM compras LEFT JOIN empleado ON compras.idEmpleado = empleado.idEmpleado LEFT JOIN proveedores ON compras.idProveedor = proveedores.idProveedores WHERE LOWER(empleado.usuario) LIKE LOWER('%"+empleado+"%') AND compras.fecha LIKE '%"+fecha+"%';";
                    break;
            
                case 6:
                    // EMPLEADO CLIENTE
                    query = "SELECT compras.idcompras as ID, empleado.usuario as EMPLEADO, proveedores.nombre as PROVEEDOR, compras.fecha as FECHA, compras.hora as HORA, compras.total as TOTAL FROM compras LEFT JOIN empleado ON compras.idEmpleado = empleado.idEmpleado LEFT JOIN proveedores ON compras.idProveedor = proveedores.idProveedores WHERE LOWER(proveedores.nombre) LIKE LOWER('%"+proveedor+"%') AND LOWER(empleado.usuario) LIKE LOWER('%"+empleado+"%');";
                    break;
            
                case 7:
                    // EMPLEADO CLIENTE FECHA
                    query = "SELECT compras.idcompras as ID, empleado.usuario as EMPLEADO, proveedores.nombre as PROVEEDOR, compras.fecha as FECHA, compras.hora as HORA, compras.total as TOTAL FROM compras LEFT JOIN empleado ON compras.idEmpleado = empleado.idEmpleado LEFT JOIN proveedores ON compras.idProveedor = proveedores.idProveedores WHERE LOWER(proveedores.nombre) LIKE LOWER('%"+proveedor+"%') AND compras.fecha LIKE '%"+fecha+"%' AND LOWER(empleado.usuario) LIKE LOWER('%"+empleado+"%');";                    
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
            
            String query = "SELECT compra_detalles.idInsumo AS ID, insumos.nombre AS INSUMO, compra_detalles.cantidad AS CANTIDAD FROM compra_detalles LEFT JOIN insumos ON compra_detalles.idInsumo = insumos.idinsumos WHERE compra_detalles.idCompra = "+id+";";
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
