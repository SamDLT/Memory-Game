import javax.swing.JFrame;

public class EqualPairLevel extends EasyLevel {
	private static int score = 0;
	protected EqualPairLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame, ScoreManager validScore) {
		super(validTurnTime, mainFrame, validScore);
		super.turnsTakenCounter.setDifficultyModeLabel("Medium Level");
		super.scoreCounter.setDifficultyModeLabel("EPL");
	}

	@Override
	protected boolean addToTurnedCardsBuffer(Card card) {
		this.turnedCardsBuffer.add(card);
		if(this.turnedCardsBuffer.size() == getCardsToTurnUp())
		{
			// there are two cards faced up
			// record the player's turn
			this.turnsTakenCounter.increment();
			// get the other card (which was already turned up)
			Card otherCard = (Card) this.turnedCardsBuffer.get(0);
			// the cards match, so remove them from the list (they will remain face up)
			if( otherCard.getNum() == card.getNum()){
				this.turnedCardsBuffer.clear();
				score = score + 10;
				scoreCounter.increment();
			}
			// the cards do not match, so start the timer to turn them down
			else{ 
				this.turnDownTimer.start();
				score = score - 1;
				scoreCounter.increment();


			}
		}
		return true;
	}

	@Override
	protected boolean turnUp(Card card) {
		// the card may be turned
		if(this.turnedCardsBuffer.size() < getCardsToTurnUp()) 
		{
			return this.addToTurnedCardsBuffer(card);
		}
		// there are already the number of EasyMode (two face up cards) in the turnedCardsBuffer
		return false;
	}

	@Override
	protected String getMode() {
		// TODO Auto-generated method stub
		return "MediumMode";
	}
	static int getScore(){
		return score;
	}
	static int resetScore(){
		return score = 0;
	}



}
