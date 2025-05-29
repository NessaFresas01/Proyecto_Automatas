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
    private static ArrayList<Lexema> lista;
    private static int index;
    private static Lexema actual;
    
    public static boolean analizar(ArrayList<Lexema> lexemas){
        lista = lexemas;
        index = 0;
        
        if(lista.isEmpty()) return false;
        return false;
    }
}
 //AUN NO SE TERMINA PIPIPI