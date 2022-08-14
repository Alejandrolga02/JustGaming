/*
 * Descripcion: Controlador para Inicio de Sesion
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Controlador;

import Modelo.MDL_Proveedores;
import Vista.Proveedores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CONT_Proveedores implements ActionListener, MouseListener {
    Proveedores vista;
    MDL_Proveedores modelo;
    
    public CONT_Proveedores(MDL_Proveedores modelo, Proveedores vista) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.btnIngresar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.tblProveedores.addMouseListener(this);
    }
    
    public void iniciarVista() {
        vista.setTitle("Servicios");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.tblProveedores.setModel(modelo.consultar());
        vista.setVisible(true);
    }
    
    public void limpiarCajasTexto(){
        vista.txtDireccion.setText("");
        vista.txtIdProveedor.setText("");
        vista.txtNombre.setText("");
        vista.txtTelefono.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vista.btnIngresar == evento.getSource()) {
            if(modelo.ingresar(vista.txtNombre.getText(), vista.txtTelefono.getText(), vista.txtDireccion.getText())){
                JOptionPane.showMessageDialog(null, "Registro insertado exitosamente");
                this.vista.tblProveedores.setModel(modelo.consultar());
                limpiarCajasTexto();
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo insertar");
            }
        }else if(vista.btnEliminar == evento.getSource()){
            if(modelo.borrar(Integer.parseInt(vista.txtIdProveedor.getText()))){
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
                this.vista.tblProveedores.setModel(modelo.consultar());
                limpiarCajasTexto();
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vista.btnActualizar == evento.getSource()){
            if(modelo.actualizar(vista.txtNombre.getText(), vista.txtTelefono.getText(), 
        vista.txtDireccion.getText(), Integer.parseInt(vista.txtIdProveedor.getText()))) {
                JOptionPane.showMessageDialog(null, "Registro modificado exitosamente");
                this.vista.tblProveedores.setModel(modelo.consultar());                
                limpiarCajasTexto();
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo actualizar");
            }
        } else if (vista.btnLimpiar == evento.getSource()) {
            limpiarCajasTexto();
        } else if (vista.btnRegresar == evento.getSource()) {
            Vista.MenuPrincipal Nvista = new Vista.MenuPrincipal();
            Controlador.CONT_MenuPrincipal Ncontrolador = new Controlador.CONT_MenuPrincipal(Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent evento) {
        if(vista.tblProveedores == evento.getSource()){
            int  fila = vista.tblProveedores.rowAtPoint(evento.getPoint());
            if (fila > -1){
                vista.txtIdProveedor.setText(String.valueOf(vista.tblProveedores.getValueAt(fila, 0)));
                vista.txtNombre.setText(String.valueOf(vista.tblProveedores.getValueAt(fila, 1)));
                vista.txtTelefono.setText(String.valueOf(vista.tblProveedores.getValueAt(fila, 2)));
                vista.txtDireccion.setText(String.valueOf(vista.tblProveedores.getValueAt(fila, 3)));
             }
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
