/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author Noah
 */
public class main {

    public static void main(String[] args) {

        GroupOfCards deck = new GroupOfCards();
        deck.init52Deck();
        
        BlackjackPlayer user = new BlackjackPlayer("Player 1");
        
        user.setHand(deck.draw(5));
        
        System.out.println("Player 1 Hand: " + user.getHand().toString());
        user.calcHandValue();
        System.out.println("Player 1 Hand Value: " + user.getHandValue());
        user.resetHand();
        
        System.out.println(user.getHand().toString());
    }
}
