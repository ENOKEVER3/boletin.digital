/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Classes.Course;
import Classes.Division;
import Classes.Note;
import Classes.Orientation;
import Classes.PendingNote;
import Classes.User;
import Classes.Year;
import Utils.Combo;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lagos
 */
public class CourseToCourse extends javax.swing.JFrame {

  /**
   * Creates new form CourseToCourse
   */
  public CourseToCourse() {
    initComponents();
    changeBoxs();
    disableButton();
  }

  ArrayList students;
  ManageCourse manageCourse;
  
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    table = new javax.swing.JTable();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    yearBox = new javax.swing.JComboBox<>();
    orientationBox = new javax.swing.JComboBox<>();
    divisionBox = new javax.swing.JComboBox<>();
    jLabel3 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    yearBox2 = new javax.swing.JComboBox<>();
    orientationBox2 = new javax.swing.JComboBox<>();
    divisionBox2 = new javax.swing.JComboBox<>();
    jLabel8 = new javax.swing.JLabel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    moveButton = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setResizable(false);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosed(java.awt.event.WindowEvent evt) {
        formWindowClosed(evt);
      }
    });

    table.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {},
        {},
        {},
        {}
      },
      new String [] {

      }
    ));
    jScrollPane1.setViewportView(table);

    jLabel4.setText("Orientaci??n:");

    jLabel5.setText("Divisi??n:");

    yearBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "                        ", "Primero", "Segundo", "Tercero", "Cuarto", "Quinto", "Sexto", "S??ptimo" }));
    yearBox.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        yearBoxItemStateChanged(evt);
      }
    });

    orientationBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "                ", "General", "Inform??tica", "Electromec??nica" }));
    orientationBox.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        orientationBoxItemStateChanged(evt);
      }
    });

    divisionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "               ", "Primera", "Segunda" }));
    divisionBox.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        divisionBoxItemStateChanged(evt);
      }
    });

    jLabel3.setText("A??o:");

    jLabel6.setText("Orientaci??n:");

    jLabel7.setText("Divisi??n:");

    yearBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "                        ", "Primero", "Segundo", "Tercero", "Cuarto", "Quinto", "Sexto", "S??ptimo" }));

    orientationBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "                ", "General", "Inform??tica", "Electromec??nica" }));

    divisionBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "               ", "Primera", "Segunda" }));

    jLabel8.setText("A??o:");

    jLabel1.setText("Mover el curso:");

    jLabel2.setText("Al curso:");

    jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel9.setText("Promoci??n de cursos");

    moveButton.setText("MOVER");
    moveButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        moveButtonActionPerformed(evt);
      }
    });

    jButton2.setText("SALIR");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });

    jButton3.setText("BUSCAR");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton3ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1))
          .addGroup(layout.createSequentialGroup()
            .addGap(61, 61, 61)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(jLabel3)
                      .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(jLabel4)
                      .addComponent(orientationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(jLabel5)
                      .addGroup(layout.createSequentialGroup()
                        .addComponent(divisionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(yearBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addComponent(jLabel8))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(jLabel6)
                      .addComponent(orientationBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(jLabel7)
                      .addGroup(layout.createSequentialGroup()
                        .addComponent(divisionBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(moveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(jLabel9)
                      .addComponent(jLabel1)
                      .addComponent(jLabel2))
                    .addGap(349, 349, 349)))
                .addGap(0, 48, Short.MAX_VALUE))
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2)))))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGap(15, 15, 15)
        .addComponent(jLabel9)
        .addGap(18, 18, 18)
        .addComponent(jLabel1)
        .addGap(11, 11, 11)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel4)
              .addComponent(jLabel5))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(orientationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(divisionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jButton3))))
        .addGap(18, 18, 18)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(13, 13, 13)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel8)
          .addComponent(jLabel6)
          .addComponent(jLabel7))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(yearBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(orientationBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(divisionBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(moveButton))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
        .addComponent(jButton2)
        .addContainerGap())
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    if (checkEmptyFields()) return;
    if (!Year.checkYear(yearBox.getSelectedItem().toString(), orientationBox.getSelectedItem().toString())) {
      JOptionPane.showMessageDialog(null, "Los datos ingresados del a??o y la orientaci??n son incorrectos");
      return;
    }
    
    int oricod = Orientation.getOrientationcod(orientationBox.getSelectedItem().toString());
    int yearcod = Year.getYearcod(yearBox.getSelectedItem().toString(), oricod);
    int curcod = Course.getCourseCod(yearBox.getSelectedItem().toString(), orientationBox.getSelectedItem().toString(), divisionBox.getSelectedItem().toString());
    
    try {
      students = Course.getStudentsByCourse(curcod);
      
      Object[] columnNames = {"Nombre", "Apellido", "Promedio General", "Materias Pendientes", "Talleres Pendientes", "Seleccionado"};
      
      DefaultTableModel model = new DefaultTableModel(null, columnNames){
        @Override
        public boolean isCellEditable(int row, int column) {
          if(column == 5) return true;
          return false;
        }  
        
        @Override
        public Class getColumnClass(int column) {
          switch (column) {
            case 0:
              return String.class;
            case 1:
              return String.class;
            case 2:
              return String.class;
            case 3:
              return Integer.class;
            case 4:
              return Integer.class;
            case 5:
              return Boolean.class;
            default:
              return Integer.class;
          }
        }
      };
      
      table.setModel(model);
      table.getTableHeader().setReorderingAllowed(false);
      table.getTableHeader().setResizingAllowed(false);
      
      students.forEach(student -> {
        User currentStudent = (User) student;
        int currentStudentCod = User.getUserCodeByUsername(currentStudent.getUsername());
        
        ArrayList pendingSubjects = PendingNote.getPendingSubjects(currentStudentCod);
        
        model.addRow(new Object[]{currentStudent.getName(),currentStudent.getLastname(), String.format("%.2f", Note.getFinalNote(currentStudentCod, oricod, yearcod, curcod)), pendingSubjects.get(0), pendingSubjects.get(1), true});
      });
      
      if(students.size() > 0) enableButton();
      else disableButton();
    } catch (ParseException ex) {
      Logger.getLogger(CourseToCourse.class.getName()).log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_jButton3ActionPerformed

  private void moveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveButtonActionPerformed
    if (checkEmptyFields2()) return;
    if (!Year.checkYear(yearBox2.getSelectedItem().toString(), orientationBox2.getSelectedItem().toString())) {
       JOptionPane.showMessageDialog(null, "Los datos ingresados del a??o y la orientaci??n son incorrectos");
      return;
    }
    
    if(!(JOptionPane.showConfirmDialog(null, "Est?? seguro de moverlo?") == 0)) return;
    
    boolean success = true;
    
    for(int i = 0; i < students.size(); i++){
      User currentStudent = (User) students.get(i);
      
      if(table.getValueAt(i, 5).equals(false)) continue;
     
      int percod = User.getUserCodeByUsername(currentStudent.getUsername());
      int oricod = Orientation.getOrientationcod(orientationBox2.getSelectedItem().toString());
      int anocod = Year.getYearcod(yearBox2.getSelectedItem().toString(), oricod);
      int curcod = Course.getCourseCod(yearBox2.getSelectedItem().toString(), orientationBox2.getSelectedItem().toString(), divisionBox2.getSelectedItem().toString());
      
      try {
        Course.registerStudentInCourse(percod, oricod, anocod, curcod);
        success = true;
      } catch (SQLException ex) {
        Logger.getLogger(CourseToCourse.class.getName()).log(Level.SEVERE, null, ex);
        success = false;
      } catch (ParseException ex) {
        Logger.getLogger(CourseToCourse.class.getName()).log(Level.SEVERE, null, ex);
        success = false;
      } 
    }
    
    if(success == true) {
      JOptionPane.showMessageDialog(this, "El curso fue movido");
      clearFields();
    } else {
      JOptionPane.showMessageDialog(this, "Se produjo un error inesperado");
    }
  }//GEN-LAST:event_moveButtonActionPerformed

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    manageCourse.setVisible(true);
    dispose();
  }//GEN-LAST:event_jButton2ActionPerformed

  private void yearBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_yearBoxItemStateChanged
    disableButton();
  }//GEN-LAST:event_yearBoxItemStateChanged

  private void orientationBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_orientationBoxItemStateChanged
    disableButton();
  }//GEN-LAST:event_orientationBoxItemStateChanged

  private void divisionBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_divisionBoxItemStateChanged
    disableButton();
  }//GEN-LAST:event_divisionBoxItemStateChanged

  private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    manageCourse.setVisible(true);
  }//GEN-LAST:event_formWindowClosed
  
  private boolean checkEmptyFields() {
    if(yearBox.getSelectedIndex() == 0) {
      JOptionPane.showMessageDialog(null, "Indique el a??o");
    } else if(orientationBox.getSelectedIndex() == 0) {
      JOptionPane.showMessageDialog(null, "Indique la orientaci??n");
    } else if (divisionBox.getSelectedIndex() == 0) {
      JOptionPane.showMessageDialog(null, "Ingrese la divisi??n");
    } else {
      return false;
    }

    return true;
  }

  void changeBoxs() {
    Combo.setComboBoxItems(Year.getYears(false), yearBox);
    Combo.setComboBoxItems(Orientation.getOrientations(false), orientationBox);
    Combo.setComboBoxItems(Division.getDivisions(false), divisionBox);
    Combo.setComboBoxItems(Year.getYears(false), yearBox2);
    Combo.setComboBoxItems(Orientation.getOrientations(false), orientationBox2);
    Combo.setComboBoxItems(Division.getDivisions(false), divisionBox2);
  }
  
  /**
   * @param args the command line arguments
   */

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox<String> divisionBox;
  private javax.swing.JComboBox<String> divisionBox2;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JButton moveButton;
  private javax.swing.JComboBox<String> orientationBox;
  private javax.swing.JComboBox<String> orientationBox2;
  private javax.swing.JTable table;
  private javax.swing.JComboBox<String> yearBox;
  private javax.swing.JComboBox<String> yearBox2;
  // End of variables declaration//GEN-END:variables

  private boolean checkEmptyFields2() {
    if(yearBox2.getSelectedIndex() == 0) {
      JOptionPane.showMessageDialog(null, "Indique el a??o");
    } else if(orientationBox2.getSelectedIndex() == 0) {
      JOptionPane.showMessageDialog(null, "Indique la orientaci??n");
    } else if (divisionBox2.getSelectedIndex() == 0) {
      JOptionPane.showMessageDialog(null, "Ingrese la divisi??n");
    } else {
      return false;
    }

    return true;
  }

  private void disableButton() {
    moveButton.setEnabled(false);
  }

  private void enableButton() {
    moveButton.setEnabled(true);
  }

  private void clearFields() {
    yearBox.setSelectedIndex(0);
    yearBox2.setSelectedIndex(0);
    orientationBox.setSelectedIndex(0);
    orientationBox2.setSelectedIndex(0);
    divisionBox.setSelectedIndex(0);
    divisionBox2.setSelectedIndex(0);
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);
  }
}
