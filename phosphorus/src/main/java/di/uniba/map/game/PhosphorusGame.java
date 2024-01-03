package di.uniba.map.game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import di.uniba.map.type.Action;
import di.uniba.map.type.ActionType;
import di.uniba.map.type.Room;

@SuppressWarnings("unchecked")
public class PhosphorusGame {

    public final int ROOM_NUMBER = 6;

    private GameEngine game;

    private void initializeGame() {
        try {

            this.game = new GameEngine();
            this.game.addCommands(initializeActions());
            this.game.addRooms(initializeRooms());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public PhosphorusGame() {
        initializeGame();
    }

    public GameEngine getGame() {
        return this.game;
    }

    /**
     * Initializes and retrieves a list of rooms by reading data from a JSON file.
     *
     * @return A list containing Room objects initialized from the JSON file.
     * @throws StreamReadException If an error occurs while reading from the input
     *                             stream.
     * @throws DatabindException   If there is a failure during JSON
     *                             deserialization.
     * @throws IOException         If an I/O exception occurs while reading the
     *                             file.
     */
    private List<Room> initializeRooms() throws StreamReadException, DatabindException, IOException {

        File JSON_SOURCE_ROOMS = new File("resources/rooms.json");
        Map<String, Object> fileRooms = new ObjectMapper().readValue(JSON_SOURCE_ROOMS, HashMap.class);
        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            Map<?, ?> result = (Map<?, ?>) fileRooms.get(Integer.toString(i));
            Room room = new Room((int) result.get("roomId"), (String) result.get("roomName"),
                    (String) result.get("roomDescription"), (String) result.get("lookDescription"),
                    (int) result.get("floorNumber"), (boolean) result.get("visible"),
                    (boolean) result.get("oxygen"));
            room.setAdjacentRooms((Integer) result.get("north"), (Integer) result.get("south"),
                    (Integer) result.get("est"), (Integer) result.get("west"));
            room.setCharacters((List<Integer>) result.get("characters"));
            rooms.add(room);
        }

        return rooms;

    }

    private List<Action> initializeActions() {

        List<Action> actions = new ArrayList<>();

        Action nord = new Action(ActionType.NORD, "nord");
        nord.setCommandAlias(new String[] { "n", "N", "Nord", "NORD" });
        actions.add(nord);

        Action sud = new Action(ActionType.SUD, "sud");
        sud.setCommandAlias(new String[] { "s", "S", "Sud", "SUD" });
        actions.add(sud);

        Action est = new Action(ActionType.EST, "est");
        est.setCommandAlias(new String[] { "e", "E", "Est", "EST" });
        actions.add(est);

        Action ovest = new Action(ActionType.OVEST, "ovest");
        ovest.setCommandAlias(new String[] { "o", "O", "Ovest", "OVEST" });
        actions.add(ovest);

        Action end = new Action(ActionType.EXIT, "end");
        end.setCommandAlias(
                new String[] { "end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit" });
        actions.add(end);

        Action look = new Action(ActionType.OSSERVA, "osserva");
        look.setCommandAlias(new String[] { "guarda", "vedi", "trova", "cerca", "descrivi" });
        actions.add(look);

        Action pickup = new Action(ActionType.RACCOGLI, "raccogli");
        pickup.setCommandAlias(new String[] { "prendi" });
        actions.add(pickup);

        Action open = new Action(ActionType.APRI, "apri");
        open.setCommandAlias(new String[] {});
        actions.add(open);

        Action push = new Action(ActionType.SPINGI, "spingi");
        push.setCommandAlias(new String[] { "premi", "attiva" });
        actions.add(push);

        return actions;
    }

}
