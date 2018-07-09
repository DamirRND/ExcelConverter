package com.converter.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.converter.Model.Roba;
import com.converter.Service.RobaService;
import com.converter.Views.RobaView;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class RobaController extends RobaView{

	
	@SuppressWarnings("unused")
	private final RobaService robaSer;
	@SuppressWarnings("unused")
	private final RobaEditController robaEdit;
	
	@Autowired
	public RobaController(RobaService robaSer, RobaEditController robaEdit) {
		super();
		this.robaSer = robaSer;
		this.robaEdit = robaEdit;
		
		robaSer.setListaJedna(robaSer.findAllCombo());
		grid.setDataProvider(
			(sortOrders, offset, limit)->{
				Map<String, Boolean> sortOrder = sortOrders.stream()
	                    .collect(Collectors.toMap(
	                         sort -> sort.getSorted(),
	                         sort -> sort.getDirection() == SortDirection.ASCENDING));
	                 return robaSer.findAll(offset, limit, sortOrder).stream();
			},
			()-> robaSer.count()
	    );
		
		robaEdit.setRobaGrid(grid);
		robaEdit.setFilter(filter);
		 
		grid.addItemClickListener(event->{
			UI.getCurrent().addWindow(robaEdit.getWindow());
			robaEdit.edit(event.getItem(), robaSer);
		});
		 grid.setSelectionMode(SelectionMode.SINGLE);
	    
	    
	    newProduct.addClickListener(noviProizvod -> {
	    	UI.getCurrent().addWindow(robaEdit.getWindow());
			robaEdit.edit(null, robaSer);
	    });

	    filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(event -> {
        	if(StringUtils.isEmpty(filter)){
        		robaSer.getListaJedna().clear();
        		robaSer.removeCache();
        		robaSer.setListaJedna(robaSer.findAllCombo());
				grid.getDataProvider().refreshAll();
			}else{
				 List<Roba> result = (List<Roba>) robaSer.getListaJedna().stream()
			                .filter(r -> {
			                    if (
			                    	String.valueOf(r.getCena()).toLowerCase().contains(event.getValue().toLowerCase()) || 
			                    	String.valueOf(r.getSifra()).toLowerCase().contains(event.getValue().toLowerCase()) ||
			                    	r.getNaziv().toLowerCase().contains(event.getValue()) ||
			                    	r.getGrupa().getNaziv().toLowerCase().contains(event.getValue().toLowerCase()) || 
			                    	r.getJm().toLowerCase().contains(event.getValue().toLowerCase())
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
