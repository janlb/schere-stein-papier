package Schere_Stein_Papier;
/**
 * Die Klasse Player repraesentiert den Spieler im Spiel.
 * Der Spieler wird waehrend einer Spielrunde aufgefordert eine Wahl zu treffen, entweder im klassischen 
 * oder im erweiterten Modus.
 */
public class Player {

    /**
     * Fordert den Spieler auf, eine Wahl basierend auf dem Spielmodus zu treffen.
     * Ueberprueft die Eingabe und stellt sicher, dass sie eine gueltige Wahl darstellt.
     * Wird eine ungueltige Wahl getroffen, wird der Spieler erneut aufgefordert, eine Wahl zu treffen.
     * 
     * @param gameMode Der Spielmodus, der entweder "1" fuer den klassischen Modus 
     *                 (Schere, Stein, Papier) oder "2" fuer den erweiterten Modus 
     *                 (Schere, Stein, Papier, Brunnen) sein kann.
     * @return Die Wahl des Spielers als String, entweder "schere", "stein", "papier" 
     *         oder zusaetzlich "brunnen" im erweiterten Modus.
     */
    public String getChoice(String gameMode) {
        String input;

        // Ueberprueft, ob der Spielmodus "1" (klassischer Modus) ist.
        if (gameMode.equals("1")) {
            System.out.print(" Schere, Stein oder Papier?: \033[1m");
            input = IO.readString().trim().toLowerCase();
            System.out.print("\033[0m");

            // Ueberprueft die Eingabe fuer den klassischen Modus.
            do {
                if (!input.equals("schere") && !input.equals("stein") && !input.equals("papier")) {
                    System.out.println();
                    System.out.print("\033[31m Ungültige Auswahl. Bitte gib entweder Schere, Stein oder Papier ein: \033[0m\033[1m");
                    input = IO.readString().trim().toLowerCase();
                    System.out.print("\033[0m");
                }
            } while (!input.equals("schere") && !input.equals("stein") && !input.equals("papier"));
        } 
        // Spielmodus "2" (erweiterter Modus)
        else {
            System.out.print(" Schere, Stein, Papier oder Brunnen?: \033[1m");
            input = IO.readString().trim().toLowerCase();
            System.out.print("\033[0m");

            // Ueberprueft die Eingabe fuer den erweiterten Modus.
            do {
                if (!input.equals("schere") && !input.equals("stein") && !input.equals("papier") && !input.equals("brunnen")) {
                    System.out.println();
                    System.out.print("\033[31m Ungültige Auswahl. Bitte gib entweder Schere, Stein, Papier oder Brunnen ein: \033[0m\033[1m");
                    input = IO.readString().trim().toLowerCase();
                    System.out.print("\033[0m");
                }
            } while (!input.equals("schere") && !input.equals("stein") && !input.equals("papier") && !input.equals("brunnen"));
        }

        // Gibt die gueltige Eingabe zurueck.
        return input;
    }
}
