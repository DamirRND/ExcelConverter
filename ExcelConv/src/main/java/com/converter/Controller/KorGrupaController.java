package com.converter.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.converter.Model.KorGrupa;
import com.converter.Service.KorGrupaService;
import com.converter.Views.KorGrupaView;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class KorGrupaController extends KorGrupaView{

	@SuppressWarnings("unused")
	private final KorGrupaService korser;
	
	@SuppressWarnings("unused")
	private final KorGrupaEditController kgedit;
	
	
	@Autowired
	public KorGrupaController(KorGrupaService korser, KorGrupaEditController kgedit) {
		super();
    	this.korser = korser;
    	this.kgedit = kgedit;
    	
    	korser.setListaJedan(korser.findAll());
    	grid.setItems(korser.findAll());
    	
    	grid.addItemClickListener(event->{
			UI.getCurrent().addWindow(kgedit.getWindow());
			kgedit.edit(event.getItem(), korser);
		 });
		
    	kgedit.setFilter(filter);
    	kgedit.setKorGrid(grid);
		 newProduct.addClickListener(noviProizvod -> {
		    	UI.getCurrent().addWindow(kgedit.getWindow());
		    	kgedit.edit(null, korser);
		 });
	
		 filter.setValueChangeMode(ValueChangeMode.LAZY);
	     filter.addValueChangeListener(event -> {
	        	if(StringUtils.isEmpty(filter)){
	        		korser.getListaJedan().clear();
	        		korser.izbrisiCache();
	        		korser.setListaJedan(korser.findAll());
					grid.getDataProvider().refreshAll();
				}else{
					 List<KorGrupa> result = (List<KorGrupa>) korser.getListaJedan().stream()
				                .filter(r -> {
				                    if (
				                    	String.valueOf(r.getSifra()).toLowerCase().contains(event.getValue().toLowerCase()) ||
				                    	r.getOpis().toLowerCase().contains(event.getValue()) || 
				                    	String.valueOf(r.getNivo()).toLowerCase().contains(event.getValue().toLowerCase())
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
