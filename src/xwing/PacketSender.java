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

    
    DatagramSocket socket;
    LinkedList<DatagramPacket> packetsToSend = new LinkedList<>();

    
    public PacketSender(DatagramSocket sock) {
        
        socket = sock;
        
    }

    @Override
    public void run () {
        
        while(true) {
            
            while(packetsToSend.size() > 0) {
                try {
                    socket.send(packetsToSend.pop());
                } catch ( IOException ex ) {
                    Logger.getLogger(PacketSender.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
        }   
    }
    
    
    public void sendPacket(DatagramPacket packet) {
        packetsToSend.add(packet);   
    }
    
}
