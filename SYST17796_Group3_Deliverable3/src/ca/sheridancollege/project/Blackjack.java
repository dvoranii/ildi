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

        playerList.add(dealer);
        playerList.add(player);

        this.setPlayers(playerList);
    }

    @Override
    public void play() {
        System.out.println("Welcome to the Game!");
        //creates deck
        deck = new GroupOfCards();
        //makes deck a standrad 52 card deck
        deck.init52Deck();
        //shuffles deck
        deck.shuffle();
        //deals 2 to each player
        startingDeal();
        //shows the first deal
        displayHands();
        //Goes through every p[layer in the game to prompt them to hit or stand
        for (Player player : this.getPlayers()) {
            BlackjackPlayer p = (BlackjackPlayer) player;

            if (!p.getName().equals("Dealer")) {
                playerPrompt(p);
            }
        }

    }
    /**
     * Displays the hands off all players in the game including Dealer.
     * Dealer hand displays the first card as UNKNOWN
     */
    public void displayHands() {
        //Displays player hands
        for (Player player : this.getPlayers()) {
            BlackjackPlayer p = (BlackjackPlayer) player;

            if (p.getName().equals("Dealer")) {
                System.out.print(p.getName() + "Hand: UNKNOWN + ");
                for (int i = 1; i < p.getHand().size(); i++) {
                    System.out.print(p.getHand().get(i).toString());
                }
                System.out.println("");
            } else {
                System.out.println(p.getName() + " Hand: " + p.getHand().toString());
            }

        }
    }

    public void startingDeal() {
        //Deal 2 cards to every player
        for (Player player : this.getPlayers()) {
            BlackjackPlayer p = (BlackjackPlayer) player;
            p.setHand(deck.draw(2));
        }
    }
    /**
     * Prompts the player whether to hit or stand.
     * If hit is selected hit() is called which in turn recalls PlayerPrompt().
     * If stand is selected
     * @param p - the BlackjackPlayer that is being prompted to play.
     */
    public void playerPrompt(BlackjackPlayer p) {

        System.out.print("Enter 1 to hit or 2 to hold: ");
        int playerChoice = input.nextInt();

        if (playerChoice == 1) {
            hit(p);
        } else if (playerChoice == 2) {
            
        }

    }

    /**
     * Draws one card form deck into the player hand.
     * After drawing the card it then displays all players hands and prompts
     * player p again.
     * @param p - the BlackjackPlayer this has selected to hit
     */
    public void hit(BlackjackPlayer p) {
        p.getHand().addAll(deck.draw(1));
        displayHands();
        playerPrompt(p);
    }

    @Override
    public void declareWinner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
