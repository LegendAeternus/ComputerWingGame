/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class GameManager {
    
    static ArrayList<Ship> ships = new ArrayList<>();
    static Ship selectedShip;
    
    static public ArrayList<Ship> getShips() {
        return ships;
    }
    
    static public void addShip(Ship s) {
        ships.add(s);
    }
    
    
}
