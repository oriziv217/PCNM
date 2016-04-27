package PCNMClient.PCNMClientView;

import PCNMClient.PCNMClientController.PCCTRL;
import static PCNMClient.PCNMClientView.WindowMustHave.showDialog;
import java.awt.Window;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author ori ziv
 */
public class PCSCR extends javax.swing.JPanel {

    private int fltrInstalDateMode;
    private int fltrWarrentymode;
    private int fltrStatus;
    private Date fltrInstalDate;

    /**
     * Creates new form PCSCR
     */
    public PCSCR() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblScreenTitle = new javax.swing.JLabel();
        lblSearchTitle = new javax.swing.JLabel();
        lblNoFilter = new javax.swing.JLabel();
        lblSearchName = new javax.swing.JLabel();
        lblSearchDescription = new javax.swing.JLabel();
        lblSearchInstalDate = new javax.swing.JLabel();
        lblSearchStatus = new javax.swing.JLabel();
        lblSearchWarrentyDate = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        cmbInstalDate = new javax.swing.JComboBox();
        cmbWarrenty = new javax.swing.JComboBox();
        cmbStatus = new javax.swing.JComboBox();
        dtpInstalDate = new org.jdesktop.swingx.JXDatePicker();
        btnSearchByComp = new javax.swing.JButton();
        btnSearchByFilter = new javax.swing.JButton();
        btnSearchByPCSpec = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 669, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );

        setBackground(java.awt.Color.white);
        setMinimumSize(new java.awt.Dimension(920, 650));

        lblScreenTitle.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        lblScreenTitle.setForeground(java.awt.Color.red);
        lblScreenTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScreenTitle.setText("PC Management");
        lblScreenTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblScreenTitle.setName("lblScreenTitle"); // NOI18N

        lblSearchTitle.setBackground(java.awt.Color.white);
        lblSearchTitle.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lblSearchTitle.setForeground(java.awt.Color.red);
        lblSearchTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearchTitle.setText("Search For PCs");

        lblNoFilter.setBackground(java.awt.Color.white);
        lblNoFilter.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNoFilter.setForeground(java.awt.Color.red);
        lblNoFilter.setText("Leave text fields empty for all PCs");

        lblSearchName.setBackground(java.awt.Color.white);
        lblSearchName.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblSearchName.setText("Search by PC name:");

        lblSearchDescription.setBackground(java.awt.Color.white);
        lblSearchDescription.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblSearchDescription.setText("Search by PC description:");

        lblSearchInstalDate.setBackground(java.awt.Color.white);
        lblSearchInstalDate.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblSearchInstalDate.setText("Search by PC installation date:");

        lblSearchStatus.setBackground(java.awt.Color.white);
        lblSearchStatus.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblSearchStatus.setText("Search by PC status:");

        lblSearchWarrentyDate.setBackground(java.awt.Color.white);
        lblSearchWarrentyDate.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblSearchWarrentyDate.setText("Search by PC Warrenty expiration:");

        txtName.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtDescription.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        cmbInstalDate.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        cmbInstalDate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Installed After", "Installed Before" }));
        cmbInstalDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInstalDateActionPerformed(evt);
            }
        });

        cmbWarrenty.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        cmbWarrenty.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Active Only", "Expired Only" }));
        cmbWarrenty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbWarrentyActionPerformed(evt);
            }
        });

        cmbStatus.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Any Status", "Enabled", "Disabled", "Suspended" }));
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });

        dtpInstalDate.setEnabled(false);
        dtpInstalDate.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        dtpInstalDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dtpInstalDateActionPerformed(evt);
            }
        });

        btnSearchByComp.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnSearchByComp.setText("Search By Component");
        btnSearchByComp.setToolTipText("Search Workstations");
        btnSearchByComp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSearchByComp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSearchByComp.setInheritsPopupMenu(true);
        btnSearchByComp.setMaximumSize(new java.awt.Dimension(280, 33));
        btnSearchByComp.setMinimumSize(new java.awt.Dimension(280, 33));
        btnSearchByComp.setName("btnSearchByComp"); // NOI18N
        btnSearchByComp.setPreferredSize(new java.awt.Dimension(280, 33));
        btnSearchByComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchByCompActionPerformed(evt);
            }
        });

        btnSearchByFilter.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnSearchByFilter.setText("Search By Filter");
        btnSearchByFilter.setToolTipText("Search Workstations");
        btnSearchByFilter.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSearchByFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSearchByFilter.setInheritsPopupMenu(true);
        btnSearchByFilter.setName("btnSearchByFilter"); // NOI18N
        btnSearchByFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchByFilterActionPerformed(evt);
            }
        });

        btnSearchByPCSpec.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnSearchByPCSpec.setText("Search By Specification");
        btnSearchByPCSpec.setToolTipText("Search Workstations");
        btnSearchByPCSpec.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSearchByPCSpec.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSearchByPCSpec.setInheritsPopupMenu(true);
        btnSearchByPCSpec.setMaximumSize(new java.awt.Dimension(280, 33));
        btnSearchByPCSpec.setMinimumSize(new java.awt.Dimension(280, 33));
        btnSearchByPCSpec.setName("btnSearchByComp"); // NOI18N
        btnSearchByPCSpec.setPreferredSize(new java.awt.Dimension(280, 33));
        btnSearchByPCSpec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchByPCSpecActionPerformed(evt);
            }
        });

        btnClose.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnClose.setForeground(java.awt.Color.red);
        btnClose.setToolTipText("Close screen and return to Network Mapping screen");
        btnClose.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClose.setInheritsPopupMenu(true);
        btnClose.setLabel("Close");
        btnClose.setName("btnClose"); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnQuit.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnQuit.setForeground(java.awt.Color.red);
        btnQuit.setText("Quit");
        btnQuit.setToolTipText("Quit PCNM");
        btnQuit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnQuit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnQuit.setInheritsPopupMenu(true);
        btnQuit.setName("btnQuit"); // NOI18N
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblScreenTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNoFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSearchStatus)
                            .addComponent(lblSearchInstalDate)
                            .addComponent(lblSearchWarrentyDate))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmbWarrenty, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbInstalDate, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(dtpInstalDate, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(lblSearchTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSearchName)
                            .addComponent(lblSearchDescription))
                        .addGap(107, 107, 107)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName)
                            .addComponent(txtDescription)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSearchByComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSearchByPCSpec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnSearchByFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblScreenTitle)
                        .addGap(18, 18, 18)
                        .addComponent(lblSearchTitle)
                        .addGap(18, 18, 18)
                        .addComponent(lblNoFilter)
                        .addGap(18, 18, 18)
                        .addComponent(lblSearchName))
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchDescription)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchInstalDate)
                    .addComponent(cmbInstalDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtpInstalDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchWarrentyDate)
                    .addComponent(cmbWarrenty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchStatus)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchByComp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchByFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchByPCSpec, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        if (PCCTRL.getNameFilter() != null)
        txtName.setText(PCCTRL.getNameFilter());
        if (PCCTRL.getDescriptionFilter() != null)
        txtName.setText(PCCTRL.getDescriptionFilter());
        cmbInstalDate.setSelectedIndex(PCCTRL.getInstallationDateModeFilter());
        if (PCCTRL.getInstallationDateModeFilter() > 0)
        dtpInstalDate.setEnabled(true);
        else
        dtpInstalDate.setEnabled(false);
        cmbWarrenty.setSelectedIndex(PCCTRL.getWarrentyModeFilter());
        cmbStatus.setSelectedIndex(PCCTRL.getStatusFilter());
        dtpInstalDate.setFormats(new SimpleDateFormat( "dd/MM/yyyy" ));
        if (PCCTRL.getInstalationDateFilter() != null)
        dtpInstalDate.setDate(PCCTRL.getInstalationDateFilter());
    }// </editor-fold>//GEN-END:initComponents

    private void cmbInstalDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInstalDateActionPerformed
        fltrInstalDateMode = cmbInstalDate.getSelectedIndex();
        if (fltrInstalDateMode == -1) return;
        if (fltrInstalDateMode == 0) dtpInstalDate.setEnabled(false);
        else dtpInstalDate.setEnabled(true);
    }//GEN-LAST:event_cmbInstalDateActionPerformed

    private void cmbWarrentyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWarrentyActionPerformed
        fltrWarrentymode = cmbWarrenty.getSelectedIndex();
    }//GEN-LAST:event_cmbWarrentyActionPerformed

    private void btnSearchByCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByCompActionPerformed
        getFilters();
        try {
            PCCTRL.searchByCompBtnPressed();
        } catch (IOException ex) {
            showDialog(this, "Lost Connection with the server", DialogType.ERROR);
            System.exit(0);
        }
    }//GEN-LAST:event_btnSearchByCompActionPerformed

    private void btnSearchByFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByFilterActionPerformed
        getFilters();
        try {
            PCCTRL.searchByPCFilterBtnPressed();
        } catch (IOException ex) {
            showDialog(this, "Lost Connection with the server", DialogType.ERROR);
            System.exit(0);
        }
    }//GEN-LAST:event_btnSearchByFilterActionPerformed

    private void btnSearchByPCSpecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByPCSpecActionPerformed
        getFilters();
        try {
            PCCTRL.searchByPCSpecBtnPressed();
        } catch (IOException ex) {
            showDialog(this, "Lost Connection with the server", DialogType.ERROR);
            System.exit(0);
        }
    }//GEN-LAST:event_btnSearchByPCSpecActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        Window window = SwingUtilities.getWindowAncestor(this);
        JFrame frame = (JFrame) window;
        frame.setTitle("PCNM - Network Mapping");
        PCCTRL.closeBtnPressed();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        PCCTRL.QuitBtnPressed();
    }//GEN-LAST:event_btnQuitActionPerformed

    private void dtpInstalDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dtpInstalDateActionPerformed
        fltrInstalDate = dtpInstalDate.getDate();
    }//GEN-LAST:event_dtpInstalDateActionPerformed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        fltrStatus = cmbStatus.getSelectedIndex();
    }//GEN-LAST:event_cmbStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnSearchByComp;
    private javax.swing.JButton btnSearchByFilter;
    private javax.swing.JButton btnSearchByPCSpec;
    private javax.swing.JComboBox cmbInstalDate;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JComboBox cmbWarrenty;
    private org.jdesktop.swingx.JXDatePicker dtpInstalDate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNoFilter;
    private javax.swing.JLabel lblScreenTitle;
    private javax.swing.JLabel lblSearchDescription;
    private javax.swing.JLabel lblSearchInstalDate;
    private javax.swing.JLabel lblSearchName;
    private javax.swing.JLabel lblSearchStatus;
    private javax.swing.JLabel lblSearchTitle;
    private javax.swing.JLabel lblSearchWarrentyDate;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

    private void getFilters() {
        PCCTRL.setNameFilter(txtName.getText());
        PCCTRL.setDescriptionFilter(txtDescription.getText());
        PCCTRL.setInstallationDateModeFilter(cmbInstalDate.getSelectedIndex());
        PCCTRL.setInstalationDateFilter(dtpInstalDate.getDate());
        PCCTRL.setWarrentyModeFilter(cmbWarrenty.getSelectedIndex());
        PCCTRL.setStatusFilter(cmbStatus.getSelectedIndex());
    }
}
