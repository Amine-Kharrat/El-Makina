import java.util.*;

public class Partie {

    // ───── Attributs ─────
    private List<Joueur> joueurs;
    private List<Joueur> joueursElimines;
    private List<Carte> pioche;
    private int indexJoueurCourant;
    private boolean partieTerminee;

    // ───── Constructeur ─────
    public Partie(List<String> nomsJoueurs) {
        this.joueurs = new ArrayList<>();
        this.joueursElimines = new ArrayList<>();
        this.pioche = new ArrayList<>();
        this.indexJoueurCourant = 0;
        this.partieTerminee = false;

        initialiserJoueurs(nomsJoueurs);
        initialiserPioche();
        melangerPioche();
        distribuerCartes();
    }

    // ───── Setup : Créer les joueurs ─────
    private void initialiserJoueurs(List<String> noms) {
        if (noms.size() < 2 || noms.size() > 6) {
            throw new IllegalArgumentException("Le nombre de joueurs doit être entre 2 et 6 !");
        }
        for (String nom : noms) {
            joueurs.add(new Joueur(nom));
        }
    }

    // ───── Setup : Créer les 21 cartes (3 copies de chaque type) ─────
    private void initialiserPioche() {
        for (int i = 0; i < 3; i++) {
            pioche.add(new BusinessWoman());
            pioche.add(new Voleur());
            pioche.add(new Terrorist());
            pioche.add(new Default());
            pioche.add(new Politicien());
            pioche.add(new Policier());
            pioche.add(new Colonel());
            pioche.add(new AgentFiscal());
        }
    }

    // ───── Setup : Mélanger la pioche ─────
    private void melangerPioche() {
        Collections.shuffle(pioche);
    }

    // ───── Setup : Distribuer 2 ou 3 cartes selon le nombre de joueurs ─────   verif
    private void distribuerCartes() {
        // 2-3 joueurs → 3 cartes, 4-6 joueurs → 2 cartes
        int nbCartes = joueurs.size() <= 3 ? 3 : 2;  // changer

        for (Joueur joueur : joueurs) {
            List<Carte> cartesDistribuees = new ArrayList<>(pioche.subList(0, nbCartes));
            pioche.subList(0, nbCartes).clear();
            joueur.getMain().addAll(cartesDistribuees);
        }
    }

    // ───── Tour : Récupérer le joueur courant ─────
    public Joueur getJoueurCourant() {
        return joueurs.get(indexJoueurCourant);
    }

    // ───── Tour : Jouer une activePour ─────
    public void jouerActivePour(Carte carte) {
        Joueur lanceur = getJoueurCourant();
        carte.activePour(lanceur);
        lanceur.setMenti(null); // pas de mensonge
    }

    // ───── Tour : Jouer une activeContre ─────
    public void jouerActiveContre(Carte carte, Joueur cible) {
        Joueur lanceur = getJoueurCourant();
        carte.activeContre(lanceur, cible);
        lanceur.setMenti(null); // pas de mensonge
    }

    // ───── Tour : Jouer une passive ─────
    public void jouerPassive1(Carte carte, Joueur cible) {
        Joueur lanceur = getJoueurCourant();
        carte.passive1(lanceur, cible);
    }

    public void jouerPassive2(Carte carte, Joueur cible) {
        Joueur lanceur = getJoueurCourant();
        carte.passive2(lanceur, cible);
    }

    // ───── Tour : Mentir ───── verif
    public void jouerMentir(String typeChoisi, Joueur cible) {
        Joueur lanceur = getJoueurCourant();

        // vérifier que le joueur ne possède pas ce type en main
        boolean possedeType = lanceur.getMain().stream()
                .anyMatch(c -> c.getClass().getSimpleName().equals(typeChoisi));

        if (possedeType) {
            System.out.println("Impossible de mentir avec un type que vous possédez !");
            return;
        }

        // stocker le mensonge
        Carte carteImpersonnee = creerCarte(typeChoisi);
        lanceur.setMenti(carteImpersonnee);

        // exécuter le pouvoir
        Mentir.mentir(typeChoisi, lanceur, cible);
    }

    // ───── Accusation ─────
    public void accuser(Joueur accusateur, Joueur accuse, int indexCarte) {
        if (accuse.aMenti()) {
            // l'accusé mentait → il perd une carte
            System.out.println(accuse.getNom() + " mentait ! Il perd une carte !");
            accuse.tuerCarte(indexCarte); // verif index comment recuperer ?
        } else {
            // l'accusé disait la vérité → l'accusateur perd une carte
            System.out.println(accusateur.getNom() + " avait tort ! Il perd une carte !");
            accusateur.tuerCarte(indexCarte);
        }
        accuse.resetMensonge();
        verifierElimination();
    }

    // ───── Echange Politicien ─────
    public void echangerCartesPoliticien(Joueur lanceur, List<Integer> indexes) {
        // vérifier que le Politicien est bien dans les indexes
        boolean politicienPresent = indexes.stream()
                .anyMatch(i -> lanceur.getMain().get(i) instanceof Politicien);

        if (!politicienPresent) {
            System.out.println("Le Politicien doit être présent dans les cartes échangées !");
            // considéré comme mensonge → Partie.java gère l'accusation
            return;
        }

        int i = indexes.size();
        List<Carte> nouvellesCartes = new ArrayList<>(pioche.subList(0, i));
        pioche.subList(0, i).clear();
        List<Carte> cartesEchangees = lanceur.echangerCartes(indexes, nouvellesCartes); // verif n'extiste pas dans la classe lanceur
        pioche.addAll(cartesEchangees); // retournent à la fin de la pioche
    }

    // ───── Fin de tour ─────
    public void finDeTour() {
        Joueur joueurCourant = getJoueurCourant();
        joueurCourant.resetMensonge(); // reset mensonge
        verifierElimination();         // vérifier les éliminations
        passerAuJoueurSuivant();       // tour suivant
    }

    // ───── Passer au joueur suivant ─────
    private void passerAuJoueurSuivant() {
        indexJoueurCourant = (indexJoueurCourant + 1) % joueurs.size();
    }

    // ───── Vérifier les éliminations ─────
    private void verifierElimination() {
        Iterator<Joueur> iterator = joueurs.iterator();
        while (iterator.hasNext()) {
            Joueur joueur = iterator.next();
            if (joueur.estElimine()) {
                System.out.println(joueur.getNom() + " est éliminé !");
                joueursElimines.add(joueur);
                iterator.remove();
            }
        }
        verifierFinDePartie();
    }

    // ───── Vérifier la fin de partie ─────
    private void verifierFinDePartie() {
        if (joueurs.size() == 1) {
            partieTerminee = true;
            System.out.println("🏆 " + joueurs.get(0).getNom() + " est le vainqueur !");
        }
    }

    // ───── Utilitaire : Créer une carte par type ─────
    private Carte creerCarte(String type) {
        return switch (type) {
            case "BusinessWoman" -> new BusinessWoman();
            case "Voleur"        -> new Voleur();
            case "Terrorist"     -> new Terrorist();
            case "Default"       -> new Default();
            case "Politicien"    -> new Politicien();
            case "Policier"      -> new Policier();
            case "Colonel"       -> new Colonel();
            case "AgentFiscal"   -> new AgentFiscal();
            default -> throw new IllegalArgumentException("Type inconnu : " + type);
        };
    }

    // ───── Getters ─────
    public List<Joueur> getJoueurs() { return joueurs; }
    public List<Carte> getPioche() { return pioche; }
    public boolean isPartieTerminee() { return partieTerminee; }
    public Joueur getVainqueur() { return partieTerminee ? joueurs.get(0) : null; }
}