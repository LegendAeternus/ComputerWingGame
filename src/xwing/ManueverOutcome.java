/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

/**Describes various possible outcomes of a maneuver
 *
 * @author Michael
 */
public class ManueverOutcome {
    
    boolean throughAsteroid;
    boolean endedOnAsteroid;
    boolean collidedWithShip;
    boolean leftMap;
    
    public boolean wentThroughAsteroid() {
        return throughAsteroid;
    }
    public boolean landedOnAsteroid() {
        return endedOnAsteroid;
    }
    public boolean collidedWithAShip() {
        return collidedWithShip;
    }
    public boolean leftMap() {
        return leftMap;
    }
    
}
