/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author lagos
 */
public class Combo {
  static public void setComboBoxItems(ArrayList list, JComboBox combo) {
    if(list == null) return;
    int listLenght = list.size();
    
    combo.removeAllItems();
    combo.addItem("");
    
    for(int i=0; i < listLenght; i++) {
      combo.addItem(list.get(i));
    }
  }
}
