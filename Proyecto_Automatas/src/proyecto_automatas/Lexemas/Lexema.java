/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_automatas.Lexemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Vanessa, Adrian
 */
public class Lexema {

    private String elemento; // Almacena el lexema (palabra, número, operador, etc.)
    private int tokem;       // Almacena el código/token asociado al lexema
    private int linea;      //Para los saltos de linea

    // Constructor que inicializa un lexema con su contenido y su token
    public Lexema(String elemento, int tokem, int linea) {
        this.elemento = elemento;
        this.tokem = tokem;
        this.linea = linea;
    }

    // Método getter para obtener el contenido del lexema
    public String getElemento() {
        return elemento;
    }

    // Método setter para cambiar el contenido del lexema
    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    // Método getter para obtener el código/token del lexema
    public int getTokem() {
        return tokem;
    }

    // Método setter para cambiar el código/token del lexema
    public void setTokem(int tokem) {
        this.tokem = tokem;
    }

    //Para linea
    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    // Método que define cómo se imprime el objeto: primero el contenido, luego el tokem
    @Override
    public String toString() {
        return elemento + "\t" + tokem + "\tLínea: " + linea;
    }

    // El método analizar() realiza el análisis léxico del texto de entrada
    public static ArrayList<Lexema> analizar(String texto) {
        texto = texto.toLowerCase();
        ArrayList<Lexema> lexemas = new ArrayList<>(); //Array para almacenar los lexemas

        HashMap<String, Integer> tablaTokens = new HashMap<>();
        //Palabras reservadas.
        tablaTokens.put("const", 1);
        tablaTokens.put("exec", 2);
        tablaTokens.put("for", 3);
        tablaTokens.put("id", 4);
        tablaTokens.put("if", 5);
        tablaTokens.put("input", 6);
        tablaTokens.put("num", 7);
        tablaTokens.put("print", 8);
        tablaTokens.put("proced", 9);
        tablaTokens.put("var", 10);
        tablaTokens.put("while", 11);

        //Operadores
        tablaTokens.put("=", 20);
        tablaTokens.put(",", 25);
        tablaTokens.put(";", 30);
        tablaTokens.put("==", 35);
        tablaTokens.put("<>", 40);
        tablaTokens.put("<", 45);
        tablaTokens.put(">", 50);
        tablaTokens.put("<=", 55);
        tablaTokens.put(">=", 60);
        tablaTokens.put("{", 65);
        tablaTokens.put("}", 70);
        tablaTokens.put(":", 75);
        tablaTokens.put("->", 80);
        tablaTokens.put("<-", 85);
        tablaTokens.put("+", 90);
        tablaTokens.put("-", 95);
        tablaTokens.put("(", 100);
        tablaTokens.put(")", 105);
        tablaTokens.put("*", 110);
        tablaTokens.put("/", 115);
        tablaTokens.put(".", 120);

        Pattern patron = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*|[1-9]\\d*|==|<=|>=|<>|->|<-|[+\\-*/=;,<>{}()[:].]");

        String[] lineas = texto.split("\\n");
        int numeroLinea = 1;

        for (String lineaTexto : lineas) {
            Matcher matcher = patron.matcher(lineaTexto);

            while (matcher.find()) {
                String token = matcher.group();
                int codigo = tablaTokens.getOrDefault(token, -1);

                if (codigo == -1) { // Verifica si el token no está en la tabla de tokens predefinidos (tablaTokens)
                    // Si el token es un número (contiene solo dígitos)
                    if (token.matches("[1-9]\\d*")) {
                        codigo = 85; // Asigna el código 85 para números (pueden ser enteros o decimales)
                    } // Si el token es un identificador (palabra que comienza con letra o guion bajo y puede tener números o guion bajo)
                    else if (token.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
                        codigo = 125; // Asigna el código 10 para identificadores (por ejemplo, nombres de variables)
                    }
                }

                lexemas.add(new Lexema(token, codigo, numeroLinea));
            }
            numeroLinea++;

        }

        return lexemas;
    }
}
