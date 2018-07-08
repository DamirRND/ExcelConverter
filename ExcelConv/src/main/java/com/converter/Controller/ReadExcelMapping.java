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

		List ApotekaLista = new ArrayList();
		try {

			Workbook workbook = WorkbookFactory.create(bis);

			int numberOfSheets = workbook.getNumberOfSheets();

			for (int i = 0; i < numberOfSheets; i++) {

				Sheet sheet = workbook.getSheetAt(i);

				Iterator rowIterator = sheet.iterator();

				rowIterator.next();

				while (rowIterator.hasNext()) {

					ExcelFajlModel apoteka = new ExcelFajlModel();

					Row row = (Row) rowIterator.next();
					Iterator cellIterator = row.cellIterator();


					while (cellIterator.hasNext()) {

						Cell cell = (Cell) cellIterator.next();

						if (cell.getColumnIndex() == 0) {

							apoteka.setSifraApoteke((int) (cell.getNumericCellValue()));

						}

						else if (cell.getColumnIndex() == 1) {
							apoteka.setNazivApoteke(cell.getStringCellValue());

						}

						else if (cell.getColumnIndex() == 2) {

							apoteka.setSifraRobe((int) (cell.getNumericCellValue()));

						} else if (cell.getColumnIndex() == 3) {

							apoteka.setNazivRobe(cell.getStringCellValue());

						} else if (cell.getColumnIndex() == 4) {

							apoteka.setKolicina((int) cell.getNumericCellValue());

						} else if (cell.getColumnIndex() == 5) {
							apoteka.setVrijednost(cell.getNumericCellValue());

						}

					}
					ApotekaLista.add(apoteka);

				}

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}

		return ApotekaLista;

	}

}
