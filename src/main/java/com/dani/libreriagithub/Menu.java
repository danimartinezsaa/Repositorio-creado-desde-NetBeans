/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dani.libreriagithub;

import javax.swing.JOptionPane;

/**
 *
 * @author dani
 */
public class Menu{

    public static void opciones(){
        String nombre;
        String[] menu={"Crear repositorio", "Clonar", "Commit", "Inicializar","Push", "Salir"};
        int opcion=JOptionPane.showOptionDialog(
                null //componente
                ,
                 "Seleccione una opción" // Mensaje
                ,
                 "GitHub" // Titulo en la barra del cuadro
                ,
                 JOptionPane.DEFAULT_OPTION // Tipo de opciones
                ,
                 JOptionPane.INFORMATION_MESSAGE // Tipo de mensaje (icono)
                ,
                 null // Icono (ninguno)
                ,
                 menu // Opciones personalizadas
                ,
                 null // Opcion por defecto
        );

        switch(opcion){
            case 0:
                nombre=JOptionPane.showInputDialog("Inserte el nombre del repositorio");
                Metodos.crear(nombre);
                break;
            case 1:
                nombre=JOptionPane.showInputDialog("Inserte el nombre que le va a dar al nuevo proyecto.");
                String url=JOptionPane.showInputDialog("Inserte url del proyecto.");
                Metodos.clonar(url, nombre);
                break;
            case 2:
                nombre=JOptionPane.showInputDialog("Inserte la ruta del repositorio");
                String msn=JOptionPane.showInputDialog("Inserte el mensaje del commit");
                Metodos.hacerCommit(nombre,msn);
                break;
            case 3:
                nombre=JOptionPane.showInputDialog("Inserte la ruta del proyecto:");
                Metodos.inicializarRepo(nombre);
                break;
            case 4:
                
                nombre=JOptionPane.showInputDialog("Inserte la ruta del proyecto:");
                url=JOptionPane.showInputDialog("Inserte la url del repositorio remoto");
                String usuario=JOptionPane.showInputDialog("Inserte usuario:");
                String contrasena=JOptionPane.showInputDialog("Inserte contraseña:");
                Metodos.push(url, nombre,contrasena,usuario);
                break;
            case 5:
                System.exit(0);
        }
    }
}
