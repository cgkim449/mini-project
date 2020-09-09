package mini.project.pms.handler;

import java.sql.Date;
import mini.project.util.Prompt;

public class PokemonHandler {
  //포켓몬 데이터
  static class Pokemon {
    int no; // 번호
    String name; // 이름
    String type; // 타입
    String skill; // 기술
    Date registeredDate; // 잡은 날짜
  }

  static final int LENGTH = 100;
  static Pokemon[] list = new Pokemon[LENGTH]; // 포켓몬 목록 배열 생성
  static int size = 0;

  public static void add() { // 포켓몬  추가
    System.out.println("[포켓몬 등록]");

    Pokemon pokemon = new Pokemon();
    pokemon.no = Prompt.inputInt("번호? ");
    pokemon.name = Prompt.inputString("이름? ");
    pokemon.type = Prompt.inputString("타입? ");
    pokemon.skill = Prompt.inputString("기술? ");
    pokemon.registeredDate = new java.sql.Date(System.currentTimeMillis());

    list[size++] = pokemon;
  }

  public static void list() { // 포켓몬 목록 출력
    System.out.println("[포켓몬 목록]");

    for (int i = 0; i < size; i++) {
      Pokemon pokemon = list[i];
      System.out.printf("%d, %s, %s, %s, %s\n",
          pokemon.no,
          pokemon.name,
          pokemon.type,
          pokemon.skill,
          pokemon.registeredDate);
    }
  }

  public static void detail() {
    System.out.println("[포켓몬 상세보기]");
    int no = Prompt.inputInt("번호? ");
    Pokemon pokemon = findByNo(no);

    if (pokemon == null) {
      System.out.println("해당 번호의 포켓몬이 없습니다.");
      return;
    }

    System.out.printf("이름: %s\n", pokemon.name);
    System.out.printf("타입: %s\n", pokemon.type);
    System.out.printf("기술: %s\n", pokemon.skill);
    System.out.printf("등록일: %s\n", pokemon.registeredDate);
  }

  public static Pokemon findByName(String name) {
    for (int i = 0; i < size; i++) {
      Pokemon Pokemon = list[i];
      if (Pokemon.name.equals(name)) {
        return Pokemon;
      }
    }
    return null;
  }

  private static Pokemon findByNo(int no) {
    for(int i = 0; i < list.length; i++) {
      Pokemon pokemon = list[i];
      if (pokemon.no == no) {
        return pokemon;
      }
    }
    return null;
  }

}

