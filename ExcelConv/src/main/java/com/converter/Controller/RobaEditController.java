package com.converter.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.EditForme.RobaEdit;
import com.converter.Service.RobaGrupaService;
import com.converter.Service.RobaService;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@UIScope
@SpringComponent
@SuppressWarnings("serial")
public class RobaEditController extends RobaEdit{

	@SuppressWarnings("unused")
	private final RobaService rser;
	@SuppressWarnings("unused")
	private final RobaGrupaService rgser;
	
	
	@Autowired
	public RobaEditController(RobaService rser, RobaGrupaService rgser) {
		super();
		this.rser = rser;
		this.rgser = rgser;
		
		 ok.addClickListener(event->{
	    	   try{
	    		   rser.save(roba);
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	    		   Notification success = new Notification(
	                       "Roba uspješno sačuvana");
	               success.setDelayMsec(2000);
	               success.setStyleName("bar success small");
	               success.setPosition(Position.BOTTOM_CENTER);
	               success.show(Page.getCurrent());
	               rser.getListaJedna().clear();
	               rser.setListaJedna(rser.findAllCombo());
	               getFilter().clear();
	               rser.removeCache();
	               getRobaGrid().getDataProvider().refreshAll();
	    	   }catch(Exception ec){
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće sačuvati robu.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 rser.getListaJedna().clear();
	                 rser.setListaJedna(rser.findAllCombo());
	                 getFilter().clear();
	                 rser.removeCache();
	                 getRobaGrid().getDataProvider().refreshAll();
	    	   }
	       });
	 
	        delete.addClickListener(event->{
	        	 try{
	        		 rser.delete(roba);
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Roba uspješno izbrisana");
	                 success.setDelayMsec(2000);
	                 success.setStyleName("bar success small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 rser.getListaJedna().clear();
	                 rser.setListaJedna(rser.findAllCombo());
	                 getFilter().clear();
	                 rser.removeCache();
	                 getRobaGrid().getDataProvider().refreshAll();
	      	   }catch(Exception ec){
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće izbrisati robu. Roba je povezana sa stavkom iz naloga.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 rser.getListaJedna().clear();
	                 rser.setListaJedna(rser.findAllCombo());
	                 getFilter().clear();
	                 rser.removeCache();
	                 getRobaGrid().getDataProvider().refreshAll();
	      	   }
	        });
	        
	       	grupa.setItems(rgser.findAll());
	}

}
