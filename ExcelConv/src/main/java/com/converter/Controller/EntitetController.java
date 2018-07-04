package com.converter.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.converter.Model.Entitet;
import com.converter.Model.Roba;
import com.converter.Service.EntitetService;
import com.converter.Views.EntitetView;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class EntitetController extends EntitetView{

	
	private final EntitetService eres;
	private final EntitetEditController eedit;
	
	@Autowired
	public EntitetController(EntitetService eres, EntitetEditController eedit) {
		super();
		this.eres = eres;
		this.eedit = eedit;
		
		
		 eres.setListaJedan(eres.findAll());
		 grid.setItems(eres.findAll());
		 
		 grid.addItemClickListener(event->{
				UI.getCurrent().addWindow(eedit.getWindow());
				eedit.edit(event.getItem(), eres);
		 });
		 
		 eedit.seteGrid(grid);
		 eedit.setFilter(filter);
		 newProduct.addClickListener(noviProizvod -> {
		    	UI.getCurrent().addWindow(eedit.getWindow());
		    	eedit.edit(null, eres);
		 });

		 filter.setValueChangeMode(ValueChangeMode.LAZY);
	     filter.addValueChangeListener(event -> {
	        	if(StringUtils.isEmpty(filter)){
	        		eres.getListaJedan().clear();
	        		eres.izbrisiCache();
	        		eres.setListaJedan(eres.findAll());
					grid.getDataProvider().refreshAll();
				}else{
					 List<Entitet> result = (List<Entitet>) eres.getListaJedan().stream()
				                .filter(r -> {
				                    if (
				                    	String.valueOf(r.getSifra()).toLowerCase().contains(event.getValue().toLowerCase()) ||
				                    	r.getNaziv().toLowerCase().contains(event.getValue())
				                    		) {
				                        return true;
				                    }
				                    return false;
				         }).collect(Collectors.toList());
					grid.setItems(result);
				}
	       });
	}

}
