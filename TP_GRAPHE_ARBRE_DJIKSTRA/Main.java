
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbreBinaire arbre = new ArbreBinaire();
        while (true) {
            clearScreen();
            System.out.println("\nMenu :");
            System.out.println("1. Tri topologique (sur graphe oriente)");
            System.out.println("2. Dijkstra + distances (graphe sans cycle negatif)");
            System.out.println("3. Construire un arbre (vide)");
            System.out.println("4. Ajouter un element dans l'arbre");
            System.out.println("5. Rechercher un element dans l'arbre");
            System.out.println("6. Supprimer un element dans l'arbre (et son sous-arbre)");
            System.out.println("7. Parcours prefixe de l'arbre");
            System.out.println("8. Parcours infixe de l'arbre");
            System.out.println("9. Parcours postfixe de l'arbre");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");

                int choix = sc.nextInt();

            switch (choix) {
                    case 1:
                        clearScreen();
                        System.out.print("Nombre de sommets : ");
                        int n = sc.nextInt();
                        Graphe gOriente = new Graphe(n);
                        Set<String> aretesAjoutees = new HashSet<>();

                        while (true) {
                            System.out.println("Ajouter une arrete (u v) ou -1 pour terminer :");
                            int u = sc.nextInt();
                            if (u == -1) break;
                            int v = sc.nextInt();

                            if (u < 0 || u >= n || v < 0 || v >= n) {
                                System.out.println("Erreur : sommets hors limites !");
                                continue;
                            }
                            if (u == v) {
                                System.out.println("Erreur : boucle interdite (u != v) !");
                                continue;
                            }
                            String edge = u + "-" + v;
                            String reverseEdge = v + "-" + u;
                            if (aretesAjoutees.contains(edge)) {
                                System.out.println("Erreur : arrete deja saisie !");
                                continue;
                            }
                            if (aretesAjoutees.contains(reverseEdge)) {
                                System.out.println("Erreur : arrete inverse interdite dans un graphe simple !");
                                continue;
                            }

                            gOriente.ajouterArete(u, v);
                            aretesAjoutees.add(edge);
                            System.out.println("Arête ajoutee : " + u + " -> " + v);
                        }

                        gOriente.afficherMatrice();

                        try {
                            List<Integer> ordre = TriTopologique.triTopologique(gOriente);
                            System.out.println("Ordre topologique : " + ordre);
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }

                        pause(sc);
                        break;

                    case 2:
                        clearScreen();
                        System.out.print("Nombre de sommets : ");
                        int n2 = sc.nextInt();
                        GrapheSimple g2 = new GrapheSimple(n2);
                        Set<String> aretesAjoutees2 = new HashSet<>();

                        while (true) {
                            System.out.println("Ajouter une arrete (u v poids) ou -1 pour terminer :");
                            int u = sc.nextInt();
                            if (u == -1) break;
                            int v = sc.nextInt();
                            int poids = sc.nextInt();

                            if (u < 0 || u >= n2 || v < 0 || v >= n2) {
                                System.out.println("Erreur : sommets hors limites !");
                                continue;
                            }
                            if (u == v) {
                                System.out.println("Erreur : boucle interdite (u != v) !");
                                continue;
                            }
                            if (poids < 0) {
                                System.out.println("Erreur : poids negatif interdit !");
                                continue;
                            }
                            String edge = u + "-" + v;
                            String reverseEdge = v + "-" + u;
                            if (aretesAjoutees2.contains(edge) || aretesAjoutees2.contains(reverseEdge)) {
                                System.out.println("Erreur : arrete deja saisie ou inverse presente !");
                                continue;
                            }

                            g2.ajouterArete(u, v, poids);
                            aretesAjoutees2.add(edge);
                            System.out.println("Arrete ajoutee : " + u + " --" + poids + "-- " + v);
                        }

                        g2.afficherMatrice();

                        System.out.print("Sommet source : ");
                        int source = sc.nextInt();
                        System.out.print("Sommet destination : ");
                        int destination = sc.nextInt();

                        if (source < 0 || source >= n2 || destination < 0 || destination >= n2) {
                            System.out.println("Erreur : sommets source/destination invalides !");
                            pause(sc);
                            break;
                        }

                        if (GrapheSimple.verifierPasDeCycleNegatif(g2, source)) {
                            System.out.println("Erreur : le graphe contient un cycle de poids negatif !");
                            pause(sc);
                            break;
                        }

                        int[] distances = GrapheSimple.dijkstra(g2, source);

                        System.out.println("\nDistances depuis " + source + " :");
                        for (int i = 0; i < distances.length; i++) {
                            if (distances[i] == Integer.MAX_VALUE) {
                                System.out.println("  Vers " + i + " : ∞ (inaccessible)");
                            } else {
                                System.out.println("  Vers " + i + " : " + distances[i]);
                            }
                        }

                        System.out.print("Distance minimale de " + source + " -> " + destination + " : ");
                        if (distances[destination] == Integer.MAX_VALUE) {
                            System.out.println("∞ (inaccessible)");
                        } else {
                            System.out.println(distances[destination]);
                        }

                        pause(sc);
                        break;

                    case 3:
                        clearScreen();
                        arbre = new ArbreBinaire();
                        System.out.println("Arbre binaire construit (vide).");
                        pause(sc);
                        break;

                    case 4:
                        clearScreen();
                        System.out.println("Saisir des valeurs à inserer (-1 pour terminer) :");
                        while (true) {
                            System.out.print("Valeur à insérer : ");
                            int valInsert = sc.nextInt();
                            if (valInsert == -1) {
                                break;
                            }
                            arbre.inserer(valInsert);
                            System.out.println("Valeur inseree.");
                        }
                        pause(sc);
                        break;

                    case 5:
                        clearScreen();
                        System.out.print("Valeur a rechercher : ");
                        int valRech = sc.nextInt();
                        boolean trouve = arbre.rechercher(valRech);
                        System.out.println(trouve ? "Valeur trouvee !" : "Valeur non trouvee.");
                        pause(sc);
                        break;

                    case 6:
                        clearScreen();
                        System.out.print("Valeur à supprimer (avec sous-arbre) : ");
                        int valSupp = sc.nextInt();
                        boolean supprime = arbre.supprimer(valSupp);
                        System.out.println(supprime ? "Suppression reussie." : "Valeur non trouvee.");
                        pause(sc);
                        break;

                    case 7:
                        clearScreen();
                        System.out.print("Parcours prefixe : ");
                        arbre.parcoursPrefixe(arbre.racine);
                        System.out.println();
                        pause(sc);
                        break;

                    case 8:
                        clearScreen();
                        System.out.print("Parcours infixe : ");
                        arbre.parcoursInfixe(arbre.racine);
                        System.out.println();
                        pause(sc);
                        break;

                    case 9:
                        clearScreen();
                        System.out.print("Parcours postfixe : ");
                        arbre.parcoursPostfixe(arbre.racine);
                        System.out.println();
                        pause(sc);
                        break;

                    case 0:
                        System.out.println("Au revoir !");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Choix invalide !");
                        pause(sc);
                }
            }
    }


    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pause(Scanner sc) {
        System.out.println("\nAppuyez sur Entree pour continuer...");
        sc.nextLine(); 
        sc.nextLine(); 
    }

}
