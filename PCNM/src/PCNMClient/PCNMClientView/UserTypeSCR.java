package PCNMClient.PCNMClientView;

import PCNMClient.PCNMClientController.UserTypesCTRL;
import PCNMClient.PCNMClientStart;
import static PCNMClient.PCNMClientView.WindowMustHave.showDialog;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ori ziv
 */
public class UserTypeSCR extends javax.swing.JPanel {

    private ArrayList<String> search_results;
    private String[][] tableContent;
    int rowCounter;
    boolean rowsToShow[];
    private String fltrCol;
    private String fltrStr;
    private boolean fltrEnabled;
    private String fltrImportance;
    private boolean doneInit;
    private FormFrame addUserForm;
    private boolean isUpdate;
    private int selected;
        
    /**
     * Creates new form UserTypeSCR
     */
    public UserTypeSCR() {
        doneInit = false;
        initComponents();
        UserTypesCTRL.resetFilters();
        fltrCol = UserTypesCTRL.getFltrCol();
        fltrEnabled = UserTypesCTRL.isFltrEnabled();
        fltrStr = UserTypesCTRL.getFltrStr();
        fltrImportance = UserTypesCTRL.getFltrImportance();
        cmbFilterBy.setSelectedItem(fltrCol);
        txtFilterStr.setText(fltrStr);
        cmbImportanceFilter.setSelectedItem(fltrImportance);
        chbEnabledOnly.setSelected(fltrEnabled);
        doneInit = true;
    }

    /**
     *
     * @param search_results
     */
    public UserTypeSCR(ArrayList<String> search_results) {
        this();
        this.search_results = search_results;
        rowCounter = search_results.size();
        rowsToShow = new boolean[rowCounter];
        Arrays.fill(rowsToShow, true);
        tableContent = new String[rowCounter][5];
        loadSearchResults();
        tblUsers.setEnabled(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAddUser = new javax.swing.JPanel();
        lblAddUserTitle = new javax.swing.JLabel();
        lblAddUserName = new javax.swing.JLabel();
        txtAddUserName = new javax.swing.JTextField();
        lblAddUserUserName = new javax.swing.JLabel();
        lblAddUserPassword = new javax.swing.JLabel();
        lblAddUserStatus = new javax.swing.JLabel();
        txtAddUserDescription = new javax.swing.JTextField();
        cmbAddUserStatus = new javax.swing.JComboBox();
        btnAddUserOK = new javax.swing.JButton();
        btnAddUserCancel = new javax.swing.JButton();
        spnAddUserImportance = new javax.swing.JSpinner();
        lblScreenTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();
        btnNewPCUserType = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        lblFilterBy = new javax.swing.JLabel();
        cmbFilterBy = new javax.swing.JComboBox();
        lblFilterStr = new javax.swing.JLabel();
        txtFilterStr = new javax.swing.JTextField();
        chbEnabledOnly = new javax.swing.JCheckBox();
        cmbImportanceFilter = new javax.swing.JComboBox();
        lblImportanceFilter = new javax.swing.JLabel();

        pnlAddUser.setMinimumSize(new java.awt.Dimension(280, 400));
        pnlAddUser.setName("pnlAddUser"); // NOI18N
        pnlAddUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAddUserTitle.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblAddUserTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddUserTitle.setText("Add New User Type");
        lblAddUserTitle.setName("lblAddUserTitle"); // NOI18N
        pnlAddUser.add(lblAddUserTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, 259, -1));

        lblAddUserName.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblAddUserName.setText("Name:");
        pnlAddUser.add(lblAddUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 67, -1, -1));

