package di.uniba.map.game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import di.uniba.map.Utils;
import di.uniba.map.parser.Parser;
import di.uniba.map.parser.ParserOutput;
import di.uniba.map.type.Action;
import di.uniba.map.type.Character;
import di.uniba.map.type.Enemy;
import di.uniba.map.type.Item;
import di.uniba.map.type.Room;
import di.uniba.map.type.Weapon;
import di.uniba.map.ui.JKeypad;
import di.uniba.map.ui.UI;
import java.sql.Timestamp;
import java.util.Date;

import java.util.Scanner;

import javax.sound.sampled.*;

/**
 * The main game class for the Phosphorus game.
 * It contains all the game data and methods for managing the game state.
 */
public class PhosphorusGame {

    private List<Action> gameActions;
    private List<Room> gameRooms;

    private GameEngine game;

    private int gameID = 0;
    private Timestamp saveTimestamp;
    private boolean menuLock;
    private List<Integer> completedRoomsIds;
    private boolean musicStatus;
    private int enemyCount = 2;
    private int gameTime = 0;
    public static final String LAB_PASSWORD = "4815";

    /**
     * Initializes the game engine with the given actions and rooms.
     *
     * @param actions The actions to add to the game engine.
     * @param rooms   The rooms to add to the game engine.
     */
    private void initializeGameEngine(List<Action> actions, List<Room> rooms) {

        this.game = new GameEngine();
        this.game.addActions(actions);
        this.game.addRooms(rooms);
        this.getGame().setCurrentRoom(rooms.get(0));

    }

    /**
     * Constructs a new PhosphorusGame.
     * Initializes the game actions and rooms, and sets up the game engine.
     */
    public PhosphorusGame() {
        try {
            this.setGameActions(Utils.initializeActions());
            this.setGameRooms(Utils.initializeRooms());

            completedRoomsIds = new ArrayList<>();

            this.initializeGameEngine(getGameActions(), getGameRooms());

            this.setMenuLock(true);
            this.setMusicStatus(true);

        } catch (Exception e) {
            System.out.println("\nErrore nel caricamento dell'avventura testuale!");
        }
    }

    /**
     * Sets the list of actions for the game.
     *
     * @param actions The list of actions to set.
     */
    public void setGameActions(List<Action> actions) {
        this.gameActions = actions;
    }

    /**
     * Returns the list of actions for the game.
     *
     * @return The list of actions for the game.
     */
    public List<Action> getGameActions() {
        return gameActions;
    }

    /**
     * Sets the list of rooms for the game.
     *
     * @param rooms The list of rooms to set.
     */
    public void setGameRooms(List<Room> rooms) {
        this.gameRooms = rooms;
    }

    /**
     * Returns the list of rooms for the game.
     *
     * @return The list of rooms for the game.
     */
    public List<Room> getGameRooms() {
        return gameRooms;
    }

    /**
     * Returns the game engine.
     *
     * @return The game engine.
     */
    public GameEngine getGame() {
        return this.game;
    }

    /**
     * Sets the game ID.
     *
     * @param gameID The game ID to set.
     */
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    /**
     * Returns the game ID.
     *
     * @return The game ID.
     */
    public int getGameID() {
        return this.gameID;
    }

    /**
     * Sets the save timestamp for the game.
     *
     * @param saveTimestamp The save timestamp to set.
     */
    public void setSaveTimestamp(Timestamp saveTimestamp) {
        this.saveTimestamp = saveTimestamp;
    }

    /**
     * Returns the save timestamp for the game.
     *
     * @return The save timestamp for the game.
     */
    public Timestamp getSaveTimestamp() {
        return this.saveTimestamp;
    }

    /**
     * Sets the menu lock status for the game.
     *
     * @param menuLock The menu lock status to set.
     */
    public void setMenuLock(boolean menuLock) {
        this.menuLock = menuLock;
    }

    /**
     * Returns the menu lock status for the game.
     *
     * @return The menu lock status for the game.
     */
    public boolean getMenuLock() {
        return this.menuLock;
    }

    /**
     * Returns the list of completed room IDs for the game.
     *
     * @return The list of completed room IDs for the game.
     */
    public List<Integer> getCompletedRoomsIds() {
        return this.completedRoomsIds;
    }

    /**
     * Sets the music status for the game.
     *
     * @param musicStatus The music status to set.
     */
    public void setMusicStatus(boolean musicStatus) {
        this.musicStatus = musicStatus;
    }

