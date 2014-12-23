/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class TieFighter extends NormalShip {
    
    
    public TieFighter(double Xmm, double Ymm, double orientDeg, int team) {
        super(Xmm, Ymm, orientDeg, team);
    }
    
    public static ArrayList<Pilot> getPossiblePilots() {
        
        possiblePilots = new ArrayList<>();
        possiblePilots.add(new Pilot("Academy Pilot",12,1));
        possiblePilots.add(new Pilot("Obsidian Squadron Pilot",13,3));
        return possiblePilots;
        
    }
    
    public static int getBaseHull() {
        baseHull = 3;
        return baseHull;
    }

    public static int getBaseShields() {
        baseShields = 0;
        return baseShields;
    }

    public static int getBaseAttack() {
        baseAttack = 2;
        return baseAttack;
    }

    public static int getBaseEvade() {
        baseEvade = 3;
        return baseEvade;
    }
    
}
