package com.converter.Views;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Component.RecieverUploadFajl;
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
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@UIScope
@Theme("mytheme")
public class ExcelView extends HorizontalLayout{

	public static final String VIEW_NAME = "excelView";
	
	public VerticalLayout vlLijevo = new VerticalLayout();
	public VerticalLayout vlDesno = new VerticalLayout();
	
	public VerticalLayout tabGridPrvi = new VerticalLayout();
	public VerticalLayout tabGridDrugi = new VerticalLayout();
	
	public VerticalLayout desno = new VerticalLayout();
	public HorizontalLayout desnoPrvi = new HorizontalLayout();
	public HorizontalLayout desnoDrugi = new HorizontalLayout();
	public HorizontalLayout desnoTreci = new HorizontalLayout();
	public HorizontalLayout desnoCetvrti = new HorizontalLayout();
	
	
	public DateField datum = new DateField();
	public ComboBox<Komitent> veleprodaja = new ComboBox<>("Veleprodaja");
	public ComboBox<OrganizacionaJedinica> orgjed = new ComboBox<>("Org. jedinica");
	public Button otvoriNalog = new Button("Otvori nalog", VaadinIcons.FILE);
	public Button importFajl = new Button("Import Excel", VaadinIcons.CREDIT_CARD);
	public Button autObrada = new Button("Automatska obrada", VaadinIcons.HANDLE_CORNER);
	public Button zatvoriNalog = new Button("Zatvori nalog", VaadinIcons.ARROW_CIRCLE_UP);
	
	
	
	public TextField robasifraext = new TextField("Šifra robe");
	public TextField robanazivext = new TextField("Naziv robe");
	public TextField kupacsifraext= new TextField("Šifra komitenta");
	public TextField kupacnazivext= new TextField("Naziv komitenta");
	public TextField cena= new TextField("Cena");
	public TextField kolicina= new TextField("Količina");
	public TextField iznos= new TextField("Vrijednost");
	
	public ComboBox<Komitent> komitent = new ComboBox<>();
	public ComboBox<Roba> roba = new ComboBox<>();
	public Button updateZapis=new Button("Ažuriraj stavku", VaadinIcons.UPLOAD);
	
	public Binder<Nalog> nalogBinder = new Binder<>(Nalog.class);
	public Binder<NalogStavka> nalogstBinder = new Binder<>(NalogStavka.class);

	public Grid<Apoteka> gridStavke = new Grid<>(Apoteka.class);
	public Grid<NalogStavka> gridStavkeGotove = new Grid<>(NalogStavka.class);
	
	public Panel panelDrugi = new Panel();
	public Nalog nalogProvjera;
	public NalogStavka nsProvjera;
	
	public RecieverUploadFajl lineBreakCounter = new RecieverUploadFajl();
    public Upload upload = new Upload(null, lineBreakCounter);
    public ImportFajl uploadInfoWindow = new ImportFajl(upload, lineBreakCounter);
    public TabSheet tabovi = new TabSheet();
    
    
	@Autowired
	public ExcelView() {
		
		Responsive.makeResponsive(this);
		setSizeFull();
		
		tabovi.addStyleName(ValoTheme.TABSHEET_FRAMED);
		tabovi.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		tabGridPrvi.addComponent(gridStavke);
		tabGridPrvi.setMargin(true);
		tabGridDrugi.addComponent(gridStavkeGotove);
		tabGridDrugi.setMargin(true);
		gridStavkeGotove.setSizeFull();
        tabovi.addTab(tabGridPrvi, "Neobrađene stavke");
        tabovi.addTab(tabGridDrugi, "Pregled");
        
		datum.setResolution(DateResolution.MONTH);
		datum.setWidth(250, Unit.PIXELS);
		addStyleName("jebeniStil");
		
	    upload.setImmediateMode(false);
	    upload.setButtonCaption("Import");
	    upload.setVisible(false);
	    
		otvoriNalog.addStyleName(ValoTheme.BUTTON_PRIMARY);
		otvoriNalog.setWidth(250, Unit.PIXELS);
		importFajl.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		importFajl.setWidth(250, Unit.PIXELS);
		updateZapis.addStyleName(ValoTheme.BUTTON_PRIMARY);
		autObrada.setWidth(250, Unit.PIXELS);
		autObrada.addStyleName(ValoTheme.BUTTON_PRIMARY);
		zatvoriNalog.addStyleName(ValoTheme.BUTTON_DANGER);
		zatvoriNalog.setWidth(250, Unit.PIXELS);
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
		vlLijevo.addComponents(orgjed, veleprodaja, datum, otvoriNalog, importFajl, autObrada, zatvoriNalog);
		vlLijevo.setSizeUndefined();
		
		robasifraext.setWidth(150, Unit.PIXELS);
		robanazivext.setWidth(350, Unit.PIXELS);
		desnoPrvi.addComponents(robasifraext, robanazivext);
		
		kupacsifraext.setWidth(150, Unit.PIXELS);
		kupacnazivext.setWidth(350, Unit.PIXELS);
		desnoDrugi.addComponents(kupacsifraext, kupacnazivext);
		desnoTreci.addComponents(kolicina, iznos,cena);
		desnoCetvrti.addComponents(komitent, roba, updateZapis);
		
		desno.addComponents(desnoPrvi, desnoDrugi, desnoTreci, desnoCetvrti);
		
		desno.setSizeUndefined();
		gridStavke.setSizeFull();
		vlDesno.addComponents(upload, desno, tabovi);
		vlDesno.setExpandRatio(tabovi, 1);
		vlDesno.setSizeFull();
		
		panelDrugi.setContent(vlDesno);
		panelDrugi.setSizeFull();
		
		initNalogBinder();
		initNalogStavkaBinder();
		addComponents(vlLijevo, panelDrugi);
		setExpandRatio(panelDrugi, 1);
		setMargin(false);
		setSpacing(false);
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
