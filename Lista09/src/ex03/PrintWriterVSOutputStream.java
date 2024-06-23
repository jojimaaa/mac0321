package ex03;

import java.io.PrintWriter;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
 * A diferença entre PrintWriter e OutputStream:
 * 
 * PrintWriter trabalha com characteres, ou seja, ele trabalha com dados em formato de caracteres,
 * ele vai converter os dados que recebe para o set de caracteres padrão do sistema, no nosso caso o UTF-8.
 * 
 * OutputStream trabalha com bytes, ou seja, ele trabalha com dados em formato de bytes, ele não faz nenhuma conversão,
 * por isso que ao abrir o arquivo gerado pelo OutputStream por um interpretador de texto (.txt), aparecem 
 * diversos simbolos estranhos, pois a saída não está no formato de texto (caracteres), e sim de bytes.
 */

public class PrintWriterVSOutputStream {
    public static void main(String[] args) {

        byte[] data = new byte[10000];
        new Random().nextBytes(data);


        // Using PrintWriter
        try {
            PrintWriter writer = new PrintWriter("src\\ex03\\outputPrintWriter.txt");
                writer.println(data);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Using OutputStream
        try (OutputStream output = new FileOutputStream("src\\ex03\\outputOutputStream.txt")) {
                output.write(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
