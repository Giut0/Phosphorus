package di.uniba.map.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import java.io.PrintStream;
import di.uniba.Utils;
import di.uniba.map.parser.ParserOutput;
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
        ;

        Action talk = new Action(ActionType.PARLA_CON, "parla");
        talk.setCommandAlias(new String[] { "parla", "chiedi", "conversa" });
        actions.add(talk);

        Action invetory = new Action(ActionType.INVENTARIO, "inventario");
        invetory.setCommandAlias(new String[] { "zaino", "oggeti", "items" });
        actions.add(invetory);

        Action shot = new Action(ActionType.SPARA_A, "spara");
        shot.setCommandAlias(new String[] { "uccidi", "ammazza", "elimina", "termina", "fredda" });
        actions.add(shot);

        Action use = new Action(ActionType.USA, "usa");
        use.setCommandAlias(new String[] { "interagisci", "utilizza" });
        actions.add(use);

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

    public void nextMove(ParserOutput p, PrintStream out) {
        switch (p.getAction().getCommandType()) {

            case EXIT:
                out.println("STO USCENDO");
                System.exit(0);
                break;

            case NORD:
                if (game.getCurrentRoom().getNorth() != null) {
                    this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getNorth()));
                    out.println("Sei entrato: " + this.game.getCurrentRoom().getName());
                    out.println(this.game.getCurrentRoom().getDescription());
                } else {
                    out.println("Non ci sono stanze a nord");
                }
                break;

            case SUD:
                if (game.getCurrentRoom().getSouth() != null) {
                    this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getSouth()));
                    out.println("Sei entrato: " + this.game.getCurrentRoom().getName());
                    out.println(this.game.getCurrentRoom().getDescription());
                } else {
                    out.println("Non ci sono stanze a sud");
                }
                break;

            case EST:
                if (game.getCurrentRoom().getEast() != null) {
                    this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getEast()));
                    out.println("Sei entrato: " + this.game.getCurrentRoom().getName());
                    out.println(this.game.getCurrentRoom().getDescription());
                } else {
                    out.println("Non ci sono stanze a est");
                }
                break;

            case OVEST:
                if (game.getCurrentRoom().getWest() != null) {
                    this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getWest()));
                    out.println("Sei entrato: " + this.game.getCurrentRoom().getName());
                    out.println(this.game.getCurrentRoom().getDescription());
                } else {
                    out.println("Non ci sono stanze a ovest");
                }
                break;

            case PARLA_CON:
                if (game.getCurrentRoom().getCharacters().size() != 0) {
                    if (p.getCharacter().isAlive()) {
                        System.out.println(p.getCharacter().getCharacterName());
                        System.out.println(p.getCharacter().getDialogs().get(0)); // TODO: Cambiare i dialoghi in
                                                                                  // maniera dinamica
                    } else {
                        System.out.println(p.getCharacter().getCharacterName() + " è morto, non puoi parlarci.");
                    }

                } else {
                    out.println(p.getCharacter().getCharacterName() + "Non è presente in questa stanza."); // TODO:
                                                                                                           // veriricare
                                                                                                           // se è da
                                                                                                           // togliere
                }

                break;

            case RACCOGLI:
                if (game.getCurrentRoom().getAdvItemsAList().size() != 0) {
                    game.getInventory().addAvdItem(p.getObject());
                    System.out.println("Hai raccolto: " + p.getObject().getItemName());
                    game.getCurrentRoom().removeItem(p.getObject().getItemName());
                } else {
                    System.out.println("Non ci sono oggetti nella stanza");
                }
                break;

            case INVENTARIO:
                if (!game.getInventory().getInventory().isEmpty()) {
                    for (Item invItem : game.getInventory().getInventory()) {
                        out.println(invItem.getItemName());
                    }
                } else {
                    out.println("L'inventario è vuoto");
                }

                break;

            case APRI:
                if (game.getCurrentRoom().getWest() != null) {
                    this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getWest()));
                    out.println("Sei entrato: " + this.game.getCurrentRoom().getName());
                    out.println(this.game.getCurrentRoom().getDescription());
                } else {
                    out.println("Non ci sono stanze a ovest");
                }
                break;

            case OSSERVA:
                if (game.getCurrentRoom().getAdvItemsAList().size() != 0) {
                    for (int i = 0; i < game.getCurrentRoom().getAdvItemsAList().size(); i++) {
                        System.out.println("Oggetto n." + (i + 1) + " "
                                + game.getCurrentRoom().getAdvItemsAList().get(i).getItemName());
                    }
                } else {
                    System.out.println("Non ci sono oggetti nella stanza");
                }
                break;

            case SPARA_A: //TODO: da compattare
                if (!(p.getCharacter() == null)) {
                    if (p.getCharacter().isAlive()) {
                        if (game.getInventory().contains("pistola")) {
                            if (p.getCharacter() instanceof Enemy || game.getInventory().contains("modifica pistola")) {
                                out.println("Hai sparato a: " + p.getCharacter().getCharacterName() + ", adesso non è più in vita.");
                                p.getCharacter().setAlive(false);
                            } else {
                                out.println("La tua pistola non è abilitata a ferire " + p.getCharacter().getCharacterName());
                            }

                        } else {
                            out.println("Non puoi sparare senza un'arma, prova a cercarne una.");
                        }

                    } else {
                        out.println(p.getCharacter().getCharacterName() + " è già morto");
                    }

                } else {
                    out.println("Non capisco a chi vuoi sparare");
                }
                break;

            default:
                break;
        }
    }
}
