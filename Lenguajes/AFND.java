import java.util.*;

/**
 * Clase que representa la implementación base (incompleta) de un Automata de Estados Finitos Determinista
 * La implementación no contiene:
 * -Manejo de excepciones
 * -Transiciones que no están explícitas
 */
public class AFND{
    
    /**
     * Función de transición.
     */
    private final HashMap<Estado,HashMap<Character,Set<Estado>>> delta;
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
    public AFND(final HashMap<Estado,HashMap<Character,Set<Estado>>> delta,final Estado inicial,final Set<Estado> finales){
        this.delta=delta;
        this.inicial=inicial;
        this.finales=finales;
    }

    /**
     * Función de transición básica, dado un estado y un caracter retorna el nuevo estado
     * -No hay manejo de transiciones que no están explícitas en la función de transición
     */
    private Set<Estado> funcionDelta(final Estado q,final Character c){
        //Falta manejo de excepciones
        return delta.get(q).getOrDefault(c, new HashSet<Estado>());
    }

    /***
     * Función de transición extendida. Dado un estado (q) y una cadena de caracteres (cadena), retorna
     * el estado final.
     */
    private Set<Estado> funcionDeltaExtendida(final Estado q,final String cadena){
        // falta manejo de excepciones
        if (cadena.isEmpty()){
            Set<Estado> res=new HashSet<Estado>(1);
            res.add(q);
            return res;
        }
        else{
            //TODO Se puede optimizar el particionamiento de la cadena
            final String x=cadena.substring(0, cadena.length() - 1);
            final Character a=cadena.charAt(cadena.length() - 1);
            Set<Estado> res=new HashSet<Estado>();
            for (Estado r:funcionDeltaExtendida(q,x)){
                res.addAll(funcionDelta(r, a));
            }
            return res;
        }
    }

    /***
     * Función que valida si dada un cadena de caracteres (w) el autómata termina en un estado final
     * iniciando el el estado inicial.
     */
    public Boolean validarEntrada(final String w){
        Set<Estado> res=funcionDeltaExtendida(inicial,w);
        for (Estado e:res)
            if (finales.contains(e))
                return true;
        return false;
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

    public static void main(final String[] args) {
        System.out.println("Prueba de AFND que determina si una cadena de caracteres un '1' en el penúltimo caracter");
        final Estado q1=new Estado("1");
        final Estado q2=new Estado("2");
        final Estado q3=new Estado("3");
        final Set<Estado> finales=new HashSet<Estado>();
        finales.add(q3);
        final HashMap<Estado,HashMap<Character,Set<Estado>>> delta=new HashMap<Estado,HashMap<Character,Set<Estado>>>(2);
        final HashMap<Character,Set<Estado>> d1=new HashMap<Character,Set<Estado>>(2);
        d1.put('0',new HashSet<Estado>(Arrays.asList(q1)) );
        d1.put('1',new HashSet<Estado>(Arrays.asList(q1,q2)));
        delta.put(q1,d1);
        final HashMap<Character,Set<Estado>> d2=new HashMap<Character,Set<Estado>>(2);
        d2.put('0',new HashSet<Estado>(Arrays.asList(q3)));
        d2.put('1',new HashSet<Estado>(Arrays.asList(q3)));
        delta.put(q2, d2);
        final HashMap<Character,Set<Estado>> d3=new HashMap<Character,Set<Estado>>(2);
        delta.put(q3, d3);
        final AFND unosImpares=new AFND(delta, q1, finales);
        final String[] cadenas={
            "011000111001111",
            "01",
            "10",
            "11011",
            "11111",
            "111000011101"
        };
        for (final String c:cadenas){
            System.out.println(c+" - "+unosImpares.validarEntrada(c));
        }
    }
}