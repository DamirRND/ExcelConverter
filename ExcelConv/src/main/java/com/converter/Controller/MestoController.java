package com.converter.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.converter.Model.Mesto;
import com.converter.Service.MestoService;
import com.converter.Views.MestoView;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class MestoController extends MestoView{

	private final MestoService mser;
	private final MestoEditController medit;
	
    @Autowired
	public MestoController(MestoService mser, MestoEditController medit) {
		super();
		this.mser = mser;
		this.medit = medit;
		
		grid.setItems(mser.findAll());
		mser.setListaJedan(mser.findAll());
		
		grid.addItemClickListener(event->{
			UI.getCurrent().addWindow(medit.getWindow());
			medit.edit(event.getItem(), mser);
		 });
	    	
		medit.setMestoGrid(grid);
		medit.setFilter(filter);
	    grid.setSelectionMode(SelectionMode.SINGLE);
	 	newProduct.addClickListener(noviProizvod -> {
	 	    	UI.getCurrent().addWindow(medit.getWindow());
	 	    	medit.edit(null, mser);
	 	});
	
	 	filter.setValueChangeMode(ValueChangeMode.LAZY);
	         filter.addValueChangeListener(event -> {
	         	if(StringUtils.isEmpty(filter)){
	         		mser.getListaJedan().clear();
	         		mser.izbrisiCache();
	         		mser.setListaJedan(mser.findAll());
	 				grid.getDataProvider().refreshAll();
	 			}else{
	 				 List<Mesto> result = (List<Mesto>) mser.getListaJedan().stream()
	 			                .filter(r -> {
	 			                    if (
	 			                    	String.valueOf(r.getPttbroj()).toLowerCase().contains(event.getValue().toLowerCase()) || 
	 			                    	r.getNaziv().toLowerCase().contains(event.getValue().toLowerCase()) ||
	 			                    	r.getRegion().getNaziv().toLowerCase().contains(event.getValue().toLowerCase()) ||
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
