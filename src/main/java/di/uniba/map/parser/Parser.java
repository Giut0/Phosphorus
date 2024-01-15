package di.uniba.map.parser;

import di.uniba.map.Utils;
import di.uniba.map.type.Action;
import di.uniba.map.type.Character;
import di.uniba.map.type.Item;

import java.util.List;
import java.util.Set;

public class Parser {

    private final Set<String> stopwords;

    public Parser(Set<String> stopwords) {
        this.stopwords = stopwords;
    }

    private int checkForAction(String token, List<Action> Actions) {
        for (int i = 0; i < Actions.size(); i++) {
            if (Actions.get(i).getCommandName().equals(token) || Actions.get(i).getCommandAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    private int checkForObject(String token, List<Item> obejcts) {
        for (int i = 0; i < obejcts.size(); i++) {
            if (obejcts.get(i).getItemName().toLowerCase().equals(token) || obejcts.get(i).getItemAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    private int checkForCharacter(String token, List<Character> characters) {
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getCharacterName().toLowerCase().trim().equals(token)) {
                return i;
            }
        }
        return -1;
    }

    public ParserOutput parseAction(String action, List<Action> actions, List<Item> objects,
            List<Character> characters) {
        List<String> tokens = Utils.parseString(action, stopwords); // Elminiazione stopwords

        if (!tokens.isEmpty()) {
            int ic = checkForAction(tokens.get(0), actions); // Controllo se il comando dell'utente è presente nella
                                                             // azioni predefinite
            if (ic > -1 && tokens.size() > 1) {
                int ob = checkForObject(tokens.get(1), objects);

                if (ob > -1) {
                    return new ParserOutput(actions.get(ic), objects.get(ob)); // <Action> <Object>
                } else {

                    int ch = checkForCharacter(tokens.get(1).toLowerCase().trim(), characters);

                    if (ch > -1) {
                        return new ParserOutput(actions.get(ic), characters.get(ch)); // <Action> <Character>
                    } else {
                        return new ParserOutput(actions.get(ic), null, null);
                    }

                }

            } else if (ic > -1) {
                return new ParserOutput(actions.get(ic)); // <Action>
            } else {
                return new ParserOutput(null);
            }
        } else {
            return null;
        }
    }

}
