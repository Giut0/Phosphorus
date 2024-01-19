package di.uniba.map.game;

import java.sql.Statement;

import di.uniba.map.type.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveGame {

    public static final String CONNECTION_STRING = "jdbc:h2:./resources/saves/saves";
    public static final String CREATE_GAME_TABLE = "CREATE TABLE IF NOT EXISTS game(gameID INT PRIMARY KEY,currentRoomID INT NOT NULL, saveTimestamp TIMESTAMP)";
    public static final String CREATE_INV_TABLE = "CREATE TABLE IF NOT EXISTS inventory(itemID INT NOT NULL,gameID INT,PRIMARY KEY(itemID),FOREIGN KEY (gameID) REFERENCES game(gameID));";
    public static final String CREATE_COMPLROOM_TABLE = "CREATE TABLE IF NOT EXISTS completedRooms(roomID INT,gameID INT,PRIMARY KEY(roomID),FOREIGN KEY (gameID) REFERENCES game(gameID));";

    private final static String INSERT_GAME = "INSERT INTO game (gameID, currentRoomID, saveTimestamp) VALUES (?, ?, ?)";
    private final static String INSERT_INV = "INSERT INTO inventory (itemID, gameID) VALUES (?, ?)";
    private final static String INSERT_COMPLROOM = "INSERT INTO completedRooms (roomID, gameID) VALUES (?, ?)";

    private final static String CLEAR_GAME = "DELETE FROM game;";
    private final static String CLEAR_INV = "DELETE FROM inventory;";
    private final static String CLEAR_COMPLROOM= "DELETE FROM completedRooms;";

    public static boolean save(PhosphorusGame game) {

        boolean result = createDB(CONNECTION_STRING);

        try {

            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            // Crea un PreparedStatement per l'inserimento dei dati
            PreparedStatement pstmt = conn.prepareStatement(INSERT_GAME);

            // Imposta i valori
            pstmt.setInt(1, game.getGameID()); // gameID
            pstmt.setInt(2, game.getGame().getCurrentRoom().getRoomID()); // currentRoomID
            pstmt.setTimestamp(3, game.getSaveTimestamp()); // saveTimestamp
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

            for (Integer roomID : game.getCompletedRoomsIds()) {
                pstmt = conn.prepareStatement(INSERT_COMPLROOM);
                pstmt.setInt(1, roomID); // gameID
                pstmt.setInt(2, game.getGameID()); // currentRoomID
                // Esegui l'aggiornamento
                pstmt.executeUpdate();
                pstmt.close();
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace(); // TODO
        }

        return result;
    }

    public PhosphorusGame resume() {

        boolean result = true;

        PhosphorusGame game = new PhosphorusGame();

        boolean createDbResult = this.createDB(CONNECTION_STRING);

        return game;
    }

    public static void clearDB(){
        boolean result = createDB(CONNECTION_STRING);
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);

            Statement stm = conn.createStatement();

            stm.executeUpdate(CLEAR_COMPLROOM);

            stm.close();

            stm = conn.createStatement();

            stm.executeUpdate(CLEAR_INV);

            stm.close();

            stm = conn.createStatement();

            stm.executeUpdate(CLEAR_GAME);

            stm.close();

            conn.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static boolean createDB(String connectionString) {
        boolean result = false;
        try {
            Connection conn = DriverManager.getConnection(connectionString);

            Statement stm = conn.createStatement();

            stm.executeUpdate(CREATE_GAME_TABLE);

            stm.close();

            stm = conn.createStatement();

            stm.executeUpdate(CREATE_INV_TABLE);

            stm.close();

            stm = conn.createStatement();

            stm.executeUpdate(CREATE_COMPLROOM_TABLE);

            stm.close();

            conn.close();
            result = true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            result = false;
            e.printStackTrace();
        }

        return result;
    }

}