    /**
     * Returns the music status for the game.
     *
     * @return The music status for the game.
     */
    public boolean getMusicStatus() {
        return this.musicStatus;
    }

    /**
     * Sets the enemy count for the game.
     *
     * @param enemyCount The enemy count to set.
     */
    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    /**
     * Returns the enemy count for the game.
     *
     * @return The enemy count for the game.
     */
    public int getEnemyCount() {
        return enemyCount;
    }

    /**
     * Increases the enemy count by one.
     */
    public void updateEnemyCount() {
        this.enemyCount++;
    }

    /**
     * Decreases the enemy count by one.
     */
    public void reduceEnemyCount() {
        this.enemyCount--;
    }

    /**
     * Sets the game time.
     *
     * @param gameTime The game time to set.
     */
    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    /**
     * Returns the game time.
     *
     * @return The game time.
     */
    public int getGameTime() {
        return gameTime;
    }

    /**
     * Checks if the player has a weapon.
     *
     * @return true if the player has a weapon, false otherwise.
     */
    public boolean checkWeapon() {

        for (Item item : game.getInventory().getItems()) {
            if (item instanceof Weapon) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the weapon is locked.
     *
     * @return true if the weapon is locked, false otherwise.
     */
    public boolean checkWeaponLocked() {

        for (Item item : game.getInventory().getItems()) {
            if (item.getItemName().equals("modifica pistola")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the oxygen is locked.
     *
     * @return true if the oxygen is locked, false otherwise.
     */
    public boolean checkOxyLocked() {
        for (Item item : game.getInventory().getItems()) {
            if (item.getItemName().equals("bombola")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the game has ended.
     *
     * @return true if the game has ended, false otherwise.
     */
    public boolean checkEnd() {
        return this.getEnemyCount() == 0;
    }

    /**
     * Handles the menu action in the game.
     *
     * This method takes a ParserOutput object, a PrintStream object, a Clip object,
     * and a GameTimer object as parameters.
     * It uses a switch statement to handle different action types and perform the
     * corresponding operations.
     *
     * @param p     The ParserOutput object that contains the action to be
     *              performed.
     * @param out   The PrintStream object that is used for output.
     * @param clip  The Clip object that is used for playing music.
     * @param timer The GameTimer object that is used for tracking the game time.
     */
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
                timer.startTimer();
                break;

            case RESUME:
                if (SaveGame.exist() == true) {
                    SaveGame.resume(this);
                    out.println("\nRiprisinato salvataggio: [" + this.getSaveTimestamp() + "]");
                    out.println("\nTi rinfersco un po la memoria, ti trovi in "
                            + this.getGame().getCurrentRoom().getRoomName().toLowerCase() + ", "
                            + this.getGame().getCurrentRoom().getRoomDescription().toLowerCase());
                    timer.setSeconds(this.getGameTime());
                    timer.startTimer();
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

    /**
     * Handles the next move in the game.
     *
     * This method takes a ParserOutput object, a PrintStream object, a Clip object,
     * and a GameTimer object as parameters.
     * It uses a switch statement to handle different action types and perform the
     * corresponding operations.
     *
     * @param p     The ParserOutput object that contains the action to be
     *              performed.
     * @param out   The PrintStream object that is used for output.
     * @param clip  The Clip object that is used for playing music.
     * @param timer The GameTimer object that is used for tracking the game time.
     * @throws InvocationTargetException If an invoked method throws an exception.
     * @throws InterruptedException      If the execution is interrupted.
     */
    public void nextMove(ParserOutput p, PrintStream out, Clip clip, GameTimer timer)
            throws InvocationTargetException, InterruptedException {

        Room currentRoom = this.game.getCurrentRoom();
        
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
                break;

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
                    out.println("\nSALVATAGGIO ESEGUITO CON SUCCESSO!");
                } else {
                    out.println("\nERRORE NEL SALVATAGGIO!");
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
                if (currentRoom.getFloorNumber() == 0) {
                    UI.printGroundFloorMap(out);
                } else {
                    UI.printFirstFloorMap(out);
                }
                break;

            case NORTH:
                if (currentRoom.getNorth() != null) {
                    if (!this.game.getRooms().get(currentRoom.getNorth()).getLocked()) {
                        if (!checkOxyLocked()
                                || this.game.getRooms().get(currentRoom.getNorth()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(currentRoom.getNorth()));
                            out.println("\n" + currentRoom.getRoomDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(currentRoom.getNorth()).getRoomName()
                                    + " non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire, di solito sono riposte nel dormitorio.");
                        }
                    } else if (this.game.getRooms().get(currentRoom.getNorth()).getPasswordRequired()) {
                        out.println("\nInserire password di " + this.game.getRooms()
                                .get(currentRoom.getNorth()).getRoomName().toLowerCase());
                        JKeypad jKeypad = new JKeypad(this.game.getRooms().get(currentRoom.getNorth()));
                        jKeypad.setVisible(true);
                    } else {
                        out.println("\nLa stanza è chiusa a chiave, non puoi entrarci.");
                    }

                } else {
                    out.println("\nNon ci sono stanze a nord, controlla la mappa se ti sei perso.");
                }
                break;

            case SOUTH:
                if (currentRoom.getSouth() != null) {
                    if (!this.game.getRooms().get(currentRoom.getSouth()).getLocked()) {
                        if (!checkOxyLocked()
                                || this.game.getRooms().get(currentRoom.getSouth()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(currentRoom.getSouth()));
                            out.println("\n" + currentRoom.getRoomDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(currentRoom.getSouth()).getRoomName()
                                    + " non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire, di solito sono riposte nel dormitorio.");
                        }
                    } else if (this.game.getRooms().get(currentRoom.getSouth()).getPasswordRequired()) {
                        out.println("\nInserire password di " + this.game.getRooms()
                                .get(currentRoom.getSouth()).getRoomName().toLowerCase());
                        JKeypad jKeypad = new JKeypad(this.game.getRooms().get(currentRoom.getSouth()));
                        jKeypad.setVisible(true);
                    } else {
                        out.println("\nLa stanza è chiusa a chiave, non puoi entrarci.");
                    }

                } else {
                    out.println("\nNon ci sono stanze a sud, controlla la mappa se ti sei perso.");
                }
                break;

            case EAST:
                if (currentRoom.getEast() != null) {
                    if (!this.game.getRooms().get(currentRoom.getEast()).getLocked()) {
                        if (!checkOxyLocked()
                                || this.game.getRooms().get(currentRoom.getEast()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(currentRoom.getEast()));
                            out.println("\n" + currentRoom.getRoomDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(currentRoom.getEast()).getRoomName()
                                    + " non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire, di solito sono riposte nel dormitorio.");
                        }
                    } else if (this.game.getRooms().get(currentRoom.getEast()).getPasswordRequired()) {
                        out.println("\nInserire password di " + this.game.getRooms()
                                .get(currentRoom.getEast()).getRoomName().toLowerCase());
                        JKeypad jKeypad = new JKeypad(this.game.getRooms().get(currentRoom.getEast()));
                        jKeypad.setVisible(true);
                    } else {
                        out.println("\nLa stanza è chiusa a chiave, non puoi entrarci.");
                    }

                } else {
                    out.println("\nNon ci sono stanze a est, controlla la mappa se ti sei perso.");
                }
                break;

            case WEST:
                if (currentRoom.getWest() != null) {
                    if (!this.game.getRooms().get(currentRoom.getWest()).getLocked()) {
                        if (!checkOxyLocked()
                                || this.game.getRooms().get(currentRoom.getWest()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(currentRoom.getWest()));
                            out.println("\n" + currentRoom.getRoomDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(currentRoom.getWest()).getRoomName()
                                            .toLowerCase()
                                    + "non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire, di solito sono riposte nel dormitorio.");
                        }
                    } else if (this.game.getRooms().get(currentRoom.getWest()).getPasswordRequired()) {
                        out.println("\nInserire password di " + this.game.getRooms()
                                .get(currentRoom.getWest()).getRoomName().toLowerCase());
                        JKeypad jKeypad = new JKeypad(this.game.getRooms().get(currentRoom.getWest()));
                        jKeypad.setVisible(true);
                    } else {
                        out.println("\nLa stanza è chiusa a chiave, non puoi entrarci.");
                    }

                } else {
                    out.println("\nNon ci sono stanze a ovest, controlla la mappa se ti sei perso.");
                }
                break;

            case TALK:
                Character currentCharacter = p.getCharacter();
                if (currentRoom.getRoomCharacters().size() != 0) {
                    if (!(currentCharacter == null)) {

                        if (currentCharacter.isAlive()) {
                            out.print("\n[" + currentCharacter.getCharacterName() + "]: \"");
                            if (!currentCharacter.getCompleted()) {

                                out.println(currentCharacter.getMainDialog() + "\"");
                                currentCharacter.setCompleted(true);
                            } else {
                                out.println(currentCharacter.getDefaultDialog() + "\"");
                            }

                        } else {
                            out.println("\n" + currentCharacter.getCharacterName() + " è morto, non puoi parlarci.");
                        }
                    } else {
                        out.println("\nLa persona con la quale vuoi parlare non è presente nella stanza!");
                    }

                }

                break;

            case PICKUP:
                if (currentRoom.getRoomItems().size() != 0) {
                    if (!(p.getItem() == null)) {

                        game.getInventory().addItem(p.getItem());
                        out.println(
                                "\nHai raccolto: " + UI.ANSI_YELLOW + p.getItem().getItemName() + UI.ANSI_RESET + ", "
                                        + p.getItem().getItemDescription());

                        if (p.getItem().getItemName().toLowerCase().equals("chiave sgabuzzino"))
                            this.getGame().getRooms().get(5).setLocked(false);

                        currentRoom.removeItem(p.getItem().getItemName());
                        if (currentRoom.getRoomItems().isEmpty()) {
                            this.completedRoomsIds.add(currentRoom.getRoomID());
                        }

                    } else {
                        out.println(
                                "\nNon ho capito quale oggetto vuoi prendere, ti ricordo di osservare l'ambiente circostante.");
                    }

                } else {
                    out.println("\nNon ci sono oggetti nella stanza.");
                }
                break;

            case INVENTORY:
                if (!game.getInventory().getItems().isEmpty()) {
                    for (Item invItem : game.getInventory().getItems()) {
                        out.println("- " + UI.ANSI_YELLOW + invItem.getItemName() + UI.ANSI_RESET);
                    }
                } else {
                    out.println("\nL'inventario è vuoto.");
                }

                break;

            case WATCH:
                if (!this.getGame().getCurrentRoom().getRoomItems().isEmpty()) {
                    out.println("\n" + currentRoom.getLookDescription());
                } else {
                    out.println("\n" + currentRoom.getRoomDescription());
                }

                break;

            case SHOOT:
                Character characterToShoot = p.getCharacter();
                if (!(characterToShoot == null)) {
                    if (characterToShoot.isAlive()) {
                        if (checkWeapon()) {
                            if (characterToShoot instanceof Enemy) {
                                this.reduceEnemyCount();
                                out.println("\nHai sparato a: " + UI.ANSI_RED + characterToShoot.getCharacterName()
                                        + UI.ANSI_RESET
                                        + ", adesso non è più in vita.");
                                characterToShoot.setAlive(false);
                                if (this.checkEnd()) {
                                    UI.trueEnding(out, timer);
                                    System.exit(0);
                                }
                            } else if (checkWeaponLocked() == false) {
                                out.println("\nHai sparato a: " + UI.ANSI_BLUE + characterToShoot.getCharacterName()
                                        + UI.ANSI_RESET
                                        + ", adesso non è più in vita.");
                                characterToShoot.setAlive(false);
                                if (!(characterToShoot instanceof Enemy)) {
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
                        out.println("\n" + characterToShoot.getCharacterName() + " è già morto.");
                    }

                } else {
                    out.println("\nNon capisco a chi vuoi sparare.");
                }
                break;

            case PROBE:

                AirQuality airQuality = new AirQuality();
                if (airQuality.getRequestStatus()) {
                    UI.printAirQuality(out, airQuality);
                } else {
                    out.println("\nErrore nel reperire i parametri, riprovare più tardi!");
                }

                break;

            default:
                out.println("\nNon puoi effettuare questa azione!");
                break;
        }
    }

    /**
     * Initializes the game.
     *
     * This method takes a PrintStream object, a Clip object, and a GameTimer object
     * as parameters.
     * It prints the game title and main menu, creates a Parser object and a Scanner
     * object, and enters a loop to handle player commands.
     *
     * @param out   The PrintStream object that is used for output.
     * @param clip  The Clip object that is used for playing music.
     * @param timer The GameTimer object that is used for tracking the game time.
     * @throws IOException               If there is an I/O problem.
     * @throws InvocationTargetException If an invoked method throws an exception.
     * @throws InterruptedException      If the execution is interrupted.
     */
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
