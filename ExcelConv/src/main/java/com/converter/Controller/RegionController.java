package com.converter.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.converter.Model.Region;
import com.converter.Service.RegionService;
import com.converter.Views.RegionView;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class RegionController extends RegionView{

	@SuppressWarnings("unused")
	private final RegionService rser;
	@SuppressWarnings("unused")
	private final RegionEditController redit;
	
	
	@Autowired
	public RegionController(RegionService rser, RegionEditController redit) {
		super();
		this.rser = rser;
		this.redit = redit;
		
		rser.setListaJedan(rser.findAll());
		grid.setItems(rser.findAll());
		
		grid.addItemClickListener(event->{
			UI.getCurrent().addWindow(redit.getWindow());
			redit.edit(event.getItem(), rser);
		 });
		 
		redit.setRegGrid(grid);
		redit.setFilter(filter);
		 newProduct.addClickListener(noviProizvod -> {
		    	UI.getCurrent().addWindow(redit.getWindow());
		    	redit.edit(null, rser);
		 });
	
		 filter.setValueChangeMode(ValueChangeMode.LAZY);
	     filter.addValueChangeListener(event -> {
	        	if(StringUtils.isEmpty(filter)){
	        		rser.getListaJedan().clear();
	        		rser.izbrisiCache();
	        		rser.setListaJedan(rser.findAll());
					grid.getDataProvider().refreshAll();
				}else{
					 List<Region> result = (List<Region>) rser.getListaJedan().stream()
				                .filter(r -> {
				                    if (
				                    	String.valueOf(r.getSifra()).toLowerCase().contains(event.getValue().toLowerCase()) ||
				                    	r.getNaziv().toLowerCase().contains(event.getValue().toLowerCase()) ||
				                    	r.getEntitet().getNaziv().toLowerCase().contains(event.getValue().toLowerCase())
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
