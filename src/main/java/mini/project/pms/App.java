package mini.project.pms;

import java.util.LinkedList;
import java.util.List;
import mini.project.pms.domail.Pokemon;
import mini.project.pms.handler.PokemonHandler;
import mini.project.util.Prompt;

public class App {

  public static void main(String[] args) {

    List<Pokemon> pokemonList = new LinkedList<>();
    PokemonHandler pokemonHandler = new PokemonHandler(pokemonList);

    loop:
      while (true) {
        String command = Prompt.inputString("명령> ");

        switch (command) {
          case "/pokemon/add": pokemonHandler.add(); break;
          case "/pokemon/list": pokemonHandler.list(); break;
          case "/pokemon/detail": pokemonHandler.detail(); break;
          case "/pokemon/update": pokemonHandler.update(); break;
          case "/pokemon/delete": pokemonHandler.delete(); break;
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