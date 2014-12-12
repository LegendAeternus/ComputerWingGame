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
public class RemoteGame implements Runnable{

    DatagramSocket sendSock;
    //String hostingIp = "192.168.1.204";
    String hostIp = "127.0.0.1";
    InetAddress hostAddr;
    NetworkInterface netInt;
    
    public RemoteGame(int port, NetworkInterface netI) {
        
        netInt = netI;
        
        try {
            sendSock = new DatagramSocket();
        } catch ( SocketException ex ) {
            Logger.getLogger(RemoteGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void run () {
        
        while(true) {
            byte[] buffer = new byte[2000];

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            try {
                if(netInt.send.size() > 0) {
                    sendSock.send(netInt.send.remove(netInt.send.size()-1));
                    System.out.println("SendingPacket  " + netInt.send.size());

                }
            } catch ( IOException ex ) {
                Logger.getLogger(RemoteGame.class.getName()).log(Level.SEVERE, null,
                                                               ex);
            }
        }
    }
    
}
