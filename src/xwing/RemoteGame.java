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
            this.destIp = InetAddress.getByName(targetIp);
        } catch ( UnknownHostException ex ) {
            Logger.getLogger(RemoteGame.class.getName()).log(Level.SEVERE, null,ex);
        }

        initialize();
        
        
    }
    
    public void initialize() {
        
        long timeout = 1000000; //Millis
        long sleep = 100; //Millis
        long time = 0; //Seconds
        
        boolean stop = false;
        
        while(time<timeout && !stop) {
            
            //System.out.println("Attempting to connect from remote " + time);
            
            sendConnectionPacket(ConnectionPacket.REMOTE);
            
            int cutoff = 100;
            int coff = 0;
            while(packetReceiver.getPackets().size() > 0 && coff < cutoff) {
                coff++;
                System.out.println("Received Packet");

                ConnectionPacket data = (ConnectionPacket)ConnectionPacket.parsePacket(packetReceiver.getPackets().pop());
                if(data!=null && data.source == ConnectionPacket.HOST) {
                    System.out.println("Connection Established");

                    GameManager.curPhase = GameManager.GamePhase.SquadBuilding;
                    packetReceiver.packetsReceived.clear();
                    stop = true;

                }
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
