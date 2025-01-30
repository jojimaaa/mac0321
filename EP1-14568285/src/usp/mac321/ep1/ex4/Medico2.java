package usp.mac321.ep1.ex4;

import usp.mac321.ep1.ex2.Droga;

public class Medico2 {
	private long monitorFreq;
	private Droga drogas;
	private long duracaoAdm;
	
	public Medico2(long monitorFreq, Droga drogas, long duracaoAdm) {
		this.monitorFreq = monitorFreq;
		this.drogas = drogas;
		this.duracaoAdm = duracaoAdm;
	}
	
	public long freq() {
		return this.monitorFreq;
	}
	
	public boolean administrarDrogas(double temp, double pac) {
		if (temp > 41 && pac > 80000) {
			return true;
		}
		return false;
		
	}
	
	public long durAdm(){
		return duracaoAdm;
	}
	
	public Droga drugToUse() {
		return this.drogas;
	}
}
