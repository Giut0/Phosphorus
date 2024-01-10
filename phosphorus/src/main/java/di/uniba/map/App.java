package di.uniba.map;

import java.io.IOException;
import java.util.Map;

import java.io.File;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import di.uniba.Utils;
import di.uniba.map.game.PhosphorusGame;
import di.uniba.map.parser.Parser;
import di.uniba.map.parser.ParserOutput;
import di.uniba.map.type.Action;
import di.uniba.map.type.ActionType;
import di.uniba.map.type.Room;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

        PhosphorusGame game = new PhosphorusGame();
        Map<Integer, Room> rooms = game.getGame().getRooms();
        Map<String, Action> actions = game.getGame().getCommands();

        game.getGame().setCurrentRoom(game.getGame().getRooms().get(0));

        Parser parser = new Parser(Utils.loadFileListInSet(new File("resources/stopwords")));

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parseAction(command, game.getGame().getCommandsAsList(),
                    game.getGame().getCurrentRoom().getAdvItemsAList(), game.getGame().getCurrentRoom().getCharacters());
            if (p.getAction() == null) {
                System.out.println("Non ho capito");
            } else if (p.getAction() != null && p.getAction().getCommandType() == ActionType.EXIT) {
                System.out.println("STO USCENDO");
                scanner.close();
                break;
            } else if (p.getAction() != null && p.getAction().getCommandType() == ActionType.NORD) {
                game.getGame()
                        .setCurrentRoom(game.getGame().getRooms().get(game.getGame().getCurrentRoom().getNorth()));
                System.out.println("Sei entrato: " + game.getGame().getCurrentRoom().getName());
                System.out.println(game.getGame().getCurrentRoom().getDescription());

            } else if (p.getAction() != null && p.getAction().getCommandType() == ActionType.OSSERVA) {
                if (game.getGame().getCurrentRoom().getAdvItemsAList().size() != 0) {
                    for (int i = 0; i < game.getGame().getCurrentRoom().getAdvItemsAList().size(); i++) {
                        System.out.println("Oggetto n." + (i + 1) + " "
                                + game.getGame().getCurrentRoom().getAdvItemsAList().get(i).getItemName());
                    }
                } else {
                    System.out.println("Non ci sono oggetti nella stanza");
                }

            } else if (p.getAction() != null && p.getAction().getCommandType() == ActionType.RACCOGLI) {
                if (game.getGame().getCurrentRoom().getAdvItemsAList().size() != 0) {
                    game.getGame().getInventory().addAvdItem(p.getObject());
                    System.out.println("Hai raccolto: " + p.getObject().getItemName());
                    game.getGame().getCurrentRoom().removeItem(p.getObject().getItemName());
                } else {
                    System.out.println("Non ci sono oggetti nella stanza");
                }

                
                /*
                 * ParserOutput p = parser.parse(command, game.getGame().getCommandsAsList(),
                 * game.getGame().getCurrentRoom().getAdvItemsAList(),
                 * game.getGame().getInventory());
                 * System.out.println("B");
                 * if (p == null || p.getAction() == null) {
                 * System.out.println("Non capisco quello che mi vuoi dire.");
                 * } else if (p.getAction() != null && p.getAction().getCommandType() ==
                 * ActionType.EXIT) {
                 * System.out.println("Addio!");
                 * break;
                 * } else {
                 * //game.nextMove(p, System.out);
                 * System.out.println();
                 * }
                 */
            }else if(p.getAction() != null && p.getAction().getCommandType() == ActionType.PARLA_CON){
                if(game.getGame().getCurrentRoom().getCharacters().size() != 0){
                    System.out.println("Stai parlando con: " + p.getCharacter().getCharacterName());
                    System.out.println(p.getCharacter().getDialogs().get(0));
                }
            }

        }
        scanner.close();

    }
}
