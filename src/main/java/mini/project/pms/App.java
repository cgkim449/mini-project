package mini.project.pms;

import java.util.LinkedList;
import java.util.List;
import mini.project.pms.domain.Pokemon;
import mini.project.pms.domain.Skill;
import mini.project.pms.handler.PokemonHandler;
import mini.project.pms.handler.SkillHandler;
import mini.project.util.Prompt;

public class App {

  public static void main(String[] args) {

    List<Pokemon> pokemonList = new LinkedList<>();
    PokemonHandler pokemonHandler = new PokemonHandler(pokemonList);
    List<Skill> skillList = new LinkedList<>();
    SkillHandler skillHandler = new SkillHandler(skillList);

    loop:
      while (true) {
        String command = Prompt.inputString("명령> ");

        switch (command) {
          case "/pokemon/add": pokemonHandler.add(); break;
          case "/pokemon/list": pokemonHandler.list(); break;
          case "/pokemon/detail": pokemonHandler.detail(); break;
          case "/pokemon/update": pokemonHandler.update(); break;
          case "/pokemon/delete": pokemonHandler.delete(); break;
          case "/skill/add": skillHandler.add(); break;
          case "/skill/list": skillHandler.list(); break;
          case "/skill/update": skillHandler.update(); break;
          case "/skill/delete": skillHandler.delete(); break;
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

