package mini.project.pms.handler;

import java.util.List;
import mini.project.pms.domain.Item;
import mini.project.pms.domain.Pokemon;
import mini.project.pms.domain.Skill;
import mini.project.util.Prompt;

public class PokemonHandler {

  List<Pokemon> pokemonList;
  SkillHandler skillHandler;
  ItemHandler itemHandler;

  public PokemonHandler(List<Pokemon> list, SkillHandler skillHandler, ItemHandler itemHandler) {
    this.pokemonList = list;
    this.skillHandler = skillHandler;
    this.itemHandler = itemHandler;
  }

  public void starterPokemon() {

    Pokemon charmander = new Pokemon();
    charmander.setName("파이리");
    Pokemon squirtle = new Pokemon();
    squirtle.setName("꼬부기");
    Pokemon bulbasaur = new Pokemon();
    bulbasaur.setName("이상해씨");

    this.pokemonList.add(charmander);
    this.pokemonList.add(squirtle);
    this.pokemonList.add(bulbasaur);

    System.out.println("[스타팅 포켓몬]");

    System.out.printf("%s\n%s\n%s\n",
        this.pokemonList.get(0).getName(),
        this.pokemonList.get(1).getName(),
        this.pokemonList.get(2).getName());

    while (true) {
      String response = Prompt.inputString(
          "어떤 포켓몬을 고르시겠습니까? \n> ");
      Pokemon chosenPokemon = findByName(response);

      if (chosenPokemon != null) {
        switch(chosenPokemon.getName()) {
          case "파이리":
            chosenPokemon.setType("불");
            chosenPokemon.setSkill("불꽃세례");
            break;
          case "꼬부기":
            chosenPokemon.setType("물");
            chosenPokemon.setSkill("물대포");
            break;
          case "이상해씨":
            chosenPokemon.setType("풀");
            chosenPokemon.setSkill("덩쿨채찍");
            break;
        }

        String nickName = Prompt.inputString(
            String.format("%s의 별명은? (취소: 빈 문자열)\n> ", chosenPokemon.getName()));

        if (nickName.length() != 0) {
          chosenPokemon.setName(nickName);
        } else {
          chosenPokemon.setName(response);
        }

        chosenPokemon.setItem("나무열매");
        chosenPokemon.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));

        pokemonList.set(0, chosenPokemon);;
        pokemonList.remove(1);
        pokemonList.remove(1);
        System.out.printf("%s을(를) 받았다!\n", chosenPokemon.getName());
        System.out.println();
        break;
      } else {
        System.out.println("다시 입력해주세요\n");
      }
    }
  }

  public void add() { // 포켓몬  추가
    System.out.println("[포켓몬 등록]");
    Pokemon pokemon = new Pokemon();

    while(true) {
      int no = Prompt.inputInt("번호? ");
      boolean hasNumber = false;

      for(int i = 0; i < pokemonList.size(); i++) {
        pokemon = pokemonList.get(i);
        if(pokemon.getNo() == no) {
          System.out.println("입력하신 번호는 이미 사용중입니다.");
          hasNumber = true;
          break;
        }
      }
      if(!hasNumber) {
        pokemon = new Pokemon();
        pokemon.setNo(no);
        break;
      }
    }

    pokemon.setName(Prompt.inputString("이름? "));
    pokemon.setType(Prompt.inputString("타입? "));
    pokemon.setSkill(Prompt.inputString("기술? "));
    pokemon.setItem(Prompt.inputString("아이템? "));
    pokemon.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));

    pokemonList.add(pokemon);
  }


  public void list() { // 포켓몬 목록 출력
    System.out.println("[포켓몬 목록]");
    for (int i = 0; i < pokemonList.size(); i++) {
      Pokemon pokemon = pokemonList.get(i);
      System.out.printf("번호: %d, 이름: %s, 잡은 날짜: %s\n",
          pokemon.getNo(),
          pokemon.getName(),
          pokemon.getRegisteredDate());
    }
  }


  public void detail() {
    System.out.println("[포켓몬 상세보기]");
    int no = Prompt.inputInt("번호? ");
    Pokemon pokemon = findByNo(no);

    if (pokemon == null) {
      System.out.println("해당 번호의 포켓몬이 없습니다.");
      return;
    }

    System.out.printf("이름: %s\n", pokemon.getName());
    System.out.printf("타입: %s\n", pokemon.getType());
    System.out.printf("기술: %s\n", pokemon.getSkill());
    System.out.printf("아이템: %s\n", pokemon.getItem());
    System.out.printf("잡은 날짜: %s\n", pokemon.getRegisteredDate());
  }

  public void update() {
    System.out.println("[포켓몬 변경]");
    int no = Prompt.inputInt("번호? ");
    Pokemon pokemon = findByNo(no);

    if (pokemon == null) {
      System.out.println("해당 번호의 포켓몬이 없습니다.");
      return;
    }

    String name = Prompt.inputString(
        String.format("이름(%s)? ", pokemon.getName()));
    String type = Prompt.inputString(
        String.format("타입(%s)? ", pokemon.getType()));
    String skill = Prompt.inputString(
        String.format("기술(%s)? ", pokemon.getSkill()));
    String item = Prompt.inputString(
        String.format("아이템(%s)? ", pokemon.getSkill()));

    String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("포켓몬 변경을 취소하였습니다.");
      return;
    }

    pokemon.setName(name);
    pokemon.setType(type);
    pokemon.setSkill(skill);

    System.out.println("포켓몬을 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[포켓몬 삭제]");
    int no = Prompt.inputInt("번호? ");
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 포켓몬이 없습니다.");
      return;
    }

    String response = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("포켓몬 삭제를 취소하였습니다.");
      return;
    }

    pokemonList.remove(index);
    System.out.println("포켓몬을 삭제하였습니다.");
  }
  public void learnSkill() {
    System.out.println("[기술 가르침]");

    StringBuilder skills = new StringBuilder();
    Pokemon pokemon = new Pokemon();

    while (true) {
      int no = Prompt.inputInt("어떤 포켓몬이 기술을 배웁니까?(번호 입력) ");
      pokemon = findByNo(no);

      if (pokemon == null) {
        System.out.println("해당 번호의 포켓몬이 없습니다.");
        String response = Prompt.inputString("계속 하시겠습니까?(y/N)");
        if (!response.equalsIgnoreCase("y")) {
          System.out.println("기술 배우기를 취소합니다.");
          return;
        }
      } else {
        while (true) {
          String name = pokemon.getName();
          skills.append(pokemon.getSkill());

          System.out.printf("%s(이)가 기술을 배웁니다.\n", name);

          int skillNo = Prompt.inputInt("어떤 기술을 배웁니까?(번호 입력) ");
          Skill skill = skillHandler.findByNo(skillNo);
          if (skill != null) {
            String skillName = skill.getSkillName();
            skills.append(", ");
            skills.append(skillName);
            pokemon.setSkill(skills.toString());
            System.out.printf("%s(은)는 %s(을)를 배웠다!", name, skillName);
            System.out.println();
            return;
          } else {
            String response = Prompt.inputString("해당 기술이 없습니다, 계속 하시겠습니까?(y/N)");
            if (!response.equalsIgnoreCase("y")) {
              System.out.println("기술 배우기를 취소합니다.");
              return;
            } //y
          }
        }
      }
    }
  }

  public void getItem() {
    System.out.println("[아이템 습득]");

    StringBuilder items = new StringBuilder();
    Pokemon pokemon = new Pokemon();

    while (true) {
      int no = Prompt.inputInt("어떤 포켓몬이 아이템을 습득했습니까?(번호 입력) ");
      pokemon = findByNo(no);

      if (pokemon == null) {
        System.out.println("해당 번호의 포켓몬이 없습니다.");
        String response = Prompt.inputString("계속 하시겠습니까?(y/N) ");
        if (!response.equalsIgnoreCase("y")) {
          System.out.println("습득한 아이템 등록을 취소합니다.");
          return;
        }
      } else {
        while (true) {
          String name = pokemon.getName();
          items.append(pokemon.getItem());

          System.out.printf("%s(이)가 아이템을 습득합니다.\n", name);

          int itemNo = Prompt.inputInt("어떤 기술을 배웁니까?(번호 입력) ");
          Item item = itemHandler.findByNo(itemNo);

          if (item != null) {
            String itemName = item.getName();
            items.append(", ");
            items.append(itemName);
            pokemon.setItem(items.toString());
            System.out.printf("%s(은)는 %s(을)를 습득했다!", name, itemName);
            System.out.println();
            return;
          } else {
            String response = Prompt.inputString("해당 아이템 정보를 조회할 수 없습니다. 다시 시도하시겠습니까?(y/N)");
            if (!response.equalsIgnoreCase("y")) {
              System.out.println("습득한 아이템 등록을 취소합니다.");
              return;
            } //y
          }
        }
      }
    }
  }

  public Pokemon findByName(String name) {
    for (int i = 0; i < pokemonList.size(); i++) {
      Pokemon pokemon = pokemonList.get(i);
      if (pokemon.getName().equals(name)) {
        return pokemon;
      }
    }
    return null;
  }

  private Pokemon findByNo(int no) {
    for(int i = 0; i < pokemonList.size(); i++) {
      Pokemon pokemon = pokemonList.get(i);
      if (pokemon.getNo() == no) {
        return pokemon;
      }
    }
    return null;
  }

  private int indexOf(int no) {
    for (int i = 0; i < pokemonList.size(); i++) {
      Pokemon pokemon = pokemonList.get(i);
      if (pokemon.getNo() == no) {
        return i;
      }
    }
    return -1;
  }

}

