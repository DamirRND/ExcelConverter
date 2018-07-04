package com.converter.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.converter.Model.Entitet;
import com.converter.Model.OrganizacionaJedinica;
import com.converter.Service.OrganizacionaJedinicaService;
import com.converter.Views.OrganizacionaJedinicaView;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class OrgJedController extends OrganizacionaJedinicaView{

	private OrganizacionaJedinicaService orgser;
	private OrgJedEditController orgedit;
	
	@Autowired
	public OrgJedController(OrganizacionaJedinicaService orgser,OrgJedEditController orgedit) {
		super();
		this.orgser = orgser;
		this.orgedit = orgedit;
		
		orgser.setListaJedan(orgser.findAll());
		grid.setItems(orgser.findAll());
		
		 grid.addItemClickListener(event->{
				UI.getCurrent().addWindow(orgedit.getWindow());
				orgedit.edit(event.getItem(), orgser);
		 });
		 
		 orgedit.setOrgGrid(grid);
		 orgedit.setFilter(filter);
		 newProduct.addClickListener(noviProizvod -> {
		    	UI.getCurrent().addWindow(orgedit.getWindow());
		    	orgedit.edit(null, orgser);
		 });

		 filter.setValueChangeMode(ValueChangeMode.LAZY);
	     filter.addValueChangeListener(event -> {
	        	if(StringUtils.isEmpty(filter)){
	        		orgser.getListaJedan().clear();
	        		orgser.izbrisiCache();
	        		orgser.setListaJedan(orgser.findAll());
					grid.getDataProvider().refreshAll();
				}else{
					 List<OrganizacionaJedinica> result = (List<OrganizacionaJedinica>) orgser.getListaJedan().stream()
				                .filter(r -> {
				                    if (
				                    	String.valueOf(r.getSifra()).toLowerCase().contains(event.getValue().toLowerCase()) ||
				                    	r.getNaziv().toLowerCase().contains(event.getValue().toLowerCase()) ||
				                    	r.getAdresa().toLowerCase().contains(event.getValue().toLowerCase()) ||
				                    	r.getMesto().getNaziv().toLowerCase().contains(event.getValue().toLowerCase()) ||
				                    	r.getPib().toLowerCase().contains(event.getValue().toLowerCase())
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
