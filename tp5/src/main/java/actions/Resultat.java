package actions;

import facade.exceptions.ResultatImpossibleException;
import modele.Match;

import java.util.ArrayList;
import java.util.Collection;

public class Resultat extends Environnement {
    // variable en entrée
    private long idMatch;
    private String resultat;

    // variables en sortie
    private Match match;



    public Collection<String> getResultatsPossibles() {
        Collection<String>resultatsPossibles = new ArrayList<>();
        resultatsPossibles.add("nul");
        resultatsPossibles.add(match.getEquipe1());
        resultatsPossibles.add(match.getEquipe2());
        return resultatsPossibles;
    }

    public void setIdMatch(long idMatch) {
        this.idMatch = idMatch;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public long getIdMatch() {
        return idMatch;
    }

    public Match getMatch() {
        return match;
    }

    @Override
    public String execute() throws Exception {
        try {
            getFacade().resultatMatch(getUtilisateur().getLogin(), idMatch, resultat);
        } catch (ResultatImpossibleException e) {
            addFieldError("resultat", "resultat "+resultat+" impossible pour ce match.");
            return INPUT;
        }
        match = getFacade().getMatch(idMatch);
        return SUCCESS;
    }
}
