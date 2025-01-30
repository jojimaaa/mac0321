package ex04;
import ex03.*;
import java.util.*;

public class Adaptador<T> implements Iterable<T>{
	private Set<T> conjunto;
	
	
	public Adaptador(MulticonjuntoArrayList<T> m) {
		conjunto = new HashSet<T>();
		for(T obj : m.getElements()) {
			if(!conjunto.contains(obj)) conjunto.add(obj);
		}
	}
	
	public Adaptador(MulticonjuntoLinkedList<T> m) {
		conjunto = new HashSet<T>();
		for(T obj : m.getElements()) {
			if(!conjunto.contains(obj)) conjunto.add(obj);
		}
	}
	
	public Adaptador(MulticonjuntoStack<T> m) {
		conjunto = new HashSet<T>();
		for(T obj : m.getElements()) {
			if(!conjunto.contains(obj)) conjunto.add(obj);
		}
	}
	
	public Adaptador(MulticonjuntoSet<T> m) {
		conjunto = new HashSet<T>();
		for(T obj : m.getElements()) {
			if(!conjunto.contains(obj)) conjunto.add(obj);
		}
	}
	
	public void add(T t) {
		if(!conjunto.contains(t)) conjunto.add(t);
	}
	
	public void addAll(MulticonjuntoArrayList<T> m) {
		for(T i : m.getElements()) {
			if(!conjunto.contains(i))
				this.conjunto.add(i);
		}
	}
	
	public void addAll(MulticonjuntoLinkedList<T> m) {
		for(T i : m.getElements()) {
			if(!conjunto.contains(i))
				this.conjunto.add(i);
		}
	}
	
	public void addAll(MulticonjuntoStack<T> m) {
		for(T i : m.getElements()) {
			if(!conjunto.contains(i))
				this.conjunto.add(i);
		}
	}
	
	public void addAll(MulticonjuntoSet<T> m) {
		for(T i : m.getElements()) {
			if(!conjunto.contains(i))
				this.conjunto.add(i);
		}
	}
	
	public boolean equals(MulticonjuntoSet<T> m) {
		return this.conjunto.equals(m.getElements());
	}
	
	public boolean equals(MulticonjuntoArrayList<T> m) {
		
		return this.conjunto.equals(m.getElements());
	}
	
	
	public boolean equals(MulticonjuntoStack<T> m) {
		return this.conjunto.equals(m.getElements());
	}
	public boolean equals(MulticonjuntoLinkedList<T> m) {
		return this.conjunto.equals(m.getElements());
	}
	
	public Set<T> pegaSet() {
		return conjunto; // Retorna o conjunto criado
	}
	
	public Iterator<T> iterator() {
		return pegaSet().iterator(); // Cria um iterável para o conjunto 
	}
}
