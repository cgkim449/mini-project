package mini.project.pms.domain;

public class Skill {

  private int no; // 번호
  private String skillName; // 기술명
  private String detail; // 설명
  private int power; // 위력

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getSkillName() {
    return skillName;
  }
  public void setSkillName(String skillName) {
    this.skillName = skillName;
  }
  public String getDetail() {
    return detail;
  }
  public void setDetail(String detail) {
    this.detail = detail;
  }
  public int getPower() {
    return power;
  }
  public void setPower(int power) {
    this.power = power;
  }

}
