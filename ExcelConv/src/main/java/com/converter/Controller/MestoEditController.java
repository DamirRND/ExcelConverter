package com.converter.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.EditForme.MestoEdit;
import com.converter.Service.EntitetService;
import com.converter.Service.MestoService;
import com.converter.Service.RegionService;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class MestoEditController extends MestoEdit{
	
	private final MestoService ms;
	private final RegionService rs;
	private final EntitetService es;
	
	@Autowired
	public MestoEditController(MestoService ms, RegionService rs, EntitetService es) {
		this.ms = ms;
		this.rs = rs;
		this.es = es;
		
		 ok.addClickListener(event->{
	    	   try{
	    		   ms.save(mesto);
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	    		   Notification success = new Notification(
	                       "Mesto uspješno sačuvano");
	               success.setDelayMsec(2000);
	               success.setStyleName("bar success small");
	               success.setPosition(Position.BOTTOM_CENTER);
	               success.show(Page.getCurrent());
	               ms.getListaJedan().clear();
	               ms.izbrisiCache();
	               ms.setListaJedan(ms.findAll());
	               getFilter().clear();
	               getMestoGrid().setItems(ms.findAll());
	    	   }catch(Exception ec){
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće sačuvati mesto.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 ms.getListaJedan().clear();
		               ms.izbrisiCache();
		               ms.setListaJedan(ms.findAll());
		               getFilter().clear();
	                 getMestoGrid().setItems(ms.findAll());
	    	   }
	       });
	 
	        delete.addClickListener(event->{
	        	 try{
	        		 ms.delete(mesto);
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Mesto uspješno izbrisano.");
	                 success.setDelayMsec(2000);
	                 success.setStyleName("bar success small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 ms.getListaJedan().clear();
		               ms.izbrisiCache();
		               ms.setListaJedan(ms.findAll());
		               getFilter().clear();
	                 getMestoGrid().setItems(ms.findAll());
	      	   }catch(Exception ec){
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće izbrisati mesto.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 ms.getListaJedan().clear();
		               ms.izbrisiCache();
		               ms.setListaJedan(ms.findAll());
		               getFilter().clear();
	                 getMestoGrid().setItems(ms.findAll());
	      	   }
	        });
	        
	        region.setItems(rs.findAll());
	        entitet.setItems(es.findAll());
	        
	}

}
