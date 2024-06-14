package ex01.A;
public class Fatorial {
    static int CalculaFatorial(int n){
        if(n < 0){
            return -1;
        }
        if (n == 0 || n == 1){
            return 1;
        }
        for (int i = n-1; i > 0; i--){
            n = n * i;
        }
        return n;
    }

    public static void main (String args[]){
        int n = 5;
        System.out.println(CalculaFatorial(n));
    }
}