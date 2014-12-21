/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

import javax.swing.JFrame;

/**
 *
 * @author Michael
 */
public class GuiManager {
    
    static XWingGUI mainWindow;//= new XWingGUI();
    
    static void showMainWindow() {
        mainWindow = new XWingGUI();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);     
    }
    
}
