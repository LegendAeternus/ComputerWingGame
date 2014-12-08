/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;


/**
 *
 * @author Michael
 */
public class Ship {
    
    Point location;
    Double orientation;
    
    double maxHull, maxShields;
    double hull, shields;
    double attack, evade;
    double sideLength;
    
    Rectangle2D.Double base = new Rectangle2D.Double();
    Shape baseDraw;
    
    public Ship(Point startingPoint, double startingOrientationDeg, double length) {
        location = startingPoint;
        orientation = startingOrientationDeg;
        sideLength = length;
        
        updateRect();
        
    }
    
    

    /** Applies a given maneuver to this ship
     *
     */
    public boolean executeManevuer() {
        return false;
        
    }
    
    public void setLocation(Point p) {
        location = p;
        updateRect();
    }
    public void setOrientation(double deg) {
        orientation = deg;
        updateRect();
    }
    public void rotate(double deg) {
        orientation += deg;
        if(orientation > 360) {
            orientation = orientation%360;
        } else if (orientation < 0) {
            orientation=orientation%-360;
            orientation+=360;
        }
        updateRect();

    }
    
    public boolean checkForShipCollision() {
        
        for(Ship s: GameManager.getShips()) {
            if(!s.equals(this)) {
                Area s1 = new Area(baseDraw);
                Area s2 = new Area(s.baseDraw);
                s1.subtract(s2);
                if(!s1.equals(new Area(baseDraw))) {
                    System.out.println("COLISION");
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private void updateRect() {
        base.setRect(0,0,sideLength, sideLength);
        AffineTransform at = new AffineTransform();
        at.translate(location.x, location.y);
        at.rotate(Math.toRadians(orientation),sideLength/2d,sideLength/2d);
        baseDraw = at.createTransformedShape(base);
    }
    
    
}
