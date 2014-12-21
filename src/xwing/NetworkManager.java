/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

/**
 *
 * @author Michael
 */
public class NetworkManager{
    
    static NetworkedGame onlineGame;
    
    static boolean isHost = false;
    static boolean connected = false;

    
    
    public static void initializeAsRemote() {    
        Thread gameCreator = new Thread() {
            public void run() {     
                onlineGame = new RemoteGame(13879,   13878,  "192.168.1.204");
        }};
        gameCreator.start();
    } 
            
    public static void initializeAsHost() {    
        Thread gameCreator = new Thread() {
            public void run() {     
                onlineGame = new HostGame(13878,   13879);
        }};
        gameCreator.start();
    } 
    
}
