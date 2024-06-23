package ex01;

import java.io.*;

/*
 * O método out faz um append do texto no arquivo output.txt
 * Limpe o arquivo antes de rodar o teste
 * 
 * Resultados obtidos
 *Benchmarking
 *	Txt Method took 50 ms
 *	Std Out Method took 140 ms
 * 
 * A escrita para a saída padrão é mais lenta que a escrita para um arquivo,
 * isso ocorre pois a saída padrão conta com uma saída síncrona, ou seja, ela espera a escrita
 * de uma linha acabar para começar a escrever a próxima.
 * Já na escrita para um arquivo, o sistema operacional é quem controla a escrita, nele
 * temos o que chamamos de I/O Buffering, ou seja, o programa dá output de tudo que ele quer
 * escrever no arquivo, e é o sistrema operacional guarda essas informações em um buffer, 
 * então todos os métodos de escrita do programa dão return antes do arquivo receber qualquer
 * tipo de escrita, e não precisam esperar a escrita anterior
 * acabar, tornando o programa mais rápido.
 */

public class Test {

    public static void main(String[] args) {
        long t, t1=0, t2 =0;
        txtClass txt = new txtClass();
        stdOutClass stdOut = new stdOutClass();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\ex01\\biblia-em-txt.txt"));
            String line = null;
            do {
                line = br.readLine();
                if(line != null){
                    txt.insertLine(line);
                    stdOut.insertLine(line);
                }
            } while (line != null);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        t= System.currentTimeMillis();
        txt.out();
        t1 += System.currentTimeMillis()-t;

        t= System.currentTimeMillis();
        stdOut.out();
        t2+= System.currentTimeMillis()-t;

        System.out.println("Benchmarking\n\tTxt Method took "+t1+" ms\n\tStd Out Method took "+t2+" ms");
    }
}
