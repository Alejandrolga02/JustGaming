/*
 * Descripcion: Modelo para MDL_Ventas
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Modelo;

import static Modelo.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class MDL_Ventas {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public boolean añadir() {
        
    }
}
