package com.converter.EditForme;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Model.Entitet;
import com.converter.Service.EntitetService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
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
public class EntitetEdit  extends Window{
	public TextField sifra;
	public TextField naziv;
	
	public Binder<Entitet> binder = new Binder<>(Entitet.class);
	
	public Button ok;
	public Button delete;
	
	public Entitet entitet;
	
	private Grid<Entitet> eGrid;
	private TextField filter;
	
	
	@Autowired
	public EntitetEdit() {
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
        root.setCaption("Entitet");
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
    
    public final void edit(Entitet e, EntitetService eser){
		final boolean persisted = e != null;
		if (persisted) {
			entitet = eser.findOne(e.getId());
		}
		else {
			entitet = new Entitet();
		}
		binder.setBean(entitet);
    }
    
    public void initBind(){
    	 binder.forField(sifra)
    	 	.withNullRepresentation("")
    		.withConverter(new StringToIntegerConverter(Integer.valueOf(0), "Samo brojevi"))
     		.bind(Entitet :: getSifra, Entitet :: setSifra);
    	 binder.forField(naziv)
    	 	.withNullRepresentation("")
    	 	.bind(Entitet :: getNaziv, Entitet :: setNaziv);
    }
    
    

	public TextField getFilter() {
		return filter;
	}

	public void setFilter(TextField filter) {
		this.filter = filter;
	}

	public Grid<Entitet> geteGrid() {
		return eGrid;
	}

	public void seteGrid(Grid<Entitet> eGrid) {
		this.eGrid = eGrid;
	}

	public EntitetEdit getWindow(){
    	return this;
    }

}
