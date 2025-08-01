
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Graphe graphe = null;
    int choix;

    do {
        clearScreen(); // Efface l'écran avant d'afficher le menu
        System.out.println("\n=== MENU ===");
        System.out.println("1. Creer graphe");
        System.out.println("2. Ajouter arrete");
        System.out.println("3. Afficher matrice");
        System.out.println("4. DFS");
        System.out.println("5. Afficher etats");
        System.out.println("0. Quitter");
        System.out.print("Choix : ");
        choix = sc.nextInt();

        switch (choix) {
            case 1:
                clearScreen();
                System.out.print("Nombre de sommets : ");
                int n = sc.nextInt();
                graphe = new Graphe(n);
                System.out.println("Graphe cree avec " + n + " sommets.");
                pause(sc);
                break;

            case 2:
                clearScreen();
                if (graphe == null) {
                    System.out.println("Creez d'abord le graphe !");
                    pause(sc);
                    break;
                }
                System.out.println("Ajoutez des arrets (entrez -1 pour arreter) :");
                while (true) {
                    System.out.print("Sommet u : ");
                    int u = sc.nextInt();
                    if (u == -1) break;

                    System.out.print("Sommet v : ");
                    int v = sc.nextInt();
                    if (v == -1) break;

                    if (u == v) {
                        System.out.println("Erreur : u et v ne peuvent pas etre identiques !");
                        continue;
                    }
                    if (u < 0 || v < 0 || u >= graphe.getTaille() || v >= graphe.getTaille()) {
                        System.out.println("Erreur : u et v doivent etre entre 0 et " + (graphe.getTaille() - 1));
                        continue;
                    }

                    graphe.ajouterArete(u, v);
                    System.out.println("Arrete (" + u + ", " + v + ") ajoutee !");
                }
                pause(sc);
                break;

            case 3:
                clearScreen();
                if (graphe != null) graphe.afficherMatrice();
                else System.out.println("Creez d'abord le graphe !");
                pause(sc);
                break;

            case 4:
                clearScreen();
                if (graphe != null) {
                    System.out.print("Sommet de depart : ");
                    int start = sc.nextInt();
                    graphe.dfs(start);
                } else System.out.println("Creez d'abord le graphe !");
                pause(sc);
                break;

            case 5:
                clearScreen();
                if (graphe != null) graphe.afficherEtats();
                else System.out.println("Creez d'abord le graphe !");
                pause(sc);
                break;

            case 0:
                System.out.println("Au revoir !");
                break;

            default:
                System.out.println("Choix invalide !");
                pause(sc);
        }
    } while (choix != 0);

    sc.close();
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
