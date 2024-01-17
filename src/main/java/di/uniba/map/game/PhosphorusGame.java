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

import java.util.Scanner;

import javax.sound.sampled.*;

@SuppressWarnings("unchecked")
public class PhosphorusGame {

    private GameEngine game;
    private boolean menuLock;
    private boolean hadGun;
    private boolean gunLocked;
    private boolean musicStatus;
    private int enemyCount = 0;
    public static final String LAB_PASSWORD = "4815";

    private void initializeGame() {
        try {

            this.game = new GameEngine();
            this.game.addCommands(initializeActions());
            this.game.addRooms(initializeRooms());
            this.menuLock = true;
            this.hadGun = false;
            this.gunLocked = true;
            this.musicStatus = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PhosphorusGame() {
        initializeGame();
        this.getGame().setCurrentRoom(this.getGame().getRooms().get(0));
    }

    public GameEngine getGame() {
        return this.game;
    }

    public void setHadGun(boolean hadGun) {
        this.hadGun = hadGun;
    }

    public boolean getHadGun() {
        return this.hadGun;
    }

    public boolean getGunLocked() {
        return this.gunLocked;
    }

    public void setGunLocked(boolean gunLocked) {
        this.gunLocked = gunLocked;
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
                        (int) result.get("floorNumber"), (boolean) result.get("oxygen"));
                room.setAdjacentRooms((Integer) result.get("north"), (Integer) result.get("south"),
                        (Integer) result.get("est"), (Integer) result.get("west"));

                room.setPasswordRequired((boolean) result.get("passwordRequired"));
                List<Integer> charactersIDs = (List<Integer>) result.get("characters");
                List<Integer> roomItemsIDs = (List<Integer>) result.get("items");

                for (Integer itemID : roomItemsIDs) {
                    room.addAdvItem(items.get(itemID));
                }
                for (Integer characterID : charactersIDs) {
                    room.addCharacter(characters.get(characterID));
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

        Action start = new Action(ActionType.START, "inizia");
        start.setCommandAlias(new String[] { "start", "comincia", "begin", "inizio", "i" });
        actions.add(start);

        Action resume = new Action(ActionType.RESUME, "continua");
        resume.setCommandAlias(new String[] { "resume", "riprendi" });
        actions.add(resume);

        Action music = new Action(ActionType.MUSIC, "musica");
        music.setCommandAlias(new String[] { "music", "canzone", "musichetta" });
        actions.add(music);

        Action map_ground = new Action(ActionType.MAPPA, "mappa");
        map_ground.setCommandAlias(new String[] { "piantina" });
        actions.add(map_ground);

        Action commands_help = new Action(ActionType.COMMAND_LIST, "comandi");
        commands_help.setCommandAlias(new String[] { "commands", "help", "aiuto" });
        actions.add(commands_help);

        return actions;
    }

    private List<Item> initializeItems() throws StreamReadException, DatabindException, IOException {
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
                    Enemy enemy = new Enemy((int) result.get("characterId"), (String) result.get("characterName"),
                            (String) result.get("characterDescription"));

                    enemy.setMainDialog((String) result.get("mainDialog"));
                    enemy.setDefaultDialog((String) result.get("defaultDialog"));

                    characters.add(enemy);
                    this.updateEnemyCount();
                } else {
                    Character character = new Character((int) result.get("characterId"),
                            (String) result.get("characterName"),
                            (String) result.get("characterDescription"));
                    character.setMainDialog((String) result.get("mainDialog"));
                    character.setDefaultDialog((String) result.get("defaultDialog"));

                    characters.add(character);
                }
            }

        }

