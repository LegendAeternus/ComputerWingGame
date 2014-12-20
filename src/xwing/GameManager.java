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
    
    static NetworkedGame onlineGame;
    
    static ArrayList<Ship> playerSquadron = new ArrayList<>();
    static ArrayList<Ship> opponentSquadron = new ArrayList<>();
    static Ship selectedShip;
    static Manuever ManueverToExecute;
    static Ship listSelectedShip;
    
    enum GamePhase {
        MainMenu,
        Movement,
        Attack,
        Actions,
        SquadBuilding
    }
    
    static GamePhase curPhase = GamePhase.MainMenu;
    
    static public ArrayList<Ship> getShips() {
        return playerSquadron;
    }
    
    static public void addShip(Ship s) {
        playerSquadron.add(s);
    }
    
    
}
