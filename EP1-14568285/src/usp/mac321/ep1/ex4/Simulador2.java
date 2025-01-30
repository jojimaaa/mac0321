package usp.mac321.ep1.ex4;

import br.usp.ime.mac321.ep1.ex1.*;
import usp.mac321.ep1.ex2.Droga;



public class Simulador2  extends Controlador{
	private Paciente2 paciente;
	private Droga ckk = new Droga(0.027, 0.054);
	private Medico2 medico;
	private long surtoTimer = -1;
	private long timer = 0;
	private long lowPacT0 = -1;
	private boolean sobMedicacao = false;
	
	//Method for deathControl
	public void deathCtl() {
		long t = System.currentTimeMillis();
		if(paciente.tempCheck(t) > 41 && surtoTimer > 0) {
			if(surtoTimer >= 600) {
				paciente.dies();
			}
		}
		if(paciente.pacCheck(t) < paciente.pacBase() && lowPacT0 > 0) {
			if(lowPacT0 >= 300) paciente.dies();
		}
	}
	
	//Creators ==========================================
	private class criaMedico extends Evento{
		public criaMedico(long time) {
			super(time);
		}

		@Override
		public void ação() {
			long tm = System.currentTimeMillis();
			medico = new Medico2(100, ckk, 300);
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
			paciente = new Paciente2(1200, 0.038);
			long tm = System.currentTimeMillis();
			insere(new iniciaSurto(tm + paciente.freq()));
		}

		@Override
		public String descrição() {
			return "Paciente criado";
		}
	}

	
	//Clock ===========================================================
	private class clock extends Evento{

		public clock(long time) {
			super(time);
		}

		@Override
		public void ação() {
			long t = System.currentTimeMillis();			
			
			insere(new clock(t + 50));
			if(timer >= 3600) {
				Simulador2.super.stop();
			}
			if(surtoTimer >= 0) surtoTimer += 50;
			if(lowPacT0 >= 0) lowPacT0 += 50;
			if(timer > 0) deathCtl();
		}

		@Override
		public String descrição() {
			String result;
			if(timer >= 3600) {
				result = timer/10 + " min\nSimulação encerrada por tempo";
			}
			else {
				result = timer/10 + " min";
				
				if(timer > 0)
					if(paciente.dead()) result = timer/10 + " min | Paciente Faleceu";
				
				timer += 50;
			}
			return result;
		}
	}
	
	
	//Eventos ============================================================
	private class monitora extends Evento{
		public monitora(long time) {
			super(time);
		}

		@Override
		public void ação() {
			long tm = System.currentTimeMillis();
			deathCtl();
			if(paciente.dead()) {
				Simulador2.super.stop();
			}
			else {
				if(medico.administrarDrogas(paciente.tempCheck(tm), paciente.pacCheck(tm))) {
					for(long i = 0; i <= medico.durAdm(); i+= 10) {
						insere(new administraDroga(tm+i, tm, medico.drugToUse()));
						sobMedicacao = true;
					}
					insere(new monitora(tm+medico.freq()));
				}
				else insere(new monitora(tm+medico.freq()));
			}
		}

		@Override
		public String descrição() {
			if(paciente.dead()) return("Médico verifica morte do paciente\nSimulação terminada");
			long tm = System.currentTimeMillis();
			double temp = paciente.tempCheck(tm);
			double pac = paciente.pacCheck(tm);
			return ("Medico consulta temperatura: " + temp + "\nMedico consulta concentração: "+ pac);
		}
	}
	
	private class iniciaSurto extends Evento{
		public iniciaSurto(long time) {
			super(time);
		}
		@Override
		public void ação() {
			long tm = System.currentTimeMillis();
			
			if(!paciente.sick() && !paciente.dead()) {
				paciente.iniciaSurto();
				surtoTimer = 0;
			}
			insere(new iniciaSurto(tm+paciente.freq()));
			
		}
		@Override
		public String descrição() {
			return "Paciente inicia surto infecioso";
		}
	}
	
	
	private class administraDroga extends Evento {
		private long t0 = 0;
		private long evTime = 0;
		private Droga droga = ckk;
		public administraDroga(long time, long t0, Droga droga) {
			super(time);
			this.t0 = t0;
			this.droga = droga;
			this.evTime = time;
		}

		@Override
		public void ação() {
			long t = System.currentTimeMillis();
			paciente.recebeDroga(this.droga, this.t0, this.evTime);
			if(paciente.pacCheck(t) < 50000 && lowPacT0 < 0) lowPacT0 = 0;
			if((this.evTime - this.t0) >= medico.durAdm()) {
				if(paciente.tempCheck(t) < 41) {
					paciente.treated();
					surtoTimer = -1;
				}
				sobMedicacao = false;
				insere(new voltaPac(t, t, paciente.pacCheck(t)));
			}
		}

		@Override
		public String descrição() {
			return "Paciente tomou remedio";
		}
		
	}
	
	
	private class voltaPac extends Evento{
		private long t0 = 0;
		private double c0 = 0;

