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

    final int BLOCKING = 1;
    final int ELECTRIC_SHOCK = 2;
    final int WATERBOMB = 3;
    final int MAKE_SLEEPY = 4;
    final int DISTRACTE = 5;

    while(true) {
      int no = Prompt.inputInt("번호? ");
      boolean hasNumber = false;

      for(int i = 0; i < skillList.size(); i++) {
        skill = skillList.get(i);
        if(skill.getNo() == no) {
          System.out.println("입력하신 번호는 이미 사용중입니다.");
          hasNumber = true;
          break;
        }
      }
      if(!hasNumber) {
        skill = new Skill();
        skill.setNo(no);

        loop:
        while(true) {
          System.out.println("어떤 기술을 등록하시겠습니까?");
          System.out.println("블록킹: 1");
          System.out.println("전기 충격: 2");
          System.out.println("물대포 쏘기: 3");
          System.out.println("잠들게 하기: 4");
          System.out.println("산만하게 하기: 5");

          int response = Prompt.inputInt("(번호 입력) > ");
          if (response > 5 || response < 1) {
            System.out.println("다시 입력해주세요.");
          } else {
            switch (response) {
              case BLOCKING:
                skill.setSkillName("블록킹");
                skill.setDetail("다른 포켓몬의 공격을 방어");
                skill.setPower(30);
                break;
              case ELECTRIC_SHOCK:
                skill.setSkillName("전기 충격");
                skill.setDetail("다른 포켓몬에게 전기 충격을 가함");
                skill.setPower(100);
                break;
              case WATERBOMB:
                skill.setSkillName("물대포 쏘기");
                skill.setDetail("다른 포켓몬에게 물대포를 쏨, 불을 끔");
                skill.setPower(70);
                break;
              case MAKE_SLEEPY:
                skill.setSkillName("잠들게 하기");
                skill.setDetail("다른 포켓몬을 잠들게 함");
                skill.setPower(60);
                break;
              case DISTRACTE:
                skill.setSkillName("산만하게 하기");
                skill.setDetail("다른 포켓몬의 주의를 분산시킴");
                skill.setPower(45);
                break;
            }
            break loop;
          }
          break;
        }
        break;
      }
    }
    skillList.add(skill);
  }

  public void list() {
    System.out.println("[기술 목록]");

    for (int i = 0; i < skillList.size(); i++) {
      Skill skill = skillList.get(i);
      System.out.printf("번호: %d, 기술명: %s\n",
          skill.getNo(),
          skill.getSkillName());
    }
  }

  public void detail() {
    System.out.println("[기술 상세보기]");
    int no = Prompt.inputInt("번호? ");
    Skill skill = findByNo(no);

    if (skill == null) {
      System.out.println("해당 번호의 기술이 없습니다.");
      return;
    }

    System.out.printf("기술명: %s\n", skill.getSkillName());
    System.out.printf("상세: %s\n", skill.getDetail());
    System.out.printf("위력: %d\n", skill.getPower());
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
    String detail = Prompt.inputString(
        String.format("상세(%s)? ", skill.getDetail()));
    int power = Prompt.inputInt(
        String.format("위력(%s)? ", skill.getPower()));

    String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("기술 변경을 취소하였습니다.");
      return;
    }

    skill.setSkillName(skillName);
    skill.setDetail(detail);
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

  public Skill findByNo(int no) {
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
