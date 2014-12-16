/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

/**
 *
 * @author Michael
 */
public class QuickThread implements Runnable{
    
    HostGame j;
    int port;
    Thread t;
    QuickThread s;
    
    boolean doonce;
    
    public QuickThread(int port) {
        this.port = port;
    }


    @Override
    public void run () {
        System.out.println("HOST THREAD RUNNING");
        if(!doonce) {
            j = new HostGame(port);
            doonce = true;
        }
    
    }
    
    
    
}
