package com.converter.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.EditForme.KomitentiEdit;
import com.converter.Service.KomitentService;
import com.converter.Service.MestoService;
import com.converter.Service.UstanovaService;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class KomitentEditVeleprodajaController extends KomitentiEdit{
	
	@SuppressWarnings("unused")
	private final KomitentService kms;
	@SuppressWarnings("unused")
	private final MestoService ms;
	@SuppressWarnings("unused")
	private final UstanovaService us;
	
	@Autowired
	public KomitentEditVeleprodajaController(KomitentService kms, MestoService ms, UstanovaService us) {
		this.kms = kms;
		this.ms = ms;
		this.us = us;
		
		 ok.addClickListener(event->{
	    	   try{
	    		   kms.save(komitent);
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	    		   Notification success = new Notification(
	                       "Komitent uspješno sačuvan");
	               success.setDelayMsec(2000);
	               success.setStyleName("bar success small");
	               success.setPosition(Position.BOTTOM_CENTER);
	               success.show(Page.getCurrent());
	               kms.getListaJedan().clear();
	               kms.izbrisiCache();
	               kms.setListaJedan(kms.findZaList());
	              getFilterVele().clear();
	              getKomGridVele().setItems(kms.findAllByTip("VP"));
	    	   }catch(Exception ec){
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće sačuvati komitenta.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 kms.getListaJedan().clear();
		               kms.izbrisiCache();
		               kms.setListaJedan(kms.findZaList());
		               getFilterVele().clear();
			              getKomGridVele().setItems(kms.findAllByTip("VP"));
	    	   }
	       });
	 
	        delete.addClickListener(event->{
	        	 try{
	        		 kms.delete(komitent);
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Komitent uspješno izbrisan.");
	                 success.setDelayMsec(2000);
	                 success.setStyleName("bar success small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 kms.getListaJedan().clear();
		               kms.izbrisiCache();
		               kms.setListaJedan(kms.findZaList());
		               getFilterVele().clear();
			              getKomGridVele().setItems(kms.findAllByTip("VP"));
	      	   }catch(Exception ec){
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće izbrisati komitenta. Komitent je povezan sa stavkom iz naloga.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 kms.getListaJedan().clear();
		               kms.izbrisiCache();
		               kms.setListaJedan(kms.findZaList());
		               getFilterVele().clear();
			              getKomGridVele().setItems(kms.findAllByTip("VP"));
	      	   }
	        });
	        
	        mesto.setItems(ms.findAll());
	        ustanova.setItems(us.findAllCombo());
	        
	}
	
}
