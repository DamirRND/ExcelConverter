package com.converter.Controller;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.converter.Model.Apoteka;






 


public class ReadExcelMapping {
	
	     @SuppressWarnings({ "rawtypes", "unchecked", "resource" })
		public  List getStudentsListFromExcel(ByteArrayInputStream bis) {
	 
	         List ApotekaLista = new ArrayList();
	         try {
	 
	 
	             Workbook workbook = new XSSFWorkbook(bis);
	 
	  
	 
	             int numberOfSheets = workbook.getNumberOfSheets();
	 
	  
	
	             //looping over each workbook sheet
	 
	             for (int i = 0; i < numberOfSheets; i++) {
	 
	                 Sheet sheet = workbook.getSheetAt(i);
	 
	                 Iterator rowIterator = sheet.iterator();
	 
	  
	                 rowIterator.next();
	                 rowIterator.next();
	                 //iterating over each row
	 
	                 while (rowIterator.hasNext()) {
	 
	  
	 
	                     Apoteka apoteka = new Apoteka();
	 
	                    
	                     
	                      Row row = (Row) rowIterator.next();
	                     Iterator cellIterator = row.cellIterator();
	 
	  
	 
	                     //Iterating over each cell (column wise)  in a particular row.
	 
	                     while (cellIterator.hasNext()) {
	 
	  
	 
	                         Cell cell = (Cell) cellIterator.next();
	 
	                         //The Cell Containing String will is name.
	 
	                    
	 
	  
	 
	                             //Cell with index 1 contains marks in Maths
	 
	                             if (cell.getColumnIndex() == 0) {
	 
	                            	// apoteka.setMaths(String.valueOf(cell.getNumericCellValue()));
	                            	 apoteka.setSifraApoteke((int)(cell.getNumericCellValue()));
	 
	                             }
	 
	                             //Cell with index 2 contains marks in Science
	 
	                             else if (cell.getColumnIndex() == 1) {
	 
	                            	// apoteka.setScience(String.valueOf(cell.getNumericCellValue()));
	                            	 apoteka.setNazivApoteke(cell.getStringCellValue());
	 
	                             }
	 
	                             //Cell with index 3 contains marks in English
	 
	                             else if (cell.getColumnIndex() == 2) {
	 
	                            	// apoteka.setEnglish(String.valueOf(cell.getNumericCellValue()));
	                            	 apoteka.setSifraRobe((int)(cell.getNumericCellValue()));
	 
	                             }
	                             else if (cell.getColumnIndex() == 3) {
	                            	 
	                            	// apoteka.setEnglish(String.valueOf(cell.getNumericCellValue()));
	                            	 apoteka.setNazivRobe(cell.getStringCellValue());
	 
	                             }
	                             else if (cell.getColumnIndex() == 4) {
	                            	 
	                            	// apoteka.setEnglish(String.valueOf(cell.getNumericCellValue()));
	                            	 apoteka.setKolicina((int)cell.getNumericCellValue());
	 
	                             }
	                             else if (cell.getColumnIndex() == 5) {
	                            	 
		                            	// apoteka.setEnglish(String.valueOf(cell.getNumericCellValue()));
		                            	 apoteka.setVrijednost(cell.getNumericCellValue());
		 
		                             }
	 
	                         
	 
	                     }
	 
	                     //end iterating a row, add all the elements of a row in list
	 
	                     ApotekaLista.add(apoteka);
	 
	                 }
	 
	             }
	 
	  
	 
	           //  fis.close();
	 
	  
	 
	         } catch (FileNotFoundException e) {
	 
	             e.printStackTrace();
	 
	         } catch (IOException e) {
	 
	             e.printStackTrace();
	 
	         }
	 
	         return ApotekaLista;
	 
	     }


}
