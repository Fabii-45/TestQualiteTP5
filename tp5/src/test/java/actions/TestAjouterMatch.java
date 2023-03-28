package actions;

import com.opensymphony.xwork2.ActionProxy;
import facade.FacadeParis;
import facade.exceptions.UtilisateurNonAdminException;
import modele.Match;
import modele.Utilisateur;
import org.apache.struts2.StrutsJUnit4TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TestAjouterMatch extends StrutsJUnit4TestCase {


    private static String etendreSiNecessaire(int x) {
        if (x <10) {
            return "0"+x;
        }
        else
            return Integer.toString(x);
    }

    @Test
    public void testOk() throws Exception {

        LocalDateTime now = LocalDateTime.now();

        String annee = etendreSiNecessaire(now.getYear()+1);
        String mois = etendreSiNecessaire(now.getMonthValue()+1);
        String jour = etendreSiNecessaire(now.getDayOfMonth());
        String heures = etendreSiNecessaire(now.getHour());
        String minutes = etendreSiNecessaire(now.getMinute());
        String notreDate = annee+"-"+mois+"-"+jour+" "+heures+":"+minutes;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime quand = LocalDateTime.parse(notreDate, formatter);

        String football = "football";
        request.addParameter("sport", football);
        String psg = "PSG";
        request.addParameter("equipe1", psg);
        String om = "OM";
        request.addParameter("equipe2", om);
        request.addParameter("quand",notreDate);

        String pseudo = "Yohan";
        long idMatch = 1;

        Map<String,Object> sessions = new HashMap<>();
        Map<String,Object> applications = new HashMap<>();

        ActionProxy actionProxy = getActionProxy("/ajouterMatch");
        actionProxy.getInvocation().getInvocationContext().setApplication(applications);
        actionProxy.getInvocation().getInvocationContext().setSession(sessions);

        FacadeParis facadeParis = mock(FacadeParis.class);
        Utilisateur utilisateur = mock(Utilisateur.class);
        Match match = mock(Match.class);

        doReturn(idMatch).when(facadeParis).ajouterMatch(pseudo,football,psg,om,quand);
        doReturn(match).when(facadeParis).getMatch(idMatch);
        doReturn(pseudo).when(utilisateur).getLogin();
        //EasyMock.expect(facadeParis.ajouterMatch(pseudo,football,psg,om,quand)).andReturn(idMatch);
        //EasyMock.expect(facadeParis.getMatch(idMatch)).andReturn(match);
        //EasyMock.expect(utilisateur.getLogin()).andReturn(pseudo);
        //EasyMock.replay(facadeParis,match,utilisateur);

        sessions.put("user",utilisateur);
        applications.put("facade",facadeParis);

        String resultat = actionProxy.execute();

        AjouterMatch actionExecutee = (AjouterMatch) actionProxy.getAction();

        Assert.assertEquals(resultat,"success");
        Assert.assertTrue(actionExecutee.getMatch()==match);

    }



    @Test
    public void testKODateAnterieure() throws Exception {

        LocalDateTime now = LocalDateTime.now();

        String annee = etendreSiNecessaire(now.getYear()-1);
        String mois = etendreSiNecessaire(now.getMonthValue()+1);
        String jour = etendreSiNecessaire(now.getDayOfMonth());
        String heures = etendreSiNecessaire(now.getHour());
        String minutes = etendreSiNecessaire(now.getMinute());
        String notreDate = annee+"-"+mois+"-"+jour+" "+heures+":"+minutes;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime quand = LocalDateTime.parse(notreDate, formatter);

        String football = "football";
        request.addParameter("sport", football);
        String psg = "PSG";
        request.addParameter("equipe1", psg);
        String om = "OM";
        request.addParameter("equipe2", om);
        request.addParameter("quand",notreDate);

        String pseudo = "Yohan";
        long idMatch = 1;

        Map<String,Object> sessions = new HashMap<>();
        Map<String,Object> applications = new HashMap<>();

        ActionProxy actionProxy = getActionProxy("/ajouterMatch");
        actionProxy.getInvocation().getInvocationContext().setApplication(applications);
        actionProxy.getInvocation().getInvocationContext().setSession(sessions);

        FacadeParis facadeParis = mock(FacadeParis.class);
        Utilisateur utilisateur = mock(Utilisateur.class);
        Match match = mock(Match.class);

        sessions.put("user",utilisateur);
        applications.put("facade",facadeParis);

        String resultat = actionProxy.execute();

        AjouterMatch actionExecutee = (AjouterMatch) actionProxy.getAction();

        Assert.assertEquals(resultat,"input");

        Assert.assertTrue(actionExecutee.getFieldErrors().get("quand").size()==1);

    }

}