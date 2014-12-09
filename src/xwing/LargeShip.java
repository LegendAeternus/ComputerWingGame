/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

/**
 *
 * @author Michael
 */
public class LargeShip extends Ship{
    
    public LargeShip(double Xmm, double Ymm, double orientDeg, int team) {
        super(new Point(Xmm, Ymm), orientDeg, 79.9, team, 72);
    }
    
}
