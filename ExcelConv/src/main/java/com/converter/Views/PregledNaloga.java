package com.converter.Views;

import com.converter.Model.Komitent;
import com.converter.Model.NalogStavka;
import com.converter.Model.Roba;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.datefield.DateResolution;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@UIScope
@Theme("mytheme")
public class PregledNaloga extends CssLayout implements View{

	public static final String VIEW_NAME = "entitetView";
	 
	
	public Grid<NalogStavka> grid = new Grid<>(NalogStavka.class);
	
	public ComboBox<Komitent> veleprodaja;
	public InlineDateField datum;
	public ComboBox<Komitent> kupci;
	public ComboBox<Roba> roba;
	
	public Button export;
	
    public PregledNaloga() {
        setSizeFull();
        addStyleName("crud-pregled");
        HorizontalLayout topLayout = createTopBar();
        
        grid.setSizeFull();
        grid.addColumn(NalogStavka -> NalogStavka.getRoba().getNaziv()).setCaption("Roba").setId("robaMoja").setHidable(true);
        grid.addColumn(NalogStavka -> NalogStavka.getKupac().getNaziv()).setCaption("Komitent").setId("komitentMoj").setHidable(true);
        grid.addColumn(NalogStavka -> NalogStavka.getNalog().getDatum()).setCaption("Datum naloga").setId("nalogMoj").setHidable(true);
        
        grid.setColumns("id","komitentMoj", "robaMoja", "cena", "kolicina", "iznos", "nalogMoj");
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
    	
    	datum = new InlineDateField();
    	datum.setResolution(DateResolution.MONTH);
    	
    	
    	kupci = new ComboBox<>();
    	kupci.setPlaceholder("Kupci");
    	kupci.setItemCaptionGenerator(Komitent::getNaziv);
    	
    	roba = new ComboBox<>();
    	roba.setPlaceholder("Roba");
    	roba.setItemCaptionGenerator(Roba::getNaziv);
    	
    	export = new Button("Export");
    	export.setStyleName(ValoTheme.BUTTON_PRIMARY);
    	
    	
        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSizeUndefined();
        topLayout.addComponent(veleprodaja);
        topLayout.addComponent(datum);
        topLayout.addComponent(kupci);
        topLayout.addComponent(roba);
        topLayout.addComponent(export);
        topLayout.setStyleName("top-bar");
        return topLayout;
    }
    
    public PregledNaloga getForm() {
    	return this;
    }
}
