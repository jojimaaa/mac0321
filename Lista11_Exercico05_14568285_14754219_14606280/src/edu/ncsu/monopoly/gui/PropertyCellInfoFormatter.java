package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.IOwnable;
import edu.ncsu.monopoly.Player;
import edu.ncsu.monopoly.PropertyCell;

public class PropertyCellInfoFormatter implements CellInfoFormatter {
    public String format(IOwnable theOwner) {
        PropertyCell c = (PropertyCell)theOwner;
        StringBuffer buf = new StringBuffer();
        Player owner = theOwner.getOwner();
        String ownerName = "";
        if(owner != null) {
        	ownerName = owner.getName();
        }
        buf.append("<html><b><font color='")
                .append(c.getColorGroup())
                .append("'>")
                .append(theOwner.getName())
                .append("</font></b><br>")
                .append("$").append(c.getPrice())
				.append("<br>Owner: ").append(ownerName)
				.append("<br>* ").append(c.getNumHouses())
                .append("</html>");
        return buf.toString();
    }
}
