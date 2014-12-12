/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;


import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Michael
 */
public class NetworkInterface{
    

    HostGame host;
    RemoteGame remote;
    int sendPort = 10026;
    int rcvrPort = 10024;
    
    int sent;
    
               // DatagramPacket packet = new DatagramPacket(buffer, buffer.length, receiverAddress, 10024);

    ArrayList<DatagramPacket> send = new ArrayList<>();
    ArrayList<DatagramPacket> rcvr = new ArrayList<>();

    
    //String hostingIp = "192.168.1.204";
    String hostIp = "127.0.0.1";
    InetAddress hostAddr;
    
    public NetworkInterface() {
        try {
            hostAddr =  InetAddress.getByName(hostIp);  
        } catch ( UnknownHostException ex ) {
            Logger.getLogger(NetworkInterface.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        host = new HostGame(rcvrPort,this);
        remote = new RemoteGame(sendPort,this);
        
        Thread server = new Thread(host);
        server.start();
        
        Thread cilent = new Thread(remote);
        cilent.start();
        
        send();
        
    }
    
    public void send() {
        System.out.println("Sent = " + sent+ " Received = " + this.rcvr.size());
        sent++;
        byte[] buffer = "0123456789".getBytes();
        send.add(new DatagramPacket(buffer, buffer.length, hostAddr, rcvrPort));
    }
    
}
    
   