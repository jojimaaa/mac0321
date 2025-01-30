package br.usp.ime.mac321.ep1.ex1;

public class EventoSimples extends Evento {

	public EventoSimples(long time) {
		super(time);

	}

	@Override
	public void ação() {
		System.out.print("Ação!\n");
		
	}

	@Override
	public String descrição() {
		return new String("Nada");
		
	}

}
