package ex01.B;


/*
Prompt do ChatGPT:
Crie uma função em java computando a função fatorial de forma decrescente e recursiva, com abundância de comentários e imprimindo valores a cada iteração
*/ 
public class FatorialDecrescenteGPT {

    // Função principal que inicia o programa
    public static void main(String[] args) {
        int numero = 5; // Número para o qual queremos calcular o fatorial
        System.out.println("Fatorial de " + numero + " é " + fatorial(numero));
    }

    // Função recursiva que calcula o fatorial de um número
    public static int fatorial(int n) {
        // Imprime o valor atual de n
        System.out.println("Chamando fatorial(" + n + ")");

        // Caso base: fatorial de 0 ou 1 é 1
        if (n == 0 || n == 1) {
            System.out.println("Base case reached with n = " + n);
            return 1;
        }

        // Chamada recursiva decrescente
        int resultado = n * fatorial(n - 1);

        // Imprime o valor atual do resultado após a chamada recursiva
        System.out.println("Retornando " + resultado + " para fatorial(" + n + ")");

        // Retorna o resultado do cálculo fatorial
        return resultado;
    }
}
