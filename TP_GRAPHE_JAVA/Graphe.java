
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Graphe {
    private final List<Sommet> sommets;
    private int[][] matriceAdj;
    private int taille;


   
    public Graphe(int n) {
        this.taille = n;
        sommets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sommets.add(new Sommet(i));
        }
        matriceAdj = new int[n][n];
    }

    public void ajouterArete(int u, int v) {
        if (u >= 0 && v >= 0 && u < taille && v < taille) {
            matriceAdj[u][v] = 1;
            matriceAdj[v][u] = 1;
            sommets.get(u).compteurSuccesseurs++;
        }
    }

    public void afficherMatrice() {
        System.out.println("Matrice d'adjacence :");
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                System.out.print(matriceAdj[i][j] + " ");
            }
            System.out.println();
        }
    }

     public int getTaille(){
        return this.taille;
    }
    public void afficherEtats() {
        for (Sommet s : sommets) {
            System.out.println(s);
        }
    }

    public void dfs(int start) {
        System.out.println("\n--- Début DFS ---");
        Set<Integer> visite = new HashSet<>();
        dfsRec(start, visite);
        System.out.println("--- Fin DFS ---");
    }

    private void dfsRec(int sommet, Set<Integer> visite) {
        Sommet s = sommets.get(sommet);
        s.setEtat(Etat.OUVERT);
        System.out.println("Visite de " + s);
        visite.add(sommet);

        for (int i = 0; i < taille; i++) {
            if (matriceAdj[sommet][i] == 1) {
                s.decrementer();
                if (!visite.contains(i)) {
                    dfsRec(i, visite);
                }
            }
        }

        if (s.compteurSuccesseurs == 0) {
            s.setEtat(Etat.FERME);
            System.out.println("Fermeture de " + s);
        }
    }
}