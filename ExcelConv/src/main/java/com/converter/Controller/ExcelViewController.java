package com.converter.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Component.RecieverUploadFajl;
import com.converter.Model.Nalog;
import com.converter.Service.KomitentService;
import com.converter.Service.NalogService;
import com.converter.Service.OrganizacionaJedinicaService;
import com.converter.Service.RobaService;
import com.converter.Views.ExcelView;
import com.converter.Views.ImportFajl;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class ExcelViewController extends ExcelView{

	

	@SuppressWarnings("unused")
	private final KomitentService kser;
	@SuppressWarnings("unused")
	private final RobaService rser;
	
	@SuppressWarnings("unused")
	private final OrganizacionaJedinicaService orgser;
	
	@SuppressWarnings("unused")
	private final NalogService ns;
	
	@SuppressWarnings("unused")
	private ImportFajl imf;
	
	@Autowired
	public ExcelViewController(KomitentService kser, RobaService rser, NalogService ns, OrganizacionaJedinicaService orgser) {
		super();
		this.kser=kser;
		this.rser = rser;
		this.ns = ns;
		this.orgser = orgser;

		automObrada.setEnabled(false);
		importFajl.setEnabled(false);
		
		komitent.setItems(kser.findAllByTip("KK"));
		roba.setItems(rser.findAllCombo());
		veleprodaja.setItems(kser.findAllByTip("VP"));
		orgjed.setItems(orgser.findAll());
		importFajl.addClickListener(importf ->{
	        RecieverUploadFajl lineBreakCounter = new RecieverUploadFajl();
	        lineBreakCounter.setSlow(true);
	 
	        Upload upload = new Upload(null, lineBreakCounter);
	        upload.setImmediateMode(false);
	        upload.setButtonCaption("Upload fajl");
	        upload.setStyleName(ValoTheme.BUTTON_FRIENDLY);
	        
	        layoutroota.addComponent(upload);
	        ImportFajl uploadInfoWindow = new ImportFajl(upload, lineBreakCounter);
	        
	        upload.addStartedListener(event -> {
	            if (uploadInfoWindow.getParent() == null) {
	            	UI.getCurrent().addWindow(uploadInfoWindow);
	            }
	            uploadInfoWindow.setClosable(false);
	            upload.setVisible(false);
	        });
	        upload.addFinishedListener(event -> {
	        	try{
	        		LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
		        	Nalog trenutniNalog = ns.findOneNalog(0, convertedDate, datum.getValue().getMonthValue());
		        	trenutniNalog.setStatus(1);
		        	trenutniNalog.setIzvornifajl(uploadInfoWindow.fileName.getValue());
		        	ns.save(trenutniNalog);
		    		   Notification success = new Notification(
		                       "Excel uspješno importovan");
		               success.setDelayMsec(2000);
		               success.setStyleName("bar success small");
		               success.setPosition(Position.BOTTOM_CENTER);
		               success.show(Page.getCurrent());
		               otvoriNalog.setEnabled(false);
		               importFajl.setEnabled(false);
		               automObrada.setEnabled(true);
		    	   }catch(Exception ec){
		      		   	Notification success = new Notification("Nije moguće importovati excel.");
		                success.setDelayMsec(5000);
		                success.setStyleName("bar error small");
		                success.setPosition(Position.BOTTOM_CENTER);
		                success.show(Page.getCurrent());
		    	   }
	        	
	        	
	        	uploadInfoWindow.setClosable(true);
	        });
		});
		
		otvoriNalog.addClickListener(nalog->{
			 try{
				 LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
					
				 provjera(ns.provjeraNaloga(convertedDate, datum.getValue().getMonthValue(), 2), ns);
				 if(nalogProvjera==null) {
					 Nalog nalogStavka = new Nalog();
						
						nalogStavka.setMesec(datum.getValue().getMonthValue());
						nalogStavka.setDatum(convertedDate);
						nalogStavka.setKomitent(veleprodaja.getValue());
						nalogStavka.setOrgjed(orgjed.getValue());
						nalogStavka.setNapomena(null);
						nalogStavka.setIzvornifajl(null);
						Random random = new Random();
						int r = random.nextInt(10000);
						nalogStavka.setSifra(r);
						nalogStavka.setStatus(0);
						ns.save(nalogStavka);
		    		   Notification success = new Notification(
		                       "Nalog uspješno otvoren");
		               success.setDelayMsec(2000);
		               success.setStyleName("bar success small");
		               success.setPosition(Position.BOTTOM_CENTER);
		               success.show(Page.getCurrent());
		               otvoriNalog.setEnabled(false);
		               datum.setReadOnly(true);
		               veleprodaja.setReadOnly(true);
		               orgjed.setReadOnly(true);
		               importFajl.setEnabled(true);
				 }else {
					 System.out.println(nalogProvjera.getStatus());
					 System.out.println(nalogProvjera.getDatum());
					 if(nalogProvjera.getStatus()==0) {
						 otvoriNalog.setEnabled(false);
			             datum.setReadOnly(true);
			             veleprodaja.setReadOnly(true);
			             orgjed.setReadOnly(true);
			             importFajl.setEnabled(true);
			             
			             Notification success = new Notification(
			                       "Prikazan je nalog koji je otvoren ranije za izabrani mjesec. Molimo Vas importujte Excel fajl.");
			               success.setDelayMsec(2000);
			               success.setStyleName("bar success small");
			               success.setPosition(Position.BOTTOM_CENTER);
			               success.show(Page.getCurrent());
					 }else if(nalogProvjera.getStatus()==1) {
						 otvoriNalog.setEnabled(false);
			             datum.setReadOnly(true);
			             veleprodaja.setReadOnly(true);
			             orgjed.setReadOnly(true);
			             automObrada.setEnabled(true);
			             
			             Notification success = new Notification(
			                       "Prikazan je nalog koji je otvoren ranije za izabrani mjesec. Molimo Vas obradite stavke koje ste ranije importovali iz excel fajla.");
			               success.setDelayMsec(2000);
			               success.setStyleName("bar success small");
			               success.setPosition(Position.BOTTOM_CENTER);
			               success.show(Page.getCurrent());
					 }
				 }
				
	    	   }catch(Exception ec){
	      		   	Notification success = new Notification("Nije moguće otvoriti nalog.");
	                success.setDelayMsec(5000);
	                success.setStyleName("bar error small");
	                success.setPosition(Position.BOTTOM_CENTER);
	                success.show(Page.getCurrent());
	    	   }
		});
	}
}
