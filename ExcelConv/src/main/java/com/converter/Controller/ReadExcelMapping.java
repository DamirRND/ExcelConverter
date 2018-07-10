package com.converter.Controller;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.converter.Model.NalogStavka;

public class ReadExcelMapping {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static  List<NalogStavka> kreirajStavkeProdaje(ByteArrayInputStream bis) {

		List<NalogStavka> stavkeProdaje = new ArrayList();
		try {	 
			Workbook workbook = WorkbookFactory.create(bis); 
			Sheet sheet = workbook.getSheetAt(0);            	 
			Iterator rowIterator = sheet.iterator();  
			rowIterator.next();
			while (rowIterator.hasNext()) { 
				NalogStavka nalogSt = new NalogStavka(); 
				Row row = (Row) rowIterator.next();

				Iterator cellIterator = row.cellIterator(); 
				while (cellIterator.hasNext()) { 
					Cell cell = (Cell) cellIterator.next(); 

					if (cell.getColumnIndex() == 0) {
						nalogSt.setKupacSifraExt( (int) cell.getNumericCellValue());

					}
					else if (cell.getColumnIndex() == 1) {
						nalogSt.setKupacNazivExt(cell.getStringCellValue());

					}
					else if (cell.getColumnIndex() == 2) {
						nalogSt.setRobaSifraExt((int)(cell.getNumericCellValue()));

					}
					else if (cell.getColumnIndex() == 3) {
						nalogSt.setRobaNazivExt(cell.getStringCellValue());

					}
					else if (cell.getColumnIndex() == 4) {
						nalogSt.setKolicina(cell.getNumericCellValue());

					}
					else if (cell.getColumnIndex() == 5) {
						nalogSt.setIznos(cell.getNumericCellValue());

					}                      
				}
				stavkeProdaje.add(nalogSt);
			}
			
		} catch (FileNotFoundException e) {	 
		e.printStackTrace(); 
		} catch (IOException e) {	 
		e.printStackTrace();        
		} catch (InvalidFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		return stavkeProdaje;

	}

}
