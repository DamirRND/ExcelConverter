package com.converter.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.converter.Model.NalogStavka;
import com.vaadin.server.VaadinService;

public class StyleExcel {
		 
		 public void writeFileUsingPOI(List<NalogStavka> listaPodata) throws IOException 
		 {
		  //create blank workbook
		  XSSFWorkbook workbook = new XSSFWorkbook(); 
		 
		  //Create a blank sheet
		  XSSFSheet sheet = workbook.createSheet("Excel");
		  
		  
		
		  
		  int rownum = 1;
		  String[] headers = new String[] {"Redni broj naloga","Naziv komitenta", "Naziv robe", "Cena robe", "Kolicina", "Iznos", "Datum naloga"};
	        Row r = sheet.createRow(0);
	    	for (int rn=0; rn<headers.length; rn++) {
	     	
	     	  Cell cell = r.createCell(rn);
	     	
	     	  cell.setCellValue(headers[rn]);
	     	  sheet.autoSizeColumn(0);
	     	  sheet.autoSizeColumn(rn);
			     CellStyle style=workbook.createCellStyle();
			     style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			     style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		 
			     // Creating a font
			        XSSFFont font= workbook.createFont();
			        font.setFontHeightInPoints((short)12);
			        font.setFontName("Arial");
			        font.setColor(IndexedColors.BLACK.getIndex());
			        
			        
			        font.setBold(true);
			        font.setItalic(false);
			       
			        style.setFont(font);
			        
			        // Setting cell style
			        cell.setCellStyle(style);
			     
			    
			    
	     	}
	    	
		  //Iterate over data and write to sheet
		
		  Row row;
		  for (int i=0;i<1;i++)
		  { 
		   
		   
				   int cellnum = 0;
				   for (NalogStavka obj : listaPodata)
				   {
					    row = sheet.createRow(rownum++);
					 
					   Cell cell = row.createCell(cellnum++);

					    cell.setCellValue((Integer)obj.getId());
					    sheet.autoSizeColumn(cellnum);
					    cell = row.createCell(cellnum++);
					    cell.setCellValue(obj.getKupac_id().getNaziv());
					    sheet.autoSizeColumn(cellnum);
					    cell = row.createCell(cellnum++);
					    cell.setCellValue(obj.getRoba().getNaziv());
					    sheet.autoSizeColumn(cellnum);
					    cell = row.createCell(cellnum++);
					    cell.setCellValue(obj.getCena());
					    sheet.autoSizeColumn(cellnum);
					    cell = row.createCell(cellnum++);
					    cell.setCellValue(obj.getKolicina());
					    sheet.autoSizeColumn(cellnum);
					    cell = row.createCell(cellnum++);
					    cell.setCellValue(obj.getIznos());
					    sheet.autoSizeColumn(cellnum);
					    cell = row.createCell(cellnum++);
					    cell.setCellValue(obj.getNalog().getDatum().toString());
					    sheet.autoSizeColumn(cellnum);
					    
				    
				    // Setting style only for header
					    if(rownum==1)
					    {
					    	// Setting font to style
					     CellStyle style=workbook.createCellStyle();
					     style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
					     style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				 
					     // Creating a font
					        XSSFFont font= workbook.createFont();
					        font.setFontHeightInPoints((short)12);
					        font.setFontName("Arial");
					        font.setColor(IndexedColors.BLACK.getIndex());
					        
					        
					        font.setBold(true);
					        font.setItalic(false);
					       
					        style.setFont(font);
					        
					        // Setting cell style
					        cell.setCellStyle(style);
					     
					    
					    }
					    cellnum=0;
				   }
		   
		   
		  }
		  try
		  {
			  final DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			  Date date = new Date();
		   FileOutputStream out = new FileOutputStream(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/VAADIN/NalogExport"+sdf.format(date).toString()+".xlsx"));
		   workbook.write(out);
		   out.close();
		  } 
		  catch (Exception e) 
		  {
		   e.printStackTrace();
		  }
		  finally {
		 
		  }
		 }
		}
