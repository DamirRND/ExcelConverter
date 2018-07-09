package com.converter.Views;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Model.Komitent;
import com.converter.Model.Nalog;
import com.converter.Model.NalogStavka;
import com.converter.Model.OrganizacionaJedinica;
import com.converter.Model.Roba;
import com.converter.Service.NalogService;
import com.converter.Service.NalogStavkaService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.datefield.DateResolution;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
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
@UIScope
@Theme("mytheme")
public class ExcelView extends HorizontalLayout{

	public static final String VIEW_NAME = "excelView";
	
	public HorizontalLayout hlPretraga = new HorizontalLayout();
	public TextField idNaloga = new TextField();
	public Button pretragaSvih = new Button();
			
	public HorizontalLayout hlZaUpload = new HorizontalLayout();
	public VerticalLayout vlLijevo = new VerticalLayout();
	public VerticalLayout vlDesno = new VerticalLayout();
	public VerticalLayout daObuhvati = new VerticalLayout();
	public VerticalLayout tabGridPrvi = new VerticalLayout();
	public VerticalLayout tabGridDrugi = new VerticalLayout();
	
	public VerticalLayout desno = new VerticalLayout();
	public HorizontalLayout desnoPrvi = new HorizontalLayout();
	public HorizontalLayout desnoDrugi = new HorizontalLayout();
	public HorizontalLayout desnoTreci = new HorizontalLayout();
	
	
	public DateField datum = new DateField();
	public ComboBox<Komitent> veleprodaja = new ComboBox<>("Veleprodaja");
	public ComboBox<OrganizacionaJedinica> orgjed = new ComboBox<>("Org. jedinica");
	public TextField nazivFajla = new TextField();
	public Button otvoriNalog = new Button("Sačuvaj nalog", VaadinIcons.UPLOAD);
	public Button importFajl = new Button("Import Excel", VaadinIcons.CREDIT_CARD);
	public Button autObrada = new Button("Automatska obrada", VaadinIcons.HANDLE_CORNER);
	public Button zatvoriNalog = new Button("Zaključaj nalog", VaadinIcons.LOCK);
	public Button noviNalog = new Button("Novi", VaadinIcons.PLUS);
	
	
	public TextField robasifraext = new TextField();
	public TextField robanazivext = new TextField();
	public TextField kupacsifraext= new TextField();
	public TextField kupacnazivext= new TextField();
	public TextField cena= new TextField("Cena");
	public TextField kolicina= new TextField("Količina");
	public TextField iznos= new TextField("Vrijednost");
	
	public ComboBox<Komitent> komitent = new ComboBox<>();
	public ComboBox<Roba> roba = new ComboBox<>();
	public Button updateZapis=new Button("Ažuriraj stavku", VaadinIcons.UPLOAD);
	public Button dodajNoviZapis = new Button("Dodaj novu stavku", VaadinIcons.PLUS);
	
	public Binder<Nalog> nalogBinder = new Binder<>(Nalog.class);
	public Binder<NalogStavka> nalogstBinder = new Binder<>(NalogStavka.class);

	public Grid<NalogStavka> gridStavke = new Grid<>(NalogStavka.class);
	public Grid<NalogStavka> gridStavkeGotove = new Grid<>(NalogStavka.class);
	
	public Panel panelDrugi = new Panel();
	public Nalog nalogProvjera;
	public NalogStavka nsProvjera;
	

