/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Michael
 */
public class XWing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Ship wing = new LargeShip(250,600,0,1);
        wing.secondaryArc = true;
        wing.pilotName = "Boba Fett";
        Ship wing2 = new NormalShip(400,300, 99,1);
        Ship wing3 = new LargeShip(490,300, 166,2);

        GameManager.addShip(wing);
        GameManager.addShip(wing2);
        GameManager.addShip(wing3);
        
        XWingGUI window = new XWingGUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
       
        
        
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(XWing.class.getName()).log(Level.SEVERE, null, ex);
            }

            Manuever move = new Manuever(new TwoTurn(), 1);
            Manuever move2 = new Manuever(new ThreeTurn(), 1);
            Manuever move3 = new Manuever(new OneBank(), 1);

            
            Point l = wing.location;
            double o = wing.orientation;
            Point l2 = wing2.location;
            double o2= wing2.orientation;
            for(double i=0;i<100;i+=.2) {
               // move.apply(wing,i);
                window.repaint();
               // try {
                    //Thread.sleep(3);
               // } catch (InterruptedException ex) {
                //    Logger.getLogger(XWing.class.getName()).log(Level.SEVERE, null, ex);
                //}
                wing.setLocation(l);
                wing.setOrientation(o);

            } 
            Thread.sleep(100);
            move.apply(wing);
            window.repaint();
            Thread.sleep(100);   
            move2.apply(wing2);
            window.repaint();
            Thread.sleep(100);
            ManueverOutcome m = move.apply(wing3);
            if(!m.collidedWithShip) {
                Thread.sleep(50);
                move3.apply(wing3);
            }
            window.repaint();
           
        }

        
    }
}
