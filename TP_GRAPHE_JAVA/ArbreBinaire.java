import java.util.LinkedList;
import java.util.Queue;

public class ArbreBinaire {

    Noeud racine;

    public ArbreBinaire() {
        racine = null;
    }

    public void inserer(int val) {
        Noeud nouveau = new Noeud(val);
        if (racine == null) {
            racine = nouveau;
            return;
        }

        Queue<Noeud> queue = new LinkedList<>();
        queue.add(racine);

        while (!queue.isEmpty()) {
            Noeud courant = queue.poll();
            if (courant.gauche == null) {
                courant.gauche = nouveau;
                return;
            } else {
                queue.add(courant.gauche);
            }
            if (courant.droite == null) {
                courant.droite = nouveau;
                return;
            } else {
                queue.add(courant.droite);
            }
        }
    }

    // Recherche (DFS) : retourne vrai si trouv�
    public boolean rechercher(int val) {
        return rechercherRec(racine, val);
    }

    private boolean rechercherRec(Noeud noeud, int val) {
        if (noeud == null) return false;
        if (noeud.valeur == val) return true;
        return rechercherRec(noeud.gauche, val) || rechercherRec(noeud.droite, val);
    }

    // Suppression d'un noeud ET son sous-arbre entier
    // Supprime le premier noeud trouv� avec la valeur donn�e, ainsi que tout son sous-arbre.
    public boolean supprimer(int val) {
        if (racine == null) return false;

        // Si racine est � supprimer
        if (racine.valeur == val) {
            racine = null;
            return true;
        }

        Queue<Noeud> queue = new LinkedList<>();
        queue.add(racine);

        while (!queue.isEmpty()) {
            Noeud courant = queue.poll();

            // V�rifier fils gauche
            if (courant.gauche != null) {
                if (courant.gauche.valeur == val) {
                    courant.gauche = null; // Supprime tout le sous-arbre gauche
                    return true;
                } else {
                    queue.add(courant.gauche);
                }
            }
            // V�rifier fils droit
            if (courant.droite != null) {
                if (courant.droite.valeur == val) {
                    courant.droite = null; // Supprime tout le sous-arbre droit
                    return true;
                } else {
                    queue.add(courant.droite);
                }
            }
        }
        return false; // Valeur non trouv�e
    }
    // Parcours pr�fix� : racine, gauche, droite
    public void parcoursPrefixe(Noeud noeud) {
        if (noeud == null) return;
        System.out.print(noeud.valeur + " ");
        parcoursPrefixe(noeud.gauche);
        parcoursPrefixe(noeud.droite);
    }

    // Parcours infix� : gauche, racine, droite
    public void parcoursInfixe(Noeud noeud) {
        if (noeud == null) return;
        parcoursInfixe(noeud.gauche);
        System.out.print(noeud.valeur + " ");
        parcoursInfixe(noeud.droite);
    }

    // Parcours postfix� : gauche, droite, racine
    public void parcoursPostfixe(Noeud noeud) {
        if (noeud == null) return;
        parcoursPostfixe(noeud.gauche);
        parcoursPostfixe(noeud.droite);
        System.out.print(noeud.valeur + " ");
    }

  }
