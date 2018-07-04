package com.converter.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.EditForme.OrganizacionaJedinicaEdit;
import com.converter.Service.MestoService;
import com.converter.Service.OrganizacionaJedinicaService;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class OrgJedEditController extends OrganizacionaJedinicaEdit{

	@SuppressWarnings("unused")
	private OrganizacionaJedinicaService orgser;
	@SuppressWarnings("unused")
	private MestoService ms;
	
	@Autowired
	public OrgJedEditController(OrganizacionaJedinicaService orgser, MestoService ms) {
		this.orgser = orgser;
		this.ms = ms;
		
		 ok.addClickListener(event->{
	    	   try{
	    		   orgser.save(orgjed);
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	    		   Notification success = new Notification(
	                       "Org. jed. uspješno sačuvana");
	               success.setDelayMsec(2000);
	               success.setStyleName("bar success small");
	               success.setPosition(Position.BOTTOM_CENTER);
	               success.show(Page.getCurrent());
	               orgser.getListaJedan().clear();
	               orgser.izbrisiCache();
	               orgser.setListaJedan(orgser.findAll());
	               getFilter().clear();
	               getOrgGrid().setItems(orgser.findAll());
	    	   }catch(Exception ec){
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće sačuvati org. jed.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 orgser.getListaJedan().clear();
		             orgser.izbrisiCache();
		             orgser.setListaJedan(orgser.findAll());
		             getFilter().clear();
	                 getOrgGrid().setItems(orgser.findAll());
	    	   }
	       });
	 
	        delete.addClickListener(event->{
	        	 try{
	        		 orgser.delete(orgjed);
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Org. jed. uspješno izbrisana");
	                 success.setDelayMsec(2000);
	                 success.setStyleName("bar success small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 orgser.getListaJedan().clear();
		             orgser.izbrisiCache();
		             orgser.setListaJedan(orgser.findAll());
		             getFilter().clear();
	                 getOrgGrid().setItems(orgser.findAll());
	      	   }catch(Exception ec){
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće izbrisati org. jed.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 orgser.getListaJedan().clear();
		             orgser.izbrisiCache();
		             orgser.setListaJedan(orgser.findAll());
		             getFilter().clear();
	                 getOrgGrid().setItems(orgser.findAll());
	      	   }
	        });
	        
	        mesto.setItems(ms.findAll());
	        
	}
}
