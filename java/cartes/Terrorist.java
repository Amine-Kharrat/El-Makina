public class Terrorist extends Carte {

    private static final int COUT = 3;

    public Terrorist() {
        super("Terrorist", "images/terrorist.png",false);
    }

    // ───── Active CONTRE : Payer 3 DT pour forcer la cible à tuer une carte ─────
    @Override
    public void activeContre(Joueur lanceur, Joueur cible) {
        if (!lanceur.payer(COUT)) return;

        System.out.println(lanceur.getNom() + " paie 3 DT et force "
            + cible.getNom() + " à tuer une de ses cartes !");

        // Partie.java reçoit le click du frontend → récupère l'index → appelle :
        // cible.tuerCarte(indexChoisiParLaCible);
    }
}