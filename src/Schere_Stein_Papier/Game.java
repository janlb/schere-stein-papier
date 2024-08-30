package Schere_Stein_Papier;

/**
 * Die Klasse Game repraesentiert das Spiel "Schere, Stein, Papier" mit zusaetzlichen Modi und mehreren Runden.
 * Sie enthaelt die Logik fuer die Spielablaeufe, die Bestimmung des Gewinners und die Verwaltung der Spielstaende.
 */
public class Game {
    /**
     * Ein Variable, die anzeigt, ob das Spiel zum ersten Mal gestartet wird.
     * Sie wird verwendet, um eine Willkommensnachricht nur einmal beim Start anzuzeigen und nicht bei jedem neuen Spiel.
     */
    private boolean isFirstGame = true;

    /**
     * Zaehlt die Anzahl der Runden, die der Spieler gewonnen hat.
     */
    private int playerWins = 0;

    /**
     * Zaehlt die Anzahl der Runden, die der Computer gewonnen hat.
     */
    private int computerWins = 0;

    /**
     * Repraesentiert den Spieler, der gegen den Computer spielt.
     * Dieses Objekt verwaltet die Eingaben des Spielers.
     */
    private final Player player;

    /**
     * Repraesentiert den Computer, der automatisch/zufaellig Zuege waehlt.
     * Dieses Objekt verwaltet die Logik der Computerwahl.
     */
    private final Computer computer;

    /**
     * Konstruktor der Klasse Game.
     * Initialisiert die Player- und Computer-Objekte, die spaeter im Spiel verwendet werden.
     */
    public Game() {
        player = new Player();
        computer = new Computer();
    }

    /**
     * Startet das Spiel und fuehrt durch den gesamten Spielablauf.
     * Diese Methode steuert die Anzeige der Willkommensnachricht, die Wahl des Spielmodus,
     * die Anzahl der Runden, den Ablauf jeder Runde und die Anzeige des Ergebnisses.
     */
    public void startGame() {
        // Zeige die Willkommensnachricht nur beim ersten Spielstart an
        if (isFirstGame) {
            displayWelcomeMessage();
            isFirstGame = false;  // Setzt die Variable auf false, nachdem die Nachricht das erste mal angezeigt wurde
        }

        // Spieler waehlt den Spielmodus: klassisch (1) oder erweitert (2)
        String gameMode = getGameMode();

        // Zeige die Spielregeln basierend auf dem gewaehlten Modus
        displayRules(gameMode);

        // Spieler waehlt die Anzahl der Runden, die gespielt werden sollen
        int rounds = getNumberOfRounds();

        // Zeige eine Nachricht an, dass das Spiel beginnt
        displayGameStartMessage();

        // Schleife, die das Spiel fuer die gewaehlte Anzahl an Runden ausfuehrt
        for (int i = 0; i < rounds; i++) {
            leerzeile();
            System.out.println(" \033[1m" + "Runde " + (i + 1) + " von " + rounds + "\033[0m");
            System.out.println(" --------------- ");
            leerzeile();

            // Spieler waehlt seinen Zug
            String playerChoice = player.getChoice(gameMode);
            leerzeile();

            System.out.println(" . . . . ."); // Abtrennung zwischen Spieler- und Computerwahl

            // Computer waehlt seinen Zug
            String computerChoice = computer.getChoice(gameMode);
            leerzeile();
            System.out.println(" Der Computer hat gewählt: \033[1m" + capitalizeFirstLetter(computerChoice) + "\033[0m");

            // Bestimme den Gewinner der Runde und zeige das Ergebnis an
            boolean showResult = rounds > 1 || i < rounds - 1;
            leerzeile();
            determineWinner(playerChoice, computerChoice, showResult, gameMode);
            leerzeile();

            // Zeige den aktuellen Spielstand nach jeder Runde an, außer bei nur einer gespielten Runde oder der letzten Runde
            if (i < rounds - 1) {
                hyphenLine();
                System.out.println(" Aktueller Spielstand: Spieler " + playerWins + " - " + computerWins + " Computer");
                hyphenLine();
            }
        }

        // Zeige den Endstand und das Gesamtergebnis nach dem Abschluss aller Runden an
        hyphenLine();
        System.out.println(" \033[1m" + "Das Spiel ist vorbei!" + "\033[0m");
        hyphenLine();
        leerzeile();
        System.out.println(" Endstand: Spieler " + playerWins + " - " + computerWins + " Computer");
        leerzeile();

        // Bestimme, ob der Spieler oder der Computer gewonnen hat, oder ob es ein Unentschieden gibt
        if (playerWins > computerWins) {
            System.out.println("\033[32m\033[1m        -----------------------------------------------------");
            System.out.println(" ++++++| Herzlichen Glückwunsch! Du hast das Spiel gewonnen! |++++++");
            System.out.println("        -----------------------------------------------------\033[0m");
        } else if (playerWins < computerWins) {
            System.out.println("\033[31m\033[1m        --------------------------------------------------------------------");
            System.out.println(" ------| Der Computer hat das Spiel gewonnen. Viel Glück beim nächsten Mal! |------");
            System.out.println("        --------------------------------------------------------------------\033[0m");
        } else {
            System.out.println("\033[33m\033[1m        --------------------------------");
            System.out.println(" //////| Das Spiel endet unentschieden! |//////");
            System.out.println("        --------------------------------\033[0m");
        }

        leerzeile();
        hyphenLine();
        leerzeile();

        // Frage den Spieler, ob er eine weitere Partie spielen moechte
        String playAgain;
        do {
            System.out.print("\033[1m Noch einmal spielen? (ja/nein):\033[0m ");
            playAgain = IO.readString().trim().toLowerCase();

            // Handhabe die Antwort des Spielers auf die Frage, ob er weiterspielen moechte
            switch (playAgain) {
                case "ja", "j" -> {
                    // Setze die Spielstaende zurueck und starte das Spiel erneut
                    playerWins = 0;
                    computerWins = 0;
                    leerzeile();
                    hyphenLine();
                    startGame(); // Starte das Spiel erneut
                    return; // Methode verlassen
                }
                case "nein", "n" -> {
                    // Beende das Spiel und verabschiede dich vom Spieler
                    leerzeile();
                    System.out.println("\033[34m\033[1m Vielen Dank fürs Spielen! Auf Wiedersehen!\033[0m");
                    leerzeile();
                    return; // Methode verlassen
                }
                default -> {
                    // Bei ungueltigen Eingaben wird der Spieler erneut aufgefordert, ja oder nein einzugeben
                    leerzeile();
                    System.out.println("\033[31m Ungültige Auswahl. Bitte gib 'ja' oder 'nein' ein.\033[0m");
                    leerzeile();
                }
            }
        } while (true); // Wiederhole die Abfrage, bis eine gueltige Antwort eingegeben wird
    }

