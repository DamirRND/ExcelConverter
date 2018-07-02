package com.converter.Views;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.EditForme.RobaEdit;
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
	
	private RobaView robaview;
	private RobaService rser;
	private RobaEdit redit;
	
	private RobaGrupeView rgview;
	private RobaGrupaService rgser;
	
	private ExcelView excelView;
	
	private UstanovaView uview;
	private UstanovaService user;
	
	private RegionView rview;
	private RegionService regser;
	
	private OrganizacionaJedinicaView orgview;
	private OrganizacionaJedinicaService orgser;
	
	private RadnikView raview;
	private RadnikService raser;
	
	private KorGrupaView kgview;
	private KorGrupaService kgser;
	
	private KomitentView kview;
	private KomitentService kser;
	
	private EntitetView eview;
	private EntitetService eserv;
	
	
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
			EntitetService eserv, RobaEdit redit) {
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
		
		Responsive.makeResponsive(this);
		setUserName(VaadinSession.getCurrent().getAttribute("Ime").toString());
        setSpacing(false);
        
        addMenuItem("Roba", VaadinIcons.BULLETS, new MenuClickHandler(){
			public void click(){
				robaview = new RobaView(rser, redit);
				setContent(robaview.getForm());
			}
		});
        
        addMenuItem("Roba grupe", VaadinIcons.FILE_TREE, new MenuClickHandler(){
			public void click(){
				rgview = new RobaGrupeView(rgser);
				setContent(rgview.getForm());
			}
		});
        
        addMenuItem("Ustanove", VaadinIcons.BUILDING, new MenuClickHandler(){
			public void click(){
				uview = new UstanovaView(user);
				setContent(uview.getForm());
			}
		});
        
        addMenuItem("Entiteti", VaadinIcons.GLOBE, new MenuClickHandler(){
			public void click(){
				eview = new EntitetView(eserv);
				setContent(eview.getForm());
			}
		});
        
        addMenuItem("Organizaciona jedinica", VaadinIcons.BUILDING_O, new MenuClickHandler(){
			public void click(){
				orgview = new OrganizacionaJedinicaView(orgser);
				setContent(orgview.getForm());
			}
		});
        
        addMenuItem("Region", VaadinIcons.AREA_SELECT, new MenuClickHandler(){
			public void click(){
				rview = new RegionView(regser);
				setContent(rview.getForm());
			}
		});

        addMenuItem("Radnici", VaadinIcons.USERS, new MenuClickHandler(){
			public void click(){
				raview = new RadnikView(raser);
				setContent(raview.getForm());
			}
		});
        
        addMenuItem("Komitenti", VaadinIcons.USER_CARD, new MenuClickHandler(){
			public void click(){
				kview = new KomitentView(kser);
				setContent(kview.getForm());
			}
		});
        
        addMenuItem("Uloge radnika", VaadinIcons.USER, new MenuClickHandler(){
			public void click(){
				kgview = new KorGrupaView(kgser);
				setContent(kgview.getForm());
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
