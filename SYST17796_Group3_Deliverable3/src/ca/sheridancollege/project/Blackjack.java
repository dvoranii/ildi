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
        System.out.println("Welcome to the Game!\n\n");
        //creates deck
        deck = new GroupOfCards();
        int i = 0;
        while (i < 5) {
            System.out.println("Begin Round " + (i + 1) + "\n");
            //makes deck a standrad 52 card deck
            deck.init52Deck();
            //shuffles deck
            deck.shuffle();
            //deals 2 to each player
            System.out.println("The dealer deals 2 cards to each player.\n");
            startingDeal();
            //shows the first deal
            displayHands();
            //Goes through every p[layer in the game to prompt them to hit or stand
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
        System.out.println("Game Over!");
        displayScores();
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

        System.out.print("Enter 1 to hit or 2 to hold: ");
        int playerChoice = input.nextInt();

        if (playerChoice == 1) {
            hit(p);
        } else if (playerChoice == 2) {
            //continues to the to prompt the next player in playerList
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
            System.out.println("You Busted! -- Next Player's Turn.");
            p.setHandValue(0);
            return;
        }

        displayHands();
        playerPrompt(p);
    }

    public void dealerTurn(BlackjackPlayer p) {
        System.out.println("Dealers turn \n");
        System.out.println("The dealer reveals their hand.");
        System.out.println(p.getName() + " Hand: " + p.getHand().toString());
        p.calcHandValue();

        while (p.getHandValue() < 17) {
            //draw a card (hit)
            p.getHand().addAll(deck.draw(1));
            System.out.println("The dealer hits.");
            //show what the dealer drew
            System.out.println(p.getName() + " Hand: " + p.getHand().toString());
            //check new handValue and if under 17 repeat
            p.calcHandValue();
        }
        //Displays the dealer final hand value
        System.out.println("The dealer hand value is: " + p.getHandValue());

    }

    public void roundEnd() {
        checkRoundWinner();
        displayScores();
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

        System.out.println(this.getPlayers().get(winIndex).getName());
    }

    public void displayScores() {
        for (Player player : this.getPlayers()) {
            BlackjackPlayer p = (BlackjackPlayer) player;

            System.out.println(p.getName() + " Score: " + p.getScoreCount());
        }
    }

    @Override
    public void declareWinner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
