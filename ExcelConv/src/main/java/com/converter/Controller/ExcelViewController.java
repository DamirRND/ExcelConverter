package com.converter.Controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.converter.Model.ExcelFajlModel;
import com.converter.Model.Nalog;
import com.converter.Model.NalogStavka;
import com.converter.Service.KomitentService;
import com.converter.Service.MapaKupacService;
import com.converter.Service.MapaRobeService;
import com.converter.Service.NalogService;
import com.converter.Service.NalogStavkaService;
import com.converter.Service.OrganizacionaJedinicaService;
import com.converter.Service.RobaService;
import com.converter.Views.ExcelView;
import com.converter.Views.ImportFajl;
import com.converter.Views.LookUpFilter;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class ExcelViewController extends ExcelView {

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

	@SuppressWarnings({"unchecked" })
	@Autowired
	public ExcelViewController(KomitentService kser, RobaService rser, NalogService ns,
			OrganizacionaJedinicaService orgser, NalogStavkaService nss, MapaRobeService ms, MapaKupacService mks) {
		super();
		this.kser = kser;
		this.rser = rser;
		this.ns = ns;
		this.orgser = orgser;
		this.nss = nss;
		this.ms = ms;
		this.mks = mks;

		importFajl.setEnabled(false);
		komitent.setItems(kser.findAllByTip("KK"));
		roba.setItems(rser.findAllCombo());
		veleprodaja.setItems(kser.findAllByTip("VP"));
		orgjed.setItems(orgser.findAll());
		panelDrugi.setEnabled(false);
		
		tabovi.addSelectedTabChangeListener(change ->{
			LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			convertedDate = convertedDate
					.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
			Nalog trenutniNalog = ns.findOneNalog(Integer.valueOf(idNaloga.getValue()), 2, convertedDate, datum.getValue().getMonthValue(),
					veleprodaja.getValue());
			if(tabovi.getTabPosition(tabovi.getTab(change.getTabSheet().getSelectedTab()))==0) {
				gridStavke.setItems(nss.findSveNeobradjene(trenutniNalog));
			}else if(tabovi.getTabPosition(tabovi.getTab(change.getTabSheet().getSelectedTab()))==1) {
				gridStavkeGotove.setItems(nss.findSveObradjene(trenutniNalog));
			}
		});
		pretragaSvih.addClickListener(pretraga ->{
			final LookUpFilter lfilter = new LookUpFilter();
			lfilter.gridNaloga.setItems(ns.findSve(2));
			ns.setZaPretragu(ns.findSve(2));
			lfilter.filter.setValueChangeMode(ValueChangeMode.LAZY);
			lfilter.filter.addValueChangeListener(event -> {
		        	if(StringUtils.isEmpty(lfilter.filter)){
		        		ns.getZaPretragu().clear();
		        		ns.removeCache();
		        		ns.setZaPretragu(ns.findSve(2));
		        		lfilter.gridNaloga.setItems(ns.findSve(2));
					}else{
						 List<Nalog> result = (List<Nalog>) ns.getZaPretragu().stream()
					                .filter(r -> {
					                    if (
					                    	String.valueOf(r.getSifra()).toLowerCase().contains(event.getValue().toLowerCase()) ||
					                    	String.valueOf(r.getDatum()).toLowerCase().contains(event.getValue().toLowerCase()) ||
					                    	String.valueOf(r.getMesec()).toLowerCase().contains(event.getValue().toLowerCase()) ||
					                    	r.getKomitent().getNaziv().toLowerCase().contains(event.getValue().toLowerCase())
					                    		) {
					                        return true;
					                    }
					                    return false;
					         }).collect(Collectors.toList());
						 lfilter.gridNaloga.setItems(result);
					}
		       });
			UI.getCurrent().addWindow(lfilter);
			lfilter.gridNaloga.setSelectionMode(SelectionMode.SINGLE);
			lfilter.gridNaloga.addItemClickListener(izaberi->{
				if(izaberi.getMouseEventDetails().isDoubleClick()) {
					idNaloga.setValue(String.valueOf(izaberi.getItem().getId()));
					idNaloga.setReadOnly(true);
					
					orgjed.setValue(izaberi.getItem().getOrgjed());
					veleprodaja.setValue(izaberi.getItem().getKomitent());
					
					if(izaberi.getItem().getIzvornifajl() != null) {
						nazivFajla.setValue(izaberi.getItem().getIzvornifajl());
					}else {
						nazivFajla.clear();
					}
					
					datum.setValue(izaberi.getItem().getDatum());
					
					if(izaberi.getItem().getStatus()==0) {
						importFajl.setEnabled(true);
						panelDrugi.setEnabled(false);
						gridStavke.setItems(new ArrayList<NalogStavka>());
						gridStavkeGotove.setItems(new ArrayList<NalogStavka>());
					}else {
						importFajl.setEnabled(false);
						LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(),
								DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						convertedDate = convertedDate
								.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
						Nalog trenutniNalog = ns.findOneNalog(Integer.valueOf(idNaloga.getValue()), 2, convertedDate, datum.getValue().getMonthValue(),
								veleprodaja.getValue());
						gridStavke.setItems(nss.findSveNeobradjene(trenutniNalog));
						panelDrugi.setEnabled(true);
					}
					UI.getCurrent().removeWindow(lfilter);
					
				}
			});
		});
		
		importFajl.addClickListener(importf -> {

			upload.setVisible(true);

			upload.addStartedListener(event -> {
				if (uploadInfoWindow.getParent() == null) {
					UI.getCurrent().addWindow(uploadInfoWindow);
				}
				uploadInfoWindow.setClosable(false);
				upload.setVisible(false);
			});
			upload.addFinishedListener(event -> {
				Nalog nalogGlavniProvjera = ns.findOneByFajl(event.getFilename());
				if (nalogGlavniProvjera == null) {
					try {
						LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(),
								DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						convertedDate = convertedDate
								.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));

						Nalog trenutniNalog = ns.findOneNalog(Integer.valueOf(idNaloga.getValue()), 2, convertedDate, datum.getValue().getMonthValue(),
								veleprodaja.getValue());

						trenutniNalog.setStatus(1);
						trenutniNalog.setIzvornifajl(uploadInfoWindow.fileName.getValue());
						ns.save(trenutniNalog);
						
						nazivFajla.setValue(uploadInfoWindow.fileName.getValue());

						ByteArrayInputStream bis = new ByteArrayInputStream(lineBreakCounter.getBaos().toByteArray());
						
						ReadExcelMapping mapa=new ReadExcelMapping();
			    		
			    		List<ExcelFajlModel> excel=mapa.getElementFromExcel(bis);
			    		Nalog trenutniNalogZaStavku = ns.findOneNalog(Integer.valueOf(idNaloga.getValue()), 2, convertedDate, datum.getValue().getMonthValue(),
								veleprodaja.getValue());
			    		for (ExcelFajlModel x : excel)
			    		{
			    			try {
			    				NalogStavka stavka=new NalogStavka();
			    				
			    				stavka.setCena(x.getVrijednost()/x.getKolicina());
			    				stavka.setKolicina((double)x.getKolicina());
			    				stavka.setIznos((double)x.getVrijednost());
			    				stavka.setNalog(trenutniNalogZaStavku);
			    				stavka.setRobasifraext(x.getSifraRobe());
			    				stavka.setRobanazivext(x.getNazivRobe());
			    				stavka.setKupacsifraext(x.getSifraApoteke());
			    				stavka.setKupacnazivext(x.getNazivApoteke());
			    				
			    				nss.save(stavka);
			    			} catch (Exception e) {
			    				e.printStackTrace();
			    			}
			    			
			    	     }
			    		
			    		Notification success = new Notification("Uspješno ste importovali excel fajl.");
						success.setDelayMsec(2000);
						success.setStyleName("bar success small");
						success.setPosition(Position.BOTTOM_CENTER);
						success.show(Page.getCurrent());
			    		

					} catch (Exception ec) {
						ec.printStackTrace();
						Notification success = new Notification("Nije moguće importovati excel.");
						success.setDelayMsec(5000);
						success.setStyleName("bar error small");
						success.setPosition(Position.BOTTOM_CENTER);
						success.show(Page.getCurrent());
					}
					uploadInfoWindow.setClosable(true);
				} else {
					uploadInfoWindow.setClosable(true);
					importFajl.setEnabled(true);
					Notification success = new Notification(
							"Podaci iz izabranog fajla su već obrađeni, molimo Vas da izaberete drugi fajl.");
					success.setDelayMsec(5000);
					success.setStyleName("bar error small");
					success.setPosition(Position.BOTTOM_CENTER);
					success.show(Page.getCurrent());
				}
			});
		});

		otvoriNalog.addClickListener(nalog -> {
			try {
				idNaloga.setReadOnly(false);
				idNaloga.clear();
				nazivFajla.clear();
				ns.removeCache();
				LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(),
						DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				convertedDate = convertedDate
						.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));

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
				
				Notification success = new Notification("Nalog uspješno otvoren. Izaberite nalog da biste ga dalje modifikovali.");
				success.setDelayMsec(2000);
				success.setStyleName("bar success small");
				success.setPosition(Position.BOTTOM_CENTER);
				success.show(Page.getCurrent());
				importFajl.setEnabled(true);
				idNaloga.setValue(String.valueOf(ns.pronadjiMax(convertedDate, datum.getValue().getMonthValue(), veleprodaja.getValue(), 2)));
				idNaloga.setReadOnly(true);
				panelDrugi.setEnabled(true);
			} catch (Exception ec) {
				ec.printStackTrace();
				Notification success = new Notification("Nije moguće otvoriti nalog.");
				success.setDelayMsec(4000);
				success.setStyleName("bar error small");
				success.setPosition(Position.BOTTOM_CENTER);
				success.show(Page.getCurrent());
			}
		});

		gridStavke.addItemClickListener(event -> {
			insert(event.getItem(), nss);
		});

		gridStavkeGotove.addItemClickListener(eventDva ->{
			insert(eventDva.getItem(), nss);
		});
		
		updateZapis.addClickListener(insertNovi -> {
			
			try {
				LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(),
						DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
				Nalog trenutniNalogZaStavku = ns.findOneNalog(Integer.valueOf(idNaloga.getValue()), 2, convertedDate, datum.getValue().getMonthValue(),
						veleprodaja.getValue());
				
				nss.save(nsProvjera);
				Notification success = new Notification("Uspješno ste ažurirali stavku naloga.");
				success.setDelayMsec(5000);
				success.setStyleName("bar success small");
				success.setPosition(Position.BOTTOM_CENTER);
				success.show(Page.getCurrent());
				
				if(tabovi.getTabPosition(tabovi.getTab(tabovi.getSelectedTab()))==0) {
					gridStavke.setItems(nss.findSveNeobradjene(trenutniNalogZaStavku));
				}else if(tabovi.getTabPosition(tabovi.getTab(tabovi.getSelectedTab()))==1) {
					gridStavkeGotove.setItems(nss.findSveObradjene(trenutniNalogZaStavku));
				}
				insert(null, nss);
			} catch (Exception ec) {
				ec.printStackTrace();
				Notification success = new Notification("Nije moguće sačuvati stavku naloga.");
				success.setDelayMsec(5000);
				success.setStyleName("bar error small");
				success.setPosition(Position.BOTTOM_CENTER);
				success.show(Page.getCurrent());
			}
		});
		
		
		dodajNoviZapis.addClickListener(novizapis ->{
			LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
			NalogStavka nsStavkaGrid = new NalogStavka();
			
			Nalog trenutniNalogZaStavku = ns.findOneNalog(Integer.valueOf(idNaloga.getValue()), 2, convertedDate, datum.getValue().getMonthValue(),
					veleprodaja.getValue());

			nsStavkaGrid.setNalog(trenutniNalogZaStavku);
			insert(nsStavkaGrid, nss);
		});
		
		zatvoriNalog.addClickListener(zatvori->{
			LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
			Nalog trenutniNalogZaStavku = ns.findOneNalog(Integer.valueOf(idNaloga.getValue()), 2, convertedDate, datum.getValue().getMonthValue(),
					veleprodaja.getValue());
			trenutniNalogZaStavku.setStatus(2);
			ns.save(trenutniNalogZaStavku);
			idNaloga.clear();
			orgjed.clear();
			veleprodaja.clear();
			datum.clear();
			nazivFajla.clear();
			importFajl.setEnabled(false);
			insert(null, nss);
			panelDrugi.setEnabled(false);
			ns.removeCache();
			Notification success = new Notification("Uspješno ste zatvorili nalog.");
			success.setDelayMsec(5000);
			success.setStyleName("bar success small");
			success.setPosition(Position.BOTTOM_CENTER);
			success.show(Page.getCurrent());
		});
		
	}
}
