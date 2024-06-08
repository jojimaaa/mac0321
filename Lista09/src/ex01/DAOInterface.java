package ex01;
import java.util.List;

public interface DAOInterface {
    public boolean insertLine(String line);
    public List<String> getAllLines();
    public void out();
}
