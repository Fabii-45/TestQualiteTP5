package actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
import facade.FacadeParis;
import facade.FacadeParisStaticImpl;
import modele.Utilisateur;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;
import java.util.Objects;

public class Environnement extends ActionSupport implements  SessionAware, ApplicationAware {

    private Map<String, Object> session;

    private FacadeParis facade;



    public FacadeParis getFacade() {
        return facade;
    }

    public Utilisateur getUtilisateur() {
        return (Utilisateur) session.get("user");
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        session.put("user", utilisateur);
    }

    public void unsetUtilisateur() {
        session.remove("user");
    }


    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }

    @Override
    public void setApplication(Map<String, Object> map) {
        this.facade = (FacadeParis) map.get("facade");
        if (Objects.isNull(facade)){
            this.facade = new FacadeParisStaticImpl();
            map.put("facade",facade);
        }


    }
}
