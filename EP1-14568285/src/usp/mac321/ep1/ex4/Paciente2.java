package usp.mac321.ep1.ex4;
import usp.mac321.ep1.ex2.Droga;

public class Paciente2 {
	private double pac = 110000; //valor base
	private double temp = 36.5; //valor base
	private double tBase = 36.6;
	private double pacBase = 110000;
	private double tempAumento = 5;
	private long freqSurtos;
	private double pacVelocidade;
	private boolean dead;
	private boolean doente;
	
	public Paciente2(long freqSurtos, double pacVelocidade) {
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
	
	public void treated() {
		this.doente = false;
	}
	
	public void recebeDroga(Droga droga, long t0, long t) {
		this.temp = this.tBase+(this.tempAumento*Math.exp(-droga.Vdt()*(t-t0)/10));
		this.pac = this.pacBase*Math.exp(-droga.Vdc()*(t-t0)/10);
	}
	
	public void voltaPac(double c0, long t0, long t) {
		this.pac = this.pacBase-((this.pacBase-c0)*Math.exp(-this.pacVelocidade*(t-t0)/10));
	}
	
	public double pacBase() {
		return this.pacBase;
	}
}
