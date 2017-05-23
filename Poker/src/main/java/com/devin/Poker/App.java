package com.devin.Poker;

import java.util.Arrays;

public class App 
{
    public static void main( String[] args )
    {
    	
    	//Sample Test Case 1
    	String[] blackTestOne = { "2H", "3D", "5S", "9C", "KD"};
    	String[] whiteTestOne = {"2C", "3H", "4S", "8C", "AH"};
    	Poker pd1 = new Poker();
      	Poker pd2 = new Poker();        
       	long[] lBlack = pd1.sortHand(blackTestOne);
       	Player blackPlayer = new Player(lBlack, "Black");
        long[] lWhite = pd2.sortHand(whiteTestOne);
        Player whitePlayer = new Player(lWhite, "White");
        whitePlayer.setResult(pd1.evaluateHand(blackPlayer));
        blackPlayer.setResult(pd2.evaluateHand(whitePlayer));
        pd1.determineWinner(whitePlayer, blackPlayer);

        //Sample Test Case 2
    	String[] blackTestTwo = {"2H", "4S", "4C", "2D", "4H"};
    	String[] whiteTestTwo = {"2S", "8S", "AS", "QS", "3S"};
    	pd1 = new Poker();
    	pd2 = new Poker();        
       	lBlack = pd1.sortHand(blackTestTwo);
       	blackPlayer = new Player(lBlack, "Black");
        lWhite = pd2.sortHand(whiteTestTwo);
        whitePlayer = new Player(lWhite, "White");
        blackPlayer.setResult(pd1.evaluateHand(blackPlayer));
        whitePlayer.setResult(pd2.evaluateHand(whitePlayer));
        pd1.determineWinner(blackPlayer, whitePlayer);
  	
        //Sample Test Case 3
    	String[] blackTestThree = {"2H", "3D", "5S", "9C", "KD"};
    	String[] whiteTestThree = {"2C", "3H", "4S", "8C", "KH"};
    	pd1 = new Poker();
    	pd2 = new Poker();        
       	lBlack = pd1.sortHand(blackTestThree);
       	blackPlayer = new Player(lBlack, "Black");
        lWhite = pd2.sortHand(whiteTestThree);
        whitePlayer = new Player(lWhite, "White");
        blackPlayer.setResult(pd1.evaluateHand(blackPlayer));
        whitePlayer.setResult(pd2.evaluateHand(whitePlayer));
        pd1.determineWinner(blackPlayer, whitePlayer);
    	
    	
    	
        //Sample Test Case 4
    	String[] blackTestFour = {"2H", "3D", "5S", "9C", "KD"};
    	String[] whiteTestFour = {"2D", "3H", "5C", "9S", "KH"};
    	pd1 = new Poker();
    	pd2 = new Poker();        
       	lBlack = pd1.sortHand(blackTestFour);
       	blackPlayer = new Player(lBlack, "Black");
        lWhite = pd2.sortHand(whiteTestFour);
        whitePlayer = new Player(lWhite, "White");
        blackPlayer.setResult(pd1.evaluateHand(blackPlayer));
        whitePlayer.setResult(pd2.evaluateHand(whitePlayer));
        pd1.determineWinner(blackPlayer, whitePlayer);
    }
}
