package com.converter.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.EditForme.RobaGrupeEdit;
import com.converter.Service.RobaGrupaService;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@UIScope
@SpringComponent
@SuppressWarnings("serial")
public class RobaGrupeEditController extends RobaGrupeEdit{

	
	@SuppressWarnings("unused")
	private final RobaGrupaService rgser;
	
	@Autowired
	public RobaGrupeEditController(RobaGrupaService rgser) {
		this.rgser = rgser;
		
		 ok.addClickListener(event->{
	    	   try{
	    		   rgser.save(rgrupa);
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	    		   Notification success = new Notification(
	                       "Roba uspješno sačuvana");
	               success.setDelayMsec(2000);
	               success.setStyleName("bar success small");
	               success.setPosition(Position.BOTTOM_CENTER);
	               success.show(Page.getCurrent());
	               rgser.getListaJedna().clear();
	               rgser.izbrisiCache();
	               rgser.setListaJedna(rgser.findAll());
	               getFilter().clear();
	               getRobaGrupeGrid().setItems(rgser.findAll());
	    	   }catch(Exception ec){
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće sačuvati grupu robe.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 rgser.getListaJedna().clear();
	                 rgser.izbrisiCache();
		             rgser.setListaJedna(rgser.findAll());
		             getFilter().clear();
	                 getRobaGrupeGrid().setItems(rgser.findAll());
	    	   }
	       });
	 
	        delete.addClickListener(event->{
	        	 try{
	        		 rgser.delete(rgrupa);
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Grupa robe uspješno izbrisana");
	                 success.setDelayMsec(2000);
	                 success.setStyleName("bar success small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 rgser.getListaJedna().clear();
		             rgser.izbrisiCache();
		             rgser.setListaJedna(rgser.findAll());
		             getFilter().clear();
	                 getRobaGrupeGrid().setItems(rgser.findAll());
	      	   }catch(Exception ec){
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće izbrisati grupu robe robu. Roba je povezana sa stavkom iz naloga.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 rgser.getListaJedna().clear();
		             rgser.izbrisiCache();
		             rgser.setListaJedna(rgser.findAll());
		             getFilter().clear();
	                 getRobaGrupeGrid().setItems(rgser.findAll());
	      	   }
	        });
	        
	}
}
