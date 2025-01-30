package ex02;
import java.util.Scanner;


class NanInput extends Exception {

}
class invalidNumber extends Exception{

}
class invalidPosition extends Exception{

}

public class jogoDaVelha {
    private boolean x_v = false;
    private boolean o_v = false;
    private tabuleiro Tabuleiro = tabuleiro.pegaTabuleiro();

    public jogoDaVelha() {
    }

public void executar() throws NanInput, invalidPosition, invalidNumber {
    Scanner input = new Scanner(System.in);
    int p;
    int cont = 0;
    while(!Tabuleiro.cheio()) {
        Tabuleiro.checaMesa();
        cont++;
        System.out.print(cont + "º turno.\nVez de X: ");
        try {
            p = input.nextInt();
        } catch (java.util.InputMismatchException e) {
            throw new NanInput();
        }
        p -=1;

        if (p < 0 || p > 8) throw new invalidNumber();
        
        int col = p % 3;
        int row = p / 3;

        
        Tabuleiro.jogada(row, col, "X");
        
        if(Tabuleiro.velha("X")) {
            x_v = true;
            break;
        }
    

        if(Tabuleiro.cheio()) 
            break;
        
        Tabuleiro.checaMesa();
        cont++;
        System.out.print(cont + "º turno.\nVez de O: ");
        try {
        p = input.nextInt();
        } catch (java.util.InputMismatchException e) {
            throw new NanInput();
        }
        p -=1;
        if (p < 0 || p > 8) throw new invalidNumber();
        
        col = p % 3;
        row = p / 3;
        
        Tabuleiro.jogada(row, col, "O");
        
        if(Tabuleiro.velha("O")) { 
            o_v = true;
            break;
        }
    }
    input.close(); 
    Tabuleiro.checaMesa();
    if(x_v) System.out.println("VITÓRIA DE X!");
    else if (o_v) System.out.println("VITÓRIA DE O!");
    else System.out.println("VELHA!");
}

public static void main(String[] args) {
    jogoDaVelha joguinho = new jogoDaVelha();
    try {
        joguinho.executar();
    }
    catch (invalidNumber e1) {
        System.err.println("*Só tem 9 quadrados, amigo");
    }
    catch (invalidPosition e2) {
        System.err.println("*Você se acha muito espertinho, né");
        System.exit(1);
    }
    catch (NanInput e3) {
        System.err.println("*Só pode número, irmão");
    }
    //Como só há uma instância de try-catch, seria redundante incluir o método exit(), já que o programa 
    //será finalizado logo após a correção do erro
}
}

