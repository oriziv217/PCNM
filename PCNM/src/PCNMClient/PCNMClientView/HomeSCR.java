package PCNMClient.PCNMClientView;

import Entities.EmpType;
import PCNMClient.PCNMClientController.CTRL;
import PCNMClient.PCNMClientController.HomeCTRL;
import PCNMClient.PCNMClientStart;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Window;

/**
 * This class implements application's client home screen
 * @author Ori Ziv
 */
public class HomeSCR extends javax.swing.JPanel {

    /**
     * Creates new form HomeSCR
     */
    public HomeSCR() {
        initComponents();
        btnEmployees.requestFocusInWindow();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jplScreenLabel = new javax.swing.JPanel();
        lblScreenTitle = new javax.swing.JLabel();
        btnQuit = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnEmployees = new javax.swing.JButton();
        btnNetMap = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        setBackground(java.awt.Color.white);
        setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        setMinimumSize(new java.awt.Dimension(900, 560));
        setName("jplHomeScreen"); // NOI18N
        setPreferredSize(new java.awt.Dimension(881, 527));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jplScreenLabel.setBackground(java.awt.Color.white);
        jplScreenLabel.setName("jplScreenLabel"); // NOI18N

        lblScreenTitle.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        lblScreenTitle.setForeground(java.awt.Color.red);
        lblScreenTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScreenTitle.setText("PCNM Home Screen");
        lblScreenTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblScreenTitle.setName("lblScreenTitle"); // NOI18N

        javax.swing.GroupLayout jplScreenLabelLayout = new javax.swing.GroupLayout(jplScreenLabel);
        jplScreenLabel.setLayout(jplScreenLabelLayout);
        jplScreenLabelLayout.setHorizontalGroup(
            jplScreenLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplScreenLabelLayout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(lblScreenTitle)
                .addContainerGap(236, Short.MAX_VALUE))
        );
        jplScreenLabelLayout.setVerticalGroup(
            jplScreenLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplScreenLabelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblScreenTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jplScreenLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnQuit.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnQuit.setForeground(java.awt.Color.red);
        btnQuit.setText("Quit");
        btnQuit.setToolTipText("Quit PCNM");
        btnQuit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnQuit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnQuit.setInheritsPopupMenu(true);
        btnQuit.setName("btnQuit"); // NOI18N
        btnQuit.addActionListener(formListener);
        add(btnQuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 447, 120, 60));

        btnClose.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnClose.setForeground(java.awt.Color.red);
        btnClose.setToolTipText("Close screen and return to log-in screen");
        btnClose.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClose.setInheritsPopupMenu(true);
        btnClose.setLabel("Close");
        btnClose.setName("btnClose"); // NOI18N
        btnClose.setNextFocusableComponent(btnQuit);
        btnClose.addActionListener(formListener);
        add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 447, 120, 60));

        btnEmployees.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnEmployees.setForeground(java.awt.Color.red);
        btnEmployees.setText("System Users Management");
        btnEmployees.setToolTipText("Manage users and roles within the system");
        btnEmployees.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEmployees.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEmployees.setInheritsPopupMenu(true);
        btnEmployees.setName("btnEmployees"); // NOI18N
        btnEmployees.setNextFocusableComponent(btnNetMap);
        btnEmployees.addActionListener(formListener);
        add(btnEmployees, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 107, 300, 60));
        if (PCNMClientStart.user.getType() != EmpType.ADMINISTRATOR)
        btnEmployees.setEnabled(false);

        btnNetMap.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnNetMap.setForeground(java.awt.Color.red);
        btnNetMap.setText("Network Mapping");
        btnNetMap.setToolTipText("Define PCs, Workstations and User Types");
        btnNetMap.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNetMap.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNetMap.setInheritsPopupMenu(true);
        btnNetMap.setName("btnNetMap"); // NOI18N
        btnNetMap.setNextFocusableComponent(btnClose);
        btnNetMap.addActionListener(formListener);
        add(btnNetMap, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 300, 60));
        if (PCNMClientStart.user.getType() == EmpType.ADMINISTRATOR)
        btnNetMap.setEnabled(false);
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == btnQuit) {
                HomeSCR.this.btnQuitActionPerformed(evt);
            }
            else if (evt.getSource() == btnClose) {
                HomeSCR.this.btnCloseActionPerformed(evt);
            }
            else if (evt.getSource() == btnEmployees) {
                HomeSCR.this.btnEmployeesActionPerformed(evt);
            }
            else if (evt.getSource() == btnNetMap) {
                HomeSCR.this.btnNetMapActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        Window window = SwingUtilities.getWindowAncestor(this);
        JFrame frame = (JFrame) window;
        frame.setTitle("PCNM - Log in");
        CTRL.closeBtnPressed();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        HomeCTRL.QuitBtnPressed();
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmployeesActionPerformed
        try {
            HomeCTRL.btnEmployeesPressed();
            
            Window window = SwingUtilities.getWindowAncestor(this);
            JFrame frame = (JFrame) window;
            frame.setTitle("PCNM System Users Management");
            
        } catch (IOException ex) {
            WindowMustHave.showDialog(this, "Lost Connection With Server", DialogType.ERROR);
            System.exit(0);
        }
    }//GEN-LAST:event_btnEmployeesActionPerformed

    private void btnNetMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNetMapActionPerformed
        Window window = SwingUtilities.getWindowAncestor(this);
        JFrame frame = (JFrame) window;
        frame.setTitle("PCNM Network Mapping");
        HomeCTRL.btnNetMapPressed();
        
    }//GEN-LAST:event_btnNetMapActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnEmployees;
    private javax.swing.JButton btnNetMap;
    private javax.swing.JButton btnQuit;
    private javax.swing.JPanel jplScreenLabel;
    private javax.swing.JLabel lblScreenTitle;
    // End of variables declaration//GEN-END:variables
}
