package com.converter.Controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Component.RecieverUploadFajl;
import com.converter.Model.Apoteka;
import com.converter.Model.Komitent;
import com.converter.Model.MapaKupca;
import com.converter.Model.MapaRobe;
import com.converter.Model.Nalog;
import com.converter.Model.NalogStavka;
import com.converter.Model.Roba;
import com.converter.Service.KomitentService;
import com.converter.Service.MapaKupacService;
import com.converter.Service.MapaRobeService;
import com.converter.Service.NalogService;
import com.converter.Service.NalogStavkaService;
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
	
	@SuppressWarnings("unused")
	private final NalogStavkaService nss;
	@SuppressWarnings("unused")
	private final MapaRobeService ms;
	
	@SuppressWarnings("unused")
	private final MapaKupacService mks;
	
	@SuppressWarnings("unchecked")
	@Autowired
	public ExcelViewController(KomitentService kser, RobaService rser, NalogService ns, OrganizacionaJedinicaService orgser, NalogStavkaService nss,MapaRobeService ms,MapaKupacService mks) {
		super();
		this.kser=kser;
		this.rser = rser;
		this.ns = ns;
		this.orgser = orgser;
		this.nss = nss;
		this.ms = ms;
		this.mks = mks;

		importFajl.setEnabled(false);
		List<Apoteka> listaNeuspjelih=new ArrayList<>();
		komitent.setItems(kser.findAllByTip("KK"));
		roba.setItems(rser.findAllCombo());
		veleprodaja.setItems(kser.findAllByTip("VP"));
		orgjed.setItems(orgser.findAll());
		importFajl.addClickListener(importf ->{
	        RecieverUploadFajl lineBreakCounter = new RecieverUploadFajl();
	 
	        Upload upload = new Upload(null, lineBreakCounter);
	        upload.setImmediateMode(false);
	        upload.setButtonCaption("Automatska obrada");
	        
	        layoutroota.addComponent(upload);
	        importFajl.setEnabled(false);
	        ImportFajl uploadInfoWindow = new ImportFajl(upload, lineBreakCounter);
	        
	        upload.addStartedListener(event -> {
	            if (uploadInfoWindow.getParent() == null) {
	            	UI.getCurrent().addWindow(uploadInfoWindow);
	            }
	            uploadInfoWindow.setClosable(false);
	            upload.setVisible(false);
	        });
	        upload.addFinishedListener(event -> {
	    		Nalog nalogGlavniProvjera = ns.findOneByFajl(event.getFilename());
	    		if(nalogGlavniProvjera==null) {
	    			try{
		        		LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
						
			        	Nalog trenutniNalog = ns.findOneNalog(0, convertedDate, datum.getValue().getMonthValue(), veleprodaja.getValue());
			        	
			        	trenutniNalog.setStatus(1);
			        	trenutniNalog.setIzvornifajl(uploadInfoWindow.fileName.getValue());
			        	ns.save(trenutniNalog);
			        	
			        	System.out.println(uploadInfoWindow.fileName.getValue().substring(uploadInfoWindow.fileName.getValue().indexOf(".")));
			        	ByteArrayInputStream bis = new ByteArrayInputStream(lineBreakCounter.getBaos().toByteArray());
			    		ReadExcelMapping mapa=new ReadExcelMapping();
			    		
			    		List<Apoteka> excel=mapa.getStudentsListFromExcel(bis);
			    	
			    		Nalog trenutniNalogZaStavku = ns.findOneNalog(1, convertedDate, datum.getValue().getMonthValue(), veleprodaja.getValue());
			    		for (Apoteka x : excel)
			    		{
			    			try {
			    				MapaRobe robaSifra= ms.findOne(x.getSifraRobe(), x.getNazivRobe(), veleprodaja.getValue().getId());
			    				MapaKupca kupacSifra=mks.findOne(x.getSifraApoteke(), x.getNazivApoteke(), veleprodaja.getValue().getId());
			    				
			    				
			    				NalogStavka stavka=new NalogStavka();
			    				
			    				Komitent komitent=kser.findOne(kupacSifra.getKupac_id());
			    				
			    				Roba roba=rser.findOne((robaSifra.getRoba_id()));
			    				
			    				stavka.setKupac_id(komitent);;
			    				stavka.setRoba(roba);
			    				stavka.setCena(x.getVrijednost()/x.getKolicina());
			    				stavka.setKolicina((double)x.getKolicina());
			    				stavka.setIznos((double)x.getVrijednost());
			    				stavka.setNalog(trenutniNalogZaStavku);
			    				System.out.println("NALOOOOG " + trenutniNalogZaStavku.getId());
			    				stavka.setRobasifraext(x.getSifraRobe());
			    				stavka.setRobanazivext(x.getNazivRobe());
			    				stavka.setKupacsifraext(x.getSifraApoteke());
			    				stavka.setKupacnazivext(x.getNazivApoteke());
			    				
			    				nss.save(stavka);
			    			} catch (Exception e) {
			    				listaNeuspjelih.add(x);
			    			}
			    			
			    		}
			    		
			    		if(listaNeuspjelih.isEmpty()) {
			    			Nalog trenutniNalogZatvaramo = ns.findOneNalog(1, convertedDate, datum.getValue().getMonthValue(), veleprodaja.getValue());
			    			trenutniNalogZatvaramo.setStatus(2);
				        	ns.save(trenutniNalogZatvaramo);
				        	
			    			Notification success = new Notification(
				                       "Excel uspješno importovan i automatski obrađen. Zatvaramo nalog.");
				               success.setDelayMsec(5000);
				               success.setStyleName("bar success small");
				               success.setPosition(Position.BOTTOM_CENTER);
				               success.show(Page.getCurrent());
				               
				             otvoriNalog.setEnabled(true);
					         importFajl.setEnabled(false);
					         datum.setReadOnly(false);
					         datum.setData(null);
				             veleprodaja.setReadOnly(false);
				             veleprodaja.setData(null);
				             orgjed.setReadOnly(false);
				             orgjed.setData(null);
			    		}else {
			    			updateZapis.setEnabled(true);
			    			Notification success = new Notification(
				                       "Excel uspješno importovan ali nije automatski obrađen. Molimo vas obradite stavke iz tabele.");
				               success.setDelayMsec(5000);
				               success.setStyleName("bar success small");
				               success.setPosition(Position.BOTTOM_CENTER);
				               success.show(Page.getCurrent());   
			    			gridStavke.setItems(listaNeuspjelih);
			    			otvoriNalog.setEnabled(false);
						    importFajl.setEnabled(false);
			    		}
			    		
			    	   }catch(Exception ec){
			    		   ec.printStackTrace();
			      		   	Notification success = new Notification("Nije moguće importovati excel.");
			                success.setDelayMsec(5000);
			                success.setStyleName("bar error small");
			                success.setPosition(Position.BOTTOM_CENTER);
			                success.show(Page.getCurrent());
			    	   }
	    			uploadInfoWindow.setClosable(true);
	    		}else {
	    			uploadInfoWindow.setClosable(true);
	    			importFajl.setEnabled(true);
	    			Notification success = new Notification("Podaci iz izabranog fajla su već obrađeni, molimo Vas da izaberete drugi fajl.");
	                success.setDelayMsec(5000);
	                success.setStyleName("bar error small");
	                success.setPosition(Position.BOTTOM_CENTER);
	                success.show(Page.getCurrent());
	    		}
	        });
		});
		
		otvoriNalog.addClickListener(nalog->{
			 try{
				 LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
				
				Nalog provjeraZaStatusDva = ns.findOneNalog(2, convertedDate, datum.getValue().getMonthValue(), veleprodaja.getValue());
				if(provjeraZaStatusDva==null) {
					 provjera(ns.provjeraNaloga(convertedDate, datum.getValue().getMonthValue(),veleprodaja.getValue(), 2), ns);
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
						 if(nalogProvjera.getStatus()==0) {
							 otvoriNalog.setEnabled(false);
				             datum.setReadOnly(true);
				             veleprodaja.setReadOnly(true);
				             orgjed.setReadOnly(true);
				             importFajl.setEnabled(true);
				             
				             Notification success = new Notification(
				                       "Prikazan je nalog koji je otvoren ranije za izabrani mjesec. Molimo Vas importujte Excel fajl.");
				               success.setDelayMsec(6000);
				               success.setStyleName("bar success small");
				               success.setPosition(Position.BOTTOM_CENTER);
				               success.show(Page.getCurrent());
						 }else if(nalogProvjera.getStatus()==1) {
							 otvoriNalog.setEnabled(false);
				             datum.setReadOnly(true);
				             veleprodaja.setReadOnly(true);
				             orgjed.setReadOnly(true);
				             
				             Notification success = new Notification(
				                       "Prikazan je nalog koji je otvoren ranije za izabrani mjesec. Molimo Vas obradite stavke koje ste ranije importovali iz excel fajla.");
				               success.setDelayMsec(6000);
				               success.setStyleName("bar success small");
				               success.setPosition(Position.BOTTOM_CENTER);
				               success.show(Page.getCurrent());
						 }
					 }
				}else {
					 otvoriNalog.setEnabled(true);
		             datum.setReadOnly(false);
		             veleprodaja.setReadOnly(false);
		             orgjed.setReadOnly(false);
		             
		             Notification success = new Notification(
		                       "Nalog za izabrani mjesec i veleprodaju je već obrađen.");
		               success.setDelayMsec(6000);
		               success.setStyleName("bar error small");
		               success.setPosition(Position.BOTTOM_CENTER);
		               success.show(Page.getCurrent());
					
				}
				
				
	    	   }catch(Exception ec){
	    		   ec.printStackTrace();
	      		   	Notification success = new Notification("Nije moguće otvoriti nalog.");
	                success.setDelayMsec(4000);
	                success.setStyleName("bar error small");
	                success.setPosition(Position.BOTTOM_CENTER);
	                success.show(Page.getCurrent());
	    	   }
		});
		
		gridStavke.addItemClickListener(event->{
				Apoteka ap = event.getItem();
				LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
				NalogStavka nsStavkaGrid = new NalogStavka();
				Nalog trenutniNalogZaStavku = ns.findOneNalog(1, convertedDate, datum.getValue().getMonthValue(), veleprodaja.getValue());
			    nsStavkaGrid.setRobanazivext(event.getItem().getNazivRobe());
			    nsStavkaGrid.setRobasifraext(event.getItem().getSifraRobe());
			    nsStavkaGrid.setKupacnazivext(event.getItem().getNazivApoteke());
			    nsStavkaGrid.setKupacsifraext(event.getItem().getSifraApoteke());
			    nsStavkaGrid.setCena(event.getItem().getVrijednost()/event.getItem().getKolicina());
			    nsStavkaGrid.setKolicina(Double.valueOf(event.getItem().getKolicina()));
			    nsStavkaGrid.setIznos(Double.valueOf(event.getItem().getVrijednost()));
			    nsStavkaGrid.setNalog(trenutniNalogZaStavku);
			    insert(nsStavkaGrid, nss);
			    listaNeuspjelih.remove(ap);
		 });
		
		updateZapis.addClickListener(insertNovi->{
			 try{
	    		   nss.save(nsProvjera);
	    		  
	               if(listaNeuspjelih.isEmpty()) {
	            	   gridStavke.setItems(listaNeuspjelih);
	            	   insert(null, nss);
	            	   updateZapis.setEnabled(false);
	            	   
	            	   LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
						
	            	   Nalog trenutniNalogZatvaramo = ns.findOneNalog(1, convertedDate, datum.getValue().getMonthValue(), veleprodaja.getValue());
		    			trenutniNalogZatvaramo.setStatus(2);
			        	ns.save(trenutniNalogZatvaramo);
			        	
		    			Notification success = new Notification(
			                       "Uspješno ste ažurirali poslednju stavku. Zatvaramo nalog za mjesec " + datum.getValue().getMonth());
			               success.setDelayMsec(5000);
			               success.setStyleName("bar success small");
			               success.setPosition(Position.BOTTOM_CENTER);
			               success.show(Page.getCurrent());
			               
			             otvoriNalog.setEnabled(true);
				         importFajl.setEnabled(false);
				         datum.setReadOnly(false);
				         datum.setData(null);
			             veleprodaja.setReadOnly(false);
			             veleprodaja.setData(null);
			             orgjed.setReadOnly(false);
			             orgjed.setData(null);
				    
	               }else {
	            	   Notification success = new Notification(
		                       "Stavka uspješno sačuvana.");
		               success.setDelayMsec(2000);
		               success.setStyleName("bar success small");
		               success.setPosition(Position.BOTTOM_CENTER);
		               success.show(Page.getCurrent());
	            	   gridStavke.setItems(listaNeuspjelih);
	               }
	               
	    	   }catch(Exception ec){
	    		   ec.printStackTrace();
	      		   	Notification success = new Notification("Nije moguće sačuvati stavku naloga.");
	                 success.setDelayMsec(5000);
	                 success.setStyleName("bar error small");
	                 success.setPosition(Position.BOTTOM_CENTER);
	                 success.show(Page.getCurrent());
	    	   }
		});
	}
}
