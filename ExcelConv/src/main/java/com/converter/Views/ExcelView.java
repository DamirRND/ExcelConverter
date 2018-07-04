package com.converter.Views;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Model.Komitent;
import com.converter.Model.NalogStavka;
import com.converter.Model.Roba;
import com.converter.Service.KomitentService;
import com.converter.Service.RobaService;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.datefield.DateResolution;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@UIScope
@Theme("mytheme")
public class ExcelView extends VerticalLayout{

	public static final String VIEW_NAME = "excelView";
	

	
	public VerticalLayout layoutroota = new VerticalLayout();
	public HorizontalLayout rootPrviDio = new HorizontalLayout();
	public HorizontalLayout lijevo = new HorizontalLayout();
	public VerticalLayout hlLijevo = new VerticalLayout();
	public VerticalLayout hlDesno = new VerticalLayout();
	
	
	public VerticalLayout desno = new VerticalLayout();
	public HorizontalLayout desnoPrvi = new HorizontalLayout();
	public HorizontalLayout desnoDrugi = new HorizontalLayout();
	public HorizontalLayout desnoTreci = new HorizontalLayout();
	public HorizontalLayout desnoCetvrti = new HorizontalLayout();
	
	
	public DateField datum = new DateField();
	public ComboBox<Komitent> veleprodaja = new ComboBox<>();
	public Button otvoriNalog = new Button("Otvori nalog", VaadinIcons.FILE);
	public Button importFajl = new Button("Import Excel", VaadinIcons.CREDIT_CARD);
	public Button automObrada= new Button("Automatska obrada", VaadinIcons.HAMMER);
	
	
	public TextField robasifraext = new TextField();
	public TextField robanazivext = new TextField();
	public TextField kupacsifraext= new TextField();
	public TextField kupacnazivext= new TextField();
	public TextField cena= new TextField();
	public TextField kolicina= new TextField();
	public TextField iznos= new TextField();
	
	public ComboBox<Komitent> komitent = new ComboBox<>();
	public ComboBox<Roba> roba = new ComboBox<>();
	public Button updateZapis=new Button("Ažuriraj stavku", VaadinIcons.UPLOAD);
	 

	public Grid<NalogStavka> gridStavke = new Grid<>(NalogStavka.class);
	
	public Panel panelPrvi = new Panel();

	public Panel panelDrugi = new Panel();
	
	@Autowired
	public ExcelView() {
		
		Responsive.makeResponsive(this);
		setSizeFull();
		datum.setResolution(DateResolution.MONTH);
		
		otvoriNalog.addStyleName(ValoTheme.BUTTON_PRIMARY);
		importFajl.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		automObrada.addStyleName(ValoTheme.BUTTON_PRIMARY);
		updateZapis.addStyleName(ValoTheme.BUTTON_PRIMARY);
		
		komitent.setPlaceholder("Komitent");
		komitent.setItemCaptionGenerator(Komitent :: getNaziv);
		komitent.setStyleName("filter-tekstpolje");
		
		roba.setPlaceholder("Roba");
		roba.setItemCaptionGenerator(Roba :: getNaziv);
		roba.setStyleName("filter-tekstpolje");
		
		veleprodaja.setPlaceholder("Veleprodaja");
		veleprodaja.setItemCaptionGenerator(Komitent :: getNaziv);
		veleprodaja.setStyleName("filter-tekstpolje");
		
		panelPrvi.setStyleName(ValoTheme.PANEL_BORDERLESS);
		panelDrugi.setStyleName(ValoTheme.PANEL_BORDERLESS);
		hlLijevo.addComponents(datum, veleprodaja, otvoriNalog);
		hlDesno.addComponents(importFajl, automObrada);
		lijevo.addComponents(hlLijevo, hlDesno);
		lijevo.addStyleName(ValoTheme.LAYOUT_CARD);
		
		robasifraext.setStyleName("filter-tekstpolje");
		robanazivext.setStyleName("filter-tekstpolje");
		kupacsifraext.setStyleName("filter-tekstpolje");
		
		kupacnazivext.setStyleName("filter-tekstpolje");
		cena.setStyleName("filter-tekstpolje");
		kolicina.setStyleName("filter-tekstpolje");
		iznos.setStyleName("filter-tekstpolje");
		
		desnoPrvi.addComponents(robasifraext, robanazivext, kupacsifraext);
		desnoDrugi.addComponents(kupacnazivext, cena, kolicina);
		desnoTreci.addComponents(iznos);
		desnoCetvrti.addComponents(komitent, roba, updateZapis);
		
		desno.addComponents(desnoPrvi, desnoDrugi, desnoTreci, desnoCetvrti);
		desno.addStyleName(ValoTheme.LAYOUT_CARD);
		lijevo.setSizeUndefined();
		desno.setSizeFull();
		rootPrviDio.addComponents(lijevo, desno);
		rootPrviDio.setExpandRatio(desno, 1);
		
		layoutroota.addComponent(rootPrviDio);
		layoutroota.setSizeUndefined();

		gridStavke.setSizeFull();
		panelPrvi.setContent(layoutroota);
		panelDrugi.setContent(gridStavke);
		panelPrvi.setSizeUndefined();
		panelDrugi.setSizeFull();
		
		addComponents(panelPrvi, panelDrugi);
		setExpandRatio(panelDrugi, 1);
	}
	
	public ExcelView getForm() {
		return this;
	}
	
}
