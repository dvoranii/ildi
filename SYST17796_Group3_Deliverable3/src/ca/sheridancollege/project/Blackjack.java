/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Noah
 */
public class Blackjack extends Game {

    private GroupOfCards deck;
    Scanner input = new Scanner(System.in);

    public Blackjack(String name, BlackjackPlayer player) {
        super(name);
        ArrayList<Player> playerList = getPlayers();
        BlackjackPlayer dealer = new BlackjackPlayer("Dealer");

        playerList.add(player);
        playerList.add(dealer);

        this.setPlayers(playerList);
    }

    @Override
    public void play() {
        System.out.println("################################");
        System.out.println("###   Welcome to the Game!   ###");
        System.out.println("################################\n");
        System.out.println("This a simple Blackjack game.\nEnter 1 to hit or 2"
                + " to stand when promted.\nClosest to 21 without going over wins"
                + " the round.\nThe game runs for 5 rounds and the player with\nthe"
                + " most round wins at that time wins!\n**For the best experience"
                + " please fullscreen your output window**\n\n");
        //creates deck
        deck = new GroupOfCards();
        int i = 0;
        while (i < 5) {
            System.out.println("#####   Begin Round " + (i + 1) + "   #####\n");
            //makes deck a standrad 52 card deck
            deck.init52Deck();
            //shuffles deck
            deck.shuffle();
            //deals 2 to each player
            System.out.println("The dealer deals 2 cards to each player.\n");
            startingDeal();
            //shows the first deal
            displayHands();
            //Goes through every player in the game to prompt them to hit or stand
            for (Player player : this.getPlayers()) {
                BlackjackPlayer p = (BlackjackPlayer) player;

                if (!p.getName().equals("Dealer")) {
                    playerPrompt(p);
                } else {
                    dealerTurn(p);
                }
            }

            roundEnd();
            i++;
        }
        declareWinner();
    }

    /**
     * Displays the hands off all players in the game including Dealer. Dealer
     * hand displays the first card as UNKNOWN
     */
    public void displayHands() {
        //Displays player hands
        for (Player player : this.getPlayers()) {
            BlackjackPlayer p = (BlackjackPlayer) player;
            p.calcHandValue();
            p.displayHand();

        }
        System.out.println("");
    }

    public void startingDeal() {
        //Deal 2 cards to every player
        for (Player player : this.getPlayers()) {
            BlackjackPlayer p = (BlackjackPlayer) player;
            p.setHand(deck.draw(2));
        }
    }

    /**
     * Prompts the player whether to hit or stand. If hit is selected hit() is
     * called which in turn recalls PlayerPrompt(). If stand is selected
     *
     * @param p - the BlackjackPlayer that is being prompted to play.
     */
    public void playerPrompt(BlackjackPlayer p) {
        boolean goodInput = false;
        int playerChoice = 0;
        while (!goodInput) {
            System.out.print("Enter 1 to hit or 2 to hold: ");
            String opt = input.nextLine();
            if (opt.length() > 1 || opt.isBlank()) {
                System.out.println("Enter ONLY 1 or 2");
                continue;
            }

            if (Character.isDigit(opt.charAt(0))) {
                int input = Integer.parseInt(opt);
                if (input == 1 || input == 2) {
                    playerChoice = input;
                    goodInput = true;
                }
            } else {
                System.out.println("Enter a 1 or 2 ONLY.");
                continue;
            }
            if (playerChoice == 1) {
                hit(p);
            } else if (playerChoice == 2) {
                System.out.println("You hold. Turn passes to the next player.\n");
                //continues to the to prompt the next player in playerList
            }
        }
    }

    /**
     * Draws one card from deck into the player hand. After drawing the card it
     * then displays all players hands and prompts player p again. If the hit
     * send the players hand value of 21 go to next player and display bust
     * message.
     *
     * @param p - the BlackjackPlayer this has selected to hit
     */
    public void hit(BlackjackPlayer p) {
        p.getHand().addAll(deck.draw(1));
        p.calcHandValue();
        System.out.println("You are dealt one card.\n");

        if (p.getHandValue() > 21) {
            p.displayHand();
            System.out.println("You Busted!  --  Next Player's Turn.");
            p.setHandValue(-1);
            return;
        }

        if (p.getHandValue() == 21) {
            p.displayHand();
            System.out.println("!!--BLACKJACK--!!  -- Next Player's Turn. ");
            return;
        }

        displayHands();
        playerPrompt(p);
    }

