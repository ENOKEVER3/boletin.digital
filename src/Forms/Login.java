package Forms;

import Classes.User;
import Utils.Config;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp2.BasicDataSource;

public class Login extends javax.swing.JFrame {

    
    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel5 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    passwordField = new javax.swing.JPasswordField();
    jSeparator1 = new javax.swing.JSeparator();
    jLabel2 = new javax.swing.JLabel();
    usernameField = new javax.swing.JTextField();
    loginButton = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setResizable(false);
    addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        formKeyPressed(evt);
      }
    });

    jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
    jLabel5.setText("BOLETIN DIGITAL");
    jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel3.setText("Contraseña:");

    passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        passwordFieldKeyPressed(evt);
      }
    });

    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel2.setText("Usuario:");

    usernameField.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        usernameFieldKeyPressed(evt);
      }
    });

    loginButton.setText("INGRESAR");
    loginButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        loginButtonActionPerformed(evt);
      }
    });
    loginButton.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        loginButtonKeyPressed(evt);
      }
    });

    jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Webp.net-resizeimage.png"))); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(47, 47, 47)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel5)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addComponent(jLabel2)
            .addComponent(usernameField)
            .addComponent(jLabel3)
            .addComponent(passwordField)
            .addComponent(jSeparator1)
            .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
        .addComponent(jLabel1)
        .addGap(41, 41, 41))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(47, 47, 47)
        .addComponent(jLabel5)
        .addGap(28, 28, 28)
        .addComponent(jLabel2)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jLabel3)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(loginButton)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap(76, Short.MAX_VALUE)
        .addComponent(jLabel1)
        .addGap(27, 27, 27))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

  private void loginButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginButtonKeyPressed
    if(evt.getKeyChar() == 10) try {
      tryLogin();
    } catch (ParseException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_loginButtonKeyPressed

  private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
    try {
      tryLogin();
    } catch (ParseException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_loginButtonActionPerformed

  private void usernameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameFieldKeyPressed
    //if(evt.getKeyChar() == 10) tryLogin();
  }//GEN-LAST:event_usernameFieldKeyPressed

  private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
    if(evt.getKeyChar() == 10) try {
      tryLogin();
    } catch (ParseException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_passwordFieldKeyPressed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JButton loginButton;
  private javax.swing.JPasswordField passwordField;
  private javax.swing.JTextField usernameField;
  // End of variables declaration//GEN-END:variables

    private void retryLogin() {
        passwordField.setText("");
        usernameField.setText("");
        usernameField.requestFocus();
        JOptionPane.showMessageDialog(null, "La contraseña no coincide o el usuario no existe.");
    }

    private boolean emptyLoginFields() {
        if(usernameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese su nombre, por favor."); 
            return true;
        }
        if(String.valueOf(passwordField.getPassword()).isEmpty()) { 
            JOptionPane.showMessageDialog(null, "Ingrese su contraseña, por favor.");
            return true; 
        }
        
        return false;
    }

    private void tryLogin() throws ParseException {
        if (emptyLoginFields()) return;
       
        if(User.checkPassword(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
            int usercode = User.getUserCodeByUsername(usernameField.getText());
            ArrayList userCategories = User.getUserCategoriesByUsercode(usercode);
            
            dispose();
            
            Menu menu = new Menu(usercode, usernameField.getText(), userCategories);        
            menu.setVisible(true);
        } else {
            retryLogin();
        }   
    }
}
