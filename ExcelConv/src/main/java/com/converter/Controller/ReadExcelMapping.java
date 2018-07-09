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

import com.converter.Model.ExcelFajlModel;

public class ReadExcelMapping {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getElementFromExcel(ByteArrayInputStream bis) {

		List ExcelFajlLista = new ArrayList();
		try {

			Workbook workbook = WorkbookFactory.create(bis);

			int numberOfSheets = workbook.getNumberOfSheets();

			for (int i = 0; i < numberOfSheets; i++) {

				Sheet sheet = workbook.getSheetAt(i);

				Iterator rowIterator = sheet.iterator();

				rowIterator.next();

				while (rowIterator.hasNext()) {

					ExcelFajlModel excel = new ExcelFajlModel();

					Row row = (Row) rowIterator.next();
					Iterator cellIterator = row.cellIterator();


					while (cellIterator.hasNext()) {

						Cell cell = (Cell) cellIterator.next();

						if (cell.getColumnIndex() == 0) {
							 excel.setSifraApoteke(cell.getStringCellValue());
							
						}

						else if (cell.getColumnIndex() == 1) {
							excel.setNazivApoteke(cell.getStringCellValue());

						}

						else if (cell.getColumnIndex() == 2) {
							excel.setSifraRobe(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == 3) {

							excel.setNazivRobe(cell.getStringCellValue());

						} else if (cell.getColumnIndex() == 4) {

							excel.setKolicina((int) cell.getNumericCellValue());

						} else if (cell.getColumnIndex() == 5) {
							excel.setVrijednost(cell.getNumericCellValue());

						}

					}
					ExcelFajlLista.add(excel);

				}

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}

		return ExcelFajlLista;

	}

}
