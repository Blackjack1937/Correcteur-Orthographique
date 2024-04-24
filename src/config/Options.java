package config;

import java.util.Arrays;

public class Options {

    public static String pathToDictionary = "resources/dico.txt";
    public static String pathToFile = "resources/fautes.txt";
    public static String word = "ababab";

    /**
     * Analyse les arguments de la ligne de commande et configure les options.
     * @param args Les arguments de la ligne de commande.
     */
    public static void parseCommandLine(String[] args) {
        if (args.length == 0) {
            return;
        }

        int index = 0;

        // Traitement de l'option d'aide
        if (isHelpOption(args[index])) {
            usage();
            System.exit(0);
        }

        // Traitement de l'option de dictionnaire
        if (isDictOption(args[index])) {
            pathToDictionary = getNextArg(args, ++index);
        }

        // Configuration du fichier ou du mot
        if (index < args.length) {
            pathToFile = args[index];
            word = args[index];
        }
    }

    /**
     * Vérifie si l'argument est une option d'aide.
     * @param arg L'argument à vérifier.
     * @return True si c'est une option d'aide, False sinon.
     */
    private static boolean isHelpOption(String arg) {
        String[] helpStrs = {"-h", "--help"};
        return Arrays.asList(helpStrs).contains(arg);
    }

    /**
     * Vérifie si l'argument est une option de dictionnaire.
     * @param arg L'argument à vérifier.
     * @return True si c'est une option de dictionnaire, False sinon.
     */
    private static boolean isDictOption(String arg) {
        String[] dictStrs = {"-d", "--dict", "--dico"};
        return Arrays.asList(dictStrs).contains(arg);
    }

    /**
     * Récupère l'argument suivant dans la liste, avec gestion des erreurs.
     * @param args Les arguments de la ligne de commande.
     * @param index L'index de l'argument actuel.
     * @return L'argument suivant si disponible.
     */
    private static String getNextArg(String[] args, int index) {
        try {
            return args[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Argument manquant pour l'option : " + args[index - 1]);
            System.exit(1);
            return ""; // Nécessaire pour la compilation, jamais atteint
        }
    }

    /**
     * Affiche l'utilisation de la commande.
     */
    private static void usage() {
        System.out.println("Usage : java -jar Spell.jar [-d dictionary] [file|word]");
    }

    /**
     * Affiche les options configurées.
     */
    public static void print() {
        System.out.printf("Dictionnaire : %s%n", Options.pathToDictionary);
        System.out.printf("Fichier : %s%n", Options.pathToFile);
        System.out.printf("Mot : %s%n", Options.word);
    }

    /**
     * Réinitialise les options aux valeurs par défaut.
     */
    public static void reset() {
        pathToDictionary = "resources/dico.txt";
        pathToFile = "resources/fautes.txt";
        word = "ababab";
    }
}
