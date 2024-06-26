package usp.mac321.ep2.ex02;

import java.util.List;

public interface GetterDAO<T> {
    public T get(T object);
    public List<T> getAll();
}
