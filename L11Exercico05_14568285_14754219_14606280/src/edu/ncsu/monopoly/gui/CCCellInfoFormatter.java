package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.IOwnable;

public class CCCellInfoFormatter implements CellInfoFormatter {
    public String format(IOwnable theOwner) {
        return "<html><font color='white'><b>" + theOwner.getName() + "</b></font></html>";
    }
}