		public voltaPac(long time, long t0, double c0) {
			super(time);
			this.t0 = t0;
			this.c0 = c0;
		}

		@Override
		public void ação() {
			long t = System.currentTimeMillis();
			if(!sobMedicacao && !paciente.dead()) paciente.voltaPac(this.c0, this.t0, t);
			if(paciente.pacCheck(t) >= 50000) lowPacT0 = -1;
			if((paciente.pacCheck(t) < paciente.pacBase()) && !sobMedicacao && !paciente.dead()) {
				insere(new voltaPac(t+10, t0, c0));
			}
			
		}

		@Override
		public String descrição() {
			if(!paciente.dead()) {
				if(!sobMedicacao) return "PAC Subindo";
				return"Paciente tomou a droga, PAC parou de subir";
			}
			return "";
		}
	}
	
	
	//Reset =============================================================================
	private class reset extends Evento{

		public reset(long time) {
			super(time);
		}

		@Override
		public void ação() {
			long t = System.currentTimeMillis();
			insere(new clock(t));
			insere(new criaPaciente(t));
			insere(new criaMedico(t));
		}

		@Override
		public String descrição() {
			return "Simulação reiniciada";
		}
	}
	
	public static void main(String[] args) {
		Simulador2 s = new Simulador2();
		long t = System.currentTimeMillis();
		s.insere(s.new reset(t));
		s.run();
	}
}

/*Para uma duração de aplicação de 30 minutos:
 * 
Simulação reiniciada
0 min
Paciente criado
Médico criado
Medico consulta temperatura: 36.5
Medico consulta concentração: 110000.0
5 min
10 min
Medico consulta temperatura: 36.5
Medico consulta concentração: 110000.0
15 min
20 min
Medico consulta temperatura: 36.5
Medico consulta concentração: 110000.0
25 min
30 min
Medico consulta temperatura: 36.5
Medico consulta concentração: 110000.0
35 min
40 min
Medico consulta temperatura: 36.5
Medico consulta concentração: 110000.0
45 min
50 min
Medico consulta temperatura: 36.5
Medico consulta concentração: 110000.0
55 min
60 min
Medico consulta temperatura: 36.5
Medico consulta concentração: 110000.0
65 min
70 min
Medico consulta temperatura: 36.5
Medico consulta concentração: 110000.0
75 min
80 min
Medico consulta temperatura: 36.5
Medico consulta concentração: 110000.0
85 min
90 min
Medico consulta temperatura: 36.5
Medico consulta concentração: 110000.0
95 min
100 min
Medico consulta temperatura: 36.5
Medico consulta concentração: 110000.0
105 min
110 min
Medico consulta temperatura: 36.5
Medico consulta concentração: 110000.0
115 min
Paciente inicia surto infecioso
120 min
Medico consulta temperatura: 41.5
Medico consulta concentração: 110000.0
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
125 min
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
130 min
Medico consulta temperatura: 40.52135756885778
Medico consulta concentração: 67658.99880448816
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
135 min
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
140 min
Paciente tomou remedio
Medico consulta temperatura: 39.51374126186995
Medico consulta concentração: 37355.507820943305
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
145 min
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
150 min
Medico consulta temperatura: 38.88516427018539
Medico consulta concentração: 22976.693263620462
Paciente tomou remedio
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
155 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
160 min
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 47562.878328826606
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
165 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
170 min
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 67301.66198678804
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
175 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
180 min
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 80800.25439525937
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
185 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
190 min
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 90031.42082209977
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
195 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
200 min
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 96447.64955213026
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
205 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
210 min
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 100732.0705245801
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
215 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
220 min
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 103662.02068845861
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
225 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
230 min
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 105682.1396203709
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
235 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
Paciente inicia surto infecioso
240 min
Medico consulta temperatura: 43.82429033111471
Medico consulta concentração: 107069.53827168295
Paciente tomou remedio
Paciente tomou a droga, PAC parou de subir
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
245 min
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
250 min
Medico consulta temperatura: 40.52135756885778
Medico consulta concentração: 67658.99880448816
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
255 min
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
260 min
Medico consulta temperatura: 39.59348395802865
Medico consulta concentração: 39428.16331068933
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
265 min
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
Paciente tomou remedio
270 min
Paciente tomou remedio
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 21768.856899197617
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
275 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
280 min
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 47325.16589890652
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
285 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
290 min
PAC Subindo
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 68737.25655504774
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
295 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
300 min
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 80800.25439525937
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
305 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 90031.42082209977
310 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
315 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 96344.25930343266
320 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
325 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
330 min
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 100696.7853932838
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
335 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
340 min
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 103637.89054884609
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
345 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
Medico consulta temperatura: 38.82429033111471
Medico consulta concentração: 105649.19886517063
350 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
355 min
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
PAC Subindo
Paciente inicia surto infecioso
360 min
Simulação encerrada por tempo
 */
