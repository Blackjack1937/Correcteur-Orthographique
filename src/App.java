import traitement_texte.Dictionary;

import java.util.ArrayList;
import java.util.Scanner;

/** Classe App qui permet de lire un mot directement depuis la stdin, et proposer 5 corrections possibles. */

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = new Dictionary("resources/dico.txt");

        System.out.println("Entrez un mot pour vérification : ");
        String word = scanner.nextLine();

        if (dictionary.checkWordInDico(word)) {
            System.out.println("Le mot " + word + " est correct.");
        } else {
            System.out.println("Corrections pour : " + word);
            ArrayList<String> corrections = dictionary.correctedWords(word);
            for (int i = 0; i < Math.min(corrections.size(), 5); i++) {
                System.out.println((i + 1) + ". " + corrections.get(i));
            }
        }
    }
}
