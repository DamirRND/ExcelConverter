package com.converter.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.EditForme.RadniciEdit;
import com.converter.Service.KorGrupaService;
import com.converter.Service.RadnikService;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class RadnikEditController extends RadniciEdit{

	private final RadnikService rs;
	private final KorGrupaService kgs;
	
	@Autowired
	public RadnikEditController(RadnikService rs, KorGrupaService kgs) {
		super();
		this.rs = rs;
		this.kgs = kgs;
		
		 ok.addClickListener(event->{
	    	   try{
	    		   rs.save(radnik);
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	    		   Notification success = new Notification(
	                       "Radnik uspješno sačuvan.");
	               success.setDelayMsec(2000);
	               success.setStyleName("bar success small");
	               success.setPosition(Position.BOTTOM_CENTER);
	               success.show(Page.getCurrent());
	               rs.getListaJedan().clear();
	               rs.izbrisiCache();
	               rs.setListaJedan(rs.findAll());
	               getFilter().clear();
	               getRadnikGrid().setItems(rs.findAll());
	    	   }catch(Exception ec){
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	    		   Notification success = new Notification(
	                       "Nije moguće sačuvati radnika.");
	               success.setDelayMsec(2000);
	               success.setStyleName("bar success small");
	               success.setPosition(Position.BOTTOM_CENTER);
	               success.show(Page.getCurrent());
	               rs.getListaJedan().clear();
	               rs.izbrisiCache();
	               rs.setListaJedan(rs.findAll());
	               getFilter().clear();
	               getRadnikGrid().setItems(rs.findAll());
	    	   }
	       });
	 
	        delete.addClickListener(event->{
	        	 try{
	        		 rs.delete(radnik);
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Radnik uspješno izbrisan.");
	                 success.setDelayMsec(2000);
	                 success.setStyleName("bar success small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 rs.getListaJedan().clear();
		               rs.izbrisiCache();
		               rs.setListaJedan(rs.findAll());
		               getFilter().clear();
	                 getRadnikGrid().setItems(rs.findAll());
	      	   }catch(Exception ec){
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće izbrisati radnika.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 rs.getListaJedan().clear();
		               rs.izbrisiCache();
		               rs.setListaJedan(rs.findAll());
		               getFilter().clear();
	                 getRadnikGrid().setItems(rs.findAll());
	      	   }
	        });
	        
	       grupa.setItems(kgs.findAll());
	}
	
}
