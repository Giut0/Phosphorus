package di.uniba.map;

import java.io.IOException;
import java.io.File;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import di.uniba.Utils;
import di.uniba.map.game.PhosphorusGame;
import di.uniba.map.game.UI;
import di.uniba.map.parser.Parser;
import di.uniba.map.parser.ParserOutput;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

        PhosphorusGame game = new PhosphorusGame();

        game.getGame().setCurrentRoom(game.getGame().getRooms().get(0));

        UI.printTitle(System.out);
        UI.printMainMenu(System.out);

        Parser parser = new Parser(Utils.loadFileListInSet(new File("resources/stopwords")));
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n>> ");
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parseAction(command, game.getGame().getCommandsAsList(),
                    game.getGame().getCurrentRoom().getAdvItemsAList(),
                    game.getGame().getCurrentRoom().getCharacters());
            if (p.getAction() == null) {
                System.out.println("Non ho capito");
            } else {
                if(game.getMenuLock()){
                    game.menuMove(p, System.out);
                    System.out.print("\n>> ");
                }else{
                    game.nextMove(p, System.out);
                    System.out.print("\n>> ");
                }
                
            }

        }
        scanner.close();

    }
}
