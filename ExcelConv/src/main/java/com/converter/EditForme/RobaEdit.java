package com.converter.EditForme;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Model.Roba;
import com.converter.Model.RobaGrupa;
import com.converter.Service.RobaService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@Theme("mytheme")
@SuppressWarnings("serial")
public class RobaEdit extends Window{

	public TextField sifra;
	public TextField naziv;
	public TextField cena;
	public  ComboBox<RobaGrupa> grupa;
	public TextField jm;
	
	public Binder<Roba> binder = new Binder<>(Roba.class);
	
	public Button ok;
	public Button delete;
	
	public Roba roba;
	
	private Grid<Roba> robaGrid;
	private TextField filter;
	
	@Autowired
	public RobaEdit() {
		addStyleName("profile-window");
	    Responsive.makeResponsive(this);
	        
	    setModal(true);
	    setClosable(true);
	    addCloseShortcut(KeyCode.ESCAPE, null);
	    setResizable(false);
	    setHeight(90.0f, Unit.PERCENTAGE);

	    VerticalLayout content = new VerticalLayout();
	    content.setSizeFull();
	    content.setMargin(new MarginInfo(true, false, false, false));
	    setContent(content);

	    TabSheet detailsWrapper = new TabSheet();
	    detailsWrapper.setSizeFull();
	    detailsWrapper.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
	    detailsWrapper.addStyleName(ValoTheme.TABSHEET_ICONS_ON_TOP);
	    detailsWrapper.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
	    content.addComponentsAndExpand(detailsWrapper);
	    //content.addComponent(detailsWrapper);
	    //content.setExpandRatio(detailsWrapper, 1f);

	    detailsWrapper.addComponent(buildProfileTab());
	    initBind();
	    content.addComponent(buildFooter());
	}
	
	private Component buildProfileTab() {
        HorizontalLayout root = new HorizontalLayout();
        root.setCaption("Roba");
        root.setIcon(VaadinIcons.BULLETS);
        root.setWidth(100.0f, Unit.PERCENTAGE);
        root.setSpacing(true);
        root.setMargin(true);
        root.addStyleName("profile-form");

        FormLayout details = new FormLayout();
        details.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        root.addComponent(details);
        root.setExpandRatio(details, 1);

        sifra = new TextField("Šifra");
        details.addComponent(sifra);
        
        naziv = new TextField("Naziv");
        details.addComponent(naziv);
        
    	cena = new TextField("Cena");
    	details.addComponent(cena);
    	 
    	grupa = new ComboBox<>();
    	grupa.setPlaceholder("Grupa");
    	grupa.setItemCaptionGenerator(RobaGrupa :: getNaziv);
    	details.addComponent(grupa);
    	 
    	jm = new TextField("JM");
    	details.addComponent(jm);
    	 
        return root;
    }

    private Component buildFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
        footer.setWidth(100.0f, Unit.PERCENTAGE);

        CssLayout dugme = new CssLayout();
        ok = new Button("OK");
        ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
        ok.setIcon(VaadinIcons.DISC);
   
        delete = new Button("Izbriši");
        delete.addStyleName(ValoTheme.BUTTON_DANGER);
        delete.setIcon(VaadinIcons.FILE_REMOVE);

        dugme.addComponents(ok, delete);
        dugme.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        footer.addComponent(dugme);
        footer.setComponentAlignment(dugme, Alignment.TOP_RIGHT);
        return footer;
    }
    
    public final void edit(Roba r, RobaService rser){
		final boolean persisted = r != null;
		if (persisted) {
			roba = rser.findOne(r.getId());
		}
		else {
			roba = new Roba();
		}
		binder.setBean(roba);
    }
    
    public void initBind(){
    	 binder.forField(sifra)
    	 	.withNullRepresentation("")
    		.withConverter(new StringToIntegerConverter(Integer.valueOf(0), "Samo brojevi"))
     		.bind(Roba :: getSifra, Roba :: setSifra);
    	 binder.forField(naziv)
    	 	.withNullRepresentation("")
    	 	.bind(Roba :: getNaziv, Roba :: setNaziv);
    	 binder.forField(cena)
    	 	.withNullRepresentation("")
    	 	.withConverter(new StringToDoubleConverter(Double.valueOf(0), "Samo brojevi"))
    	 	.bind(Roba :: getCena, Roba :: setCena);
    	 binder.forField(grupa)
    	 	.bind(Roba :: getRobagrupa, Roba :: setRobagrupa);
    	 binder.forField(jm)
    	 	.withNullRepresentation("")
    	 	.bind(Roba :: getJm, Roba :: setJm);
    }
    
    
    
    public Grid<Roba> getRobaGrid() {
		return robaGrid;
	}

	public void setRobaGrid(Grid<Roba> robaGrid) {
		this.robaGrid = robaGrid;
	}
	
	

	public TextField getFilter() {
		return filter;
	}

	public void setFilter(TextField filter) {
		this.filter = filter;
	}

	public RobaEdit getWindow(){
    	return this;
    }
}
