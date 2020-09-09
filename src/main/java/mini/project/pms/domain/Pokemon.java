package mini.project.pms.domain;

import java.sql.Date;

public class Pokemon {

  private int no; // 번호
  private String name; // 이름
  private String type; // 타입
  private String skill; // 기술
  private String item; // 아이템
  public String getItem() {
    return item;
  }
  public void setItem(String item) {
    this.item = item;
  }
  private Date registeredDate; // 잡은 날짜

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getSkill() {
    return skill;
  }
  public void setSkill(String skill) {
    this.skill = skill;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

}
