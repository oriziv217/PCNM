package PCNMClient.PCNMClientView;

import PCNMClient.PCNMClientController.WorkstationCTRL;
import PCNMClient.PCNMClientStart;
import static PCNMClient.PCNMClientView.WindowMustHave.showDialog;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * This class implements Workstation types management screen
 * @author ori ziv
 */
public class WSTypeSCR extends javax.swing.JPanel {
    private ArrayList<String> wst_rows;
    private int rowCounter;
    private boolean[] rowsToShow;
    private String[][] tableContent;
    private boolean doneInit;
    private int fltrField;
    private String fltrStirng;
    private boolean fltrEnabled;
    private int fltrMinScore;
    private boolean isUpdate;
    private FormFrame addWSType;
    private int selected;
    private int onScreenWSTypeID;

    /**
     * Creates new form WSTypeSCR
     */
    public WSTypeSCR() {
        doneInit = false;
        initComponents();
        doneInit = true;
    }

    /**
     * Creates new form WSTypeSCR with content
     * @param wst_rows
     */
    public WSTypeSCR(ArrayList<String> wst_rows) {
        this();
        doneInit = false;
        this.wst_rows = wst_rows;
        rowCounter = wst_rows.size();
        rowsToShow = new boolean[rowCounter];
        Arrays.fill(rowsToShow, true);
        tableContent = new String[rowCounter][5];
        loadSearchResults();
        tblTypes.setEnabled(true);
        doneInit = true;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAddWSType = new javax.swing.JPanel();
        lblAddWSTypeTitle = new javax.swing.JLabel();
        lblAddWSTypeName = new javax.swing.JLabel();
        lblAddWSTypeDescription = new javax.swing.JLabel();
        lblAddWSTypeMinRate = new javax.swing.JLabel();
        lblAddWSTypeStatus = new javax.swing.JLabel();
        spnAddWSTypeMinRate = new javax.swing.JSpinner();
        txtAddWSTypeName = new javax.swing.JTextField();
        txtAddWSTypeDescription = new javax.swing.JTextField();
        cmbAddWSTypeStatus = new javax.swing.JComboBox();
        btnAddWSTypeOK = new javax.swing.JButton();
        btnAddWSTypeCancel = new javax.swing.JButton();
        lblScreenTitle = new javax.swing.JLabel();
        lblFilterBy = new javax.swing.JLabel();
        cmbFilterBy = new javax.swing.JComboBox();
        lblFilterStr = new javax.swing.JLabel();
        txtFilterStr = new javax.swing.JTextField();
        chbEnabledOnly = new javax.swing.JCheckBox();
        lblMinScoreFilter = new javax.swing.JLabel();
        cmbMinScoreFilter = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTypes = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();
        btnNewWorkstationType = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();

        pnlAddWSType.setBackground(java.awt.Color.white);
        pnlAddWSType.setMinimumSize(new java.awt.Dimension(315, 320));

        lblAddWSTypeTitle.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblAddWSTypeTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddWSTypeTitle.setText("Add New Workstation Type");
        lblAddWSTypeTitle.setName("lblAddWSTypeTitle"); // NOI18N

        lblAddWSTypeName.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblAddWSTypeName.setText("Name:");

        lblAddWSTypeDescription.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblAddWSTypeDescription.setText("Description:");

        lblAddWSTypeMinRate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblAddWSTypeMinRate.setText("Minimal Rate:");

        lblAddWSTypeStatus.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblAddWSTypeStatus.setText("Status:");

        spnAddWSTypeMinRate.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        spnAddWSTypeMinRate.setModel(new javax.swing.SpinnerNumberModel(100, 1, 200, 1));

        txtAddWSTypeName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtAddWSTypeName.setToolTipText("");

        txtAddWSTypeDescription.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtAddWSTypeDescription.setToolTipText("");

        cmbAddWSTypeStatus.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cmbAddWSTypeStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enabled", "Disabled", "Suspended" }));

        btnAddWSTypeOK.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAddWSTypeOK.setText("OK");
        btnAddWSTypeOK.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAddWSTypeOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWSTypeOKActionPerformed(evt);
            }
        });

        btnAddWSTypeCancel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAddWSTypeCancel.setText("Cancel");
        btnAddWSTypeCancel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAddWSTypeCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddWSTypeCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAddWSTypeLayout = new javax.swing.GroupLayout(pnlAddWSType);
        pnlAddWSType.setLayout(pnlAddWSTypeLayout);
        pnlAddWSTypeLayout.setHorizontalGroup(
            pnlAddWSTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAddWSTypeTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
            .addGroup(pnlAddWSTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAddWSTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlAddWSTypeLayout.createSequentialGroup()
                        .addGroup(pnlAddWSTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAddWSTypeMinRate)
                            .addComponent(lblAddWSTypeName)
                            .addComponent(lblAddWSTypeDescription)
                            .addComponent(lblAddWSTypeStatus))
                        .addGap(18, 18, 18)
                        .addGroup(pnlAddWSTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbAddWSTypeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddWSTypeDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddWSTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnAddWSTypeMinRate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlAddWSTypeLayout.createSequentialGroup()
                        .addComponent(btnAddWSTypeOK, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddWSTypeCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAddWSTypeLayout.setVerticalGroup(
            pnlAddWSTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddWSTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddWSTypeTitle)
                .addGap(18, 18, 18)
                .addGroup(pnlAddWSTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddWSTypeName)
                    .addComponent(txtAddWSTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddWSTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddWSTypeDescription)
                    .addComponent(txtAddWSTypeDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddWSTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddWSTypeMinRate)
                    .addComponent(spnAddWSTypeMinRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddWSTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddWSTypeStatus)
                    .addComponent(cmbAddWSTypeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddWSTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddWSTypeOK)
                    .addComponent(btnAddWSTypeCancel))
                .addContainerGap(152, Short.MAX_VALUE))
        );

        setBackground(java.awt.Color.white);
        setMinimumSize(new java.awt.Dimension(1185, 715));

        lblScreenTitle.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        lblScreenTitle.setForeground(java.awt.Color.red);
        lblScreenTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScreenTitle.setText("Workstation's Types Management");
        lblScreenTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblScreenTitle.setName("lblScreenTitle"); // NOI18N

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

        lblMinScoreFilter.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblMinScoreFilter.setText("Minimal Score Filter:");

        cmbMinScoreFilter.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        cmbMinScoreFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Show All", "Greater then 100", "Less Then 100" }));
        cmbMinScoreFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMinScoreFilterActionPerformed(evt);
            }
        });

        tblTypes.setAutoCreateRowSorter(true);
        tblTypes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblTypes.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        tblTypes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Description", "Minimal Score", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblTypes.setName("tblTypes"); // NOI18N
        tblTypes.setNextFocusableComponent(btnNewWorkstationType);
        tblTypes.setOpaque(false);
        tblTypes.setRowHeight(32);
        jScrollPane1.setViewportView(tblTypes);
        if (tblTypes.getColumnModel().getColumnCount() > 0) {
            tblTypes.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblTypes.getColumnModel().getColumn(1).setPreferredWidth(400);
            tblTypes.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblTypes.getColumnModel().getColumn(3).setPreferredWidth(100);
        }
        tblTypes.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 24));
        /*tblTypes.getRowSorter().addRowSorterListener(
            new RowSorterListener() {
                @Override
                public void sorterChanged (RowSorterEvent rse) {
                    if (rse.getType() == Type.SORTED)
                    fixIndexes();
                }
            });*/

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

            btnNewWorkstationType.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
            btnNewWorkstationType.setText("New Workstation Type");
            btnNewWorkstationType.setToolTipText("Add new system user");
            btnNewWorkstationType.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            btnNewWorkstationType.setMaximumSize(new java.awt.Dimension(99, 33));
            btnNewWorkstationType.setMinimumSize(new java.awt.Dimension(99, 33));
            btnNewWorkstationType.setPreferredSize(new java.awt.Dimension(99, 33));
            btnNewWorkstationType.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnNewWorkstationTypeActionPerformed(evt);
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
                        .addComponent(lblScreenTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(111, 111, 111)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblFilterStr)
                                .addComponent(lblFilterBy))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtFilterStr, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbFilterBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(chbEnabledOnly)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblMinScoreFilter)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmbMinScoreFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(109, 109, 109)
                            .addComponent(btnNewWorkstationType, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(101, 101, 101)
                            .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblScreenTitle)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblFilterBy)
                                .addComponent(cmbFilterBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(chbEnabledOnly, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFilterStr)
                        .addComponent(txtFilterStr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMinScoreFilter)
                        .addComponent(cmbMinScoreFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNewWorkstationType, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }// </editor-fold>//GEN-END:initComponents

    private void cmbFilterByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilterByActionPerformed
        if (doneInit) {
            fltrField = cmbFilterBy.getSelectedIndex();
            fltrStirng = txtFilterStr.getText();
            if (fltrStirng == null) fltrStirng = "";
            applyFilter();
        }
    }//GEN-LAST:event_cmbFilterByActionPerformed

    private void txtFilterStrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFilterStrFocusLost
        if (doneInit) {
            fltrStirng = txtFilterStr.getText();
            if (fltrStirng == null) fltrStirng = "";
            applyFilter();
        }
    }//GEN-LAST:event_txtFilterStrFocusLost

    private void txtFilterStrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFilterStrActionPerformed
        if (doneInit) {
            fltrStirng = txtFilterStr.getText();
            if (fltrStirng == null) fltrStirng = "";
            applyFilter();
        }
    }//GEN-LAST:event_txtFilterStrActionPerformed

    private void chbEnabledOnlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEnabledOnlyActionPerformed
        if (doneInit) {
            fltrEnabled = chbEnabledOnly.isSelected();
            applyFilter();
        }
    }//GEN-LAST:event_chbEnabledOnlyActionPerformed

    private void cmbMinScoreFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMinScoreFilterActionPerformed
        if (doneInit) {
            fltrMinScore = cmbMinScoreFilter.getSelectedIndex();
            applyFilter();
        }
    }//GEN-LAST:event_cmbMinScoreFilterActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        WorkstationCTRL.searchResaultCloseBtnPressed();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnNewWorkstationTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewWorkstationTypeActionPerformed
        isUpdate = false;
        clearFields();
        onScreenWSTypeID = 0;
        addWSType = new FormFrame();
        addWSType.setSize(pnlAddWSType.getMinimumSize());
        addWSType.setLocationRelativeTo(null);
        lblAddWSTypeTitle.setText("Add New Workstation Type");
        addWSType.getContentPane().add(pnlAddWSType);
        addWSType.addWindowListener(exitListener);
        addWSType.getContentPane().setVisible(true);
        PCNMClientStart.appWindow.setEnabled(false);
        addWSType.setVisible(true);
    }//GEN-LAST:event_btnNewWorkstationTypeActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        selected = tblTypes.getSelectedRow();
        if (selected == -1) {
            showDialog(this, "Please select Workstation Type", DialogType.INFO);
            return;
        }
        isUpdate = true;
        int index = tblTypes.convertRowIndexToModel(selected);
        onScreenWSTypeID = Integer.parseInt(tableContent[index][0]);
        txtAddWSTypeName.setText(tableContent[index][1]);
        txtAddWSTypeDescription.setText(tableContent[index][2]);
        spnAddWSTypeMinRate.setValue(Integer.parseInt(tableContent[index][3]));
        cmbAddWSTypeStatus.setSelectedIndex(getStatusIndex(tableContent[index][4]));
        addWSType = new FormFrame();
        lblAddWSTypeTitle.setText("Update Workstation Type");
        addWSType.setSize(pnlAddWSType.getMinimumSize());
        addWSType.setLocationRelativeTo(null);
        addWSType.getContentPane().add(pnlAddWSType);
        addWSType.addWindowListener(exitListener);
        addWSType.getContentPane().setVisible(true);
        PCNMClientStart.appWindow.setEnabled(false);
        addWSType.setVisible(true);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        WorkstationCTRL.QuitBtnPressed();
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnAddWSTypeOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWSTypeOKActionPerformed
        String name = txtAddWSTypeName.getText();
        String description = txtAddWSTypeDescription.getText();
        int min_rate = (Integer)spnAddWSTypeMinRate.getValue();
        String status = (String)cmbAddWSTypeStatus.getSelectedItem();
        if (name.isEmpty() || description.isEmpty() || status.isEmpty()) {
            showDialog(pnlAddWSType, "All fields are mandatory.", DialogType.INFO);
            return;
        }
        
        for (String[] type : tableContent) {
            if (onScreenWSTypeID != Integer.parseInt(type[0]) && type[1].equalsIgnoreCase(name)) {
                showDialog(pnlAddWSType, "Workstation Type name must be unique.", DialogType.INFO);
                return;
            }
        }
        
        try {
            if (!isUpdate) {
                WorkstationCTRL.AddWSTypeOKBtnPressed(name, description, min_rate, status);
            } else {
                WorkstationCTRL.UpdateWSTypeOKBtnPressed(onScreenWSTypeID, name, description, min_rate, status);
            }
        } catch (IOException ex) {
            showDialog(pnlAddWSType, "Lost Connection with the server", DialogType.ERROR);
            System.exit(0);
        }
        clearFields();
        addWSType.dispose();
        PCNMClientStart.appWindow.setEnabled(true);
        PCNMClientStart.appWindow.requestFocus();
    }//GEN-LAST:event_btnAddWSTypeOKActionPerformed

    private void btnAddWSTypeCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddWSTypeCancelActionPerformed
        clearFields();
        addWSType.dispose();
        PCNMClientStart.appWindow.setEnabled(true);
        PCNMClientStart.appWindow.requestFocus();
    }//GEN-LAST:event_btnAddWSTypeCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddWSTypeCancel;
    private javax.swing.JButton btnAddWSTypeOK;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnNewWorkstationType;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox chbEnabledOnly;
    private javax.swing.JComboBox cmbAddWSTypeStatus;
    private javax.swing.JComboBox cmbFilterBy;
    private javax.swing.JComboBox cmbMinScoreFilter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddWSTypeDescription;
    private javax.swing.JLabel lblAddWSTypeMinRate;
    private javax.swing.JLabel lblAddWSTypeName;
    private javax.swing.JLabel lblAddWSTypeStatus;
    private javax.swing.JLabel lblAddWSTypeTitle;
    private javax.swing.JLabel lblFilterBy;
    private javax.swing.JLabel lblFilterStr;
    private javax.swing.JLabel lblMinScoreFilter;
    private javax.swing.JLabel lblScreenTitle;
    private javax.swing.JPanel pnlAddWSType;
    private javax.swing.JSpinner spnAddWSTypeMinRate;
    private javax.swing.JTable tblTypes;
    private javax.swing.JTextField txtAddWSTypeDescription;
    private javax.swing.JTextField txtAddWSTypeName;
    private javax.swing.JTextField txtFilterStr;
    // End of variables declaration//GEN-END:variables

    private void loadSearchResults() {
        String row;
        DefaultTableModel dtm = (DefaultTableModel)tblTypes.getModel();
        dtm.setRowCount(rowCounter);
        int cur_row = 0;
        for (int i = 0 ; i < wst_rows.size() ; i ++) {
            if (rowsToShow[i]) {
                row = wst_rows.get(i);
                tableContent[i] = row.split(",");
                dtm.setValueAt(tableContent[i][1], cur_row, 0);
                dtm.setValueAt(tableContent[i][2], cur_row, 1);
                dtm.setValueAt(Integer.parseInt(tableContent[i][3]), cur_row, 2);
                dtm.setValueAt(tableContent[i][4], cur_row, 3);
                cur_row ++;
            }
            if (cur_row > rowCounter)
                i = wst_rows.size();
        }
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        tblTypes.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
    }

    private void applyFilter() {
        Arrays.fill(rowsToShow, true);
        rowCounter = tableContent.length;
        for (int i = 0 ; i < tableContent.length ; i ++) {
            if (rowsToShow[i] && fltrField == 1 && !fltrStirng.isEmpty() && tableContent[i][1].toLowerCase().indexOf(fltrStirng) == -1) rowsToShow[i] = false;
            if (rowsToShow[i] && fltrField == 2 && !fltrStirng.isEmpty() && tableContent[i][2].toLowerCase().indexOf(fltrStirng) == -1) rowsToShow[i] = false;
            if (rowsToShow[i] && fltrEnabled && !tableContent[i][4].equals("Enabled")) rowsToShow[i] = false;
            if (rowsToShow[i] && fltrMinScore == 1 && Integer.parseInt(tableContent[i][3]) < 100) rowsToShow[i] = false;
            if (rowsToShow[i] && fltrMinScore == 2 && Integer.parseInt(tableContent[i][3]) > 100) rowsToShow[i] = false;
            if (!rowsToShow[i]) rowCounter --;
        }
            loadSearchResults();
    }

    private void clearFields() {
            txtAddWSTypeName.setText("");
            txtAddWSTypeDescription.setText("");
            spnAddWSTypeMinRate.setValue(new Integer(100));
            cmbAddWSTypeStatus.setSelectedIndex(0);
        }

        private WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clearFields();
                addWSType.dispose();
                PCNMClientStart.appWindow.setEnabled(true);
                PCNMClientStart.appWindow.requestFocus();
            }
        };
        
    private int getStatusIndex(String stsStr) {
        switch (stsStr) {
            case "Enabled":
                return 0;
            case "Disabled":
                return 1;
            case "Suspended":
                return 2;
        }
        return -1;
    }

}