    /**
     * Zeigt die Willkommensnachricht beim ersten Start des Spiels an.
     * Diese Methode wird nur einmal aufgerufen, wenn das Spiel zum ersten Mal gestartet wird.
     */
    private void displayWelcomeMessage() {
        leerzeile();
        System.out.println("\033[34m\033[1m--------------------------------------------------"); // Durchgezogene Linie lang
        System.out.println(" Willkommen zu Schere, Stein, Papier!\033[0m");
        System.out.println("\033[34m Viel Spaß beim Spielen!");
        System.out.println("\033[1m--------------------------------------------------\033[0m"); // Durchgezogene Linie lang
    }

    /**
     * Zeigt eine Nachricht nach der Auswahl von Modus und Rundenanzahl an.
     * Kennzeichnet den Start des Spiels fuer den Spieler.
     */
    private void displayGameStartMessage() {
        leerzeile();
        hyphenLine();
        System.out.println("\033[1m Das Spiel kann beginnen!\033[0m");
        hyphenLine();
    }

    /**
     * Fuegt eine Leerzeile in die Konsole ein.
     * Dies wird verwendet, um die Ausgabe uebersichtlicher zu gestalten.
     */
    public void leerzeile() {
        System.out.println();
    }

    /**
     * Zeigt eine durchgezogene Linie in der Konsole an.
     * Dies wird verwendet, um Abschnitte in der Ausgabe optisch zu trennen.
     */
    public void hyphenLine() {
        System.out.println("----------------------------------------");
    }

    /**
     * Fordert den Spieler auf, einen Spielmodus zu waehlen.
     * Der Spieler kann zwischen dem klassischen Modus (Schere, Stein, Papier) und dem erweiterten Modus (Schere, Stein, Papier, Brunnen) waehlen.
     *
     * @return Der gewaehlte Spielmodus als String. "1" steht fuer den klassischen Modus, "2" fuer den erweiterten Modus.
     */
    private String getGameMode() {
        String gameMode;
        leerzeile();
        System.out.println("\033[1m Welchen Modus möchstest du spielen?\033[0m");
        leerzeile();
        System.out.println(" 1. Klassisch (Schere, Stein, Papier)");
        System.out.println(" 2. Erweitert (Schere, Stein, Papier, Brunnen)");
        leerzeile();
        leerzeile();
        System.out.print("\033[1m Deine Auswahl (1 oder 2):\033[0m ");
        gameMode = IO.readString().trim().toLowerCase();
        do {
            if (!gameMode.equals("1") && !gameMode.equals("2")) {
                leerzeile();
                System.out.print("\033[31m Ungültige Auswahl. Bitte gib entweder 1 oder 2 ein: \033[0m\033[1m");
                gameMode = IO.readString().trim().toLowerCase();
                System.out.print("\033[0m");
            }
        } while (!gameMode.equals("1") && !gameMode.equals("2"));
        return gameMode;
    }

    /**
     * Fordert den Spieler auf, die Anzahl der zu spielenden Runden auszuwaehlen.
     * Der Spieler gibt eine Zahl ein, die die Anzahl der Runden festlegt.
     *
     * @return Die Anzahl der Runden als ganze Zahl.
     */
    public int getNumberOfRounds() {
        int rounds;
        do {
            leerzeile();
            System.out.print(" Gib die Anzahl der Runden ein, die gespielt werden sollen (1-10): ");
            rounds = IO.readInt();
            if (rounds < 1 || rounds > 10) {
                leerzeile();
                System.out.println("\033[31m Ungültige Anzahl. Bitte gib eine Zahl zwischen 1 und 10 ein.\033[0m");
            }
        } while (rounds < 1 || rounds > 10); // Wiederhole die Abfrage, bis eine gueltige Anzahl eingegeben wird
        return rounds;
    }

