
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TriTopologique {

    public static List<Integer> triTopologique(Graphe g) {
        int n = g.getTaille();
        int[] degreEntrant = new int[n];

        // Calcul du degré entrant pour chaque sommet
        for (int v = 0; v < n; v++) {
            for (int u = 0; u < n; u++) {
                if (g.getMatriceAdj()[u][v] == 1) {
                    degreEntrant[v]++;
                }
            }
        }

        // File des sommets avec degré entrant nul
        Queue<Integer> file = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degreEntrant[i] == 0) {
                file.add(i);
            }
        }

        List<Integer> ordre = new ArrayList<>();

        while (!file.isEmpty()) {
            int u = file.poll();
            ordre.add(u);

            // On enlève u et on décrémente le degré entrant de ses successeurs
            for (int v = 0; v < n; v++) {
                if (g.getMatriceAdj()[u][v] == 1) {
                    degreEntrant[v]--;
                    if (degreEntrant[v] == 0) {
                        file.add(v);
                    }
                }
            }
        }

        if (ordre.size() != n) {
            throw new RuntimeException("Le graphe contient un cycle !");
        }

        return ordre;
    }


}