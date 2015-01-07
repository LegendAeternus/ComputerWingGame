/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

/**
 *
 * @author Chris
 */
public class Pilot implements Cloneable {
    private String pilotName;
    private int pointCost;
    private int initiative;
    
    
    public Pilot(String pilotName, int pointCost, int initiative){
        this.pilotName = pilotName;
        this.pointCost = pointCost;
        this.initiative = initiative;
    }

    public String getPilotName() {
        return pilotName;
    }

    public int getPointCost() {
        return pointCost;
    }

    public int getInitiative() {
        return initiative;
    }
    
    @Override
    public String toString() {
        return pilotName;
    }
    
    @Override
    public Pilot clone() {
        Pilot copy = new Pilot(pilotName, pointCost, initiative);     
        return copy;
    }
    
}
