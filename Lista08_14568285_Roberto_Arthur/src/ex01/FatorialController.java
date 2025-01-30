package ex01;

public class FatorialController {
    public int calcularFatorial(int numero) throws IllegalArgumentException {
        if (numero < 0) {
            throw new IllegalArgumentException("Número não pode ser negativo.");
        }
        if(numero == 0) return 1;
        int resultado = 1;
        for (int i = 1; i <= numero; i++) {
            resultado *= i;
        }
        if(resultado <= 0 && numero != 0) throw new IllegalArgumentException("Excedeu o limite de Integer (32bits)");
        return resultado;
    }
}
