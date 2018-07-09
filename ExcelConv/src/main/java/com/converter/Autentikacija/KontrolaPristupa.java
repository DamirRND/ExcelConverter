package com.converter.Autentikacija;

import java.io.Serializable;

import com.converter.Model.Radnik;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;


@UIScope
@SpringComponent
public interface KontrolaPristupa extends Serializable{

	public void signIn(String username, String password);

    public boolean isUserSignedIn();

    public boolean isUserInRole(String role);

    public String getImeKorisnika();
    
    public Radnik getRadnik();
    
    public void sessionDestroy();

	int getUloguKorisnika();
}
