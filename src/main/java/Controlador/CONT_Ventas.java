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

public class CONT_Ventas implements ActionListener, MouseListener {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    Ventas vista;
    MDL_Ventas modelo;
    
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
    }
    
    public void fillCombox(int id) {
        try {
            conn = getConnection();

            String query = null;

            if (id == 0) {
                query = "SELECT idCliente, nombreCompleto FROM cliente WHERE idCliente != 1;";
            } else {
                query = "SELECT idServicio, servicio FROM servicios;";
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
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
