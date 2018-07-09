package com.converter.Views;

import com.converter.Model.Nalog;
import com.vaadin.annotations.Theme;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;


@SuppressWarnings("serial")
@UIScope
@Theme("mytheme")
public class LookUpFilter extends Window{

	public VerticalLayout vl = new VerticalLayout();
	public TextField filter = new TextField();
	public Grid<Nalog> gridNaloga = new Grid<>(Nalog.class);
	
	
	public LookUpFilter() {
		vl.setSizeFull();
		vl.setSpacing(false);
		gridNaloga.setSizeFull();
		gridNaloga.addColumn(Nalog -> Nalog.getKomitent().getNaziv()).setCaption("Naziv komitenta").setId("mojKomitent");
		gridNaloga.setColumns("id","sifra","datum", "izvorniFajl", "mesec", "mojKomitent");
		filter.setPlaceholder("Pretraga..");
		vl.addComponents(filter, gridNaloga);
		vl.setExpandRatio(gridNaloga, 1);
		
		setSizeFull();
		setContent(vl);
		setClosable(false);
		setModal(true);
	}
}
