package di.uniba.map.game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import di.uniba.map.Utils;
import di.uniba.map.parser.Parser;
import di.uniba.map.parser.ParserOutput;
import di.uniba.map.type.Action;
import di.uniba.map.type.ActionType;
import di.uniba.map.type.Character;
import di.uniba.map.type.Enemy;
import di.uniba.map.type.Item;
import di.uniba.map.type.KeyItem;
import di.uniba.map.type.Room;
import di.uniba.map.type.Weapon;
import di.uniba.map.ui.JKeypad;
import di.uniba.map.ui.UI;
import java.sql.Timestamp;
import java.util.Date;

import java.util.Scanner;

import javax.sound.sampled.*;

@SuppressWarnings("unchecked")
public class PhosphorusGame {

    List<Action> actions;
    List<Room> rooms;

    private GameEngine game;
    private int gameID = 0;
    Timestamp saveTimestamp;
    private boolean menuLock;
    private List<Integer> completedRoomsIds;
    private boolean musicStatus;
    private int enemyCount = 0;
    private int gameTime = 0;
    public static final String LAB_PASSWORD = "4815";

    private void initializeGameEngine() {

        this.game = new GameEngine();
        this.game.addActions(this.actions);
        this.game.addRooms(this.rooms);
        this.getGame().setCurrentRoom(this.getGame().getRooms().get(0));

    }

