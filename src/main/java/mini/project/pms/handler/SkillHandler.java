package mini.project.pms.handler;

import java.util.List;
import mini.project.pms.domain.Skill;
import mini.project.util.Prompt;

public class SkillHandler {

  List<Skill> skillList;
  PokemonHandler pokemonHandler;

  public SkillHandler(List<Skill> list) {
    this.skillList = list;
  }

  public void add() {
    System.out.println("[기술 등록]");

    Skill skill = new Skill();
    skill.setNo(Prompt.inputInt("번호? "));
    skill.setSkillName(Prompt.inputString("기술명? "));
    skill.setType(Prompt.inputString("타입? "));
    skill.setPower(Prompt.inputInt("위력? "));

    skillList.add(skill);
  }

  public void list() {
    System.out.println("[기술 목록]");

    for (int i = 0; i < skillList.size(); i++) {
      Skill skill = skillList.get(i);
      System.out.printf("%d, %s, %s, %d\n",
          skill.getNo(),
          skill.getSkillName(),
          skill.getType(),
          skill.getPower());
    }
  }

  public void update() {
    System.out.println("[기술 변경]");
    int no = Prompt.inputInt("번호? ");
    Skill skill = findByNo(no);

    if (skill == null) {
      System.out.println("해당 번호의 기술이 없습니다.");
      return;
    }

    String skillName = Prompt.inputString(
        String.format("기술명(%s)? ", skill.getSkillName()));
    String type = Prompt.inputString(
        String.format("타입(%s)? ", skill.getType()));
    int power = Prompt.inputInt(
        String.format("위력(%s)? ", skill.getPower()));

    String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("기술 변경을 취소하였습니다.");
      return;
    }

    skill.setSkillName(skillName);
    skill.setType(type);
    skill.setPower(power);

    System.out.println("기술을 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[기술 삭제]");
    int no = Prompt.inputInt("번호? ");
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 기술이 없습니다.");
      return;
    }

    String response = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("기술 삭제를 취소하였습니다.");
      return;
    }

    skillList.remove(index);
    System.out.println("기술을 삭제하였습니다.");
  }

  private Skill findByNo(int no) {
    for (int i = 0; i < skillList.size(); i++) {
      Skill skill = skillList.get(i);
      if (skill.getNo() == no) {
        return skill;
      }
    }
    return null;
  }

  public Skill findByName(String skillName) {
    for (int i = 0; i < skillList.size(); i++) {
      Skill skill = skillList.get(i);
      if (skill.getSkillName().equals(skillName)) {
        return skill;
      }
    }
    return null;
  }

  private int indexOf(int no) {
    for (int i = 0; i < skillList.size(); i++) {
      Skill skill = skillList.get(i);
      if (skill.getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
