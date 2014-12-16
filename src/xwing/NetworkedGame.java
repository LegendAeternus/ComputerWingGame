/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;


import java.awt.List;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Michael
 */
public class NetworkedGame {
    
    static boolean connected = false;
    
    DatagramSocket destSocket;
    DatagramSocket listenSocket;

    InetAddress targetIp;    
    
    PacketSender packetSender;
    PacketReceiver packetReceiver;
    
    Thread sendThread, rcvThread;
    
    /**This class does stuff
     *
     * @param port yeah its a port
     * @param netI why do you care
     */
    public NetworkedGame(int destinationPort, int listeningPort) {
    
        
        try {
            destSocket = new DatagramSocket(destinationPort);
            listenSocket = new DatagramSocket(listeningPort);
        } catch ( SocketException ex ) {
            Logger.getLogger(HostGame.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        packetSender = new PacketSender(destSocket);
        packetReceiver = new PacketReceiver(listenSocket);
        
        sendThread = new Thread(packetSender);
        rcvThread = new Thread(rcvThread);
        
        rcvThread.start();
        sendThread.start();


    }
    
}
