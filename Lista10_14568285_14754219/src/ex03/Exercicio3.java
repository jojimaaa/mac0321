package ex03;

/*
 * O segundo código é mais legível e mais intuitivo, não necessitando de comentários.
 * Além disso, o uso de uma constante para a constante gravitacional é uma boa prática de programação,
 * mantendo o código mais organizado, manutenível e facilitando a expansão do programa, uma vez que não será necessário
 * reescrever o mesmo número várias vezes, e também facilita a alteração do valor da constante, caso necessário.
 */

public class Exercicio3 {
    double energiaPotencial1(double massa, double altura) {
        // g = 9.81: a constante gravitacional
        return massa * 9.81 * altura;
    }

    static final double CONSTANTE_GRAVITACIONAL = 9.81;

    double energiaPotencial2(double massa, double altura) {
        return massa * CONSTANTE_GRAVITACIONAL * altura;
    }
}