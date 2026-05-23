public class Politicien extends Carte {

    public Politicien() {
        super("Politicien", "images/politicien.png", false);
    }

    // ───── Active POUR : Echanger le Politicien (+ cartes optionnelles) avec le top du deck ─────
    @Override
    public void activePour(Joueur lanceur) {
        System.out.println(lanceur.getNom() + " échange le Politicien et ses cartes choisies !");
        // Partie.java :
        // 1. Récupère les indexes choisis par le joueur via le frontend
        // 2. Vérifie que le Politicien est bien dans les indexes (sinon → mensonge !)
        // 3. Appelle lanceur.echangerCartes(indexes)
        // 4. Met les cartes échangées en défausse
        // 5. Donne le même nombre de cartes depuis le top du deck
    }
}