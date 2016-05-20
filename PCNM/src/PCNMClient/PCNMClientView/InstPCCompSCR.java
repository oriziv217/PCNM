package PCNMClient.PCNMClientView;

import PCNMClient.PCNMClientController.PCCTRL;
import static PCNMClient.PCNMClientView.WindowMustHave.showDialog;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import java.awt.Font;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ori ziv
 */
public class InstPCCompSCR extends javax.swing.JPanel {

    private boolean doneInit;
    private String PCData;
    private ArrayList<String> compEnaList;
    private ArrayList<String> compInstList;
    private int enaCompRowCounter;
    private int instCompRowCounter;
    private boolean[] selectedEnaComps;
    private boolean[] selectedInstComps;
    private String[][] enaCompTableContent;
    private ArrayList<String[]> instCompTableContent;
    
    /**
     * Creates new form InstPCCompSCR
     */
    public InstPCCompSCR() {
        doneInit = false;
        compEnaList = new ArrayList<String>();
        compInstList = new ArrayList<String>();
        
        initComponents();
        doneInit = true;
    }

    public InstPCCompSCR(ArrayList<String> enaComp_tbl, String PC_String, ArrayList<String> instComp_tbl) {
        this();
        doneInit = false;
        compEnaList = enaComp_tbl;
        PCData = PC_String;
        compInstList = instComp_tbl;
        enaCompRowCounter = compEnaList.size();
        instCompRowCounter = compInstList.size();
        selectedEnaComps = new boolean[enaCompRowCounter];
        Arrays.fill(selectedEnaComps, false);
        selectedInstComps = new boolean[instCompRowCounter];
        Arrays.fill(selectedInstComps, false);
        enaCompTableContent = new String[enaCompRowCounter][6];
        instCompTableContent = new ArrayList<String[]>();
        loadEnaCompTable();
        setPCData();
        loadInstCompTable();
        calcPCScore();
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

        lblPCCompTitle = new javax.swing.JLabel();
        lbPCCompName = new javax.swing.JLabel();
        txtPCCompName = new javax.swing.JTextField();
        lbPCCompDescription = new javax.swing.JLabel();
        txtPCCompDescription = new javax.swing.JTextField();
        lblPCCompSpecName = new javax.swing.JLabel();
        txtPCCompSpecName = new javax.swing.JTextField();
        lblPCPropertiesSpec1 = new javax.swing.JLabel();
        txtPCCompSpecDescription = new javax.swing.JTextField();
        lblPCPropertiesInstDate1 = new javax.swing.JLabel();
        txtPCCompInstallDate = new javax.swing.JTextField();
        lblPCCompWarrenty = new javax.swing.JLabel();
        txtPCCompWarrenty = new javax.swing.JTextField();
        lblPCCompStatus = new javax.swing.JLabel();
        txtPCCompStatus = new javax.swing.JTextField();
        lblPCCompOriginalPCScore = new javax.swing.JLabel();
        lblPCCompOriginalScoreVal = new javax.swing.JLabel();
        lblPCCompEnaComp = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPCCompEnaComp = new javax.swing.JTable();
        lblPCCompInstComp = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPCCompInstComp = new javax.swing.JTable();
        btnPCCompInstComp = new javax.swing.JButton();
        btnPCCompUninstComp = new javax.swing.JButton();
        btnPCCompApply = new javax.swing.JButton();
        btnPCCompCancel = new javax.swing.JButton();
        lblPCCompAfterPCScore = new javax.swing.JLabel();
        lblPCCompAfterScoreVal = new javax.swing.JLabel();

        setBackground(java.awt.Color.white);
        setMinimumSize(new java.awt.Dimension(1200, 800));
        setPreferredSize(new java.awt.Dimension(1200, 800));

        lblPCCompTitle.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblPCCompTitle.setForeground(java.awt.Color.red);
        lblPCCompTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPCCompTitle.setText("Install/Uninstall Components");
        lblPCCompTitle.setName("lblPCPropertiesTitle"); // NOI18N

        lbPCCompName.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbPCCompName.setText("Name:");

        txtPCCompName.setEditable(false);
        txtPCCompName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtPCCompName.setToolTipText("");

        lbPCCompDescription.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbPCCompDescription.setText("Description:");

        txtPCCompDescription.setEditable(false);
        txtPCCompDescription.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtPCCompDescription.setToolTipText("");

        lblPCCompSpecName.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblPCCompSpecName.setText("Spec' Name:");

        txtPCCompSpecName.setEditable(false);
        txtPCCompSpecName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtPCCompSpecName.setToolTipText("");

        lblPCPropertiesSpec1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblPCPropertiesSpec1.setText("Spec' Desc':");

        txtPCCompSpecDescription.setEditable(false);
        txtPCCompSpecDescription.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtPCCompSpecDescription.setToolTipText("");

        lblPCPropertiesInstDate1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblPCPropertiesInstDate1.setText("Instalation Date:");

        txtPCCompInstallDate.setEditable(false);
        txtPCCompInstallDate.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtPCCompInstallDate.setToolTipText("");

        lblPCCompWarrenty.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblPCCompWarrenty.setText("Warrenty Expiration:");

        txtPCCompWarrenty.setEditable(false);
        txtPCCompWarrenty.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtPCCompWarrenty.setToolTipText("");

        lblPCCompStatus.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblPCCompStatus.setText("PC Status:");

        txtPCCompStatus.setEditable(false);
        txtPCCompStatus.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtPCCompStatus.setToolTipText("");

        lblPCCompOriginalPCScore.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblPCCompOriginalPCScore.setForeground(java.awt.Color.red);
        lblPCCompOriginalPCScore.setText("Original PC Score:");

        lblPCCompOriginalScoreVal.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblPCCompOriginalScoreVal.setForeground(java.awt.Color.red);
        lblPCCompOriginalScoreVal.setText("100");

        lblPCCompEnaComp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblPCCompEnaComp.setText("Available Components:");

        tblPCCompEnaComp.setAutoCreateRowSorter(true);
        tblPCCompEnaComp.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tblPCCompEnaComp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Description", "Price", "Value Add"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPCCompEnaComp.setRowHeight(32);
        jScrollPane2.setViewportView(tblPCCompEnaComp);
        tblPCCompEnaComp.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 24));
        tblPCCompEnaComp.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblPCCompEnaComp.getSelectionModel().addListSelectionListener(tblPCCompEnaCompListListener);

        lblPCCompInstComp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblPCCompInstComp.setText("Installed Components:");

        tblPCCompInstComp.setAutoCreateRowSorter(true);
        tblPCCompInstComp.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tblPCCompInstComp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Description", "Price", "Value Add"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPCCompInstComp.setRowHeight(32);
        jScrollPane3.setViewportView(tblPCCompInstComp);
        tblPCCompInstComp.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 24));
        tblPCCompInstComp.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblPCCompInstComp.getSelectionModel().addListSelectionListener(tblPCCompInstCompListListener);

        btnPCCompInstComp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPCCompInstComp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/RightArrow.jpg"))); // NOI18N
        btnPCCompInstComp.setToolTipText("Install Available Components");
        btnPCCompInstComp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPCCompInstComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPCCompInstCompActionPerformed(evt);
            }
        });

        btnPCCompUninstComp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPCCompUninstComp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/LeftArrow.jpg"))); // NOI18N
        btnPCCompUninstComp.setToolTipText("Uninstall Selecetd Components");
        btnPCCompUninstComp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPCCompUninstComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPCCompUninstCompActionPerformed(evt);
            }
        });

        btnPCCompApply.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPCCompApply.setText("Apply Changes");
        btnPCCompApply.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPCCompApply.setMaximumSize(new java.awt.Dimension(130, 54));
        btnPCCompApply.setMinimumSize(new java.awt.Dimension(130, 54));
        btnPCCompApply.setPreferredSize(new java.awt.Dimension(130, 54));
        btnPCCompApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPCCompApplyActionPerformed(evt);
            }
        });

        btnPCCompCancel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnPCCompCancel.setText("Cancel");
        btnPCCompCancel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPCCompCancel.setMaximumSize(new java.awt.Dimension(130, 54));
        btnPCCompCancel.setMinimumSize(new java.awt.Dimension(130, 54));
        btnPCCompCancel.setPreferredSize(new java.awt.Dimension(130, 54));
        btnPCCompCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPCCompCancelActionPerformed(evt);
            }
        });

        lblPCCompAfterPCScore.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblPCCompAfterPCScore.setForeground(java.awt.Color.red);
        lblPCCompAfterPCScore.setText("After Changes Score:");

        lblPCCompAfterScoreVal.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblPCCompAfterScoreVal.setForeground(java.awt.Color.red);
        lblPCCompAfterScoreVal.setText("100");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPCCompTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPCCompEnaComp)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnPCCompInstComp, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                    .addComponent(btnPCCompUninstComp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPCCompInstComp)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lbPCCompName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPCCompName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbPCCompDescription)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPCCompDescription))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPCCompSpecName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPCCompSpecName, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPCPropertiesSpec1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPCCompSpecDescription))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(lblPCPropertiesInstDate1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPCCompInstallDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPCCompWarrenty))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPCCompOriginalPCScore)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPCCompOriginalScoreVal)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPCCompWarrenty, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPCCompStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPCCompStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPCCompAfterPCScore)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPCCompAfterScoreVal)))
                        .addGap(0, 151, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnPCCompApply, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPCCompCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPCCompTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPCCompName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPCCompDescription)
                    .addComponent(txtPCCompDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPCCompName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPCCompSpecName)
                    .addComponent(txtPCCompSpecName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPCPropertiesSpec1)
                    .addComponent(txtPCCompSpecDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPCPropertiesInstDate1)
                    .addComponent(txtPCCompInstallDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPCCompWarrenty)
                    .addComponent(txtPCCompWarrenty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPCCompStatus)
                    .addComponent(txtPCCompStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPCCompAfterPCScore, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPCCompOriginalPCScore)
                                .addComponent(lblPCCompOriginalScoreVal)
                                .addComponent(lblPCCompAfterScoreVal)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPCCompEnaComp)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(btnPCCompInstComp)
                                .addGap(18, 18, 18)
                                .addComponent(btnPCCompUninstComp))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPCCompInstComp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPCCompApply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPCCompCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPCCompInstCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPCCompInstCompActionPerformed
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String[] pcString = PCData.split(",");
        for (int i = 0 ; i < selectedEnaComps.length ; i ++) {
            if (selectedEnaComps[i]) {
                String[] newInstlation = new String[9];
                newInstlation[0] = enaCompTableContent[i][0];
                newInstlation[1] = enaCompTableContent[i][1];
                newInstlation[2] = enaCompTableContent[i][2];
                newInstlation[3] = enaCompTableContent[i][3];
                newInstlation[4] = enaCompTableContent[i][4];
                newInstlation[5] = sdf.format(cal.getTime());
                newInstlation[6] = "";
                newInstlation[7] = "";
                newInstlation[8] = pcString[8];
                instCompTableContent.add(newInstlation);
            }
        }
        selectedInstComps = new boolean[instCompRowCounter];
        Arrays.fill(selectedInstComps, false);
        refreshInstCompTable();
        calcPCScore();
        doneInit = false;
        tblPCCompEnaComp.clearSelection();
        Arrays.fill(selectedEnaComps, false);
        doneInit = true;
    }//GEN-LAST:event_btnPCCompInstCompActionPerformed

    private void btnPCCompUninstCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPCCompUninstCompActionPerformed
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0 ; i < selectedInstComps.length ; i ++) {
            if (selectedInstComps[i]) {
                String[] row = instCompTableContent.get(i);
                if (row[7].isEmpty()) {
                    instCompTableContent.remove(i);
                } else {
                    row[6] = sdf.format(cal.getTime());
                    instCompTableContent.remove(i);
                    instCompTableContent.add(i, row);
                }
            }
        }
        selectedInstComps = new boolean[instCompRowCounter];
        Arrays.fill(selectedInstComps, false);
        refreshInstCompTable();
        calcPCScore();
        doneInit = false;
        tblPCCompInstComp.clearSelection();
        doneInit = true;
    }//GEN-LAST:event_btnPCCompUninstCompActionPerformed

    private void btnPCCompApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPCCompApplyActionPerformed
        String[] pc = PCData.split(",");
        int pcid = Integer.parseInt(pc[0]);
        ArrayList<String[]> toInstall = new ArrayList<String[]>();
        ArrayList<String[]> toRemove = new ArrayList<String[]>();
        for (String[] row : instCompTableContent) {
            if (row[7].isEmpty())
                toInstall.add(row);
            if (!row[6].isEmpty())
                toRemove.add(row);
        }
        try {
            PCCTRL.addRemoveComp(pcid, toInstall, toRemove);
        } catch (IOException ex) {
            showDialog(this, "Lost Connection with the server", DialogType.ERROR);
            System.exit(0);
        }
    }//GEN-LAST:event_btnPCCompApplyActionPerformed

    private void btnPCCompCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPCCompCancelActionPerformed
        PCCTRL.pcCompCancelBtnPressed();
    }//GEN-LAST:event_btnPCCompCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPCCompApply;
    private javax.swing.JButton btnPCCompCancel;
    private javax.swing.JButton btnPCCompInstComp;
    private javax.swing.JButton btnPCCompUninstComp;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbPCCompDescription;
    private javax.swing.JLabel lbPCCompName;
    private javax.swing.JLabel lblPCCompAfterPCScore;
    private javax.swing.JLabel lblPCCompAfterScoreVal;
    private javax.swing.JLabel lblPCCompEnaComp;
    private javax.swing.JLabel lblPCCompInstComp;
    private javax.swing.JLabel lblPCCompOriginalPCScore;
    private javax.swing.JLabel lblPCCompOriginalScoreVal;
    private javax.swing.JLabel lblPCCompSpecName;
    private javax.swing.JLabel lblPCCompStatus;
    private javax.swing.JLabel lblPCCompTitle;
    private javax.swing.JLabel lblPCCompWarrenty;
    private javax.swing.JLabel lblPCPropertiesInstDate1;
    private javax.swing.JLabel lblPCPropertiesSpec1;
    private javax.swing.JTable tblPCCompEnaComp;
    private javax.swing.JTable tblPCCompInstComp;
    private javax.swing.JTextField txtPCCompDescription;
    private javax.swing.JTextField txtPCCompInstallDate;
    private javax.swing.JTextField txtPCCompName;
    private javax.swing.JTextField txtPCCompSpecDescription;
    private javax.swing.JTextField txtPCCompSpecName;
    private javax.swing.JTextField txtPCCompStatus;
    private javax.swing.JTextField txtPCCompWarrenty;
    // End of variables declaration//GEN-END:variables
    
    private ListSelectionListener tblPCCompEnaCompListListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent lse) {
            if (!doneInit || lse.getValueIsAdjusting()) return;
            ListSelectionModel lsm = (ListSelectionModel)lse.getSource();
            if (lsm.isSelectionEmpty())
                return;
            Arrays.fill(selectedEnaComps, false);
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            DefaultTableModel dtm = (DefaultTableModel)tblPCCompEnaComp.getModel();
            for (int i = minIndex ; i <= maxIndex ; i ++) {
                if (lsm.isSelectedIndex(i)) {
                    String name = (String)dtm.getValueAt(tblPCCompEnaComp.convertRowIndexToModel(i), 0);
                    for (int j = 0 ; j < enaCompTableContent.length ; j ++) {
                        if (enaCompTableContent[j][1].equals(name)) {
                            selectedEnaComps[j] = true;
                            j = enaCompTableContent.length;
                        }
                    }
                }
            }
        }
    };
    
    private ListSelectionListener tblPCCompInstCompListListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent lse) {
            if (!doneInit || lse.getValueIsAdjusting()) return;
            ListSelectionModel lsm = (ListSelectionModel)lse.getSource();
            if (lsm.isSelectionEmpty())
                return;
            Arrays.fill(selectedInstComps, false);
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            DefaultTableModel dtm = (DefaultTableModel)tblPCCompInstComp.getModel();
            for (int i = minIndex ; i <= maxIndex ; i ++) {
                if (lsm.isSelectedIndex(i)) {
                    String name = (String)dtm.getValueAt(tblPCCompInstComp.convertRowIndexToModel(i), 0);
                    for (int j = 0 ; j < instCompTableContent.size() ; j ++) {
                        if (instCompTableContent.get(j)[1].equals(name)) {
                            selectedInstComps[j] = true;
                            j = instCompTableContent.size();
                        }
                    }
                }
            }
        }
    };

    private void loadEnaCompTable() {
        String row;
        DefaultTableModel dtm = (DefaultTableModel)tblPCCompEnaComp.getModel();
        dtm.setRowCount(enaCompRowCounter);
        for (int i = 0 ; i < compEnaList.size() ; i ++) {
            row = compEnaList.get(i);
            enaCompTableContent[i] = row.split(",");
            dtm.setValueAt(enaCompTableContent[i][1], i, 0);
            dtm.setValueAt(enaCompTableContent[i][2], i, 1);
            dtm.setValueAt(Float.parseFloat(enaCompTableContent[i][3]), i, 2);
            dtm.setValueAt(Float.parseFloat(enaCompTableContent[i][4]), i, 3);
        }
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        tblPCCompEnaComp.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
        tblPCCompEnaComp.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
    }

    private void setPCData() {
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String[] pc = PCData.split(",");
        txtPCCompName.setText(pc[1]);
        txtPCCompDescription.setText(pc[2]);
        txtPCCompStatus.setText(pc[4]);
        txtPCCompSpecName.setText(pc[6]);
        txtPCCompDescription.setText(pc[7]);
        txtPCCompInstallDate.setText(pc[3]);
        String[] instDateArr = pc[3].split("/");
        cal.set(Integer.parseInt(instDateArr[2]), Integer.parseInt(instDateArr[1]) - 1, Integer.parseInt(instDateArr[0]));
        cal.add(Calendar.MONTH, Integer.parseInt(pc[8]));
        txtPCCompWarrenty.setText(df.format(cal.getTime()));
    }

    private void loadInstCompTable() {
        String row;
        DefaultTableModel dtm = (DefaultTableModel)tblPCCompInstComp.getModel();
        dtm.setRowCount(instCompRowCounter);
        for (int i = 0 ; i < compInstList.size() ; i ++) {
            row = compInstList.get(i);
            instCompTableContent.add(row.split(","));
            dtm.setValueAt(instCompTableContent.get(i)[1], i, 0);
            dtm.setValueAt(instCompTableContent.get(i)[2], i, 1);
            dtm.setValueAt(Float.parseFloat(instCompTableContent.get(i)[3]), i, 2);
            dtm.setValueAt(Float.parseFloat(instCompTableContent.get(i)[4]), i, 3);
        }
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        tblPCCompInstComp.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
        tblPCCompInstComp.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
    }

    private void refreshInstCompTable() {
        DefaultTableModel dtm = (DefaultTableModel)tblPCCompInstComp.getModel();
        instCompRowCounter = 0;
        dtm.setRowCount(0);
        for (int i = 0 ; i < instCompTableContent.size() ; i ++) {
            String[] row = instCompTableContent.get(i);
            if (row[6].isEmpty()) {
                instCompRowCounter ++;
                Object[] tblRow = new Object[4];
                tblRow[0] = row[1];
                tblRow[1] = row[2];
                tblRow[2] = Float.parseFloat(row[3]);
                tblRow[3] = Float.parseFloat(row[4]);
                dtm.addRow(tblRow);
            }
        }
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        tblPCCompInstComp.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
        tblPCCompInstComp.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
    }
    private void calcPCScore() {
        float score = 0;
        String[] pcStrings = PCData.split(",");
        score = Float.parseFloat(pcStrings[10]);
        for (String[] pccomp : instCompTableContent)
            score *= Float.parseFloat(pccomp[4]);
        if (!doneInit) lblPCCompOriginalScoreVal.setText(String.valueOf(roundFloat(score, 2)));
        lblPCCompAfterScoreVal.setText(String.valueOf(roundFloat(score, 2)));
    }
    
    private float roundFloat(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}