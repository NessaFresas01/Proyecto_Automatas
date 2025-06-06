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
        errores.clear();
        if (lista.isEmpty()) {
            throw new RuntimeException("El programa está vacío");
        }

        actual = lista.get(index);
        tokenActual = actual.getTokem();

        //<Programa> -> <Bloque>.
        Programa(); //Inicio
        return true;
    }

    //Avanza al siguente lexema 
    private static void avanzarToken() {
        if (index < lista.size() - 1) {
            index++;
            actual = lista.get(index);
            tokenActual = actual.getTokem();
        } else {
            tokenActual = -1; //Fin
            int lineaFin = lista.isEmpty() ? 0 : lista.get(lista.size() - 1).getLinea() + 1;
            actual = new Lexema("fin de entrada", -1, lineaFin);

        }

    }

    public static ArrayList<String> errores = new ArrayList<>();

    /**
     * Regla <Programa> -> <Bloque>. Se espera un punto (.) al final del
     * programa.
     */
    private static void Programa() {
        /* Bloque(); //Analizador para el bloque principal del lexema 
        if (tokenActual == 120) {
            //El . llega el valor de 120
            avanzarToken(); //Aqui entro el punto
        } else {
            lanzarError("Programa", ".");
        }
        //System.out.println(".");*/
        Bloque();
        System.out.println("Revisando token para '.' - tokenActual=" + tokenActual + ", lexema='" + obtenerLexemaActual() + "'");
        if (tokenActual == 120) {
            avanzarToken();
        } else {
            lanzarError("Programa", ".");
        }
    }

    //<Bloque> -> <bConstante> <bDeclaracion> <bProcedimiento> <Proposicion>
    private static void Bloque() {
        bConstante();
        bDeclaracion();
        bProcedimiento();
        Proposicion();
    }

    //<bAsig> ->  id = num
    private static void bAsig() { //se puede cambiar
        if (tokenActual == 4) { //4 corresponde a id
            avanzarToken();
            if (tokenActual == 20) { //20 corresponde a  = 
                avanzarToken();
                if (tokenActual == 7) { // 7 Corresponde a num
                    avanzarToken();
                } else {
                    lanzarError("bAsig", "num");
                }
            } else {
                lanzarError("bAsig", "=");
            }

        } else {
            lanzarError("bAsig", "identificador");
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
                lanzarError("bConstante", ";");

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
            lanzarError("bIdentificador", "idetificador");
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
                lanzarError("bDeclaracion", ";");
            }
        }
    }

    //Chaguitos para q jale :D
    //corregir, depues del ; y antes de la barra de | lleva bprocedimiento
    //<bProcedimiento> -> Proced id ; <Bloque> ; | ∅
    private static void bProcedimiento() { //se puede cambiar
        if (tokenActual != 9) { //El 9 corresponde a const
            return;
        }
        avanzarToken();

        if (tokenActual != 4) { //4 corresponde a id
            lanzarError("bProcedimiento", "identificador");
        }
        avanzarToken();

        if (tokenActual == 30) { //30 es para ;
            lanzarError("bProcedimiento", ";");
        }
        avanzarToken();
        Bloque();

        if (tokenActual == 30) { //30 es para ;
            lanzarError("bProcedimiento", ";");
        }
        avanzarToken();
        bProcedimiento();

    }

    // <pProposicion> -> { <pSecuencia> }
    private static void Proposicion() { //corregir
        pMultiplo();
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
                lanzarError("Proposicion", "una instrucción válida (como print, if, while, etc.)");
                avanzarToken();
        }
    }

    // <pProposicion> -> {; <pSecuencia> } //falta programar el ;
    private static void pProposicion() {
        if (tokenActual == 65) { // {
            avanzarToken();
            pSecuencia();
            if (tokenActual == 30) { // ;
                avanzarToken();
                if (tokenActual == 70) { // }
                    avanzarToken();
                } else {
                    lanzarError("pProposicion", "{");
                }
            } else {
                lanzarError("pProposicion", ";");
            }
        } else {
            lanzarError("pProposicion", "}");
        }
    }

    // <pSecuencia> -> <Proposicion> <pSecuenciaA>
    private static void pSecuencia() {
        Proposicion(); //
        pSecuenciaA();
    }

    // <pSecuenciaA> -> ∅ | ; <pSecuencia>
    private static void pSecuenciaA() {
        if (tokenActual == 30) { //30 es para ;
            avanzarToken();
            pSecuencia();
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
                lanzarError("pAsignacion", "=");
            }
        } else {
            lanzarError("pAsignacion", "identificador");
        }

    }

    // <pPrint> -> print <pDato>
    private static void pPrint() {
        if (tokenActual == 8) { // 8 para print
            avanzarToken();
            pDato();
        } else {
            lanzarError("pPrint", "print");
        }
    }

    // <pInput> -> input id
    private static void pInput() {
        if (tokenActual == 6) { // 6 es para input
            avanzarToken();
            if (tokenActual == 4) { //4 corresponde a id
                avanzarToken();
            } else {
                lanzarError("pInput", "identificador después de 'input'");
            }
        } else {
            lanzarError("pInput", "input");
        }
    }

    // <pExec> -> exec id
    private static void pExce() {
        if (tokenActual == 2) { //el 2 corresponde a exec
            avanzarToken();
            if (tokenActual == 4) { //4 es para id
                avanzarToken();

            } else {
                lanzarError("pExec", "identificador después de 'exec'");
            }
        } else {
            lanzarError("pExec", "'exec'");
        }
    }

    // <pCondicion> -> if <cCondicion> : <Proposicion>
    private static void pCondicion() {
        if (tokenActual == 5) { //el 5 corresponde a exec
            avanzarToken();
            cCondicion();
            if (tokenActual == 75) { //75 es para id
                avanzarToken();
                Proposicion();

            } else {
                lanzarError("pCondicion", ": después de la condición");
            }
        } else {
            lanzarError("pCondicion", "if");
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
                lanzarError("pWhile", ": después de la condición");
            }
        } else {
            lanzarError("pWhile", "while");
        }
    }

    // <pFor> -> for id = <Expresion> <pFlechas> <Expresion> : <Proposicion>
    private static void pFor() {
        //Pendiente pq esta demasido largo y aaaaaaaaa 
        if (tokenActual == 3) { // for
            avanzarToken();
            if (tokenActual == 4) { // id
                avanzarToken();
                if (tokenActual == 20) { // =
                    avanzarToken();
                    Expresion();
                    pFlechas();
                    Expresion();
                    if (tokenActual == 75) { // :
                        avanzarToken();
                        Proposicion();
                    } else {
                        lanzarError("pFor", " : ");
                    }
                } else {
                    lanzarError("pFor", "  = ");
                }
            } else {
                lanzarError("pFor", "identificador");
            }
        } else {
            lanzarError("pFor", "for");
        }
    }

    // <pFlechas> -> -> | <-
    private static void pFlechas() {
        if (tokenActual == 80 || tokenActual == 85) { //Puede ser -> o <-
            avanzarToken();
        } else {
            lanzarError("pFlechas", "'->' o '<-'");
        }

    }

    // <pDato> -> id | num
    private static void pDato() {
        if (tokenActual == 4 || tokenActual == 7) { //Puede ser id o num
            avanzarToken();
        } else {
            lanzarError("pDato", "identificador o número");
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
        if (tokenActual == 35 || tokenActual == 40 || tokenActual == 45
                || tokenActual == 50 || tokenActual == 55 || tokenActual == 60) {
            avanzarToken();
        } else {
            lanzarError("cMultiplo", "operador relacional (==, <>, <, >, <=, >=)");
        }
    }

    // <Expresion> -> <Termino> <eExpresionA>
    private static void Expresion() {
        Termino();
        eExpresionA();

    }

    // <eExpresionA> -> ∅ | <eSignos> <Expresion>
    private static void eExpresionA() {
        if (tokenActual == 90 || tokenActual == 95) { // + o -
            avanzarToken();
            Expresion();
        }

    }

    // <Termino> -> <Factor> <tTerminoA>
    private static void Termino() {
        Factor();
        tTerminoA();
    }
    // <tTerminoA> -> ∅ | <tSignos> <Termino>

    private static void tTerminoA() {
        if (tokenActual == 110 || tokenActual == 115) { // * o /
            avanzarToken();
            Termino();
        }

    }

    // <Factor> -> <fFactor>
    private static void Factor() {
        fFactor();

    }
    // <fFactor> -> <fExp> | id | num

    private static void fFactor() {
        if (tokenActual == 100) { // (
            fExp();
        } else if (tokenActual == 4 || tokenActual == 7) { // id o num
            avanzarToken();
        } else {
            lanzarError("fFactor", "identificador, número o '('");
        }

    }
    // <fExp> -> ( <Expresion> )

    private static void fExp() {
        if (tokenActual == 100) { // (
            avanzarToken();
            Expresion();
            if (tokenActual == 105) { // )
                avanzarToken();
            } else {
                lanzarError("fExp", "')'");
            }
        } else {
            lanzarError("fExp", "'('");
        }
    }

    private static String obtenerLexemaActual() {
        return actual != null ? actual.getElemento() : "fin de entrada";
    }

    public static void lanzarError(String regla, String esperado) {
        String mensaje = "Error en <" + regla + ">, línea " + actual.getLinea()
                + ": se esperaba '" + esperado + "' pero se encontró '" + obtenerLexemaActual() + "'";
        errores.add(mensaje);
        avanzarToken(); // Avanza a los demas bloques
    }

}
