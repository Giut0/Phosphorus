package di.uniba.map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import di.uniba.map.type.Room;
import di.uniba.map.type.Character;

public class App {
    public static void main(String[] args) throws StreamReadException, DatabindException, IOException {

        /*File JSON_SOURCE = new File("resources/rooms.json");
        File JSON_SOURCE2 = new File("resources/characters.json");

        Map<String, Object> result = new ObjectMapper().readValue(JSON_SOURCE, HashMap.class);
        Map<String, Object> result2 = new ObjectMapper().readValue(JSON_SOURCE2, HashMap.class); */

        File JSON_SOURCE_ROOMS = new File("resources/rooms.json");
        Map<String, Object> fileRooms = new ObjectMapper().readValue(JSON_SOURCE_ROOMS, HashMap.class);
        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            Map<?, ?> result = (Map<?, ?>) fileRooms.get(Integer.toString(i));
            Room room = new Room((int) result.get("roomId"), (String) result.get("roomName"),
                    (String) result.get("roomDescription"), (String) result.get("lookDescription"), (int) result.get("floorNumber"), (boolean) result.get("visible"),
                    (boolean) result.get("oxygen"));
            room.setAdjacentRooms((Integer) result.get("north"), (Integer) result.get("south"), (Integer) result.get("est"), (Integer) result.get("west"));
            rooms.add(room);

        }

        for(Room elem : rooms){
            System.out.println("****************************************************");
            System.out.println("ROOM NAME : " + elem.getName());
            System.out.println("NORD : " + elem.getNorth());
            System.out.println("SUD : " + elem.getSouth());
            System.out.println("EST : " + elem.getEast());
            System.out.println("OVEST : " + elem.getWest());
            System.out.println("****************************************************");
        }

        
        /* 

        Map<?, ?> a2 = (Map<?, ?>) result2.get("0");
        Map<?, ?> z = (Map<?, ?>) result2.get("1");
        Map<?, ?> x = (Map<?, ?>) result2.get("2");
        Map<?, ?> c = (Map<?, ?>) result2.get("3");

        Room room = new Room((int) a.get("roomId"), (String) a.get("roomName"), (String) a.get("roomDescription"),
                (boolean) a.get("visible"), (boolean) a.get("oxygen"), 0);

        Character character = new Character((int) a2.get("characterId"), (String) a2.get("characterName"),
                (String) a2.get("characterDescription"));
        Character character2 = new Character((int) z.get("characterId"), (String) z.get("characterName"),
                (String) z.get("characterDescription"));
        Character character3 = new Character((int) x.get("characterId"), (String) x.get("characterName"),
                (String) x.get("characterDescription"));
        Character character4 = new Character((int) c.get("characterId"), (String) c.get("characterName"),
                (String) c.get("characterDescription"));

        List<Character> cha = new ArrayList<Character>();

        cha.add(character);
        cha.add(character2);
        cha.add(character3);
        cha.add(character4);

        room.setLook((String) a.get("lookDescription"));
        ArrayList<?> ch = (ArrayList<?>) a.get("characters");

        for (Character ci : cha) {
            for (int i = 0; i < ch.size(); i++) {
                if ((int) ch.get(i) == ci.getCharacterId()) {
                    room.addCharacter(ci);
                }
            }

        }

        for (int i = 0; i < room.getCharacters().size(); i++) {
            System.out.println(room.getCharacters().get(i).getCharacterName());
        }

        Map<?, ?> b = (Map<?, ?>) result.get(a.get("north").toString());
        */

    }

}
