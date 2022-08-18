/*
Descripción: Creación del modelo para la realización de compras
Nombre: López Robles Jesús Daniel
Fecha: 18-agosto-2022
*/
package Modelo;

import static Modelo.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MDL_Compras {
    //Atributos de la clase
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    //Método para la inserción en la tabla compra
    public boolean comprasInsertar(int idproveedor, int idempleado, java.sql.Date fecha, java.sql.Time hora, float total){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Preparar la sentencia sql
            String sql = "INSERT INTO compras VALUES (" + idproveedor + "," + idempleado + ",'" + fecha + "','" + hora + "'," + total + ");";
            //Ejecución de la sentencia
            stm = conn.prepareStatement(sql);
            int result = stm.executeUpdate(sql);
            //Cierre de la conexión
            Conexion.close(conn);
            Conexion.close(rs);
            Conexion.close(stm);
            return true;
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
            conn = null;
            return false;
        }
    }
    
    //Método para la inserción en la tabla detallesCompras
    public boolean detallesComprasinsertar(int idcompra, int idinsumo, int cantidad){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Preparar la sentencia sql
            String sql = "INSERT INTO compra_detalles VALUES (" + idcompra + "," + idinsumo + "," + cantidad + ");";
            //Ejecución de la sentencia
             stm = conn.prepareStatement(sql);
            int result = stm.executeUpdate(sql);
            //Cierre de la conexión
             Conexion.close(conn);
            Conexion.close(rs);
            Conexion.close(stm);
            return true;
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
            conn = null;
            return false;
        }
    }
}
