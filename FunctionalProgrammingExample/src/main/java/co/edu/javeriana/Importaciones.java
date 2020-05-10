package co.edu.javeriana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/***
 * Clase que muestra un ejemplo de uso de funciones de orden superior (funciones que reciben funciones como paraámetros o retornan funciones)
 */
public class Importaciones {

    /***
     * Programa de ejemplo
     * @param args argumentos de main
     */
    public static void main(String[] args) {
        List<Pedido> pedidos= new ArrayList<>();
        pedidos.add(new Pedido("Patitos de hule", 1500, "China"));
        pedidos.add(new Pedido("Telefonos Garfield", 3200, "Vietnam"));
        pedidos.add(new Pedido("Zapatos", 2000, "Honduras"));
        pedidos.add(new Pedido("Pisco", 3000, "Peru"));
        System.out.println("Pedidos TLC");
        imprimirPedidos(pedidos,Importaciones::filtroTlc);
        System.out.println("Pedidos Alianza Pacífico");
        imprimirPedidos(pedidos,Importaciones::filtroPacifico);
        System.out.println("Aranceles TLC:"+aranceles(pedidos,Importaciones::filtroTlc));
        System.out.println("Aranceles Alianza Pacífico:"+aranceles(pedidos,Importaciones::filtroPacifico));

    }

    /***
     * Función que recibe una lista de pedidos y un predicado (función), y solo imprime los
     * pedidos que cumplan con el predicado
     * @param pedidos lista de pedidos
     * @param funcionPredicado función Pedido->boolean que determina si un pedido cumple con un filtro
     */
    public static void imprimirPedidos(List<Pedido> pedidos,Predicate<Pedido> funcionPredicado){
        pedidos.stream().filter(funcionPredicado).forEach(System.out::println);
    }

    /***
     * Función que calcula los aranceles de una lista de pedidos basándose en un filtro
     * @param pedidos lista de pedidos
     * @param funcionPredicado función Pedido->boolean que determina si un pedido cumple con un filtro
     * @return suma de aranceles. Para los pedidos que no cumplen con el predicado es 25% del precio,
     * y para los demás es 0
     */
    public static double aranceles(List<Pedido> pedidos,Predicate<Pedido> funcionPredicado){
        //Map to double invoca la función predicado y dependiendo del resultado
        //calcula el arancel
        return pedidos.stream()
                .mapToDouble(
                        p->(funcionPredicado.test(p))?0:p.getPrecio()*0.25
                ).sum();
    }

    /***
     * Función que define si un pedido hace parte de tratados TLC (ficticios)
     * @param p pedido
     * @return verdadero si el origen del pedido hace parte algun TLC
     */
    public static boolean filtroTlc(Pedido p){
        String[] paises=new String[]{"China","México","USA"};
        final List<String> listPaises= Arrays.asList(paises);
        return listPaises.contains(p.getOrigen());
    }

    /***
     * Función que define si un pedido hace parte de la alianza del pacífico
     * @param p pedido
     * @return verdadero si el origen del pedido hace parte de la alianza
     */
    public static boolean filtroPacifico(Pedido p){
        String[] paises=new String[]{"Peru","Chile","México"};
        final List<String> listPaises= Arrays.asList(paises);
        return listPaises.contains(p.getOrigen());
    }

    /***
     * Clase pedido
     */
    static class Pedido{
        private String producto;
        private double precio;
        private String origen;

        public Pedido(String producto, double precio, String origen) {
            this.producto = producto;
            this.precio = precio;
            this.origen = origen;
        }

        public String getProducto() {
            return producto;
        }

        public void setProducto(String producto) {
            this.producto = producto;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public String getOrigen() {
            return origen;
        }

        public void setOrigen(String origen) {
            this.origen = origen;
        }

        @Override
        public String toString() {
            return "Pedido{" +
                    "producto='" + producto + '\'' +
                    ", precio=" + precio +
                    ", origen='" + origen + '\'' +
                    '}';
        }
    }
}
