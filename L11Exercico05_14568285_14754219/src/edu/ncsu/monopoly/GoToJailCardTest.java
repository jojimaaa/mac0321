package edu.ncsu.monopoly;

import junit.framework.TestCase;

public class GoToJailCardTest extends TestCase {
    GameMaster gameMaster;
    Card jailCard = new JailCard(Card.TYPE_CC);
    
    protected void setUp() {
		gameMaster = GameMaster.instance();
		gameMaster.setGameBoard(new GameBoardCCJail());
		gameMaster.setNumberOfPlayers(1);
		gameMaster.reset();
		gameMaster.setGUI(new MockGUI());
		gameMaster.getGameBoard().addCard(jailCard);
    }
    
    public void testJailCardAction() {
		Card card = gameMaster.drawCCCard();
		assertEquals(jailCard, card);
		card.applyAction();
		theOwner theOwner = gameMaster.getCurrentPlayer().getPosition();
		assertEquals(gameMaster.getGameBoard().queryCell("Jail"), theOwner);
    }
    
    public void testJailCardLabel() {
        assertEquals("Go to Jail immediately without collecting" +
        		" $200 when passing the GO theOwner", jailCard.getLabel());
    }
    
    public void testJailCardUI() {
        gameMaster.movePlayer(0, 1);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
		theOwner theOwner = gameMaster.getCurrentPlayer().getPosition();
		assertEquals(gameMaster.getGameBoard().queryCell("Jail"), theOwner);
		assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
    }
}
