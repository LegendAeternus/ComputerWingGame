/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

import java.util.TimerTask;

/**
 *
 * @author Michael
 */
public class RenderManager extends TimerTask{

    XWingGUI gui;
    public RenderManager(XWingGUI gui) {
        this.gui = gui;
    }
    
    
    @Override
    public void run() {
        gui.repaint();
    }
    
}
