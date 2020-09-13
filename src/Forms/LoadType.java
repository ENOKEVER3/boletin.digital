/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import static Classes.Subject.errorCode;
import static Classes.Subject.getForcod;
import static Classes.Subject.getName;
import Utils.Config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author lagos
 */
public class LoadType extends javax.swing.JFrame {

  /**
   * Creates new form LoadType
   */
  public LoadType() {
    initComponents();
  }
  
  LoadSubject loadSubject;
  
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    nameField = new javax.swing.JTextField();
    jButton1 = new javax.swing.JButton();
    jToggleButton1 = new javax.swing.JToggleButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setText("Nuevo categoria de materias");

    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
    jLabel2.setText("Categoria");

    jLabel3.setText("Nombre:");

    jButton1.setText("CREAR");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jToggleButton1.setText("SALIR");
    jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jToggleButton1ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addGroup(layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(jLabel2)
              .addComponent(jLabel1))))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel2)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel1)
        .addGap(35, 35, 35)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButton1)
          .addComponent(jToggleButton1))
        .addContainerGap(12, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
    dispose();
  }//GEN-LAST:event_jToggleButton1ActionPerformed

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if(nameField.getText().isEmpty()) {
      JOptionPane.showMessageDialog(null, "Ingrese el nombre de la categoria de materias");
    }
    
    try {
      if(newCategory(nameField.getText())) {
        loadSubject.changeBoxs();
        JOptionPane.showMessageDialog(null, "La categoria fue creada correctamente");
      } else {
        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado");
      }
    } catch (ParseException ex) {
      Logger.getLogger(LoadType.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    nameField.setText("");
    nameField.requestFocus();
  }//GEN-LAST:event_jButton1ActionPerformed

  /**
   * @param args the command line arguments
   */

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JToggleButton jToggleButton1;
  private javax.swing.JTextField nameField;
  // End of variables declaration//GEN-END:variables

  private boolean categoryAlreadyExist(String categorie) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    String query = "SELECT * FROM `FORMACIONES` WHERE `FOR_NOMBRE`='" + categorie + "';";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.execute();
      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();

      if(rs.next()){
        updateCategory(rs.getInt("FOR_COD"));
        return true;
      }

    } catch (SQLException e) {
      System.out.println("ERROR: " + e);
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    return false;
  }
  
  private void updateCategory(int forcod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

    String query = "UPDATE FORMACIONES SET FOR_FECHAFIN=? WHERE FOR_COD=" + forcod + " AND FOR_FECHAFIN < ?;";

    try {           
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setDate(1, neverDate);
      preparedStatemnet.setDate(2, neverDate);
      preparedStatemnet.execute();

    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  private boolean newCategory(String categorie) throws ParseException {
    if(categoryAlreadyExist(categorie)) return true;

    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

    String query = "INSERT INTO `FORMACIONES` (`FOR_NOMBRE`, `FOR_FECHAFIN`) VALUES (?, ?);";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setString(1, categorie);
      preparedStatemnet.setDate(2, neverDate);
      preparedStatemnet.execute();


      return true;
    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
      errorCode = e.getErrorCode();
    } finally {
      if(connection != null) try {
          connection.close();
      } catch (SQLException ex) {
          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    return false;
  }
}