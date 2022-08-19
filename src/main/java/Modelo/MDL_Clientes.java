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
    public DefaultTableModel clientesConsultar(int id, String nombre){
        try{
            //Abrir la conexion
            conn = getConnection();
            //Preparando la instruccion
            String sql = null;
            if (id == 0) { // CONSULTA GENERAL
                sql = "SELECT idCliente AS ID,nombreCompleto AS 'NOMBRE COMPLETO',telefono AS 'TELFEFONO',correoElectronico AS 'CORREO ELECTRONICO',domicilio AS DOMICILIO FROM cliente WHERE cliente.estatus = 1;";
            } else if (id == 1){ // NOMBRE
                sql = "SELECT idCliente AS ID,nombreCompleto AS 'NOMBRE COMPLETO',telefono AS 'TELFEFONO',correoElectronico AS 'CORREO ELECTRONICO',domicilio AS DOMICILIO FROM cliente WHERE cliente.estatus = 1 AND LOWER(cliente.nombreCompleto) LIKE LOWER('%"+nombre+"%');";
            }
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
                for(int i = 0; i < columnas; i++){
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
    public boolean clienteEliminar(int id){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Preparando la instrucción
            String sql = "UPDATE cliente SET cliente.estatus = 0 WHERE cliente.idCliente = " + id + ";";
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
    public boolean clienteActualizar (int idCliente, String nombreCompleto, String telefono, String correoElectronico, String domicilio){
        try{
            //Abrir la conexion
            conn = getConnection();
            //Preparando la instruccion
            String sql = "UPDATE cliente SET nombreCompleto = '" + nombreCompleto + "', telefono = '" + telefono + "', correoElectronico = '" + 
                    correoElectronico + "', domicilio = '" + domicilio + "' WHERE idCliente = " + idCliente + ";";
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
}
