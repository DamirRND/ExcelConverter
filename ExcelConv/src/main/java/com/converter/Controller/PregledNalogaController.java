package com.converter.Controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Model.Nalog;
import com.converter.Model.NalogStavka;
import com.converter.Service.KomitentService;
import com.converter.Service.NalogService;
import com.converter.Service.NalogStavkaService;
import com.converter.Service.RobaService;
import com.converter.Views.PregledNaloga;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class PregledNalogaController extends PregledNaloga{

	@SuppressWarnings("unused")
	private final KomitentService ks;
	@SuppressWarnings("unused")
	private final RobaService rs;
	@SuppressWarnings("unused")
	private final NalogStavkaService nss;
	
	@SuppressWarnings("unused")
	private final NalogService ns;
	
	
	@SuppressWarnings("static-access")
	@Autowired
	public PregledNalogaController(KomitentService ks, RobaService rs, NalogStavkaService nss, NalogService ns) {
		super();
		this.ks = ks;
		this.rs = rs;
		this.nss = nss;
		this.ns = ns;
		
		veleprodaja.setItems(ks.findAllByTip("VP"));
		kupci.setItems(ks.findAllByTip("KK"));
		roba.setItems(rs.findAllCombo());
		
		StyleExcel excel = new StyleExcel();
		
		veleprodaja.addValueChangeListener(vele->{
			if(vele.getValue()==null) {
				if(roba.getValue()==null) {
					exportExcel(grid, nss.findAllByKupac(kupci.getValue()), nss);
				}else if(kupci.getValue()==null){
					exportExcel(grid, nss.findAllByRoba(roba.getValue()), nss);
				}else {
					exportExcel(grid, nss.findAllByKupacAndRoba(kupci.getValue(), roba.getValue()), nss);
				}
			}else {
				LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
				Nalog nalog = ns.findZaExport(convertedDate, datum.getValue().getMonthValue(), vele.getValue());
				if(roba.getValue()==null && kupci.getValue()!=null) {
					exportExcel(grid, nss.findallByKupacAndNalog(kupci.getValue(), nalog), nss);
				}else if(roba.getValue()!=null && kupci.getValue()==null) {
					exportExcel(grid, nss.findallByRobaAndNalog(roba.getValue(), nalog), nss);
				}else if(roba.getValue()==null && kupci.getValue()==null){
					exportExcel(grid, nss.findallByNalog(nalog), nss);
				}else {
					exportExcel(grid, nss.findallByKupacAndRobaAndNalog(kupci.getValue(), roba.getValue(), nalog), nss);
				}
			}
		});
		
		kupci.addValueChangeListener(kupci->{
			if(veleprodaja.getValue()==null) {
				if(roba.getValue()==null) {
					exportExcel(grid, nss.findAllByKupac(kupci.getValue()), nss);
				}else if(kupci.getValue()==null){
					exportExcel(grid, nss.findAllByRoba(roba.getValue()), nss);
				}else {
					exportExcel(grid, nss.findAllByKupacAndRoba(kupci.getValue(), roba.getValue()), nss);
				}
			}else {
				LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
				Nalog nalog = ns.findZaExport(convertedDate, datum.getValue().getMonthValue(), veleprodaja.getValue());
				if(roba.getValue()==null && kupci.getValue()!=null) {
					exportExcel(grid, nss.findallByKupacAndNalog(kupci.getValue(), nalog), nss);
				}else if(roba.getValue()!=null && kupci.getValue()==null) {
					exportExcel(grid, nss.findallByRobaAndNalog(roba.getValue(), nalog), nss);
				}else if(roba.getValue()==null && kupci.getValue()==null){
					exportExcel(grid, nss.findallByNalog(nalog), nss);
				}else {
					exportExcel(grid, nss.findallByKupacAndRobaAndNalog(kupci.getValue(), roba.getValue(), nalog), nss);
				}
			}
			
		});
		
		roba.addValueChangeListener(roba->{
			if(veleprodaja.getValue()==null) {
				if(roba.getValue()==null) {
					exportExcel(grid, nss.findAllByKupac(kupci.getValue()), nss);
				}else if(kupci.getValue()==null){
					exportExcel(grid, nss.findAllByRoba(roba.getValue()), nss);
				}else {
					exportExcel(grid, nss.findAllByKupacAndRoba(kupci.getValue(), roba.getValue()), nss);
				}
			}else {
				LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
				Nalog nalog = ns.findZaExport(convertedDate, datum.getValue().getMonthValue(), veleprodaja.getValue());
				if(roba.getValue()==null && kupci.getValue()!=null) {
					exportExcel(grid, nss.findallByKupacAndNalog(kupci.getValue(), nalog), nss);
				}else if(roba.getValue()!=null && kupci.getValue()==null) {
					exportExcel(grid, nss.findallByRobaAndNalog(roba.getValue(), nalog), nss);
				}else if(roba.getValue()==null && kupci.getValue()==null){
					exportExcel(grid, nss.findallByNalog(nalog), nss);
				}else {
					exportExcel(grid, nss.findallByKupacAndRobaAndNalog(kupci.getValue(), roba.getValue(), nalog), nss);
				}
			}
		});

		export.addClickListener(export->{
			final DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			
			try {
				excel.writeFileUsingPOI(nss.getListaZaExport());
				getUI().getPage().open("/ExcelConv/VAADIN/NalogExport"+sdf.format(date).toString()+".xlsx", "_blank");
				Notification success = new Notification("Uspješno ste exportovali excel.");
                success.setDelayMsec(5000);
                success.setStyleName("bar success small");
                success.setPosition(Position.BOTTOM_CENTER);
                success.show(Page.getCurrent());
			} catch (IOException  e) {
				e.printStackTrace();
				Notification success = new Notification("Nije moguće exportovati excel.");
                success.setDelayMsec(5000);
                success.setStyleName("bar error small");
                success.setPosition(Position.BOTTOM_CENTER);
                success.show(Page.getCurrent());
			}finally {
//				File fajl =new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/VAADIN/NalogExport"+sdf.format(date).toString()+".xlsx");
//				fajl.delete();
			}
		});
	}
	
	public void exportExcel(Grid<NalogStavka> grid, List<NalogStavka> listaZaGrid, NalogStavkaService nss) {
		if(nss.getListaZaExport()!=null) {
			nss.getListaZaExport().clear();
		}
		nss.removeCache();
		nss.setListaZaExport(listaZaGrid);
		grid.setItems(listaZaGrid);
	}
}
