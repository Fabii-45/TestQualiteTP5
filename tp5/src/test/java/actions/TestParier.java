package actions;

import com.opensymphony.xwork2.ActionProxy;
import facade.FacadeParis;
import facade.exceptions.ResultatImpossibleException;
import modele.Match;
import modele.Pari;
import modele.Utilisateur;
import org.apache.struts2.StrutsJUnit4TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class TestParier extends StrutsJUnit4TestCase {

    @Test
    public void testOk() throws Exception {
        String idMatch = "1";
        request.addParameter("idMatch", idMatch);

        String psg = "PSG";
        request.addParameter("resultat", psg);

        String montant = "15";
        request.addParameter("montant", montant);

        //pseudo en variable de session
        String pseudo = "Fabi";
        long idPari = 10;

        Map<String, Object> sessions = new HashMap<>();
        Map<String, Object> applications = new HashMap<>();

        ActionProxy actionProxy = getActionProxy("/parier");
        actionProxy.getInvocation().getInvocationContext().setApplication(applications);
        actionProxy.getInvocation().getInvocationContext().setSession(sessions);


        FacadeParis facadeParis = mock(FacadeParis.class);
        Utilisateur utilisateur = mock(Utilisateur.class);
        Match match = mock(Match.class);
        Pari pari = mock(Pari.class);

        doReturn(match).when(facadeParis).getMatch(Long.parseLong(idMatch));
        doReturn(idPari).when(facadeParis).parier(pseudo, Long.parseLong(idMatch), psg, Double.parseDouble(montant));
        doReturn(pari).when(facadeParis).getPari(idPari);
        doReturn(match).when(pari).getMatch();
        doReturn(pseudo).when(utilisateur).getLogin();

        sessions.put("user", utilisateur);
        applications.put("facade", facadeParis);

        String resultat = actionProxy.execute();
        Parier actionExecutee = (Parier) actionProxy.getAction();

        Assert.assertEquals(resultat, "success");
        Assert.assertTrue(actionExecutee.getPari() == pari);
    }

    @Test
    public void testKOMontantNegatif() throws Exception {
        String idMatch = "1";
        request.addParameter("idMatch", idMatch);

        String psg = "PSG";
        request.addParameter("resultat", psg);

        String montant = "-15";
        request.addParameter("montant", montant);

        Map<String, Object> sessions = new HashMap<>();
        Map<String, Object> applications = new HashMap<>();

        ActionProxy actionProxy = getActionProxy("/parier");
        actionProxy.getInvocation().getInvocationContext().setApplication(applications);
        actionProxy.getInvocation().getInvocationContext().setSession(sessions);

        FacadeParis facadeParis = mock(FacadeParis.class);
        Utilisateur utilisateur = mock(Utilisateur.class);
        Match match = mock(Match.class);

        doReturn(match).when(facadeParis).getMatch(Long.parseLong(idMatch));

        sessions.put("user", utilisateur);
        applications.put("facade", facadeParis);

        String resultat = actionProxy.execute();
        Parier actionExecutee = (Parier) actionProxy.getAction();

        Assert.assertEquals(resultat,"input");
        Assert.assertTrue(actionExecutee.getFieldErrors().get("montant").size()==1);
    }

    @Test
    public void testKOMatchInexistant() throws Exception {
        String idMatch = "1";
        request.addParameter("idMatch", idMatch);

        String psg = "PSG";
        request.addParameter("resultat", psg);

        String montant = "15";
        request.addParameter("montant", montant);

        Map<String, Object> sessions = new HashMap<>();
        Map<String, Object> applications = new HashMap<>();

        ActionProxy actionProxy = getActionProxy("/parier");
        actionProxy.getInvocation().getInvocationContext().setApplication(applications);
        actionProxy.getInvocation().getInvocationContext().setSession(sessions);

        FacadeParis facadeParis = mock(FacadeParis.class);
        Utilisateur utilisateur = mock(Utilisateur.class);

        doReturn(null).when(facadeParis).getMatch(Long.parseLong(idMatch));

        sessions.put("user", utilisateur);
        applications.put("facade", facadeParis);

        String resultat = actionProxy.execute();
        Parier actionExecutee = (Parier) actionProxy.getAction();

        Assert.assertEquals(resultat,"input");
        Assert.assertTrue(actionExecutee.getFieldErrors().get("idMatch").size()==1);
    }

    //Dernier test qui passe pas lol
    @Test
    public void testResultatIncoherent() throws Exception {
        String idMatch = "1";
        request.addParameter("idMatch", idMatch);

        String psg = "PSG";
        request.addParameter("resultat", psg);

        String montant = "15";
        request.addParameter("montant", montant);

        //pseudo en variable de session
        String pseudo = "Fabi";

        Map<String, Object> sessions = new HashMap<>();
        Map<String, Object> applications = new HashMap<>();

        ActionProxy actionProxy = getActionProxy("/parier");
        actionProxy.getInvocation().getInvocationContext().setApplication(applications);
        actionProxy.getInvocation().getInvocationContext().setSession(sessions);


        FacadeParis facadeParis = mock(FacadeParis.class);
        Utilisateur utilisateur = mock(Utilisateur.class);
        Match match = mock(Match.class);

        doReturn(match).when(facadeParis).getMatch(Long.parseLong(idMatch));
        doThrow(ResultatImpossibleException.class).when(facadeParis).parier(pseudo, Long.parseLong(idMatch), "OM", Double.parseDouble(montant));


        sessions.put("user", utilisateur);
        applications.put("facade", facadeParis);

        String resultat = actionProxy.execute();
        Parier actionExecutee = (Parier) actionProxy.getAction();

        Assert.assertEquals(resultat,"input");
        Assert.assertTrue(actionExecutee.getFieldErrors().get("resultat").size()==1);
    }


}
