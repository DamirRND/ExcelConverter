package com.converter.Autentikacija;

import com.converter.Model.Radnik;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;


@UIScope
@SpringComponent
public class KorisnikMetode {

    public static final String CURRENT_USER_SESSION_ATTRIBUTE_KEY = KorisnikMetode.class
            .getCanonicalName();

    public KorisnikMetode() {
    }

    public static Radnik get() {
        Radnik currentUser = (Radnik) getCurrentRequest().getWrappedSession()
                .getAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY);
        if (currentUser == null) {
            return null;
        } else {
            return currentUser;
        }
    }

    public static void set(Radnik currentUser) {
        if (currentUser == null) {
            getCurrentRequest().getWrappedSession().removeAttribute(
                    CURRENT_USER_SESSION_ATTRIBUTE_KEY);
        } else {
            getCurrentRequest().getWrappedSession().setAttribute(
                    CURRENT_USER_SESSION_ATTRIBUTE_KEY, currentUser);
        }
    }

    private static VaadinRequest getCurrentRequest() {
        VaadinRequest request = VaadinService.getCurrentRequest();
        if (request == null) {
            throw new IllegalStateException(
                    "Sesija je zatvorena!");
        }
        return request;
    }
    
    public static void izbrisiSesiju() {
            getCurrentRequest().getWrappedSession().removeAttribute(
                    CURRENT_USER_SESSION_ATTRIBUTE_KEY);
    }


}
