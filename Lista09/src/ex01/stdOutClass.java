package ex01;

import java.util.ArrayList;
import java.util.List;
public class stdOutClass implements DAOInterface {

    protected List<String> lines;

    public stdOutClass() {
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
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println();
    }
}
