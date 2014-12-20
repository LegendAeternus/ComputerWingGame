/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import static xwing.GameManager.GamePhase.SquadBuilding;

/**
 *
 * @author Michael
 */
public class GameMap extends javax.swing.JPanel {
   
    
    ShipList l;
    Point size = new Point(914.4,914.4);
    double scale = 1;
    
    Ship newShip;
    Point pref;
    
    
    ArrayList<Ship> ships = new ArrayList<>();
    
    /**
     * Creates new form GameMap
     */
    public GameMap() {
        initComponents();
    }
   
    
   @Override
    protected void paintComponent(Graphics g) {
       drawBackground(g);
       
        if(null != GameManager.selectedShip) {
            System.out.println("Here");
           RangeArea area = new RangeArea(GameManager.selectedShip);
           area.draw(g);
       }
       
       for(Ship s: GameManager.getShips()) {
           s.draw(g);
       }
       


    }

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        java.awt.geom.Point2D.Double point = new java.awt.geom.Point2D.Double(evt.getX(),evt.getY());
        
        
        switch(GameManager.curPhase) {
            
            case SquadBuilding:
                break;
            case Movement:
                for(Ship s: GameManager.playerSquadron) {
                    if (s.baseDraw.contains(point)) {
                        GameManager.selectedShip = s;
                        return;
                    }
                }
                GameManager.selectedShip = null;
                break;
            default:
                break;
        }
        
     
        
        
    }//GEN-LAST:event_formMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
            
        java.awt.geom.Point2D.Double point = new java.awt.geom.Point2D.Double(evt.getX(),evt.getY());

        switch(GameManager.curPhase) {
            
            case SquadBuilding:
                newShip = new LargeShip((double)evt.getX(),(double)evt.getY(),0,1);
                GameManager.addShip(newShip);
                newShip.orientation = 95d;
                pref = new Point(evt.getX(),evt.getY());
                break;
            case Movement:
            default:
                break;
        }
        
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        switch(GameManager.curPhase) {
            
            case SquadBuilding:
                System.out.println("Dragged");
                if(newShip!=null) {
                    Point cur = new Point(evt.getX(),evt.getY());
                    Point diff = cur.subtract(pref);
                    double orient = Math.toDegrees(Math.atan2(diff.y, diff.x));
                    newShip.setOrientation(orient);
                    newShip.orientation = orient;
                    System.out.println(newShip.orientation);
                    System.out.println("Dragged Not Null :: " + orient);
                }
                break;
            case Movement:
            default:
                break;
        }
        

    }//GEN-LAST:event_formMouseDragged

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        System.out.println(newShip.orientation);
        l.loadList();
        l.setSelectedIndex(GameManager.playerSquadron.indexOf(newShip));
        newShip = null;
    }//GEN-LAST:event_formMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void drawBackground(Graphics g) {
        int width = (int)(size.x * scale);
        int height = (int)(size.y * scale);
        this.setSize(width,height);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    }
}
