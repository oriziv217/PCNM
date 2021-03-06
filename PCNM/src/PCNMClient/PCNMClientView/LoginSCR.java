package PCNMClient.PCNMClientView;

import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientController.LoginCTRL;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * This class implements login view
 * @author ori ziv
 */
public class LoginSCR extends javax.swing.JFrame {

    /**
     * JPanel getter
     * @return
     */
    public JPanel getPnlLogin() {
        return pnlLogin;
    }

    private String userName = "";
    private char[] password;
    private String server = "";
    private int port = 11111;
    /**
     * Creates new form LoginSCR
     */
    public LoginSCR() {
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

        pnlLogin = new javax.swing.JPanel();
        pswd = new javax.swing.JPasswordField();
        lblServer = new javax.swing.JLabel();
        lblScreenName = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        lblPort = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        lblPswd = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtServer = new javax.swing.JTextField();
        chbxDefaultPort = new javax.swing.JCheckBox();
        txtUserName = new javax.swing.JTextField();
        lblUserName = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PCNM Client - Log in");
        setBounds(new java.awt.Rectangle(300, 300, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("PCNMClient Login Screen"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout(5, 5));

        pnlLogin.setBackground(java.awt.Color.white);
        pnlLogin.setMinimumSize(new java.awt.Dimension(380, 355));
        pnlLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pswd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pswd.setToolTipText("Type pasword");
        pswd.setName(" pswd"); // NOI18N
        pswd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pswdFocusLost(evt);
            }
        });
        pswd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pswdActionPerformed(evt);
            }
        });
        pnlLogin.add(pswd, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 127, 203, -1));

        lblServer.setFont(new java.awt.Font("David", 1, 20)); // NOI18N
        lblServer.setText("Server:");
        lblServer.setName("lblServer"); // NOI18N
        pnlLogin.add(lblServer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 166, -1, -1));

        lblScreenName.setFont(new java.awt.Font("David", 1, 24)); // NOI18N
        lblScreenName.setForeground(java.awt.Color.red);
        lblScreenName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScreenName.setText("Log-In:");
        lblScreenName.setName("lblScreenName"); // NOI18N
        pnlLogin.add(lblScreenName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 51, -1, -1));

        btnExit.setFont(new java.awt.Font("David", 1, 20)); // NOI18N
        btnExit.setText(" Exit");
        btnExit.setToolTipText("Press to close the program");
        btnExit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExit.setMaximumSize(new java.awt.Dimension(41, 17));
        btnExit.setMinimumSize(new java.awt.Dimension(41, 17));
        btnExit.setName("btnExit"); // NOI18N
        btnExit.setPreferredSize(new java.awt.Dimension(41, 17));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        pnlLogin.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 269, 76, 42));

        lblPort.setFont(new java.awt.Font("David", 1, 20)); // NOI18N
        lblPort.setText("Port:");
        lblPort.setName("lblPort"); // NOI18N
        pnlLogin.add(lblPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 206, -1, -1));

        txtPort.setEditable(false);
        txtPort.setFont(new java.awt.Font("David", 0, 12)); // NOI18N
        txtPort.setText("11111");
        txtPort.setToolTipText("Type server port number");
        txtPort.setDragEnabled(true);
        txtPort.setName("txtPort"); // NOI18N
        txtPort.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPortFocusLost(evt);
            }
        });
        txtPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPortActionPerformed(evt);
            }
        });
        pnlLogin.add(txtPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 209, 203, -1));

        lblPswd.setFont(new java.awt.Font("David", 1, 20)); // NOI18N
        lblPswd.setText("Password:");
        lblPswd.setName("lblPswd"); // NOI18N
        pnlLogin.add(lblPswd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 127, -1, -1));

        btnLogin.setFont(new java.awt.Font("David", 1, 20)); // NOI18N
        btnLogin.setText("Log-in");
        btnLogin.setToolTipText("Press in order to login");
        btnLogin.setActionCommand("loginBtnPressed");
        btnLogin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLogin.setEnabled(false);
        btnLogin.setName("btnLogin"); // NOI18N
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        pnlLogin.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 269, 76, 42));

        txtServer.setFont(new java.awt.Font("David", 0, 12)); // NOI18N
        txtServer.setToolTipText("Type server name or IP address");
        txtServer.setName("txtServer"); // NOI18N
        txtServer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtServerFocusLost(evt);
            }
        });
        txtServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServerActionPerformed(evt);
            }
        });
        pnlLogin.add(txtServer, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 169, 203, -1));

        chbxDefaultPort.setFont(new java.awt.Font("David", 0, 12)); // NOI18N
        chbxDefaultPort.setSelected(true);
        chbxDefaultPort.setText("Default port");
        chbxDefaultPort.setToolTipText("Check in order to use default port number");
        chbxDefaultPort.setActionCommand("useDeafultPort");
        chbxDefaultPort.setName("chbxDefaultPort"); // NOI18N
        chbxDefaultPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbxDefaultPortActionPerformed(evt);
            }
        });
        pnlLogin.add(chbxDefaultPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 203, -1));

        txtUserName.setFont(new java.awt.Font("David", 0, 12)); // NOI18N
        txtUserName.setToolTipText("Type your user name");
        txtUserName.setName("txtUserName"); // NOI18N
        txtUserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserNameFocusLost(evt);
            }
        });
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });
        pnlLogin.add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 203, -1));

        lblUserName.setFont(new java.awt.Font("David", 1, 20)); // NOI18N
        lblUserName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUserName.setText("User Name:");
        lblUserName.setName("lblUserName"); // NOI18N
        pnlLogin.add(lblUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 87, -1, -1));

        lblTitle.setFont(new java.awt.Font("David", 1, 26)); // NOI18N
        lblTitle.setForeground(java.awt.Color.red);
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("PCNM Client");
        lblTitle.setName("lblTitle"); // NOI18N
        pnlLogin.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 18, -1, -1));

        getContentPane().add(pnlLogin, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            PCNMClientModel.killConnection();
        } catch (IOException ex) {
            Logger.getLogger(LoginSCR.class.getName()).log(Level.SEVERE, null, ex);
            this.dispose();
        }
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void pswdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswdActionPerformed
        // check rather login button should turnned on
        password = pswd.getPassword();
        canLogin();
    }//GEN-LAST:event_pswdActionPerformed

    private void txtServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServerActionPerformed
        // check rather login button should turnned on
        server = txtServer.getText();
        canLogin();
    }//GEN-LAST:event_txtServerActionPerformed

    private void txtPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPortActionPerformed
        // check value validity
        try {
            port = Integer.parseInt(txtPort.getText());
        } catch (NumberFormatException e) {
            txtPort.setText("");
            port = 0;
            WindowMustHave.showDialog(this.pnlLogin, "Port must be a number", DialogType.ERROR);
        }
        if (port <= 0) {
            port = 0;
            txtPort.setText("");
            WindowMustHave.showDialog(this.pnlLogin, "Invalid port number", DialogType.ERROR);
        } else
            // check rather login button should turnned on
            canLogin();
    }//GEN-LAST:event_txtPortActionPerformed

    private void chbxDefaultPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbxDefaultPortActionPerformed
        // check rather login button should turnned on
        if (chbxDefaultPort.isSelected()) {
            txtPort.setEditable(false);
            txtPort.setText("11111");
            port = 11111;
            canLogin();
        } else
            txtPort.setEditable(true);
    }//GEN-LAST:event_chbxDefaultPortActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // close connections and dispose window
        try {
            PCNMClientModel.killConnection();
        } catch (IOException ex) {
            this.dispose();
        }
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // connect to server
        try {
            PCNMClientModel.initPCNMClient(server, port);
        } catch (IOException ex) {
            WindowMustHave.showDialog(this.pnlLogin, "Error connecting to the server", DialogType.ERROR);
            return;
        }
        try {
            // start login sequance
            LoginCTRL.loginBtnPressed(userName, password);
            setTitle("PCNM - Home Screen");
        } catch (IOException ex) {
            WindowMustHave.showDialog(this.pnlLogin, "Error sending message to server", DialogType.ERROR);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtUserNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserNameFocusLost
        userName = txtUserName.getText();
        canLogin();
    }//GEN-LAST:event_txtUserNameFocusLost

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        userName = txtUserName.getText();
        canLogin();
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void pswdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pswdFocusLost
        // check rather login button should turnned on
        password = pswd.getPassword();
        canLogin();
    }//GEN-LAST:event_pswdFocusLost

    private void txtServerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtServerFocusLost
        // check rather login button should turnned on
        server = txtServer.getText();
        canLogin();
    }//GEN-LAST:event_txtServerFocusLost

    private void txtPortFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPortFocusLost
        // check value validity
        try {
            port = Integer.parseInt(txtPort.getText());
        } catch (NumberFormatException e) {
            txtPort.setText("");
            port = 0;
            WindowMustHave.showDialog(this.pnlLogin, "Port must be a number", DialogType.ERROR);
        }
        if (port <= 0) {
            port = 0;
            txtPort.setText("");
            WindowMustHave.showDialog(this.pnlLogin, "Invalid port number", DialogType.ERROR);
        } else
            // check rather login button should turnned on
            canLogin();
    }//GEN-LAST:event_txtPortFocusLost

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("Convert2Lambda")
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
            java.util.logging.Logger.getLogger(LoginSCR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginSCR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginSCR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginSCR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginSCR().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox chbxDefaultPort;
    private javax.swing.JLabel lblPort;
    private javax.swing.JLabel lblPswd;
    private javax.swing.JLabel lblScreenName;
    private javax.swing.JLabel lblServer;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPasswordField pswd;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtServer;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables

    private void canLogin() {
        // only if all fields were filled, turn login button on
        if (userName.length() > 0 && password != null && password.length > 0 && server.length() > 0 && port > 0)
            btnLogin.setEnabled(true);
        else
            btnLogin.setEnabled(false);
    }
    
    /**
     * This method handles login failuer
     * @param message
     */
    public void badLogin(String message) {
        switch (message) {
            case "Bad User Name or Password":
                txtUserName.setText("");
                pswd.setText("");
                break;
            case "Bad Password":
                pswd.setText("");
                break;
        }
        WindowMustHave.showDialog(this.pnlLogin, message, DialogType.WARNING);
    }
}
