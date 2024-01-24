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

public class PhosphorusGame {

    List<Action> gameActions;
    List<Room> gameRooms;

    private GameEngine game;

    private int gameID = 0;
    Timestamp saveTimestamp;
    private boolean menuLock;
    private List<Integer> completedRoomsIds;
    private boolean musicStatus;
    private int enemyCount = 2;
    private int gameTime = 0;
    public static final String LAB_PASSWORD = "4815";

    private void initializeGameEngine(List<Action> actions, List<Room> rooms) {

        this.game = new GameEngine();
        this.game.addActions(actions);
        this.game.addRooms(rooms);
        this.getGame().setCurrentRoom(rooms.get(0));

    }

    public PhosphorusGame() {
        try {
            this.setGameActions(Utils.initializeActions());
            this.setGameRooms(Utils.initializeRooms());

            completedRoomsIds = new ArrayList<>();

            this.initializeGameEngine(getGameActions(), getGameRooms());

            this.setMenuLock(true);
            this.setMusicStatus(true);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setGameActions(List<Action> actions) {
        this.gameActions = actions;
    }

    public List<Action> getGameActions() {
        return gameActions;
    }

    public void setGameRooms(List<Room> rooms) {
        this.gameRooms = rooms;
    }

    public List<Room> getGameRooms() {
        return gameRooms;
    }

    public GameEngine getGame() {
        return this.game;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getGameID() {
        return this.gameID;
    }

    public void setSaveTimestamp(Timestamp saveTimestamp) {
        this.saveTimestamp = saveTimestamp;
    }

    public Timestamp getSaveTimestamp() {
        return this.saveTimestamp;
    }

    public void setMenuLock(boolean menuLock) {
        this.menuLock = menuLock;
    }

    public boolean getMenuLock() {
        return this.menuLock;
    }

    public List<Integer> getCompletedRoomsIds() {
        return this.completedRoomsIds;
    }

    public void setMusicStatus(boolean musicStatus) {
        this.musicStatus = musicStatus;
    }

    public boolean getMusicStatus() {
        return this.musicStatus;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void updateEnemyCount() {
        this.enemyCount++;
    }

    public void reduceEnemyCount() {
        this.enemyCount--;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getGameTime() {
        return gameTime;
    }

    public boolean checkWeapon() {

        for (Item item : game.getInventory().getItems()) {
            if (item instanceof Weapon) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWeaponLocked() {

        for (Item item : game.getInventory().getItems()) {
            if (item.getItemName().equals("modifica pistola")) {
                return false;
            }
        }
        return true;
    }

    public boolean checkOxyLocked() {
        for (Item item : game.getInventory().getItems()) {
            if (item.getItemName().equals("bombola")) {
                return false;
            }
        }
        return true;
    }

    public boolean checkEnd() {
        return this.getEnemyCount() == 0;
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
                if (game.getCurrentRoom().getFloorNumber() == 0) {
                    UI.printGroundFloorMap(out);
                } else {
                    UI.printFirstFloorMap(out);
                }
                break;

            case NORTH:
                if (game.getCurrentRoom().getNorth() != null) {
                    if (!this.game.getRooms().get(this.game.getCurrentRoom().getNorth()).getLocked()) {
                        if (!checkOxyLocked()
                                || this.game.getRooms().get(this.game.getCurrentRoom().getNorth()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getNorth()));
                            out.println("\n" + this.game.getCurrentRoom().getRoomDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(this.game.getCurrentRoom().getNorth()).getRoomName()
                                    + " non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire, di solito sono riposte nel dormitorio.");
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
                    out.println("\nNon ci sono stanze a nord, controlla la mappa se ti sei perso.");
                }
                break;

            case SOUTH:
                if (game.getCurrentRoom().getSouth() != null) {
                    if (!this.game.getRooms().get(this.game.getCurrentRoom().getSouth()).getLocked()) {
                        if (!checkOxyLocked()
                                || this.game.getRooms().get(this.game.getCurrentRoom().getSouth()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getSouth()));
                            out.println("\n" + this.game.getCurrentRoom().getRoomDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(this.game.getCurrentRoom().getSouth()).getRoomName()
                                    + " non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire, di solito sono riposte nel dormitorio.");
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
                    out.println("\nNon ci sono stanze a sud, controlla la mappa se ti sei perso.");
                }
                break;

            case EAST:
                if (game.getCurrentRoom().getEast() != null) {
                    if (!this.game.getRooms().get(this.game.getCurrentRoom().getEast()).getLocked()) {
                        if (!checkOxyLocked()
                                || this.game.getRooms().get(this.game.getCurrentRoom().getEast()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getEast()));
                            out.println("\n" + this.game.getCurrentRoom().getRoomDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(this.game.getCurrentRoom().getEast()).getRoomName()
                                    + " non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire, di solito sono riposte nel dormitorio.");
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
                    out.println("\nNon ci sono stanze a est, controlla la mappa se ti sei perso.");
                }
                break;

            case WEST:
                if (game.getCurrentRoom().getWest() != null) {
                    if (!this.game.getRooms().get(this.game.getCurrentRoom().getWest()).getLocked()) {
                        if (!checkOxyLocked()
                                || this.game.getRooms().get(this.game.getCurrentRoom().getWest()).isOxygen()) {
                            this.game.setCurrentRoom(this.game.getRooms().get(this.game.getCurrentRoom().getWest()));
                            out.println("\n" + this.game.getCurrentRoom().getRoomDescription());
                        } else {
                            out.println("\nIn "
                                    + this.game.getRooms().get(this.game.getCurrentRoom().getWest()).getRoomName()
                                            .toLowerCase()
                                    + "non c'è ossigeno, devi avere una bombola d'ossigeno con te per proseguire, di solito sono riposte nel dormitorio.");
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
                    out.println("\nNon ci sono stanze a ovest, controlla la mappa se ti sei perso.");
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
                    out.println("\n" + game.getCurrentRoom().getLookDescription());
                } else {
                    out.println("\n" + game.getCurrentRoom().getRoomDescription());
                }

                break;

            case SHOOT:
                if (!(p.getCharacter() == null)) {
                    if (p.getCharacter().isAlive()) {
                        if (checkWeapon()) {
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
                            } else if (checkWeaponLocked() == false) {
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
                        out.println("\n" + p.getCharacter().getCharacterName() + " è già morto.");
                    }

                } else {
                    out.println("\nNon capisco a chi vuoi sparare.");
                }
                break;

            case PROBE:

                AirQuality airQuality = new AirQuality();
                if(airQuality.getRequestStatus()){
                    UI.printAirQuality(out, airQuality);
                }else{
                    out.println("\nErrore nel reperire i parametri, riprovare più tardi!");
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
