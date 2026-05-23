public class Joueur {
    private String nom;
    private List<Carte> main;       // cartes vivantes
    private List<Carte> mortes;     // cartes éliminées
    private int argent;

    private static final int ARGENT_MIN = 0;
    private static final int ARGENT_MAX = 12;

    public Joueur(String nom) {
        this.nom = nom;
        this.main = new ArrayList<>();
        this.mortes = new ArrayList<>();
        this.argent = 2;
    }

    // ───── Tuer une carte (le joueur choisit laquelle) ─────
    public void tuerCarte(int index) {
    if (index < 0 || index >= main.size()) {
        System.out.println("Index invalide !");
        return;
    }
    Carte carte = main.remove(index);
    mortes.add(carte);
    System.out.println(nom + " : la carte " + carte.getNom() + " est morte !");
    }

    // ───── Vérifier si le joueur est éliminé ─────
    public boolean estElimine() {
        return main.isEmpty();
    }

    // ───── Argent ─────
    public void gagnerArgent(int montant) {
        this.argent = Math.min(this.argent + montant, ARGENT_MAX);
    }

    public void perdreArgent(int montant) {
        this.argent = Math.max(this.argent - montant, ARGENT_MIN);
    }

    public boolean payer(int montant) {
        if (this.argent - montant < ARGENT_MIN) {
            System.out.println(nom + " : fonds insuffisants !");
            return false;
        }
        this.argent -= montant;
        return true;
    }

    // ───── Getters ─────
    public String getNom() { return nom; }
    public int getArgent() { return argent; }
    public List<Carte> getMain() { return main; }
    public boolean estVivant() { return !estElimine(); }
}