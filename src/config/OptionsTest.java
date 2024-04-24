package config;

import static java.lang.String.join;

public class OptionsTest {

    private static class Test {
        private final String description;
        private String[] commandLine;

        public Test(String desc, String cLine) {
            this.description = desc;
            this.commandLine = cLine.split(" ");
            if (this.commandLine.length == 1 && this.commandLine[0].isEmpty()) {
                this.commandLine = new String[] {};
            }
        }

        public void run() {
            System.out.println("\n" + this.description);
            System.out.printf("Ligne de commande : %s%n", join(" ", this.commandLine));
            System.out.printf("Nombre d'arguments : %d%n", this.commandLine.length);
            Options.parseCommandLine(this.commandLine);
            Options.print();
            Options.reset();
            System.out.println("Fin du test\n");
        }
    }

    public static void main(String[] args) {
        Test testDictionnaire = new Test(
                "Test bon fonctionnement option dictionnaire",
                "-d monDictionnaire monFichier"
        );
        Test testPasDeDictionnaire = new Test(
                "Test bon fonctionnement s'il y a seulement un fichier",
                "monFichier"
        );
        Test testCLineVide = new Test(
                "Test bon fonctionnement si la ligne de commande est vide",
                ""
        );
        Test testHelp = new Test(
                "Test bon fonctionnement option help",
                "-h"
        );

        testDictionnaire.run();
        testPasDeDictionnaire.run();
        testCLineVide.run();
        testHelp.run();
    }
}
