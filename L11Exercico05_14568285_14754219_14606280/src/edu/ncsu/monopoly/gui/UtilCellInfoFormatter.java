package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.IOwnable;
import edu.ncsu.monopoly.Player;
import edu.ncsu.monopoly.UtilityCell;

public class UtilCellInfoFormatter implements CellInfoFormatter {

	public String format(IOwnable theOwner) {
        UtilityCell c = (UtilityCell)theOwner;
        StringBuffer buf = new StringBuffer();
        Player owner = theOwner.getOwner();
        String ownerName = "";
        if(owner != null) {
        	ownerName = owner.getName();
        }
        buf.append("<html><b><font color='olive'>")
                .append(theOwner.getName())
                .append("</font></b><br>")
                .append("$").append(c.getPrice())
				.append("<br>Owner: ").append(ownerName)
                .append("</html>");
        return buf.toString();
	}
}
