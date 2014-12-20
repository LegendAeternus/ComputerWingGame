/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;


import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



/**
 *
 * @author Michael
 */
public class HostGame extends NetworkedGame{

    DatagramSocket socket;
    
    public HostGame(int destinationPort, int listeningPort) {      
        super(destinationPort, listeningPort);

        initialize();
        
        
    }
    
    public void initialize() {
        
        destIp = null;
        
        long timeout = 1000000; //Millis
        long sleep = 100; //Millis
        long time = 0; //Seconds
        
        while(time<timeout && destIp == null) {

            //ConnectionPacket data = new ConnectionPacket(ConnectionPacket.HOST);   
            //byte buffer[] = data.getByteArray().getRawBuffer();
            
            //DatagramPacket connectionTest = new DatagramPacket(data.getByteArray().getRawBuffer(), data.getByteArray().size(), destIp, socket.getLocalPort());
            //packetSender.sendPacket(connectionTest);

            int cutoff = 100;
            int coff = 0;
            System.out.println(packetReceiver.getPackets().size() + " Packets Received");
            
            while(packetReceiver.getPackets().size() > 0 && coff < cutoff) {
                System.out.println(packetReceiver.getPackets().size() + " Packets Received");
                coff++;
                ConnectionPacket data = (ConnectionPacket)ConnectionPacket.parsePacket(packetReceiver.getPackets().pop());
                if(data!=null && data.source == ConnectionPacket.REMOTE) {
                    if(showConfirmationOptionPane(data.ipSource)) {
                        System.out.println("Connection Established");
                        try {
                            destIp = InetAddress.getByName(data.ipSource);
                            destPort = data.portSource;
                        } catch ( UnknownHostException ex ) {
                            Logger.getLogger(HostGame.class.getName()).
                                    log(Level.SEVERE, null, ex);
                        }
                        
                        sendConnectionPacket(ConnectionPacket.HOST);
                        GameManager.curPhase = GameManager.GamePhase.SquadBuilding;
                        packetReceiver.packetsReceived.clear();
                        
                    }
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
    

    
    
    boolean showConfirmationOptionPane(String ipAddress) {
        
        Object[] options = {"Accept", "Deny"};
        int selection = JOptionPane.showOptionDialog(GuiManager.mainWindow, "Player at IP Address \"" + ipAddress + "\" wants to connect",
                                                            "Incoming Connection!",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
        if(selection == 0)
            return true;
        else
            return false;

        
    }
    
}
