package com.converter.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.EditForme.EntitetEdit;
import com.converter.Service.EntitetService;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class EntitetEditController extends EntitetEdit{
	
	@SuppressWarnings("unused")
	private final EntitetService eser;
	
	
	@Autowired
	public EntitetEditController(EntitetService eser) {
		this.eser = eser;
		
		 ok.addClickListener(event->{
	    	   try{
	    		   eser.save(entitet);
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	    		   Notification success = new Notification(
	                       "Entitet uspješno sačuvan.");
	               success.setDelayMsec(2000);
	               success.setStyleName("bar success small");
	               success.setPosition(Position.BOTTOM_CENTER);
	               success.show(Page.getCurrent());
	               eser.getListaJedan().clear();
	               eser.izbrisiCache();
	               eser.setListaJedan(eser.findAll());
	               getFilter().clear();
	               geteGrid().setItems(eser.findAll());
	    	   }catch(Exception ec){
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće sačuvati entitet.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 eser.getListaJedan().clear();
		             eser.izbrisiCache();
		             eser.setListaJedan(eser.findAll());
		             getFilter().clear();
	                 geteGrid().setItems(eser.findAll());
	    	   }
	       });
	 
	        delete.addClickListener(event->{
	        	 try{
	        		 eser.delete(entitet);
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Entitet uspješno izbrisan.");
	                 success.setDelayMsec(2000);
	                 success.setStyleName("bar success small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 eser.getListaJedan().clear();
		             eser.izbrisiCache();
		             eser.setListaJedan(eser.findAll());
		             getFilter().clear();
	                 geteGrid().setItems(eser.findAll());
	      	   }catch(Exception ec){
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće izbrisati Entitet.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 eser.getListaJedan().clear();
		             eser.izbrisiCache();
		             eser.setListaJedan(eser.findAll());
		             getFilter().clear();
	                 geteGrid().setItems(eser.findAll());
	      	   }
	        });
	        
	}

}
