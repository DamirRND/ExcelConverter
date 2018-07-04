package com.converter.Views;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Controller.EntitetController;
import com.converter.Controller.EntitetEditController;
import com.converter.Controller.OrgJedController;
import com.converter.Controller.OrgJedEditController;
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
	private RobaService rser;
	private RobaEditController redit;
	
	private RobaGrupeController rgview;
	private RobaGrupaService rgser;
	private RobaGrupeEditController rgeditcont;
	
	private ExcelView excelView;
	
	private UstanoveController uview;
	private UstanovaService user;
	private UstanoveEditController uedit;
	
	private RegionController rview;
	private RegionService regser;
	private RegionEditController rgedit;
	
	private OrgJedController orgview;
	private OrganizacionaJedinicaService orgser;
	private OrgJedEditController orgedit;
	
	private RadnikController raview;
	private RadnikService raser;
	private RadnikEditController redc;
	
	private KorGrupaView kgview;
	private KorGrupaService kgser;
	
	private KomitentView kview;
	private KomitentService kser;
	
	private EntitetController eview;
	private EntitetService eserv;
	private EntitetEditController eedit;
	
	
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
			RobaEditController redit,
			RobaGrupeEditController rgeditcont,
			UstanoveEditController uedit,
			EntitetEditController eedit,
			OrgJedEditController orgedit,
			RegionEditController rgedit,
			RadnikEditController redc) {
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
		
		Responsive.makeResponsive(this);
		setUserName(VaadinSession.getCurrent().getAttribute("Ime").toString());
        setSpacing(false);
        
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
				kgview = new KorGrupaView(kgser);
				setContent(kgview.getForm());
			}
		});
        
        addMenuItem("Komitenti", VaadinIcons.USER_CARD, new MenuClickHandler(){
			public void click(){
				kview = new KomitentView(kser);
				setContent(kview.getForm());
			}
		});
        
      
        
        
        addMenuItem("Excel", VaadinIcons.WORKPLACE, new MenuClickHandler(){
			public void click(){
				excelView = new ExcelView(kser, rser);
				setContent(excelView.getForm());
			}
		});
        
        
        
        setSizeFull();
    }
	
}
