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

    public RenderManager() {
    }
    
    
    @Override
    public void run() {
        GuiManager.mainWindow.showPhase(GameManager.curPhase);
        GuiManager.mainWindow.refresh();
    }
}
