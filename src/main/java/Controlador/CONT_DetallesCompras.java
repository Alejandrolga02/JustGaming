/*
Descripción: Controlador para la vista de Detalles Compras
Nombre: López Robles Jesús Daniel
Fecha: 16-agosto-2022
*/
package Controlador;

import Modelo.MDL_DetallesCompras;
import Vista.DetallesCompras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class CONT_DetallesCompras implements ActionListener, MouseListener {
    DetallesCompras vista;
    MDL_DetallesCompras modelo;
    
    public CONT_DetallesCompras (MDL_DetallesCompras modelo, DetallesCompras vista) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.txtIdCompra.addActionListener(this);
        this.vista.txtEmpleado.addActionListener(this);
        this.vista.txtCliente.addActionListener(this);
        this.vista.txtFecha.addActionListener(this);
        this.vista.txtTotal.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnConsultar.addActionListener(this);
        this.vista.tblCompras.addMouseListener(this);
        this.vista.tblDetallesCompras.addMouseListener(this);
    }
    
    public void iniciarVista() {
        vista.setTitle("Detalles Compras");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        this.vista.tblCompras.setModel(modelo.consultar(0, vista.txtEmpleado.getText(), vista.txtCliente.getText(),vista.txtFecha.getText()));
        vista.setVisible(true);
    }
    
    public void limpiarCajasTexto(){
        vista.txtIdCompra.setText("");
        vista.txtEmpleado.setText("");
        vista.txtCliente.setText("");
        vista.txtFecha.setText("");
        vista.txtTotal.setText("");
        this.vista.tblCompras.setModel(modelo.consultar(0, vista.txtEmpleado.getText(), vista.txtCliente.getText(),vista.txtFecha.getText()));
        this.vista.tblDetallesCompras.setModel(modelo.consultarDetalles(0));
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (vista.btnConsultar == evento.getSource()) {
            if (vista.txtCliente.getText().isEmpty() && vista.txtEmpleado.getText().isEmpty() && vista.txtFecha.getText().isEmpty()) { // TODO VACIO
                this.vista.tblCompras.setModel(modelo.consultar(0, vista.txtEmpleado.getText(), vista.txtCliente.getText(),vista.txtFecha.getText()));
                this.vista.tblDetallesCompras.setModel(modelo.consultarDetalles(0));
            } else {
                if (vista.txtEmpleado.getText().isEmpty()) { // NO EMPLEADO
                    if (vista.txtCliente.getText().isEmpty()) { // BUSQUEDA FECHA
                        this.vista.tblCompras.setModel(modelo.consultar(1, vista.txtEmpleado.getText(), vista.txtCliente.getText(),vista.txtFecha.getText()));
                        this.vista.tblDetallesCompras.setModel(modelo.consultarDetalles(0));
                    } else {
                        if (vista.txtFecha.getText().isEmpty()) { // BUSQUEDA CLIENTE
                            this.vista.tblCompras.setModel(modelo.consultar(2, vista.txtEmpleado.getText(), vista.txtCliente.getText(),vista.txtFecha.getText()));
                            this.vista.tblDetallesCompras.setModel(modelo.consultarDetalles(0));
                        } else { // BUSQUEDA CLIENTE Y FECHA
                            this.vista.tblCompras.setModel(modelo.consultar(3, vista.txtEmpleado.getText(), vista.txtCliente.getText(),vista.txtFecha.getText()));
                            this.vista.tblDetallesCompras.setModel(modelo.consultarDetalles(0));                            
                        }
                    }
                } else { // SI EMPLEADO
                    if (vista.txtCliente.getText().isEmpty()) { 
                        if (vista.txtFecha.getText().isEmpty()) { // BUSQUEDA EMPLEADO
                            this.vista.tblCompras.setModel(modelo.consultar(4, vista.txtEmpleado.getText(), vista.txtCliente.getText(),vista.txtFecha.getText()));
                            this.vista.tblDetallesCompras.setModel(modelo.consultarDetalles(0));                               
                        } else { // BUSQUEDA FECHA Y EMPLEADO
                            this.vista.tblCompras.setModel(modelo.consultar(5, vista.txtEmpleado.getText(), vista.txtCliente.getText(),vista.txtFecha.getText()));
                            this.vista.tblDetallesCompras.setModel(modelo.consultarDetalles(0));                            
                        }
                    } else {
                        if (vista.txtFecha.getText().isEmpty()) { // BUSQUEDA CLIENTE Y EMPLEADO
                            this.vista.tblCompras.setModel(modelo.consultar(6, vista.txtEmpleado.getText(), vista.txtCliente.getText(),vista.txtFecha.getText()));
                            this.vista.tblDetallesCompras.setModel(modelo.consultarDetalles(0));
                        } else {
                            this.vista.tblCompras.setModel(modelo.consultar(7, vista.txtEmpleado.getText(), vista.txtCliente.getText(),vista.txtFecha.getText())); // BUSQUEDA CLIENTE, FECHA Y EMPLEADO
                            this.vista.tblDetallesCompras.setModel(modelo.consultarDetalles(0));                            
                        }
                    }                    
                }
            }
        } else if (vista.btnRegresar == evento.getSource()) { // Boton Regresar Presionado
            Vista.MenuCompras Nvista = new Vista.MenuCompras();
            Controlador.CONT_MenuCompras Ncontrolador = new Controlador.CONT_MenuCompras(Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
        } else if (vista.btnLimpiar == evento.getSource()) {
            this.limpiarCajasTexto();
        }
    }

    @Override
    public void mouseClicked(MouseEvent evento) {
        if(vista.tblCompras == evento.getSource()){
            int  fila = vista.tblCompras.rowAtPoint(evento.getPoint());
            if (fila > -1){
                vista.txtIdCompra.setText(String.valueOf(vista.tblCompras.getValueAt(fila, 0)));
                vista.txtEmpleado.setText(String.valueOf(vista.tblCompras.getValueAt(fila, 1)));
                vista.txtCliente.setText(String.valueOf(vista.tblCompras.getValueAt(fila, 2)));
                vista.txtFecha.setText(String.valueOf(vista.tblCompras.getValueAt(fila, 3)));
                vista.txtTotal.setText(String.valueOf(vista.tblCompras.getValueAt(fila, 5)));
             }
            
            vista.tblDetallesCompras.setModel(modelo.consultarDetalles(Integer.parseInt(vista.txtIdCompra.getText())));
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
