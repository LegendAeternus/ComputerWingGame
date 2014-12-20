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

    int interval; //Milliseconds between checking for new packets to send.
    DatagramSocket socket;
    LinkedList<DatagramPacket> packetsReceived = new LinkedList<>();

    
    public PacketReceiver(DatagramSocket sock, int loopsPerSecond) {
        
        socket = sock;
        interval = (int) (1.0/(double)loopsPerSecond);
    
    }
    

    @Override
    public void run () {
        
        while(true) {
            //System.out.println("Trying to receive Packets");
            
            receivePackets();
                        
            //=== Wait before executing loop again ===
            try {
                Thread.sleep(interval);
            } catch ( InterruptedException ex ) {
                Logger.getLogger(PacketSender.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }   
    }
        
    
    
    private void receivePackets() {

        //=== Create packet buffer ===
        byte[] buffer = new byte[2000];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        
        //=== Look for incoming packets ===
        try {
            socket.receive(packet);
            if(packet.getLength() > 0) {
                packetsReceived.add(packet);
                //System.out.println("Received Packet");
            }    
        } catch ( IOException ex ) {
            Logger.getLogger(PacketSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public LinkedList<DatagramPacket> getPackets() {
        return packetsReceived;
    }
    
    
}
