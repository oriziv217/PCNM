package PCNMServer;

import PCNMServer.ServerLogic.PCNMServerLogic;
import java.io.IOException;

/**
 * This class implements the server's main screen
 * @author Ori Ziv
 */
public class PCNMServerMainSCR extends javax.swing.JFrame {

    /**
     * Creates new form PCNMServerMainSCR
     */
    public PCNMServerMainSCR() {
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

        lblTitle = new javax.swing.JLabel();
        lblSrvSettings = new javax.swing.JLabel();
        lblServerPort = new javax.swing.JLabel();
        lblDBSettings = new javax.swing.JLabel();
        lblDBSrvName = new javax.swing.JLabel();
        lblDBName = new javax.swing.JLabel();
        lblDBUserName = new javax.swing.JLabel();
        lblDBPSWD = new javax.swing.JLabel();
        txtSrvPort = new javax.swing.JTextField();
        btnStartStopSrv = new javax.swing.JButton();
        txtDBSrvName = new javax.swing.JTextField();
        txtDBName = new javax.swing.JTextField();
        txtDBUserName = new javax.swing.JTextField();
        pswdDBPassword = new javax.swing.JPasswordField();
        btnConnectDB = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtareaSRVConsole = new javax.swing.JTextArea();
        lblSrvConsole = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PCNM Server");
        setBounds(new java.awt.Rectangle(300, 300, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("MainSCR"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("David", 1, 24)); // NOI18N
        lblTitle.setForeground(java.awt.Color.red);
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("PCNM Server");
        lblTitle.setName("lblTitle"); // NOI18N

        lblSrvSettings.setFont(new java.awt.Font("David", 1, 18)); // NOI18N
        lblSrvSettings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSrvSettings.setText("Server Settings:");
        lblSrvSettings.setName("lblSrvSettings"); // NOI18N

        lblServerPort.setFont(new java.awt.Font("David", 1, 14)); // NOI18N
        lblServerPort.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServerPort.setText("Server Port: ");
        lblServerPort.setName("lblServerPort"); // NOI18N

        lblDBSettings.setFont(new java.awt.Font("David", 1, 18)); // NOI18N
        lblDBSettings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDBSettings.setText("Database Connection:");
        lblDBSettings.setName("lblDBSettings"); // NOI18N

        lblDBSrvName.setFont(new java.awt.Font("David", 1, 14)); // NOI18N
        lblDBSrvName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDBSrvName.setText("DB Server name: ");
        lblDBSrvName.setName("lblDBSrvName"); // NOI18N

        lblDBName.setFont(new java.awt.Font("David", 1, 14)); // NOI18N
        lblDBName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDBName.setText("DB name: ");
        lblDBName.setName("lblDBName"); // NOI18N

        lblDBUserName.setFont(new java.awt.Font("David", 1, 14)); // NOI18N
        lblDBUserName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDBUserName.setText("DB User Name: ");
        lblDBUserName.setName("lblDBUserName"); // NOI18N

        lblDBPSWD.setFont(new java.awt.Font("David", 1, 14)); // NOI18N
        lblDBPSWD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDBPSWD.setText("DB Password: ");
        lblDBPSWD.setName("lblDBPSWD"); // NOI18N

        txtSrvPort.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSrvPort.setText("11111");
        txtSrvPort.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtSrvPort.setName("txtSrvPort"); // NOI18N

        btnStartStopSrv.setFont(new java.awt.Font("David", 1, 12)); // NOI18N
        btnStartStopSrv.setText("Start/Stop Server");
        btnStartStopSrv.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnStartStopSrv.setName("btnStartStopSrv"); // NOI18N
        btnStartStopSrv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartStopSrvActionPerformed(evt);
            }
        });

        txtDBSrvName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDBSrvName.setText("localhost");
        txtDBSrvName.setToolTipText("DB server name or IP address");
        txtDBSrvName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtDBSrvName.setName("txtDBSrvName"); // NOI18N

        txtDBName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDBName.setText("pcnm");
        txtDBName.setToolTipText("DB schema name");
        txtDBName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtDBName.setName("txtDBName"); // NOI18N

        txtDBUserName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDBUserName.setText("root");
        txtDBUserName.setToolTipText("DB authorized user name");
        txtDBUserName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtDBUserName.setName("txtDBUserName"); // NOI18N

        pswdDBPassword.setText("123456");
        pswdDBPassword.setToolTipText("DB authorized user password");
        pswdDBPassword.setName("pswdDBPassword"); // NOI18N

