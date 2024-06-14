package edu.ncsu.monopoly.gui;

import edu.ncsu.monopoly.theOwner;
import edu.ncsu.monopoly.Player;
import edu.ncsu.monopoly.RailRoadCell;

public class RRCellInfoFormatter implements CellInfoFormatter {
    public String format(theOwner theOwner) {
        RailRoadCell c = (RailRoadCell)theOwner;
        StringBuffer buf = new StringBuffer();
        Player owner = theOwner.getOwner();
        String ownerName = "";
        if(owner != null) {
        	ownerName = owner.getName();
        }
        buf.append("<html><b><font color='lime'>")
                .append(theOwner.getName())
                .append("</font></b><br>")
                .append("$").append(c.getPrice())
				.append("<br>Owner: ").append(ownerName)
                .append("</html>");
        return buf.toString();
    }
}
