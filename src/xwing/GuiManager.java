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
    
    static XWingGUI mainWindow = new XWingGUI();
    
    static void showMainWindow() {
        XWingGUI window = new XWingGUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    
}
