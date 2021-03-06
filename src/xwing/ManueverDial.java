/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Michael
 */
public class ManueverDial extends javax.swing.JPanel {

    BufferedImage template;
    ManueverShape moveMap[][] = new ManueverShape[6][5];
    
    
    /**
     * Creates new form ManueverDial
     */
    public ManueverDial() {
        initComponents();
        createMoveMap();
        try {
            template = ImageIO.read(new File("resources" + System.getProperty("file.separator") + "xwing-manuevers.png"));
        } catch (IOException e) {
            System.out.println("Unable to load template image");
        }
        
        
    }
    
   @Override
    protected void paintComponent(Graphics g) { 
       g.drawImage(template, 0,0,this.getWidth(), this.getHeight(), null);
    }
    
   
   private void createMoveMap() {

        moveMap[0][0] = new OneTurn();
        moveMap[1][0] = new OneBank();
        moveMap[2][0] = new OneStrait();
        moveMap[3][0] = new OneBank();
        moveMap[4][0] = new OneTurn();
        moveMap[5][0] = new OneStrait();
        moveMap[0][1] = new TwoTurn();
        moveMap[1][1] = new TwoBank();
        moveMap[2][1] = new TwoStrait();
        moveMap[3][1] = new TwoBank();
        moveMap[4][1] = new TwoTurn();
        moveMap[5][1] = new TwoStrait();
        moveMap[0][2] = new ThreeTurn();
        moveMap[1][2] = new ThreeBank();
        moveMap[2][2] = new ThreeStrait();
        moveMap[3][2] = new ThreeBank();
        moveMap[4][2] = new ThreeTurn();
        moveMap[5][2] = new ThreeStrait();
        //moveMap[0][3] = new OneTurn();
        //moveMap[1][3] = new OneTurn();
        moveMap[2][3] = new FourStrait();
        //moveMap[3][3] = new OneTurn();
        //moveMap[4][3] = new OneTurn();
        moveMap[5][3] = new FourStrait();
        //moveMap[0][4] = new OneTurn();
        //moveMap[1][4] = new OneTurn();
        moveMap[2][4] = new FiveStrait();
        //moveMap[3][4] = new OneTurn();
        //moveMap[4][4] = new OneTurn();
        moveMap[5][4] = new FiveStrait();
        //moveMap[0][5] = new OneTurn();
        //moveMap[1][5] = new OneTurn();
        //moveMap[2][5] = new OneTurn();
        //moveMap[3][5] = new OneTurn();
        //moveMap[4][5] = new OneTurn();
        //moveMap[5][5] = new OneTurn();
        //moveMap[0][6] = new OneTurn();
        //moveMap[1][6] = new OneTurn();
        //moveMap[2][6] = new OneTurn();
        //moveMap[3][6] = new OneTurn();
        //moveMap[4][6] = new OneTurn();
        //moveMap[5][6] = new OneTurn();


              
       
       
       
   }
   
   
   
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 479, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        double xPct = (double)evt.getX() / (double)this.getWidth();
        double yPct = (double)evt.getY() / (double)this.getHeight();
        
        int row = 5-(int)(Math.floor(yPct*5d));
        int column = (int)(Math.floor(xPct*7d));
        
        
        
        
        
        System.out.println("Row: "+row + " Col: "+ column + "x " + xPct + " y " + yPct);
        ManueverShape chosen = moveMap[column-1][row-1];
        Manuever m = new Manuever(chosen, (column>=3?-1:1));
        if(column == 6) {
            m.kTurn = true;
        }
        
        
        if(null!=GameManager.selectedShip) {
            GameManager.ManueverToExecute = m;
            
            final Manuever mThread = m;
            Thread gameCreator = new Thread() {
            public void run() {     
                mThread.apply(GameManager.selectedShip);
            }};
            gameCreator.start();
            System.out.println("applied maneuver");
        }
        
        
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
