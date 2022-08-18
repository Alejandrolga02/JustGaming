/*
Descripción: Creación del controlador para la tabla de insumos
Nombre: López Robles Jesús Damiel
Fecha: 13-agosto-2022
*/
package Controlador;

import Modelo.Conexion;
import static Modelo.Conexion.getConnection;
import Modelo.MDL_Insumos;
import Vista.Insumos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CONT_Insumos implements ActionListener, MouseListener{
    //Atrinutos necesarios
    MDL_Insumos modelo;
    Insumos vista;
    
    //Constructor de parametros
    public CONT_Insumos(MDL_Insumos modelo, Insumos vista){
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnElimina.addActionListener(this);
        this.vista.btnIngresar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.tblInsumos.addMouseListener(this);
        if (Conexion.getUSER_ROL() != 1) {
            this.vista.btnActualizar.setEnabled(false);
            this.vista.btnElimina.setEnabled(false);
            this.vista.btnIngresar.setEnabled(false);
        }
        this.vista.btnConsultar.addActionListener(this);
    }
    
    //Método para iniciar la vista
    public void iniciarVista(){
        vista.setTitle("Insumos");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.tblInsumos.setModel(modelo.insumosConsultar(0,vista.txtNombre.getText()));
        vista.setVisible(true);
    }
    
    //Método para limpiar las cajas de texto
    public void limpiarCajastexto(){
        vista.txtCantidad.setText("");
        vista.txtCosto.setText("");
        vista.txtIdInsumo.setText("");
        vista.txtNombre.setText("");
        this.vista.tblInsumos.setModel(modelo.insumosConsultar(0,vista.txtNombre.getText()));
    }
    
    //Validación de solo numeros
    public boolean validarNumero(String numero){
        Pattern patron = Pattern.compile("[0-9]+");
        Matcher matcher = patron.matcher(numero);
        boolean resultado = matcher.matches();
        if(resultado){
            return true;
        }else{
            return false;
        }
    }
    
    //Validación de que es un numero entero
    public boolean validarNumeroentero(String numero){
        try{
            Double.parseDouble(numero);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
        
    //Método para las acciones de los botes
    @Override
    public void actionPerformed(ActionEvent evento) {
        boolean valido1Cantidad = validarNumero(vista.txtCantidad.getText());
        boolean validoPrecio = validarNumeroentero(vista.txtCosto.getText());
        
        if(vista.btnIngresar == evento.getSource()){ //Boton para ingresar
            if(vista.txtNombre.getText().equals("") || vista.txtCantidad.getText().equals("") || vista.txtCosto.getText().equals("")){ //Verificación
                JOptionPane.showMessageDialog(null, "Existencia de campos sin llenar");
            }else if(valido1Cantidad){ //Validar si cantidad solo contiene nuemoros
                if(validoPrecio){   //Validar que costo solo contiene numero
                    if(modelo.insumosInsertar(vista.txtNombre.getText(), Integer.parseInt(vista.txtCantidad.getText()), 
                             Float.parseFloat(vista.txtCosto.getText()))){//Validación de la consulta
                        JOptionPane.showMessageDialog(null, "Registro insertado exitoso");
                        this.vista.tblInsumos.setModel(modelo.insumosConsultar(0,vista.txtNombre.getText()));
                        limpiarCajastexto();
                    }else{
                        JOptionPane.showMessageDialog(null, "No se pudo insertar");
                    }
                }else{
                    vista.txtCosto.setText("");
                    JOptionPane.showMessageDialog(null, "El campo costo sole debe contener numeros");
                }
            }else{
                vista.txtCantidad.setText("");
                JOptionPane.showMessageDialog(null, "El campo cantidad solo debe contener numeros sin punto decimal");
            }
        }else if(vista.btnActualizar == evento.getSource()){
            if(vista.txtNombre.getText().equals("") || vista.txtCantidad.getText().equals("") || vista.txtCosto.getText().equals("") || 
                    vista.txtIdInsumo.getText().equals("")){ //Validar existencia de campos sin llenar
                JOptionPane.showMessageDialog(null, "Existencia de campos sin llenar");
            }else if(valido1Cantidad){
                if(validoPrecio){
                    if(modelo.insumosActualizar(Integer.parseInt(vista.txtIdInsumo.getText()), vista.txtNombre.getText(), 
                            Integer.parseInt(vista.txtCantidad.getText()), Float.parseFloat(vista.txtCosto.getText()))){
                        JOptionPane.showMessageDialog(null, "Registro actualizado");
                        this.vista.tblInsumos.setModel(modelo.insumosConsultar(0,vista.txtNombre.getText()));
                        limpiarCajastexto();
                    }else{
                         JOptionPane.showMessageDialog(null, "No se pudo actualizar");
                    }
                }else{
                    vista.txtCosto.setText("");
                    JOptionPane.showMessageDialog(null, "El campo costo sole debe contener numeros");
                }
            }else{
                vista.txtCantidad.setText("");
                JOptionPane.showMessageDialog(null, "El campo cantidad solo debe contener numeros sin punto decimal");
            }
        }else if(vista.btnElimina == evento.getSource()){
            if(vista.txtIdInsumo.getText().equals("")){
                JOptionPane.showMessageDialog(null, "El campo de Id Insumo esta vacio");
            }else if(modelo.insumosEliminar(Integer.parseInt(vista.txtIdInsumo.getText()))){
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
                this.vista.tblInsumos.setModel(modelo.insumosConsultar(0,vista.txtNombre.getText()));
                limpiarCajastexto();
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vista.btnLimpiar == evento.getSource()){
            limpiarCajastexto();
        }else if(vista.btnRegresar == evento.getSource()){
            Vista.MenuPrincipal Nvista = new Vista.MenuPrincipal();
            Controlador.CONT_MenuPrincipal Ncontrolador = new Controlador.CONT_MenuPrincipal(Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose(); 
        }else if(vista.btnConsultar == evento.getSource()){
            if(vista.txtNombre.getText().isEmpty()){
                this.vista.tblInsumos.setModel(modelo.insumosConsultar(0,vista.txtNombre.getText()));
            }else{
                this.vista.tblInsumos.setModel(modelo.insumosConsultar(1,vista.txtNombre.getText()));
            }
    
        }
    }
    
    
    
    @Override
    public void mouseClicked(MouseEvent evento) {
        if(vista.tblInsumos == evento.getSource()){
            int fila = vista.tblInsumos.rowAtPoint(evento.getPoint());
            if(fila > -1){
                vista.txtIdInsumo.setText(String.valueOf(vista.tblInsumos.getValueAt(fila, 0)));
                vista.txtNombre.setText(String.valueOf(vista.tblInsumos.getValueAt(fila, 1)));
                vista.txtCantidad.setText(String.valueOf(vista.tblInsumos.getValueAt(fila, 2)));
                vista.txtCosto.setText(String.valueOf(vista.tblInsumos.getValueAt(fila, 3)));
            }
        }
    }

   
    
    
    
    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
