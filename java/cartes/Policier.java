public class Policier extends Carte {

    public Policier() {
        super("Policier", "images/policier.png", true);
    }

    // ───── Active CONTRE : Voir une carte de la cible par index ─────
    @Override
    public void activeContre(Joueur lanceur, Joueur cible) {
        System.out.println(lanceur.getNom() + " inspecte une carte de " + cible.getNom() + " !");
        // Partie.java :
        // 1. Récupère l'index choisi par le lanceur via le frontend
        // 2. Affiche la carte UNIQUEMENT au lanceur
    }

    // ───── Passive : Bloquer un autre Policier de voir les cartes ─────
    @Override
    public void passive1(Joueur lanceur, Joueur cible) {
        System.out.println(lanceur.getNom() + " bloque l'inspection de " + cible.getNom() + " !");
        // Partie.java :
        // Dans la fenêtre de 5 secondes après l'activeContre d'un Policier
        // si un autre joueur active cette passive → inspection annulée
    }
}