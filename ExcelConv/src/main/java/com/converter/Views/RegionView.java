package com.converter.Views;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Component.RestFilterButton;
import com.converter.Model.Region;
import com.converter.Model.Roba;
import com.converter.Service.RegionService;
import com.converter.Service.RobaService;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.shared.data.sort.SortDirection;
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
public class RegionView extends CssLayout implements View{

	public static final String VIEW_NAME = "regionView";
	
	private final RegionService rser;
	public Grid<Region> grid = new Grid<>(Region.class);
    public TextField filter;
    public Button newProduct;
    
    @Autowired
    public RegionView(RegionService rser) {
    	super();
    	this.rser = rser;
        setSizeFull();
        addStyleName("crud-pregled");
        HorizontalLayout topLayout = createTopBar();
        
        grid.setSizeFull();
        grid.addColumn(Region -> Region.getEntitet().getNaziv()).setCaption("Naziv entiteta").setId("entitetNaziv").setHidable(true);
        grid.setItems(rser.findAll());
        grid.setColumns("sifra", "naziv", "entitetNaziv");
       
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

        newProduct = new Button("Novi entitet");
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
    
    public RegionView getForm() {
    	return this;
    }
}
