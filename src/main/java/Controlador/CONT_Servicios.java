/*
 * Descripcion: Controlador para CONT_Servicios
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Controlador;

import Modelo.Conexion;
import static Modelo.Conexion.getConnection;
import Modelo.MDL_Servicios;
import Vista.Servicios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CONT_Servicios implements ActionListener, MouseListener {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
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
        this.vista.comboxInsumos.addMouseListener(this);
        if (Conexion.getUSER_ROL() != 1) {
            this.vista.btnActualizar.setEnabled(false);
            this.vista.btnEliminar.setEnabled(false);
            this.vista.btnIngresar.setEnabled(false);
        }
    }

    public void iniciarVista() {
        vista.setTitle("Servicios");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.tblServicios.setModel(modelo.consultar());
        fillCombox();
        vista.setVisible(true);
    }
    
    public void limpiarCajasTexto(){
        vista.txtServicio.setText("");
        vista.txtPrecio.setText("");
        vista.txtIdServicio.setText("");
        this.vista.tblServicios.setModel(modelo.consultar());   
        vista.comboxInsumos.setSelectedIndex(0);
    }
    
    public boolean precioValido(String precio) {
        try {
            float npre = Float.parseFloat(precio);
            
            return npre >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public void fillCombox() {
        try {
            conn = getConnection();

            String query = "SELECT nombre FROM insumos;";

            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                vista.comboxInsumos.addItem(rs.getString("nombre"));
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public void selectItem(String insumo) {
        try {
            conn = getConnection();
            
            String query = "SELECT idinsumos, nombre FROM insumos WHERE nombre = '"+insumo+"';;";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while(rs.next()){
                vista.comboxInsumos.setSelectedIndex(rs.getInt("idinsumos"));
            }        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public int getIdinsumo() {
        try {
            conn = getConnection();
            
            String query = "SELECT idinsumos FROM insumos WHERE nombre = '"+vista.comboxInsumos.getSelectedItem().toString()+"';;";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while(rs.next()){
                return rs.getInt("idinsumos");
            }        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return 5;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vista.btnIngresar == evento.getSource()) { // Boton Ingresar presionado
            boolean isValid = precioValido(vista.txtPrecio.getText());
            
            if(!vista.txtServicio.getText().isEmpty() && isValid && vista.comboxInsumos.getSelectedIndex() > 0){
                if(modelo.ingresar(vista.txtServicio.getText(), Float.parseFloat(vista.txtPrecio.getText()), getIdinsumo())){
                    JOptionPane.showMessageDialog(null, "Registro insertado exitosamente");
                    this.vista.tblServicios.setModel(modelo.consultar());
                    limpiarCajasTexto();
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo insertar");
                }                
            } else if (isValid) {
                JOptionPane.showMessageDialog(null, "Faltan insertar datos");
            } else {
                vista.txtPrecio.setText("");
                JOptionPane.showMessageDialog(null, "Precio invalido");
            }
        }else if(vista.btnEliminar == evento.getSource()){
            if (!vista.txtIdServicio.getText().isEmpty()) { // Validacion de campo
                if(modelo.borrar(Integer.parseInt(vista.txtIdServicio.getText()))){
                    JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
                    this.vista.tblServicios.setModel(modelo.consultar());
                    limpiarCajasTexto();
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar");
                }                
            } else {
                JOptionPane.showMessageDialog(null, "Se necesita el Id para eliminar");                
            }
        }else if(vista.btnActualizar == evento.getSource()){
            boolean isValid = precioValido(vista.txtPrecio.getText());
            
            if(!vista.txtServicio.getText().isEmpty() && isValid && !vista.txtIdServicio.getText().isEmpty() && vista.comboxInsumos.getSelectedIndex() > 0){
                if(modelo.actualizar(vista.txtServicio.getText(), Float.parseFloat(vista.txtPrecio.getText()), getIdinsumo(), Integer.parseInt(vista.txtIdServicio.getText()))) {
                    JOptionPane.showMessageDialog(null, "Registro modificado exitosamente");
                    this.vista.tblServicios.setModel(modelo.consultar());                
                    limpiarCajasTexto();
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar");
                }                
            } else if (isValid) {
                JOptionPane.showMessageDialog(null, "Faltan insertar datos");
            } else {
                vista.txtPrecio.setText("");
                JOptionPane.showMessageDialog(null, "Precio invalido");
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
        if(vista.tblServicios == evento.getSource()){
            int  fila = vista.tblServicios.rowAtPoint(evento.getPoint());
            if (fila > -1){
                vista.txtIdServicio.setText(String.valueOf(vista.tblServicios.getValueAt(fila, 0)));
                vista.txtServicio.setText(String.valueOf(vista.tblServicios.getValueAt(fila, 1)));
                vista.txtPrecio.setText(String.valueOf(vista.tblServicios.getValueAt(fila, 2)));
                selectItem(String.valueOf(vista.tblServicios.getValueAt(fila, 3)));
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
