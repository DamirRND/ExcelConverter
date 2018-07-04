package com.converter.EditForme;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Model.KorGrupa;
import com.converter.Model.Radnik;
import com.converter.Service.RadnikService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
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
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@Theme("mytheme")
@SuppressWarnings("serial")
public class RadniciEdit extends Window{

	
	public TextField korime;
	public PasswordField korlozinka;
	public TextField naziv;
	public  ComboBox<KorGrupa> grupa;
	
	public Binder<Radnik> binder = new Binder<>(Radnik.class);
	
	public Button ok;
	public Button delete;
	
	public Radnik radnik;
	
	private Grid<Radnik> radnikGrid;
	
	@Autowired
	public RadniciEdit() {
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
        root.setCaption("Radnik");
        root.setIcon(VaadinIcons.BULLETS);
        root.setWidth(100.0f, Unit.PERCENTAGE);
        root.setSpacing(true);
        root.setMargin(true);
        root.addStyleName("profile-form");

        FormLayout details = new FormLayout();
        details.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        root.addComponent(details);
        root.setExpandRatio(details, 1);

        korime = new TextField("Korisničko ime");
        details.addComponent(korime);
        
        korlozinka = new PasswordField("Šifra");
        details.addComponent(korlozinka);
    	 
    	grupa = new ComboBox<>();
    	grupa.setPlaceholder("Grupa");
    	grupa.setItemCaptionGenerator(KorGrupa :: getOpis);
    	details.addComponent(grupa);
    	
        naziv = new TextField("Ime i prezime");
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
    
    public final void edit(Radnik r, RadnikService rser){
		final boolean persisted = r != null;
		if (persisted) {
			radnik = rser.findOne(r.getId());
		}
		else {
			radnik = new Radnik();
		}
		binder.setBean(radnik);
    }
    
    public void initBind(){
    	 binder.forField(korime)
    	 	.withNullRepresentation("")
     		.bind(Radnik :: getKorime, Radnik :: setKorime);
    	 binder.forField(korlozinka)
    	 	.withNullRepresentation("")
    	 	.bind(Radnik :: getKorlozinka, Radnik :: setKorlozinka);
    	 binder.forField(grupa)
 	 		.bind(Radnik :: getKorgrupa, Radnik :: setKorgrupa);
    	 binder.forField(naziv)
    	 	.withNullRepresentation("")
    	 	.bind(Radnik :: getNaziv, Radnik :: setNaziv);
    
    }
    
    


	public Grid<Radnik> getRadnikGrid() {
		return radnikGrid;
	}

	public void setRadnikGrid(Grid<Radnik> radnikGrid) {
		this.radnikGrid = radnikGrid;
	}

	public RadniciEdit getWindow(){
    	return this;
    }
	
}
