package ex02;
public class tabuleiro {
    String[][] jogo = new String[3][3];
    private boolean[] pos = new boolean[9];
    private static tabuleiro instancia;

    private tabuleiro() {
        for (int j = 0; j <3; j++) 
            for(int i = 0; i < 3; i++) 
                this.jogo[j][i] = " ";
    }

    public boolean velha(String c) {
        //Horizontal
        for (int i = 0; i < 3; i++)
            if(this.jogo[i][0] == c && this.jogo[i][1] == c && this.jogo[i][2] == c) {
                return true;
            }

        //Vertical
        for (int i = 0; i < 3; i++)
            if(this.jogo[0][i] == c && this.jogo[1][i] == c && this.jogo[2][i] == c) {
                return true;
            }

        //Diagonal
            if(this.jogo[1][1] == c) 
                if ((this.jogo[0][0] == c && this.jogo[2][2] == c) || (this.jogo[0][2] == c && this.jogo[2][0] == c)) {
                    return true;
                }

        return false;

    }

    public boolean cheio() {
        for (int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(!(this.jogo[i][j] == "X" || this.jogo[i][j] == "O")) return false;
      
        return true;
    }

    public void checaMesa() {
        for (int j = 0; j <3; j++) {
            for(int i = 0; i < 3; i++) {
                System.out.print(" " + jogo[j][i] + " ");
                    
                if(i!=2) System.out.print("|");
                }
            System.out.println();
            if(j != 2) System.out.println("___ ___ ___");
        }
        System.out.println("_____________");
    }

    public void jogada(int row, int col, String c) throws invalidPosition {
        if(pos[3*row + col]) throw new invalidPosition();
        else {
            pos[3*row + col] = true;
            this.jogo[row][col] = c;
    }
    }

    static {
        if (instancia == null)
            instancia = new tabuleiro();
    }

    public static tabuleiro pegaTabuleiro() {
        return instancia;
    }
}
