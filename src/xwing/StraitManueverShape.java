/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

/**
 *
 * @author Michael
 */
public class StraitManueverShape extends ManueverShape{
   
    public StraitManueverShape(double fowardDistmm) {
        this.fowardDistmm = fowardDistmm;
        pathLength = fowardDistmm;
        this.sideDistmm = 0;
        this.rotationDeg = 0;
    }

    @Override
    public Point getPositionAtPercent(double percent) {
        return new Point(0.0,percent/100.0 * fowardDistmm);
    }
}