        return characters;
    }

    public void menuMove(ParserOutput p, PrintStream out, Clip clip) {
        switch (p.getAction().getCommandType()) {

            case EXIT: // TODO: migliorare
                out.println("STO USCENDO");
                System.exit(0);
                break;

            case MUSIC:
                if (getMusicStatus()) { // TODO: migliorare UI
                    out.println("STOP MUSIC");
                    setMusicStatus(false);
                    clip.stop();
                } else {
                    out.println("RESUME MUSIC");
                    setMusicStatus(true);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                break;

            case START:
                UI.printIntro(out);

                setMenuLock(false);

                this.getGame().setCurrentRoom(this.getGame().getRooms().get(0));
                break;

            case RESUME: // TODO

                setMenuLock(false);
                break;

            default:
                out.println("\nNon ho capito, ti ricordo che sei ancora nel menù principale.");
                break;
        }
    }

    public void nextMove(ParserOutput p, PrintStream out, Clip clip)
            throws InvocationTargetException, InterruptedException {
        switch (p.getAction().getCommandType()) {

            case EXIT: // TODO: migliorare
                out.println("STO USCENDO");
                System.exit(0);
                break;

            case COMMAND_LIST:
                UI.printCommandsList(out);
                break;

            case MUSIC: // TODO: migliorare UI
                if (getMusicStatus()) {
                    out.println("STOP MUSIC");
                    setMusicStatus(false);
                    clip.stop();
                } else {
                    out.println("RESUME MUSIC");
                    setMusicStatus(true);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                break;

            case MAPPA:
                if (game.getCurrentRoom().getFloorNumber() == 0) {
                    UI.printGroundFloorMap(out);
                } else {
                    UI.printFirstFloorMap(out);
                }
                break;

            case NORD:

                if (game.getCurrentRoom().getNorth() != null) {
                    if (!this.game.getRooms().get(this.game.getCurrentRoom().getNorth()).getLocked()) {
                        if (this.game.getInventory().contains("bombola")
                                || this.game.getRooms().get(this.game.getCurrentRoom().getNorth()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getNorth()));
                            out.println("\n" + this.game.getCurrentRoom().getDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(this.game.getCurrentRoom().getNorth()).getName()
                                    + " non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire");
                        }
                    } else if (this.game.getRooms().get(this.game.getCurrentRoom().getNorth()).getPasswordRequired()) {
                        JKeypad jKeypad = new JKeypad(this.game.getRooms().get(this.game.getCurrentRoom().getNorth()));
                        jKeypad.setVisible(true);
                    } else {
                        out.println("\nLa stanza è chiusa a chiave, non puoi entrarci.");
                    }

                } else {
                    out.println("\nNon ci sono stanze a nord");
                }
                break;

            case SUD:
                if (game.getCurrentRoom().getSouth() != null) {
                    if (!this.game.getRooms().get(this.game.getCurrentRoom().getSouth()).getLocked()) {
                        if (this.game.getInventory().contains("bombola")
                                || this.game.getRooms().get(this.game.getCurrentRoom().getSouth()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getSouth()));
                            out.println("\n" + this.game.getCurrentRoom().getDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(this.game.getCurrentRoom().getSouth()).getName()
                                    + " non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire");
                        }
                    } else if (this.game.getRooms().get(this.game.getCurrentRoom().getSouth()).getPasswordRequired()) {
                        JKeypad jKeypad = new JKeypad(this.game.getRooms().get(this.game.getCurrentRoom().getSouth()));
                        jKeypad.setVisible(true);
                    } else {
                        out.println("\nLa stanza è chiusa a chiave, non puoi entrarci.");
                    }

                } else {
                    out.println("\nNon ci sono stanze a sud");
                }
                break;

            case EST:
                if (game.getCurrentRoom().getEast() != null) {
                    if (!this.game.getRooms().get(this.game.getCurrentRoom().getEast()).getLocked()) {
                        if (this.game.getInventory().contains("bombola")
                                || this.game.getRooms().get(this.game.getCurrentRoom().getEast()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getEast()));
                            out.println("\n" + this.game.getCurrentRoom().getDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(this.game.getCurrentRoom().getEast()).getName()
                                    + " non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire");
                        }
                    } else if (this.game.getRooms().get(this.game.getCurrentRoom().getEast()).getPasswordRequired()) {
                        JKeypad jKeypad = new JKeypad(this.game.getRooms().get(this.game.getCurrentRoom().getEast()));
                        jKeypad.setVisible(true);
                    } else {
                        out.println("\nLa stanza è chiusa a chiave, non puoi entrarci.");
                    }

                } else {
                    out.println("\nNon ci sono stanze a est");
                }
                break;

            case OVEST:
                if (game.getCurrentRoom().getWest() != null) {
                    if (!this.game.getRooms().get(this.game.getCurrentRoom().getWest()).getLocked()) {
                        if (this.game.getInventory().contains("bombola")
                                || this.game.getRooms().get(this.game.getCurrentRoom().getWest()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getWest()));
                            out.println("\n" + this.game.getCurrentRoom().getDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(this.game.getCurrentRoom().getWest()).getName()
                                    + "non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire");
                        }
                    } else if (this.game.getRooms().get(this.game.getCurrentRoom().getWest()).getPasswordRequired()) {
                        JKeypad jKeypad = new JKeypad(this.game.getRooms().get(this.game.getCurrentRoom().getWest()));
                        jKeypad.setVisible(true);
                    } else {
                        out.println("\nLa stanza è chiusa a chiave, non puoi entrarci.");
                    }

                } else {
                    out.println("\nNon ci sono stanze a ovest");
                }
                break;

            case PARLA_CON:
                if (game.getCurrentRoom().getCharacters().size() != 0) {
                    if (p.getCharacter().isAlive()) {
                        System.out.print("\n[" + p.getCharacter().getCharacterName() + "]: \"");
                        if (!p.getCharacter().getCompleted()) {

                            System.out.println(p.getCharacter().getMainDialog() + "\"");
                            p.getCharacter().setCompleted(true);
                        } else {
                            System.out.println(p.getCharacter().getDefaultDialog() + "\"");
                        }

                    } else {
                        System.out.println("\n" + p.getCharacter().getCharacterName() + " è morto, non puoi parlarci.");
                    }

                } else {
                    out.println("\n" + p.getCharacter().getCharacterName() + "Non è presente in questa stanza."); // TODO:
                                                                                                                  // veriricare
                                                                                                                  // e è
                                                                                                                  // da
                                                                                                                  // ogliere
                }

                break;

            case RACCOGLI:
                if (game.getCurrentRoom().getAdvItemsAList().size() != 0) {
                    game.getInventory().addAvdItem(p.getObject());
                    System.out.println("\nHai raccolto: " + p.getObject().getItemName() + ", "
                            + p.getObject().getItemDescription());
                    if (p.getObject() instanceof Weapon)
                        this.setHadGun(true);

                    if (p.getObject().getItemAlias().contains("modifica"))
                        this.setGunLocked(false);

                    if (p.getObject().getItemName().toLowerCase().equals("chiave sgabuzzino"))
                        this.getGame().getRooms().get(5).setLocked(false);

                    game.getCurrentRoom().removeItem(p.getObject().getItemName());

                } else {
                    System.out.println("\nNon ci sono oggetti nella stanza");
                }
                break;

            case INVENTARIO:
                if (!game.getInventory().getInventory().isEmpty()) {
                    for (Item invItem : game.getInventory().getInventory()) {
                        out.println(invItem.getItemName());
                    }
                } else {
                    out.println("\nL'inventario è vuoto");
                }

                break;

            case OSSERVA:
                if (!this.getGame().getCurrentRoom().getAdvItemsAList().isEmpty()) {
                    out.println("\n" + game.getCurrentRoom().getLookDescription());
                } else {
                    out.println("\n" + game.getCurrentRoom().getDescription());
                }

                break;

            case SPARA_A:
                if (!(p.getCharacter() == null)) {
                    if (p.getCharacter().isAlive()) {
                        if (getHadGun()) {
                            if (p.getCharacter() instanceof Enemy)
                                this.reduceEnemyCount();
                            if (getGunLocked() == false) {
                                out.println("\nHai sparato a: " + p.getCharacter().getCharacterName()
                                        + ", adesso non è più in vita.");
                                p.getCharacter().setAlive(false);
                                UI.badEning(out);
                                System.exit(0);
                            } else {
                                out.println("\nLa tua pistola non è abilitata a ferire "
                                        + p.getCharacter().getCharacterName());
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

    public void initGame(Clip clip) throws IOException, InvocationTargetException, InterruptedException {

        UI.printTitle(System.out);
        UI.printMainMenu(System.out);

        Parser parser = new Parser(Utils.loadFileListInSet(new File("resources/stopwords")));
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n>> ");
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parseAction(command, this.getGame().getCommandsAsList(),
                    this.getGame().getCurrentRoom().getAdvItemsAList(),
                    this.getGame().getCurrentRoom().getCharacters());
            if (p.getAction() == null) {
                System.out.println("\nNon ho capito");
                System.out.print("\n>> ");
            } else {
                if (this.getMenuLock()) {
                    this.menuMove(p, System.out, clip);
                    System.out.print("\n>> ");
                } else {
                    if (!this.checkEnd()) {
                        this.nextMove(p, System.out, clip);
                        System.out.print("\n>> ");
                    } else {
                        UI.trueEnding(System.out);
                        System.exit(0);
                    }

                }

            }

        }
        scanner.close();
    }
}
