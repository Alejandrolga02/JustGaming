/*
Descripción: Creación del modelo para la tabla de clientes
Nombre: López Robles Jesús Damiel
Fecha: 13-agosto-2022
*/
package Modelo;

import static Modelo.Conexion.getConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    //Método para Eliminar a un cliente
    public boolean clinteEliminar(){
        return false;
    }
}
