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
    List<Pokemon> pokemonList = new ArrayList<>();
    PokemonHandler pokemonHandler = new PokemonHandler(pokemonList, skillHandler);

    List<Item> itemList = new LinkedList<>();
    ItemHandler itemHandler = new ItemHandler(itemList);

    pokemonHandler.starterPokemon();

    loop:
      while (true) {

        String command = Prompt.inputString("명령> ");

        switch (command) {
          case "/pokemon/add": pokemonHandler.add(); break;
          case "/pokemon/list": pokemonHandler.list(); break;
          case "/pokemon/detail": pokemonHandler.detail(); break;
          case "/pokemon/update": pokemonHandler.update(); break;
          case "/pokemon/delete": pokemonHandler.delete(); break;
          case "/pokemon/teach": pokemonHandler.teach(); break;
          case "/skill/add": skillHandler.add(); break;
          case "/skill/list": skillHandler.list(); break;
          case "/skill/update": skillHandler.update(); break;
          case "/skill/delete": skillHandler.delete(); break;
          case "/item/add": itemHandler.add(); break;
          case "/item/list": itemHandler.list(); break;
          case "/item/detail": itemHandler.detail(); break;
          case "/item/update": itemHandler.update(); break;
          case "/item/delete": itemHandler.delete(); break;
          case "quit":
          case "exit":
            System.out.println("안녕!");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령입니다.");
        }
        System.out.println();
      }

    Prompt.close();
  }
}

