/*
 * Descripcion: Clase para utilidades
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 26 de Junio de 2022
 */

package JustGaming;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Utilidades {
    public void conectar() {
        Connection con;
        
        try{
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            String cadenaDeConexion = "jdbc:mysql://127.0.0.1:3306/JustGaming";
            String usuario = txtUsuario.getText();
            String contraseña = txtContraseña.getText();
            con = DriverManager.getConnection(cadenaDeConexion,usuario,contraseña);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se puede conectar");
        }
    }
}
