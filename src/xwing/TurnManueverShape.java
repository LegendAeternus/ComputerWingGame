/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

/**
 *
 * @author Michael
 */
public class TurnManueverShape extends ManueverShape {
       
    private double turnRadius;
        
    public TurnManueverShape(double fowardDistmm, double sideDistmm, double rotationDeg) {
        
        this.fowardDistmm = fowardDistmm;
        this.sideDistmm = sideDistmm;
        this.rotationDeg = rotationDeg;
        
        calcRadius();
    }
    
    public void calcRadius() {
         turnRadius = fowardDistmm/Math.sin(Math.toRadians(rotationDeg));
         pathLength = turnRadius * 2 * Math.PI * rotationDeg/360.0;
    }
    public double getTurnRadius() {
        return turnRadius;
    }
    @Override
    public Point getPositionAtPercent(double percent) {
        double angle = percent/100.0 * this.rotationDeg;
        double fowardDist = Math.sin(Math.toRadians(angle)) * turnRadius;
        double sideDist = turnRadius - Math.cos(Math.toRadians(angle)) * turnRadius;
        
        return new Point(sideDist, fowardDist);
    }
}
