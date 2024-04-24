import config.*;
import traitement_texte.*;

/** Classe Main qui lit et propose des corrections pour tous les mots du fichier "fautes.txt". */

public class Main {

    public static void main(String[] args) {
        Options.parseCommandLine(args);

        Timer timer_dico = new Timer("Dictionnaire");
        Dictionary dico = new Dictionary("resources/dico.txt");

        timer_dico.print_time_past();

        Timer timer_speller = new Timer("Correcteur orthographique");

        SpellChecker spellchecker = new SpellChecker(Options.pathToFile, Options.word, dico);
        for (String word : spellchecker.getWords()) {
            spellchecker.spell(word);
        }

        timer_speller.print_time_past();
    }

    private static class Timer {
        long startTime;
        String name;

        public Timer(String name) {
            startTime = System.nanoTime();
            this.name = name;
        }

        public long time_past() {
            return System.nanoTime() - startTime;
        }

        public void print_time_past() {
            String message = String.format("   ⏱️ Timer %s : %f sec. past  ⏱️", name, ((double) time_past()) / 1_000_000_000.0);
            System.out.println("\n");
            System.out.println(message);
            System.out.println("\n");
        }

    }
}
