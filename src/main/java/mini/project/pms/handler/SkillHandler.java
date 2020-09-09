package mini.project.pms.handler;

import mini.project.util.Prompt;

public class SkillHandler { // 포켓몬의 기술을 관리하는 Handler

  static class Skill {
    int no; // 번호
    String name; // 이름
    String type; // 타입
    int power; // 위력
  }
  static final int LENGTH = 100; 
  static Skill[] list = new Skill[LENGTH]; // 스킬 목록 배열 생성
  static int size = 0; 

  public static void add() { // 기술 추가
    System.out.println("[기술 등록]");

    Skill skill = new Skill();
    skill.no = Prompt.inputInt("번호? ");
    skill.name = Prompt.inputString("이름? ");
    skill.type = Prompt.inputString("타입? ");
    skill.power = Prompt.inputInt("위력? ");

    list[size++] = skill;
  }

  public static void list() { // 기술 목록 출력
    System.out.println("[기술 목록]");

    for (int i = 0; i < size; i++) {
      Skill skill = list[i];
      System.out.printf("%d, %s, %s, %d\n",
          skill.no, 
          skill.name, 
          skill.type, 
          skill.power);
    }
  }
}
