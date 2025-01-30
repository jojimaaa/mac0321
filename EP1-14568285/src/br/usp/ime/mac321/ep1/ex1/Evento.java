package br.usp.ime.mac321.ep1.ex1;

abstract public class Evento {
	private long eventTime;
	
	public Evento(long time) {
		eventTime = time;
	}
	
	public boolean ready() {
		return System.currentTimeMillis() >= eventTime;
	}
	
	abstract public void ação();
	abstract public String descrição();
}
