package com.converter.Controller;


import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Autentikacija.KontrolaPristupa;
import com.converter.Views.LoginScreenView;
import com.converter.Views.MainScreenView;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;


@UIScope
@SpringComponent
@SuppressWarnings("serial")
public class LoginController extends LoginScreenView{

	@Autowired
	private KontrolaPristupa kpristupa;
	
	public LoginController(KontrolaPristupa kpristupa) {
		this.kpristupa = kpristupa;
		
		login.addClickListener(login->{
			kpristupa.signIn(username.getValue().toString(), password.getValue().toString());
			if(kpristupa.isUserSignedIn()) {
				VaadinSession.getCurrent().setAttribute("Ime", kpristupa.getRadnik().getNaziv());
				UI.getCurrent().getNavigator().navigateTo(MainScreenView.VIEW_NAME);
			}else {
				Notification.show("Korisnik ne postoji.");
				login.getButton().setEnabled(true);
			}
			
		});
		
		
		
		forgotPassword.addClickListener(zablozinku->{
			Notification.show("Nije jos pripremljena forma za promjenu lozinke.");
		});
	}
}
