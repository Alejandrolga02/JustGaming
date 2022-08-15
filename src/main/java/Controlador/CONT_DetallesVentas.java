/*
 * Descripcion: Controlador para Detalles ventas
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Controlador;

import Modelo.MDL_DetallesVentas;
import Vista.DetallesVentas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class CONT_DetallesVentas implements ActionListener, MouseListener {
    DetallesVentas vista;
    MDL_DetallesVentas modelo;
    
    public CONT_DetallesVentas (MDL_DetallesVentas modelo, DetallesVentas vista) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.txtIdVenta.addActionListener(this);
        this.vista.txtEmpleado.addActionListener(this);
        this.vista.txtCliente.addActionListener(this);
        this.vista.txtFecha.addActionListener(this);
        this.vista.txtTotal.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.tblVentas.addMouseListener(this);
        this.vista.tblDetallesVentas.addMouseListener(this);
    }
    
    public void iniciarVista() {
        vista.setTitle("Detalles Ventas");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.tblVentas.setModel(modelo.consultar());
        vista.setVisible(true);
    }
    
    public void limpiarCajasTexto(){
        vista.txtIdVenta.setText("");
        vista.txtEmpleado.setText("");
        vista.txtCliente.setText("");
        vista.txtFecha.setText("");
        vista.txtTotal.setText("");
        vista.tblVentas.setModel(modelo.consultar());
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (vista.btnRegresar == evento.getSource()) { // Boton Regresar Presionado
            Vista.MenuPrincipal Nvista = new Vista.MenuPrincipal();
            Controlador.CONT_MenuPrincipal Ncontrolador = new Controlador.CONT_MenuPrincipal(Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent evento) {
        if(vista.tblVentas == evento.getSource()){
            int  fila = vista.tblVentas.rowAtPoint(evento.getPoint());
            if (fila > -1){
                vista.txtIdVenta.setText(String.valueOf(vista.tblVentas.getValueAt(fila, 0)));
                vista.txtEmpleado.setText(String.valueOf(vista.tblVentas.getValueAt(fila, 1)));
                vista.txtCliente.setText(String.valueOf(vista.tblVentas.getValueAt(fila, 2)));
                vista.txtFecha.setText(String.valueOf(vista.tblVentas.getValueAt(fila, 3)));
                vista.txtTotal.setText(String.valueOf(vista.tblVentas.getValueAt(fila, 4)));
             }
            
            vista.tblDetallesVentas.setModel(modelo.consultarDetalles(Integer.parseInt(vista.txtIdVenta.getText())));
        }
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
