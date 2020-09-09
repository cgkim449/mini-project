package mini.project.pms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import mini.project.pms.domain.Item;
import mini.project.pms.domain.Pokemon;
import mini.project.pms.domain.Skill;
import mini.project.pms.handler.ItemHandler;
import mini.project.pms.handler.PokemonHandler;
import mini.project.pms.handler.SkillHandler;
import mini.project.util.Prompt;

public class App {

  public static void main(String[] args) {

    List<Skill> skillList = new LinkedList<>();
    SkillHandler skillHandler = new SkillHandler(skillList);

    List<Item> itemList = new LinkedList<>();
    ItemHandler itemHandler = new ItemHandler(itemList);

    List<Pokemon> pokemonList = new ArrayList<>();
    PokemonHandler pokemonHandler = new PokemonHandler(pokemonList, skillHandler, itemHandler);

    pokemonHandler.starterPokemon();

    loop:
      while (true) {

        System.out.println("도움이 필요하시면 '/help'를 입력하세요!");
        String command = Prompt.inputString("명령> ");

        switch (command) {
          case "/help" : help(); break;
          case "/pokemon/add": pokemonHandler.add(); break;
          case "/pokemon/list": pokemonHandler.list(); break;
          case "/pokemon/detail": pokemonHandler.detail(); break;
          case "/pokemon/update": pokemonHandler.update(); break;
          case "/pokemon/delete": pokemonHandler.delete(); break;
          case "/pokemon/teach": pokemonHandler.teach(); break;
          case "/pokemon/obtain": pokemonHandler.obtain(); break;
          case "/skill/add": skillHandler.add(); break;
          case "/skill/list": skillHandler.list(); break;
          case "/skill/detail": skillHandler.detail(); break;
          case "/skill/update": skillHandler.update(); break;
          case "/skill/delete": skillHandler.delete(); break;
          case "/item/add": itemHandler.add(); break;
          case "/item/list": itemHandler.list(); break;
          case "/item/detail": itemHandler.detail(); break;
          case "/item/update": itemHandler.update(); break;
          case "/item/delete": itemHandler.delete(); break;
          case "quit":
          case "exit":
            System.out.println("안녕!!");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령입니다.");
        }
        System.out.println();
      }

    Prompt.close();
  }

  public static void help() {
    System.out.println("[사용 설명서]");
    System.out.println("/pokemon/add : 포켓몬 신규 등록");
    System.out.println("/pokemon/list : 포켓몬 목록 조회");
    System.out.println("/pokemon/detail : 포켓몬 상세 조회");
    System.out.println("/pokemon/update : 포켓몬 변경");
    System.out.println("/pokemon/delete : 포켓몬 삭제");
    System.out.println("/pokemon/teach : 포켓몬에게 기술 가르침");
    System.out.println("/pokemon/obtain : 포켓몬이 습득한 아이템 등록");
    System.out.println("/skill/add : 기술 등록");
    System.out.println("/skill/list : 기술 목록 조회");
    System.out.println("/skill/detail : 기술 상세 조회");
    System.out.println("/skill/update : 기술 변경");
    System.out.println("/skill/delete : 기술 삭제");
    System.out.println("/item/add : 아이템 등록");
    System.out.println("/item/list : 아이템 목록 조회");
    System.out.println("/item/detail : 아이템 상세 조회");
    System.out.println("/item/update : 아이템 변경");
    System.out.println("/item/delete : 아이템 삭제");
  }

}

