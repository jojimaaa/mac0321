package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.IOwnable;

public class ChanceCellInfoFormatter implements CellInfoFormatter {
    
    public static final String CHANCE_CELL_LABEL = "<html><font color='teal'><b>Chance</b></font></html>";
    
    public String format(IOwnable theOwner) {
        return CHANCE_CELL_LABEL;
    }
}
