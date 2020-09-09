/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Classes.Course;
import Classes.Subject;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class LoadSubject extends javax.swing.JFrame {

    /**
     * Creates new form LoadSubject
     */
    public LoadSubject() {
        initComponents();
    }
    
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
        jLabel2 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        yearBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        orientationBox = new javax.swing.JComboBox();
        exitLoadSubject = new javax.swing.JButton();
        loadSubject = new javax.swing.JButton();
        typeBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Cargar materias");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Indique el nombre de la materia que desee cargar:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Determine los siguientes datos:");

        jLabel4.setText("Año:");

        yearBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "           ", "Primero", "Segundo", "Tercero", "Cuarto", "Quinto", "Sexto", "Séptimo" }));

        jLabel5.setText("Orientación:");

        orientationBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "           ", "General", "Informática", "Electromecánica" }));
        orientationBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orientationBoxActionPerformed(evt);
            }
        });

        exitLoadSubject.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        exitLoadSubject.setText("SALIR");
        exitLoadSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitLoadSubjectActionPerformed(evt);
            }
        });

        loadSubject.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loadSubject.setText("CARGAR");
        loadSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadSubjectActionPerformed(evt);
            }
        });

        typeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "                                             ", "Formación General", "Formación Técnica Específica" }));

        jLabel6.setText("Tipo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(typeBox, 0, 221, Short.MAX_VALUE))
                        .addGap(192, 192, 192))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(orientationBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(nameField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loadSubject)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exitLoadSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(26, 26, 26))
                    .addComponent(orientationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(26, 26, 26))
                    .addComponent(typeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitLoadSubject)
                    .addComponent(loadSubject))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadSubjectActionPerformed
        if (checkEmptyFields()) return;
        if (!Subject.checkYear(yearBox.getSelectedItem().toString(), orientationBox.getSelectedIndex())) {
            JOptionPane.showMessageDialog(null, "Los datos ingresados del año y la orientación son incorrectos");
            return;
        }
        if (!Subject.checkType(typeBox.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "El tipo de matería no existe");
            return;
        }
        if (orientationBox.getSelectedIndex() == 1 && typeBox.getSelectedIndex() == 2) {
            JOptionPane.showMessageDialog(null, "Revise el tipo de la materia");
            return;
        }
        
        Subject subject = new Subject();
        subject.setForcod(typeBox.getSelectedIndex());
        subject.setName(nameField.getText());
        try {
            if(subject.save()) {
                JOptionPane.showMessageDialog(null, "La materia fue creada correctamente");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Algo salió mal. código de error: " + subject.errorCode);
            }
        } catch (ParseException ex) {
            Logger.getLogger(LoadSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loadSubjectActionPerformed

    private void orientationBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orientationBoxActionPerformed
        
    }//GEN-LAST:event_orientationBoxActionPerformed

    private void exitLoadSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitLoadSubjectActionPerformed
        menu.setVisible(true);
        dispose();
    }//GEN-LAST:event_exitLoadSubjectActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitLoadSubject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton loadSubject;
    private javax.swing.JTextField nameField;
    private javax.swing.JComboBox orientationBox;
    private javax.swing.JComboBox<String> typeBox;
    private javax.swing.JComboBox yearBox;
    // End of variables declaration//GEN-END:variables

    private boolean checkEmptyFields() {
        if (nameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Indique el nombre");
        } else if(yearBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Indique el año");
        } else if(orientationBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Indique la orientación");
        } else if (typeBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese el tipo");
        } else {
            return false;
        }
        
        return true;
    }
    
    public void clearFields() {
        nameField.setText("");
        yearBox.setSelectedIndex(0);
        orientationBox.setSelectedIndex(0);
        typeBox.setSelectedIndex(0);
        nameField.requestFocus();
    }
}
