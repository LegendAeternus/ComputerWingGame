/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class RangeArea {
    
    
    Area range1;
    Area range2;
    Area range3;
    
    Area arc;
    
    double range1Dist = 100;
    double range2Dist = 200;
    double range3Dist = 300;
    
    
    Ship ship;
    
    public Area createRangeShape(double dist) {
        
        double halfSide = ship.sideLength/2d;
        Rectangle2D.Double top = new Rectangle2D.Double(-halfSide, halfSide, ship.sideLength, dist);
        Rectangle2D.Double bot = new Rectangle2D.Double(-halfSide, -halfSide-dist, ship.sideLength, dist);
        Rectangle2D.Double right = new Rectangle2D.Double(halfSide, -halfSide, dist, ship.sideLength);
        Rectangle2D.Double left = new Rectangle2D.Double(-halfSide - dist, -halfSide, dist, ship.sideLength);
        Area shape = new Area(top);
        shape.add(new Area(right));
        shape.add(new Area(bot));
        shape.add(new Area(left));
        
        Ellipse2D.Double leftTop = new Ellipse2D.Double(-halfSide - dist, halfSide - dist, dist*2, dist*2);
        Ellipse2D.Double rightTop = new Ellipse2D.Double(halfSide - dist, halfSide - dist, dist*2, dist*2);
        Ellipse2D.Double leftBot = new Ellipse2D.Double(-halfSide - dist, -halfSide - dist, dist*2, dist*2);
        Ellipse2D.Double rightBot = new Ellipse2D.Double(halfSide - dist, -halfSide - dist, dist*2, dist*2);
        
        Area lt = new Area(leftTop);
        Area rt = new Area(rightTop);
        Area lb = new Area(leftBot);
        Area rb = new Area(rightBot);
        
        Area base = new Area(new Rectangle2D.Double(-halfSide,-halfSide,ship.sideLength,ship.sideLength));
        lt.subtract(base);
        rt.subtract(base);
        lb.subtract(base);
        rb.subtract(base);
        
        
        shape.add(lt);
        shape.add(rt);
        shape.add(lb);
        shape.add(rb);
        

        return shape;
        
    }
    
    public RangeArea(Ship s) {
        ship = s;
        range1 = createRangeShape(range1Dist);
        range2 = createRangeShape(range2Dist);
        range3 = createRangeShape(range3Dist);

    }
    
    public void draw(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
        g.setColor(new Color(0f,.8f,0f,.3f));
        AffineTransform at = new AffineTransform();
        at.translate(ship.location.x, ship.location.y);
        at.rotate(Math.toRadians(ship.orientation));
        
        arc = getArc();
        
        
        Area r1 = (Area)range1.clone();
        r1.subtract(arc);
        g2D.fill(at.createTransformedShape(r1));
        Area r2 = (Area)range2.clone();
        r2.subtract(arc);
        g2D.fill(at.createTransformedShape(r2));
        Area r3 = (Area)range3.clone();
        r3.subtract(arc);
        g2D.fill(at.createTransformedShape(r3));
        g.setColor(new Color(1f,0f,0f,.3f));
        
        Area a1 = (Area)arc.clone();
        a1.intersect(range1);
        Area a2 = (Area)arc.clone();
        a2.intersect(range2);
        Area a3 = (Area)arc.clone();
        a3.intersect(range3);
        g2D.fill(at.createTransformedShape(a1));
        g2D.fill(at.createTransformedShape(a2));
        g2D.fill(at.createTransformedShape(a3));

        
    }
    
    
        public Area getArc() {
            Point l = new Point(ship.sideLength/2d,ship.arcWidth/2d);
            Point r = new Point(ship.sideLength/2d,-ship.arcWidth/2d);
            l = l.multiply(range3Dist/l.length()*2);
            r = r.multiply(range3Dist/r.length()*2);
            
            // draw GeneralPath (polygon)
            double x1Points[] = {0,l.x,r.x};
            double y1Points[] = {0,l.y,r.y};
            GeneralPath polygon =  new GeneralPath(GeneralPath.WIND_EVEN_ODD,  x1Points.length);
            polygon.moveTo(x1Points[0], y1Points[0]);

            for(int index = 1; index < x1Points.length; index++) {
                    polygon.lineTo(x1Points[index], y1Points[index]);
            };
            polygon.closePath();
            
            Area arc = new Area(polygon);
            
            
            if(ship.secondaryArc) {
                Point ls = new Point(-ship.sideLength/2d,ship.arcWidth/2d);
                Point rs = new Point(-ship.sideLength/2d,-ship.arcWidth/2d);
                ls = ls.multiply(range3Dist/ls.length()*2);
                rs = rs.multiply(range3Dist/rs.length()*2);

       
                // draw GeneralPath (polygon)
                double x1sPoints[] = {0,ls.x,rs.x};
                double y1sPoints[] = {0,ls.y,rs.y};
                GeneralPath polygons =  new GeneralPath(GeneralPath.WIND_EVEN_ODD,  x1Points.length);
                polygons.moveTo(x1sPoints[0], y1sPoints[0]);

                for(int index = 1; index < x1sPoints.length; index++) {
                polygons.lineTo(x1sPoints[index], y1sPoints[index]);
                };
                polygons.closePath();

                Area arcs = new Area(polygons);

                arc.add(arcs);
                
            }
            

            
            
            
            
            
            
            arc.intersect(range3);
            
            return arc;
        }

            
}
