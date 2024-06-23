package usp.mac321.ep2.ex02;

public interface ModifyDAO<T> {
    public boolean add(T object);
    public boolean remove(T object);
}
