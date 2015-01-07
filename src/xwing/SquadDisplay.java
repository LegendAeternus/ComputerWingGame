/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xwing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Michael
 */
public class SquadDisplay extends javax.swing.JPanel {

    
    
    private ArrayList<ShipForm> shipForms = new ArrayList<>();
    boolean shipsChanged = false;
    
    /**Used to get the list of ships that this squadDisplay is showing
     * 
     * @return An ArrayList containing the ships this squad display is showing
     */
    public ArrayList<Ship> getShips() {
        ArrayList<Ship> squad = new ArrayList<>();
        for(ShipForm form: shipForms) {
            squad.add(form.local);
        }
        return squad;
    }
    public void addShip(Ship s) {
        ShipForm newForm = new ShipForm();
        newForm.loadShipForEdit(s);
        shipForms.add(newForm);
        newForm.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        shipFormsPanel.add(newForm);
    }
    public void clearShips() {
        shipFormsPanel.removeAll();
        shipForms.clear();
    }
    
    
    /**
     * Syncs the list of ship forms with the list of ships, deleting forms that
     * are no longer have a corresponding ship, and adding new forms for new ships
     */
    public void updateShipForms() {

        updateComponentSizes();
       
        
    }
    
    /**
     * Updates the preferred size of the ship panel so that the scroll pane works
     * properly
     */
    private void updateComponentSizes() {

        int width = 0;
        for(ShipForm f: shipForms) {
            width += f.getWidth()+80;
        }

        int height = 0;
        if(shipForms.size()>0) {
            height = shipForms.get(0).getHeight();
        }
        
        //System.out.println("Width and Height = "+ width + "  " + height);
        shipFormsPanel.setPreferredSize(new Dimension(width,height));
         
    }
    
    public int getSquadPointsUsed() {
        int points=0;
        for(ShipForm f: shipForms) {
            points+=f.getShip().getPointCost();
        }
        return points;
    }
         

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    
    
    /**
     * Creates new form SquadDisplay
     */
    public SquadDisplay() {
        initComponents();
        WrapLayout layout = new WrapLayout();
        layout.setHgap(10);
        layout.setAlignment(WrapLayout.LEFT);
        shipFormsPanel.setLayout(layout);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        shipFormsPanel = new javax.swing.JPanel();

        jScrollPane1.setBorder(null);
        jScrollPane1.setViewportView(shipFormsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel shipFormsPanel;
    // End of variables declaration//GEN-END:variables
}
