public abstract class Carte {

    // ───── Attributs ─────
    protected String nom;
    protected String image;
    protected boolean etatique;

    // ───── Constructeur ─────
    public Carte(String nom, String image ,boolean etatique) {
        this.nom = nom;
        this.image = image;
        this.etatique = etatique;
    }

    // ───── Active POUR (agit sur soi-même) ─────
    public void activePour(Joueur lanceur) {}

    // ───── Active CONTRE (cible un autre joueur) ─────
    public void activeContre(Joueur lanceur, Joueur cible) {}

    // ───── Passive (toujours contre, deux joueurs impliqués) ─────
    public void passive1(Joueur lanceur, Joueur cible) {}
    public void passive2(Joueur lanceur, Joueur cible) {}

    // ───── Getters ─────
    public String getNom() { return nom; }
    public String getImage() { return image; }
    public boolean isEtatique() { return etatique; }
}