        btnConnectDB.setFont(new java.awt.Font("David", 1, 12)); // NOI18N
        btnConnectDB.setText("Connect to DB");
        btnConnectDB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnConnectDB.setName("btnConnectDB"); // NOI18N
        btnConnectDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectDBActionPerformed(evt);
            }
        });

        txtareaSRVConsole.setColumns(20);
        txtareaSRVConsole.setFont(new java.awt.Font("Courier New", 0, 10)); // NOI18N
        txtareaSRVConsole.setRows(5);
        txtareaSRVConsole.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtareaSRVConsole.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtareaSRVConsole.setFocusable(false);
        txtareaSRVConsole.setName("txtareaSRVConsole"); // NOI18N
        txtareaSRVConsole.setEditable(false);
        jScrollPane1.setViewportView(txtareaSRVConsole);

        lblSrvConsole.setFont(new java.awt.Font("David", 1, 18)); // NOI18N
        lblSrvConsole.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSrvConsole.setText("Server Console:");
        lblSrvConsole.setName("lblSrvConsole"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblSrvSettings)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblServerPort)
                                                .addGap(42, 42, 42)
                                                .addComponent(txtSrvPort, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(78, 78, 78))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(btnStartStopSrv, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDBSettings)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDBSrvName)
                                            .addComponent(lblDBName)
                                            .addComponent(lblDBUserName)
                                            .addComponent(lblDBPSWD))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtDBUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                            .addComponent(txtDBName, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                            .addComponent(txtDBSrvName)
                                            .addComponent(pswdDBPassword)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(btnConnectDB, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(lblTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSrvConsole)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSrvSettings)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblServerPort)
                            .addComponent(txtSrvPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDBSettings)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDBSrvName)
                            .addComponent(txtDBSrvName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDBName)
                            .addComponent(txtDBName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDBUserName)
                            .addComponent(txtDBUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDBPSWD)
                            .addComponent(pswdDBPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnStartStopSrv, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConnectDB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSrvConsole)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblDBSrvName.getAccessibleContext().setAccessibleName("lblDBSrvName");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartStopSrvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartStopSrvActionPerformed
        String port = txtSrvPort.getText();
        boolean listens;
        // if server is not listennig - start it
        if (PCNMServerLogic.pcnm == null || PCNMServerLogic.pcnm.isListening() == false) {
            if (port == null || port.isEmpty()) {
                txtareaSRVConsole.append("Server port is a mandatory field\n");
                return;
            }
            try {
                PCNMServerLogic.initServer(port);
            } catch (NumberFormatException e) {
                txtareaSRVConsole.append(String.format("%s is not a valid port\n", port));
                return;
            }
        }
        // revert server listening status
        try {
            listens = PCNMServerLogic.startStopListening();
        } catch (IOException e) {
            txtareaSRVConsole.append(e.getMessage());
            return;
        }
        if (listens)
            txtareaSRVConsole.append(String.format("Server is up and listening to port %s\n", port));
        else
            txtareaSRVConsole.append("Server is down\n");
    }//GEN-LAST:event_btnStartStopSrvActionPerformed

    private void btnConnectDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectDBActionPerformed
        String DBSrv;
        String DBName;
        String DBUser;
        char [] DBPswd;
        try {
            DBSrv = txtDBSrvName.getText();
            DBName = txtDBName.getText();
            DBUser = txtDBUserName.getText();
            DBPswd = pswdDBPassword.getPassword();
        } catch (NullPointerException e) {
            txtareaSRVConsole.append("All DB Connection Settings fields are mandatory\n");
            return;
        }
        if (DBSrv.isEmpty() || DBName.isEmpty() || DBUser.isEmpty() || DBPswd.length == 0) {
            txtareaSRVConsole.append("All DB Connection Settings fields are mandatory\n");
            return;
        }
        PCNMServerLogic.setDBParams(DBSrv, DBName, DBUser, DBPswd);
        txtareaSRVConsole.append("DB connection parameters loaded\n");
    }//GEN-LAST:event_btnConnectDBActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (PCNMServerLogic.pcnm != null && PCNMServerLogic.pcnm.isListening() == true)
            try {
                PCNMServerLogic.pcnm.close();
        } catch (IOException ex) {}
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PCNMServerMainSCR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PCNMServerMainSCR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PCNMServerMainSCR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PCNMServerMainSCR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PCNMServerMainSCR().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnectDB;
    private javax.swing.JButton btnStartStopSrv;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDBName;
    private javax.swing.JLabel lblDBPSWD;
    private javax.swing.JLabel lblDBSettings;
    private javax.swing.JLabel lblDBSrvName;
    private javax.swing.JLabel lblDBUserName;
    private javax.swing.JLabel lblServerPort;
    private javax.swing.JLabel lblSrvConsole;
    private javax.swing.JLabel lblSrvSettings;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPasswordField pswdDBPassword;
    private javax.swing.JTextField txtDBName;
    private javax.swing.JTextField txtDBSrvName;
    private javax.swing.JTextField txtDBUserName;
    private javax.swing.JTextField txtSrvPort;
    private javax.swing.JTextArea txtareaSRVConsole;
    // End of variables declaration//GEN-END:variables
}
