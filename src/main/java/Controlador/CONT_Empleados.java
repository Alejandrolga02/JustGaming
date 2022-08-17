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
//import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    public java.sql.Date conseguirFecha(String fecha){
        java.sql.Date a = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date parsed = format.parse(fecha);
            java.sql.Date fechaSQL = new java.sql.Date(parsed.getTime());
            return fechaSQL;
        } catch(ParseException e) {
            return a;
        }
    }
    
    //Método para validar el telefono
     public boolean telefonoValido(String telefono) { // Validacion de telefono
        try {
            if (telefono.length() == 7 || telefono.length() == 10 || telefono.length() == 12) {
                Double ntel = Double.parseDouble(telefono);

                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
     
    //Validación de que el numero no contenga caracteres
    public boolean validarTelefono(String numero){
        Pattern patron = Pattern.compile("[0-9]+");
        Matcher matcher = patron.matcher(numero);
        boolean resultado = matcher.matches();
        if(resultado){
            return true;
        }else{
            return false;
        }
    }
    
    //Método para el uso de los botones
    @Override
    public void actionPerformed(ActionEvent evento) {
        boolean isValid = telefonoValido(vista.txtTelefono.getText());
        boolean valido = validarTelefono(vista.txtTelefono.getText());
        if(vista.btnIngresar == evento.getSource()){ //Boton de para ingresar datos
            if(vista.txtApellido.getText().equals("") || vista.txtDomicilio.getText().equals("") || vista.txtFechaDeNacimiento.getText().equals("")
                    || vista.txtNombre.getText().equals("") || vista.txtTelefono.getText().equals("") || vista.txtidRol.getText().equals("")){ //Validación de camos vacios
                JOptionPane.showMessageDialog(null, "Existencia de campos sin llenar");
            }else if(isValid && valido){  //Validación del campo telefono
                if(modelo.empleadoInsertar(vista.txtNombre.getText(), vista.txtApellido.getText(), 
                    vista.txtTelefono.getText(), vista.txtDomicilio.getText(), (java.sql.Date) conseguirFecha(vista.txtFechaDeNacimiento.getText()), 
                    conseguirUsuario(vista.txtNombre.getText(),vista.txtApellido.getText()), Integer.parseInt(vista.txtidRol.getText()))){ //Ejecución de la consulta
                    JOptionPane.showMessageDialog(null, "Registro insertado exitoso");
                    this.vista.tblEmpleados.setModel(modelo.empleadoConsultar()); //Actualización de la tabla en la vista
                    limpiarCajastexto();
                }else{
                    JOptionPane.showMessageDialog(null, "No se logro insertar");
                }
            }else{
                JOptionPane.showMessageDialog(null, "El formato del numero de telefono no es valido");
            }
        }else if(vista.btnActualizar == evento.getSource()){ //Boton de actualizar
            if(vista.txtApellido.getText().equals("") || vista.txtDomicilio.getText().equals("") || vista.txtFechaDeNacimiento.getText().equals("")
                    || vista.txtNombre.getText().equals("") || vista.txtTelefono.getText().equals("") || vista.txtidRol.getText().equals("") 
                    || vista.txtIdEmpleado.getText().equals("")){ //Validación de los campos vacios
                JOptionPane.showMessageDialog(null, "Existencia de campos sin llenar");
            }else if(isValid && valido){ //Validación del telefono
                if(modelo.empleadoActualizar(Integer.parseInt(vista.txtIdEmpleado.getText()), vista.txtNombre.getText(), vista.txtApellido.getText()
                    , vista.txtTelefono.getText(), vista.txtDomicilio.getText(), (java.sql.Date)conseguirFecha(vista.txtFechaDeNacimiento.getText())
                    , Integer.parseInt(vista.txtidRol.getText()))){ //Ejecución de la consulta
                    JOptionPane.showMessageDialog(null, "Registro modificado exitosamente");
                    limpiarCajastexto();
                    this.vista.tblEmpleados.setModel(modelo.empleadoConsultar()); //Actualización de la tabla en la vista
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar");
                }
            }else{
                JOptionPane.showMessageDialog(null, "El formato del numero de telefono no es valido");
            }
        }else if(vista.btnEliminar == evento.getSource()){ //Boton de eliminar
            if(vista.txtIdEmpleado.getText().equals("")){ //Validación de campo vacio
                JOptionPane.showMessageDialog(null, "Campo IdEmpleado esta vacio");
            }else if(modelo.empleadoEliminar(Integer.parseInt(vista.txtIdEmpleado.getText()))){ //Ejecución de la consulta
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
                limpiarCajastexto();
                this.vista.tblEmpleados.setModel(modelo.empleadoConsultar()); //Actualización de la tabla en la vista
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vista.btnLimpiar == evento.getSource()){
            limpiarCajastexto();
        }else if(vista.btnRegresar == evento.getSource()){ //Boton para regrresar a la vista anterior
            Vista.MenuPrincipal Nvista = new Vista.MenuPrincipal();
            Controlador.CONT_MenuPrincipal Ncontrolador = new Controlador.CONT_MenuPrincipal(Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
        }
    }

    
    
    
    
    
    
    
    
    @Override
    public void mouseClicked(MouseEvent evento) {
        if(vista.tblEmpleados == evento.getSource()){ //Evento para seleccionar un campo de la tabla
            int fila = vista.tblEmpleados.rowAtPoint(evento.getPoint());
            if(fila > -1){
                vista.txtIdEmpleado.setText(String.valueOf(vista.tblEmpleados.getValueAt(fila, 0)));
                vista.txtNombre.setText(String.valueOf(vista.tblEmpleados.getValueAt(fila, 1)));
                vista.txtApellido.setText(String.valueOf(vista.tblEmpleados.getValueAt(fila, 2)));
                vista.txtTelefono.setText(String.valueOf(vista.tblEmpleados.getValueAt(fila, 3)));
                vista.txtDomicilio.setText(String.valueOf(vista.tblEmpleados.getValueAt(fila, 4)));
                vista.txtFechaDeNacimiento.setText(String.valueOf(vista.tblEmpleados.getValueAt(fila, 5)));
                vista.txtidRol.setText(String.valueOf(vista.tblEmpleados.getValueAt(fila, 7)));
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
