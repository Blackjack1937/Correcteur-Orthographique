# Correcteur Orthographique
---

### Description du Projet
Le projet de correcteur orthographique est conçu pour identifier les erreurs orthographiques dans les mots et proposer
des corrections appropriées. Utilisant un dictionnaire de mots français, le système vérifie l'orthographe des mots et,
en cas d'erreur, suggère des alternatives proches en termes orthographiques. La rapidité et l'efficacité sont assurées
grâce à l'utilisation de techniques algorithmiques avancées.

---
### Structure du Projet

correcteur_orthographique
│
├── traitement_texte
│ ├── Correcteur.java
│ └── Dictionnaire.java
│
├── algorithmes
│ ├── Levenshtein.java
│ └── PrefixTree.java
│
├── trigrammes
│ └── Trigram.java
│
├── config
│ ├── Options.java
│ └── OptionsTest.java
│
└── Main.java

### Fonctionnalités
- **Vérification Orthographique :** Le correcteur orthographique peut déterminer si un mot donné est correctement orthographié en le comparant à un dictionnaire de mots français.
- **Suggestions de Correction :** En cas de faute d'orthographe, le système propose une liste de mots proches, aidant l'utilisateur à trouver le bon mot.
- **Optimisation des Performances :** L'application est conçue pour gérer efficacement un grand volume de mots grâce à des algorithmes et des structures de données optimisés.

---

### Composition du Projet
- **Dictionary.java :** Centralise la gestion du dictionnaire. Il lit les mots à partir d'un fichier et offre des fonctionnalités pour vérifier l'existence d'un mot et suggérer des corrections.
- **Levenshtein.java :** Implémente l'algorithme de distance de Levenshtein pour calculer la proximité entre deux mots, essentielle pour suggérer des corrections pertinentes.
- **PrefixTree.java :** Utilise une structure d'arbre de préfixes pour stocker et rechercher efficacement les mots dans le dictionnaire.
- **SpellChecker.java :** Le cœur du système, qui gère la logique de vérification de l'orthographe et de proposition des corrections basées sur le dictionnaire et les trigrammes.
- **Trigram.java :** Génère et stocke des trigrammes pour chaque mot, permettant une recherche rapide de mots similaires pour la correction.
- **Options.java & OptionsTest.java :** Fournissent une interface pour configurer le correcteur et tester différentes configurations.
- **Main.java :** Corrige les mots du fichier "fautes.txt".
- **App.java :** Corrige le mot fourni à la stdin.
---

### Instructions d'Utilisation
1. **Compilation :** Compilez les fichiers Java en utilisant votre IDE Java ou la ligne de commande.
2. **Exécution :** Exécutez la classe "Main.java" pour corriger le fichier "fautes.txt" en entier, et la classe "App.java" pour lire directement depuis la stdin et proposer 5 corrections possibles.
3. **Vérification de l'orthographe :** Entrez les mots que vous souhaitez vérifier en modifiant le fichier "/resources/fautes". Le programme indiquera si chaque mot est correct ou incorrect.
4. **Suggestions de Corrections :** Pour les mots incorrects, le correcteur affichera une liste de suggestions basées sur la proximité orthographique.

---

### Complexité Algorithmique

Le correcteur orthographique utilise plusieurs algorithmes et structures de données, chacun ayant sa propre complexité. Voici un aperçu de la complexité de certains composants clés :

#### 1. Distance de Levenshtein
- La méthode de calcul de la distance de Levenshtein a une complexité temporelle de `O(m x n)`, où `m` et `n` sont les longueurs des deux mots comparés. Cette complexité provient de l'approche de programmation dynamique, nécessitant une matrice de taille `m x n` pour le calcul des distances.

#### 2. Recherche dans l'Arbre de Préfixes (Trie)
- La recherche d'un mot dans un arbre de préfixes est de complexité `O(k)`, où `k` est la longueur du mot. La recherche nécessite de parcourir l'arbre depuis la racine jusqu'à un nœud terminal, en suivant les caractères du mot.

#### 3. Génération et Recherche de Trigrammes
- La génération de trigrammes pour un mot donné a une complexité de `O(l)`, où `l` est la longueur du mot. La recherche de mots avec des trigrammes communs est généralement rapide car elle se fait via une recherche dans une hashmap.

#### 4. Traitement Global
- Pour chaque mot entré, le correcteur vérifie d'abord sa présence dans le dictionnaire (recherche dans l'arbre de préfixes).
- Si le mot n'est pas trouvé, il génère des trigrammes et recherche des mots similaires.
- Pour chaque mot similaire trouvé, le correcteur calcule la distance de Levenshtein et sélectionne les 5 corrections les plus probables.

La complexité totale du correcteur dépend de ces opérations combinées. Pour un mot de longueur `k`, la complexité est principalement influencée par le calcul de la distance de Levenshtein pour plusieurs paires de mots.

---

### Licence
Ce projet est sous licence Apache 2.0. Pour plus de détails, veuillez consulter le fichier LICENSE ou visiter [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).

---

### Détails Techniques
- **Version Java** : Java 21
- **IDE** : IntelliJ IDEA 2023.2.5

---

### Sources
- **Théorie** : "Introduction a l'algorithmique" et notes de cours.
- **Wikipédia** : https://fr.wikipedia.org/wiki/Distance_de_Levenshtein
- **TP Univ. Paris-Diderot** : https://www.linguist.univ-paris-diderot.fr/~amsili/Ens20/pdf/tp_l8dn003_20_04_cor_q3.pdf

---


