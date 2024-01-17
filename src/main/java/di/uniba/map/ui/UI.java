package di.uniba.map.ui;
import java.io.PrintStream;

public class UI {

    public static void printTitle(PrintStream out){
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

    public static void printMainMenu(PrintStream out){
        out.println("\nBenvenut* nell'avventura testuale phosporus, ecco una lista di comandi che possono aiutarti nel corso della tua impresa:\n" + //
                "\n" + //
                "\t- Inizia: da il via ad una nuova partita,\n" + //
                "\t- Riprendi: riprendi l'avventura da dove l'avevi lasciata,\n" + //
                "\t- Musica: per disattivare o attivare la musica del gioco,\n" + //
                "\t- Comandi: per una panoramica generale di tutto quello che puoi fare nel gioco.\n" + //
                "\n" + //
                "Quando sei pronto ad iniziare digita Inizia.");
    }

    public static void printCommandsList(PrintStream out){
        out.println("\nLista di tutti i comandi che puoi eseguire in game:\n" + //
                "\n" + //
                "\t- Esci: per uscire dal gioco e tornare al menù principale,\n" + //
                "\t- Musica: per disattivare o attivare la musica del gioco,\n" + //
                "\t- Mappa: per visualizzare la mappa di gioco,\n" + //
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

    public static void printIntro(PrintStream out){
        out.println("\nTi trovi a bordo di una navicella spaziale, sei di ritorno dopo un lungo viaggio per recuperare il fosforo \n"
                                +
                                "che attualmente sulla terra scarseggia, avete catturato due alieni che producono fosforo naturalmente rilasciandolo come meccanismo di difesa.\n"
                                +
                                "Hai appena aperto gli occhi dopo un lungo sonno, senti il corpo tutto intorpidito, ti alzi dal letto, vedi il tuo collega \u001B[34ml'agente13\u001B[0m già in piedi,\n"
                                +
                                "senti dagli autoparlanti una voce molto fastidiosa: \"A tutti gli agenti, venite immediatamente nella sala meeting!\"");
    }

    public static void printGroundFloorMap(PrintStream out){
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

    public static void printFirstFloorMap(PrintStream out){
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

    public static void badEning(PrintStream out){
        out.println("\nSono scattati gli allarmi, vedi il tuo collega che ti seguiva come un'ombra tirare fuori la pistola, te le punta addoso, vedi bianco...\n");
        printEnd(out);
    }

    public static void printEnd(PrintStream out) {
        out.println("\nGrazie mille per aver giocato a phosphorus!");
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

    public static void trueEnding(PrintStream out){
        out.println("\nAdesso tutti e due gli alieni sono morti, una vocina nella tua testa ti sussurra \"hai completato la missione, contribuendo allo sruttamento di altri esseri viventi per il benessere di altri, complimenti!\"");
        printEnd(out);
    }
}
