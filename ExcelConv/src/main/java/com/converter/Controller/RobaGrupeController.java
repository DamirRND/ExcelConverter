package com.converter.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.converter.Model.Roba;
import com.converter.Model.RobaGrupa;
import com.converter.Service.RobaGrupaService;
import com.converter.Views.RobaGrupeView;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;
import com.vaadin.ui.Grid.SelectionMode;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class RobaGrupeController extends RobaGrupeView{

	private final RobaGrupaService rgser;
	private final RobaGrupeEditController rgedit;
	
	@Autowired
	public RobaGrupeController(RobaGrupaService rgser, RobaGrupeEditController rgedit) {
		super();
		this.rgser = rgser;
		this.rgedit = rgedit;
		
		rgser.setListaJedna(rgser.findAll());
		grid.setItems(rgser.findAll());
		grid.setSelectionMode(SelectionMode.SINGLE);
		
		rgedit.setRobaGrupeGrid(grid);
		rgedit.setFilter(filter);
		grid.addItemClickListener(event->{
			UI.getCurrent().addWindow(rgedit.getWindow());
			rgedit.edit(event.getItem(), rgser);
		});
		
		newProduct.addClickListener(noviProizvod -> {
		    	UI.getCurrent().addWindow(rgedit.getWindow());
		    	rgedit.edit(null, rgser);
		});

		filter.setValueChangeMode(ValueChangeMode.LAZY);
	    filter.addValueChangeListener(event -> {
	        	if(StringUtils.isEmpty(filter)){
	        		rgser.getListaJedna().clear();
	        		rgser.izbrisiCache();
	        		rgser.setListaJedna(rgser.findAll());
					grid.getDataProvider().refreshAll();
				}else{
					 List<RobaGrupa> result = (List<RobaGrupa>) rgser.getListaJedna().stream()
				                .filter(rg -> {
				                    if (
				                    	String.valueOf(rg.getSifra()).toLowerCase().contains(event.getValue().toLowerCase()) ||
				                    	rg.getNaziv().toLowerCase().contains(event.getValue()) 
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
