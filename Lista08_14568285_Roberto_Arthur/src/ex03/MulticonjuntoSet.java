package ex03;

import java.util.HashSet;
import java.util.Set;

public class MulticonjuntoSet <T> {

	private Set<T> elementos;
	
	public MulticonjuntoSet() {
		super();
		this.elementos = new HashSet<T>();
	}
	
	public void add(T elemento) {	
		this.elementos.add(elemento);
	}

	
	public void addAll(MulticonjuntoSet<T> m) {
		this.elementos.addAll(m.elementos);		
	}

	public boolean equals(MulticonjuntoSet<T> m) {
		if(this==m) return true;
		return this.elementos.equals(m.elementos);
	}

	
	public Set<T> getElements() {
		return this.elementos;
	}

}
