/*
 * Descripcion: Controlador para CONT_Servicios
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Controlador;

import Modelo.MDL_Servicios;
import Vista.Servicios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CONT_Servicios implements ActionListener, MouseListener {
    Servicios vista;
    MDL_Servicios modelo;
    
    public CONT_Servicios(MDL_Servicios modelo, Servicios vista) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.btnIngresar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.tblServicios.addMouseListener(this);
    }
    
    public void iniciarVista() {
        vista.setTitle("Servicios");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.tblServicios.setModel(modelo.consultar());
        vista.setVisible(true);
    }
    
    public void limpiarCajasTexto(){
        vista.txtServicios.setText("");
        vista.txtPrecio.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vista.btnIngresar == evento.getSource()) {
            if(modelo.ingresar(vista.txtServicios.getText(), Float.parseFloat(vista.txtPrecio.getText()))){
                JOptionPane.showMessageDialog(null, "Registro insertado exitosamente");
                this.vista.tblServicios.setModel(modelo.consultar());
                limpiarCajasTexto();
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo insertar");
            }
        }else if(vista.btnEliminar == evento.getSource()){
            /*if(modelo.usuariosEliminar(Integer.parseInt(vista.txtId.getText()))){
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
                this.vista.tbUsuarios.setModel(modelo.usuariosConsultar());
                ocultarContraseñaEnTabla();
                limpiarCajasTexto();
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }*/
        }else if(vista.btnActualizar == evento.getSource()){
            /*if(modelo.usuariosActualizar(
                    Integer.parseInt(vista.txtId.getText()),
                    vista.txtNombre.getText(),vista.txtContrasena.getText(),vista.txtCorreo.getText())){
                JOptionPane.showMessageDialog(null, "Registro modificado exitosamente");
                this.vista.tbUsuarios.setModel(modelo.usuariosConsultar());                
                ocultarContraseñaEnTabla();                            
                limpiarCajasTexto();
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo actualizar");
            }*/        
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
