package usp.mac321.ep1.ex2;

public class Medico {
	private long monitorFreq;
	private Droga drogas;
	private long duracaoAdm;
	
	public Medico(long monitorFreq, Droga drogas, long duracaoAdm) {
		this.monitorFreq = monitorFreq;
		this.drogas = drogas;
		this.duracaoAdm = duracaoAdm;
	}
	
	public long freq() {
		return this.monitorFreq;
	}
	
	public boolean administrarDrogas(double temp) {
		if (temp > 36.5) {
			return true;
		}
		return false;
		
	}
}
