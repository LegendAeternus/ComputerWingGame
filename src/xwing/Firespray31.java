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
public class Firespray31 extends LargeShip {
    
    
    public Firespray31(double Xmm, double Ymm, double orientDeg, int team) {
        super(Xmm, Ymm, orientDeg, team);
        ShipName = "Firespray-31";
        secondaryArc = true;
    }
    
    public static ArrayList<Pilot> getPossiblePilots() {
        
        possiblePilots = new ArrayList<>();
        possiblePilots.add(new Pilot("Boba Fett",39,8));
        possiblePilots.add(new Pilot("Kath Scarlett",38,7));
        possiblePilots.add(new Pilot("Krassis Trelix",36,5));
        possiblePilots.add(new Pilot("Bounty Hunter",33,3));

        return possiblePilots;
        
    }
    
    public static int getBaseHull() {
        baseHull = 6;
        return baseHull;
    }

    public static int getBaseShields() {
        baseShields = 4;
        return baseShields;
    }

    public static int getBaseAttack() {
        baseAttack = 3;
        return baseAttack;
    }

    public static int getBaseEvade() {
        baseEvade = 2;
        return baseEvade;
    }
    
}
