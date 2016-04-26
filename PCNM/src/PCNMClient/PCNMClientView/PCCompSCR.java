package PCNMClient.PCNMClientView;

import PCNMClient.PCNMClientController.ComponentCTRL;
import static PCNMClient.PCNMClientView.WindowMustHave.showDialog;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Window;

/**
 * This class implements PC-Components search screen
 * @author ori ziv
 */
public class PCCompSCR extends javax.swing.JPanel {

    /**
     * Creates new form PCCompSCR
     */
    public PCCompSCR() {
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

        lblScreenTitle = new javax.swing.JLabel();
        lblSearchTitle = new javax.swing.JLabel();
        lblNoFilter = new javax.swing.JLabel();
        lblSearchName = new javax.swing.JLabel();
        lblSearchDescription = new javax.swing.JLabel();
        lblSearchPrice = new javax.swing.JLabel();
        lblSearchValAdd = new javax.swing.JLabel();
        lblSearchAddVal1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        cmbMoreLess = new javax.swing.JComboBox();
        spnPrice = new javax.swing.JSpinner();
        cmbMoreLessValAdd = new javax.swing.JComboBox();
        spnValAd = new javax.swing.JSpinner();
        cmbStatus = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setBackground(java.awt.Color.white);
        setMinimumSize(new java.awt.Dimension(800, 650));

        lblScreenTitle.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        lblScreenTitle.setForeground(java.awt.Color.red);
        lblScreenTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScreenTitle.setText("PC Components Management");
        lblScreenTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblScreenTitle.setName("lblScreenTitle"); // NOI18N

        lblSearchTitle.setBackground(java.awt.Color.white);
        lblSearchTitle.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lblSearchTitle.setForeground(java.awt.Color.red);
        lblSearchTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearchTitle.setText("Search For PC Components");

        lblNoFilter.setBackground(java.awt.Color.white);
        lblNoFilter.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNoFilter.setForeground(java.awt.Color.red);
        lblNoFilter.setText("Leave text fields empty for all PC Components");

        lblSearchName.setBackground(java.awt.Color.white);
        lblSearchName.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblSearchName.setText("Search by Component name:");

        lblSearchDescription.setBackground(java.awt.Color.white);
        lblSearchDescription.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblSearchDescription.setText("Search by Component Description:");

        lblSearchPrice.setBackground(java.awt.Color.white);
        lblSearchPrice.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblSearchPrice.setText("Search by Component Price:");

        lblSearchValAdd.setBackground(java.awt.Color.white);
        lblSearchValAdd.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblSearchValAdd.setText("Search by Component Added Value:");

        lblSearchAddVal1.setBackground(java.awt.Color.white);
        lblSearchAddVal1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblSearchAddVal1.setText("Search by Component Status:");

        txtName.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtDescription.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        cmbMoreLess.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        cmbMoreLess.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "More Then", "Less Then" }));
        cmbMoreLess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMoreLessActionPerformed(evt);
            }
        });

        spnPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        spnPrice.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        spnPrice.setEnabled(false);

        cmbMoreLessValAdd.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        cmbMoreLessValAdd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "More Then", "Less Then" }));
        cmbMoreLessValAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMoreLessValAddActionPerformed(evt);
            }
        });

        spnValAd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        spnValAd.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(1.99f), Float.valueOf(0.1f)));
        spnValAd.setEnabled(false);

        cmbStatus.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Any Status", "Enabled", "Disabled", "Suspended" }));

        btnSearch.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.setToolTipText("Search PC component");
        btnSearch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSearch.setInheritsPopupMenu(true);
        btnSearch.setName("btnSearch"); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblScreenTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
            .addComponent(lblSearchTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNoFilter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSearchValAdd)
                                    .addComponent(lblSearchAddVal1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmbMoreLessValAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(spnValAd))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSearchName)
                                .addGap(91, 91, 91)
                                .addComponent(txtName))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSearchDescription)
                                .addGap(32, 32, 32)
                                .addComponent(txtDescription))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSearchPrice)
                                .addGap(93, 93, 93)
                                .addComponent(cmbMoreLess, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(spnPrice)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblScreenTitle)
                .addGap(18, 18, 18)
                .addComponent(lblSearchTitle)
                .addGap(18, 18, 18)
                .addComponent(lblNoFilter)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchDescription)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchPrice)
                    .addComponent(cmbMoreLess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchValAdd)
                    .addComponent(cmbMoreLessValAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnValAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchAddVal1)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        if (ComponentCTRL.getNameFilter() != null)
        txtName.setText(ComponentCTRL.getNameFilter());
        if (ComponentCTRL.getDescriptionFilter() != null)
        txtDescription.setText(ComponentCTRL.getDescriptionFilter());
        cmbMoreLess.setSelectedIndex(ComponentCTRL.getPriceCmb());
        spnPrice.setValue(ComponentCTRL.getPriceFilter());
        cmbMoreLessValAdd.setSelectedIndex(ComponentCTRL.getValAddCmb());
        spnValAd.setValue(ComponentCTRL.getValAddFilter());
        cmbStatus.setSelectedIndex(ComponentCTRL.getStatusCmb());
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String name = txtName.getText();
        String description = txtDescription.getText();
        int priceIDX = cmbMoreLess.getSelectedIndex();
        float price;
        if (priceIDX == -1) price = -1;
        else price = (Float)spnPrice.getValue();
        int valAddIDX = cmbMoreLessValAdd.getSelectedIndex();
        float valAdd;
        if (valAddIDX == -1) valAdd = -1;
        else valAdd = (Float)spnValAd.getValue();
        int status = cmbStatus.getSelectedIndex();
        ComponentCTRL.setAllFilters(name, description, priceIDX, price, valAddIDX, valAdd, status);
        try {
            ComponentCTRL.searchBtnPressed();
        } catch (IOException ex) {
            showDialog(this, "Lost Connection with the server", DialogType.ERROR);
            System.exit(0);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        ComponentCTRL.QuitBtnPressed();
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        Window window = SwingUtilities.getWindowAncestor(this);
        JFrame frame = (JFrame) window;
        frame.setTitle("PCNM - Network Mapping");
        ComponentCTRL.closeBtnPressed();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void cmbMoreLessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMoreLessActionPerformed
        int selected = cmbMoreLess.getSelectedIndex();
        if (selected == -1) return;
        if (selected == 0) spnPrice.setEnabled(false);
        else spnPrice.setEnabled(true);
    }//GEN-LAST:event_cmbMoreLessActionPerformed

    private void cmbMoreLessValAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMoreLessValAddActionPerformed
        int selected = cmbMoreLessValAdd.getSelectedIndex();
        if (selected == -1) return;
        if (selected == 0) spnValAd.setEnabled(false);
        else spnValAd.setEnabled(true);
    }//GEN-LAST:event_cmbMoreLessValAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cmbMoreLess;
    private javax.swing.JComboBox cmbMoreLessValAdd;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JLabel lblNoFilter;
    private javax.swing.JLabel lblScreenTitle;
    private javax.swing.JLabel lblSearchAddVal1;
    private javax.swing.JLabel lblSearchDescription;
    private javax.swing.JLabel lblSearchName;
    private javax.swing.JLabel lblSearchPrice;
    private javax.swing.JLabel lblSearchTitle;
    private javax.swing.JLabel lblSearchValAdd;
    private javax.swing.JSpinner spnPrice;
    private javax.swing.JSpinner spnValAd;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
