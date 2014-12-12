/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Michael
 */
public class Manuever {
    
    ManueverShape shape;
    int manueverDirection;
    boolean kTurn;
    boolean animation = true;
    double animDuration = 2;
    int animFPS = 60;
    
    
    public Manuever(ManueverShape shape, int direction) {
        this.shape = shape;
        this.manueverDirection = direction;
    }
    
    
    public ManueverOutcome apply(Ship ship) {
       
        ManueverOutcome outcome = new ManueverOutcome();
        
        
        Point originalCoords = ship.location;
        Double originalOrientation = ship.orientation;
        
        int totalFrames = (int)(animDuration * (double)animFPS);
        int waitMillis = (int)(1000d/(double)animFPS);
        for(int f=0;f<=totalFrames;f++) {
            double pct = (double)f/(double)totalFrames * 100d;
            try {
                Thread.sleep(waitMillis);
            } catch (InterruptedException ex) {
                Logger.getLogger(Manuever.class.getName()).log(Level.SEVERE, null, ex);
            }
            ship.setLocation(originalCoords);
            ship.setOrientation(originalOrientation);
            transformShipPositionAndRotation(pct, ship, shape, manueverDirection);
        }
        
        
        if(ship.checkForShipCollision() == false) {
            if(kTurn)
                ship.rotate(180);
            return outcome;
        }
        
        outcome.collidedWithShip = true;
        
        double increment = -.1;
        double percent = 100;
        while(ship.checkForShipCollision() && percent >=0) {
            percent += increment;
            ship.setLocation(originalCoords);
            ship.setOrientation(originalOrientation);
            
            transformShipPositionAndRotation(percent, ship, shape, manueverDirection);
        }
       
        
        
        return outcome;
    }
    
    public void transformShipPositionAndRotation(double percent, Ship ship, ManueverShape shape, int direction) {
        
        Point front = getFrontBasePositionAtPercent(percent,ship,shape);
        Point back = getBackBasePositionAtPercent(percent,ship,shape);
        
        Point center = front.add(back).divide(2);
        
        center.x = center.x*(double)direction;
        Point centerCorrect = new Point(center.y, -center.x);
        
        double angle = Math.toDegrees(Math.atan2(front.x-back.x,front.y-back.y));
        
        ship.setLocation(centerCorrect.rotate(ship.orientation).add(ship.location));
        ship.rotate(angle * -(double)direction);

    }
    
    public Point getBackBasePositionAtPercent(double percent, Ship ship, ManueverShape shape) {
                
        double totalPathDist = shape.getPathLength() + ship.sideLength; //Path length of front and back;
        
        double dist = percent/100 * totalPathDist;
        
        Point pointBack;
        double shapePctBack = (dist-ship.sideLength) / shape.pathLength;
        if(shapePctBack > 0) {
            Point shapePoint = shape.getPositionAtPercent(shapePctBack*100d);
            pointBack = new Point(shapePoint.x, shapePoint.y + ship.sideLength/2d);
        } else {
            pointBack = new Point(0, dist - ship.sideLength/2d);
        }
        return pointBack;
        
    }
    public Point getFrontBasePositionAtPercent(double percent, Ship ship, ManueverShape shape) {
        
        double totalPathDist = shape.getPathLength() + ship.sideLength; //Path length of front and back;
        
        double dist = percent/100 * totalPathDist;
        
        //---------------------------------------
        //  Position of front center of ship base
        //---------------------------------------
        Point pointFrnt;
        double shapePctFrnt = dist / shape.pathLength;
        if(shapePctFrnt < 1) {
            pointFrnt = shape.getPositionAtPercent(shapePctFrnt*100d);
            pointFrnt.y+= ship.sideLength/2d;
        } else {

            Point curveComplete = shape.getPositionAtPercent(100);
            double extraDist = dist - shape.pathLength;
            double extraFoward = extraDist * Math.cos(Math.toRadians(shape.getRotationDeg()));
            double extraSide = extraDist * Math.sin(Math.toRadians(shape.getRotationDeg()));
            
            pointFrnt = new Point(curveComplete.x + extraSide, curveComplete.y + extraFoward + ship.sideLength/2d);
        }
        return pointFrnt;

    }
    
}
