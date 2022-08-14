/*
Descripción: Creación del contolador para la tabla de clientes
Nombre: López Robles Jesús Damiel
Fecha: 13-agosto-2022
*/
package Controlador;

import Modelo.MDL_Clientes;
import Vista.Clientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class CONT_Clientes implements ActionListener, MouseListener{
    //Atributos necesarios
    MDL_Clientes modelo;
    Clientes vista;
    
    //Constructor de parametros
    public CONT_Clientes(MDL_Clientes modelo, Clientes vista){
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnIngresar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.tblClientes.addMouseListener(this);
    }
    
    //Método para iniciar la vista
    public void iniciarVista(){
        vista.setTitle("Servicios");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.tblClientes.setModel(modelo.clientesConsultar());
        vista.setVisible(true);
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
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
