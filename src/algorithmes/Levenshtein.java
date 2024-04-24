package algorithmes;

public class Levenshtein {

    /**
     * Calcule la distance de Levenshtein entre deux chaînes de caractères.
     * @param str1 Première chaîne de caractères.
     * @param str2 Deuxième chaîne de caractères.
     * @return La distance de algorithmes.Levenshtein.
     */
    public static int distance(String str1, String str2) {
        int n = str1.length() + 1;
        int m = str2.length() + 1;

        // Initialisation du tableau pour stocker les distances intermédiaires
        int[][] values = new int[n][m];

        // Remplir la première ligne et la première colonne
        for (int i = 0; i < n; i++) {
            values[i][0] = i;
        }
        for (int i = 0; i < m; i++) {
            values[0][i] = i;
        }

        // Calcul de la distance de algorithmes.Levenshtein
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                char last_1 = str1.charAt(i - 1);
                char last_2 = str2.charAt(j - 1);

                if (last_1 == last_2) {
                    values[i][j] = values[i - 1][j - 1];
                } else {
                    values[i][j] = 1 + Math.min(Math.min(values[i - 1][j - 1], values[i - 1][j]), values[i][j - 1]);
                }
            }
        }

        // Retourner la distance calculée (coin inférieur droit du tableau)
        return values[n - 1][m - 1];
    }
}
