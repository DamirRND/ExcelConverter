package com.converter.Views;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Component.RestFilterButton;
import com.converter.Model.Roba;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.themes.ValoTheme;


@SuppressWarnings("serial")
@UIScope
@Theme("mytheme")
public class RobaView extends CssLayout implements View{

	public static final String VIEW_NAME = "robaView";

	public Grid<Roba> grid = new Grid<>(Roba.class);
    public TextField filter;
    public Button newProduct;
    
	@Autowired
    public RobaView() {
		
        setSizeFull();
        addStyleName("crud-pregled");
        HorizontalLayout topLayout = createTopBar();
        grid.setSizeFull();
        grid.addColumn(Roba -> Roba.getRobagrupa().getNaziv()).setCaption("Grupa robe").setId("grupa").setHidable(true);
	    grid.setColumns("id","sifra", "naziv", "jm", "grupa", "cena");
	    grid.getColumn("id").setHidden(true);
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
        
        newProduct = new Button("Novi proizvod");
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
    
    public RobaView getForm() {
    	return this;
    }
}
