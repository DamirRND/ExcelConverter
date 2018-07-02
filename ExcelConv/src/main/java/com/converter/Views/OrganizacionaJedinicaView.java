package com.converter.Views;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Component.RestFilterButton;
import com.converter.Model.OrganizacionaJedinica;
import com.converter.Model.Region;
import com.converter.Service.OrganizacionaJedinicaService;
import com.converter.Service.RegionService;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
@Theme("mytheme")
public class OrganizacionaJedinicaView extends CssLayout implements View{

	public static final String VIEW_NAME = "orgjedView";
	
	private final OrganizacionaJedinicaService orgser;
	public Grid<OrganizacionaJedinica> grid = new Grid<>(OrganizacionaJedinica.class);
    public TextField filter;
    public Button newProduct;
    
    @Autowired
    public OrganizacionaJedinicaView(OrganizacionaJedinicaService orgser) {
    	super();
    	this.orgser =orgser;
        setSizeFull();
        addStyleName("crud-pregled");
        HorizontalLayout topLayout = createTopBar();
        
        grid.setSizeFull();
        grid.addColumn(OrganizacionaJedinica -> OrganizacionaJedinica.getMesto().getNaziv()).setCaption("Naziv mesta").setId("mestoNaziv").setHidable(true);
        grid.setItems(orgser.findAll());

        grid.setColumns("sifra", "naziv", "pib", "adresa", "mestoNaziv");
       
        VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.addComponent(topLayout);
        barAndGridLayout.addComponent(grid);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.setExpandRatio(grid, 1);
        barAndGridLayout.setStyleName("crud-main-layout");

        addComponent(barAndGridLayout);

    }

    public HorizontalLayout createTopBar() {
        filter = new TextField();
        filter.setStyleName("filter-tekstpolje");
        filter.setPlaceholder("Pretraga");
        RestFilterButton.extend(filter);
        
        filter.addValueChangeListener(event -> {});

        newProduct = new Button("Nova org. jedinica");
        newProduct.addStyleName(ValoTheme.BUTTON_PRIMARY);
        newProduct.setIcon(VaadinIcons.PLUS_CIRCLE);

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.addComponent(filter);
        topLayout.addComponent(newProduct);
        topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
        topLayout.setExpandRatio(filter, 1);
        topLayout.setStyleName("top-bar");
        return topLayout;
    }
    
    public OrganizacionaJedinicaView getForm() {
    	return this;
    }
}
