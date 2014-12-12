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
public class HostGame implements Runnable{

    DatagramSocket rcvrSock;
    NetworkInterface netInt;
    
    public HostGame(int port, NetworkInterface netI) {
        netInt = netI;
    
        try {
            rcvrSock = new DatagramSocket(port);
        } catch ( SocketException ex ) {
            Logger.getLogger(HostGame.class.getName()).log(Level.SEVERE, null,
                                                           ex);
        }
        
    }

    @Override
    public void run () {
        
        while(true) {
            byte[] buffer = new byte[2000];

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                rcvrSock.receive(packet);
                netInt.rcvr.add(packet);
                System.out.println("Received");
            } catch ( IOException ex ) {
                Logger.getLogger(HostGame.class.getName()).log(Level.SEVERE, null,
                                                               ex);
            }
        }
    }
    
}
