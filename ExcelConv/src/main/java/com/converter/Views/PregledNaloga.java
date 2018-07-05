package com.converter.Views;

import com.converter.Component.RestFilterButton;
import com.converter.Model.Entitet;
import com.converter.Model.Komitent;
import com.converter.Model.NalogStavka;
import com.converter.Model.Roba;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@UIScope
@Theme("mytheme")
public class PregledNaloga extends CssLayout implements View{

	public static final String VIEW_NAME = "entitetView";
	 
	
	public Grid<NalogStavka> grid = new Grid<>(NalogStavka.class);
	public ComboBox<Komitent> veleprodaja;
	public ComboBox<Komitent> kupci;
	public ComboBox<Roba> roba;
	
    public PregledNaloga() {
        setSizeFull();
        addStyleName("crud-pregled");
        HorizontalLayout topLayout = createTopBar();
        
        grid.setSizeFull();
       
        grid.setColumns("id","sifra", "naziv");
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
        
    	veleprodaja = new ComboBox<>();
    	veleprodaja.setPlaceholder("Veleprodaja");
    	veleprodaja.setItemCaptionGenerator(Komitent::getNaziv);
    	
    	kupci = new ComboBox<>();
    	kupci.setPlaceholder("Kupci");
    	kupci.setItemCaptionGenerator(Komitent::getNaziv);
    	
    	roba = new ComboBox<>();
    	roba.setPlaceholder("Roba");
    	roba.setItemCaptionGenerator(Roba::getNaziv);
    	
        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.addComponent(veleprodaja);
        topLayout.addComponent(kupci);
        topLayout.addComponent(roba);
        topLayout.setStyleName("top-bar");
        return topLayout;
    }
    
    public PregledNaloga getForm() {
    	return this;
    }
}
