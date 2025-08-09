public class Sommet {
    int id;
    Etat etat;
    int compteurSuccesseurs;

    public Sommet(int id) {
        this.id = id;
        this.etat = Etat.NON_EXPLORE;
        this.compteurSuccesseurs = 0;
    }

    public void decrementer() {
        if (compteurSuccesseurs > 0) {
            compteurSuccesseurs--;
        }
    }

    public void setEtat(Etat e) {
        this.etat = e;
    }

    @Override
    public String toString() {
        return "Sommet " + id + " [" + etat + ", reste " + compteurSuccesseurs + "]";
    }
}