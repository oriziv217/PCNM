package PCNMClient.PCNMClientView;

import PCNMClient.PCNMClientController.TrioCTRL;
import java.awt.Font;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ori ziv
 */
public class TrioPropertiesSCR extends javax.swing.JPanel {

    private ArrayList<String[]> tableContent;
    private float pcTotalScore;
    private float trioTotalScore;
    private float componentsValueAdds[];
    private int pcScore;
    private float wsMultiplier;
    private float pcutMultiplier;

    /**
     * Creates new form TrioPropertiesSCR
     */
    public TrioPropertiesSCR() {
        initComponents();
    }

    public TrioPropertiesSCR(String[] pcStrings, ArrayList<String[]> compStrings, String[] wsStrings, String[] pcutStrings) {
        this();
        txtPCName.setText(pcStrings[1]);
        txtPCDescription.setText(pcStrings[2]);
        txtPCSpecName.setText(pcStrings[6]);
        txtPCSpecDescription.setText(pcStrings[7]);
        txtPCScore.setText(pcStrings[10]);
        pcScore = Integer.parseInt(pcStrings[10]);
        tableContent = compStrings;
        if (tableContent != null) {
            componentsValueAdds = new float[compStrings.size()];
            loadTable();
        }
        
        txtWorkstationName.setText(wsStrings[1]);
        txtWorkstationDescription.setText(wsStrings[2]);
        txtWorkstationImportance.setText(wsStrings[3]);
        wsMultiplier = Float.parseFloat(wsStrings[3]);
        txtWorkstationType.setText(wsStrings[6]);
        
        txtUserTypeName.setText(pcutStrings[1]);
        txtUserTypeDescription.setText(pcutStrings[2]);
        txtUserTypeImportance.setText(pcutStrings[3]);
        pcutMultiplier = Float.parseFloat(pcutStrings[3]);

        calcPCTotalScore();
        calcTrioTotalScore();
        
        txtPCTotalScroe.setText(String.valueOf(pcTotalScore));
        txtTrioTotalScore.setText(String.valueOf(trioTotalScore));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblScreenTitle = new javax.swing.JLabel();
        lblPcName = new javax.swing.JLabel();
        lblPCDescription = new javax.swing.JLabel();
        lblPCSpecName = new javax.swing.JLabel();
        lblPCSpecDescription = new javax.swing.JLabel();
        lblPCScore = new javax.swing.JLabel();
        lblPCComponents = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPCComponents = new javax.swing.JTable();
        txtPCSpecDescription = new javax.swing.JTextField();
        txtPCDescription = new javax.swing.JTextField();
        txtPCName = new javax.swing.JTextField();
        txtPCSpecName = new javax.swing.JTextField();
        txtPCScore = new javax.swing.JTextField();
        lblWorkstationName = new javax.swing.JLabel();
        lblWorkstationDescription = new javax.swing.JLabel();
        lblWorkstationType = new javax.swing.JLabel();
        lblWorkstationImportance = new javax.swing.JLabel();
        txtWorkstationName = new javax.swing.JTextField();
        txtWorkstationDescription = new javax.swing.JTextField();
        txtWorkstationType = new javax.swing.JTextField();
        txtWorkstationImportance = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lblUserTypeName = new javax.swing.JLabel();
        lblUserTypeDescription = new javax.swing.JLabel();
        lblUserTypeImportance = new javax.swing.JLabel();
        txtUserTypeName = new javax.swing.JTextField();
        txtUserTypeDescription = new javax.swing.JTextField();
        txtUserTypeImportance = new javax.swing.JTextField();
        lblTrioTotalScore = new javax.swing.JLabel();
        lblPCTotalScroe = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        txtPCTotalScroe = new javax.swing.JTextField();
        txtTrioTotalScore = new javax.swing.JTextField();

        setBackground(java.awt.Color.white);
        setMinimumSize(new java.awt.Dimension(1475, 735));
        setPreferredSize(new java.awt.Dimension(1475, 735));
        setRequestFocusEnabled(false);

        lblScreenTitle.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        lblScreenTitle.setForeground(java.awt.Color.red);
        lblScreenTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScreenTitle.setText("PC-Workstation-User Type Connection");
        lblScreenTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblScreenTitle.setName("lblScreenTitle"); // NOI18N

        lblPcName.setBackground(java.awt.Color.white);
        lblPcName.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblPcName.setText("PC Name:");

        lblPCDescription.setBackground(java.awt.Color.white);
        lblPCDescription.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblPCDescription.setText("PC Description:");

        lblPCSpecName.setBackground(java.awt.Color.white);
        lblPCSpecName.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblPCSpecName.setText("Specification:");

        lblPCSpecDescription.setBackground(java.awt.Color.white);
        lblPCSpecDescription.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblPCSpecDescription.setText("Spec Description:");

        lblPCScore.setBackground(java.awt.Color.white);
        lblPCScore.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblPCScore.setText("Basic Score:");

        lblPCComponents.setBackground(java.awt.Color.white);
        lblPCComponents.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblPCComponents.setText("Components:");

        tblPCComponents.setAutoCreateRowSorter(true);
        tblPCComponents.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tblPCComponents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Description", "Value Add"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPCComponents.setRowHeight(32);
        jScrollPane1.setViewportView(tblPCComponents);
        if (tblPCComponents.getColumnModel().getColumnCount() > 0) {
            tblPCComponents.getColumnModel().getColumn(0).setMinWidth(212);
            tblPCComponents.getColumnModel().getColumn(0).setPreferredWidth(212);
            tblPCComponents.getColumnModel().getColumn(1).setMinWidth(395);
            tblPCComponents.getColumnModel().getColumn(1).setPreferredWidth(395);
            tblPCComponents.getColumnModel().getColumn(2).setMinWidth(118);
            tblPCComponents.getColumnModel().getColumn(2).setPreferredWidth(118);
        }
        tblPCComponents.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 24));

        txtPCSpecDescription.setEditable(false);
        txtPCSpecDescription.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtPCDescription.setEditable(false);
        txtPCDescription.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtPCName.setEditable(false);
        txtPCName.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtPCSpecName.setEditable(false);
        txtPCSpecName.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtPCScore.setEditable(false);
        txtPCScore.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        lblWorkstationName.setBackground(java.awt.Color.white);
        lblWorkstationName.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblWorkstationName.setText("Workstation Name:");

        lblWorkstationDescription.setBackground(java.awt.Color.white);
        lblWorkstationDescription.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblWorkstationDescription.setText("Workstation Description:");

        lblWorkstationType.setBackground(java.awt.Color.white);
        lblWorkstationType.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblWorkstationType.setText("Workstation Type:");

        lblWorkstationImportance.setBackground(java.awt.Color.white);
        lblWorkstationImportance.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblWorkstationImportance.setText("Workstation Multiplier:");

        txtWorkstationName.setEditable(false);
        txtWorkstationName.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtWorkstationDescription.setEditable(false);
        txtWorkstationDescription.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtWorkstationType.setEditable(false);
        txtWorkstationType.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtWorkstationImportance.setEditable(false);
        txtWorkstationImportance.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        lblUserTypeName.setBackground(java.awt.Color.white);
        lblUserTypeName.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblUserTypeName.setText("User Type:");

        lblUserTypeDescription.setBackground(java.awt.Color.white);
        lblUserTypeDescription.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblUserTypeDescription.setText("User Type Description:");

        lblUserTypeImportance.setBackground(java.awt.Color.white);
        lblUserTypeImportance.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblUserTypeImportance.setText("User Type Multiplier:");

        txtUserTypeName.setEditable(false);
        txtUserTypeName.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtUserTypeDescription.setEditable(false);
        txtUserTypeDescription.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtUserTypeImportance.setEditable(false);
        txtUserTypeImportance.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        lblTrioTotalScore.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        lblTrioTotalScore.setForeground(java.awt.Color.red);
        lblTrioTotalScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrioTotalScore.setText("Connection Total Rate:");
        lblTrioTotalScore.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTrioTotalScore.setName("lblScreenTitle"); // NOI18N

        lblPCTotalScroe.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        lblPCTotalScroe.setForeground(java.awt.Color.red);
        lblPCTotalScroe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPCTotalScroe.setText("PC Total Score:");
        lblPCTotalScroe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPCTotalScroe.setName("lblScreenTitle"); // NOI18N

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

        txtPCTotalScroe.setEditable(false);
        txtPCTotalScroe.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        txtPCTotalScroe.setForeground(java.awt.Color.red);

        txtTrioTotalScore.setEditable(false);
        txtTrioTotalScore.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        txtTrioTotalScore.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblScreenTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblPCSpecDescription)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPCSpecDescription))
                                .addComponent(lblPCComponents)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblPCDescription)
                                        .addComponent(lblPcName)
                                        .addComponent(lblPCSpecName))
                                    .addGap(20, 20, 20)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPCDescription)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtPCName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtPCSpecName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblPCScore)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtPCScore, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPCTotalScroe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPCTotalScroe, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWorkstationDescription)
                            .addComponent(txtUserTypeDescription)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTrioTotalScore)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTrioTotalScore))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblWorkstationName)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtWorkstationName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblWorkstationDescription)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblWorkstationType)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtWorkstationType, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(lblWorkstationImportance)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtWorkstationImportance, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblUserTypeName)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtUserTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblUserTypeDescription)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblUserTypeImportance)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtUserTypeImportance, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblScreenTitle)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPcName)
                            .addComponent(txtPCName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPCDescription)
                            .addComponent(txtPCDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPCSpecName)
                            .addComponent(txtPCSpecName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPCScore)
                            .addComponent(txtPCScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPCSpecDescription)
                            .addComponent(txtPCSpecDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPCComponents)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPCTotalScroe)
                            .addComponent(lblTrioTotalScore)
                            .addComponent(txtPCTotalScroe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTrioTotalScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblWorkstationName)
                            .addComponent(txtWorkstationName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblWorkstationDescription)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtWorkstationDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblWorkstationType)
                            .addComponent(txtWorkstationType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblWorkstationImportance)
                            .addComponent(txtWorkstationImportance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUserTypeName)
                            .addComponent(txtUserTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblUserTypeDescription)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUserTypeDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUserTypeImportance)
                            .addComponent(txtUserTypeImportance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        TrioCTRL.closeBtnPressedPropertiesScreen();
    }//GEN-LAST:event_btnCloseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblPCComponents;
    private javax.swing.JLabel lblPCDescription;
    private javax.swing.JLabel lblPCScore;
    private javax.swing.JLabel lblPCSpecDescription;
    private javax.swing.JLabel lblPCSpecName;
    private javax.swing.JLabel lblPCTotalScroe;
    private javax.swing.JLabel lblPcName;
    private javax.swing.JLabel lblScreenTitle;
    private javax.swing.JLabel lblTrioTotalScore;
    private javax.swing.JLabel lblUserTypeDescription;
    private javax.swing.JLabel lblUserTypeImportance;
    private javax.swing.JLabel lblUserTypeName;
    private javax.swing.JLabel lblWorkstationDescription;
    private javax.swing.JLabel lblWorkstationImportance;
    private javax.swing.JLabel lblWorkstationName;
    private javax.swing.JLabel lblWorkstationType;
    private javax.swing.JTable tblPCComponents;
    private javax.swing.JTextField txtPCDescription;
    private javax.swing.JTextField txtPCName;
    private javax.swing.JTextField txtPCScore;
    private javax.swing.JTextField txtPCSpecDescription;
    private javax.swing.JTextField txtPCSpecName;
    private javax.swing.JTextField txtPCTotalScroe;
    private javax.swing.JTextField txtTrioTotalScore;
    private javax.swing.JTextField txtUserTypeDescription;
    private javax.swing.JTextField txtUserTypeImportance;
    private javax.swing.JTextField txtUserTypeName;
    private javax.swing.JTextField txtWorkstationDescription;
    private javax.swing.JTextField txtWorkstationImportance;
    private javax.swing.JTextField txtWorkstationName;
    private javax.swing.JTextField txtWorkstationType;
    // End of variables declaration//GEN-END:variables

    private void loadTable() {
        DefaultTableModel dtm = (DefaultTableModel)tblPCComponents.getModel();
        dtm.setRowCount(tableContent.size());
        int currentRow = 0;
        for (String[] row : tableContent) {
            dtm.setValueAt(row[1], currentRow, 0);
            dtm.setValueAt(row[2], currentRow, 1);
            componentsValueAdds[currentRow] = Float.parseFloat(row[4]);
            dtm.setValueAt(componentsValueAdds[currentRow], currentRow, 2);
            currentRow ++;
        }
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        tblPCComponents.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
    }

    private void calcPCTotalScore() {
        pcTotalScore = pcScore;
        if (componentsValueAdds != null && componentsValueAdds.length > 0)
            for (float valAdd : componentsValueAdds)
                pcTotalScore *= valAdd;
        pcTotalScore = roundFloat(pcTotalScore, 2);
    }

    private void calcTrioTotalScore() {
        trioTotalScore = pcTotalScore * wsMultiplier * pcutMultiplier;
        trioTotalScore = roundFloat(trioTotalScore, 2);
    }
    
    private float roundFloat(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
