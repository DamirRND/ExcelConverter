package com.converter.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Model.Nalog;
import com.converter.Service.KomitentService;
import com.converter.Service.NalogService;
import com.converter.Service.NalogStavkaService;
import com.converter.Service.RobaService;
import com.converter.Views.PregledNaloga;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

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
		
		veleprodaja.addValueChangeListener(vele->{
			if(vele.getValue()==null) {
				if(roba.getValue()==null) {
					grid.setItems(nss.findAllByKupac(kupci.getValue()));
				}else if(kupci.getValue()==null){
					grid.setItems(nss.findAllByRoba(roba.getValue()));
				}else {
					grid.setItems(nss.findAllByKupacAndRoba(kupci.getValue(), roba.getValue()));
				}
			}else {
				LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
				Nalog nalog = ns.findZaExport(convertedDate, datum.getValue().getMonthValue(), vele.getValue());
				if(roba.getValue()==null && kupci.getValue()!=null) {
					grid.setItems(nss.findallByKupacAndNalog(kupci.getValue(), nalog));
				}else if(roba.getValue()!=null && kupci.getValue()==null) {
					grid.setItems(nss.findallByRobaAndNalog(roba.getValue(), nalog));
				}else if(roba.getValue()==null && kupci.getValue()==null){
					grid.setItems(nss.findallByNalog(nalog));
				}else {
					grid.setItems(nss.findallByKupacAndRobaAndNalog(kupci.getValue(), roba.getValue(), nalog));
				}
			}
		});
		
		kupci.addValueChangeListener(kupci->{
			if(veleprodaja.getValue()==null) {
				if(roba.getValue()==null) {
					grid.setItems(nss.findAllByKupac(kupci.getValue()));
				}else if(kupci.getValue()==null){
					grid.setItems(nss.findAllByRoba(roba.getValue()));
				}else {
					grid.setItems(nss.findAllByKupacAndRoba(kupci.getValue(), roba.getValue()));
				}
			}else {
				LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
				Nalog nalog = ns.findZaExport(convertedDate, datum.getValue().getMonthValue(), veleprodaja.getValue());
				if(roba.getValue()==null && kupci.getValue()!=null) {
					grid.setItems(nss.findallByKupacAndNalog(kupci.getValue(), nalog));
				}else if(roba.getValue()!=null && kupci.getValue()==null) {
					grid.setItems(nss.findallByRobaAndNalog(roba.getValue(), nalog));
				}else if(roba.getValue()==null && kupci.getValue()==null){
					grid.setItems(nss.findallByNalog(nalog));
				}else {
					grid.setItems(nss.findallByKupacAndRobaAndNalog(kupci.getValue(), roba.getValue(), nalog));
				}
			}
			
		});
		
		roba.addValueChangeListener(roba->{
			if(veleprodaja.getValue()==null) {
				if(roba.getValue()==null) {
					grid.setItems(nss.findAllByKupac(kupci.getValue()));
				}else if(kupci.getValue()==null){
					grid.setItems(nss.findAllByRoba(roba.getValue()));
				}else {
					grid.setItems(nss.findAllByKupacAndRoba(kupci.getValue(), roba.getValue()));
				}
			}else {
				LocalDate convertedDate = LocalDate.parse(datum.getValue().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				convertedDate = convertedDate.withDayOfMonth(convertedDate.getMonth().length(convertedDate.isLeapYear()));
				Nalog nalog = ns.findZaExport(convertedDate, datum.getValue().getMonthValue(), veleprodaja.getValue());
				if(roba.getValue()==null && kupci.getValue()!=null) {
					grid.setItems(nss.findallByKupacAndNalog(kupci.getValue(), nalog));
				}else if(roba.getValue()!=null && kupci.getValue()==null) {
					grid.setItems(nss.findallByRobaAndNalog(roba.getValue(), nalog));
				}else if(roba.getValue()==null && kupci.getValue()==null){
					grid.setItems(nss.findallByNalog(nalog));
				}else {
					grid.setItems(nss.findallByKupacAndRobaAndNalog(kupci.getValue(), roba.getValue(), nalog));
				}
			}
		});

		export.addClickListener(export->{
			
		});
	}
}
