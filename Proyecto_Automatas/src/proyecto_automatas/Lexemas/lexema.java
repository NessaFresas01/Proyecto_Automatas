package proyecto_automatas.Lexemas;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Vanessa, Adrián
 */


public class Lexema {
    private String elemento; // Almacena el lexema (palabra, número, operador, etc.)
    private int tokem;       // Almacena el código/token asociado al lexema

    // Constructor que inicializa un lexema con su contenido y su token
    public Lexema(String elemento, int tokem) {
        this.elemento = elemento;
        this.tokem = tokem;
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

    // Método que define cómo se imprime el objeto: primero el token, luego el contenido
    @Override
    public String toString() {
        return tokem + "\t" + elemento; // Separa con tabulador
    }

    // El método analizar() realiza el análisis léxico del texto de entrada
    public static ArrayList<Lexema> analizar(String texto) {
        // TODO: sin comentarios aquí según tu petición
        texto = texto.toLowerCase();
        ArrayList<Lexema> lexemas = new ArrayList<>();

        HashMap<String, Integer> tablaTokens = new HashMap<>();
        tablaTokens.put("const", 5);
        tablaTokens.put("id", 10);
        tablaTokens.put("=", 15);
        tablaTokens.put(",", 20);
        tablaTokens.put(";", 25);
        tablaTokens.put("var", 30);
        tablaTokens.put("proced", 35);
        tablaTokens.put("==", 40);
        tablaTokens.put("<>", 45);
        tablaTokens.put("<", 50);
        tablaTokens.put(">", 55);
        tablaTokens.put("<=", 60);
        tablaTokens.put("=>", 65);
        tablaTokens.put("{", 70);
        tablaTokens.put("}", 75);
        tablaTokens.put("print", 80);
        tablaTokens.put("num", 85);
        tablaTokens.put("input", 90);
        tablaTokens.put("exec", 95);
        tablaTokens.put("if", 100);
        tablaTokens.put(":", 105);
        tablaTokens.put("while", 110);
        tablaTokens.put("for", 115);
        tablaTokens.put("->", 120);
        tablaTokens.put("<-", 125);
        tablaTokens.put("+", 130);
        tablaTokens.put("-", 135);
        tablaTokens.put("(", 140);
        tablaTokens.put(")", 145);
        tablaTokens.put("*", 150);
        tablaTokens.put("/", 155);

        Pattern patron = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*|\\d+|==|<=|=>|<>|->|<-|[+\\-*/=;,<>{}()[:]]");
        Matcher matcher = patron.matcher(texto);

        while (matcher.find()) {
            String token = matcher.group();
            int codigo = tablaTokens.getOrDefault(token, -1);

    if (codigo == -1) { // Verifica si el token no está en la tabla de tokens predefinidos (tablaTokens)
    // Si el token es un número (contiene solo dígitos)
    if (token.matches("\\d+")) {
        codigo = 500; // Asigna el código 500 para números (pueden ser enteros o decimales)
    } 
    // Si el token es un identificador (palabra que comienza con letra o guion bajo y puede tener números o guion bajo)
    else if (token.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
        codigo = 100; // Asigna el código 100 para identificadores (por ejemplo, nombres de variables)
      }
    }


            lexemas.add(new Lexema(token, codigo));
        }

        return lexemas;
    }
}
