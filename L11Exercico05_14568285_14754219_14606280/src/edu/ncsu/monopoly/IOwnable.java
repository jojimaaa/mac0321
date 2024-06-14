package edu.ncsu.monopoly;

public interface IOwnable {

	String getName();

	Player getOwner();

	int getPrice();

	boolean isAvailable();

	void playAction();

	void setAvailable(boolean available);

	void setOwner(Player owner);

	String toString();

}