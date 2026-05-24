public class Mentir {

    public static void mentir(String typeChoisi, Joueur lanceur, Joueur cible) {
        
        // Partie.java vérifie d'abord que le joueur ne possède pas ce type en main
        // sinon → considéré comme mensonge invalide
        
        Carte carteImpersonnee;
        
        switch (typeChoisi) {
            case "BusinessWoman" -> carteImpersonnee = new BusinessWoman();
            case "Voleur"        -> carteImpersonnee = new Voleur();
            case "Terrorist"     -> carteImpersonnee = new Terrorist();
            case "Politicien"    -> carteImpersonnee = new Politicien();
            case "Policier"      -> carteImpersonnee = new Policier();
            case "Colonel"       -> carteImpersonnee = new Colonel();
            case "AgentFiscal"   -> carteImpersonnee = new AgentFiscal();
            default -> {
                System.out.println("Type de carte invalide !");
                return;
            }
        }

        // jouer le pouvoir de la carte impersonnée
        if (cible == null) {
            carteImpersonnee.activePour(lanceur);
        } else {
            carteImpersonnee.activeContre(lanceur, cible);
        }
    }
}