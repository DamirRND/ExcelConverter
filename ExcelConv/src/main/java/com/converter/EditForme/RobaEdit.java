package com.converter.EditForme;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Model.Komitent;
import com.converter.Model.Roba;
import com.converter.Model.RobaGrupa;
import com.converter.Service.RobaGrupaService;
import com.converter.Service.RobaService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@SpringComponent
@Theme("mytheme")
@SuppressWarnings("serial")
public class RobaEdit extends Window{

	private TextField sifra;
	private TextField naziv;
	private TextField cena;
	private ComboBox<RobaGrupa> grupa;
	private TextField jm;
	
	public Binder<Roba> binder = new Binder<>(Roba.class);
	
	private Roba roba;
	
	private final RobaService rser;
	private final RobaGrupaService rgser;
	
	
	@Autowired
	public RobaEdit(RobaService rser, RobaGrupaService rgser) {
		super();
		this.rser = rser;
		this.rgser = rgser;
		
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
        root.setCaption("Aktivnost");
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
    	grupa.setItems(rgser.findAll());
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
        Button ok = new Button("OK");
        ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
        ok.setIcon(VaadinIcons.DISC);
   
        Button delete = new Button("Izbriši");
        delete.addStyleName(ValoTheme.BUTTON_DANGER);
        delete.setIcon(VaadinIcons.FILE_REMOVE);
        
        ok.addClickListener(event->{
    	   try{
    		   rser.save(roba);
    		   ((UI) getWindow().getParent()).removeWindow(getWindow());
    		   Notification success = new Notification(
                       "Roba uspješno sačuvana");
               success.setDelayMsec(2000);
               success.setStyleName("bar success small");
               success.setPosition(Position.BOTTOM_CENTER);
               success.show(Page.getCurrent());
    	   }catch(Exception ec){
    		   ec.printStackTrace();
    	   }
       });
 
        delete.addClickListener(event->{
        	 try{
        		 rser.delete(roba);
      		   ((UI) getWindow().getParent()).removeWindow(getWindow());
      		   Notification success = new Notification(
                         "Roba uspješno izbrisana");
                 success.setDelayMsec(2000);
                 success.setStyleName("bar success small");
                 success.setPosition(Position.BOTTOM_CENTER);
                 success.show(Page.getCurrent());
      	   }catch(Exception ec){
      		   ec.printStackTrace();
      	   }
        });
        
        ok.focus();
        dugme.addComponents(ok, delete);
        dugme.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        footer.addComponent(dugme);
        footer.setComponentAlignment(dugme, Alignment.TOP_RIGHT);
        return footer;
    }
    
    public final void edit(Roba r){
		final boolean persisted = r != null;
		if (persisted) {
			System.out.println(rser.findOne(r.getId()).getNaziv());
			roba = rser.findOne(r.getId());
		}
		else {
			roba = r;
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
    
    public RobaEdit getWindow(){
    	return this;
    }
}
