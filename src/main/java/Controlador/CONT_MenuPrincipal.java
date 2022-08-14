/*
 * Descripcion: Controlador para Menu Principal
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Controlador;

import Vista.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

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
            Vista.MenuVentas Nvista = new Vista.MenuVentas();
            Controlador.CONT_MenuVentas Ncontrolador = new Controlador.CONT_MenuVentas(Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
        } else if (vista.btnInsumos == evento.getSource()) {
            /*Vista.Insumos Nvista = new Vista.Insumos();
            Modelo.MDL_Insumos Nmodelo = new Modelo.MDL_Insumos();
            Controlador.CONT_Insumos Ncontrolador = new ControladorInsumos(Nmodelo, Nvista);
            Ncontrolador.iniciarVista();*/
            vista.dispose();
        } else if (vista.btnClientes == evento.getSource()) {
            Vista.Clientes Nvista = new Vista.Clientes();
            Modelo.MDL_Clientes Nmodelo = new Modelo.MDL_Clientes();
            Controlador.CONT_Clientes Ncontrolador = new Controlador.CONT_Clientes(Nmodelo, Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
        } else if (vista.btnCompras == evento.getSource()) {
            Vista.MenuCompras Nvista = new Vista.MenuCompras();
            Controlador.CONT_MenuCompras Ncontrolador = new Controlador.CONT_MenuCompras(Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
        } else if (vista.btnEmpleados == evento.getSource()) {
            /*Vista.Empleados Nvista = new Vista.Empleados();
            Modelo.MDL_Empleados Nmodelo = new Modelo.MDL_Empleados();
            Controlador.CONT_Empleados Ncontrolador = new Controlador.CONT_MenuVentas(Nmodelo, Nvista);
            Ncontrolador.iniciarVista();*/
            vista.dispose();
        } else if (vista.btnServicios == evento.getSource()) {
            Vista.Servicios Nvista = new Vista.Servicios();
            Modelo.MDL_Servicios Nmodelo = new Modelo.MDL_Servicios();
            Controlador.CONT_Servicios Ncontrolador = new Controlador.CONT_Servicios(Nmodelo, Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();            
        } else if (vista.btnLogout == evento.getSource()) {
            Vista.InicioSesion Nvista = new Vista.InicioSesion();
            Modelo.MDL_InicioSesion Nmodelo = new Modelo.MDL_InicioSesion();
            Controlador.CONT_InicioSesion Ncontrolador = new Controlador.CONT_InicioSesion(Nmodelo, Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
