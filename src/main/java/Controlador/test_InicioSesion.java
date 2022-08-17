/*
 * Descripcion: Prueba para Inicio de Sesion
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package Controlador;

import Modelo.MDL_InicioSesion;
import Vista.InicioSesion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class test_InicioSesion {
    public static void main(String[] args) {
        MDL_InicioSesion modelo = new MDL_InicioSesion();
        InicioSesion vista = new InicioSesion();
        CONT_InicioSesion controlador = new CONT_InicioSesion(modelo, vista);
        controlador.iniciarVista();
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CONT_InicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
