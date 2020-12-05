/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

/**
 *
 * @author Noah
 */
public class CardFor52Deck extends Card {

    private Value value;
    private Suit suit;

    public enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS
    };

    public enum Value {
        ACE(11), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);

        private final int cardVal;

        private Value(int cardVal) {
            this.cardVal = cardVal;
        }

        public int getCardVal() {
            return this.cardVal;
        }

    };

    public CardFor52Deck(Value value, Suit suit) {
        setValue(value);
        setSuit(suit);
    }

    @Override
    public String toString() {
        return this.getValue().name() + " of " + this.getSuit().name();
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

}
