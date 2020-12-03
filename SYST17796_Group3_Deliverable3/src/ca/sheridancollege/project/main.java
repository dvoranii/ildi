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
        
        deck.printCards();
        
    }
}
