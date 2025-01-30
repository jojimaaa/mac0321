package usp.mac321.ep1.ex2;

public class Paciente {
	private double pac = 110000; //valor base
	private double temp = 36.5; //valor base
	private double tempAumento = 5;
	private long freqSurtos;
	private double pacVelocidade;
	private boolean dead;
	private boolean doente;
	
	public Paciente(long freqSurtos, double pacVelocidade) {
		this.freqSurtos = freqSurtos;
		this.pacVelocidade = pacVelocidade;
		this.dead = false;
		this.doente = false;
	}
	
	public long freq() {
		return this.freqSurtos;
	}
	
	public double tempCheck(long time) {
		return this.temp;
	}
	
	public double pacCheck(long time) {
		return this.pac;
	}
	
	public boolean dead() {
		return this.dead;
	}
	
	public void dies() {
		this.dead = true;
		this.pac = -1;
		this.temp = -1;
	}
	
	public boolean sick() {
		return this.doente;
	}
	
	public void iniciaSurto() {
		this.temp = this.temp + this.tempAumento;
		this.doente = true;
	}
	
	public void recebeDroga(Droga droga) {
		this.pac = this.pac - droga.Vdc();
		this.doente = false;
	}
	
}
