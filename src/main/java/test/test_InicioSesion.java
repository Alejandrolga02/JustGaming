/*
 * Descripcion: Prueba para Inicio de Sesion
 * Autor: Alejandro Iván Lizárraga Rojas
 * Fecha: 12 de Agosto de 2022
 */

package test;

import Controlador.CONT_InicioSesion;
import Modelo.MDL_InicioSesion;
import Vista.InicioSesion;

public class test_InicioSesion {
    public static void main(String[] args) {
        MDL_InicioSesion modelo = new MDL_InicioSesion();
        InicioSesion vista = new InicioSesion();
        CONT_InicioSesion controlador = new CONT_InicioSesion(modelo, vista);
        controlador.iniciarVista();
    }
}
