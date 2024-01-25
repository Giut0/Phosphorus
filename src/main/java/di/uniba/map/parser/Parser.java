package di.uniba.map.parser;

import di.uniba.map.Utils;
import di.uniba.map.type.Action;
import di.uniba.map.type.Character;
import di.uniba.map.type.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The Parser class provides methods to parse user input into game actions.
 */
public class Parser {

    private final Set<String> stopwords;

    /**
     * Constructor for the Parser class.
     * @param stopwords The set of stopwords to ignore when parsing.
     */
    public Parser(Set<String> stopwords) {
        this.stopwords = stopwords;
    }

    /**
     * Checks if a token matches an action.
     * @param token The token to check.
     * @param actions The list of actions to check against.
     * @return The index of the matching action in the list, or -1 if no match is found.
     */
    private int checkForAction(String token, List<Action> actions) {
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i).getActionName().equals(token) || actions.get(i).getActionAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if a token matches an item.
     * @param token The token to check.
     * @param objects The list of items to check against.
     * @return The index of the matching item in the list, or -1 if no match is found.
     */
    private int checkForItem(String token, List<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemName().toLowerCase().equals(token)
                    || items.get(i).getItemAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if a token matches a character.
     * @param token The token to check.
     * @param characters The list of characters to check against.
     * @return The index of the matching character in the list, or -1 if no match is found.
     */
    private int checkForCharacter(String token, List<Character> characters) {
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getCharacterName().toLowerCase().trim().equals(token)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Parses a user action into a ParserOutput object.
     * @param action The user action to parse.
     * @param actions The list of possible actions.
     * @param objects The list of possible items.
     * @param characters The list of possible characters.
     * @return A ParserOutput object representing the parsed action.
     */
    public ParserOutput parseAction(String action, List<Action> actions, List<Item> objects,
            List<Character> characters) {
        List<String> tokens = new ArrayList<>();
        if (action.length() > 1) {
            tokens = Utils.parseString(action, stopwords); // Stopwords elimination
        } else {
            tokens.add(action);
        }

        if (!tokens.isEmpty()) {
            int ic = checkForAction(tokens.get(0), actions);
            if (ic > -1 && tokens.size() > 1) {
                int ob = checkForItem(tokens.get(1), objects);

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
