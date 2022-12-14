/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Classes.Categorie;
import Classes.User;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lagos
 */
public class UserList extends javax.swing.JFrame {

  /**
   * Creates new form UserList
   */
  public UserList() {
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

    nameField = new javax.swing.JTextField();
    lastnameField = new javax.swing.JTextField();
    usernameBox = new javax.swing.JComboBox<>();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    jLabel10 = new javax.swing.JLabel();
    usernameLabel = new javax.swing.JLabel();
    nameLabel = new javax.swing.JLabel();
    lastnameLabel = new javax.swing.JLabel();
    emailLabel = new javax.swing.JLabel();
    otherEmailLabel = new javax.swing.JLabel();
    phoneLabel = new javax.swing.JLabel();
    genderLabel = new javax.swing.JLabel();
    jToggleButton1 = new javax.swing.JToggleButton();
    categorieBox = new javax.swing.JComboBox<>();
    jLabel11 = new javax.swing.JLabel();
    jButton2 = new javax.swing.JButton();
    jSeparator1 = new javax.swing.JSeparator();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setResizable(false);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowActivated(java.awt.event.WindowEvent evt) {
        formWindowActivated(evt);
      }
    });

    usernameBox.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        usernameBoxItemStateChanged(evt);
      }
    });

    jLabel1.setText("Nombre:");

    jLabel2.setText("Apellido:");

    jButton1.setText("BUSCAR");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jLabel4.setText("Nombre:");

    jLabel5.setText("Apellido:");

    jLabel6.setText("Usuario:");

    jLabel7.setText("Correo:");

    jLabel8.setText("Correo2:");

    jLabel9.setText("Tel??fono:");

    jLabel10.setText("Sexo:");

    usernameLabel.setText("             ");

    nameLabel.setText("             ");

    lastnameLabel.setText("             ");

    emailLabel.setText("             ");

    otherEmailLabel.setText("             ");

    phoneLabel.setText("             ");

    genderLabel.setText("             ");

    jToggleButton1.setText("COPIAR USUARIO");
    jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jToggleButton1ActionPerformed(evt);
      }
    });

    jLabel11.setText("Categoria:");

    jButton2.setText("CERRAR");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jButton2))
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(otherEmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel5)
                  .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(lastnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(15, 15, 15)
                .addComponent(phoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(33, 33, 33)
                .addComponent(genderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        .addContainerGap())
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGap(0, 0, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(usernameBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jLabel1)
          .addComponent(jLabel2)
          .addComponent(jLabel11)
          .addComponent(categorieBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(lastnameField)
          .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
          .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(141, 141, 141))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(27, 27, 27)
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jLabel2)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(lastnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jLabel11)
        .addGap(1, 1, 1)
        .addComponent(categorieBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(29, 29, 29)
        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(usernameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jToggleButton1)
        .addGap(26, 26, 26)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel6)
          .addComponent(usernameLabel))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(nameLabel))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel5)
          .addComponent(lastnameLabel, javax.swing.GroupLayout.Alignment.TRAILING))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel7)
          .addComponent(emailLabel))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel8)
          .addComponent(otherEmailLabel))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel9)
          .addComponent(phoneLabel))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel10)
          .addComponent(genderLabel))
        .addGap(34, 34, 34)
        .addComponent(jButton2)
        .addContainerGap())
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void usernameBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_usernameBoxItemStateChanged
    if (usernameBox.getSelectedItem().toString().equals("")) return;
    int usercod = User.getUserCodeByUsername((usernameBox.getSelectedItem().toString()));
    
    if(usercod == 0) {
      JOptionPane.showMessageDialog(null, "El usuario no existe");
    }
    
    try {
      setLabels(usercod);
    } catch (SQLException ex) {
      Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_usernameBoxItemStateChanged

  private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
    if(usernameBox.getSelectedItem().toString().isEmpty()) return;
    
    StringSelection stringSelection = new StringSelection(usernameBox.getSelectedItem().toString()); 
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, null);
    
    JOptionPane.showMessageDialog(null, "El nombre de usuario ha sido copiado al portapapeles");
  }//GEN-LAST:event_jToggleButton1ActionPerformed

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String name = nameField.getText();
    String lastname = lastnameField.getText();
       
    if(name.isEmpty() && lastname.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Complete alguno de los campos");
      return;
    }
    
    ArrayList usersnames = new ArrayList();
    usersnames = User.searchUsersUsername(name, lastname, Categorie.getCatcod((String) categorieBox.getSelectedItem()));
    
    cleanLabels();
    
    usernameBox.removeAllItems();
    
    usersnames.forEach(username -> {
      usernameBox.addItem((String) username);
    });
  }//GEN-LAST:event_jButton1ActionPerformed

  private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
   Utils.Combo.setComboBoxItems(Categorie.getCategories(), categorieBox);
  }//GEN-LAST:event_formWindowActivated

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    dispose();
  }//GEN-LAST:event_jButton2ActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox<String> categorieBox;
  private javax.swing.JLabel emailLabel;
  private javax.swing.JLabel genderLabel;
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JToggleButton jToggleButton1;
  private javax.swing.JTextField lastnameField;
  private javax.swing.JLabel lastnameLabel;
  private javax.swing.JTextField nameField;
  private javax.swing.JLabel nameLabel;
  private javax.swing.JLabel otherEmailLabel;
  private javax.swing.JLabel phoneLabel;
  private javax.swing.JComboBox<String> usernameBox;
  private javax.swing.JLabel usernameLabel;
  // End of variables declaration//GEN-END:variables

  private void setLabels(int usercod) throws SQLException {
    User user = User.getUser(usercod);
    
    if(user == null) {
      JOptionPane.showMessageDialog(null, "error inesperado");
      return;
    }
    
    usernameLabel.setText(user.getUsername());
    nameLabel.setText(user.getName());
    lastnameLabel.setText(user.getLastname());
    emailLabel.setText(user.getEmail());
    otherEmailLabel.setText(user.getOptionalEmail());
    phoneLabel.setText(String.valueOf(user.getPhone()));
    genderLabel.setText(user.getGender());
  }
  
  private void cleanLabels() {

    usernameLabel.setText("");
    nameLabel.setText("");
    lastnameLabel.setText("");
    emailLabel.setText("");
    otherEmailLabel.setText("");
    phoneLabel.setText("");
    genderLabel.setText("");
  }
}
