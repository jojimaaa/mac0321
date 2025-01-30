package ex03;

import java.util.ArrayList;

public class MulticonjuntoArrayList <T>{
	
	protected ArrayList<T> elementos;
	
	public MulticonjuntoArrayList() {
		super();
		this.elementos = new ArrayList<T>();
	}
	
	public void add(T elemento) {
		this.elementos.add(elemento);
	}
	
	public void addAll(MulticonjuntoArrayList<T> m) {
		for(T i : m.elementos) {
			this.elementos.add(i);
		}
	}
	
	public boolean equals(MulticonjuntoArrayList<T> m) {
		if(this == m) return true;
		return this.elementos.equals(m.elementos);
	}
	
	public T getObject(int i) {
		if(i >= 0 && i < this.elementos.size()) return this.elementos.get(i);
		return null;
	}

	public ArrayList<T> getElements() {
		return this.elementos;
	}
	
}
