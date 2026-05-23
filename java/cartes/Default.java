public class Default extends Carte {

    private static final int COUT_CONTRE = 7;
    private static final int COUT_PASSIVE = 9;

    public Default() {
        super("Default", "images/default.png", false);
    }

    // ───── Active POUR : Gagner 2 DT si carte étatique en main, sinon 1 DT ─────
    @Override
    public void activePour(Joueur lanceur) {
        boolean possedeCibleEtatique = lanceur.getMain()
                .stream()
                .anyMatch(Carte::isEtatique);

        if (possedeCibleEtatique) {
            lanceur.gagnerArgent(2);
            System.out.println(lanceur.getNom() + " gagne 2 DT (carte étatique possédée) !");
        } else {
            lanceur.gagnerArgent(1);
            System.out.println(lanceur.getNom() + " gagne 1 DT !");
        }
    }

    // ───── Active CONTRE : Payer 7 DT pour forcer la cible à tuer une carte ─────
    @Override
    public void activeContre(Joueur lanceur, Joueur cible) {
        if (!lanceur.payer(COUT_CONTRE)) return;
        System.out.println(lanceur.getNom() + " paie 7 DT et force "
                         + cible.getNom() + " à tuer une de ses cartes !");
        // Partie.java → cible.tuerCarte(index)
    }

    // ───── Passive : Payer 9 DT pour bloquer n'importe quelle tuerie ─────
    @Override
    public void passive1(Joueur lanceur, Joueur cible) {
        if (!lanceur.payer(COUT_PASSIVE)) return;
        System.out.println(lanceur.getNom() + " paie 9 DT et bloque la tuerie sur "
                         + cible.getNom() + " !");
        // Partie.java → annule l'action de tuerie en cours
    }
}