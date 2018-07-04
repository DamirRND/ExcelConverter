package com.converter.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.EditForme.UlogeRadnikaEdit;
import com.converter.Service.KorGrupaService;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class KorGrupaEditController extends UlogeRadnikaEdit{

	@SuppressWarnings("unused")
	private final KorGrupaService kgs;
	
	@Autowired
	public KorGrupaEditController(KorGrupaService kgs) {
		this.kgs = kgs;
		
		 ok.addClickListener(event->{
	    	   try{
	    		   kgs.save(korgrupa);
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	    		   Notification success = new Notification(
	                       "Uloga radnika uspješno sačuvana");
	               success.setDelayMsec(2000);
	               success.setStyleName("bar success small");
	               success.setPosition(Position.BOTTOM_CENTER);
	               success.show(Page.getCurrent());
	               kgs.getListaJedan().clear();
	               kgs.izbrisiCache();
	               kgs.setListaJedan(kgs.findAll());
	               getFilter().clear();
	               getKorGrid().setItems(kgs.findAll());
	    	   }catch(Exception ec){
	    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće sačuvati ulogu radnika.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 kgs.getListaJedan().clear();
		               kgs.izbrisiCache();
		               kgs.setListaJedan(kgs.findAll());
		               getFilter().clear();
	                 getKorGrid().setItems(kgs.findAll());
	    	   }
	       });
	 
	        delete.addClickListener(event->{
	        	 try{
	        		 kgs.delete(korgrupa);
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Uloga radnika uspješno izbrisana.");
	                 success.setDelayMsec(2000);
	                 success.setStyleName("bar success small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 kgs.getListaJedan().clear();
		               kgs.izbrisiCache();
		               kgs.setListaJedan(kgs.findAll());
		               getFilter().clear();
	                 getKorGrid().setItems(kgs.findAll());
	      	   }catch(Exception ec){
	      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
	      		   	Notification success = new Notification("Nije moguće izbrisati ulogu radnika.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	                 kgs.getListaJedan().clear();
		               kgs.izbrisiCache();
		               kgs.setListaJedan(kgs.findAll());
		               getFilter().clear();
	                 getKorGrid().setItems(kgs.findAll());
	      	   }
	        });
	        
	}
}
