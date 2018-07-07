package com.converter.Views;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Controller.EntitetController;
import com.converter.Controller.EntitetEditController;
import com.converter.Controller.ExcelViewController;
import com.converter.Controller.KomitentController;
import com.converter.Controller.KomitentEditController;
import com.converter.Controller.KorGrupaController;
import com.converter.Controller.KorGrupaEditController;
import com.converter.Controller.MestoController;
import com.converter.Controller.MestoEditController;
import com.converter.Controller.OrgJedController;
import com.converter.Controller.OrgJedEditController;
import com.converter.Controller.PregledNalogaController;
import com.converter.Controller.RadnikController;
import com.converter.Controller.RadnikEditController;
import com.converter.Controller.RegionController;
import com.converter.Controller.RegionEditController;
import com.converter.Controller.RobaController;
import com.converter.Controller.RobaEditController;
import com.converter.Controller.RobaGrupeController;
import com.converter.Controller.RobaGrupeEditController;
import com.converter.Controller.UstanoveController;
import com.converter.Controller.UstanoveEditController;
import com.converter.Service.EntitetService;
import com.converter.Service.KomitentService;
import com.converter.Service.KorGrupaService;
import com.converter.Service.MapaKupacService;
import com.converter.Service.MapaRobeService;
import com.converter.Service.MestoService;
import com.converter.Service.NalogService;
import com.converter.Service.NalogStavkaService;
import com.converter.Service.OrganizacionaJedinicaService;
import com.converter.Service.RadnikService;
import com.converter.Service.RegionService;
import com.converter.Service.RobaGrupaService;
import com.converter.Service.RobaService;
import com.converter.Service.UstanovaService;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;

@SpringView(name=MainScreenView.VIEW_NAME)
@UIScope
@Theme("mytheme")
@SuppressWarnings("serial")
public class MainScreenView extends SideMenu implements View{

	
	public static final String VIEW_NAME = "mainScreenView";
	
	private RobaController robaview;
	@SuppressWarnings("unused")
	private RobaService rser;
	@SuppressWarnings("unused")
	private RobaEditController redit;
	
	private RobaGrupeController rgview;
	@SuppressWarnings("unused")
	private RobaGrupaService rgser;
	@SuppressWarnings("unused")
	private RobaGrupeEditController rgeditcont;
	
	private ExcelViewController excelView;
	@SuppressWarnings("unused")
	private NalogService ns;
	
	private UstanoveController uview;
	@SuppressWarnings("unused")
	private UstanovaService user;
	@SuppressWarnings("unused")
	private UstanoveEditController uedit;
	
	private RegionController rview;
	@SuppressWarnings("unused")
	private RegionService regser;
	@SuppressWarnings("unused")
	private RegionEditController rgedit;
	
	private OrgJedController orgview;
	@SuppressWarnings("unused")
	private OrganizacionaJedinicaService orgser;
	@SuppressWarnings("unused")
	private OrgJedEditController orgedit;
	
	private RadnikController raview;
	@SuppressWarnings("unused")
	private RadnikService raser;
	@SuppressWarnings("unused")
	private RadnikEditController redc;
	
	private KorGrupaController kgview;
	@SuppressWarnings("unused")
	private KorGrupaService kgser;
	@SuppressWarnings("unused")
	private KorGrupaEditController kgedit;
	
	private KomitentController kview;
	@SuppressWarnings("unused")
	private KomitentService kser;
	@SuppressWarnings("unused")
	private KomitentEditController kedit;
	
	private EntitetController eview;
	@SuppressWarnings("unused")
	private EntitetService eserv;
	@SuppressWarnings("unused")
	private EntitetEditController eedit;
	
	private MestoController mview;
	@SuppressWarnings("unused")
	private MestoService mser;
	@SuppressWarnings("unused")
	private MestoEditController medit;
	
	@SuppressWarnings("unused")
	private NalogStavkaService nss;
	@SuppressWarnings("unused")
	private MapaRobeService ms;
	@SuppressWarnings("unused")
	private MapaKupacService mks;
	
	private PregledNalogaController pview;
	
