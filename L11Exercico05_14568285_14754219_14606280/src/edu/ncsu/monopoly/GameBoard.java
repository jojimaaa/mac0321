package edu.ncsu.monopoly;

import java.util.ArrayList;
import java.util.Hashtable;

public class GameBoard {

	private ArrayList cells = new ArrayList();
    private ArrayList chanceCards = new ArrayList();
	//the key of colorGroups is the name of the color group.
	private Hashtable colorGroups = new Hashtable();
	private ArrayList communityChestCards = new ArrayList();
	private GameMaster gameMaster;
	
	public GameBoard() {
		IOwnable go = new GoCell();
		addCell(go);
	}

    public void addCard(Card card) {
        if(card.getCardType() == Card.TYPE_CC) {
            communityChestCards.add(card);
        } else {
            chanceCards.add(card);
        }
    }
	
	public void addCell(IOwnable theOwner) {
		cells.add(theOwner);
	}
	
	public void addCell(PropertyCell cell) {
		String cellColorGroup = cell.getColorGroup();
		int propertyNumber = getPropertyNumberForColor(cellColorGroup);
		colorGroups.put(cellColorGroup, new Integer(propertyNumber + 1));
        cells.add(cell);
	}

    public Card drawCCCard() {
        Card card = (Card)communityChestCards.get(0);
        communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    public Card drawChanceCard() {
        Card card = (Card)chanceCards.get(0);
        chanceCards.remove(0);
        addCard(card);
        return card;
    }

	public IOwnable getCell(int newIndex) {
		return (IOwnable)cells.get(newIndex);
	}
	
	public int getCellNumber() {
		return cells.size();
	}
	
	public PropertyCell[] getPropertiesInMonopoly(String color) {
		PropertyCell[] monopolyCells = 
			new PropertyCell[getPropertyNumberForColor(color)];
		int counter = 0;
		for (int i = 0; i < getCellNumber(); i++) {
			IOwnable c = getCell(i);
			if(c instanceof PropertyCell) {
				PropertyCell pc = (PropertyCell)c;
				if(pc.getColorGroup().equals(color)) {
					monopolyCells[counter] = pc;
					counter++;
				}
			}
		}
		return monopolyCells;
	}
	
	public int getPropertyNumberForColor(String name) {
		Integer number = (Integer)colorGroups.get(name);
		if(number != null) {
			return number.intValue();
		}
		return 0;
	}

	public IOwnable queryCell(String string) {
		for(int i = 0; i < cells.size(); i++){
			IOwnable temp = (IOwnable)cells.get(i); 
			if(temp.getName().equals(string)) {
				return temp;
			}
		}
		return null;
	}
	
	public int queryCellIndex(String string){
		for(int i = 0; i < cells.size(); i++){
			IOwnable temp = (IOwnable)cells.get(i); 
			if(temp.getName().equals(string)) {
				return i;
			}
		}
		return -1;
	}

    public void removeCards() {
        communityChestCards.clear();
    }
}
