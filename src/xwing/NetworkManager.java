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
    
    private static boolean isHost = false;
    private static boolean connected = false;

    
    
    public static void initializeAsRemote() {    
        Thread gameCreator = new Thread() {
            public void run() {     
                onlineGame = new RemoteGame(13879,   13878,  "192.168.1.204");
                connected = true;
                isHost = true;
        }};
        gameCreator.start();
    } 
            
    public static void initializeAsHost() {    
        Thread gameCreator = new Thread() {
            public void run() {     
                onlineGame = new HostGame(13878,   13879);
                connected = true;
                isHost = false;
        }};
        gameCreator.start();
    } 
    
    static public boolean isHost() {
        return isHost;
    }
    
    static public boolean isOnlineGame() {
        return connected;
    }
    
}
