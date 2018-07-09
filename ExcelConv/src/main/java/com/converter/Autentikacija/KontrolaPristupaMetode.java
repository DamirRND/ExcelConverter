package com.converter.Autentikacija;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Model.Radnik;
import com.converter.Service.RadnikService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class KontrolaPristupaMetode implements KontrolaPristupa{
	private static final long serialVersionUID = 1L;
	
	private RadnikService radnikser;
	
	@Autowired
	public KontrolaPristupaMetode(RadnikService radnikser) {
		this.radnikser = radnikser;
	}
	
	@Override
    public void signIn(String username, String password) {
		Radnik radnik = radnikser.findOne(username, password);
		
		if(radnik!=null) {
			KorisnikMetode.set(radnik);
		}else {
		}
    }

    @Override
    public boolean isUserSignedIn() {
    	return KorisnikMetode.get() != null ;
    }

    @Override
    public boolean isUserInRole(String role) {
        if ("Administrator".equals(role)) {
            return getImeKorisnika().equals("admin");
        }else if("Napredni korisnik".equals(role)) {
        	return getImeKorisnika().equals("nprkorisnik");
        }else if("Korisnik".equals(role)) {
        	return getImeKorisnika().equals("korisnik");
        }

        return true;
    }

    @Override
    public String getImeKorisnika() {
        return KorisnikMetode.get().getNaziv();
    }
    
    @Override
    public int getUloguKorisnika() {
    	return KorisnikMetode.get().getKorgrupa().getNivo();
    }

    @Override
    public Radnik getRadnik() {
    	return KorisnikMetode.get();
    }
    
    @Override
    public void sessionDestroy() {
    	KorisnikMetode.izbrisiSesiju();
    }
}
