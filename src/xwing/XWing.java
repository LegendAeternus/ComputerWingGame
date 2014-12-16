/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

import java.net.*;
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
        
        //Ship wing = new LargeShip(250,600,0,1);
        //wing.secondaryArc = true;
        //wing.pilotName = "Boba Fett";
        //Ship wing2 = new NormalShip(400,300, 99,1);
        //Ship wing3 = new LargeShip(490,300, 166,2);

        //GameManager.addShip(wing);
        //GameManager.addShip(wing2);
        //GameManager.addShip(wing3);
        
        XWingGUI window = new XWingGUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
       /*
        
        int port = 18290;
        InetAddress targetIp = null;
        DatagramSocket sock = null;
        try {
            sock = new DatagramSocket(port);
            targetIp = InetAddress.getByName("127.0.0.1");

        } catch ( SocketException ex ) {
            Logger.getLogger(XWing.class.getName()).log(Level.SEVERE, null, ex);
        } catch ( UnknownHostException ex ) {
            Logger.getLogger(XWing.class.getName()).log(Level.SEVERE, null, ex);
        }

        PacketReceiver s = new PacketReceiver(sock);
        //PacketSender send = new PacketSender(sock);
        
        Thread t1 = new Thread(s);
        //Thread t2 = new Thread(send);
        
        t1.start();
        //t2.start();
        
            ConnectionPacket data = new ConnectionPacket(ConnectionPacket.REMOTE);   
            byte buffer[] = data.getByteArray().getRawBuffer();
            
            DatagramPacket connectionTest = new DatagramPacket(data.getByteArray().getRawBuffer(), data.getByteArray().size(), targetIp, 18290);//socket.getLocalPort());
            //send.sendPacket(connectionTest);
            
            Thread.sleep(1000);
            System.out.println(s.getPackets().size());
        
        
        RemoteGame r = new RemoteGame(port+1,"127.0.0.1");
        
*/
                    
                    
        while(true) {
            
            Thread.sleep(10);
            
            if(GameManager.selectedShip != null && GameManager.ManueverToExecute != null) {
                GameManager.ManueverToExecute.apply(GameManager.selectedShip);
                GameManager.ManueverToExecute = null;
            }
          //  try {
                //Thread.sleep(100);
            //} catch (InterruptedException ex) {
           //     Logger.getLogger(XWing.class.getName()).log(Level.SEVERE, null, ex);
           // }

            Manuever move = new Manuever(new TwoTurn(), 1);
            Manuever move2 = new Manuever(new ThreeTurn(), 1);
            Manuever move3 = new Manuever(new OneBank(), 1);

            
            
            //Point l = wing.location;
            //double o = wing.orientation;
            //Point l2 = wing2.location;
            //double o2= wing2.orientation;
            for(double i=0;i<100;i+=.2) {
               // move.apply(wing,i);
                //window.repaint();
               // try {
                    //Thread.sleep(3);
               // } catch (InterruptedException ex) {
                //    Logger.getLogger(XWing.class.getName()).log(Level.SEVERE, null, ex);
                //}
                //wing.setLocation(l);
                //wing.setOrientation(o);

            } 
            //Thread.sleep(100);
            //move.apply(wing);
            //window.repaint();
            //Thread.sleep(100);   
            //move2.apply(wing2);
            //window.repaint();
            //Thread.sleep(100);
            //ManueverOutcome m = move.apply(wing3);
            //if(!m.collidedWithShip) {
            //    Thread.sleep(50);
            //    move3.apply(wing3);
           // }
           // window.repaint();
           
        }

        
    }
}
