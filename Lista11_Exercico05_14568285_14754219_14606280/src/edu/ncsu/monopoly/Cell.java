package edu.ncsu.monopoly;

public abstract class Cell implements IOwnable {
	private String name;
	protected Player theOwner;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Player getOwner() {
		return theOwner;
	}
	
	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public abstract void playAction();

	void setName(String name) {
		this.name = name;
	}

	@Override
	public void setOwner(Player owner) {
		this.theOwner = owner;
	}
    
    @Override
	public String toString() {
        return name;
    }
}