    public TabSheet tabovi = new TabSheet();
    
    
	@Autowired
	public ExcelView() {
		
		Responsive.makeResponsive(this);
		setSizeFull();
		
		komitent.setPlaceholder("Komitent");
		roba.setPlaceholder("Roba");
		komitent.setWidth(512,Unit.PIXELS);
		roba.setWidth(512, Unit.PIXELS);
		pretragaSvih.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		pretragaSvih.setIcon(VaadinIcons.SEARCH);
		idNaloga.setWidth(170, Unit.PIXELS);
		noviNalog.setStyleName(ValoTheme.BUTTON_PRIMARY);
		hlPretraga.addComponents(noviNalog, idNaloga, pretragaSvih);
		hlPretraga.setSpacing(false);
		hlPretraga.setMargin(false);
		hlPretraga.setSizeUndefined();
		
		
		tabovi.addStyleName(ValoTheme.TABSHEET_FRAMED);
		tabovi.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		tabGridPrvi.addComponent(gridStavke);
		tabGridPrvi.setMargin(true);
		tabGridDrugi.addComponent(gridStavkeGotove);
		tabGridDrugi.setMargin(true);
		gridStavkeGotove.setSizeFull();
		
		gridStavke.setColumns("id","kupac","roba","robaSifraExt", "robaNazivExt", "kupacSifraExt", "kupacNazivExt", "kolicina", "iznos", "cena");
		gridStavke.getColumn("id").setHidden(true);
		gridStavke.getColumn("robaSifraExt").setHidden(true);
		gridStavke.getColumn("kupacSifraExt").setHidden(true);
		gridStavke.getColumn("robaNazivExt").setWidth(150);
		gridStavke.getColumn("kupacNazivExt").setWidth(150);
		gridStavke.getColumn("kupac").setWidth(150);
		gridStavke.getColumn("roba").setWidth(150);
		
		gridStavkeGotove.setColumns("id","kupac","roba","robaSifraExt", "robaNazivExt", "kupacSifraExt", "kupacNazivExt", "kolicina", "iznos", "cena");
		gridStavkeGotove.getColumn("id").setHidden(true);
		gridStavkeGotove.getColumn("robaSifraExt").setHidden(true);
		gridStavkeGotove.getColumn("kupacSifraExt").setHidden(true);
		gridStavkeGotove.getColumn("robaNazivExt").setWidth(150);
		gridStavkeGotove.getColumn("kupacNazivExt").setWidth(150);
		gridStavkeGotove.getColumn("kupac").setWidth(150);
		gridStavkeGotove.getColumn("roba").setWidth(150);
		
        tabovi.addTab(tabGridPrvi, "Neobrađene stavke");
        tabovi.addTab(tabGridDrugi, "Pregled");
        tabovi.getTab(tabGridPrvi).setId("neobradjeni");
        tabovi.getTab(tabGridDrugi).setId("obradjeni");
        tabovi.setSizeFull();
		datum.setResolution(DateResolution.MONTH);
		datum.setWidth(250, Unit.PIXELS);
		addStyleName("jebeniStil");
		
	   
	    
		otvoriNalog.addStyleName(ValoTheme.BUTTON_PRIMARY);
		otvoriNalog.setWidth(250, Unit.PIXELS);
		importFajl.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		importFajl.setWidth(250, Unit.PIXELS);
		updateZapis.addStyleName(ValoTheme.BUTTON_PRIMARY);
		autObrada.setWidth(250, Unit.PIXELS);
		autObrada.addStyleName(ValoTheme.BUTTON_PRIMARY);
		zatvoriNalog.addStyleName(ValoTheme.BUTTON_DANGER);
		zatvoriNalog.setWidth(250, Unit.PIXELS);
		nazivFajla.setWidth(250, Unit.PIXELS);
		komitent.setPlaceholder("Komitent");
		komitent.setItemCaptionGenerator(Komitent :: getNaziv);
		
		orgjed.setPlaceholder("Org. jedinica");
		orgjed.setItemCaptionGenerator(OrganizacionaJedinica :: getNaziv);
		orgjed.setWidth(250, Unit.PIXELS);
		
		roba.setPlaceholder("Roba");
		roba.setItemCaptionGenerator(Roba :: getNaziv);
		
		veleprodaja.setPlaceholder("Veleprodaja");
		veleprodaja.setItemCaptionGenerator(Komitent :: getNaziv);
		veleprodaja.setWidth(250, Unit.PIXELS);
		
		panelDrugi.setStyleName(ValoTheme.PANEL_WELL);
		vlLijevo.addComponents(hlPretraga,orgjed, veleprodaja, datum, otvoriNalog, importFajl,nazivFajla, hlZaUpload, autObrada, zatvoriNalog);
		vlLijevo.setSizeFull();
		daObuhvati.addComponents(hlPretraga, vlLijevo);
		daObuhvati.setMargin(false);
		daObuhvati.setSpacing(false);
		daObuhvati.setExpandRatio(vlLijevo, 1);
		daObuhvati.setSizeUndefined();
		robasifraext.setWidth(150, Unit.PIXELS);
		robanazivext.setWidth(350, Unit.PIXELS);
		desnoPrvi.addComponents(robasifraext, robanazivext);
		desnoPrvi.setSizeUndefined();
		kupacsifraext.setWidth(150, Unit.PIXELS);
		kupacnazivext.setWidth(350, Unit.PIXELS);
		desnoDrugi.addComponents(kupacsifraext, kupacnazivext);
		desnoTreci.addComponents(cena, kolicina, iznos,updateZapis, dodajNoviZapis);
		desnoTreci.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);
		desnoDrugi.setSizeUndefined();
		desnoTreci.setSizeUndefined();
		
