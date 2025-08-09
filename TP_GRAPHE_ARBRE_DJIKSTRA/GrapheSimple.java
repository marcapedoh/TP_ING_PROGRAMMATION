import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrapheSimple extends Graphe{
    
     public GrapheSimple(int n) {
        super(n);
    }

    //spécification du graphe non oriente
    @Override
    public void ajouterArete(int u, int v) {
        if (u >= 0 && v >= 0 && u < taille && v < taille) {
            matriceAdj[u][v] = 1;
            matriceAdj[v][u] = 1;
            sommets.get(u).compteurSuccesseurs++;
        }
    }

     public void ajouterArete(int u, int v,int poids) {
        if (u >= 0 && v >= 0 && u < taille && v < taille) {
            matriceAdj[u][v] = poids;
            matriceAdj[v][u] = poids;
            sommets.get(u).compteurSuccesseurs++;
        }
    }

    public static int[] dijkstra(Graphe g, int source) {
        int n = g.getTaille();
        int[][] poids = g.getMatriceAdj();
        int[] dist = new int[n];
        boolean[] visite = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = minDistance(dist, visite);
            visite[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visite[v] && poids[u][v] > 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + poids[u][v] < dist[v]) {
                    dist[v] = dist[u] + poids[u][v];
                }
            }
        }

        return dist;
    }

    private static int minDistance(int[] dist, boolean[] visite) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!visite[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static boolean verifierPasDeCycleNegatif(Graphe g, int source) {
        int n = g.getTaille();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // 1. Extraire les arêtes une seule fois
        List<int[]> aretes = new ArrayList<>();
        int[][] poids = g.getMatriceAdj();
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (poids[u][v] != 0) {
                    aretes.add(new int[]{u, v, poids[u][v]});
                }
            }
        }

        // 2. Relaxation sur n-1 itérations
        for (int k = 0; k < n - 1; k++) {
            for (int[] e : aretes) {
                int u = e[0], v = e[1], w = e[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // 3. Détection de cycle négatif
        for (int[] e : aretes) {
            int u = e[0], v = e[1], w = e[2];
            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                return true;
            }
        }

        return false;
    }


   
}
