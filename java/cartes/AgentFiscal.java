public class AgentFiscal extends Carte {

    public AgentFiscal() {
        super("Agent Fiscal", "images/agentfiscal.png", true); // étatique = true
    }

    // ───── Active CONTRE : Voler 1 DT si la cible possède 7 DT ou plus ─────
    @Override
    public void activeContre(Joueur lanceur, Joueur cible) {
        if (cible.getArgent() >= 7) {
            cible.perdreArgent(1);
            lanceur.gagnerArgent(1);
            System.out.println(lanceur.getNom() + " vole 1 DT à " + cible.getNom() + " !");
        } else {
            System.out.println(cible.getNom() + " possède moins de 7 DT, action impossible !");
        }
    }

    // ───── Passive 1 : Si une BusinessWoman ramasse 4 DT, lui enlever 1 DT ─────
    @Override
    public void passive1(Joueur lanceur, Joueur cible) {
        cible.perdreArgent(1);
        lanceur.gagnerArgent(1);
        System.out.println(lanceur.getNom() + " taxe 1 DT à la BusinessWoman " + cible.getNom() + " !");
        // Partie.java :
        // Dans la fenêtre de 5 secondes après activePour d'une BusinessWoman
        // → appelle cette passive
    }

    // ───── Passive 2 : Réduire le gain de 2 DT (Default étatique) à 1 DT ─────
    @Override
    public void passive2(Joueur lanceur, Joueur cible) {
        cible.perdreArgent(1); // le 1 DT perdu disparaît, personne ne le prend
        System.out.println(lanceur.getNom() + " réduit le gain étatique de " + cible.getNom() + " de 2 à 1 DT !");
        // Partie.java :
        // Dans la fenêtre de 5 secondes après activePour de Default (avec carte étatique)
        // → appelle cette passive
    }
}