		dodajNoviZapis.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		desno.addComponents(komitent,desnoPrvi, roba, desnoDrugi, desnoTreci);
		desno.setMargin(false);
		desno.setSizeUndefined();
		gridStavke.setSizeFull();
		hlZaUpload.setSizeUndefined();
		hlZaUpload.setSpacing(false);
		hlZaUpload.setMargin(false);
		vlDesno.addComponents(desno, tabovi);
		vlDesno.setExpandRatio(tabovi, 1);
		vlDesno.setSizeFull();
		
		panelDrugi.setContent(vlDesno);
		panelDrugi.setSizeFull();
		
		initNalogBinder();
		initNalogStavkaBinder();
		addComponents(daObuhvati, panelDrugi);
		setExpandRatio(panelDrugi, 1);
		setMargin(false);
		setSpacing(false);
	}
	
	public final void provjera(Nalog n, NalogService ns){
		final boolean persisted = n != null;
		if (persisted) {
			nalogProvjera = ns.findOne(n.getId());
		}else {
			nalogProvjera = new Nalog();
		}
		nalogBinder.setBean(nalogProvjera);
    }
	
	public final void insert(NalogStavka ns , NalogStavkaService nss) {
		nsProvjera = ns;
		nalogstBinder.setBean(nsProvjera);
	}

	public void initNalogBinder() {
		nalogBinder.forField(datum)
	 		.bind(Nalog :: getDatum, Nalog :: setDatum);
		nalogBinder.forField(veleprodaja)
		 	.bind(Nalog :: getKomitent, Nalog :: setKomitent);
		nalogBinder.forField(orgjed)
	 		.bind(Nalog :: getOrgjed, Nalog :: setOrgjed);
	}
	
	public void initNalogStavkaBinder() {
		nalogstBinder.forField(robasifraext)
		 	.withNullRepresentation("")
			.withConverter(new StringToIntegerConverter(Integer.valueOf(0), "Samo brojevi"))
	 		.bind(NalogStavka :: getRobaSifraExt, NalogStavka :: setRobaSifraExt);
		nalogstBinder.forField(robanazivext)
		 	.bind(NalogStavka :: getRobaNazivExt, NalogStavka :: setRobaNazivExt);
		nalogstBinder.forField(kupacsifraext)
			.withNullRepresentation("")
			.withConverter(new StringToIntegerConverter(Integer.valueOf(0), "Samo brojevi"))
	 		.bind(NalogStavka :: getKupacSifraExt, NalogStavka :: setKupacSifraExt);
		nalogstBinder.forField(kupacnazivext)
 			.bind(NalogStavka :: getKupacNazivExt, NalogStavka :: setKupacNazivExt);
		nalogstBinder.forField(cena)
			.withNullRepresentation("")
			.withConverter(new StringToDoubleConverter(Double.valueOf(0), "Samo brojevi"))
			.bind(NalogStavka :: getCena, NalogStavka :: setCena);
		nalogstBinder.forField(kolicina)
			.withNullRepresentation("")
			.withConverter(new StringToDoubleConverter(Double.valueOf(0), "Samo brojevi"))
			.bind(NalogStavka :: getKolicina, NalogStavka :: setKolicina);
		nalogstBinder.forField(iznos)
			.withNullRepresentation("")
			.withConverter(new StringToDoubleConverter(Double.valueOf(0), "Samo brojevi"))
			.bind(NalogStavka :: getIznos, NalogStavka :: setIznos);
		nalogstBinder.forField(komitent)
			.bind(NalogStavka :: getKupac, NalogStavka :: setKupac);
		nalogstBinder.forField(roba)
			.bind(NalogStavka :: getRoba, NalogStavka :: setRoba);
	}
	
	public ExcelView getForm() {
		return this;
	}
	
}
