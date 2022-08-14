/*
Descripción: Creación del modelo para la tabla de clientes
Nombre: López Robles Jesús Damiel
Fecha: 13-agosto-2022
*/
package Modelo;

import static Modelo.Conexion.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class MDL_Clientes {
    //Atributos de la clase
    Connection conn = null;
    
    //Método para insertar en la tabla clientes.
    public boolean clientesInsertar(String nombreCompleto, String telefono, String correoElectronico, 
                                    String domicilio){
        try{
            //Abrir la conexion
            conn = getConnection();
            //Preparando la instruccion
            String sql = "INSERT INTO cliente (nombreCompleto,telefono,correoElectronico,domicilio) VALUES ('" + nombreCompleto + "','" + 
                    telefono + "','" + correoElectronico + "','" + domicilio + "');";
            //Ejecucución de la sentencia
            Statement stm = conn.createStatement();
            int registro = stm.executeUpdate(sql); //Los Update regresan valores enteros
            //Cierre de la conexión
            Conexion.close(conn);
            return true;
        }catch(SQLException ex){
            return false;
        }
        
    }
    
    //Método para mostrar la tabla clientes
    public DefaultTableModel clientesConsultar(){
        try{
            //Abrir la conexion
            conn = getConnection();
            //Preparando la instruccion
            String sql = "SELECT * FROM cliente WHERE cliente.estatus = 1;";
            //Ejecucución de la sentencia
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            //Pasar los datos obtenidos a una tabla
            DefaultTableModel dtm = new DefaultTableModel();
            ResultSetMetaData rsMD = rs.getMetaData();
            
            int columnas = rsMD.getColumnCount(); //Nos da el numero de columnas
            //Ciclo para obtener las columnas
            for(int i = 1; i <= columnas; i++){
                dtm.addColumn(rsMD.getColumnLabel(i)); //Obtener los titulos de las columnas
            }
            
            // Ciclo para las filas
            while(rs.next()){
                Object[] fila = new Object[columnas];
                for(int i = 1; i < columnas; i++){
                    fila[i] = rs.getObject(i+1);
                }
                dtm.addRow(fila);
            }
            return dtm;
        }catch(SQLException ex){
            return null;
        }
    }
    
    
    //Método para Eliminar a un cliente
    public boolean clinteEliminar(int id){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Preparando la instrucción
            String sql = "UPDATE ON";
        }catch(SQLException ex){
            
        }
        return false;
    }
}
