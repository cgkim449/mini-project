package mini.project.pms;

import mini.project.pms.handler.PokemonHandler;
import mini.project.util.Prompt;

public class App {

  public static void main(String[] args) {

    loop:
      while (true) {
        String command = Prompt.inputString("명령> ");

        switch (command) {
          case "/pokemon/add": PokemonHandler.add(); break;
          case "/pokemon/list": PokemonHandler.list(); break;
          case "/pokemon/detail": PokemonHandler.detail(); break;
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