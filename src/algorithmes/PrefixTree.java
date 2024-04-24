package algorithmes;

import java.util.HashMap;

public class PrefixTree {
    TrieNode root;

    public PrefixTree() {
        root = new TrieNode();
    }

    private static class TrieNode {
        private final HashMap<Character, TrieNode> children;
        private boolean isTerminal;  // Marque la fin d'un mot

        public TrieNode() {
            this.children = new HashMap<>();
        }
    }

    /**
     * Insère un mot dans l'arbre de préfixes.
     * @param word Le mot à insérer.
     */
    public void insert(String word) {
        TrieNode current = root;

        for (char character : word.toCharArray()) {
            current.children.putIfAbsent(character, new TrieNode());
            current = current.children.get(character);
        }

        current.isTerminal = true;  // Marque la fin du mot
    }

    /**
     * Recherche un mot dans l'arbre de préfixes.
     * @param word Le mot à rechercher.
     * @return True si le mot est trouvé, False sinon.
     */
    public boolean search(String word) {
        TrieNode current = root;

        for (char character : word.toCharArray()) {
            current = current.children.get(character);
            if (current == null) {
                return false;
            }
        }

        return current.isTerminal;  // Vérifie si c'est bien la fin d'un mot
    }
}
