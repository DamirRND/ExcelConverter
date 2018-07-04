package com.converter.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.EditForme.RegionEdit;
import com.converter.Service.EntitetService;
import com.converter.Service.RegionService;
import com.converter.Service.RobaGrupaService;
import com.converter.Service.RobaService;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class RegionEditController extends RegionEdit{

	private RegionService rser;
	private EntitetService es;
	
	@Autowired
	public RegionEditController(RegionService rser, EntitetService es) {
		super();
		this.rser = rser;
		this.es = es;
		
		 ok.addClickListener(event->{
	    	   try{
	    		   rser.save(region);
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	    		   Notification success = new Notification(
	                       "Region uspješno sačuvan.");
	               success.setDelayMsec(2000);
	               success.setStyleName("bar success small");
	               success.setPosition(Position.BOTTOM_CENTER);
	               success.show(Page.getCurrent());
	               rser.getListaJedan().clear();
	               rser.izbrisiCache();
	               rser.setListaJedan(rser.findAll());
	               getFilter().clear();
	               getRegGrid().setItems(rser.findAll());
	    	   }catch(Exception ec){
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	    		   Notification success = new Notification(
	                       "Nije moguće sačuvati region.");
	               success.setDelayMsec(2000);
	               success.setStyleName("bar success small");
	               success.setPosition(Position.BOTTOM_CENTER);
	               success.show(Page.getCurrent());
	               rser.getListaJedan().clear();
	               rser.izbrisiCache();
	               rser.setListaJedan(rser.findAll());
	               getFilter().clear();
	               getRegGrid().setItems(rser.findAll());
	    	   }
	       });
	 
	        delete.addClickListener(event->{
	        	 try{
	        		 rser.delete(region);
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Region uspješno izbrisan.");
	                 success.setDelayMsec(2000);
	                 success.setStyleName("bar success small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 rser.getListaJedan().clear();
		               rser.izbrisiCache();
		               rser.setListaJedan(rser.findAll());
		               getFilter().clear();
	                 getRegGrid().setItems(rser.findAll());
	      	   }catch(Exception ec){
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće izbrisati region.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 rser.getListaJedan().clear();
		               rser.izbrisiCache();
		               rser.setListaJedan(rser.findAll());
		               getFilter().clear();
	                 getRegGrid().setItems(rser.findAll());
	      	   }
	        });
	       entitet.setItems(es.findAll());
	}
	
}