    public void dealerTurn(BlackjackPlayer p) {
        System.out.println("#####  Dealers turn   ##### \n");
        for (int i = 0; i < this.getPlayers().size() - 1; i++) {
            BlackjackPlayer temp = (BlackjackPlayer) this.getPlayers().get(i);
            if (temp.getHandValue() != -1) {
                break;
            }
            System.out.println("All players have busted Dealer wins.\n");
            return;
        }
        System.out.println("The dealer reveals their hand.");
        System.out.println(p.getName() + " Hand: " + p.getHand().toString());
        p.calcHandValue();

        while (p.getHandValue() < 17) {
            //draw a card (hit)
            p.getHand().addAll(deck.draw(1));
            System.out.println("\nThe dealer hits.");
            //show what the dealer drew
            System.out.println(p.getName() + " Hand: " + p.getHand().toString());
            //check new handValue and if under 17 repeat
            p.calcHandValue();
            if (p.getHandValue() > 21) {
                System.out.println("The dealer hand value is: " + p.getHandValue());
                System.out.println("\nThe dealer busted!");
                return;
            }
            System.out.println("\nThe dealer holds.");
        }

        //Displays the dealer final hand value
        System.out.println("The dealer hand value is: " + p.getHandValue() + "\n");

    }

    public void roundEnd() {
        checkRoundWinner();
        displayScores();
        System.out.print("Press Enter to continue.");
        input.nextLine();
    }

    public void checkRoundWinner() {
        int winIndex = 0;
        int i = 0;
        int highestHand = 0;
        for (Player player : this.getPlayers()) {
            BlackjackPlayer p = (BlackjackPlayer) player;
            p.calcHandValue();

            if (p.getHandValue() > highestHand && p.getHandValue() <= 21) {
                highestHand = p.getHandValue();
                winIndex = i;
            }

            i++;

        }
        BlackjackPlayer winner = (BlackjackPlayer) this.getPlayers().get(winIndex);

        winner.setScoreCount(winner.getScoreCount() + 1);

        System.out.println(this.getPlayers().get(winIndex).getName()
                + " wins the round! +1\n");
    }

    public void displayScores() {
        for (Player player : this.getPlayers()) {
            BlackjackPlayer p = (BlackjackPlayer) player;

            System.out.println(p.getName() + " Score: "
                    + p.getScoreCount());
        }
        System.out.println("");
    }

    public void rematch() {
        boolean goodInput = false;
        int userSelect = 0;

        while (!goodInput) {
            System.out.print("\nEnter 1 to play again, or 2 to quit: ");
            String opt = input.nextLine();
            if (opt.length() > 1 || opt.isBlank()) {
                System.out.println("Enter ONLY 1 or 2");
                continue;
            }

            if (Character.isDigit(opt.charAt(0))) {
                int input = Integer.parseInt(opt);
                if (input == 1 || input == 2) {
                    userSelect = input;
                    goodInput = true;
                }
            } else {
                System.out.println("Enter a 1 or 2 ONLY.");
                continue;
            }
            
            if(userSelect == 1){
                play();
            }
            if(userSelect == 2){
                System.exit(0);
            }

        }
    }

    @Override
    public void declareWinner() {
        BlackjackPlayer winner = null;
        int winningScore = 0;

        for (Player player : this.getPlayers()) {
            BlackjackPlayer p = (BlackjackPlayer) player;
            if (p.getScoreCount() > winningScore) {
                winningScore = p.getScoreCount();
                winner = p;
            }
        }

        System.out.println("\n" + winner.getName() + " has won the game!\n"
                + "With a score of: " + winningScore + "\n");

        displayScores();
        rematch();
    }

}
