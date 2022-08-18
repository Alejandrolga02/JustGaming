/*
 * Descripcion: Controlador para CONT_Ventas
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Controlador;

import static Modelo.Conexion.getConnection;
import Modelo.MDL_DetallesVentas;
import Modelo.MDL_Ventas;
import Vista.DetallesVentas;
import Vista.Ventas;
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
import javax.swing.table.DefaultTableModel;

public class CONT_Ventas implements ActionListener, MouseListener {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    Ventas vista;
    MDL_Ventas modelo;
    DefaultTableModel dtm = new DefaultTableModel();
            
    public CONT_Ventas (MDL_Ventas modelo, Ventas vista) {
        this.vista = vista;
        this.modelo = modelo;
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
    }
    
    public void iniciarVista() {
        vista.setTitle("Ventas");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        fillCombox(0);
        fillCombox(1);
        vista.setVisible(true);
    }
    
    public void limpiarCajasTexto(){
        vista.txtCantidad.setText("");
        vista.txtTotal.setText("");
        vista.comboxCliente.setSelectedIndex(0);
        vista.comboxServicio.setSelectedIndex(0);
        vista.lblDisponibles.setVisible(false);
    }
    
    public int disponibilidad() {
        try {
            conn = getConnection();

            String query = "SELECT servicios.idServicio, insumos.cantidad FROM servicios LEFT JOIN insumos ON servicios.idInsumo = insumos.idinsumos WHERE servicios.idServicio = "+getIdServicio()+";";

            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return 0;
    }
    
    public int getIdServicio() {
        try {
            conn = getConnection();
            
            String query = "SELECT idServicio FROM servicios WHERE servicio = '"+vista.comboxServicio.getSelectedItem().toString()+"';";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while(rs.next()){
                return rs.getInt("idServicio");
            }        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return 5;
    }
    
    public void cantidadValida() {
        try {
            int cantidad = Integer.parseInt(this.vista.txtCantidad.getText());
            int disponible = disponibilidad();
            this.vista.lblDisponibles.setVisible(true);
            if(cantidad > 0) { // Cantidad valida
                JOptionPane.showMessageDialog(null, "Introduzca una cantidad valida");
            } else if (cantidad > disponible) {
                JOptionPane.showMessageDialog(null, "No contamos con los insumos suficientes");
            }
        } catch (NumberFormatException e) {
            
        }
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

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vista.btnIngresar == evento.getSource()) { // Boton Ingresar presionado
            /*boolean isValid = precioValido(vista.txtPrecio.getText());
            
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
            }*/
        }else if(vista.btnBorrar == evento.getSource()){
            /*if (!vista.txtIdServicio.getText().isEmpty()) { // Validacion de campo
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
            }*/
        } else if (vista.btnLimpiar == evento.getSource()) {
            limpiarCajasTexto();
        } else if (vista.btnRegresar == evento.getSource()) {
            Vista.MenuVentas Nvista = new Vista.MenuVentas();
            Controlador.CONT_MenuVentas Ncontrolador = new Controlador.CONT_MenuVentas(Nvista);
            Ncontrolador.iniciarVista();
            vista.dispose();
        } else if (vista.comboxServicio == evento.getSource()) {
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent evento) {
        if(vista.tblVentas == evento.getSource()){
            int  fila = vista.tblVentas.rowAtPoint(evento.getPoint());
            if (fila > -1){
                vista.txtCantidad.setText(String.valueOf(vista.tblVentas.getValueAt(fila, 0)));
                vista.txtTotal.setText(String.valueOf(vista.tblVentas.getValueAt(fila, 1)));
                vista.txtCantidad.setText(String.valueOf(vista.tblVentas.getValueAt(fila, 2)));
                //selectItem(String.valueOf(vista.tblServicios.getValueAt(fila, 3)));
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
    
    public class servicio {
        public int id;
        public String servicio;
        public int cantidad;
        public float total;

        public servicio(int id, String servicio, int cantidad, float total) {
            this.id = id;
            this.servicio = servicio;
            this.cantidad = cantidad;
            this.total = total;
        }
    }
}
