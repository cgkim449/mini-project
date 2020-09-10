package mini.project.pms.handler;

import java.util.List;
import mini.project.pms.domain.Item;
import mini.project.pms.domain.Pokemon;
import mini.project.util.Prompt;

public class ItemHandler {

  List<Item> itemList;

  public ItemHandler(List<Item> list) {
    this.itemList = list;
  }

  public void choose(PokemonHandler pokemonHandler) { // 의존객체를 파라미터로 주입

    Item chosenItem = new Item();

    loop1 : while (true) {
      System.out.println("[아이템 선택]");
      String name = Prompt.inputString("어떤 아이템을 사용하시겠습니까? ");
      chosenItem = findByName(name);
      if (chosenItem == null) {
        String response = Prompt.inputString("해당 아이템이 없습니다, 계속 하시겠습니까?(y/N) ");
        if (!response.equalsIgnoreCase("y")) {
          System.out.println("아이템 선택을 취소합니다.");
          return;
        }
      } else {
        loop :while (true) {
          System.out.println("사용하다: 1");
          System.out.println("지니게하다: 2");
          System.out.println("버리다: 3");
          System.out.println("그만두다: 4");
          final int USE = 1;
          final int GIVE = 2;
          final int THROW = 3;
          final int QUIT = 4;
          int response = Prompt.inputInt("(번호 입력) > ");

          if (response < 1 || response > 5) {
            System.out.println("다시 입력해주세요");
            continue loop;
          }
          switch (response) {
            case USE:
              Pokemon chosenPokemon = new Pokemon();
              while (true) {
                String name1 = Prompt.inputString("어떤 포켓몬에게 사용하시겠습니까? ");
                chosenPokemon = pokemonHandler.findByName(name1);
                if (chosenPokemon == null) {
                  String response1 = Prompt.inputString(
                      "해당 포켓몬이 없습니다, 계속 하시겠습니까?(y/N) ");
                  if (!response1.equalsIgnoreCase("y")) {
                    System.out.println("아이템 선택을 취소합니다.");
                    return;
                  }
                } else {
                  switch (chosenItem.getName()) {
                    case "나무열매":
                      System.out.printf("%s(은)는 hp를 10 회복했다\n", chosenPokemon.getName());
                      return;
                    case "이상한사탕":
                      System.out.printf("%s(은)는 레벨이 1 올랐다!\n", chosenPokemon.getName());
                      return;
                    case "타우린":
                      System.out.printf("%s(은)는 공격력이 1 올랐다!\n", chosenPokemon.getName());
                      return;
                    case "사포닌":
                      System.out.printf("%s(은)는 방어력이 1이 올랐다!\n", chosenPokemon.getName());
                      return;
                  }
                }
              }
            case GIVE:
              while (true) {
                String name1 = Prompt.inputString("어떤 포켓몬에게 주시겠습니까? ");
                chosenPokemon = pokemonHandler.findByName(name1);
                if (chosenPokemon == null) {
                  String response1 = Prompt.inputString(
                      "해당 포켓몬이 없습니다, 계속 하시겠습니까?(y/N) ");
                  if (!response1.equalsIgnoreCase("y")) {
                    System.out.println("아이템 선택을 취소합니다.");
                    return;
                  }
                } else {
                  System.out.printf("%s에게 %s(을)를 지니게했다!\n",chosenPokemon.getName(),chosenItem.getName());
                  return;
                }
              }
            case THROW:
              String response1 = Prompt.inputString("정말 버리시겠습니까? (y/N)\n");
              if(!response1.equalsIgnoreCase("y")) {
                System.out.printf("%s(을)를 버렸습니다\n", chosenItem.getName());
                return;
              } else {
                System.out.print("버리지 않고 끝났다\n");
                return;
              }
            case QUIT:
              System.out.print("아이템 선택을 취소합니다\n");
              return;
            default:
              System.out.print("다시 입력해주세요\n");
              continue loop;
          }
        }
      }
    }
  }

