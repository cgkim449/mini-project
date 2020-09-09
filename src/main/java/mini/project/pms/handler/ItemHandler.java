package mini.project.pms.handler;

import java.util.List;
import mini.project.pms.domain.Item;
import mini.project.util.Prompt;

public class ItemHandler {

  List<Item> itemList;

  public ItemHandler(List<Item> list) {
    this.itemList = list;
  }

  public void add() {
    System.out.println("[아이템 등록]");

    Item item = new Item();
    item.setNo(Prompt.inputInt("번호? "));
    item.setName(Prompt.inputString("아이템명? "));
    item.setFunction(Prompt.inputString("기능? "));
    item.setPrice(Prompt.inputInt("가격? "));

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
