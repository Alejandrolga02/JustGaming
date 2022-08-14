/*
Descripción: Creación del modelo para la tabla de empleados
Nombre: López Robles Jesús Damiel
Fecha: 13-agosto-2022
*/
package Modelo;

import static Modelo.Conexion.getConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class MDL_Empleados {
    //Atributos necesarios
    Connection conn = null;
    
    //Método para insertar en la tabla de empleados
    public boolean empleadoInsertar(String nombre, String apellido, String telefono, String domicilio, Date fechaNacimiento, String usuario,
            int idRol){
        try{
            //Abrit la conexión
            conn = getConnection();
            //Preparando la instrucción
            String sql = "INSERT INTO empleado (nombre, apellido, telefono, domicilio, fechaNacimiento, usuario, idRol) VALUES ('" + 
                    nombre + "','" + apellido + "','" + telefono + "','" + domicilio + "','" + fechaNacimiento + "','" + usuario + "','" 
                    + idRol + "');";
            //Ejecución de la sentencia
            Statement stm = conn.createStatement();
            int registro = stm.executeUpdate(sql);
            //Cierre de la conexion
            Conexion.close(conn);
            return true;
        }catch(SQLException ex){
            return false;
        }
    }
    
    //Método para mostrar la tabla
    public DefaultTableModel empleadoConsultar(){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Preparando la consulta
            String sql = "SELECT empleado.idEmpleado,empleado.nombre,empleado.apellido,empleado.telefono,empleado.domicilio,empleado.fechaNacimiento,"
                    + "empelado.usuario,roles.rol,empleado.idRol FROM empleado INNER JOIN roles ON empleado.idRol = roles.idRol WHERE empleado.estatus = 1;";
            //Ejecución de la sentencia
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            //Pasar los datos obtenidos a la tabla
            DefaultTableModel dtm = new DefaultTableModel();
            ResultSetMetaData rsMd = rs.getMetaData();
            int columnas = rsMd.getColumnCount(); //Nos da el numeor de columnas
            //Ciclo plara obtener las columnas
            for(int i = 1; i <= columnas; i++){
                dtm.addColumn(rsMd.getColumnLabel(i)); //Obtener los nombres de las columnas
            }
            //Ciclo para las filas
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
    
    //Método para elimnar 
    public boolean empleadoEliminar(int id){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Preparando la instrucción
            String sql = "UPDATE empleado SET empelado.estatus = 0 WHERE cliente.idCliente = " + id + ";";
            //Ejecución de la sentencia
            Statement stm = conn.createStatement();
            int registro = stm.executeUpdate(sql);
            //Cierre de la conexión
            Conexion.close(conn);
            return true;
        }catch(SQLException ex){
            return false;
        }
    }
    
    //Método para actualizar
    public boolean empleadoActualizar(int Idempleado,String nombre, String apellido, String telefono, String domicilio, Date fechaNacimiento, int idrol){
        try{
            //Abrir conexión
            conn = getConnection();
            //Preparando la instrucción
            String sql = "UPDATE empleado SET idEmpleado = '" + Idempleado + "',nombre = '" + nombre + "',apellido = '" + apellido + "',telefono = '"
                    + telefono + "',domicilio = '" + domicilio + "','fechaNacimiento = '" + fechaNacimiento + "',idRol= '" + idrol + "' "
                    + "WHERE idEmpleado = " + Idempleado + ";";
            //Ejecución de la sentencia
            Statement stm = conn.createStatement();
            int registro = stm.executeUpdate(sql);
            //Cierre de la conexión
            Conexion.close(conn);
            return true;
        }catch(SQLException ex){
            return false;
        }
    }
}
