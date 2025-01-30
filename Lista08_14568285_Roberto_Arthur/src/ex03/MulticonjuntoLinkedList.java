package ex03;

import java.util.LinkedList;

public class MulticonjuntoLinkedList<T> {

	private LinkedList<T> elementos;
	
	public MulticonjuntoLinkedList() {
		super();
		this.elementos = new LinkedList<T>();
	}

	public void add(T elemento) {
		this.elementos.add(elemento);
		
	}

	public void addAll(MulticonjuntoLinkedList<T> m) {
		for(T obj: m.elementos) this.elementos.add(obj);
	}

	
	public boolean equals(MulticonjuntoLinkedList<T> m) {
		if(this == m) return true;
		return this.elementos.equals(m.elementos);
	}

	
	public T getObject(int i) {
		if(i>=0 && i< this.elementos.size()) return this.elementos.get(i);
		return null;
	}

	public LinkedList<T> getElements() {
		return this.elementos;
	}
}
