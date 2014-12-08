/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

/**
 *
 * @author Michael
 */
public class Point {
    
    double x,y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Point add(Point p){
        return new Point(x+p.x,y+p.y);
    }
    public Point subtract(Point p){
        return new Point(x-p.x,y-p.y);
    }
    public Point divide(double amount) {
        return new Point(x/amount,y/amount);
    }
    public Point multiply(double amount) {
        return new Point(x*amount,y*amount);
    }
    
    public Point rotate(double angleDeg) {
        double yT = Math.sin(Math.toRadians(angleDeg)) * y - Math.cos(Math.toRadians(angleDeg)) * x;
        double xT = Math.cos(Math.toRadians(angleDeg)) * y + Math.sin(Math.toRadians(angleDeg)) * x;
        
        return new Point(xT,yT);
    }
    
}
