/*
Descripción: Creación del controlador para la realización de compras
Nombre: López Robles Jesús Daniel
Fecha: 18-agosto-2022
*/
package Controlador;

import Modelo.MDL_Compras;
import Vista.Compras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;

public class CONT_Compras implements ActionListener, MouseListener{
    //Atributos de la clase
    Connection conn = null;
    PreparedStatement stmt = null;
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
        
        vista.setVisible(true);
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseClicked(MouseEvent evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
