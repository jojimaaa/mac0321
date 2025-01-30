package usp.mac321.ep1.ex2;

public class Droga {
	private double Vdt;
	private double Vdc;
	
	public Droga(double Vdt, double Vdc) {
		this.Vdc = Vdc;
		this.Vdt = Vdt;
	}
	
	public double Vdt() {
		return this.Vdt;
	}
	
	public double Vdc() {
		return this.Vdc;
	}
}
