/*
 * Descripcion: Controlador para Inicio de Sesion
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Controlador;

import Modelo.Conexion;
import Modelo.MDL_InicioSesion;
import Vista.InicioSesion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CONT_InicioSesion implements ActionListener, MouseListener{
    private MDL_InicioSesion modelo;
    private InicioSesion vista;
    
    public CONT_InicioSesion(MDL_InicioSesion modelo,InicioSesion vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btnEntrar.addActionListener(this);
    }
    
    public void iniciarVista() {
        vista.setTitle("Iniciar Sesión");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }   

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vista.btnEntrar == evento.getSource()) {
            //Conexion.setJDBC(vista.txtUsuario.getText(), vista.txtPass.getText());
            Conexion.setUSER(vista.txtUsuario.getText(), vista.txtPass.getText());
            
            if(modelo.login()) {
                JOptionPane.showMessageDialog(null, "Sesión Iniciada Correctamente");
                Vista.MenuPrincipal Nvista = new Vista.MenuPrincipal();
                Controlador.CONT_MenuPrincipal Ncontrolador = new Controlador.CONT_MenuPrincipal(Nvista);
                Ncontrolador.iniciarVista();
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Datos erroneos");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