  public void add() {
    System.out.println("[아이템 등록]");
    Item item = new Item();

    final int BERRY = 1;
    final int CANDY = 2;
    final int TAURINE = 3;
    final int SAPONIN = 4;

    while(true) {
      int no = Prompt.inputInt("번호? ");
      boolean hasNumber = false;

      for(int i = 0; i < itemList.size(); i++) {
        item = itemList.get(i);
        if(item.getNo() == no) {
          System.out.println("입력하신 번호는 이미 사용중입니다.");
          hasNumber = true;
          break;
        }
      }
      if(!hasNumber) {
        item = new Item();
        item.setNo(no);

        loop : while(true) {
          System.out.println("어떤 아이템을 등록하시겠습니까? ");
          System.out.println("나무열매: 1");
          System.out.println("이상한 사탕: 2");
          System.out.println("타우린: 3");
          System.out.println("사포닌: 4");
          int response = Prompt.inputInt("(번호 입력) > ");

          if (response > 4 || response < 1) {
            System.out.println("다시 입력해주세요.");
          } else {
            switch (response) {
              case BERRY:
                item.setFunction("HP 10 회복");
                item.setPrice(10);
                item.setName("나무열매");
                break;
              case CANDY:
                item.setFunction("레벨 1 증가");
                item.setPrice(100);
                item.setName("이상한 사탕");
                break;
              case TAURINE:
                item.setFunction("공격력 1 증가");
                item.setPrice(50);
                item.setName("타우린");
                break;
              case SAPONIN:
                item.setFunction("방어력 1 증가");
                item.setPrice(40);
                item.setName("사포닌");
                break;
            }
            break loop;
          }
          break;
        }

        break;
      }
    }
    itemList.add(item);
  }

  public void list() {
    System.out.println("[아이템 목록]");

    for(int i = 0; i < itemList.size(); i++ ) {
      Item item = itemList.get(i);
      System.out.printf("번호: %d, 아이템명: %s\n",
          item.getNo(),
          item.getName());
    }
  }

  public void detail() {
    System.out.println("[아이템 상세보기]");
    int no = Prompt.inputInt("번호? ");
    Item item = findByNo(no);

    if (item == null) {
      System.out.println("해당 아이템이 없습니다.");
      return;
    }

    System.out.printf("이름: %s\n", item.getName());
    System.out.printf("기능: %s\n", item.getFunction());
    System.out.printf("가격: %d\n", item.getPrice());
  }

  public void update() {
    System.out.println("[아이템 변경]");
    int no = Prompt.inputInt("번호? ");
    Item item = findByNo(no);

    if (item == null) {
      System.out.println("해당 아이템이 없습니다.");
      return;
    }

    String function = Prompt.inputString(
        String.format("아이템명(%s)? ", item.getName()));
    String name = Prompt.inputString(
        String.format("기능(%s)? ", item.getFunction()));
    int price = Prompt.inputInt(
        String.format("가격(%s)? ", item.getPrice()));

    String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("아이템 변경을 취소하였습니다.");
      return;
    }

    item.setName(name);
    item.setFunction(function);
    item.setPrice(price);

    System.out.println("아이템을 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[아이템 삭제]");
    int no = Prompt.inputInt("번호? ");
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 아이템이 없습니다.");
      return;
    }

    String response = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (!response.equalsIgnoreCase("y")) {
      System.out.println("아이템 삭제를 취소하였습니다.");
      return;
    }

    itemList.remove(index);
    System.out.println("아이템을 삭제하였습니다.");
  }

  public Item findByName(String name) {
    for (int i = 0; i < itemList.size(); i++) {
      Item item = itemList.get(i);
      if (item.getName().equals(name)) {
        return item;
      }
    }
    return null;
  }

  private Item findByNo(int no) {
    for(int i = 0; i < itemList.size(); i++) {
      Item item = itemList.get(i);
      if (item.getNo() == no) {
        return item;
      }
    }
    return null;
  }

  private int indexOf(int no) {
    for (int i = 0; i < itemList.size(); i++) {
      Item item = itemList.get(i);
      if (item.getNo() == no) {
        return i;
      }
    }
    return -1;
  }

}
