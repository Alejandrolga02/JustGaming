/*
 * Descripcion: Controlador para Menu Compras
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Controlador;

import Modelo.Conexion;
import Vista.MenuCompras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CONT_MenuCompras implements ActionListener, MouseListener {
    private MenuCompras vista;
    
    public CONT_MenuCompras (MenuCompras vista) {
        this.vista = vista;
        this.vista.btnCompras.addActionListener(this);
        this.vista.btnDetalles.addActionListener(this);
        this.vista.btnProveedores.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
    }
    
    public void iniciarVista() {
        vista.setTitle("Menu Compras");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vista.btnCompras == evento.getSource()) {
            if (Conexion.getUSER_ID() == 1) {
                Vista.Compras Nvista = new Vista.Compras();
                Controlador.CONT_Compras Ncontrolador = new Controlador.CONT_Compras(Nvista);
                Ncontrolador.iniciarVista();
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No tienes permiso para entrar");
            }
        } else if (vista.btnDetalles == evento.getSource()) {
            if (Conexion.getUSER_ID() == 1) {            
                Vista.DetallesCompras Nvista = new Vista.DetallesCompras();
                Modelo.MDL_DetallesCompras Nmodelo = new Modelo.MDL_DetallesCompras();
                Controlador.CONT_DetallesCompras Ncontrolador = new CONT_DetallesCompras(Nmodelo, Nvista);
                Ncontrolador.iniciarVista();
                vista.dispose(); 
            } else {
                JOptionPane.showMessageDialog(null, "No tienes permiso para entrar");
            }                
        } else if (vista.btnProveedores == evento.getSource()) {
            if (Conexion.getUSER_ID() == 1) {            
                Vista.Proveedores Nvista = new Vista.Proveedores();
                Modelo.MDL_Proveedores Nmodelo = new Modelo.MDL_Proveedores();
                Controlador.CONT_Proveedores Ncontrolador = new CONT_Proveedores(Nmodelo, Nvista);
                Ncontrolador.iniciarVista();
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No tienes permiso para entrar");
            }            
        }else if (vista.btnRegresar == evento.getSource()) {
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
