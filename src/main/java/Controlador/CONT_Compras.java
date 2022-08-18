/*
Descripción: Creación del controlador para la realización de compras
Nombre: López Robles Jesús Daniel
Fecha: 18-agosto-2022
*/
package Controlador;

import static Modelo.Conexion.getConnection;
import Modelo.MDL_Compras;
import Vista.Compras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;

public class CONT_Compras implements ActionListener, MouseListener{
    //Atributos de la clase
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    //Creacion de los objetos
    Compras vista;
    MDL_Compras modelo;
    
    //Constructor de parametros
    public CONT_Compras(Compras vista, MDL_Compras modelo){
        this.vista = vista;
        this.modelo = modelo;
        this.vista.btnBorrar.addActionListener(this);
        this.vista.btnIngresar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.btnTerminar.addActionListener(this);
        this.vista.comboxProveedor.addActionListener(this);
        this.vista.comboxInsumo.addActionListener(this);
    }    
    
    //Método para iniciar la vista
    public void iniciarVista(){
        vista.setTitle("Compras");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        comboProveedores();
        comboInsumos();
        vista.setVisible(true);
    }
    
    //Método para limpiar las cajas de texto
    public void limpiarCajastexto(){
        vista.txtCantidad.setText("");
        vista.comboxInsumo.setSelectedIndex(0);
        vista.comboxProveedor.setSelectedIndex(0);
        this.vista.tblCompras.setModel(null);
    }
    
    //Método para obtener los proveedores en el comboBox
    public void comboProveedores(){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Prepara la sentencia sql
            String sql = "SELECT nombre FROM proveedores;";
            //Ejecución de la sentencia
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            //Pasar la información al cmobo box
            while(rs.next()){
                vista.comboxProveedor.addItem(rs.getString("nombre"));
            }
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
    
    //Método para obtener los insumos en el comboBox
    public void comboInsumos(){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Prepara la sentencia sql
            String sql = "SELECT nombre FROM insumos;";
            //Ejecución de la sentencia
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            //Pasar la información al cmobo box
            while(rs.next()){
                vista.comboxInsumo.addItem(rs.getString("nombre"));
            }
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
    
    
    
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseClicked(MouseEvent evento) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
