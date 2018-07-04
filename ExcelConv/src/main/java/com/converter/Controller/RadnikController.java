package com.converter.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.converter.Model.Radnik;
import com.converter.Service.RadnikService;
import com.converter.Views.RadnikView;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class RadnikController extends RadnikView{

	private final RadnikService rser;
	private final RadnikEditController redc;
	
	@Autowired
	public RadnikController(RadnikService rser, RadnikEditController redc) {
		super();
		this.rser = rser;
    	this.redc = redc;
    	
    	grid.setItems(rser.findAll());
    	
    	grid.addItemClickListener(event->{
			UI.getCurrent().addWindow(redc.getWindow());
			redc.edit(event.getItem(), rser);
		});
    	
    	redc.setRadnikGrid(grid);
    	grid.setSelectionMode(SelectionMode.SINGLE);
 	    newProduct.addClickListener(noviProizvod -> {
 	    	UI.getCurrent().addWindow(redc.getWindow());
 	    	redc.edit(null, rser);
 	    });

 	    filter.setValueChangeMode(ValueChangeMode.LAZY);
         filter.addValueChangeListener(event -> {
         	if(StringUtils.isEmpty(filter)){
 				grid.getDataProvider().refreshAll();
 			}else{
 				 List<Radnik> result = (List<Radnik>) rser.getListaJedan().stream()
 			                .filter(r -> {
 			                    if (
 			                    	String.valueOf(r.getKorime()).toLowerCase().contains(event.getValue().toLowerCase()) || 
 			                    	r.getNaziv().toLowerCase().contains(event.getValue()) ||
 			                    	r.getKorgrupa().getOpis().toLowerCase().contains(event.getValue().toLowerCase()) 
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