        txtAddUserName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtAddUserName.setToolTipText("");
        pnlAddUser.add(txtAddUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 64, 130, -1));

        lblAddUserUserName.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblAddUserUserName.setText("Description:");
        pnlAddUser.add(lblAddUserUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 113, -1, -1));

        lblAddUserPassword.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblAddUserPassword.setText("Importance:");
        pnlAddUser.add(lblAddUserPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        lblAddUserStatus.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblAddUserStatus.setText("Status:");
        pnlAddUser.add(lblAddUserStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        txtAddUserDescription.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtAddUserDescription.setToolTipText("");
        pnlAddUser.add(txtAddUserDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 130, -1));

        cmbAddUserStatus.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cmbAddUserStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enabled", "Disabled", "Suspended" }));
        pnlAddUser.add(cmbAddUserStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 130, -1));

        btnAddUserOK.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAddUserOK.setText("OK");
        btnAddUserOK.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAddUserOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserOKActionPerformed(evt);
            }
        });
        pnlAddUser.add(btnAddUserOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 70, -1));

        btnAddUserCancel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAddUserCancel.setText("Cancel");
        btnAddUserCancel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAddUserCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserCancelActionPerformed(evt);
            }
        });
        pnlAddUser.add(btnAddUserCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 70, -1));

        spnAddUserImportance.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        spnAddUserImportance.setModel(new javax.swing.SpinnerNumberModel(1.0d, 0.1d, 1.9d, 0.1d));
        pnlAddUser.add(spnAddUserImportance, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 130, 28));

        clearFields();

        setBackground(java.awt.Color.white);
        setMinimumSize(new java.awt.Dimension(1185, 810));

        lblScreenTitle.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        lblScreenTitle.setForeground(java.awt.Color.red);
        lblScreenTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScreenTitle.setText("PC-User's Types Management");
        lblScreenTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblScreenTitle.setName("lblScreenTitle"); // NOI18N

        tblUsers.setAutoCreateRowSorter(true);
        tblUsers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblUsers.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Description", "Importance", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblUsers.setName("tblUsers"); // NOI18N
        tblUsers.setRowHeight(32);
        jScrollPane1.setViewportView(tblUsers);
        tblUsers.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tblUsers.getColumnModel().getColumnCount() > 0) {
            tblUsers.getColumnModel().getColumn(0).setMinWidth(150);
            tblUsers.getColumnModel().getColumn(1).setMinWidth(450);
            tblUsers.getColumnModel().getColumn(2).setMinWidth(150);
            tblUsers.getColumnModel().getColumn(3).setMinWidth(150);
        }
        tblUsers.getAccessibleContext().setAccessibleName("");
        tblUsers.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 24));

        btnClose.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnClose.setForeground(java.awt.Color.red);
        btnClose.setToolTipText("Close screen and return to log-in screen");
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

        btnNewPCUserType.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnNewPCUserType.setText("New User Type");
        btnNewPCUserType.setToolTipText("Add new system user");
        btnNewPCUserType.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNewPCUserType.setMaximumSize(new java.awt.Dimension(99, 33));
        btnNewPCUserType.setMinimumSize(new java.awt.Dimension(99, 33));
        btnNewPCUserType.setPreferredSize(new java.awt.Dimension(99, 33));
        btnNewPCUserType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPCUserTypeActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnUpdate.setText("Update Selected");
        btnUpdate.setToolTipText("Update Selected");
        btnUpdate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUpdate.setMaximumSize(new java.awt.Dimension(99, 33));
        btnUpdate.setMinimumSize(new java.awt.Dimension(99, 33));
        btnUpdate.setPreferredSize(new java.awt.Dimension(99, 33));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        lblFilterBy.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblFilterBy.setText("Filter By:");

        cmbFilterBy.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        cmbFilterBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Show All", "Name", "Description" }));
        cmbFilterBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFilterByActionPerformed(evt);
            }
        });

        lblFilterStr.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblFilterStr.setText("Filter String:");

        txtFilterStr.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtFilterStr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFilterStrFocusLost(evt);
            }
        });
        txtFilterStr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFilterStrActionPerformed(evt);
            }
        });

        chbEnabledOnly.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        chbEnabledOnly.setText("Show Enabled Only");
        chbEnabledOnly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbEnabledOnlyActionPerformed(evt);
            }
        });

        cmbImportanceFilter.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        cmbImportanceFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Show All", "Greater then 1.0", "Less Then 1.0" }));
        cmbImportanceFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbImportanceFilterActionPerformed(evt);
            }
        });

        lblImportanceFilter.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblImportanceFilter.setText("Importance Filter:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblScreenTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(btnNewPCUserType, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblFilterBy)
                .addGap(18, 18, 18)
                .addComponent(cmbFilterBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFilterStr)
                .addGap(18, 18, 18)
                .addComponent(txtFilterStr, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chbEnabledOnly)
                .addGap(18, 18, 18)
                .addComponent(lblImportanceFilter)
                .addGap(18, 18, 18)
                .addComponent(cmbImportanceFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblScreenTitle)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFilterBy)
                            .addComponent(cmbFilterBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFilterStr)
                            .addComponent(txtFilterStr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chbEnabledOnly)
                            .addComponent(cmbImportanceFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblImportanceFilter))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewPCUserType, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        UserTypesCTRL.closeBtnPressed();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        UserTypesCTRL.QuitBtnPressed();
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnNewPCUserTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPCUserTypeActionPerformed
        isUpdate = false;
        clearFields();
        addUserForm = new FormFrame();
        addUserForm.setSize(pnlAddUser.getMinimumSize());
        addUserForm.getContentPane().add(pnlAddUser);
        addUserForm.getContentPane().setVisible(true);
        PCNMClientStart.appWindow.setEnabled(false);
        addUserForm.setVisible(true);
    }//GEN-LAST:event_btnNewPCUserTypeActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        selected = tblUsers.getSelectedRow();
        if (selected == -1) {
            showDialog(this, "Please select user", DialogType.INFO);
            return;
        }
        isUpdate = true;
        txtAddUserName.setText(tableContent[selected][1]);
        txtAddUserDescription.setText(tableContent[selected][2]);
        spnAddUserImportance.setValue(Double.parseDouble(tableContent[selected][3]));
        cmbAddUserStatus.setSelectedItem(tableContent[selected][4]);
        addUserForm = new FormFrame();
        addUserForm.setSize(pnlAddUser.getMinimumSize());
        addUserForm.getContentPane().add(pnlAddUser);
        addUserForm.getContentPane().setVisible(true);
        PCNMClientStart.appWindow.setEnabled(false);
        addUserForm.setVisible(true);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void cmbFilterByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilterByActionPerformed
        if (!doneInit) return;
        fltrCol = String.valueOf(cmbFilterBy.getSelectedItem());
        UserTypesCTRL.setFltrCol(fltrCol);
        if (fltrCol.equals("Show All"))
            applyFilter();
    }//GEN-LAST:event_cmbFilterByActionPerformed

    private void txtFilterStrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFilterStrActionPerformed
        if (!doneInit) return;
        String txt = txtFilterStr.getText();
        if (txt.equals(fltrStr)) return;
        fltrStr = txt;
        UserTypesCTRL.setFltrStr(fltrStr);
        applyFilter();
    }//GEN-LAST:event_txtFilterStrActionPerformed

    private void txtFilterStrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFilterStrFocusLost
        if (!doneInit) return;
        String txt = txtFilterStr.getText();
        if (txt.equals(fltrStr)) return;
        fltrStr = txt;
        UserTypesCTRL.setFltrStr(fltrStr);
        applyFilter();
    }//GEN-LAST:event_txtFilterStrFocusLost

    private void chbEnabledOnlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEnabledOnlyActionPerformed
        if (!doneInit) return;
        fltrEnabled = chbEnabledOnly.isSelected();
        UserTypesCTRL.setFltrEnabled(fltrEnabled);
        applyFilter();
    }//GEN-LAST:event_chbEnabledOnlyActionPerformed

    private void cmbImportanceFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbImportanceFilterActionPerformed
        if (!doneInit) return;
        fltrImportance = String.valueOf(cmbImportanceFilter.getSelectedItem());
        UserTypesCTRL.setFltrImportance(fltrImportance);
        applyFilter();
    }//GEN-LAST:event_cmbImportanceFilterActionPerformed

    private void btnAddUserOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserOKActionPerformed
        String name = txtAddUserName.getText();
        String description = txtAddUserDescription.getText();
        double importance = (Double) spnAddUserImportance.getValue();
        String status = (String)cmbAddUserStatus.getSelectedItem();
        if (name.isEmpty() || description.isEmpty() || status.isEmpty()) {
            showDialog(pnlAddUser, "All fields are mandatory.", DialogType.INFO);
            return;
        }
        if (!isUpdate) {
            for (int i = 0 ; i < tableContent.length ; i ++) {
                if (name.equals(tableContent[i][1])) {
                    showDialog(pnlAddUser, "User type's name must be unique.", DialogType.INFO);
                    return;
                }
            }
        }
        try {
            UserTypesCTRL.setFltrCol(fltrCol);
            UserTypesCTRL.setFltrEnabled(fltrEnabled);
            UserTypesCTRL.setFltrImportance(fltrImportance);
            UserTypesCTRL.setFltrStr(fltrStr);
            if (!isUpdate)
                UserTypesCTRL.btnAddNewUserOKPressed (name, description, importance, status);
            else
                UserTypesCTRL.btnUpdateUserPressed (Integer.valueOf(tableContent[selected][0]), name, description, importance, status);
        } catch (IOException ex) {
            showDialog(pnlAddUser, "Lost Connection with the server", DialogType.ERROR);
            System.exit(0);
        }
        addUserForm.dispose();
        clearFields();
        PCNMClientStart.appWindow.setEnabled(true);
        PCNMClientStart.appWindow.requestFocus();
    }//GEN-LAST:event_btnAddUserOKActionPerformed

    private void btnAddUserCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserCancelActionPerformed
        clearFields();
        addUserForm.dispose();
        PCNMClientStart.appWindow.setEnabled(true);
        PCNMClientStart.appWindow.requestFocus();
    }//GEN-LAST:event_btnAddUserCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUserCancel;
    private javax.swing.JButton btnAddUserOK;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnNewPCUserType;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox chbEnabledOnly;
    private javax.swing.JComboBox cmbAddUserStatus;
    private javax.swing.JComboBox cmbFilterBy;
    private javax.swing.JComboBox cmbImportanceFilter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddUserName;
    private javax.swing.JLabel lblAddUserPassword;
    private javax.swing.JLabel lblAddUserStatus;
    private javax.swing.JLabel lblAddUserTitle;
    private javax.swing.JLabel lblAddUserUserName;
    private javax.swing.JLabel lblFilterBy;
    private javax.swing.JLabel lblFilterStr;
    private javax.swing.JLabel lblImportanceFilter;
    private javax.swing.JLabel lblScreenTitle;
    private javax.swing.JPanel pnlAddUser;
    private javax.swing.JSpinner spnAddUserImportance;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txtAddUserDescription;
    private javax.swing.JTextField txtAddUserName;
    private javax.swing.JTextField txtFilterStr;
    // End of variables declaration//GEN-END:variables

    private void loadSearchResults() {
        String row;
        DefaultTableModel dtm = (DefaultTableModel)tblUsers.getModel();
        dtm.setRowCount(rowCounter);
        int cur_row = 0;
        for (int i = 0 ; i < search_results.size() ; i ++) {
            if (rowsToShow[i]) {
                row = search_results.get(i);
                tableContent[i] = row.split(",");
                dtm.setValueAt(tableContent[i][1], cur_row, 0);
                dtm.setValueAt(tableContent[i][2], cur_row, 1);
                dtm.setValueAt(Double.parseDouble(tableContent[i][3]), cur_row, 2);
                dtm.setValueAt(tableContent[i][4], cur_row, 3);
                cur_row ++;
                if (cur_row > rowCounter)
                    i = search_results.size();
            }
        }
    }

    private void applyFilter() {
        Arrays.fill(rowsToShow, true);
        rowCounter = rowsToShow.length;
        for (int i = 0 ; i < tableContent.length ; i ++) {
            if (rowsToShow[i] && fltrCol.equals("Name") && tableContent[i][1].indexOf(fltrStr) == -1)
                rowsToShow[i] = false;
            if (rowsToShow[i] && fltrCol.equalsIgnoreCase("Description") && tableContent[i][2].indexOf(fltrStr) == -1)
                rowsToShow[i] = false;
            if (rowsToShow[i] && fltrEnabled && !tableContent[i][4].equalsIgnoreCase("Enabled"))
                rowsToShow[i] = false;
            if (rowsToShow[i] && fltrImportance.equalsIgnoreCase("Greater then 1.0") && Float.valueOf(tableContent[i][3]) < 1)
                rowsToShow[i] = false;
            if (rowsToShow[i] && fltrImportance.equalsIgnoreCase("Less Then 1.0") && Float.valueOf(tableContent[i][3]) > 1)
                rowsToShow[i] = false;
            if (!rowsToShow[i])
                rowCounter --;
        }
        loadSearchResults();
    }

    private void clearFields() {
        txtAddUserName.setText("");
        txtAddUserDescription.setText("");
        spnAddUserImportance.setValue(1.0);
        cmbAddUserStatus.setSelectedIndex(0);
    }
}
