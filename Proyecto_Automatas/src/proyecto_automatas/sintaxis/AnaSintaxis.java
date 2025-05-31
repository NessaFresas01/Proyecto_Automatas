/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_automatas.sintaxis;

import java.util.ArrayList;
import proyecto_automatas.Lexemas.Lexema;
import proyecto_automatas.control.Control;

/**
 *
 * @author Vanessa, Adrian 
 */
public class AnaSintaxis {
    private static ArrayList<Lexema> lista; //Lista para los lexemas a analizar
    private static int index;               //índice para la lista de lexemas
    private static Lexema actual;           //Variable para poder evaluar el lexema 
    private static int tokenActual;         //Variable para los tokens 
    
    //Método principal para inciar el análisis sintático
    public static boolean analizar(ArrayList<Lexema> lexemas){
        lista = lexemas;
        index = 0;
        
        if(lista.isEmpty()) return false;

        actual = lista.get(index);
        tokenActual = actual.getTokem();
        
        //Definición de análisis con un try para:
        //<Programa> -> <Bloque>.
        
        try{
            Programa(); //Inicio
            return true;
        }catch(Exception e){
            System.out.println("Error"+e.getMessage());
            return false;
         }
        }
    
    //Avanza al siguente lexema 
    private static void avanzarToken(){
        if(index < lista.size() -1){
          index++;
          actual = lista.get(index);
          
        }else{
            tokenActual = -1; //Fin
        }
        
    }
    /**
     * Regla de producción <Programa> -> <Bloque>.
     * Se espera un punto (.) al final del programa.
     */

    private static void Programa() throws Exception {
        Bloque(); //Analizador para el bloque principal del lexema
        
        if(tokenActual == 120){
            //El . llega el valor de 120
            avanzarToken(); //Aqui entro el punto
            System.out.println("Correcto");
        }else{
            throw new Exception("Error, se esperaba un .");
        }
    }

    private static void Bloque() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        
}
