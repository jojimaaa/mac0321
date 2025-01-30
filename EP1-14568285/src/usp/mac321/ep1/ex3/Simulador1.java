package usp.mac321.ep1.ex3;

import br.usp.ime.mac321.ep1.ex1.*;
import usp.mac321.ep1.ex2.*;

public class Simulador1 extends Controlador {
	private Paciente paciente;
	private Droga droga = new Droga(1, 15);
	private Medico medico;
	private long deathTimer = 0;
	private long timer = 0;
	
	
	private class clock extends Evento{

		public clock(long time) {
			super(time);
		}

		@Override
		public void ação() {
			long t = System.currentTimeMillis();			
			insere(new clock(t + 50));
		}

		@Override
		public String descrição() {
			String result = timer/10 + " min";
			timer += 50;
			return result;
		}
		
	}
	
	private class criaMedico extends Evento{
		public criaMedico(long time) {
			super(time);
		}

		@Override
		public void ação() {
			long tm = System.currentTimeMillis();
			medico = new Medico(100, droga, 0);
			insere(new monitora(tm));
		}

		@Override
		public String descrição() {
			return "Médico criado";
		}
	}
	
	private class criaPaciente extends Evento{
		public criaPaciente(long time) {
			super(time);
		}

		@Override
		public void ação() {
			paciente = new Paciente(51, 0);
			long tm = System.currentTimeMillis();
			insere(new iniciaSurto(tm + paciente.freq()));
		}

		@Override
		public String descrição() {
			return "Paciente criado";
		}
	}
	
	private class iniciaSurto extends Evento{
		public iniciaSurto(long time) {
			super(time);
		}
		@Override
		public void ação() {
			if(!paciente.sick() && !paciente.dead()) {
				paciente.iniciaSurto();
				deathTimer = 0;
			}
			else {
				deathTimer += paciente.freq();
			}
			
			if(deathTimer >= 600) {
				paciente.dies();
			}
			long tm = System.currentTimeMillis();
			insere(new iniciaSurto(tm+paciente.freq()));
			
		}
		@Override
		public String descrição() {
			return "Paciente inicia surto infecioso";
		}
	}
	
	private class monitora extends Evento{
		public monitora(long time) {
			super(time);
		}

		@Override
		public void ação() {
			long tm = System.currentTimeMillis();
			if(paciente.dead()) {
				Simulador1.super.stop();
			}
			else insere(new monitora(tm+medico.freq()));
		}

		@Override
		public String descrição() {
			long tm = System.currentTimeMillis();
			double temp = paciente.tempCheck(tm);
			double pac = paciente.pacCheck(tm);
			if(paciente.dead()) return("Médico verifica morte do paciente\nSimulação terminada");
			return ("Medico consulta temperatura: " + temp + "\nMedico consulta concentração: "+ pac);
		}
		
	}
	
	private class reset extends Evento{
		public reset(long time) {
			super(time);
		}

		@Override
		public void ação() {
			long t = System.currentTimeMillis();
			insere(new clock(t));
			insere(new criaMedico(t));
			insere(new criaPaciente(t));
		}

		@Override
		public String descrição() {
			return "Simulação Resetada";
		}
	}

	public void restart(long time) {
		insere(new reset(time));
	}
	
	public static void main(String[] args) {
		Simulador1 s = new Simulador1();
		long tm = System.currentTimeMillis();
		s.insere(s.new reset(tm));
		s.run();
	}
}
