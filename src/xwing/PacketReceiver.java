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
public class PacketReceiver implements Runnable {

    
    DatagramSocket socket;
    LinkedList<DatagramPacket> packetsReceived = new LinkedList<>();

    
    public PacketReceiver(DatagramSocket sock) {
        
        socket = sock;
        
    }
    

    @Override
    public void run () {
        
        while(true) {
            try {
                Thread.sleep(10);
            } catch ( InterruptedException ex ) {
                Logger.getLogger(PacketReceiver.class.getName()).
                        log(Level.SEVERE, null, ex);
            }

            
            byte[] buffer = new byte[2000];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                
            try {
                socket.receive(packet);
                if(packet.getLength() > 0) {
                    packetsReceived.add(packet);
                }
                
            } catch ( IOException ex ) {
                Logger.getLogger(PacketSender.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("Packets Received");

        }   
    }
        
    public LinkedList<DatagramPacket> getPackets() {
        return packetsReceived;
    }
    
    
}