	@Autowired
	public MainScreenView(
			RobaService rser, 
			RobaGrupaService rgser,
			UstanovaService user,
			RegionService regser,
			OrganizacionaJedinicaService orgser,
			RadnikService raser,
			KorGrupaService kgser,
			KomitentService kser,
			EntitetService eserv,
			MestoService mser,
			RobaEditController redit,
			RobaGrupeEditController rgeditcont,
			UstanoveEditController uedit,
			EntitetEditController eedit,
			OrgJedEditController orgedit,
			RegionEditController rgedit,
			RadnikEditController redc,
			KorGrupaEditController kgedit,
			KomitentEditController kedit,
			MestoEditController medit,
			NalogService ns,
			NalogStavkaService nss,
			MapaRobeService ms,
			MapaKupacService mks) {
		this.rser = rser;
		this.rgser = rgser;
		this.user = user;
		this.regser = regser;
		this.orgser = orgser;
		this.raser =raser;
		this.kgser = kgser;
		this.kser = kser;
		this.eserv = eserv;
		this.redit = redit;
		this.uedit = uedit;
		this.orgedit = orgedit;
		this.rgedit = rgedit;
		this.redc = redc;
		this.kgedit = kgedit;
		this.kedit = kedit;
		this.ns = ns;
		this.nss= nss;
		this.ms = ms;
		this.mks = mks;
		
		Responsive.makeResponsive(this);
		setUserName(VaadinSession.getCurrent().getAttribute("Ime").toString());
        setSpacing(false);
        
        addMenuItem("Korespodentska prodaja", VaadinIcons.WORKPLACE, new MenuClickHandler(){
     			public void click(){
     				excelView = new ExcelViewController(kser, rser,ns, orgser, nss, ms, mks);
     				setContent(excelView.getForm());
     			}
     	});
        
        addMenuItem("Izvještaj", VaadinIcons.USER_CARD, new MenuClickHandler(){
			public void click(){
				pview = new PregledNalogaController(kser, rser, nss, ns);
				setContent(pview.getForm());
			}
		});

        addMenuItem("Roba", VaadinIcons.BULLETS, new MenuClickHandler(){
			public void click(){
				robaview = new RobaController(rser, redit);
				setContent(robaview.getForm());
			}
		});
        
        addMenuItem("Roba grupe", VaadinIcons.FILE_TREE, new MenuClickHandler(){
			public void click(){
				rgview = new RobaGrupeController(rgser, rgeditcont);
				setContent(rgview.getForm());
			}
		});
        
        addMenuItem("Ustanove", VaadinIcons.BUILDING, new MenuClickHandler(){
			public void click(){
				uview = new UstanoveController(user, uedit);
				setContent(uview.getForm());
			}
		});
        
        addMenuItem("Entiteti", VaadinIcons.GLOBE, new MenuClickHandler(){
			public void click(){
				eview = new EntitetController(eserv, eedit);
				setContent(eview.getForm());
			}
		});
        
        addMenuItem("Organizaciona jedinica", VaadinIcons.BUILDING_O, new MenuClickHandler(){
			public void click(){
				orgview = new OrgJedController(orgser, orgedit);
				setContent(orgview.getForm());
			}
		});
        
        addMenuItem("Region", VaadinIcons.AREA_SELECT, new MenuClickHandler(){
			public void click(){
				rview = new RegionController(regser, rgedit);
				setContent(rview.getForm());
			}
		});
        

        addMenuItem("Radnici", VaadinIcons.USERS, new MenuClickHandler(){
			public void click(){
				raview = new RadnikController(raser, redc);
				setContent(raview.getForm());
			}
		});
        
        
        addMenuItem("Uloge radnika", VaadinIcons.USER, new MenuClickHandler(){
			public void click(){
				kgview = new KorGrupaController(kgser, kgedit);
				setContent(kgview.getForm());
			}
		});
        
        addMenuItem("Mesto", VaadinIcons.GLOBE, new MenuClickHandler(){
			public void click(){
				mview = new MestoController(mser, medit);
				setContent(mview.getForm());
			}
		});
        
        addMenuItem("Komitenti", VaadinIcons.USER_CARD, new MenuClickHandler(){
			public void click(){
				kview = new KomitentController(kser, kedit);
				setContent(kview.getForm());
			}
		});
        
        
     
        
        
        
        setSizeFull();
    }
	
}
