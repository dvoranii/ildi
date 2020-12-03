/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you
 * might want to subclass this more than once. The group of cards has a maximum
 * size attribute which is flexible for reuse.
 *
 * @author Noah McNabb
 * @author Paul Bonenfant Jan 2020
 */
public class GroupOfCards {

    //The group of cards, stored in an ArrayList
    private ArrayList<Card> cards = new ArrayList<Card>();
    private int size;//the size of the grouping

    public GroupOfCards() {

    }

    public GroupOfCards(int size) {
        this.size = size;
    }

    /**
     * Sets the size of the deck to 52 and fills it with all the cards of a
     * standard 52 card playing deck modeled by {@link CardFor52Deck}.
     */
    public void init52Deck() {
        this.setSize(52);

        for (CardFor52Deck.Suit suit : CardFor52Deck.Suit.values()) {
            for (CardFor52Deck.Value value : CardFor52Deck.Value.values()) {
                CardFor52Deck card = new CardFor52Deck(value, suit);
                this.cards.add(card);
            }

        }

    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void printCards() {
        for (Card c : this.getCards()) {
            System.out.println(c.toString());
        }
    }

    /**
     * Removes the first (num) of Cards from this GroupOfCards and returns them
     * as an ArrayList.
     *
     * @param num - the number of cards to be drawn
     * @return An ArrayList containing the drawn card objects
     */
    public ArrayList<Card> draw(int num) {
        int i = 0;
        ArrayList<Card> drawn = new ArrayList<Card>();

        while (i < num) {
            drawn.add(this.getCards().get(0));
            this.getCards().remove(0);
            i++;
        }
        return drawn;
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the max size for the group of cards
     */
    public void setSize(int size) {
        this.size = size;
    }

}//end class