    /**
     * Zeigt die Spielregeln basierend auf dem gewaehlten Spielmodus an.
     * Diese Methode erklaert dem Spieler die Regeln des Spiels, je nachdem, ob der klassische oder der erweiterte Modus gewaehlt wurde.
     *
     * @param gameMode Der gewaehlte Spielmodus ("1" fuer klassisch, "2" fuer erweitert).
     */
    private void displayRules(String gameMode) {
        leerzeile();
        if (gameMode.equals("1")) { // Klassischer Modus
            hyphenLine();
            leerzeile();
            System.out.println("\033[1m §§ Regeln für den klassischen Modus §§\033[0m");
            leerzeile();
            System.out.println(" - Schere schlägt Papier");
            System.out.println(" - Papier schlägt Stein");
            System.out.println(" - Stein schlägt Schere");
            leerzeile();
            hyphenLine();
        } else if (gameMode.equals("2")) { // Erweiterter Modus
            hyphenLine();
            leerzeile();
            System.out.println("\033[1m §§ Regeln für den erweiterten Modus §§\033[0m");
            leerzeile();
            System.out.println(" - Schere schlägt Papier");
            System.out.println(" - Papier schlägt Stein und Brunnen");
            System.out.println(" - Stein schlägt Schere");
            System.out.println(" - Brunnen schlägt Schere und Stein");
            leerzeile();
            hyphenLine();
        }
    }

    /**
     * Ermittelt den Gewinner einer Runde basierend auf den Entscheidungen des Spielers und des Computers.
     * Diese Methode aktualisiert die Zaehler fuer Spieler- und Computer-Siege und zeigt das Ergebnis der Runde an, falls gewuenscht.
     *
     * @param playerChoice   Die Wahl des Spielers ("schere", "stein", "papier", "brunnen").
     * @param computerChoice Die Wahl des Computers ("schere", "stein", "papier", "brunnen").
     * @param showResult     Ein boolescher Wert, der angibt, ob das Ergebnis der Runde angezeigt werden soll.
     * @param gameMode       Der gewaehlte Spielmodus ("1" fuer klassisch, "2" fuer erweitert).
     */
    private void determineWinner(String playerChoice, String computerChoice, boolean showResult, String gameMode) {
        // ueberpruefe auf ein Unentschieden
        if (playerChoice.equals(computerChoice)) {
            if (showResult) {
                System.out.println("\033[33m" + " Unentschieden!" + "\033[0m");
            }
        } else if (gameMode.equals("1")) { // Klassischer Modus: Pruefe die Bedigungen zum Gewinnen
            if ((playerChoice.equals("schere") && computerChoice.equals("papier")) ||
                (playerChoice.equals("stein") && computerChoice.equals("schere")) ||
                (playerChoice.equals("papier") && computerChoice.equals("stein"))) {
                if (showResult) {
                    System.out.println("\033[32m" + " Du hast gewonnen!" + "\033[0m");
                }
                playerWins++; // Erhoehe die Anzahl der Siege des Spielers
            } else {
                if (showResult) {
                    System.out.println("\033[31m" + " Du hast verloren!" + "\033[0m");
                }
                computerWins++; // Erhoehe die Anzahl der Siege des Computers
            }
        } else { // Erweiterter Modus: Pruefe die Bedingungen zum Gewinnen mit Brunnen
            if ((playerChoice.equals("schere") && computerChoice.equals("papier")) ||
                (playerChoice.equals("stein") && computerChoice.equals("schere")) ||
                (playerChoice.equals("papier") && (computerChoice.equals("stein") || computerChoice.equals("brunnen"))) ||
                (playerChoice.equals("brunnen") && (computerChoice.equals("stein") || computerChoice.equals("schere")))) {
                if (showResult) {
                    System.out.println("\033[32m" + " Du hast gewonnen!" + "\033[0m");
                }
                playerWins++; // Erhoehe die Anzahl der Siege des Spielers
            } else {
                if (showResult) {
                    System.out.println("\033[31m" + " Du hast verloren!" + "\033[0m");
                }
                computerWins++; // Erhoehe die Anzahl der Siege des Computers
            }
        }
    }

    /**
     * aendert den ersten Buchstaben eines Strings zu einem Großbuchstaben.
     * Dies wird verwendet, um die Wahl des Computers ansprechend auszugeben.
     *
     * @param input Der Eingabestring, dessen erster Buchstabe großgeschrieben werden soll.
     * @return Der String mit einem großgeschriebenen ersten Buchstaben.
     */
    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input; // Gib den urspruenglichen String zurueck, falls er leer ist
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1); // Erster Buchstabe großschreiben und den Rest des Strings beibehalten
    }
}
