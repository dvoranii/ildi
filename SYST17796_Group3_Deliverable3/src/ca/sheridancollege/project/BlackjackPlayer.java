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
public class BlackjackPlayer extends Player{
    private ArrayList<Card> hand = new ArrayList<>();
    
    private int handValue, scoreCount;
    
    public BlackjackPlayer(String name){
        super(name);
    }
    
    public void resetHand(){
        this.hand.clear();
    }
    
    public void calcHandValue(){
        this.handValue = 0;
        //Adds up the total Value of the Hand
        for(Card c : this.hand){
            CardFor52Deck d = (CardFor52Deck)c;
            this.handValue = handValue + d.getValue().getCardVal();
        }
        //Handles changing Aces to have a value of 1 instead of 11 to avoid bust
        if(handValue > 21){
            for(Card c : this.hand){
                CardFor52Deck d = (CardFor52Deck)c;
                if(d.getValue() == CardFor52Deck.Value.ACE && handValue > 21){
                    this.handValue = handValue -10;
                }
            }
        }
    }
    
    @Override
    public void play() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int getHandValue() {
        return handValue;
    }

    public void setHandValue(int handValue) {
        this.handValue = handValue;
    }

    public int getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(int scoreCount) {
        this.scoreCount = scoreCount;
    }
    
    
    
}
