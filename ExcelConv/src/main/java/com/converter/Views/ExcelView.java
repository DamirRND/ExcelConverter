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
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
@Theme("mytheme")
public class ExcelView extends VerticalLayout{

	public static final String VIEW_NAME = "excelView";
	

	private final KomitentService kser;
	private final RobaService rser;
	
	
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
	public ExcelView(KomitentService kser, RobaService rser) {
		this.kser=kser;
		this.rser = rser;
		Responsive.makeResponsive(this);
		setSizeFull();
		komitent.setPlaceholder("Komitent");
		komitent.setItems(kser.findAllByTip("KK"));
		komitent.setItemCaptionGenerator(Komitent :: getNaziv);
		
		roba.setPlaceholder("Roba");
		roba.setItems(rser.findAllCombo());
		roba.setItemCaptionGenerator(Roba :: getNaziv);
		
		veleprodaja.setPlaceholder("Veleprodaja");
		veleprodaja.setItems(kser.findAllByTip("VP"));
		veleprodaja.setItemCaptionGenerator(Komitent :: getNaziv);
		
		panelPrvi.setStyleName(ValoTheme.PANEL_BORDERLESS);
		panelDrugi.setStyleName(ValoTheme.PANEL_BORDERLESS);
		hlLijevo.addComponents(datum, veleprodaja, otvoriNalog);
		hlDesno.addComponents(importFajl, automObrada);
		lijevo.addComponents(hlLijevo, hlDesno);
		lijevo.addStyleName(ValoTheme.LAYOUT_CARD);
		
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
		rootPrviDio.setSizeUndefined();
		gridStavke.setSizeFull();
		panelPrvi.setContent(rootPrviDio);
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
