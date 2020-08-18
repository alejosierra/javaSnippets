import java.util.*;

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
    private HashMap<Estado,HashMap<Character,Estado>> delta;
    /**
     * Estado inicial
     */
    private Estado inicial;
    /**
     * Estados finales
     */
    private List<Estado> finales;
    //EL alfabeto es el conjunto de Character

    /***
     * Construye un autómata de estados finitos determinista
     * El HashMap delta corresponde a la función de transición, por cada estado determina que transiciones "salen" de ese estado
     * El estado inicial
     * Una lista con los estados finales
     */
    public AFD(HashMap<Estado,HashMap<Character,Estado>> delta,Estado inicial,List<Estado> finales){
        this.delta=delta;
        this.inicial=inicial;
        this.finales=finales;
    }

    /**
     * Función de transición básica, dado un estado y un caracter retorna el nuevo estado
     * -No hay manejo de transiciones que no están explícitas en la función de transición
     */
    private Estado funcionDelta(Estado q,Character c){
        //Falta manejo de excepciones
        return delta.get(q).get(c);
    }

    /***
     * Función de transición extendida. Dado un estado (q) y una cadena de caracteres (cadena), retorna
     * el estado final.
     */
    private Estado funcionDeltaExtendida(Estado q,String cadena){
        // falta manejo de excepciones
        if (cadena.isEmpty())
            return q;
        else{
            //TODO Se puede optimizar el particionamiento de la cadena
            String x=cadena.substring(0, cadena.length() - 1);
            Character a=cadena.charAt(cadena.length() - 1);
            return funcionDelta(funcionDeltaExtendida(q,x), a);
        }
    }

    /***
     * Función que valida si dada un cadena de caracteres (w) el autómata termina en un estado final
     * iniciando el el estado inicial.
     */
    public Boolean validarEntrada(String w){
        return finales.contains(funcionDeltaExtendida(inicial,w));
    }

    /***
     * Clase que representa un estado genérico de un autómata. Los estado se identifican por un nombre.
     */
    public static class Estado{
        private String nombre;

        /***
         * Construye un estado a partir del nombre
         */
        public Estado(String nombre){
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
    private static void agregarRango(Character ini,Character fin, Estado destino, HashMap<Character,Estado> transiciones){
        for (Character c=ini;c<=fin;c++){
            System.out.println(c);
            transiciones.put(c, destino);
        }
    }

    private static void pruebaUnosImpares(){
        System.out.println("Prueba de AFD que determina si una cadena de caracteres tiene una cantidad impar de '1's");
        Estado qEven=new Estado("qEven");
        Estado qOdd=new Estado("qOdd");
        List<Estado> finales=new LinkedList<Estado>();
        finales.add(qOdd);
        HashMap<Estado,HashMap<Character,Estado>> delta=new HashMap<Estado,HashMap<Character,Estado>>(2);
        HashMap<Character,Estado> dEven=new HashMap<Character,Estado>(2);
        dEven.put('0',qEven);
        dEven.put('1',qOdd);
        delta.put(qEven,dEven);
        HashMap<Character,Estado> dOdd=new HashMap<Character,Estado>(2);
        dOdd.put('0',qOdd);
        dOdd.put('1',qEven);
        delta.put(qOdd, dOdd);
        AFD unosImpares=new AFD(delta, qEven, finales);
        String[] cadenas={
            "011000111001111",
            "01",
            "1",
            "11011",
            "11111",
            "111000011111"
        };
        for (String c:cadenas){
            System.out.println(c+" - "+unosImpares.validarEntrada(c));
        }
    }

    /***
     * Prueba de AFD par avariables que inicial con letra o guión bajo y 
     * continuan con letra gión bajo o número
     */
    private static void pruebaVariables(){
        System.out.println("Prueba de AFD que determina si una cadena de caracteres puede ser el nombre de una variable");
        Estado q0=new Estado("q0");
        Estado q1=new Estado("q1");
        List<Estado> finales=new LinkedList<Estado>();
        finales.add(q1);
        HashMap<Estado,HashMap<Character,Estado>> delta=new HashMap<Estado,HashMap<Character,Estado>>(2);
        HashMap<Character,Estado> d0=new HashMap<Character,Estado>(60);
        d0.put('_',q1);
        agregarRango('a', 'z', q1, d0);
        agregarRango('A', 'Z', q1, d0);
        delta.put(q0,d0);
        HashMap<Character,Estado> d1=new HashMap<Character,Estado>(60);
        d1.put('_',q1);
        agregarRango('a', 'z', q1, d1);
        agregarRango('A', 'Z', q1, d1);
        agregarRango('0', '9', q1, d1);
        delta.put(q1, d1);
        AFD unosImpares=new AFD(delta, q0, finales);
        String[] cadenas={
            "myVar",
            "_",
            "a",
            "8",
            "var9",
            "_myvar8"
        };
        for (String c:cadenas){
            System.out.println(c+" - "+unosImpares.validarEntrada(c));
        }
    }

    public static void main(String[] args) {
        pruebaVariables();
    }
}