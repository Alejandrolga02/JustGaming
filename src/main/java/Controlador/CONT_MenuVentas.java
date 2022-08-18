/*
 * Descripcion: Controlador para Inicio de Sesion
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Controlador;

import Vista.MenuVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class CONT_MenuVentas implements ActionListener, MouseListener {
    private MenuVentas vista;
    
    public CONT_MenuVentas(MenuVentas vista) {
        this.vista = vista;
        this.vista.btnVentas.addActionListener(this);
        this.vista.btnDetalles.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
    }
    
    public void iniciarVista() {
        vista.setTitle("Menu Ventas");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vista.btnVentas == evento.getSource()) {
            Vista.Ventas Nvista = new Vista.Ventas();
            Controlador.CONT_Ventas Ncontrolador = new Controlador.CONT_Ventas(Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
        } else if (vista.btnDetalles == evento.getSource()) {
            Vista.DetallesVentas Nvista = new Vista.DetallesVentas();
            Modelo.MDL_DetallesVentas Nmodelo = new Modelo.MDL_DetallesVentas();
            Controlador.CONT_DetallesVentas Ncontrolador = new CONT_DetallesVentas(Nmodelo, Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();        
        } else if (vista.btnRegresar == evento.getSource()) {
            Vista.MenuPrincipal Nvista = new Vista.MenuPrincipal();
            Controlador.CONT_MenuPrincipal Ncontrolador = new Controlador.CONT_MenuPrincipal(Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
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
