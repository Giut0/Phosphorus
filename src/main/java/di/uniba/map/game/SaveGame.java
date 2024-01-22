package di.uniba.map.game;

import java.sql.Statement;

import di.uniba.map.type.Character;
import di.uniba.map.type.Item;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import di.uniba.map.type.Room;

import java.util.List;

public class SaveGame {

    private static final String CONNECTION_STRING = "jdbc:h2:./resources/saves/sav";
    private static final String CREATE_GAME_TABLE = "CREATE TABLE IF NOT EXISTS game(gameID INT PRIMARY KEY,currentRoomID INT NOT NULL, enemyCount INT NOT NULL, saveTimestamp TIMESTAMP)";
    private static final String CREATE_INV_TABLE = "CREATE TABLE IF NOT EXISTS inventory(itemID INT NOT NULL,gameID INT,PRIMARY KEY(itemID, gameID),FOREIGN KEY (gameID) REFERENCES game(gameID));";
    private static final String CREATE_KILLED_CHARACTER_TABLE = "CREATE TABLE IF NOT EXISTS killedCharacter(characterID INT NOT NULL,gameID INT,PRIMARY KEY(characterID, gameID),FOREIGN KEY (gameID) REFERENCES game(gameID));";

    private static final String INSERT_GAME = "INSERT INTO game (gameID, currentRoomID, enemyCount, saveTimestamp) VALUES (?, ?, ?, ?)";
    private static final String INSERT_INV = "INSERT INTO inventory (itemID, gameID) VALUES (?, ?)";
    private static final String INSERT_KILLED_CHARACTER = "INSERT INTO killedCharacter (characterID, gameID) VALUES (?, ?)";

    private static final String CLEAR_GAME = "DELETE FROM game;";
    private static final String CLEAR_INV = "DELETE FROM inventory;";
    private static final String CLEAR_KILLED_CHARACTER = "DELETE FROM killedCharacter;";

    private static final String SELECT_INVENTORY = "SELECT inventory.itemID FROM inventory WHERE inventory.gameID=?";
    private static final String SELECT_KILLED_CHARACTERS = "SELECT killedCharacter.characterID FROM killedCharacter WHERE killedCharacter.gameID=?";

    public static boolean save(PhosphorusGame game) {
        boolean saveResult = false;
        try {

            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            // Crea un PreparedStatement per l'inserimento dei dati
            PreparedStatement pstmt = conn.prepareStatement(INSERT_GAME);

            // Imposta i valori
            pstmt.setInt(1, game.getGameID()); // gameID
            pstmt.setInt(2, game.getGame().getCurrentRoom().getRoomID()); // currentRoomID
            pstmt.setInt(3, game.getEnemyCount()); // currentRoomID
            pstmt.setTimestamp(4, game.getSaveTimestamp()); // saveTimestamp
            // Esegui l'aggiornamento
            pstmt.executeUpdate();
            pstmt.close();

            for (Item item : game.getGame().getInventory().getAdvItemList()) {
                pstmt = conn.prepareStatement(INSERT_INV);
                pstmt.setInt(1, item.getItemID()); // gameID
                pstmt.setInt(2, game.getGameID()); // currentRoomID
                // Esegui l'aggiornamento
                pstmt.executeUpdate();
                pstmt.close();
            }

            for (Room room : game.getGame().getRoomsAsList()) {
                for (Character character : room.getCharacters()) {
                    if (!character.isAlive()) {
                        pstmt = conn.prepareStatement(INSERT_KILLED_CHARACTER);
                        pstmt.setInt(1, character.getCharacterId()); // gameID
                        pstmt.setInt(2, game.getGameID()); // currentRoomID
                        // Esegui l'aggiornamento
                        pstmt.executeUpdate();
                        pstmt.close();
                    }
                }
            }

            conn.close();
            saveResult = true;

        } catch (Exception e) {
            System.out.println("\nErrore nella connessione al database!");
        }

        return saveResult;
    }

    public static PhosphorusGame resume(PhosphorusGame game) {

        List<Room> roomsList = game.getGame().getRoomsAsList();

        try {

            Connection conn = DriverManager.getConnection(CONNECTION_STRING);

            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM game");
            while (rs.next()) {
                if (rs.getInt(1) == game.getGameID()) {
                    game.setGameID(game.getGameID());
                    game.getGame().setCurrentRoom(roomsList.get(rs.getInt(2)));
                    game.setEnemyCount(rs.getInt(3));
                    game.setSaveTimestamp(rs.getTimestamp(4));
                }
            }
            rs.close();
            stm.close();
            PreparedStatement pstmt = conn.prepareStatement(SELECT_INVENTORY);
            pstmt.setInt(1, game.getGameID()); // gameID

            rs = pstmt.executeQuery();

            List<Item> items = game.initializeItems();

            while (rs.next()) { // Per aggiungere all'inventario gli oggetti della sessione precedente

                for (Item item : items) {

                    if (rs.getInt(1) == item.getItemID()) {
                        game.getGame().addItem(item);
                    }
                }
            }

            for (Item item : game.getGame().getInventory().getAdvItemList()) { // Per eliminare gli oggetti
                                                                               // dell'inventario dalla mappa

                for (Room room : roomsList) {
                    if (room.conteinItem(item)) {
                        room.removeItem(item.getItemName());
                    }
                }

            }

            pstmt.close();

            pstmt = conn.prepareStatement(SELECT_KILLED_CHARACTERS);
            pstmt.setInt(1, game.getGameID()); // gameID

            rs = pstmt.executeQuery();

            while (rs.next()) {
                for (Room room : game.getGame().getRoomsAsList()) {
                    for (Character character : room.getCharacters()) {
                        if (character.getCharacterId() == rs.getInt(1)) {
                            character.setAlive(false);
                        }
                    }
                }

            }

            pstmt.close();
            rs.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("\nErrore nell'ottenere la sessione precedente!");
        }

        return game;
    }

    public static void clearDB() {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);

            Statement stm = conn.createStatement();

            stm.executeUpdate(CLEAR_INV);

            stm.close();

            stm = conn.createStatement();

            stm.executeUpdate(CLEAR_KILLED_CHARACTER);

            stm.close();

            stm = conn.createStatement();

            stm.executeUpdate(CLEAR_GAME);

            stm.close();

            conn.close();

        } catch (SQLException e) {
            System.out.println("\nErrore nell'inizializzare il database!");
        }

    }

    public static boolean createDB() {
        boolean result = false;
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);

            Statement stm = conn.createStatement();

            stm.executeUpdate(CREATE_GAME_TABLE);

            stm.close();

            stm = conn.createStatement();

            stm.executeUpdate(CREATE_INV_TABLE);

            stm.close();

            stm = conn.createStatement();

            stm.executeUpdate(CREATE_KILLED_CHARACTER_TABLE);

            stm.close();

            conn.close();
            result = true;

        } catch (SQLException e) {
            result = false;
            System.out.println("\nErrore nella creazione del database!");
        }

        return result;
    }

    public static boolean exist() {

        boolean databaseExists = false;

        File db = new File("./resources/saves/sav.mv.db");

        if (db.exists()) {
            databaseExists = true;
        }

        return databaseExists;

    }

}
