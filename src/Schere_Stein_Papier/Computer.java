package Schere_Stein_Papier;
/**
 * Die Klasse Computer repraesentiert den Computer bzw. den Gegner des Spielers.
 * Der Computer trifft eine zufaellige Wahl basierend auf dem Auswahlmoeglichkeiten des gewaehlten Spielmodus. (mit oder ohne Brunnen)
 */
public class Computer {

    /**
     * Generiert die Wahl des Computers basierend auf dem aktuellen Spielmodus.
     * 
     * @param gameMode Der Spielmodus, der entweder "1" fuer den klassischen Modus 
     *                 (Schere, Stein, Papier) oder "2" fuer den erweiterten Modus 
     *                 (Schere, Stein, Papier, Brunnen) sein kann.
     * @return Die Wahl des Computers als String, entweder "schere", "stein", "papier" 
     *         oder "brunnen" im erweiterten Modus.
     */
    public String getChoice(String gameMode) {

        // Bestimmt die moeglichen Optionen basierend auf dem vom Spieler ausgewaehlten Spielmodus.
        String[] choices = gameMode.equals("1") 
                ? new String[]{"schere", "stein", "papier"} 
                : new String[]{"schere", "stein", "papier", "brunnen"};
        
        // Waehlt zufaellig eine der Optionen aus.
        int randomIndex = (int) (Math.random() * choices.length);
        
        // Gibt die zufaellig ausgewaehlte Option zurueck.
        return choices[randomIndex];
    }
}
