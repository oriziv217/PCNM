package PCNMClient.PCNMClientView;

import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Ori Ziv
 */
public class UserTypeSCR extends javax.swing.JPanel {

    private SpinnerNumberModel spnImportance = new SpinnerNumberModel(1.0, 0.1, 1.9, 0.1);
    /**
     * Creates new form UserTypeSCR
     */
    public UserTypeSCR() {
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

        txtCellEditor = new javax.swing.JTextField();
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();

        txtCellEditor.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        setMinimumSize(new java.awt.Dimension(1020, 423));

        pnlTitle.setBackground(java.awt.Color.white);
        pnlTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setBackground(java.awt.Color.white);
        lblTitle.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        lblTitle.setForeground(java.awt.Color.red);
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("PC-User's type management");
        pnlTitle.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(243, 11, -1, -1));

        pnlTable.setBackground(java.awt.Color.white);

        tblUsers.setAutoCreateRowSorter(true);
        tblUsers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblUsers.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblUsers.setCellSelectionEnabled(true);
        tblUsers.setRowHeight(32);
        jScrollPane1.setViewportView(tblUsers);
        tblUsers.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tblUsers.getColumnModel().getColumnCount() > 0) {
            tblUsers.getColumnModel().getColumn(0).setMinWidth(200);
            tblUsers.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblUsers.getColumnModel().getColumn(0).setMaxWidth(200);
            tblUsers.getColumnModel().getColumn(1).setMinWidth(600);
            tblUsers.getColumnModel().getColumn(1).setPreferredWidth(600);
            tblUsers.getColumnModel().getColumn(1).setMaxWidth(600);
            tblUsers.getColumnModel().getColumn(2).setMinWidth(100);
            tblUsers.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblUsers.getColumnModel().getColumn(2).setMaxWidth(100);
            tblUsers.getColumnModel().getColumn(3).setMinWidth(100);
            tblUsers.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblUsers.getColumnModel().getColumn(3).setMaxWidth(100);
        }
        /*if (tblUsers.getColumnModel().getColumnCount() > 0) {
            tblUsers.getColumnModel().getColumn(2).setCellEditor(new SpinnerCellEditor(1.0, 0.1, 1.9, 0.1));
        }*/
        tblUsers.getColumn("Importance").setCellRenderer(new SpinnerCellRenderer());
        tblUsers.getColumn("Importance").setCellEditor(new SpinnerCellEditor());

        javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(pnlTable);
        pnlTable.setLayout(pnlTableLayout);
        pnlTableLayout.setHorizontalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnlTableLayout.setVerticalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 91, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
            .addComponent(pnlTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txtCellEditor;
    // End of variables declaration//GEN-END:variables
}
