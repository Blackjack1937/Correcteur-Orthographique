package traitement_texte;

import algorithmes.Levenshtein;
import algorithmes.PrefixTree;
import trigrammes.Trigram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {

    public PrefixTree dico = new PrefixTree();
    public Trigram trigrams = new Trigram();

    /**
     * Constructeur qui lit le fichier de dictionnaire et initialise l'arbre des préfixes et les trigrammes.
     * @param filename Le nom du fichier contenant le dictionnaire.
     */
    public Dictionary(String filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String word;
            while((word = br.readLine()) != null) {
                dico.insert(word);  // Insertion dans l'arbre des préfixes
                trigrams.insert(word);  // Insertion dans les trigrammes
            }
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            // Ajouter ici la gestion des erreurs ou la propagation de l'exception
        }
    }

    /**
     * Vérifie si un mot est présent dans le dictionnaire.
     * @param word Le mot à vérifier.
     * @return True si le mot est dans le dictionnaire, False sinon.
     */
    public Boolean checkWordInDico(String word) {
        return dico.search(word);
    }

    /**
     * Trouve et retourne les corrections possibles d'un mot.
     * @param word Le mot à corriger.
     * @return Une liste des corrections possibles.
     */
    public ArrayList<String> correctedWords(String word){
        // Construction de la liste des trigrams du mot "word"
        Trigram trigram = new Trigram();
        trigram.insert(word);

        HashMap<String, ArrayList<String>> wordTrigrams = trigram.getAll();
        ArrayList<String> commonsTrigrams = new ArrayList<>();

        // Récupération du dictionnaire de tout les mots qui ont le ou les memes trigrammes que le mot "word"
        HashMap<String, ArrayList<String>> allTrigrams = trigrams.getAll();
        for (String i : wordTrigrams.keySet()) {
            if (allTrigrams.get(i) != null) {
                commonsTrigrams.addAll(allTrigrams.get(i));
            }
        }

        LinkedHashMap<String, Integer> occurences = countOccurences(commonsTrigrams);

        ArrayList<String> firstWords = firstNWords(occurences, 100);

        LinkedHashMap<String, Integer> commonWords = levenshteinCommonWords(firstWords, word);

        return firstNWords(commonWords, 5);
    }

    /**
     * Retourne un Map contenant les mots (par ordre décroissant) ayant le plus de trigrammes communs avec
     * le mot recherché.
     * @param commonsTrigrams Liste des trigrammes communs.
     * @return Map triée par nombre d'occurrences décroissantes.
     */
    private LinkedHashMap<String, Integer> countOccurences(ArrayList<String> commonsTrigrams) {

        HashMap<String, Integer> occurences = new HashMap<>();

        for (String i: commonsTrigrams) {
            occurences.merge(i, 1, Integer::sum);
        }

        LinkedHashMap<String, Integer> reverseSortedOccurences = new LinkedHashMap<>();

        occurences.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedOccurences.put(x.getKey(), x.getValue()));
        return reverseSortedOccurences;
    }

    /**
     * Renvoie les N premiers mots définis par maxWords.
     * @param orderedOccurences Occurrences triées des mots.
     * @param maxWords Nombre maximum de mots à retourner.
     * @return Liste des N premiers mots.
     */
    private ArrayList<String> firstNWords(LinkedHashMap<String, Integer> orderedOccurences, Integer maxWords) {
        ArrayList<String> words = new ArrayList<>();

        int counter = 1;
        for (Map.Entry<String, Integer> element : orderedOccurences.entrySet()) {
            words.add(element.getKey());
            if (counter >= maxWords) {
                break;
            }
            counter++;
        }

        return words;
    }

    /**
     * Renvoie la distance entre le mot recherché et chaque mot trouvé.
     * @param firstNWords Liste des premiers mots à comparer.
     * @param queryWord Le mot recherché.
     * @return Map des mots avec leur distance de algorithmes.Levenshtein par rapport au mot recherché.
     */
    private LinkedHashMap<String, Integer> levenshteinCommonWords(ArrayList<String> firstNWords, String queryWord) {
        LinkedHashMap<String, Integer> wordsDistances = new LinkedHashMap<>();

        for (String word : firstNWords) {
            wordsDistances.put(word, Levenshtein.distance(queryWord, word));
        }

        LinkedHashMap<String, Integer> sortedWordsDistances = new LinkedHashMap<>();

        wordsDistances.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedWordsDistances.put(x.getKey(), x.getValue()));

        return sortedWordsDistances;
    }
}
