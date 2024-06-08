package ex01;

import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.*;



public class txtClass implements DAOInterface {

    protected List<String> lines;

    public txtClass() {
        this.lines = new ArrayList<String>();
    }

    @Override
    public boolean insertLine(String line) {
        return lines.add(line);
    }

    @Override
    public List<String> getAllLines() {
        return lines;
    }

    @Override
    public void out() {
        List<String> lines = getAllLines();
        try {
            PrintWriter writer = new PrintWriter("src\\ex01\\output.txt", "UTF-8");
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}