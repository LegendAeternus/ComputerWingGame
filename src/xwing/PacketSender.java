/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Michael
 */
public class PacketSender implements Runnable{

    int interval; //Milliseconds between checking for new packets to send.
    DatagramSocket socket;
    LinkedList<DatagramPacket> packetsToSend = new LinkedList<>();

    
    public PacketSender(DatagramSocket sock, int loopsPerSecond) {
        
        socket = sock;
        interval = (int) (1.0/(double)loopsPerSecond);
        
    }

    @Override
    public void run () {
        
        while(true) {
            
            //=== Send Packets ===
            while(packetsToSend.size() > 0) {
                try {
                    socket.send(packetsToSend.pop());
                    System.out.println("Send Packet");
                    //System.out.println("sent packet");
                } catch ( IOException ex ) {
                    Logger.getLogger(PacketSender.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            //=== Wait before executing loop again ===
            try {
                Thread.sleep(interval);
            } catch ( InterruptedException ex ) {
                Logger.getLogger(PacketSender.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }   
    }
    
    
    public void sendPacket(DatagramPacket packet) {
        packetsToSend.add(packet);   
    }
    
}
