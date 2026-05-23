public class BusinessWoman extends Carte {

    public BusinessWoman() {
        super("BusinessWoman", "images/businesswoman.png",false);
    }

    @Override
    public void activePour(Joueur lanceur) {
        lanceur.gagnerArgent(4);
        System.out.println(lanceur.getNom() + " gagne 4 DT !");
    }
}