/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


/**
 *
 * @author Michael
 */
public class Ship {
    
    Point location;
    Double orientation;
    
    protected Pilot pilot;
    protected static ArrayList<Pilot> possiblePilots;
    
    boolean secondaryArc;
    boolean turret;
    
    
    double arcWidth;
    protected static int baseHull, baseShields;
    protected int hull, shields;
    protected static int baseAttack, baseEvade;
    double sideLength;
    
    String upgrades;
    String ShipName = "No Name";
    
    int team;
    
    //=================
    // Render Vars
    //=================
    boolean showRange;
    Rectangle2D.Double base = new Rectangle2D.Double();
    Shape baseDraw;
    
    public Ship(Point startingPoint, double startingOrientationDeg, double length, int team, double arcWidth) {
        location = startingPoint;
        orientation = startingOrientationDeg;
        sideLength = length;
        this.arcWidth = arcWidth;
        this.team = team;
        hull = this.getBaseHull();
        shields = this.getBaseShields();
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
        
        for(Ship s: GameManager.getAllShips()) {
            if(!s.equals(this)) {
                Area s1 = new Area(baseDraw);
                Area s2 = new Area(s.baseDraw);
                s1.subtract(s2);
                if(!s1.equals(new Area(baseDraw))) {
                    //System.out.println("COLISION");
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private void updateRect() {
        base.setRect(0,0,sideLength, sideLength);
        AffineTransform at = new AffineTransform();
        at.translate(location.x-sideLength/2d, location.y-sideLength/2d);
        at.rotate(Math.toRadians(orientation),sideLength/2d,sideLength/2d);
        baseDraw = at.createTransformedShape(base);
    }
    
    public void draw(Graphics g) {
       Graphics2D g2D = (Graphics2D) g;
       if(team == 1) {
           g2D.setColor(new Color(50,0,0));
       } else {
           g2D.setColor(new Color(0,0,50));
       }
       g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       try {
        g2D.fill(baseDraw);
        drawArc(g);
        g2D.setColor(Color.WHITE);
        Stroke prev = g2D.getStroke();
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2D.setStroke(dashed);
        g2D.draw(baseDraw);
        g2D.setStroke(prev);


        
        g2D.setColor(Color.WHITE);
        g2D.drawString(pilot.getPilotName(), (int)location.x, (int)location.y);
        
        

       } catch (java.lang.NullPointerException ex) {
           
        }
    }
       
    public void drawArc(Graphics g) {
       Graphics2D g2D = (Graphics2D) g;
        Stroke prev = g2D.getStroke();
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2D.setStroke(dashed);
        
        
        g.setColor(Color.RED);
        Point lineL = new Point(sideLength/2d,arcWidth/2d);
        Point lineR = new Point(sideLength/2d,-arcWidth/2d);
        Point arcSideL = lineL.rotate(orientation);
        Point arcSideR = lineR.rotate(orientation);
        g.drawLine((int)location.x, (int)location.y, (int) (location.x + arcSideL.x),(int) (location.y + arcSideL.y));
        g.drawLine((int)location.x, (int)location.y, (int) (arcSideR.add(location)).x,(int) (arcSideR.add(location)).y);
        g2D.setStroke(prev);
        
        if(secondaryArc)
            drawBackArc(g);
   }
    
    public void drawBackArc(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        Stroke prev = g2D.getStroke();
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
        g2D.setStroke(dashed);
        g2D.setColor(Color.RED);
        Point lineL = new Point(sideLength/2d,arcWidth/2d);
        Point lineR = new Point(sideLength/2d,-arcWidth/2d);
        Point arcSideL = lineL.rotate(orientation);
        Point arcSideR = lineR.rotate(orientation);
        g2D.drawLine((int)location.x, (int)location.y, (int) (location.x - arcSideL.x),(int) (location.y - arcSideL.y));
        g2D.drawLine((int)location.x, (int)location.y, (int) (location.subtract(arcSideR)).x,(int) (location.subtract(arcSideR)).y);
        g2D.setStroke(prev);
    }
    
    public void drawRange(Graphics g) {
        
        Area firstCorner = new Area();

    }

    public static ArrayList<Pilot> getPossiblePilots() {
        return possiblePilots;
    }

    public static int getBaseHull() {
        return baseHull;
    }

    public static int getBaseShields() {
        return baseShields;
    }

    public int getHull() {
        return hull;
    }

    public int getShields() {
        return shields;
    }

    public static int getBaseAttack() {
        return baseAttack;
    }

    public static int getBaseEvade() {
        return baseEvade;
    }
    
    public int getPointCost() {
        if(pilot != null) {
            return pilot.getPointCost();
        } else {
            return -1;
        }
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }
    
    void initialize() {
        hull = getBaseHull();
        shields = getBaseShields();

    }
    
    public Point getLocation() {
        return location;
    }

}
