package ex03;

import java.util.Stack;

public class MulticonjuntoStack <T> {

	
	private Stack<T> elementos;
	
	public MulticonjuntoStack() {
		super();
		this.elementos = new Stack<T>();
	}
	
	public void add(T elemento) {
		this.elementos.push(elemento);		
	}

	
	public void addAll(MulticonjuntoStack<T> m) {
		for(T t : m.elementos) this.elementos.push(t);		
	}

	public boolean equals(MulticonjuntoStack<T> m) {
		if(this == m) return true;
		return this.elementos.equals(m.elementos);
	}

	
	public Stack<T> getElements() {
		return this.elementos;
	}

	public T getObject(int i) {
		if(i >= 0 && i < this.elementos.size()) return this.elementos.get(i);
		return null;
	}

}
