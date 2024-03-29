/*
Descripción: Creación del modelo para la tabla de insumos
Nombre: López Robles Jesús Damiel
Fecha: 13-agosto-2022
*/
package Modelo;

import static Modelo.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MDL_Insumos {
    //Atributo de la clase
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    //Método para insertar en insumos
    public boolean insumosInsertar(String nombre, int cantidad, float costo){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Preparando la instrucción
            String sql = "INSERT INTO insumos (nombre,cantidad,costo) VALUES ('" + nombre + "','" + cantidad + "','" + costo + "');";
            //Ejecución de la sentencia
            Statement stm = conn.createStatement();
            int register = stm.executeUpdate(sql);
            //Cierre de la conexión
            Conexion.close(conn);
            return true;
        }catch(SQLException ex){
            return false;
        }
    }
    
    //Método para consultar la tabla de insumos
    public DefaultTableModel insumosConsultar(int id, String nombre){
        try{
            //Abrir la conexión
            conn = getConnection();
            String sql = null;
            if(id == 0){
                sql = "SELECT insumos.idinsumos AS ID, insumos.nombre AS INSUMO, insumos.cantidad AS CANTIDAD, insumos.costo AS COSTO FROM insumos WHERE insumos.estatus = 1;";
            }else if(id == 1){
                sql = "SELECT insumos.idinsumos AS ID, insumos.nombre AS INSUMO, insumos.cantidad AS CANTIDAD, insumos.costo AS COSTO FROM insumos WHERE insumos.estatus = 1 AND LOWER(insumos.nombre) LIKE LOWER('%"+nombre+"%');";
            }
            //Ejecución de la sentencias sql
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            //Pasar los datos obtenidos a una tabla
            DefaultTableModel dtm = new DefaultTableModel();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            int columnas = rsmd.getColumnCount(); //Nos da el numero de columnas de la tabla
            //ciclo para pasar los nombres de las columnas
            for(int i = 1;i <= columnas;i++){
                dtm.addColumn(rsmd.getColumnLabel(i)); //Obtener el nombre de la columnas
            }
            
            //Ciclo para conseguir las filas
            while(rs.next()){
                Object[] fila = new Object[columnas];
                for(int i = 0; i < columnas; i++){
                    fila[i] = rs.getObject(i+1);
                }
                dtm.addRow(fila);
            }
            if (dtm.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No hay resultados");
                return insumosConsultar(0, "");
            }
            return dtm;
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
            return null;
        }
    }
    
    //Método para eliminar un insumo
    public boolean insumosEliminar(int id){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Preparando la instrucción
            String sql = "UPDATE insumos SET insumos.estatus = 0 WHERE insumos.idinsumos = " + id + ";";
            //Ejecución de la sentencia
            Statement stm = conn.createStatement();
            int register = stm.executeUpdate(sql);
            //Cierre de la conexión
            Conexion.close(conn);
            return true;
        }catch(SQLException ex){
            return false;
        }
    }
    
    //Método para actualizar datos
    public boolean insumosActualizar(int id,String nombre, int cantidad, float costo){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Preparando la instrucción
            String sql = "UPDATE insumos SET nombre = '" + nombre + "', cantidad = " + cantidad + ", costo = " + costo 
                    + "WHERE idinsumos = " + id + ";";
            //Ejecución de la sentencia
            Statement stm = conn.createStatement();
            int register = stm.executeUpdate(sql);
            //Cierre de la conexión
            Conexion.close(conn);
            return true;
        }catch(SQLException ex){
            return false;
        }
    }
}
