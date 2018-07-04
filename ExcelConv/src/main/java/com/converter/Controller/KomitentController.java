package com.converter.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.converter.Model.Komitent;
import com.converter.Service.KomitentService;
import com.converter.Views.KomitentView;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class KomitentController extends KomitentView{

	
	private final KomitentService kser;
	private final KomitentEditController kedit;
	
	public KomitentController(KomitentService kser, KomitentEditController kedit) {
		super();
		this.kser = kser;
		this.kedit = kedit;
		
		kser.setListaJedan(kser.findZaList());
		 grid.setDataProvider(
					(sortOrders, offset, limit)->{
						Map<String, Boolean> sortOrder = sortOrders.stream()
	                            .collect(Collectors.toMap(
	                                    sort -> sort.getSorted(),
	                                    sort -> sort.getDirection() == SortDirection.ASCENDING));

	                    return kser.findAll(offset, limit, sortOrder).stream();
					},
					()-> kser.count()
	        );
		 
		grid.addItemClickListener(event->{
				UI.getCurrent().addWindow(kedit.getWindow());
				kedit.edit(event.getItem(), kser);
		 });
	    	
		kedit.setKomGrid(grid);
		kedit.setFilter(filter);
	    grid.setSelectionMode(SelectionMode.SINGLE);
	 	newProduct.addClickListener(noviProizvod -> {
	 	    	UI.getCurrent().addWindow(kedit.getWindow());
	 	    	kedit.edit(null, kser);
	 	});

	 	filter.setValueChangeMode(ValueChangeMode.LAZY);
	         filter.addValueChangeListener(event -> {
	         	if(StringUtils.isEmpty(filter)){
	         		kser.getListaJedan().clear();
	         		kser.izbrisiCache();
	         		kser.setListaJedan(kser.findZaList());
	 				grid.getDataProvider().refreshAll();
	 			}else{
	 				 List<Komitent> result = (List<Komitent>) kser.getListaJedan().stream()
	 			                .filter(r -> {
	 			                    if (
	 			                    	String.valueOf(r.getSifra()).toLowerCase().contains(event.getValue().toLowerCase()) || 
	 			                    	r.getNaziv().toLowerCase().contains(event.getValue().toLowerCase()) ||
	 			                    	r.getPib().toLowerCase().contains(event.getValue().toLowerCase()) ||
	 			                    	r.getAdresa().toLowerCase().contains(event.getValue().toLowerCase()) ||
	 			                    	String.valueOf(r.getTip().toLowerCase()).contains(event.getValue().toLowerCase()) ||
	 			                    	r.getMesto().getNaziv().toLowerCase().contains(event.getValue().toLowerCase()) ||
	 			                    	r.getUstanova().getNaziv().toLowerCase().contains(event.getValue().toLowerCase())
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
