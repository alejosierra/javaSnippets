import java.util.Scanner;

/***
 * Ejemplo de manejo de arreglos con datos primitivos y entrada y salida por consola en Java
 * El código solo ilustra un algoritmo que hace uso de las herramientas básicas del lenguaje y no se enfoca en ser óptimo
 * Se puede mejorar con herramientas extra como las classes Arrays, LinkedList, entre otras.
 * Se puede también optiizar el cálculo de promedio haciendo cálculos 
 */
public class StatsCovid{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int cont=0;
        int casos[]=new int[10];
        int recuperados[]=new int[10];
        int fallecidos[]=new int[10];
        int casosAcumulados[]=new int[10];
        int recuperadosAcumulados[]=new int[10];
        int fallecidosAcumulados[]=new int[10];
        double promedios3dias[]=new double[100];
        double promedios7dias[]=new double[100];
        do{
            System.out.println("Ingrese la contidas de nuevos casos(-1 para salir):");
            int casosDia=sc.nextInt();
            if (casosDia==-1)
                break;
            if (cont==casos.length){
                //Incrementar el tamaño de los arreglos
                //Crear nuevos arreglos
                int casosTemp[]=new int[cont*2];
                int recuperadosTemp[]=new int[cont*2];
                int fallecidosTemp[]=new int[cont*2];
                int casosAcumTemp[]=new int[cont*2];
                int recuperadosAcumTemp[]=new int[cont*2];
                int fallecidosAcumTemp[]=new int[cont*2];
                //Clonar valores de los arreglos
                for (int i=0;i<cont;i++){
                    casosTemp[i]=casos[i];
                    recuperadosTemp[i]=recuperados[i];
                    fallecidosTemp[i]=fallecidos[i];
                    casosAcumTemp[i]=casosAcumulados[i];
                    recuperadosAcumTemp[i]=recuperadosAcumulados[i];
                    fallecidosAcumTemp[i]=fallecidosAcumulados[i];
                }
                //Remplazar referencias
                casos=casosTemp;
                recuperados=recuperadosTemp;
                fallecidos=fallecidosTemp;
                casosAcumulados=casosAcumTemp;
                recuperadosAcumulados=recuperadosAcumTemp;
                fallecidosAcumulados=fallecidosAcumTemp;
            }
            casos[cont]=casosDia;
            System.out.println("Ingrese la contidas de nuevos recuperados:");
            recuperados[cont]=sc.nextInt();
            System.out.println("Ingrese la contidas de nuevos fallecidos:");
            fallecidos[cont]=sc.nextInt();
            casosAcumulados[cont]=cont==0?casosDia:casosAcumulados[cont-1]+casosDia;
            recuperadosAcumulados[cont]=cont==0?recuperados[cont]:recuperadosAcumulados[cont-1]+recuperados[cont];
            fallecidosAcumulados[cont]=cont==0?fallecidos[cont]:fallecidosAcumulados[cont-1]+fallecidos[cont];
            System.out.println("Casos acumulados:"+casosAcumulados[cont]);
            System.out.println("Recuperados acumulados:"+recuperadosAcumulados[cont]);
            System.out.println("Fallecidos acumulados:"+fallecidosAcumulados[cont]);
            System.out.println("Casos activos:"+(casosAcumulados[cont]-recuperadosAcumulados[cont]-fallecidosAcumulados[cont]));
            //Promedios
            if (cont>=2){
                double sum=0;
                for (int i=cont;i>=cont-2;i--)
                    sum+=casos[i];
                promedios3dias[cont]=sum/3;
                System.out.println("Promedio casos últ. 3 días:"+promedios3dias[cont]);
            }else
                promedios3dias[cont]=0;
            if (cont>=6){
                double sum=0;
                for (int i=cont;i>=cont-6;i--)
                    sum+=casos[i];
                promedios7dias[cont]=sum/7;
                System.out.println("Promedio casos últ. 7 días:"+promedios7dias[cont]);
            }else
                promedios7dias[cont]=0;
            cont++;
        }while(true);
        for (int i=0;i<cont;i++){
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\t%d\t%.3f\t%.3f%n", 
                casos[i],
                recuperados[i],
                fallecidos[i],
                casosAcumulados[i],
                recuperadosAcumulados[i],
                fallecidosAcumulados[i],
                casosAcumulados[i]-recuperadosAcumulados[i]-fallecidosAcumulados[i],
                promedios3dias[i],
                promedios7dias[i]
            );
        }
    }
}