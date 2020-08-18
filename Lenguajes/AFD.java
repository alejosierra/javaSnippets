import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa la implementación base (incompleta) de un Automata de Estados Finitos Determinista
 * La implementación no contiene:
 * -Manejo de excepciones
 * -Transiciones que no están explícitas
 */
public class AFD{
    
    /**
     * Función de transición.
     */
    private final HashMap<Estado,HashMap<Character,Estado>> delta;
    /**
     * Estado inicial
     */
    private final Estado inicial;
    /**
     * Estados finales
     */
    private final Set<Estado> finales;
    //EL alfabeto es el conjunto de Character

    /***
     * Construye un autómata de estados finitos determinista
     * El HashMap delta corresponde a la función de transición, por cada estado determina que transiciones "salen" de ese estado
     * El estado inicial
     * Una lista con los estados finales
     */
    public AFD(final HashMap<Estado,HashMap<Character,Estado>> delta,final Estado inicial,final Set<Estado> finales){
        this.delta=delta;
        this.inicial=inicial;
        this.finales=finales;
    }

    /**
     * Función de transición básica, dado un estado y un caracter retorna el nuevo estado
     * -No hay manejo de transiciones que no están explícitas en la función de transición
     */
    private Estado funcionDelta(final Estado q,final Character c) throws InvalidStateException{
        try{
            return delta.get(q).get(c);
        }catch(NullPointerException npe){
            throw new InvalidStateException();
        }
        
    }

    /***
     * Función de transición extendida. Dado un estado (q) y una cadena de caracteres (cadena), retorna
     * el estado final.
     */
    private Estado funcionDeltaExtendida(final Estado q,final String cadena) throws InvalidStateException{
        // falta manejo de excepciones
        if (cadena.isEmpty())
            return q;
        else{
            //TODO Se puede optimizar el particionamiento de la cadena
            final String x=cadena.substring(0, cadena.length() - 1);
            final Character a=cadena.charAt(cadena.length() - 1);
            return funcionDelta(funcionDeltaExtendida(q,x), a);
        }
    }

    /***
     * Función que valida si dada un cadena de caracteres (w) el autómata termina en un estado final
     * iniciando el el estado inicial.
     */
    public Boolean validarEntrada(String w){
        try{
            return finales.contains(funcionDeltaExtendida(inicial,w));
        }catch(InvalidStateException ise){
            return false;
        }
        
    }

    /***
     * Clase que representa un estado genérico de un autómata. Los estado se identifican por un nombre.
     */
    public static class Estado{
        private final String nombre;

        /***
         * Construye un estado a partir del nombre
         */
        public Estado(final String nombre){
            this.nombre=nombre;
        }

        public int hashCode(){
            return nombre.hashCode();
        }

        public String toString(){
            return nombre;
        }
    }

    /***
     * Agrega un rango de caracteres a un hashmap
     * @param ini caracter inicial (incluido)
     * @param fin caracter final (includio)
     * @param destino estado destino
     * @param transiciones mapa de transiciones. EN este mapa quedar{an las nuevas transiciones
     */
    private static void agregarRango(final Character ini,final Character fin, final Estado destino, final HashMap<Character,Estado> transiciones){
        for (Character c=ini;c<=fin;c++){
            transiciones.put(c, destino);
        }
    }

    /***
     * Prueba de AFD con autómata que valida cadenas de 1s y 0s con una cantidad impar de 1s
     */
    private static void pruebaUnosImpares(){
        System.out.println("Prueba de AFD que determina si una cadena de caracteres tiene una cantidad impar de '1's");
        final Estado qEven=new Estado("qEven");
        final Estado qOdd=new Estado("qOdd");
        final Set<Estado> finales=new HashSet<Estado>(2);
        finales.add(qOdd);
        final HashMap<Estado,HashMap<Character,Estado>> delta=new HashMap<Estado,HashMap<Character,Estado>>(2);
        final HashMap<Character,Estado> dEven=new HashMap<Character,Estado>(2);
        dEven.put('0',qEven);
        dEven.put('1',qOdd);
        delta.put(qEven,dEven);
        final HashMap<Character,Estado> dOdd=new HashMap<Character,Estado>(2);
        dOdd.put('0',qOdd);
        dOdd.put('1',qEven);
        delta.put(qOdd, dOdd);
        final AFD unosImpares=new AFD(delta, qEven, finales);
        final String[] cadenas={
            "011000111001111",
            "01",
            "1",
            "11011",
            "11111",
            "111000011111"
        };
        for (final String c:cadenas){
            System.out.println(c+" - "+unosImpares.validarEntrada(c));
        }
    }

    /***
     * Prueba de AFD par avariables que inicial con letra o guión bajo y 
     * continuan con letra gión bajo o número
     */
    private static void pruebaVariables(){
        System.out.println("Prueba de AFD que determina si una cadena de caracteres puede ser el nombre de una variable");
        final Estado q0=new Estado("q0");
        final Estado q1=new Estado("q1");
        final Set<Estado> finales=new HashSet<Estado>(2);
        finales.add(q1);
        final HashMap<Estado,HashMap<Character,Estado>> delta=new HashMap<Estado,HashMap<Character,Estado>>(2);
        final HashMap<Character,Estado> d0=new HashMap<Character,Estado>(60);
        d0.put('_',q1);
        agregarRango('a', 'z', q1, d0);
        agregarRango('A', 'Z', q1, d0);
        delta.put(q0,d0);
        final HashMap<Character,Estado> d1=new HashMap<Character,Estado>(60);
        d1.put('_',q1);
        agregarRango('a', 'z', q1, d1);
        agregarRango('A', 'Z', q1, d1);
        agregarRango('0', '9', q1, d1);
        delta.put(q1, d1);
        final AFD variables=new AFD(delta, q0, finales);
        final String[] cadenas={
            "myVar",
            "_",
            "a",
            "8",
            "var9",
            "_myvar8",
            "",
            "myVar?",
            " ",
            "!",
            "\"\""
        };
        for (final String c:cadenas){
            System.out.println(c+" - "+variables.validarEntrada(c));
        }
    }

    /***
     * Excepción para representar saltos rechazados por la función de transción
     */
    class InvalidStateException extends Exception{

        /**
         * Por defecto
         */
        private static final long serialVersionUID = 1L;
        
    }

    public static void main(final String[] args) {
        pruebaVariables();
    }
}