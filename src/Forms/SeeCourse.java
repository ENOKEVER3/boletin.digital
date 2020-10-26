/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Classes.Period;
import Classes.Course;
import Classes.Note;
import Classes.Period;
import Classes.Orientation;
import Classes.PendingNote;
import Classes.Subject;
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
 * @author acer
 */
public class LoadNote extends javax.swing.JFrame {

    /**
     * Creates new form LoadNote
     */
    public LoadNote() {
        initComponents();
        blockButton();
        changeBoxs();
    }
    
    int oricod;
    int yearcod;
    int curcod;
    int matcod;
    int prdcod;
    ArrayList students;
    ArrayList studentsNotes;
    
    Menu menu;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    loadButton = new javax.swing.JButton();
    exitLoadNote = new javax.swing.JButton();
    searchButton = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    subjectBox = new javax.swing.JComboBox();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jLabel12 = new javax.swing.JLabel();
    yearBox = new javax.swing.JComboBox();
    divisionBox = new javax.swing.JComboBox<>();
    orientationBox = new javax.swing.JComboBox();
    jLabel6 = new javax.swing.JLabel();
    periodsBox = new javax.swing.JComboBox<>();
    jLabel3 = new javax.swing.JLabel();
    selectButton = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    table = new javax.swing.JTable();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setResizable(false);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosed(java.awt.event.WindowEvent evt) {
        formWindowClosed(evt);
      }
    });

    jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel1.setText("Carga de notas");

    loadButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    loadButton.setText("CARGAR");
    loadButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        loadButtonActionPerformed(evt);
      }
    });

    exitLoadNote.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    exitLoadNote.setText("SALIR");
    exitLoadNote.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        exitLoadNoteActionPerformed(evt);
      }
    });

    searchButton.setText("BUSCAR");
    searchButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        searchButtonActionPerformed(evt);
      }
    });

    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel2.setText("Indique la materia:");

    subjectBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "                         ", "Matemática", "Lengua" }));
    subjectBox.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        subjectBoxItemStateChanged(evt);
      }
    });
    subjectBox.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        subjectBoxActionPerformed(evt);
      }
    });

    jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel4.setText("Indique el curso:");

    jLabel5.setText("Año:");

    jLabel12.setText("División");

    yearBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "                    ", "Primero", "Segundo", "Tercero", "Cuarto", "Quinto", "Sexto", "Séptimo" }));
    yearBox.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        yearBoxItemStateChanged(evt);
      }
    });
    yearBox.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        yearBoxActionPerformed(evt);
      }
    });

    divisionBox.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        divisionBoxItemStateChanged(evt);
      }
    });
    divisionBox.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        divisionBoxActionPerformed(evt);
      }
    });

    orientationBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "                             ", "General", "Informática", "Electromecánica" }));
    orientationBox.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        orientationBoxItemStateChanged(evt);
      }
    });
    orientationBox.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        orientationBoxActionPerformed(evt);
      }
    });

    jLabel6.setText("Orientación:");

    periodsBox.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(java.awt.event.ItemEvent evt) {
        periodsBoxItemStateChanged(evt);
      }
    });

    jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel3.setText("Seleccione el período:");

    selectButton.setText("SELECCIONAR");
    selectButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        selectButtonActionPerformed(evt);
      }
    });

    table.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {

      }
    ));
    jScrollPane1.setViewportView(table);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(loadButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(exitLoadNote, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(selectButton)
            .addComponent(jLabel3)
            .addComponent(jLabel4)
            .addComponent(subjectBox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel2)
            .addComponent(jLabel1)
            .addGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLabel5)
                .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchButton))
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel6)
                .addComponent(orientationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel12)
                .addComponent(divisionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(periodsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(29, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jLabel1)
        .addGap(18, 18, 18)
        .addComponent(jLabel2)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(subjectBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jLabel4)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel5)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel12)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(divisionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel6)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(orientationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(18, 18, 18)
        .addComponent(searchButton)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel3)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(periodsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(selectButton)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(exitLoadNote, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(loadButton))
        .addGap(10, 10, 10))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void exitLoadNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitLoadNoteActionPerformed
    menu.setVisible(true);
    dispose();
  }//GEN-LAST:event_exitLoadNoteActionPerformed

  private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    menu.setVisible(true);
  }//GEN-LAST:event_formWindowClosed

  private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
    if(checkEmptyFields()) return;
    oricod = Orientation.getOrientationcod(orientationBox.getSelectedItem().toString());
    yearcod = Year.getYearcod(yearBox.getSelectedItem().toString(), oricod);
    String division = divisionBox.getSelectedItem().toString();
    
    curcod = Course.getCurcodByDivsion(oricod, yearcod, division);
    
    if(curcod == 0) {
      JOptionPane.showMessageDialog(null, "Los datos del curso son incorrectos");
    } else {
      ArrayList teacherscod = Subject.getTeachersCod(oricod, yearcod, curcod, subjectBox.getSelectedItem().toString());
      matcod = (int) teacherscod.get(0);
      
      if(teacherscod.contains(menu.currentUserCode)) {
        enableButton();
      } else {
        JOptionPane.showMessageDialog(null, "La materia no fue encontrada");
      }
    }
  }//GEN-LAST:event_searchButtonActionPerformed

  private void subjectBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_subjectBoxItemStateChanged
    blockButton();
  }//GEN-LAST:event_subjectBoxItemStateChanged

  private void subjectBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectBoxActionPerformed

  }//GEN-LAST:event_subjectBoxActionPerformed

  private void yearBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_yearBoxItemStateChanged
    blockButton();
  }//GEN-LAST:event_yearBoxItemStateChanged

  private void yearBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearBoxActionPerformed

  }//GEN-LAST:event_yearBoxActionPerformed

  private void divisionBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_divisionBoxItemStateChanged
    blockButton();
  }//GEN-LAST:event_divisionBoxItemStateChanged

  private void divisionBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divisionBoxActionPerformed

  }//GEN-LAST:event_divisionBoxActionPerformed

  private void orientationBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_orientationBoxItemStateChanged
    blockButton();
  }//GEN-LAST:event_orientationBoxItemStateChanged

  private void orientationBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orientationBoxActionPerformed

  }//GEN-LAST:event_orientationBoxActionPerformed

  private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonActionPerformed
    if(periodsBox.getSelectedIndex() == 0) {
      JOptionPane.showMessageDialog(null, "Seleccione un período");
      return;
    }
    
    prdcod = Period.getPeriodCod(periodsBox.getSelectedItem().toString());
    
    try {
      changeTable();
    } catch (ParseException ex) {
      Logger.getLogger(LoadNote.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }//GEN-LAST:event_selectButtonActionPerformed

  private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
    
    try {
      table.getCellEditor().stopCellEditing();
    } catch(Error e) {
      JOptionPane.showMessageDialog(null, "Error inesperado :/");
    }
    
    int studentsSize = students.size();
    
    boolean isNewNote = false;
    
    for (int i = 0; i < studentsSize; i++) {
      
      ArrayList originalNotes = (ArrayList) studentsNotes.get(i);
      User currentUser = (User) students.get(i);
      int currentUsercod = User.getUserCodeByUsername(currentUser.getUsername());
      
      int n1 = (int) table.getModel().getValueAt(i,2);
      int n2 = (int) table.getModel().getValueAt(i,3);
      int n3 = (int) table.getModel().getValueAt(i,4);
      
      if((n1 == 0) || (n2 == 0) || (n3 == 0)) {
        JOptionPane.showMessageDialog(null, "Ingrese todas las notas del alumno: " + currentUser.getName() + " " + currentUser.getLastname());
        continue;
      }
      
      for (int j = 0; j < 3; j++) {

        int newNote = (int) table.getModel().getValueAt(i,j+2);
        
        String period = periodsBox.getSelectedItem().toString() ;
        
        if (!period.equals("PRIMER TRIMESTRE") && !period.equals("SEGUNDO TRIMESTRE") && !period.equals("TERCER TRIMESTRE")) {
          if(newNote > 7) {
            JOptionPane.showMessageDialog(null, "El máximo para un periodo de recuperación es de siete");
            continue;
          }
        }
        
        if(newNote < 0 || newNote > 10) {
          JOptionPane.showMessageDialog(null, "Una nota no es válida");
          continue;
        }
        
        if((int) originalNotes.get(j) != newNote) {
          isNewNote = true;
          if((int) originalNotes.get(j) == 0) 
            try {
              Note.createNote(currentUsercod, oricod, yearcod, curcod, matcod, prdcod, j, newNote, menu.currentUserCode);
            } catch (SQLException ex) {
            Logger.getLogger(LoadNote.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ParseException ex) {
            Logger.getLogger(LoadNote.class.getName()).log(Level.SEVERE, null, ex);
          } else {
            try {
              Note.updateNote(currentUsercod, oricod, yearcod, curcod, matcod, prdcod, j, newNote, menu.currentUserCode);
            } catch (ParseException ex) {
              Logger.getLogger(LoadNote.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        }
      }
      table.getCellEditor();
    }
    
    if(!isNewNote) {
      JOptionPane.showMessageDialog(null, "No se han agregado notas nuevas");
      return;
    }
    
    try {
      changeTable();
    } catch (ParseException ex) {
      Logger.getLogger(LoadNote.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    JOptionPane.showMessageDialog(null, "cambios guardados");
  }//GEN-LAST:event_loadButtonActionPerformed

  private void periodsBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_periodsBoxItemStateChanged
    loadButton.setEnabled(false);
  }//GEN-LAST:event_periodsBoxItemStateChanged

    
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox<String> divisionBox;
  private javax.swing.JButton exitLoadNote;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JButton loadButton;
  private javax.swing.JComboBox orientationBox;
  private javax.swing.JComboBox<String> periodsBox;
  private javax.swing.JButton searchButton;
  private javax.swing.JButton selectButton;
  private javax.swing.JComboBox subjectBox;
  private javax.swing.JTable table;
  private javax.swing.JComboBox yearBox;
  // End of variables declaration//GEN-END:variables

  private boolean checkEmptyFields() {
    if(subjectBox.getSelectedIndex() == 0) {
      JOptionPane.showMessageDialog(null, "Indique la materia");
    } else if(yearBox.getSelectedIndex() == 0) {
      JOptionPane.showMessageDialog(null, "Indique el año");
    } else if(orientationBox.getSelectedIndex() == 0) {
      JOptionPane.showMessageDialog(null, "Indique la orientación");
    } else if(divisionBox.getSelectedIndex() == 0) {
      JOptionPane.showMessageDialog(null, "Indique la división");
    } else {
      return false;
    }
    
    return true;
  }
  
  public void blockButton() {
    periodsBox.setEnabled(false);
    selectButton.setEnabled(false);
    loadButton.setEnabled(false);
  }
  
  public void enableButton() {
    periodsBox.setEnabled(true);
    selectButton.setEnabled(true);
  }
  
  private void changeBoxs() {
    
    ArrayList data = User.getTeacherData(menu.currentUserCode);
    
    ArrayList orientations = (ArrayList) data.get(0);
    ArrayList years = (ArrayList) data.get(1);
    ArrayList divisions = (ArrayList) data.get(2);
    ArrayList subjects = (ArrayList) data.get(3);
    
    Combo.setComboBoxItems(orientations, orientationBox);
    Combo.setComboBoxItems(years, yearBox);
    Combo.setComboBoxItems(divisions, divisionBox);
    Combo.setComboBoxItems(subjects, subjectBox);
    
    Combo.setComboBoxItems(Period.getPeriods(), periodsBox);
  }
  
  public void cleanFields() {
      yearBox.setSelectedIndex(0);
      orientationBox.setSelectedIndex(0);
      divisionBox.setSelectedIndex(0);
      subjectBox.setSelectedIndex(0);
      periodsBox.setSelectedIndex(0);
  }

  private void changeTable() throws ParseException {
    students = Course.getStudentsByCourse(curcod);
    
    Object[] columnNames = {"Nombre", "Apellido", "Nota 1", "Nota 2", "Nota 3"};
      
      DefaultTableModel model = new DefaultTableModel(null, columnNames){
        @Override
        public boolean isCellEditable(int row, int column) {
          if(column == 2 || column == 3 || column == 4) return true;
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
              return Integer.class;
            case 3:
              return Integer.class;
            case 4:
              return Integer.class;
            default:
              return Integer.class;
          }
        }
      };
      
      table.setModel(model);
      table.getTableHeader().setReorderingAllowed(false);
      table.getTableHeader().setResizingAllowed(false);
      
      int studentsSize = students.size();
      
      studentsNotes = new ArrayList();
      ArrayList studentsToRemove = new ArrayList();
      
      for (int i = 0; i < studentsSize; i++) {
        User currentStudent = (User) students.get(i);
        int studentCod = User.getUserCodeByUsername(currentStudent.getUsername());
        
        if(!periodsBox.getSelectedItem().toString().equals("PRIMER TRIMESTRE") && !periodsBox.getSelectedItem().toString().equals("SEGUNDO TRIMESTRE") && !periodsBox.getSelectedItem().toString().equals("TERCER TRIMESTRE")) {
          if(!PendingNote.hasPendingSubject(studentCod, oricod, yearcod, curcod, matcod, menu.currentUserCode)) {
            studentsToRemove.add(currentStudent);
            continue;
          }
        }  
        
        ArrayList notes = Note.getNote(studentCod, oricod, yearcod, curcod, matcod, prdcod);  
        
        Note n1 = (Note) notes.get(0);
        Note n2 = (Note) notes.get(1);
        Note n3 = (Note) notes.get(2);
        
        ArrayList note = new ArrayList();
        
        note.add(n1.getValue());
        note.add(n2.getValue());
        note.add(n3.getValue());
        
        studentsNotes.add(note);
        
        model.addRow(new Object[]{currentStudent.getName(), currentStudent.getLastname(), n1.getValue(), n2.getValue(), n3.getValue()});
      }
      
      studentsToRemove.forEach(student -> {
        students.remove(student);
      });
      
      if(students.size() > 0) loadButton.setEnabled(true);
      else loadButton.setEnabled(false);
  }
}
