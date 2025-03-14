/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_automatas.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import proyecto_automatas.vista.Ventana;

/**
 *
 * @author Vanessa, Adrián
 */

  public class Control { // Define la clase Control.
    Ventana v; // Declara una variable de tipo Ventana.

    public Control(Ventana v) { // Constructor de la clase Control que recibe un objeto Ventana.
        this.v = v; // Asigna el objeto Ventana recibido a la variable v.
    }

    // Método para abrir archivo con file chooser.
    public void abrirArchivo() { 
        JFileChooser chooser = new JFileChooser(); // Crea un objeto JFileChooser para seleccionar archivos.
        chooser.setDialogTitle("Seleccionar Archivo"); // Establece el título del diálogo.
        String path = null; // Declara una variable para almacenar la ruta del archivo.
        int value = chooser.showOpenDialog(v); // Muestra el diálogo de selección de archivos y guarda el resultado.
        
        if (value == JFileChooser.APPROVE_OPTION) { // Verifica si se seleccionó un archivo.
            path = chooser.getSelectedFile().getAbsolutePath(); // Obtiene la ruta del archivo seleccionado.
            v.getjLabelArchivo().setText(path); // Muestra la ruta de acceso en la etiqueta de la ventana.
        }
        
        if (path == null) { // Verifica si no se seleccionó correctamente un archivo.
            JOptionPane.showMessageDialog(v, "El archivo no fue seleccionado correctamente"); // Muestra un mensaje de error.
            return; // Termina la ejecución del método.
        }
        
        leerArchivo(path); // Llama al método leerArchivo con la ruta del archivo.
    }

    public void leerArchivo(String archivo) { 
        try {            
            BufferedReader br = new BufferedReader(new FileReader(archivo)); 
            // Crea un BufferedReader para leer el archivo. FileReader es una clase anónima.
            String linea; // Declara una variable para almacenar cada línea del archivo.
            
            while ((linea = br.readLine()) != null) { // Lee cada línea del archivo hasta que no haya más.
                v.getTxtContenido().append(linea + "\n"); // Agrega la línea leída al contenido del JTextArea en la ventana.
            }
        } catch (IOException e) { // Captura excepciones de tipo IOException.
            System.err.print("Error al leer el archivo: " + e.getMessage()); // Muestra un mensaje de error en la consola.
        }
    }    
}


