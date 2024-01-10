package di.uniba.map.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import di.uniba.Utils;
import di.uniba.map.type.Action;
import di.uniba.map.type.ActionType;
import di.uniba.map.type.Character;
import di.uniba.map.type.Enemy;
import di.uniba.map.type.Item;
import di.uniba.map.type.KeyItem;
import di.uniba.map.type.Room;
import di.uniba.map.type.Weapon;

@SuppressWarnings("unchecked")
public class PhosphorusGame {

    private GameEngine game;

    private void initializeGame() {
        try {

            this.game = new GameEngine();
            this.game.addCommands(initializeActions());
            this.game.addRooms(initializeRooms());

        } catch (Exception e) {
            e.printStackTrace();
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

        Map<String, Object> roomsFile = Utils.readJSON("resources/rooms.json");
        List<Room> rooms = new ArrayList<>();
        List<Item> items = initializeItems();
        List<Character> characters = initializeCharacters();
        if (roomsFile != null) {

            for (int i = 0; i < roomsFile.size(); i++) {
                Map<?, ?> result = (Map<?, ?>) roomsFile.get(Integer.toString(i));
                Room room = new Room((int) result.get("roomId"), (String) result.get("roomName"),
                        (String) result.get("roomDescription"), (String) result.get("lookDescription"),
                        (int) result.get("floorNumber"), (boolean) result.get("visible"),
                        (boolean) result.get("oxygen"));
                room.setAdjacentRooms((Integer) result.get("north"), (Integer) result.get("south"),
                        (Integer) result.get("est"), (Integer) result.get("west"));
                List<Integer> charactersIDs = (List<Integer>) result.get("characters");
                List<Integer> roomItemsIDs = (List<Integer>) result.get("items");

                for (Integer itemID : roomItemsIDs) {
                    room.addAdvItem(items.get(itemID));
                }

                for (Integer characterID : charactersIDs) {
                    room.addCharacter(characters.get(characterID));
                }

                rooms.add(room);

            }
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

        Action talk = new Action(ActionType.PARLA_CON, "parla");
        talk.setCommandAlias(new String[] { "parla", "chiedi", "conversa" });
        actions.add(talk);

        return actions;
    }

    private List<Item> initializeItems() throws StreamReadException, DatabindException, IOException {
        Map<String, Object> itemsFile = Utils.readJSON("resources/items.json");
        List<Item> items = new ArrayList<>();
        if (itemsFile != null) {

            for (int i = 0; i < itemsFile.size(); i++) {
                Map<?, ?> result = (Map<?, ?>) itemsFile.get(Integer.toString(i));
                if (((String) result.get("itemType")).equals("Weapon")) {
                    Item item = new Weapon((int) result.get("itemID"), (String) result.get("itemName"),
                            (String) result.get("itemDescription"), (int) result.get("itemLocation"));
                    item.setItemAlias(new HashSet<>((List<String>) result.get("itemAlias")));
                    items.add(item);
                } else {
                    Item item = new KeyItem((int) result.get("itemID"), (String) result.get("itemName"),
                            (String) result.get("itemDescription"), (int) result.get("itemLocation"));
                    item.setItemAlias(new HashSet<>((List<String>) result.get("itemAlias")));
                    items.add(item);
                }

            }
        }
        return items;
    }

    private List<Character> initializeCharacters() throws StreamReadException, DatabindException, IOException {
        Map<String, Object> charactersFile = Utils.readJSON("resources/characters.json");
        List<Character> characters = new ArrayList<>();

        if (charactersFile != null) {

            for (int i = 0; i < charactersFile.size(); i++) {
                Map<?, ?> result = (Map<?, ?>) charactersFile.get(Integer.toString(i));
                if (((String) result.get("type")).equals("enemy")) {
                    Enemy enemy = new Enemy((int) result.get("characterId"), (String) result.get("characterName"),
                            (String) result.get("characterDescription"));
                    List<String> dialogs = (List<String>) result.get("dialogs");
                    enemy.setDialogs(dialogs);
                    characters.add(enemy);
                } else {
                    Character character = new Character((int) result.get("characterId"),
                            (String) result.get("characterName"),
                            (String) result.get("characterDescription"));
                    List<String> dialogs = (List<String>) result.get("dialogs");
                    character.setDialogs(dialogs);
                    characters.add(character);
                }
            }

        }

        return characters;
    }
}

/*
 * "0": {
 * "characterId": 0,
 * "characterName": "Agente u13",
 * "characterDescription": "...",
 * "dialogs": [
 * "Buongiorno f24, ho saputo che sei nuovo, hai sentito il comandante, dobbiamo andare in sala meeting. Il comandante è un tipo un po all'antica, sembra fermo al 1984, ma vabbè andiamo."
 * ,
 * "Cosa c'è non ti ricordi dove si trova? Appena a nord di quì, ti lascio l'onore di premere il pulsante per aprire la porta."
 * ,
 * "Ti ho detto che è un tipo un po' all'antica, io comunque rimango quì per tenere sotto controllo questa zona, va pure con r17, non è un tipo molto loquace, ma sa il fatto suo."
 * ],
 * "type": "enemy"
 * 
 * 
 */