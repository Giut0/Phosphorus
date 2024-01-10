package di.uniba.map.parser;

import di.uniba.Utils;
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
            if (obejcts.get(i).getItemName().equals(token) || obejcts.get(i).getItemAlias().contains(token)) {
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

    public ParserOutput parseAction(String action, List<Action> actions, List<Item> objects, List<Character> characters) {
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
                    }

                }
            
            } else {
                return new ParserOutput(actions.get(ic), null, null);
            }
        } else {
            return null;
        }
        return null;

    }

    /*
     * ATTENZIONE: il parser è implementato in modo abbastanza independete dalla
     * lingua, ma riconosce solo
     * frasi semplici del tipo <azione> <oggetto> <oggetto>. Eventuali articoli o
     * preposizioni vengono semplicemente
     * rimossi.
     */
    /*public ParserOutput parse(String Action, List<Action> Actions, List<Item> objects, Inventory inventory) {
        List<String> tokens = Utils.parseString(Action, stopwords); // Elminiazione stopwords
        List<Item> items = inventory.getAdvItemList();
        System.out.println(items);
        if (!tokens.isEmpty()) {
            int ic = checkForAction(tokens.get(0), Actions); // Controllo se il comando dell'utente è presente nella
                                                             // azioni predefinite
            if (ic > -1) {
                System.out.println("C'è");
                if (tokens.size() > 1) {
                    int io = checkForObject(tokens.get(1), objects); // Controllo se l'oggetto è presente ndella lista
                                                                     // di oggetti
                    int ioinv = -1;
                    if (io < 0 && tokens.size() > 2) { // Se le parole sono più di 2 insiste a trovare un ogetto nella
                                                       // frase
                        io = checkForObject(tokens.get(2), objects);
                    }
                    if (io < 0) { // Se le parole sono solo 2 e non ci sono oggetti, cerca nell'inventario
                        ioinv = checkForObject(tokens.get(1), items);
                        if (ioinv < 0 && tokens.size() > 2) { // Insiste la ricerca nell'inventario
                            ioinv = checkForObject(tokens.get(2), items);
                        }
                    }
                    if (io > -1 && ioinv > -1) { // Se ci sono sia oggetto che oggetto inventario
                        return new ParserOutput(Actions.get(ic), objects.get(io), items.get(ioinv)); // <Azione>
                                                                                                     // <oggetto>
                                                                                                     // <oggetto_inventario>
                    } else if (io > -1) {
                        return new ParserOutput(Actions.get(ic), objects.get(io), null); // <Azione> <oggetto>
                    } else if (ioinv > -1) {
                        return new ParserOutput(Actions.get(ic), null, items.get(ioinv)); // <Azione>
                                                                                          // <oggetto_inventario>
                    } else {
                        System.out.println("CAZ");
                        return new ParserOutput(Actions.get(ic), null, null); // <Azione>
                    }
                } else {
                    return new ParserOutput(Actions.get(ic), null);
                }
            } else {
                return new ParserOutput(null, null);
            }
        } else {
            return null;
        }
    } */

}