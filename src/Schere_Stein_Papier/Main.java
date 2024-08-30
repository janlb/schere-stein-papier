package Schere_Stein_Papier;
/**
 * Die Klasse Main enthaelt den Einstiegspunkt fuer die Programm.
 * Sie startet das Spiel, indem sie eine Instanz der Game-Klasse erstellt und die Methode startGame() aufruft.
 */
public class Main {

    /**
     * Der Haupteinstiegspunkt des Programms. Diese Methode startet das Spiel.
     *
     * @param args Kommandozeilenargumente (werden hier nicht verwendet).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
