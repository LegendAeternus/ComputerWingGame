/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

/**
 *
 * @author Michael
 */
public abstract class ManueverShape {
    
    protected double pathLength;
    protected double fowardDistmm;
    protected double sideDistmm;
    protected double rotationDeg;
    
    
    
    public abstract Point getPositionAtPercent(double percent);
    

    public double getPathLength() {
        return pathLength;
    }
    public double getFowardDistmm() {
        return fowardDistmm;
    }
    public double getSideDistmm() {
        return sideDistmm;
    }
    public double getRotationDeg() {
        return rotationDeg;
    }
}
