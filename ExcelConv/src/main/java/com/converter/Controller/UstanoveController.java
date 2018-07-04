package com.converter.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.converter.Model.Roba;
import com.converter.Model.Ustanova;
import com.converter.Service.UstanovaService;
import com.converter.Views.UstanovaView;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class UstanoveController extends UstanovaView{

	private final UstanovaService user;
	private final UstanoveEditController uedit;
	
	@Autowired
	public UstanoveController(UstanovaService user, UstanoveEditController uedit) {
		super();
		this.user = user;
		this.uedit = uedit;
		
		 grid.setDataProvider(
					(sortOrders, offset, limit)->{
						Map<String, Boolean> sortOrder = sortOrders.stream()
	                            .collect(Collectors.toMap(
	                                    sort -> sort.getSorted(),
	                                    sort -> sort.getDirection() == SortDirection.ASCENDING));

	                    return user.findAll(offset, limit, sortOrder).stream();
					},
					()-> user.count()
	        );
		 
		 grid.addItemClickListener(event->{
				UI.getCurrent().addWindow(uedit.getWindow());
				uedit.edit(event.getItem(), user);
			});
		 
		 uedit.setuGrid(grid);
		    
		    newProduct.addClickListener(noviProizvod -> {
		    	UI.getCurrent().addWindow(uedit.getWindow());
		    	uedit.edit(null, user);
		    });

		    filter.setValueChangeMode(ValueChangeMode.LAZY);
	        filter.addValueChangeListener(event -> {
	        	if(StringUtils.isEmpty(filter)){
					grid.getDataProvider().refreshAll();
				}else{
					 List<Ustanova> result = (List<Ustanova>) user.getListaJedan().stream()
				                .filter(u -> {
				                    if (
				                    	String.valueOf(u.getSifra()).toLowerCase().contains(event.getValue().toLowerCase()) ||
				                    	u.getNaziv().toLowerCase().contains(event.getValue()) 
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
