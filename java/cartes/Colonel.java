public class Colonel extends Carte {

    private static final int COUT = 4;

    public Colonel() {
        super("Colonel", "images/colonel.png", true); // étatique = true, militaire !
    }

    // ───── Active CONTRE : Deviner une carte de la cible ─────
    @Override
    public void activeContre(Joueur lanceur, Joueur cible) {
        if (!lanceur.payer(COUT)) return;
        System.out.println(lanceur.getNom() + " paie 4 DT et tente de deviner une carte de " + cible.getNom() + " !");
        // Partie.java :
        // 1. Récupère le nom de la carte devinée par le lanceur via le frontend
        // 2. Cherche la première occurrence dans cible.getMain()
        //    → trouvée : cible.tuerCarte(index de la première occurrence)
        //    → non trouvée : cible.gagnerArgent(COUT)
    }

    // ───── Passive : Bloquer uniquement les attaques du Terrorist ─────
    @Override
    public void passive1(Joueur lanceur, Joueur cible) {
        System.out.println(lanceur.getNom() + " bloque l'attaque du Terrorist sur " + cible.getNom() + " !");
        // Partie.java :
        // Dans la fenêtre de 5 secondes après activeContre d'un Terrorist
        // si cette passive est activée → action du Terrorist annulée
    }
}