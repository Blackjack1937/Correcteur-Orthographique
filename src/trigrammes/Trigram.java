package trigrammes;

import java.util.ArrayList;
import java.util.HashMap;

public class Trigram {

    private final HashMap<String, ArrayList<String>> trigrams = new HashMap<>();

    /**
     * Insère un mot dans le trigramme en générant tous les trigrammes possibles du mot.
     * @param word Le mot à insérer dans le trigramme.
     */
    public void insert(String word) {
        String extendedWord = '<' + word + '>';

        for (int i = 0; i < extendedWord.length() - 2; i++) {
            String trigram = extendedWord.substring(i, i + 3);

            trigrams.computeIfAbsent(trigram, k -> new ArrayList<>()).add(word);

            if (trigram.indexOf('>') != -1) {
                break;
            }
        }
    }

    /**
     * Récupère la liste de tous les trigrammes et des mots associés.
     * @return Une HashMap des trigrammes avec la liste de mots correspondants.
     */
    public HashMap<String, ArrayList<String>> getAll() {
        return trigrams;
    }
}
