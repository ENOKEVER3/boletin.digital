/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Date;

public class UsersCategories {
  private int catcod;
  private int percod;
  private Date startDate;

  public int getCatcod() {
      return catcod;
  }

  public void setCatcod(int catcod) {
      this.catcod = catcod;
  }

  public int getPercod() {
      return percod;
  }

  public void setPercod(int percod) {
      this.percod = percod;
  }

  public Date getStartDate() {
      return startDate;
  }

  public void setStartDate(Date startDate) {
      this.startDate = startDate;
  }

  public Date getFinishDate() {
      return finishDate;
  }

  public void setFinishDate(Date finishDate) {
      this.finishDate = finishDate;
  }
  private Date finishDate;
}
