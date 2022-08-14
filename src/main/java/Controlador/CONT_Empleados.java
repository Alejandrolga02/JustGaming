/*
Descripción: Creación del controlador para la tabla de empleados
Nombre: López Robles Jesús Damiel
Fecha: 13-agosto-2022
*/
package Controlador;

import Modelo.MDL_Empleados;
import Vista.Empleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author laptop
 */
public class CONT_Empleados implements ActionListener, MouseListener{
    //Atributos necedsarios
    MDL_Empleados modelo;
    Empleados vista;
    
    //Comstrcutor de parametros
    public CONT_Empleados(MDL_Empleados modelo, Empleados vista){
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnIngresar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.tblEmpleados.addMouseListener(this);
    }
    
    //Método para iniciar la vista
    public void iniciarVista(){
        vista.setTitle("Empleados");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.tblEmpleados.setModel(modelo.empleadoConsultar());
        vista.setVisible(true);
    }
    
    //Metodo para limpiar cajas de texto
    public void limpiarCajastexto(){
        vista.txtDomicilio.setText("");
        vista.txtFechaDeNacimiento.setText("");
        vista.txtIdEmpleado.setText("");
        vista.txtNombre.setText("");
        vista.txtApellido.setText("");
        vista.txtTelefono.setText("");
        vista.txtidRol.setText("");
    }
    
    //Método para obtener el usuario
    public String conseguirUsuario(String nombre, String apellido){
        String usuario = nombre.substring(0, 3) + apellido.substring(0, 3);
        return usuario;
    }
    
    //Método para cambiar la fecha de String a Date
    public Date conseguirFecha(String fecha){
        Date date = new Date(fecha);
        
        long time = date.getTime();
        java.sql.Date fechaBuena = new java.sql.Date(time);
        return fechaBuena;
    }
    
    //Método para el uso de los botones
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vista.btnIngresar == evento.getSource()){
            if(vista.txtApellido.getText().equals("") || vista.txtDomicilio.getText().equals("") || vista.txtFechaDeNacimiento.getText().equals("")
                    || vista.txtNombre.getText().equals("") || vista.txtTelefono.getText().equals("") || vista.txtidRol.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Existencia de campos sin llenar");
            }else if(modelo.empleadoInsertar(vista.txtNombre.getText(), vista.txtApellido.getText(), 
                    vista.txtTelefono.getText(), vista.txtDomicilio.getText(), (java.sql.Date)conseguirFecha(vista.txtFechaDeNacimiento.getText()), 
                    conseguirUsuario(vista.txtNombre.getText(),vista.txtApellido.getText()), Integer.parseInt(vista.txtidRol.getText()))){
                JOptionPane.showMessageDialog(null, "Registro insertado exitoso");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo insertar");
            }
        }else if(vista.btnActualizar == evento.getSource()){
            if(vista.txtApellido.getText().equals("") || vista.txtDomicilio.getText().equals("") || vista.txtFechaDeNacimiento.getText().equals("")
                    || vista.txtNombre.getText().equals("") || vista.txtTelefono.getText().equals("") || vista.txtidRol.getText().equals("") 
                    || vista.txtIdEmpleado.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Existencia de campos sin llenar");
            }else if(modelo.empleadoActualizar(Integer.parseInt(vista.txtIdEmpleado.getText()), vista.txtNombre.getText(), vista.txtApellido.getText()
                    , vista.txtTelefono.getText(), vista.txtDomicilio.getText(), (java.sql.Date)conseguirFecha(vista.txtFechaDeNacimiento.getText())
                    , Integer.parseInt(vista.txtidRol.getText()))){
                JOptionPane.showMessageDialog(null, "Registro modificado exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo actualizar");
            }
        }else if(vista.btnEliminar == evento.getSource()){
            if(vista.txtIdEmpleado.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Campo IdEmpleado esta vacio");
            }else if(modelo.empleadoEliminar(Integer.parseInt(vista.txtIdEmpleado.getText()))){
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vista.btnLimpiar == evento.getSource()){
            limpiarCajastexto();
        }else if(vista.btnRegresar == evento.getSource()){
            
        }
    }

    
    
    
    
    
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
