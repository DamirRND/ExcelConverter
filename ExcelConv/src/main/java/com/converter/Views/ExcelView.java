package com.converter.Views;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Model.Apoteka;
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
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
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
	
	
	public InlineDateField datum = new InlineDateField();
	public ComboBox<Komitent> veleprodaja = new ComboBox<>();
	public ComboBox<OrganizacionaJedinica> orgjed = new ComboBox<>();
	public Button otvoriNalog = new Button("Otvori nalog", VaadinIcons.FILE);
	public Button importFajl = new Button("Import Excel", VaadinIcons.CREDIT_CARD);
	
	
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
	
	public Binder<Nalog> nalogBinder = new Binder<>(Nalog.class);
	public Binder<NalogStavka> nalogstBinder = new Binder<>(NalogStavka.class);

	public Grid<Apoteka> gridStavke = new Grid<>(Apoteka.class);
	
	public Panel panelPrvi = new Panel();

	public Panel panelDrugi = new Panel();
	public Nalog nalogProvjera;
	public NalogStavka nsProvjera;
	@Autowired
	public ExcelView() {
		
		Responsive.makeResponsive(this);
		setSizeFull();
		datum.setResolution(DateResolution.MONTH);
		addStyleName("jebeniStil");
		
		otvoriNalog.addStyleName(ValoTheme.BUTTON_PRIMARY);
		importFajl.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		updateZapis.addStyleName(ValoTheme.BUTTON_PRIMARY);
		
		komitent.setPlaceholder("Komitent");
		komitent.setItemCaptionGenerator(Komitent :: getNaziv);
		komitent.setStyleName("filter-tekstpolje");
		
		orgjed.setPlaceholder("Org. jedinica");
		orgjed.setItemCaptionGenerator(OrganizacionaJedinica :: getNaziv);
		orgjed.setStyleName("filter-tekstpolje");
		
		roba.setPlaceholder("Roba");
		roba.setItemCaptionGenerator(Roba :: getNaziv);
		roba.setStyleName("filter-tekstpolje");
		
		veleprodaja.setPlaceholder("Veleprodaja");
		veleprodaja.setItemCaptionGenerator(Komitent :: getNaziv);
		veleprodaja.setStyleName("filter-tekstpolje");
		
		panelPrvi.setStyleName(ValoTheme.PANEL_BORDERLESS);
		panelDrugi.setStyleName(ValoTheme.PANEL_BORDERLESS);
		hlLijevo.addComponents(datum, veleprodaja,orgjed, otvoriNalog);
		hlDesno.addComponent(importFajl);
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
		
		initNalogBinder();
		initNalogStavkaBinder();
		addComponents(panelPrvi, panelDrugi);
		setExpandRatio(panelDrugi, 1);
	}
	
	public final void provjera(Nalog n, NalogService ns){
		final boolean persisted = n != null;
		if (persisted) {
			nalogProvjera = ns.findOne(n.getId());
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
	 		.bind(NalogStavka :: getRobasifraext, NalogStavka :: setRobasifraext);
		nalogstBinder.forField(robanazivext)
		 	.bind(NalogStavka :: getRobanazivext, NalogStavka :: setRobanazivext);
		nalogstBinder.forField(kupacsifraext)
			.withNullRepresentation("")
			.withConverter(new StringToIntegerConverter(Integer.valueOf(0), "Samo brojevi"))
	 		.bind(NalogStavka :: getKupacsifraext, NalogStavka :: setKupacsifraext);
		nalogstBinder.forField(kupacnazivext)
 			.bind(NalogStavka :: getKupacnazivext, NalogStavka :: setKupacnazivext);
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
			.bind(NalogStavka :: getKupac_id, NalogStavka :: setKupac_id);
		nalogstBinder.forField(roba)
			.bind(NalogStavka :: getRoba, NalogStavka :: setRoba);
	}
	
	public ExcelView getForm() {
		return this;
	}
	
}
