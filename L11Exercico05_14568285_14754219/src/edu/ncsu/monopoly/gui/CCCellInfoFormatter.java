package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.theOwner;

public class CCCellInfoFormatter implements CellInfoFormatter {
    public String format(theOwner theOwner) {
        return "<html><font color='white'><b>" + theOwner.getName() + "</b></font></html>";
    }
}
