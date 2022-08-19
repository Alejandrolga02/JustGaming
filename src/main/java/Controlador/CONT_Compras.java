/*
Descripción: Creación del controlador para la realización de compras
Nombre: López Robles Jesús Daniel
Fecha: 18-agosto-2022
*/
package Controlador;

import Modelo.Conexion;
import static Modelo.Conexion.getConnection;
import Vista.Compras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CONT_Compras implements ActionListener, MouseListener{
    //Atributos de la clase
    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    //Creacion de los objetos
    Compras vista;
    
    //Declaración del array  y la tabla
    DefaultTableModel dtm = new DefaultTableModel();
    ArrayList<CONT_Compras.Insumos> listainsumos =  new ArrayList<>();
    
    int cant;
    int index;
    
    //Método para llenar lista
    public void llenarLista(){
        listainsumos.add(getInsumos());
    }
    
    //Dato duplicado
    public boolean duplicadoDato(int id) {
        index = id;
        for (CONT_Compras.Insumos insumo : listainsumos) {
            if(id == insumo.id) {
                cant = insumo.cantidad;
                insumo.totalInsumo = insumo.cantidad * insumo.costo;
                return true;
            }
        }
        return false;
    }
    
    public void modificarDato() {
        Object[] datos = new Object[dtm.getColumnCount()];
        float total = 0;
        dtm.setRowCount(0);
        for (CONT_Compras.Insumos insumo : listainsumos) {
            if (insumo.id == index) {
                datos[0] = insumo.id;
                datos[1] = insumo.insumo;
                insumo.cantidad = cant;
                datos[2] = insumo.cantidad;
                datos[3] = insumo.costo;
                datos[4] = insumo.totalInsumo;                
            } else {
                datos[0] = insumo.id;
                datos[1] = insumo.insumo;
                datos[2] = insumo.cantidad;
                datos[3] = insumo.costo;
                datos[4] = insumo.totalInsumo;
            }
            total += insumo.totalInsumo;                
            dtm.addRow(datos);
        }
        vista.tblCompras.setModel(dtm);
        vista.txtTotal.setText(String.valueOf(total));
    }
    
    //Método pra crear la tabla
    public void setModelo() {
        String[] header = {"ID", "INSUMO", "CANTIDAD", "COSTO UNITARIO", "TOTAL"};
        dtm.setColumnIdentifiers(header);
        vista.tblCompras.setModel(dtm);
    }
    
    //Método para ingresar los datos a la tabla
    public void setDatos() {
        Object[] datos = new Object[dtm.getColumnCount()];
        float total = 0;
        dtm.setRowCount(0);
        for (CONT_Compras.Insumos insumo : listainsumos) {
            datos[0] = insumo.id;
            datos[1] = insumo.insumo;
            datos[2] = insumo.cantidad;
            datos[3] = insumo.costo;
            datos[4] = insumo.totalInsumo;
            total = (float) (total + insumo.totalInsumo);
            dtm.addRow(datos);
        }
        vista.tblCompras.setModel(dtm);
        vista.txtTotal.setText(String.valueOf(total));
    }
    
    //Método para eliminar un insumo de la compra
    public void eliminarDato() {
        int fila = vista.tblCompras.getSelectedRow();
        float insumototal = Float.parseFloat(String.valueOf(vista.tblCompras.getValueAt(fila, 4)));
        double total = 0;
        total = Float.parseFloat(vista.txtTotal.getText());
        total = total - insumototal;
        vista.txtTotal.setText(String.valueOf(total));
        
        if (fila >= 0) {
            dtm.removeRow(fila);
            listainsumos.remove(fila);
        } else {
            JOptionPane.showConfirmDialog(null, "Seleccione una Fila");
        }
    }
    
    //Constructor de parametros
    public CONT_Compras(Compras vista){
        this.vista = vista;
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
        this.vista.btnProveedores.addActionListener(this);
    }    
    
    //Método para iniciar la vista
    public void iniciarVista(){
        vista.setTitle("Compras");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        comboProveedores();
        comboInsumos();
        this.setModelo();
        vista.setVisible(true);
    }
    
    //Método para limpiar las cajas de texto
    public void limpiarCajasTexto(){
        vista.txtCantidad.setText("");
        vista.comboxInsumo.setSelectedIndex(0);
    }
    
    //Obtener los insumos
    public CONT_Compras.Insumos getInsumos(){
        try{
            conn = getConnection();
            
            String sql = "SELECT idinsumos, nombre, costo FROM insumos WHERE nombre = '" + vista.comboxInsumo.getSelectedItem().toString() + "';";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            
            while(rs.next()){
                return new CONT_Compras.Insumos(rs.getInt("idinsumos"), rs.getString("nombre"), 
                        Integer.parseInt(vista.txtCantidad.getText()), rs.getDouble("costo"));
            }
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
        return null;
    }
    
    //Método para obtener los proveedores en el comboBox
    public void comboProveedores(){
        try{
            //Abrir la conexión
            conn = getConnection();
            //Prepara la sentencia sql
            String sql = "SELECT nombre FROM proveedores WHERE estatus = 1;";
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
            String sql = "SELECT nombre FROM insumos WHERE estatus = 1;";
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
    
    //Seleccionar un item
    public void selectItem(String insumo) {
        try {
            conn = getConnection();
            
            String query = "SELECT idinsumos, nombre FROM insumos WHERE nombre = '"+insumo+"';;";
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();

            while(rs.next()){
                vista.comboxInsumo.setSelectedIndex(rs.getInt("idinsumos"));
            }        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public boolean cantidadValida(String num) {
        try {
            int cantidad = Integer.parseInt(num);
            
            if(vista.comboxInsumo.getSelectedIndex() <= 0) { // Menor cantidad o 0
                return true;
            } else if (cantidad <= 0) { // Mas cantidad que lo existente
                JOptionPane.showMessageDialog(null, "Introduzca una cantidad valida");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
    
    public int getProveedor() {
        try {
            conn = getConnection();
            String query = null;
            if (vista.comboxProveedor.getSelectedIndex() > 0) {
                query = "SELECT idProveedores FROM proveedores WHERE nombre = '"+vista.comboxProveedor.getSelectedItem().toString()+"';";
            } else {
                return 0;
            }
            
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();

            while(rs.next()){
                return rs.getInt("idProveedores");
            }        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return 1;
    }
    
    public void insertarDatos() {
        try{
            //Abrir la conexión
            conn = getConnection();
            //Preparando la instrucción
            
            String query = "INSERT INTO compras(idEmpleado, idProveedor, total) VALUES ("+Conexion.getUSER_ID()+","+getProveedor()+","+Float.parseFloat(vista.txtTotal.getText())+");";
            //Ejecución de la sentencia
            Statement stm = conn.createStatement();
            int register = stm.executeUpdate(query);
            //Cierre de la conexión
            Conexion.close(conn);
        }catch(SQLException ex){
            
        }
    }
    
    public int getMaxCompra() {
        try {
            conn = getConnection();
            String query = null;
            
            query = "SELECT MAX(compras.idcompras) FROM compras;";
            
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();

            while(rs.next()){
                return rs.getInt(1);
            }     
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return 1;        
    }
    
    public void insertarDetalles() {
        try{
            //Abrir la conexión
            conn = getConnection();
            
            //Preparando la instrucción
            Object[] datos = new Object[dtm.getColumnCount()];
            float total = 0;
            dtm.setRowCount(0);
            for (CONT_Compras.Insumos insumo : listainsumos) {
                datos[0] = insumo.id;
                datos[1] = insumo.insumo;
                datos[2] = insumo.cantidad;
                datos[3] = insumo.costo;
                datos[4] = insumo.totalInsumo;
                total += insumo.totalInsumo;
                // INSERT INTO compra_detalles(idCompra, idInsumo, cantidad) VALUES (NULL, '', '', '');
                String query = "INSERT INTO compra_detalles(idCompra, idInsumo, cantidad) VALUES ("+getMaxCompra()+","+insumo.id+","+insumo.cantidad+");";
                actualizarInsumo(insumo.id, insumo.cantidad);
                //Ejecución de la sentencia
                //Abrir la conexión
                conn = getConnection();
                Statement stm = conn.createStatement();
                int register = stm.executeUpdate(query);
                //Cierre de la conexión
            }   
            Conexion.close(conn);
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
    
    public void actualizarInsumo(int insumo, int cantidad) {
        try {
            //Abrir la conexión
            conn = getConnection();
            
            String query = "UPDATE insumos SET cantidad=cantidad+"+cantidad+" WHERE insumos.idinsumos="+insumo+";";
            Statement stm = conn.createStatement();
            int register = stm.executeUpdate(query);
            Conexion.close(conn);
            Conexion.close(stm);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vista.btnIngresar == evento.getSource()) { // Boton Ingresar presionado
            if (cantidadValida(vista.txtCantidad.getText()) && vista.comboxInsumo.getSelectedIndex() >= 1) { // CAMPOS OK
                if (duplicadoDato(getInsumos().id)) { // SERVICIO DUPLICADO
                    cant = cant+Integer.parseInt(this.vista.txtCantidad.getText());
                    if (cantidadValida(String.valueOf(cant))) {
                        modificarDato();
                    }    
                } else if (cantidadValida(this.vista.txtCantidad.getText())) {
                    llenarLista();
                    setDatos();                
                }
            } else {
                JOptionPane.showMessageDialog(null, "Existencia de campo sin rellenar");
            }
        }else if(vista.btnBorrar == evento.getSource()){
            if (vista.comboxInsumo.getSelectedIndex() > 0) {
                eliminarDato();
                limpiarCajasTexto();
            } else {
                JOptionPane.showMessageDialog(null, "Necesitas seleccionar un insumo para eliminarlo");
            }
        } else if (vista.btnLimpiar == evento.getSource()) {
            limpiarCajasTexto();
        } else if (vista.btnRegresar == evento.getSource()) {
            Vista.MenuCompras Nvista = new Vista.MenuCompras();
            Controlador.CONT_MenuCompras Ncontrolador = new Controlador.CONT_MenuCompras(Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
        } else if (vista.comboxInsumo == evento.getSource()) {
            vista.txtCantidad.setText("1");
        } else if (vista.btnTerminar == evento.getSource()){
            if(vista.tblCompras.getRowCount() > 0) {
                if (getProveedor() != 0) {
                    insertarDatos();
                    insertarDetalles();
                    Vista.Compras Nvista = new Vista.Compras();
                    Controlador.CONT_Compras Ncontrolador = new Controlador.CONT_Compras(Nvista);
                    Ncontrolador.iniciarVista();
                    vista.dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Introduzca un proveedor para terminar la venta");
                }      
            } else {
                JOptionPane.showMessageDialog(null, "Introduzca insumos para terminar la venta");
            }
        } else if (vista.btnProveedores == evento.getSource()) {
                Vista.Proveedores Nvista = new Vista.Proveedores();
                Modelo.MDL_Proveedores Nmodelo = new Modelo.MDL_Proveedores();
                Controlador.CONT_Proveedores Ncontrolador = new Controlador.CONT_Proveedores(Nmodelo, Nvista, true);
                Ncontrolador.iniciarVista();
                vista.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent evento) {
       if(vista.tblCompras == evento.getSource()){
            int  fila = vista.tblCompras.rowAtPoint(evento.getPoint());
            if (fila > -1){
                vista.txtCantidad.setText(String.valueOf(vista.tblCompras.getValueAt(fila, 2)));
                selectItem(String.valueOf(vista.tblCompras.getValueAt(fila, 1)));
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
    
    public class Insumos{
        int id;
        String insumo;
        int cantidad;
        double costo;
        double totalInsumo;
        
        public Insumos(int id, String insumo, int cantidad,  double costo){
            this.id = id;
            this.insumo = insumo;
            this.cantidad = cantidad;
            this.totalInsumo = (cantidad * costo);
            this.costo = costo;
        }
    }
    
}
