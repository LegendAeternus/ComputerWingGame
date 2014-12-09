/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

/**
 *
 * @author Michael
 */
public class NormalShip extends Ship{
    
    public NormalShip(double Xmm, double Ymm, double orientDeg, int team) {
        super(new Point(Xmm, Ymm), orientDeg, 39.9, team, 34);
    }
    
    
}
