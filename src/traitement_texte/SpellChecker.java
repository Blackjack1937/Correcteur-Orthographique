package traitement_texte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SpellChecker {

    private final ArrayList<String> words = new ArrayList<>();
    private final Dictionary dictionary;

    public SpellChecker(String pathToFile, String word, Dictionary dictionary) {
        this.dictionary = dictionary;

        if (!pathToFile.isEmpty()) {
            try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
                String aWord;
                while((aWord = br.readLine()) != null) {
                    words.add(aWord);
                }
            } catch(IOException e) {
                System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
                words.add(word); // Ajoute le mot en paramètre si la lecture échoue
            }
        } else {
            words.add(word);
        }
    }

    public void spell(String word) {
        if (word == null || word.isEmpty()) {
            System.out.println("\uD83D\uDD34 ️Le mot est vide ou nul.");
            return;
        }

        if (dictionary.checkWordInDico(word)) {
            System.out.println("\uD83D\uDFE2 Le mot " + word + " est correct.");
        } else {
            System.out.println("\uD83D\uDFE1 Corrections possibles pour : " + word);
            for (String correctedWord : dictionary.correctedWords(word)) {
                System.out.println("    ✦ " + correctedWord);
            }
        }
        System.out.println("——————————————————————————————————————————————————————");
    }

    public ArrayList<String> getWords() {
        return words;
    }
}
