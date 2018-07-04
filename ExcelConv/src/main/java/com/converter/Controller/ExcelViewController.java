package com.converter.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.converter.Component.RecieverUploadFajl;
import com.converter.Service.KomitentService;
import com.converter.Service.RobaService;
import com.converter.Views.ExcelView;
import com.converter.Views.ImportFajl;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
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
	private ImportFajl imf;
	
	@Autowired
	public ExcelViewController(KomitentService kser, RobaService rser) {
		super();
		this.kser=kser;
		this.rser = rser;
		

		komitent.setItems(kser.findAllByTip("KK"));
		roba.setItems(rser.findAllCombo());
		veleprodaja.setItems(kser.findAllByTip("VP"));
		
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
	        upload.addFinishedListener(event -> uploadInfoWindow.setClosable(true));
		});
	}
}
