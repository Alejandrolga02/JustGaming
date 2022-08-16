/*
Descripción: Creación del contolador para la tabla de clientes
Nombre: López Robles Jesús Damiel
Fecha: 13-agosto-2022
*/
package Controlador;

import Modelo.MDL_Clientes;
import Vista.Clientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CONT_Clientes implements ActionListener, MouseListener{
    //Atributos necesarios
    MDL_Clientes modelo;
    Clientes vista;
    
    //Constructor de parametros
    public CONT_Clientes(MDL_Clientes modelo, Clientes vista){
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnIngresar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.tblClientes.addMouseListener(this);
    }
    
    //Método para iniciar la vista
    public void iniciarVista(){
        vista.setTitle("Clientes");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.tblClientes.setModel(modelo.clientesConsultar());
        vista.setVisible(true);
    }
    
    //Método para limpiar las cajas de texto
    public void limpiarCajastexto(){
        vista.txtCorreo.setText("");
        vista.txtDomicilio.setText("");
        vista.txtIdCliente.setText("");
        vista.txtNombre.setText("");
        vista.txtTelefono.setText("");
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
    
    //Método para las acciones de los botones
    @Override
    public void actionPerformed(ActionEvent evento) {
        boolean isValid = telefonoValido(vista.txtTelefono.getText());
        boolean valido = validarTelefono(vista.txtTelefono.getText());
        if(vista.btnIngresar == evento.getSource()){  //Boton de ingresar
            if(vista.txtNombre.getText().equals("") || vista.txtDomicilio.getText().equals("") || vista.txtCorreo.getText().equals("") || 
                    vista.txtTelefono.getText().equals("")){   //Verificación de que los campos no esten vacios
                JOptionPane.showMessageDialog(null, "Existencia de campos sin llenar");
            }else if(isValid && valido){ //Verificación del telefono
                if(modelo.clientesInsertar(vista.txtNombre.getText(), vista.txtTelefono.getText(),  //Se eejecuta la consulta
                    vista.txtCorreo.getText(), vista.txtDomicilio.getText())){
                    JOptionPane.showMessageDialog(null, "Registro insertado exitoso");
                    this.vista.tblClientes.setModel(modelo.clientesConsultar()); //Se actualiza la tabla en la vista
                    limpiarCajastexto();
                }else{   //Ocurrio un error
                    JOptionPane.showMessageDialog(null, "No se pudo insertar");
                }
            }else{
                JOptionPane.showMessageDialog(null, "El formato del numero de telefono no es valido");
            }
        }else if(vista.btnActualizar == evento.getSource()){  //Boton de actualizar el campo
            if((vista.txtNombre.getText().equals("")) || (vista.txtDomicilio.getText().equals("")) || (vista.txtCorreo.getText().equals("")) || 
                    (vista.txtTelefono.getText().equals(""))){  //Verificación de que los campos no esten vacios
                JOptionPane.showMessageDialog(null, "Existencia de campos sin llenar");
            }else if(isValid && valido){
                
                if(modelo.clienteActualizar(Integer.parseInt(vista.txtIdCliente.getText()),vista.txtNombre.getText(), vista.txtTelefono.getText(), 
                    vista.txtCorreo.getText(), vista.txtDomicilio.getText())){ //Se ejecuta la sentencia 
                    JOptionPane.showMessageDialog(null, "Registro modificado exitosamente");
                    this.vista.tblClientes.setModel(modelo.clientesConsultar()); //Se actualiza la tabla en la vista
                    limpiarCajastexto();
                }else{ //Ocurrio un error
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar");
                }
            }else{
                JOptionPane.showMessageDialog(null, "El formato del numero de telefono no es valido");
            }
        }else if(vista.btnEliminar == evento.getSource()){ //Boton de eliminar 
            if(vista.txtIdCliente.getText().equals("")){ //Verificación del campo de id
                JOptionPane.showMessageDialog(null, "Campo IdCliente esta vacio");
            }else if(modelo.clienteEliminar(Integer.parseInt(vista.txtIdCliente.getText()))){ //Se ejecuta la sentencia
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
                this.vista.tblClientes.setModel(modelo.clientesConsultar());  //Se actualiza la tabla en la vista
                limpiarCajastexto();
            }else{ //Ocurrio un error
                JOptionPane.showMessageDialog(null, "No se pudo eliminar");
            }
        }else if(vista.btnLimpiar == evento.getSource()){  //Boton de limpiar las cajas de texto
            limpiarCajastexto();
        }else if(vista.btnRegresar == evento.getSource()){ //Boton de regresar la vista
            Vista.MenuPrincipal Nvista = new Vista.MenuPrincipal();
            Controlador.CONT_MenuPrincipal Ncontrolador = new Controlador.CONT_MenuPrincipal(Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
        }
    }

    
    

    @Override
    public void mouseClicked(MouseEvent evento) { //Evento para selecionar un campo de la tabla
       if(vista.tblClientes == evento.getSource()){
           int fila = vista.tblClientes.rowAtPoint(evento.getPoint());
           if(fila > -1){
               vista.txtIdCliente.setText(String.valueOf(vista.tblClientes.getValueAt(fila, 0)));
               vista.txtNombre.setText(String.valueOf(vista.tblClientes.getValueAt(fila, 1)));
               vista.txtTelefono.setText(String.valueOf(vista.tblClientes.getValueAt(fila, 2)));
               vista.txtCorreo.setText(String.valueOf(vista.tblClientes.getValueAt(fila, 3)));
               vista.txtDomicilio.setText(String.valueOf(vista.tblClientes.getValueAt(fila, 4)));
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
