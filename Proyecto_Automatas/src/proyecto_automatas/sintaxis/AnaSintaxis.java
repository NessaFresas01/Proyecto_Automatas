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
    private static int index = 0;               //índice para la lista de lexemas
    private static Lexema actual;           //Variable para poder evaluar el lexema 
    private static int tokenActual;         //Variable para los tokens 

    //Método principal para inciar el análisis sintático
    public static boolean analizar(ArrayList<Lexema> lexemas) {
        lista = lexemas;
        index = 0;

        if (lista.isEmpty()) {
            return false;
        }

        actual = lista.get(index);
        tokenActual = actual.getTokem();

        //Definición de análisis con un try para:
        //<Programa> -> <Bloque>.
        try {
            Programa(); //Inicio
            return true;
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return false;
        }
    }

    //Avanza al siguente lexema 
    private static void avanzarToken() {
        if (index < lista.size() - 1) {
            index++;
            actual = lista.get(index);

        } else {
            tokenActual = -1; //Fin
        }

    }

    /**
     * Regla <Programa> -> <Bloque>. Se espera un punto (.) al final del
     * programa.
     */
    private static void Programa() {
        Bloque(); //Analizador para el bloque principal del lexema 
        if (tokenActual == 120) {
            //El . llega el valor de 120
            avanzarToken(); //Aqui entro el punto
            System.out.println("Correcto");
        } else {
            throw new RuntimeException("Se esperaba '.' al final del programa");
        }
        //System.out.println(".");
    }

    //<Bloque> -> <bConstante> <bDeclaracion> <bProcedimiento> <Proposicion>
    private static void Bloque() {
        bConstante();
        /* bDeclaracion();
       bProcedimiento();
       Proposicion();*/
    }

    //<bAsig> ->  id = num
    private static void bAsig() {
        if (tokenActual == 4) { //4 corresponde a id
            avanzarToken();
            if (tokenActual == 20) { //20 corresponde a  = 
                avanzarToken();
                if (tokenActual == 7) { // 7 Corresponde a num
                    avanzarToken();
                }
            } else {
                throw new RuntimeException("Se esperaba un num al despues de = ");
            }

        } else {
            throw new RuntimeException("Se esperaba un identificador");
        }
    }
    // <bListaAsig> -> <bAsig> <bListaAsigA>

    private static void bListaAsig() {
        bAsig();
        bListaAsigA();
    }
    // <bListaAsigA> -> ∅ | , <bListaAsig>

    private static void bListaAsigA() {
        if (tokenActual == 25) { // el 25 es para la ,
            avanzarToken();
            bListaAsig();
        }
    }

    // <bConstante> -> const <bListaAsig> ; | ∅
    private static void bConstante() {
        if (tokenActual == 1) { //1 = Const
            avanzarToken();
            bListaAsig();
            if (tokenActual == 30) { // 3o corresponde a ;
                avanzarToken();
            } else {
                throw new RuntimeException("Se esperaba ';' después de la declaración de constantes");

            }

        }
    }

    // <bIdentificador> -> id | id , <bIdentificador>
    private static void bIdentificador() {
        if (tokenActual == 4) { //4 corresponde a id
            avanzarToken();
            if (tokenActual == 25) {
                avanzarToken();
                bIdentificador();
            }
        } else {
            throw new RuntimeException("Se esperaba un identificador");
        }
    }
    // <bDeclaracion> -> var <bIdentificador> ; | ∅

    private static void bDeclaracion() {
        if (tokenActual == 10) { //10 corresponde a var
            avanzarToken();
            bIdentificador();
            if (tokenActual == 30) { //30 corresponde a ;
                avanzarToken();
            } else {
                throw new RuntimeException("Se esperaba ';' después de la declaración de variables");
            }
        }
    }

    //Chaguitos para q jale :D
    //<bProcedimiento> -> Proced id ; <Bloque> ; | ∅
    private static void bProcedimiento() {
        if (tokenActual == 9) { //El 9 corresponde a const
            avanzarToken();
            if (tokenActual == 4) { //4 corresponde a id
                avanzarToken();
                if (tokenActual == 30) { //30 es para ;
                    avanzarToken();
                    Bloque();
                    if (tokenActual == 30) { //30 es para ;
                        avanzarToken();
                    } else {
                        throw new RuntimeException("Se esperaba ';' después del bloque del procedimiento");
                    }
                } else {
                    throw new RuntimeException("Se esperaba ';' después del bloque del indentificador");
                }
            } else {
                throw new RuntimeException("Se esperaba un identificador despu[es de proced");
            }
        }
    }

    // <Proposicion>
    private static void Proposicion() {

        //Se supone que al entrar el . se finaliza el programa
        //el -1 verifica que ya no hay tokens que analizar
        //No terminal 
        if (tokenActual != 120 && tokenActual != -1) {
            //Si aun hay tokens entonces entra de nuevo a pMultiplo();
            pMultiplo();
        }

    }

    // <pMultiplo> -> 
    private static void pMultiplo() {
        switch (tokenActual) {
            case 65: // {
                pProposicion();
                break;
            case 4: // id
                pAsignacion();
                break;
            case 8: //Print
                pPrint();
                break;
            case 6: //input
                pInput();
                break;
            case 2: //exec
                pExce();
                break;
            case 5: //if
                pCondicion();
                break;
            case 11: //while
                pWhile();
                break;
            case 3: //for
                pFor();
                break;
            default:
                throw new RuntimeException("Proposicion no reconocidaF");
        }
    }

    // <pProposicion> -> { <pSecuencia> }
    private static void pProposicion() {
        if (tokenActual == 65) { // {
            avanzarToken();
            pSecuencia();
            if (tokenActual == 70) { // }
                avanzarToken();
            } else {
                throw new RuntimeException("Se esperaba '}' para cerrar la proposición");

            }
        } else {
            throw new RuntimeException("Se esperaba '{' para iniciar proposición");

        }
    }

    // <pSecuencia> -> <Proposicion> <pSecuenciaA>
    private static void pSecuencia() {
        Proposicion();
        pSecuenciaA();
    }

    // <pSecuenciaA> -> ∅ | ; <pSecuencia>
    private static void pSecuenciaA() {
        if (tokenActual == 30) { //30 es para ;
            avanzarToken();
            pSecuenciaA();
        }
    }

    // <pAsignacion> -> id = <Expresion>
    private static void pAsignacion() {
        if (tokenActual == 4) { //4 es para el id
            avanzarToken();
            if (tokenActual == 20) { // 20 es para el =
                avanzarToken();
                Expresion();

            } else {
                throw new RuntimeException("Se esperaba '=' ");
            }
        } else {
            throw new RuntimeException("Se esperaba un identificador");
        }

    }

    // <pPrint> -> print <pDato>
    private static void pPrint() {
        if (tokenActual == 8) { // 8 para print
            avanzarToken();
            pDato();
        } else {
            throw new RuntimeException("Se esperaba 'print");
        }
    }

    // <pInput> -> input id
    private static void pInput() {
        if (tokenActual == 6) { // 6 es para input
            avanzarToken();
            if (tokenActual == 4) { //4 corresponde a id
                avanzarToken();
            } else {
                throw new RuntimeException("Se esperaba identificar despues de input");
            }
        } else {
            throw new RuntimeException("Se esperaba 'input' ");
        }
    }

    // <pExec> -> exec id
    private static void pExce() {
        if (tokenActual == 2) { //el 2 corresponde a exec
            avanzarToken();
            if (tokenActual == 4) { //4 es para id
                avanzarToken();

            } else {
                throw new RuntimeException("Se esperaba identificador despues de 'exec' ");
            }
        } else {
            throw new RuntimeException("Se esperaba 'exec' ");
        }
    }

    // <pCondicion> -> if <cCondicion> : <Proposicion>
    private static void pCondicion() {
        if (tokenActual == 5) { //el 5 corresponde a exec
            avanzarToken();
            cCondicion();
            if (tokenActual == 75) { //4 es para id
                avanzarToken();
                Proposicion();

            } else {
                throw new RuntimeException("Se esperaba : despues de condicion ");
            }
        } else {
            throw new RuntimeException("Se esperaba 'if' ");
        }
    }

    // <pWhile> -> while <cCondicion> : <Proposicion>
    private static void pWhile() {
        if (tokenActual == 11) { //el 11 corresponde a while
            avanzarToken();
            cCondicion();
            if (tokenActual == 75) { //75 es para :
                avanzarToken();
                Proposicion();
            } else {
                throw new RuntimeException("Se esperaba : despues de condicion ");
            }
        } else {
            throw new RuntimeException("Se esperaba 'while' ");
        }
    }

    // <pFor> -> for id = <Expresion> <pFlechas> <Expresion> : <Proposicion>
    private static void pFor() {
        //Pendiente pq esta demasido largo y aaaaaaaaa 
    }
    // <pFlechas> -> -> | <-

    private static void pFlechas() {
        if (tokenActual == 80 || tokenActual == 85) { //Puede ser -> o <-
            avanzarToken();
        } else {
            throw new RuntimeException("Se esperaba -> o <-");
        }

    }

    // <pDato> -> id | num
    private static void pDato() {
        if (tokenActual == 4 || tokenActual == 7) { //Puede ser id o num
            avanzarToken();
        } else {
            throw new RuntimeException("Se esperaba un identificador o numero ");
        }
    }

    // <cCondicion> -> <Expresion> <cMultiplo> <Expresion>
    private static void cCondicion() {
        Expresion();
        cMultiplo();
        Expresion();
    }

    // <cMultiplo> -> == | <> | < | > | <= | >=
    private static void cMultiplo() {

    }

    // <Expresion> -> <Termino> <eExpresionA>
    private static void Expresion() {

    }

    // <eExpresionA> -> ∅ | <eSignos> <Expresion>
    private static void eExpresionA() {

    }
    // <Termino> -> <Factor> <tTerminoA>

    private static void Termino() {

    }
    // <tTerminoA> -> ∅ | <tSignos> <Termino>

    private static void tTerminoA() {

    }
    // <Factor> -> <fFactor>

    private static void Factor() {

    }
    // <fFactor> -> <fExp> | id | num

    private static void fFactor() {

    }
    // <fExp> -> ( <Expresion> )

    private static void fExp() {

    }

}