    public PhosphorusGame() {
        try {
            actions = initializeActions();
            rooms = initializeRooms();
            completedRoomsIds = new ArrayList<>();

            this.initializeGameEngine();
            this.menuLock = true;
            this.musicStatus = true;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getGameID() {
        return this.gameID;
    }

    public Timestamp getSaveTimestamp() {
        return this.saveTimestamp;
    }

    public void setSaveTimestamp(Timestamp saveTimestamp) {
        this.saveTimestamp = saveTimestamp;
    }

    public List<Integer> getCompletedRoomsIds() {
        return this.completedRoomsIds;
    }

    public GameEngine getGame() {
        return this.game;
    }

    public boolean getHadGun() {

        for (Item item : game.getInventory().getItems()) {
            if (item instanceof Weapon) {
                return true;
            }
        }

        return false;
    }

    public boolean isGunLocked() {

        for (Item item : game.getInventory().getItems()) {
            if (item.getItemName().equals("modifica pistola")) {
                return false;
            }
        }
        return true;
    }

    public boolean getMenuLock() {
        return this.menuLock;
    }

    public void setMusicStatus(boolean musicStatus) {
        this.musicStatus = musicStatus;
    }

    public boolean getMusicStatus() {
        return this.musicStatus;
    }

    public void setMenuLock(boolean menuLock) {
        this.menuLock = menuLock;
    }

    public void updateEnemyCount() {
        this.enemyCount++;
    }

    public void reduceEnemyCount() {
        this.enemyCount--;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public boolean checkEnd() {
        return this.getEnemyCount() == 0;
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
    public List<Room> initializeRooms() throws StreamReadException, DatabindException, IOException {

        Map<String, Object> roomsFile = Utils.readJSON("resources/rooms.json");
        List<Room> rooms = new ArrayList<>();
        List<Item> items = initializeItems();
        List<Character> characters = initializeCharacters();
        if (roomsFile != null) {

            for (int i = 0; i < roomsFile.size(); i++) {
                Map<?, ?> result = (Map<?, ?>) roomsFile.get(Integer.toString(i));
                Room room = new Room((int) result.get("roomId"), (String) result.get("roomName"),
                        (String) result.get("roomDescription"), (String) result.get("lookDescription"),
                        (int) result.get("floorNumber"), (boolean) result.get("oxygen"));
                room.setAdjacentRooms((Integer) result.get("north"), (Integer) result.get("south"),
                        (Integer) result.get("est"), (Integer) result.get("west"));

                room.setPasswordRequired((boolean) result.get("passwordRequired"));
                List<Integer> charactersIDs = (List<Integer>) result.get("characters");
                List<Integer> roomItemsIDs = (List<Integer>) result.get("items");

                for (Integer itemID : roomItemsIDs) {
                    room.addItem(items.get(itemID));
                }
                for (Integer characterID : charactersIDs) {
                    room.addRoomCharacter(characters.get(characterID));
                }

                if ((boolean) result.get("locked")) {
                    room.setLocked(true);
                }

                rooms.add(room);

            }
        }

        return rooms;

    }

    private List<Action> initializeActions() {

        List<Action> actions = new ArrayList<>();

        Action north = new Action(ActionType.NORTH, "nord");
        north.setActionAlias(new String[] { "n", "N", "Nord", "NORD" });
        actions.add(north);

        Action south = new Action(ActionType.SOUTH, "sud");
        south.setActionAlias(new String[] { "s", "S", "Sud", "SUD" });
        actions.add(south);

        Action east = new Action(ActionType.EAST, "est");
        east.setActionAlias(new String[] { "e", "E", "Est", "EST" });
        actions.add(east);

        Action west = new Action(ActionType.WEST, "ovest");
        west.setActionAlias(new String[] { "o", "O", "Ovest", "OVEST" });
        actions.add(west);

        Action end = new Action(ActionType.EXIT, "end");
        end.setActionAlias(
                new String[] { "end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit" });
        actions.add(end);

        Action look = new Action(ActionType.WATCH, "osserva");
        look.setActionAlias(new String[] { "guarda", "vedi", "trova", "cerca", "descrivi" });
        actions.add(look);

        Action pickup = new Action(ActionType.PICKUP, "raccogli");
        pickup.setActionAlias(new String[] { "prendi" });
        actions.add(pickup);

        Action talk = new Action(ActionType.TALK, "parla");
        talk.setActionAlias(new String[] { "parla", "chiedi", "conversa" });
        actions.add(talk);

        Action invetory = new Action(ActionType.INVENTORY, "inventario");
        invetory.setActionAlias(new String[] { "zaino", "oggeti", "items", "inv" });
        actions.add(invetory);

        Action shoot = new Action(ActionType.SHOOT, "spara");
        shoot.setActionAlias(new String[] { "uccidi", "ammazza", "elimina", "termina", "fredda" });
        actions.add(shoot);

        Action use = new Action(ActionType.USE, "usa");
        use.setActionAlias(new String[] { "interagisci", "utilizza" });
        actions.add(use);

        Action start = new Action(ActionType.START, "inizia");
        start.setActionAlias(new String[] { "start", "comincia", "begin", "inizio", "i", "s" });
        actions.add(start);

        Action resume = new Action(ActionType.RESUME, "continua");
        resume.setActionAlias(new String[] { "resume", "riprendi" });
        actions.add(resume);

        Action music = new Action(ActionType.MUSIC, "musica");
        music.setActionAlias(new String[] { "music", "canzone", "musichetta" });
        actions.add(music);

        Action map_ground = new Action(ActionType.MAP, "mappa");
        map_ground.setActionAlias(new String[] { "piantina" });
        actions.add(map_ground);

        Action commands_help = new Action(ActionType.COMMAND_LIST, "comandi");
        commands_help.setActionAlias(new String[] { "commands", "help", "aiuto", "h", "c" });
        actions.add(commands_help);

        Action menu = new Action(ActionType.MENU, "menu");
        menu.setActionAlias(new String[] { "m" });
        actions.add(menu);

        Action save = new Action(ActionType.SAVE, "salva");
        save.setActionAlias(new String[] { "save", "salvare", "salvataggio" });
        actions.add(save);

        return actions;
    }

    public List<Item> initializeItems() throws StreamReadException, DatabindException, IOException {
        Map<String, Object> itemsFile = Utils.readJSON("resources/items.json");
        List<Item> items = new ArrayList<>();
        if (itemsFile != null) {

            for (int i = 0; i < itemsFile.size(); i++) {
                Map<?, ?> result = (Map<?, ?>) itemsFile.get(Integer.toString(i));
                if (((String) result.get("itemType")).equals("Weapon")) {
                    Item item = new Weapon((int) result.get("itemID"), ((String) result.get("itemName")).toLowerCase(),
                            ((String) result.get("itemDescription")).toLowerCase(), (int) result.get("itemLocation"));
                    item.setItemAlias(new HashSet<>((List<String>) result.get("itemAlias")));
                    items.add(item);
                } else {
                    Item item = new KeyItem((int) result.get("itemID"), ((String) result.get("itemName")).toLowerCase(),
                            ((String) result.get("itemDescription")).toLowerCase(), (int) result.get("itemLocation"));
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
                    Enemy enemy = new Enemy((int) result.get("characterId"), (String) result.get("characterName"));

                    enemy.setMainDialog((String) result.get("mainDialog"));
                    enemy.setDefaultDialog((String) result.get("defaultDialog"));

                    characters.add(enemy);
                    this.updateEnemyCount();
                } else {
                    Character character = new Character((int) result.get("characterId"),
                            (String) result.get("characterName"));
                    character.setMainDialog((String) result.get("mainDialog"));
                    character.setDefaultDialog((String) result.get("defaultDialog"));

                    characters.add(character);
                }
            }

        }

        return characters;
    }

    public void menuMove(ParserOutput p, PrintStream out, Clip clip, GameTimer timer) {
        switch (p.getAction().getActionType()) {

            case EXIT:
                UI.printEnd(out, timer);
                clip.close();
                System.exit(0);
                break;

            case MUSIC:
                if (getMusicStatus()) {
                    out.println("\nInterruzione musica...");
                    setMusicStatus(false);
                    clip.stop();
                } else {
                    out.println("\nRiproduzione musica...");
                    setMusicStatus(true);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                break;

            case START:
                UI.printIntro(out);
                setMenuLock(false);
                timer.resetTimer();
                break;

            case RESUME: // TODO
                if (SaveGame.exist() == true) {
                    SaveGame.resume(this);
                    out.println("\nRiprisinato salvataggio: [" + this.getSaveTimestamp() + "]");
                    out.println("\nTi rinfersco un po la memoria, ti trovi in "
                            + this.getGame().getCurrentRoom().getRoomName().toLowerCase() + ", "
                            + this.getGame().getCurrentRoom().getRoomDescription().toLowerCase());
                    timer.setSeconds(this.getGameTime());
                    setMenuLock(false);
                } else {
                    System.out.println("\nNon sono disponibili salvataggi da ripristinare!");
                }

                break;

            default:
                out.println("\nNon ho capito, ti ricordo che sei ancora nel menù principale.");
                break;
        }
    }

    public void nextMove(ParserOutput p, PrintStream out, Clip clip, GameTimer timer)
            throws InvocationTargetException, InterruptedException {
        switch (p.getAction().getActionType()) {

            case EXIT:
                this.setMenuLock(true);
                this.getGame().getInventory().getItems().clear();
                UI.printMainMenu(out);

                break;

            case COMMAND_LIST:
                UI.printCommandsList(out);
                break;

            case MENU:
                this.setMenuLock(true);
                UI.printMainMenu(out);

            case SAVE:
                Date date = new Date();
                this.setSaveTimestamp(new Timestamp(date.getTime()));
                if (!SaveGame.exist()) {
                    SaveGame.createDB();
                }
                SaveGame.clearDB();
                this.setGameTime(timer.getTime());
                boolean result = SaveGame.save(this);
                if (result) {
                    out.println("\nSALVATAGGIO ESEGUITO CON SUCCESSO");
                } else {
                    out.println("\nERRORE NEL SALVATAGGIO");
                }

                break;

            case MUSIC:
                if (getMusicStatus()) {
                    out.println("\nInterruzione musica...");
                    setMusicStatus(false);
                    clip.stop();
                } else {
                    out.println("\nRiproduzione musica...");
                    setMusicStatus(true);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                break;

            case MAP:
                if (game.getCurrentRoom().getFloorNumber() == 0) {
                    UI.printGroundFloorMap(out);
                } else {
                    UI.printFirstFloorMap(out);
                }
                break;

            case NORTH:
                if (game.getCurrentRoom().getNorth() != null) {
                    if (!this.game.getRooms().get(this.game.getCurrentRoom().getNorth()).getLocked()) {
                        if (this.game.getInventory().contains("bombola")
                                || this.game.getRooms().get(this.game.getCurrentRoom().getNorth()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getNorth()));
                            out.println("\n" + this.game.getCurrentRoom().getRoomDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(this.game.getCurrentRoom().getNorth()).getRoomName()
                                    + " non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire");
                        }
                    } else if (this.game.getRooms().get(this.game.getCurrentRoom().getNorth()).getPasswordRequired()) {
                        out.println("\nInserire password di " + this.game.getRooms()
                                .get(this.game.getCurrentRoom().getNorth()).getRoomName().toLowerCase());
                        JKeypad jKeypad = new JKeypad(this.game.getRooms().get(this.game.getCurrentRoom().getNorth()));
                        jKeypad.setVisible(true);
                    } else {
                        out.println("\nLa stanza è chiusa a chiave, non puoi entrarci.");
                    }

                } else {
                    out.println("\nNon ci sono stanze a nord");
                }
                break;

            case SOUTH:
                if (game.getCurrentRoom().getSouth() != null) {
                    if (!this.game.getRooms().get(this.game.getCurrentRoom().getSouth()).getLocked()) {
                        if (this.game.getInventory().contains("bombola")
                                || this.game.getRooms().get(this.game.getCurrentRoom().getSouth()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getSouth()));
                            out.println("\n" + this.game.getCurrentRoom().getRoomDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(this.game.getCurrentRoom().getSouth()).getRoomName()
                                    + " non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire");
                        }
                    } else if (this.game.getRooms().get(this.game.getCurrentRoom().getSouth()).getPasswordRequired()) {
                        out.println("\nInserire password di " + this.game.getRooms()
                                .get(this.game.getCurrentRoom().getSouth()).getRoomName().toLowerCase());
                        JKeypad jKeypad = new JKeypad(this.game.getRooms().get(this.game.getCurrentRoom().getSouth()));
                        jKeypad.setVisible(true);
                    } else {
                        out.println("\nLa stanza è chiusa a chiave, non puoi entrarci.");
                    }

                } else {
                    out.println("\nNon ci sono stanze a sud");
                }
                break;

            case EAST:
                if (game.getCurrentRoom().getEast() != null) {
                    if (!this.game.getRooms().get(this.game.getCurrentRoom().getEast()).getLocked()) {
                        if (this.game.getInventory().contains("bombola")
                                || this.game.getRooms().get(this.game.getCurrentRoom().getEast()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getEast()));
                            out.println("\n" + this.game.getCurrentRoom().getRoomDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(this.game.getCurrentRoom().getEast()).getRoomName()
                                    + " non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire");
                        }
                    } else if (this.game.getRooms().get(this.game.getCurrentRoom().getEast()).getPasswordRequired()) {
                        out.println("\nInserire password di " + this.game.getRooms()
                                .get(this.game.getCurrentRoom().getEast()).getRoomName().toLowerCase());
                        JKeypad jKeypad = new JKeypad(this.game.getRooms().get(this.game.getCurrentRoom().getEast()));
                        jKeypad.setVisible(true);
                    } else {
                        out.println("\nLa stanza è chiusa a chiave, non puoi entrarci.");
                    }

                } else {
                    out.println("\nNon ci sono stanze a est");
                }
                break;

            case WEST:
                if (game.getCurrentRoom().getWest() != null) {
                    if (!this.game.getRooms().get(this.game.getCurrentRoom().getWest()).getLocked()) {
                        if (this.game.getInventory().contains("bombola")
                                || this.game.getRooms().get(this.game.getCurrentRoom().getWest()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getWest()));
                            out.println("\n" + this.game.getCurrentRoom().getRoomDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(this.game.getCurrentRoom().getWest()).getRoomName()
                                            .toLowerCase()
                                    + "non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire");
                        }
                    } else if (this.game.getRooms().get(this.game.getCurrentRoom().getWest()).getPasswordRequired()) {
                        out.println("\nInserire password di " + this.game.getRooms()
                                .get(this.game.getCurrentRoom().getWest()).getRoomName().toLowerCase());
                        JKeypad jKeypad = new JKeypad(this.game.getRooms().get(this.game.getCurrentRoom().getWest()));
                        jKeypad.setVisible(true);
                    } else {
                        out.println("\nLa stanza è chiusa a chiave, non puoi entrarci.");
                    }

                } else {
                    out.println("\nNon ci sono stanze a ovest");
                }
                break;

            case TALK:
                if (game.getCurrentRoom().getRoomCharacters().size() != 0) {
                    if (p.getCharacter().isAlive()) {
                        out.print("\n[" + p.getCharacter().getCharacterName() + "]: \"");
                        if (!p.getCharacter().getCompleted()) {

                            out.println(p.getCharacter().getMainDialog() + "\"");
                            p.getCharacter().setCompleted(true);
                        } else {
                            out.println(p.getCharacter().getDefaultDialog() + "\"");
                        }

                    } else {
                        out.println("\n" + p.getCharacter().getCharacterName() + " è morto, non puoi parlarci.");
                    }

                }

                break;

            case PICKUP:
                if (game.getCurrentRoom().getRoomItems().size() != 0) {
                    game.getInventory().addItem(p.getItem());
                    out.println(
                            "\nHai raccolto: " + UI.ANSI_YELLOW + p.getItem().getItemName() + UI.ANSI_RESET + ", "
                                    + p.getItem().getItemDescription());

                    if (p.getItem().getItemName().toLowerCase().equals("chiave sgabuzzino"))
                        this.getGame().getRooms().get(5).setLocked(false);

                    game.getCurrentRoom().removeItem(p.getItem().getItemName());
                    if (game.getCurrentRoom().getRoomItems().isEmpty()) {
                        this.completedRoomsIds.add(game.getCurrentRoom().getRoomID());
                    }

                } else {
                    out.println("\nNon ci sono oggetti nella stanza");
                }
                break;

            case INVENTORY:
                if (!game.getInventory().getItems().isEmpty()) {
                    for (Item invItem : game.getInventory().getItems()) {
                        out.println("- " + UI.ANSI_YELLOW + invItem.getItemName() + UI.ANSI_RESET);
                    }
                } else {
                    out.println("\nL'inventario è vuoto");
                }

                break;

            case WATCH:
                if (!this.getGame().getCurrentRoom().getRoomItems().isEmpty()) {
                    out.println("\n" + game.getCurrentRoom().getLookDescription());
                } else {
                    out.println("\n" + game.getCurrentRoom().getRoomDescription());
                }

                break;

            case SHOOT:
                if (!(p.getCharacter() == null)) {
                    if (p.getCharacter().isAlive()) {
                        if (getHadGun()) {
                            if (p.getCharacter() instanceof Enemy) {
                                this.reduceEnemyCount();
                                out.println("\nHai sparato a: " + UI.ANSI_RED + p.getCharacter().getCharacterName()
                                        + UI.ANSI_RESET
                                        + ", adesso non è più in vita.");
                                p.getCharacter().setAlive(false);
                                if (this.checkEnd()) {
                                    UI.trueEnding(out, timer);
                                    System.exit(0);
                                }
                            } else if (isGunLocked() == false) {
                                out.println("\nHai sparato a: " + UI.ANSI_BLUE + p.getCharacter().getCharacterName()
                                        + UI.ANSI_RESET
                                        + ", adesso non è più in vita.");
                                p.getCharacter().setAlive(false);
                                if (!(p.getCharacter() instanceof Enemy)) {
                                    UI.badEnding(out, timer);
                                    System.exit(0);
                                }

                            } else {
                                out.println("\nLa tua pistola non è abilitata a ferire "
                                        + p.getCharacter().getCharacterName() + "!");
                            }

                        } else {
                            out.println("\nNon puoi sparare senza un'arma, prova a cercarne una.");
                        }

                    } else {
                        out.println("\n" + p.getCharacter().getCharacterName() + " è già morto");
                    }

                } else {
                    out.println("\nNon capisco a chi vuoi sparare");
                }
                break;

            default:
                break;
        }
    }

    public void initGame(PrintStream out, Clip clip, GameTimer timer)
            throws IOException, InvocationTargetException, InterruptedException {

        UI.printTitle(out);
        UI.printMainMenu(out);

        Parser parser = new Parser(Utils.loadFileListInSet(new File("resources/stopwords")));
        Scanner scanner = new Scanner(System.in);

        out.print("\n>> ");
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parseAction(command, this.getGame().getCommandsAsList(),
                    this.getGame().getCurrentRoom().getRoomItems(),
                    this.getGame().getCurrentRoom().getRoomCharacters());
            if (p.getAction() == null) {
                out.println("\nNon ho capito");
                out.print("\n>> ");
            } else {
                if (this.getMenuLock()) {
                    this.menuMove(p, out, clip, timer);
                    out.print("\n>> ");
                } else {
                    if (!this.checkEnd()) {
                        this.nextMove(p, out, clip, timer);
                        out.print("\n>> ");
                    } else {
                        UI.printEnd(out, timer);
                        System.exit(0);
                    }

                }

            }

        }
        scanner.close();
    }
}
