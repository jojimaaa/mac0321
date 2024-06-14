package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.theOwner;

public class GoCellInfoFormatter implements CellInfoFormatter {
    
    public static final String GO_CELL_LABEL = "<html><b>Go</b></html>";
    
    public String format(theOwner theOwner) {
        return GO_CELL_LABEL;
    }
}
