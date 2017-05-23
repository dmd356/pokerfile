package com.devin.Poker;

public class Player {
	long[] hand = new long[5];
	long[] handAfterEvaluation = new long[5];
	long winningCard = 0;
	long result = 0;
	String playerHandSentence;
	String name;
	
	public Player(long[] hand, String name){
		this.hand = hand;
		this.name = name;
	}
	
	public String getPlayerHandSentence() {
		return playerHandSentence;
	}
	
	public long[] getHandAfterEvaluation(){
		return handAfterEvaluation;
	}
	
	public long[] getHand(){
		return hand;
	}
	
	public long getWinningCard(){
		return winningCard;
	}
	
	public String getName(){
		return name;
	}
	
	public long getResult(){
		return result;
	}
	
	public void setWinningCard(long winningCard){
		this.winningCard = winningCard;
	}
	
	public void setHand(long[] hand){
		this.hand = hand;
	}
	
	public void setNewHandAfterEvaluation(long[] hand){
		handAfterEvaluation = hand;
	}
	
	public void setResult(long result){
		this.result = result;
	}
	
	public void setPlayerHandSentence(String playerHandSentence) {
		this.playerHandSentence = playerHandSentence;
	}
	
	
}
