/*
Descripción: Creación del controlador para la realización de compras
Nombre: López Robles Jesús Daniel
Fecha: 18-agosto-2022
*/
package Controlador;

import static Modelo.Conexion.getConnection;
import Modelo.MDL_Compras;
import Vista.Compras;
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

public class CONT_Compras implements ActionListener, MouseListener{
    //Atributos de la clase
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    //Creacion de los objetos
    Compras vista;
    MDL_Compras modelo;
    
    //Constructor de parametros
    public CONT_Compras(Compras vista, MDL_Compras modelo){
        this.vista = vista;
        this.modelo = modelo;
        this.vista.btnBorrar.addActionListener(this);
        this.vista.btnIngresar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.btnTerminar.addActionListener(this);
        this.vista.comboxProveedor.addActionListener(this);
        this.vista.comboxInsumo.addActionListener(this);
        this.vista.txtCantidad.addActionListener(this);
        this.vista.txtTotal.addActionListener(this);
        this.vista.tblCompras.addMouseListener(this);
    }    
    
    //Método para iniciar la vista
    public void iniciarVista(){
        vista.setTitle("Compras");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        comboProveedores();
        comboInsumos();
        vista.setVisible(true);
    }
    
    //Método para limpiar las cajas de texto
    public void limpiarCajastexto(){
        vista.txtCantidad.setText("");
        vista.comboxInsumo.setSelectedIndex(0);
        vista.comboxProveedor.setSelectedIndex(0);
        this.vista.tblCompras.setModel(null);
    }
    
    //Método para obtener los proveedores en el comboBox
    public void comboProveedores(){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Prepara la sentencia sql
            String sql = "SELECT nombre FROM proveedores;";
            //Ejecución de la sentencia
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            //Pasar la información al cmobo box
            while(rs.next()){
                vista.comboxProveedor.addItem(rs.getString("nombre"));
            }
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
    
    //Método para obtener los insumos en el comboBox
    public void comboInsumos(){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Prepara la sentencia sql
            String sql = "SELECT nombre FROM insumos;";
            //Ejecución de la sentencia
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            //Pasar la información al cmobo box
            while(rs.next()){
                vista.comboxInsumo.addItem(rs.getString("nombre"));
            }
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
    
    //Método para validar la cantidad de producto comprado
    public boolean validarCantidad(String cantidad){
        Pattern patron = Pattern.compile("[0-9]+");
        Matcher matcher = patron.matcher(cantidad);
        boolean resultado = matcher.matches();
        if(resultado){
            return true;
        }else{
            return false;
        }
    }
    
    //Método para obtener el id del insumo
    public int getIdInsumo(){
        try {
            conn = getConnection();
            
            String query = "SELECT idinsumos FROM insumos WHERE nombre = '"+vista.comboxInsumo.getSelectedItem().toString()+"';";
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();

            while(rs.next()){
                return rs.getInt("idinsumos");
            }        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return 5;
    }
    
    //Método para obtener el id del Proveedor
    public int getIdProveedore(){
        try {
            conn = getConnection();
            
            String query = "SELECT idProveedor FROM proveedores WHERE nombre = '"+vista.comboxProveedor.getSelectedItem().toString()+"';";
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();

            while(rs.next()){
                return rs.getInt("idProveedores");
            }        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return 5;
    }
    
    //Método para scar el total de la compras
    public double sumarTotal(int IdInsumo){
        Double costo = 0.0, total = 0.0;
        int cantidad;
        String n = "";
        try{
            conn = getConnection();
            String query = "SELECT costo FROM insumos WHERE idinsumos = '" + IdInsumo + ";";
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            
            while(rs.next()){
                costo = rs.getDouble("costo");
            }
        }catch(SQLException ex){
            return 0;
        }
        cantidad = Integer.parseInt(vista.txtCantidad.getText());
        total = total + (costo * cantidad);
        return total;
    }
    
    
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseClicked(MouseEvent evento) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
