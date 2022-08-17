/*
Descripción: Creación del modelo para la tabla de empleados
Nombre: López Robles Jesús Damiel
Fecha: 13-agosto-2022
*/
package Modelo;

import static Modelo.Conexion.getConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class MDL_Empleados {
    //Atributos necesarios
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    //Método para insertar en la tabla de empleados
    public boolean empleadoInsertar(String nombre, String apellido, String telefono, String domicilio, java.sql.Date fechaNacimiento, String usuario,
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
            String query = "SELECT empleado.idEmpleado,empleado.nombre,empleado.apellido,empleado.telefono,empleado.domicilio,empleado.fechaNacimiento,"
                    + "empleado.usuario,empleado.idRol FROM empleado INNER JOIN roles ON empleado.idRol = roles.idRol WHERE empleado.estatus = 1;";
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
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
            return null;
        }
    }
    
    //Método para elimnar 
    public boolean empleadoEliminar(int id){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Preparando la instrucción
            String sql = "UPDATE empleado SET empleado.estatus = 0 WHERE empleado.idEmpleado = " + id + ";";
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
    public boolean empleadoActualizar(int Idempleado,String nombre, String apellido, String telefono, String domicilio, java.sql.Date fechaNacimiento, int idrol){
        try{
            //Abrir conexión
            conn = getConnection();
            //Preparando la instrucción
            String sql = "UPDATE empleado SET nombre= '" + nombre + "',apellido= '" + apellido 
                    + "',telefono= '" + telefono + "',domicilio= '" + domicilio + "','fechaNacimiento= '" + fechaNacimiento + 
                    "', idRol= " + idrol + " WHERE idEmpleado = '" + Idempleado + "';";
            //Ejecución de la sentencia
            stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate(sql);
            //Cierre de la conexión
            Conexion.close(conn);
            return true;
        }catch(SQLException ex){
            return false;
        }
    }
}
