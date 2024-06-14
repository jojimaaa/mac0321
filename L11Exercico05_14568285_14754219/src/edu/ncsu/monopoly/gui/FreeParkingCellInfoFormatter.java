package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.theOwner;

public class FreeParkingCellInfoFormatter implements CellInfoFormatter {
    
    public static final String FP_CELL_LABEL = "<html><b>Free Parking</b></html>";
    
    public String format(theOwner theOwner) {
        return FP_CELL_LABEL;
    }
}
