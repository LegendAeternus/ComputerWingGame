/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;


import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Michael
 */
public class RemoteGame extends NetworkedGame{
    
    public RemoteGame(int destinationPort, int listeningPort, String targetIp) {      
        super(destinationPort, listeningPort);
        try {
            this.targetIp = InetAddress.getByName(targetIp);
        } catch ( UnknownHostException ex ) {
            Logger.getLogger(RemoteGame.class.getName()).log(Level.SEVERE, null,ex);
        }

        initialize();
        
        
    }
    
    public void initialize() {
        
        long timeout = 1000000; //Millis
        long sleep = 100; //Millis
        long time = 0; //Seconds
        
        while(time<timeout) {
            
            System.out.println("Attempting to connect from remote " + time);
            
            ConnectionPacket data = new ConnectionPacket(ConnectionPacket.REMOTE);   
            byte buffer[] = data.getByteArray().getRawBuffer();
            
            DatagramPacket connectionTest = new DatagramPacket(data.getByteArray().getRawBuffer(), data.getByteArray().size(), targetIp, 18290);//socket.getLocalPort());
            packetSender.sendPacket(connectionTest);
            
            int cutoff = 100;
            int coff = 0;
            while(packetReceiver.getPackets().size() > 0 && coff > cutoff) {
                coff++;
                PacketParser.parsePacket(packetReceiver.getPackets().pop());
            }
            
            try {
                Thread.sleep(sleep);
            } catch ( InterruptedException ex ) {
                Logger.getLogger(RemoteGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            time+=sleep;
        }
        
        System.out.println("TIME OUT");

        
        
    }
    
}
