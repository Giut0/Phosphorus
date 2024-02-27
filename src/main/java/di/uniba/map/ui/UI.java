package di.uniba.map.ui;

import java.io.PrintStream;

import di.uniba.map.game.AirQuality;
import di.uniba.map.game.GameTimer;

/**
 *  * The UI class handles the user interface of the game.
 *  
 */
public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    /**
     *      * Prints the title of the game.
     *      * @param out The PrintStream to print the title to.
     *      
     */
    public static void printTitle(PrintStream out) {
        out.println("\n╔═════════════════════════════════════════════════════════════════╗  \n" + //
                "║      ____  __                     __                            ║\n" + //
                "║     / __ \\/ /_  ____  _________  / /_  ____  _______  _______   ║\n" + //
                "║    / /_/ / __ \\/ __ \\/ ___/ __ \\/ __ \\/ __ \\/ ___/ / / / ___/   ║   \n" + //
                "║   / ____/ / / / /_/ (__  ) /_/ / / / / /_/ / /  / /_/ (__  )    ║\n" + //
                "║  /_/   /_/ /_/\\____/____/ .___/_/ /_/\\____/_/   \\__,_/____/     ║\n" + //
                "║                        /_/                                      ║\n" + //
                "║                                         by Vito Proscia         ║\n" + //
                "║                                                                 ║\n" + //
                "╚═════════════════════════════════════════════════════════════════╝");
    }

    /**
     *      * Prints the main menu of the game.
     *      * @param out The PrintStream to print the main menu to.
     *      
     */
    public static void printMainMenu(PrintStream out) {
        out.println(
                "\nBenvenut* nell'avventura testuale Phosphorus, ecco una lista di comandi che possono aiutarti nel corso della tua impresa:\n"
                        + //
                        "\n" + //
                        "\t- Inizia: da il via ad una nuova partita,\n" + //
                        "\t- Riprendi: riprendi l'avventura da dove l'avevi lasciata,\n" + //
                        "\t- Musica: per disattivare o attivare la musica del gioco,\n" + //
                        "\t- Comandi: per una panoramica generale di tutto quello che puoi fare nel gioco.\n" + //
                        "\t- Esci: per uscire dal gioco e riprendere a viverne un altro.\n" + //
                        "\n" + //
                        "Quando sei pronto ad iniziare digita Inizia.");
    }

    /**
     *      * Prints the list of commands that can be executed in the game.
     *      * @param out The PrintStream to print the commands list to.
     *      
     */
    public static void printCommandsList(PrintStream out) {
        out.println("\nLista di tutti i comandi che puoi eseguire in game:\n" + //
                "\n" + //
                "\t- Esci: per uscire dal gioco e tornare al menù principale,\n" + //
                "\t- Menu: per tornare al menù principale,\n" + //
                "\t- Musica: per disattivare o attivare la musica del gioco,\n" + //
                "\t- Mappa: per visualizzare la mappa di gioco,\n" + //
                "\t- Sonda: per visualizzare i parametri della qualità dell'aria del tuo paese in tempo reale,\n" +
                "\t- Salva: per salvare i progressi di gioco,\n" + //
                "\t- Nord: per muoversi a nord,\n" + //
                "\t- Sud: per muoversi a sud,\n" + //
                "\t- Est: per muoversi a est,\n" + //
                "\t- Ovest: per muoversi a ovest,\n" + //
                "\t- Parla: per parlare ad un NPC,\n" + //
                "\t- Raccogli: per prendere un oggetto,\n" + //
                "\t- Inventario: per visualizzare tutti gli oggetti che hai raccolto,\n" + //
                "\t- Osserva: per ispezionare l'ambente circostante,\n" + //
                "\t- Spara: per sparare ad un avversario.");
    }

    /**
     *      * Prints the introduction of the game.
     *      * @param out The PrintStream to print the introduction to.
     *      
     */
    public static void printIntro(PrintStream out) {
        out.println(
                "\nTi trovi a bordo di una navicella spaziale, sei di ritorno dopo un lungo viaggio per recuperare il fosforo \n"
                        +
                        "che attualmente sulla terra scarseggia, avete catturato due alieni che producono fosforo naturalmente rilasciandolo come meccanismo di difesa.\n"
                        +
                        "\nHai appena aperto gli occhi dopo un lungo sonno, senti il corpo tutto intorpidito, ti alzi dal letto, vedi il tuo collega \u001B[34ma13\u001B[0m già in piedi,\n"
                        +
                        "senti dagli autoparlanti una voce molto fastidiosa: \"A tutti gli agenti, venite immediatamente nella sala meeting!\"");
    }

    /**
     * Prints the map of the ground floor.
     * 
     * @param out The PrintStream to print the map to.
     */
    public static void printGroundFloorMap(PrintStream out) {
        out.println("\n╔═════════════════════════════════════════════════════════════════╗");
        out.println("║                        Mappa piano terra                        ║");
        out.println("╠═════════════════════════════════════════════════════════════════╣");
        out.println("║                                                                 ║\n" + //
                "║    ╔═══════════════════════════════════╗                        ║\n" + //
                "║    ║                                   ║                        ║\n" + //
                "║    ║  ╞═╡                              ╠════════════════╗       ║\n" + //
                "║    ║  ╞═╡                              ║                ║       ║\n" + //
                "║    ║  ╞═╡                              ╨                ║       ║\n" + //
                "║    ║                                   ╥                ║       ║\n" + //
                "║    ║                                   ║  Laboratorio   ║       ║\n" + //
                "║    ║  Sala meeting                     ╠════════════════╝       ║\n" + //
                "║    ╚════════════════╦═════════╡ ╞══════╣                        ║\n" + //
                "║                     ║                  ║                        ║\n" +
                "║                     ║                  ║                        ║\n" + //
                "║                     ║ Dormitorio       ║                        ║\n" + //
                "║                     ╚══════════════════╝                        ║\n" + //
                "║                                                                 ║");
        out.println("╚═════════════════════════════════════════════════════════════════╝");
    }

    /**
     * Prints the map of the first floor.
     * 
     * @param out The PrintStream to print the map to.
     */
    public static void printFirstFloorMap(PrintStream out) {
        out.println("\n╔═════════════════════════════════════════════════════════════════╗");
        out.println("║                        Mappa primo piano                        ║");
        out.println("╠═════════════════════════════════════════════════════════════════╣");
        out.println("║                                                                 ║\n" + //
                "║    ╔══════════════════╦════════════════════════════════╗        ║\n" + //
                "║    ║                  ║                                ║        ║\n" + //
                "║    ║  ╞═╡             ║                                ║        ║\n" + //
                "║    ║  ╞═╡             ╨                                ║        ║\n" + //
                "║    ║  ╞═╡             ╥                                ║        ║\n" + //
                "║    ║                  ║                  Sala mototi   ║        ║\n" + //
                "║    ║                  ║                 ╔══════════════╝        ║\n" + //
                "║    ║  Mensa           ║                 ║                       ║\n" +
                "║    ╚══════════════════╬════════╡ ╞══════╣                       ║\n" + //
                "║                       ║                 ║                       ║\n" + //
                "║                       ║                 ║                       ║\n" + //
                "║                       ║  Sgabuzzino     ║                       ║\n" + //
                "║                       ╚═════════════════╝                       ║\n" +
                "║                                                                 ║");
        out.println("╚═════════════════════════════════════════════════════════════════╝");
    }

    /**
     * Prints the air quality.
     * 
     * @param out        The PrintStream to print the air quality to.
     * @param airQuality The air quality to print.
     */
    public static void printAirQuality(PrintStream out, AirQuality airQuality) {

        out.printf("\n╔═════════════════════╦══════════════╦═════════╗ \n" +
                "║ Indicatori ambient. ║    Valori    ║  Esito  ║ \n" +
                "╠═════════════════════╬══════════════╬═════════╣ \n" +
                "║                     ║              ║         ║ \n" +
                "║  EU_AQI	      ║     %d       ║  %s   ║ \n" +
                "║  Ozone O₃           ║  %.2f μg/m³ ║  %s   ║ \n" +
                "║  Sulphur Dio. SO₂   ║   %.2f μg/m³ ║  %s   ║ \n" +
                "║  UV_index:          ║   %.2f       ║  %s   ║  \n" +
                "║  Nitrogen Dio. NO₂  ║  %.2f μg/m³  ║  %s   ║ \n" +
                "║                     ║              ║	       ║ \n" +
                "╚═════════════════════╩══════════════╩═════════╝\n", airQuality.getEuropeanAqi(),
                airQuality.checkEuropeanAqi(), airQuality.getOzone(), airQuality.checkOzone(),
                airQuality.getSulphurDioxide(), airQuality.checkSulphurDioxide(), airQuality.getUvIndex(),
                airQuality.checkUVIndex(), airQuality.getNitrogenDioxide(), airQuality.checkNitrogenDioxide());

    }

    /**
     * Prints the bad ending of the game.
     * 
     * @param out   The PrintStream to print the bad ending to.
     * @param timer The GameTimer to print the game time.
     */
    public static void badEnding(PrintStream out, GameTimer timer) {
        out.println(
                "\nSono scattati gli allarmi, vedi il tuo collega che ti seguiva come un'ombra tirare fuori la pistola, te le punta addoso, vedi bianco...");
        printEnd(out, timer);
    }

    /**
     * Prints the end of the game.
     * 
     * @param out   The PrintStream to print the end to.
     * @param timer The GameTimer to print the game time.
     */
    public static void printEnd(PrintStream out, GameTimer timer) {
        out.println("\nGrazie mille per aver giocato a phosphorus!");
        out.println("\nTempo di gioco: " + timer.getTime() + " secondi!");
        out.println("\nArt director: Vito Proscia \n" + //
                "Game designer: Vito Proscia \n" + //
                "Graphic designer: Vito Proscia \n" + //
                "Sound designer: Vito Proscia\n" + //
                "Project Manager: Vito Proscia\n" + //
                "Writer: Vito Proscia \n" + //
                "Developer: Vito Proscia\n" + //
                "Producer: Vito Proscia\n" + //
                "Concept Artist: Vito Proscia \n" + //
                "Game tester: Vito Proscia\n");
    }

    /**
     * Prints the true ending of the game.
     * 
     * @param out   The PrintStream to print the true ending to.
     * @param timer The GameTimer to print the game time.
     */
    public static void trueEnding(PrintStream out, GameTimer timer) {
        out.println(
                "\nAdesso tutti e due gli alieni sono morti, una vocina nella tua testa ti sussurra \"hai completato la missione, contribuendo allo sruttamento di altri esseri viventi per il benessere di altri, complimenti!\"");
        printEnd(out, timer);
    }
}
