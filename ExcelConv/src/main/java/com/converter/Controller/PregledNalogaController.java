package com.converter.Controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Service.KomitentService;
import com.converter.Service.NalogStavkaService;
import com.converter.Service.RobaService;
import com.converter.Views.PregledNaloga;
import com.vaadin.shared.data.sort.SortDirection;
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
	
	@Autowired
	public PregledNalogaController(KomitentService ks, RobaService rs, NalogStavkaService nss) {
		super();
		this.ks = ks;
		this.rs = rs;
		this.nss = nss;
		
		grid.setDataProvider(
				(sortOrders, offset, limit)->{
					Map<String, Boolean> sortOrder = sortOrders.stream()
		                    .collect(Collectors.toMap(
		                         sort -> sort.getSorted(),
		                         sort -> sort.getDirection() == SortDirection.ASCENDING));
		                 return nss.findAll(offset, limit, sortOrder).stream();
				},
				()-> nss.count()
		  );
		
		
		veleprodaja.setItems(ks.findAllByTip("VP"));
		kupci.setItems(ks.findAllByTip("KK"));
		roba.setItems(rs.findAllCombo());
		
	}
}
