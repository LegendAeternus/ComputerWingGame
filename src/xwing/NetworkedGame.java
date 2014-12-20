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
    
    int destPort;
    DatagramSocket localSocket;

    InetAddress destIp;    
    
    PacketSender packetSender;
    PacketReceiver packetReceiver;
    
    Thread sendThread, rcvThread;
    
    /**This class does stuff
     *
     * @param port yeah its a port
     * @param netI why do you care
     */
    public NetworkedGame(int localPort, int destinationPort) {
    
        destPort = destinationPort;

        try {
            localSocket = new DatagramSocket(localPort);
        } catch ( SocketException ex ) {
            Logger.getLogger(HostGame.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        int loopsPerSecond = 10;
        packetSender = new PacketSender(localSocket, loopsPerSecond);
        packetReceiver = new PacketReceiver(localSocket, loopsPerSecond);
        
        sendThread = new Thread(packetSender, "Packet Sender");
        rcvThread = new Thread(packetReceiver, "Packet Receiver");
        
        rcvThread.start();
        sendThread.start();

    }
    
    
    protected void sendConnectionPacket(char gameType) {
        ConnectionPacket data = null;
        if(gameType == ConnectionPacket.REMOTE) {
            data = new ConnectionPacket(ConnectionPacket.REMOTE);   
        } else if (gameType == ConnectionPacket.HOST) {
            data = new ConnectionPacket(ConnectionPacket.HOST);   
        }
        
        byte buffer[] = data.getByteArray().getRawBuffer();

        DatagramPacket connectionTest = new DatagramPacket(data.getByteArray().getRawBuffer(), data.getByteArray().size(),destIp, destPort);
        packetSender.sendPacket(connectionTest);
    }
    
}
