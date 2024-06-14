package edu.ncsu.monopoly;

public abstract class theOwner implements IOwnable {
	private String name;
	protected Player owner;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Player getOwner() {
		return owner;
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
		this.owner = owner;
	}
    
    @Override
	public String toString() {
        return name;
    }
}
