package ex01.B;

public class FatorialDecrescenteRefatorado {
    public static int fatorial(int n) {
        if(n < 0){
            return -1;
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        int resultado = n * fatorial(n - 1);
        return resultado;
    }

    public static void main(String[] args) {
        int numero = 5;
        System.out.println("Fatorial de " + numero + " é " + fatorial(numero));
    }
}
