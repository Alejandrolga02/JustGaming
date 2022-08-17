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
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class MDL_DetallesCompras {
    //Atributos de la clase
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    //Método para obtner la tabla de compras
    public DefaultTableModel consultarCompras(){
        try{
            //Abrir la conexion
            conn = getConnection();
            //Crear la consulta
            
            
            return null;
        }catch(SQLException e){
            return null;
        }
    }
    
    
    //Método para obtener la tabla de detallesCompras
    public DefaultTableModel consultarDetallescompras(){
        return null;
    }
}
