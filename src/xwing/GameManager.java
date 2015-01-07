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
    
    static boolean squadChanged = true;
    
    enum GamePhase {
        MainMenu,
        SquadPlacement,
        Movement,
        Attack,
        Actions,
        SquadBuilding
    }
    
    enum PlayerNum {
        Player,
        Opponent
        
    }
    
    enum Faction {
        Rebels,
        Imperials,
        Scum
    }
    
    static GamePhase curPhase = GamePhase.MainMenu;
    
    static public ArrayList<Ship> getPlayerShips() {
        return playerSquadron;
    }
    
    static public ArrayList<Ship> getOpponentShips() {
        return opponentSquadron;
    }
    
    static public ArrayList<Ship> getAllShips() {
        ArrayList<Ship> combined = new ArrayList<>();
        combined.addAll(playerSquadron);
        combined.addAll(opponentSquadron);
        return combined;
    }
    
    static public void addShip(Ship s) {
        playerSquadron.add(s);
    }
    
    static public void resetShips() {
        
        for(Ship s: playerSquadron) {
            s.initialize();
        }
        for(Ship s: opponentSquadron) {
            s.initialize();
        } 
    }
    
    static public Ship getLowestUnplacedShip() {
        
        int minSkill = 100;
        int minIndex = 0;
        PlayerNum minTeam = PlayerNum.Opponent;
        
        for(int i=0; i<opponentSquadron.size(); i++) {
            if(opponentSquadron.get(i).getPilot().getInitiative() < minSkill) {
                Point position = opponentSquadron.get(i).getLocation();
                if(position.x == 0 && position.y == 0) {
                    minSkill = opponentSquadron.get(i).getPilot().getInitiative();
                    minIndex = i;
                    minTeam = PlayerNum.Opponent;
                }
            }
        }
        for(int i=0; i<playerSquadron.size(); i++) {
            if(playerSquadron.get(i).getPilot().getInitiative() < minSkill) {
                Point position = playerSquadron.get(i).getLocation();
                if(position.x == 0 && position.y == 0) {
                    minSkill = playerSquadron.get(i).getPilot().getInitiative();
                    minIndex = i;
                    minTeam = PlayerNum.Player;
                }
            }
        }
        
        if(minSkill != 100) {
            if(minTeam == PlayerNum.Player) {
                return playerSquadron.get(minIndex);
            } else {
                return opponentSquadron.get(minIndex);
            }
        }
        return null;
    }
    
    
}
