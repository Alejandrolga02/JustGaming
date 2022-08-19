/*
 * Descripcion: Controlador para CONT_Ventas
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Controlador;

import Modelo.Conexion;
import static Modelo.Conexion.getConnection;
import Vista.Ventas;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CONT_Ventas implements ActionListener, MouseListener {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    Ventas vista;    
    DefaultTableModel dtm = new DefaultTableModel();
    ArrayList<CONT_Ventas.Servicio> listaServicios =  new ArrayList<>();
    int cant;
    int index;
    
    public void llenarLista() {
        listaServicios.add(getServicio());
    }
    
    public void setModelo() {
        String[] header = {"ID", "SERVICIO", "CANTIDAD", "PRECIO UNITARIO","TOTAL"};
        dtm.setColumnIdentifiers(header);
        vista.tblVentas.setModel(dtm);
    }
    
    public void setDatos() {
        Object[] datos = new Object[dtm.getColumnCount()];
        float total = 0;
        dtm.setRowCount(0);
        for (CONT_Ventas.Servicio servicio : listaServicios) {
            datos[0] = servicio.id;
            datos[1] = servicio.servicio;
            datos[2] = servicio.cantidad;
            datos[3] = servicio.precioUnitario;
            datos[4] = servicio.total;
            total += servicio.total;
            dtm.addRow(datos);
        }
        vista.tblVentas.setModel(dtm);
        vista.txtTotal.setText(String.valueOf(total));
    }
    
    public void eliminarDato() {
        int fila = vista.tblVentas.getSelectedRow();
        float total = 0;
        float totalServicio = Float.parseFloat(String.valueOf(vista.tblVentas.getValueAt(fila, 4)));
        total = Float.parseFloat(vista.txtTotal.getText());
        total = total - totalServicio;
        vista.txtTotal.setText(String.valueOf(total));
        
        if (fila >= 0) {
            dtm.removeRow(fila);
            listaServicios.remove(fila);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una Fila");
        }
    }
    
    public boolean duplicadoDato(int id) {
        index = id;
        for (CONT_Ventas.Servicio servicio : listaServicios) {
            if(id == servicio.id) {
                cant = servicio.cantidad;
                servicio.total = servicio.cantidad * servicio.precioUnitario;
                return true;
            }
        }
        return false;
    }
    
    public void modificarDato() {
        Object[] datos = new Object[dtm.getColumnCount()];
        float total = 0;
        dtm.setRowCount(0);
        for (CONT_Ventas.Servicio servicio : listaServicios) {
            if (servicio.id == index) {
                datos[0] = servicio.id;
                datos[1] = servicio.servicio;
                servicio.cantidad = cant;
                datos[2] = servicio.cantidad;
                datos[3] = servicio.precioUnitario;
                servicio.total = servicio.precioUnitario * servicio.cantidad;                
                datos[4] = servicio.total;                
            } else {
                datos[0] = servicio.id;
                datos[1] = servicio.servicio;
                datos[2] = servicio.cantidad;
                datos[3] = servicio.precioUnitario;
                datos[4] = servicio.total;
            }
            total += servicio.total;                
            dtm.addRow(datos);
        }
        vista.txtTotal.setText(String.valueOf(total));
        vista.tblVentas.setModel(dtm);
    }
                    
    public CONT_Ventas (Ventas vista) {
        this.vista = vista;
        this.vista.comboxCliente.addActionListener(this);
        this.vista.comboxServicio.addActionListener(this);
        this.vista.txtCantidad.addActionListener(this);
        this.vista.txtTotal.addActionListener(this);
        this.vista.btnTerminar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.btnIngresar.addActionListener(this);
        this.vista.btnBorrar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.tblVentas.addMouseListener(this);
        this.vista.lblDisponibles.setVisible(false);
        this.vista.btnClientes.addActionListener(this);
    }
    
    public void iniciarVista() {
        vista.setTitle("Ventas");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        fillCombox(0);
        fillCombox(1);
        this.setModelo();
        vista.setVisible(true);
    }
    
    public void limpiarCajasTexto(){
        vista.txtCantidad.setText("1");
        vista.comboxServicio.setSelectedIndex(0);
        vista.lblDisponibles.setVisible(false);
    }
    
    public int disponibilidad(int id) {
        try {
            conn = getConnection();

            String query = "SELECT cantidad FROM insumos WHERE insumos.idinsumos = "+id+";";

            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                return rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return 0;
        }
        return 0;
    }
    
    public CONT_Ventas.Servicio getServicio() {
        try {
            conn = getConnection();
            
            String query = "SELECT idServicio, servicio, precio, idInsumo FROM servicios WHERE servicio = '"+vista.comboxServicio.getSelectedItem().toString()+"';";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while(rs.next()){
                return new CONT_Ventas.Servicio(rs.getInt("idServicio"), rs.getString("servicio"), Integer.parseInt(vista.txtCantidad.getText()), rs.getFloat("precio"), rs.getInt("idInsumo"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
    
    public boolean cantidadValida(String num) {
        try {
            int cantidad = Integer.parseInt(num);
            int disponible = 0;
            if (vista.comboxServicio.getSelectedIndex() > 0) {
                disponible = disponibilidad(getServicio().insumo);
            } else {
                return false;
            }
            
            if(cantidad <= 0) { // Menor cantidad o 0
                JOptionPane.showMessageDialog(null, "Introduzca una cantidad valida");
                return false;
            } else if (cantidad > disponible) { // Mas cantidad que lo existente
                JOptionPane.showMessageDialog(null, "No contamos con los insumos suficientes");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
    
    public void fillCombox(int id) {
        try {
            conn = getConnection();

            String query = null;

            if (id == 0) {
                query = "SELECT idCliente, nombreCompleto FROM cliente WHERE idCliente != 1 AND estatus = 1;";
            } else {
                query = "SELECT idServicio, servicio FROM servicios WHERE estatus = 1;";
            }

            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            if (id == 0) {
                while(rs.next()){
                    vista.comboxCliente.addItem(rs.getString("nombreCompleto"));
                }
            } else {
                while(rs.next()){
                    vista.comboxServicio.addItem(rs.getString("servicio"));
                }
            }
        } catch (SQLException ex) {
            
        }
    }
    
    public void selectItem(String servicio) {
        try {
            conn = getConnection();
            
            String query = "SELECT idServicio, servicio FROM servicios WHERE servicio = '"+servicio+"';;";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while(rs.next()){
                vista.comboxServicio.setSelectedIndex(rs.getInt("idServicio"));
            }        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public int getCliente() {
        try {
            conn = getConnection();
            String query = null;
            if (vista.comboxCliente.getSelectedIndex() > 0) {
                query = "SELECT idCliente FROM cliente WHERE nombreCompleto = '"+vista.comboxCliente.getSelectedItem().toString()+"';";
            } else {
                return 1;
            }
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while(rs.next()){
                return rs.getInt("idCliente");
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
            
            
            String query = "INSERT INTO ventas(idEmpleado, idCliente,total) VALUES ("+Conexion.getUSER_ID()+","+getCliente()+","+Float.parseFloat(vista.txtTotal.getText())+");";
            //Ejecución de la sentencia
            Statement stm = conn.createStatement();
            int register = stm.executeUpdate(query);
            //Cierre de la conexión
            Conexion.close(conn);
        }catch(SQLException ex){
            
        }
    }
    
    public int getMaxVenta() {
        try {
            conn = getConnection();
            String query = null;
            
            query = "SELECT MAX(ventas.idVenta) FROM ventas;";
            
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

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
            for (CONT_Ventas.Servicio servicio : listaServicios) {
                datos[0] = servicio.id;
                datos[1] = servicio.servicio;
                datos[2] = servicio.cantidad;
                datos[3] = servicio.precioUnitario;
                datos[4] = servicio.total;
                total += servicio.total;
                
                String query = "INSERT INTO ventas_detalle(idVentas, idServicio,cantidad) VALUES ("+getMaxVenta()+","+servicio.id+","+servicio.cantidad+");";
                actualizarInsumo(servicio.insumo, servicio.cantidad);
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
            
            String query = "UPDATE insumos SET cantidad=cantidad-"+cantidad+" WHERE insumos.idinsumos="+insumo+";";
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
            if (cantidadValida(vista.txtCantidad.getText()) && vista.comboxServicio.getSelectedIndex() >= 1) { // CAMPOS OK
                if (duplicadoDato(getServicio().id)) { // SERVICIO DUPLICADO
                    cant = cant+Integer.parseInt(this.vista.txtCantidad.getText());
                    if (cantidadValida(String.valueOf(cant))) {
                        modificarDato();
                        limpiarCajasTexto();
                    }    
                } else if (cantidadValida(this.vista.txtCantidad.getText())) {
                    llenarLista();
                    setDatos();
                    limpiarCajasTexto();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Existencia de campo sin rellenar");
            }
        }else if(vista.btnBorrar == evento.getSource()){
            if (vista.comboxServicio.getSelectedIndex() > 0) {
                eliminarDato();
                limpiarCajasTexto();
            } else {
                JOptionPane.showMessageDialog(null, "Necesitas seleccionar un servicio para eliminarlo");
            }
        } else if (vista.btnLimpiar == evento.getSource()) {
            limpiarCajasTexto();
        } else if (vista.btnRegresar == evento.getSource()) {
            Vista.MenuVentas Nvista = new Vista.MenuVentas();
            Controlador.CONT_MenuVentas Ncontrolador = new Controlador.CONT_MenuVentas(Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
        } else if (vista.comboxServicio == evento.getSource()) {
            vista.txtCantidad.setText("1");
            if (vista.comboxServicio.getSelectedIndex() == 0) {
                vista.lblDisponibles.setVisible(false);
            } else {
                vista.lblDisponibles.setVisible(true);
                vista.lblDisponibles.setText("Disponibles: "+disponibilidad(getServicio().insumo));
            }
        } else if (vista.btnTerminar == evento.getSource()){
            if(vista.tblVentas.getRowCount() > 0) {
                insertarDatos();
                insertarDetalles();
                Vista.Ventas Nvista = new Vista.Ventas();
                Controlador.CONT_Ventas Ncontrolador = new Controlador.CONT_Ventas(Nvista);
                Ncontrolador.iniciarVista();
                vista.dispose();                
            } else {
                JOptionPane.showMessageDialog(null, "Introduzca servicios para terminar la venta");
            }
        } else if (vista.btnClientes == evento.getSource()) {
            Vista.Clientes Nvista = new Vista.Clientes();
            Modelo.MDL_Clientes Nmodelo = new Modelo.MDL_Clientes();
            Controlador.CONT_Clientes Ncontrolador = new Controlador.CONT_Clientes(Nmodelo, Nvista, true);
            Ncontrolador.iniciarVista();
            vista.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent evento) {
        if(vista.tblVentas == evento.getSource()){
            int  fila = vista.tblVentas.rowAtPoint(evento.getPoint());
            if (fila > -1){
                vista.txtCantidad.setText(String.valueOf(vista.tblVentas.getValueAt(fila, 2)));
                selectItem(String.valueOf(vista.tblVentas.getValueAt(fila, 1)));
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
    
    public class Servicio {
        public int id;
        public String servicio;
        public int cantidad;
        public float precioUnitario;
        public float total;
        public int insumo;

        public Servicio(int id,String servicio, int cantidad, float precioUnitario, int insumo) {
            this.id = id;
            this.servicio = servicio;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
            this.total = cantidad*precioUnitario;
            this.insumo = insumo;
        }
    }
}
