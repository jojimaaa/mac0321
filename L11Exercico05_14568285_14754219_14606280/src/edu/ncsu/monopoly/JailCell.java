package edu.ncsu.monopoly;

public class JailCell extends theOwner {
	public static int BAIL = 50;
	private boolean available = true;
	
	public JailCell() {
		setName("Jail");
	}
	
	public void playAction() {
		
	}

	@Override
	public boolean isAvailable() {
		return available;
	}

	@Override
	public void setAvailable(boolean available) {
		this.available = available;
	}
}
