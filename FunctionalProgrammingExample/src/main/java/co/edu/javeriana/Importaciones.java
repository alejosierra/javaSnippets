package co.edu.javeriana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Importaciones {
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

    public static void imprimirPedidos(List<Pedido> pedidos,Predicate<Pedido> predicado){
        pedidos.stream().filter(predicado).forEach(System.out::println);
    }

    public static double aranceles(List<Pedido> pedidos,Predicate<Pedido> funcionPredicado){
        return pedidos.stream().mapToDouble(p->(funcionPredicado.test(p))?0:p.getPrecio()*0.25).sum();
    }

    public static boolean filtroTlc(Pedido p){
        String[] paises=new String[]{"China","México","USA"};
        final List<String> listPaises= Arrays.asList(paises);
        return listPaises.contains(p.getOrigen());
    }

    public static boolean filtroPacifico(Pedido p){
        String[] paises=new String[]{"Peru","Chile","México"};
        final List<String> listPaises= Arrays.asList(paises);
        return listPaises.contains(p.getOrigen());
    }

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
