package di.uniba.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import di.uniba.map.game.PhosphorusGame;
import di.uniba.map.type.Action;
import di.uniba.map.type.Room;

public class App {
    public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

        PhosphorusGame game = new PhosphorusGame();
        Map<String, Room> rooms = game.getGame().getRooms();
        Map<String, Action> actions = game.getGame().getCommands();

        System.out.println(rooms.get("Laboratorio").getAdvItems().get(0).getItemName());

        System.out.println(actions.get("nord").getCommandAlias());

   

    }

}
