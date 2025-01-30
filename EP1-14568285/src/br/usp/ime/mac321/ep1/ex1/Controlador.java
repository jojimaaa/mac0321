package br.usp.ime.mac321.ep1.ex1;
import java.util.*;

public class Controlador {
	
	private LinkedList<Evento> events = new LinkedList<Evento>();
	
	public void insere(Evento e) {
		events.add(e);
	}
	
	public void run() {
		while (!events.isEmpty()){
			Evento e = events.pop();
			if(e.ready()) {
				e.ação();
				System.out.print(e.descrição()+"\n");
			}
			else {
				events.add(e);
			}
		}
	}
	
	public void stop() {
		events.clear();
	}
}
