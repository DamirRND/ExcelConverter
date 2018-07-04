package com.converter.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.EditForme.UstanoveEdit;
import com.converter.Model.Ustanova;
import com.converter.Service.RobaGrupaService;
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
public class UstanoveEditController extends UstanoveEdit{
	
	private final UstanovaService user;
	
	@Autowired
	public UstanoveEditController(UstanovaService user) {
		this.user = user;
		
		 ok.addClickListener(event->{
	    	   try{
	    		   user.save(ustanova);
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	    		   Notification success = new Notification(
	                       "Ustanova uspješno sačuvana");
	               success.setDelayMsec(2000);
	               success.setStyleName("bar success small");
	               success.setPosition(Position.BOTTOM_CENTER);
	               success.show(Page.getCurrent());
	               getuGrid().getDataProvider().refreshAll();
	    	   }catch(Exception ec){
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće sačuvati ustanovu.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 getuGrid().getDataProvider().refreshAll();
	    	   }
	       });
	 
	        delete.addClickListener(event->{
	        	 try{
	        		 user.delete(ustanova);
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Ustanova uspješno izbrisana");
	                 success.setDelayMsec(2000);
	                 success.setStyleName("bar success small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 getuGrid().getDataProvider().refreshAll();
	      	   }catch(Exception ec){
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće izbrisati ustanovu. Ustanova je povezana sa stavkom iz naloga.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 getuGrid().getDataProvider().refreshAll();
	      	   }
	        });
	        
	}

}
