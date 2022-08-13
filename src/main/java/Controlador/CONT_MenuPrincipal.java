/*
 * Descripcion: Controlador para Menu Principal
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Controlador;

import Modelo.Conexion;
import Vista.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CONT_MenuPrincipal implements ActionListener, MouseListener{
    private MenuPrincipal vista;

    public CONT_MenuPrincipal(MenuPrincipal vista) {
        this.vista = vista;
        this.vista.btnVentas.addActionListener(this);
        this.vista.btnInsumos.addActionListener(this);
        this.vista.btnClientes.addActionListener(this);
        this.vista.btnCompras.addActionListener(this);
        this.vista.btnEmpleados.addActionListener(this);
        this.vista.btnServicios.addActionListener(this);
        this.vista.btnLogout.addActionListener(this);
    }
    
    public void iniciarVista() {
        vista.setTitle("Menu Principal");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vista.btnVentas == evento.getSource()) {
            Vista.MenuVentas menuVentas = new Vista.MenuVentas();
            Controlador.CONT_MenuVentas CONT_menuVentas = new Controlador.CONT_MenuVentas(menuVentas);
            CONT_menuVentas.iniciarVista();
            vista.dispose();
        } else if (vista.btnInsumos == evento.getSource()) {
            Vista.Insumos insumos = new Vista.Insumos();
            Controlador.Insumos CONT_insumos = new ControladorInsumos(menuVentas);
            CONT_insumos.iniciarVista();
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
