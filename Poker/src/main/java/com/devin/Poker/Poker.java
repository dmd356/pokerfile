package com.devin.Poker;

import java.util.Arrays;

public class Poker {
	private String[] deckOfCards = new String[52];
	private long[] newHand = new long[5];

	public long evaluateHand(Player player) {
		int flushCount = 0;
		int straightCount = 0;
		int straightFlushCount = 0;
		int fourKindCount = 0;
		int pairCount = -1;
		long handResult = 0;
		long fourOfaKind = 0;
		long[] newHand = new long[5];
		long[] playerHand = player.getHand();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				boolean isStraightQualifier = playerHand[i] % 100 + 1 == playerHand[j] % 100
						|| playerHand[i] % 100 == playerHand[j] % 100 + 1;
				boolean isStraightFlush = playerHand[i] + 1 == playerHand[j] || playerHand[i] == playerHand[j] + 1;
				boolean isPairQualifier = playerHand[i] % 100 == playerHand[j] % 100 && i != j
						&& newHand[i] != (long) playerHand[i];
				boolean isFourOfKind = playerHand[i] % 100 == playerHand[j] % 100 && i != j;
				boolean isFlush = playerHand[i] - (playerHand[i] % 10) == playerHand[j] - (playerHand[j] % 10);
				if (isFlush) {
					if (i == flushCount && i != j) {
						flushCount++;
						continue;
					}
				}
				if (isStraightQualifier) {
					if (isStraightFlush) {
						straightFlushCount++;
						newHand[i] = (long) playerHand[i];
						continue;
					} else {
						newHand[i] = (long) playerHand[i];
						straightCount++;
						continue;
					}
				}
				if (isFourOfKind) {
					fourKindCount++;
					fourOfaKind = playerHand[i] % 10;
				}
				if (playerHand[i] % 100 == playerHand[j] % 100 && i != j && newHand[i] != (long) playerHand[i]) {
					if (i > j && newHand[pairCount] != (long) playerHand[i] && pairCount >= 0) {
						pairCount++;
						newHand[pairCount] = (long) playerHand[i];
						continue;
					} else {

						if (pairCount >= 0) {
							if (newHand[pairCount] != (long) playerHand[i]) {
								pairCount++;
								newHand[pairCount] = (long) playerHand[i];
								continue;
							}
						}
						if (pairCount < 0) {
							pairCount++;
							newHand[pairCount] = (long) playerHand[i];
							continue;
						}
					}
				}
			}
		}
		pairCount = 0;
		sortHandArray(newHand);
		for (int i = 0; i < newHand.length; i++) {
			for (int j = 0; j < newHand.length; j++) {
				if (newHand[i] > 0 && i >= j || newHand[i] > 0 && i == 0) {
					if (newHand[i] == newHand[j] && j != i) {
						pairCount++;
					}
				}
			}
		}
		Arrays.sort(newHand);
		player.setNewHandAfterEvaluation(newHand);
		// System.out.println("Four of a Kind:" + fourKindCount + " Straight
		// Count:" + straightCount
		// + " Straight Flush Count:" + straightFlushCount);
		if (fourKindCount > 8) {
			player.setPlayerHandSentence("Thats a four of a kind of " + fourOfaKind);
			System.out.println(Arrays.toString(newHand));
			handResult = 7;
		} else if (straightCount > 6) {
			player.setPlayerHandSentence("Thats a straight! Winning Hand : " + Arrays.toString(newHand));
			System.out.println(Arrays.toString(newHand));
			handResult = 4;
		} else if (straightFlushCount >= 4) {
			player.setPlayerHandSentence("Thats a Straight Flush! Winning Hand : " + Arrays.toString(newHand));
			System.out.println(Arrays.toString(newHand));
			handResult = 8;
		} else if (flushCount == 5) {
			player.setPlayerHandSentence("Thats a Flush! Winning Hand : " + Arrays.toString(newHand));
			System.out.println(Arrays.toString(newHand));
			handResult = 5;
		} else if (pairCount > 0) { // eval pairs
			long card;
			switch (pairCount) {
			case 2:
				card = newHand[4];
				player.setPlayerHandSentence("Thats a pair of " + card + "s!");
				handResult = 1; // pairs
				break;
			case 3:
				card = newHand[2];
				player.setPlayerHandSentence("Thats a three of a kind of " + card + "s!");
				handResult = 3; // 3 of a kind
				break;
			case 4:
				player.setPlayerHandSentence("Thats two Pairs of " + newHand[1] + " and " + newHand[3] + "!");
				handResult = 2; // 2 pairs
				break;
			case 5:
				player.setPlayerHandSentence("Thats a full house! " + newHand[0] + " appears "
						+ countNumber(newHand, newHand[0]) + " times! " + "and " + newHand[4] + " appears "
						+ countNumber(newHand, newHand[4]) + " times! " + Arrays.toString(newHand));
				handResult = 6; // 2 pairs
				break;
			}
		}
		return handResult;
	}// evaluate hand

	public long[] sortHand(String[] playerHand) {

		for (int i = 0; i < 5; i++) {
			String x = playerHand[i].substring(0, 1);

			if (canParse(x) == true) {
				newHand[i] = Integer.parseInt(x);
			} else {
				switch (x) {
				case "T":
					newHand[i] = 10;
					break;
				case "J":
					newHand[i] = 11;
					break;
				case "Q":
					newHand[i] = 12;
					break;
				case "K":
					newHand[i] = 13;
					break;
				case "A":
					newHand[i] = 14;
					break;
				}
			}
			String y = playerHand[i].substring(1);
			switch (y) {
			case "C":
				newHand[i] += 100;
				break;
			case "D":
				newHand[i] += 200;
				break;
			case "S":
				newHand[i] += 300;
				break;
			case "H":
				newHand[i] += 400;
				break;
			}
		}
		Arrays.sort(newHand);
		// System.out.println(Arrays.toString(newHand));
		return newHand;
	}// sort hand

	public String sortHandBackToFormat(long card) {
		if (card > 9 && card < 15) {
			switch ((int) card) {
			case 10:
				return "Ten";
			case 11:
				return "Jack";
			case 12:
				return "Queen";
			case 13:
				return "King";
			case 14:
				return "Ace";
			}
		}
		return Long.toString(card);
	}//reverse long

	public int countNumber(long[] args, long num) {
		int count = 0;
		for (int i = 0; i < args.length; i++) {
			if (num == args[i]) {
				count++;
			}
		}
		return count;
	} // count numbers

	public boolean canParse(String parse) {
		boolean canParse = true;
		try {
			Integer.parseInt(parse);
		} catch (NumberFormatException e) {
			canParse = false;
		}
		return canParse;
	} // can parse

	public void determineWinner(Player p1, Player p2) {
		long p1Result = p1.getResult();
		long p2Result = p2.getResult();
		if (p1Result > p2Result) {
			System.out.println("Player White Wins! " + p1.getPlayerHandSentence());
		} else if (p1Result < p2Result) {
			System.out.println("Player Black Wins! " + p2.getPlayerHandSentence());
		} else if (p1Result == p2Result) {
			p1Result = determineWinnerCase(p1, p1.getResult());
			p2Result = determineWinnerCase(p2, p2.getResult());
			if (p1Result > p2Result) {
				System.out.println("Player " + p1.getName() + " wins with " + sortHandBackToFormat(p1.getWinningCard()) + "!");
			} else if (p1Result < p2Result) {
				System.out.println("Player " + p2.getName() + " wins with " + sortHandBackToFormat(p2.getWinningCard()) + "!");
			} else {
				p1Result = nextHighCard(p1, 3);
				p2Result = nextHighCard(p2, 3);
				if (p1Result > p2Result) {
					System.out.println("Player " + p1.getName() + " wins with high card " + sortHandBackToFormat(p1.getWinningCard()) + "!");
				} else if (p1Result < p2Result) {
					System.out.println("Player " + p2.getName() + " wins with high card " + sortHandBackToFormat(p2.getWinningCard()) + "!");
				} else {
					System.out.println("Thats a tie!!");

				}
			}
		}
	}

	private long determineWinnerCase(Player player, long caseNum) {
		long[] hand = sortNewHand(player);
		if (caseNum == 6) {
			player.setWinningCard(hand[2]);
			return hand[2];
		} else {
			player.setWinningCard(hand[4]);
			return hand[4];
		}
	}

	private long nextHighCard(Player player, int nextCard) {
		long[] hand = sortNewHand(player);
		return hand[nextCard];
	}

	private long[] sortNewHand(Player player) {
		long[] hand = player.getHand();
		for (int i = 0; i < hand.length; i++) {
			hand[i] = hand[i] % 100;
		}
		Arrays.sort(hand);
		return hand;
	}

	private long[] sortHandArray(long[] hand) {
		for (int i = 0; i < hand.length; i++) {
			hand[i] = hand[i] % 100;
		}
		Arrays.sort(hand);
		return hand;
	}
}
