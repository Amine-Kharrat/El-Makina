public class Joueur {
    private String nom;
    private List<Carte> main;       // cartes vivantes
    private List<Carte> mortes;     // cartes éliminées
    private int argent;
    private boolean aMenti = false;
    private Carte carteImpersonnee = null; // pour savoir quelle carte il a prétendu jouer


    private static final int ARGENT_MIN = 0;
    private static final int ARGENT_MAX = 12;

    public Joueur(String nom) {
        this.nom = nom;
        this.main = new ArrayList<>();
        this.mortes = new ArrayList<>();
        this.argent = 2;
    }

    public void setMenti(Carte carte) {
    this.aMenti = true;
    this.carteImpersonnee = carte;
    }

    public void resetMensonge() {
        this.aMenti = false;
        this.carteImpersonnee = null;
    }

    public boolean aMenti() { return aMenti; }
    public Carte getCarteImpersonnee() { return carteImpersonnee; }

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

    public List<Carte> echangerCartes(List<Integer> indexes, List<Carte> nouvellesCartes) {
        List<Carte> cartesEchangees = new ArrayList<>();
        indexes.sort(Collections.reverseOrder());
        for (int index : indexes) {
            if (index >= 0 && index < main.size()) {
                cartesEchangees.add(main.remove(index));
            }
        }
        main.addAll(nouvellesCartes);
        return cartesEchangees;
    }

    // ───── Getters ─────
    public String getNom() { return nom; }
    public int getArgent() { return argent; }
    public List<Carte> getMain() { return main; }
    public boolean estVivant() { return !estElimine(); }
}