public class Voleur extends Carte {

    public Voleur() {
        super("Voleur", "images/voleur.png",false);
    }

    @Override
    public void activeContre(Joueur lanceur, Joueur cible) {
        int montantVole = Math.min(2, cible.getArgent());
        cible.perdreArgent(montantVole);
        lanceur.gagnerArgent(montantVole);
        System.out.println(lanceur.getNom() + " vole " + montantVole + " DT à " + cible.getNom());
    }

    @Override
    public void passive1(Joueur lanceur, Joueur cible) {
        System.out.println(lanceur.getNom() + " bloque le vol sur " + cible.getNom() + " !");
    }
}