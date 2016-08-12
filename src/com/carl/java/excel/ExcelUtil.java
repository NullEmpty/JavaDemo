package com.carl.java.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author: Peichen Xu
 * @since: 2016-8-10
 */
public class ExcelUtil {
	
	private static final String KEY_NAME = "name";

	public static void writeExcel(String path, String sheetName,
			List<Entry> list, String[] languages, String valueLanguage)
			throws InvalidFormatException, IOException {
		if (list == null || list.size() <= 0) {
			return;
		}
		File f = new File(path);
		boolean newFile = !f.exists();
		XSSFWorkbook xssfWorkbook = null;
		FileInputStream fis = null;
		if (newFile) {
			xssfWorkbook = new XSSFWorkbook();
		} else {
			fis = new FileInputStream(f);
			xssfWorkbook = new XSSFWorkbook(fis);
		}

		if (xssfWorkbook.getSheet(sheetName) != null) {
			xssfWorkbook.removeSheetAt(xssfWorkbook.getSheetIndex(sheetName));
		}
		xssfWorkbook.createSheet(sheetName);
		XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
		XSSFRow row0 = xssfSheet.createRow(0);
		XSSFCell cell0 = row0.createCell(0);
		cell0.setCellValue(KEY_NAME);

		int indexValue = -1;
		for (int i = 0; i < languages.length; i++) {
			int j = i + 1;
			cell0 = row0.createCell(j);
			cell0.setCellValue(languages[i]);
			if (languages[i].equalsIgnoreCase(valueLanguage)) {
				indexValue = j;
			}
		}
		if (indexValue <= 0) {
			indexValue = languages.length + 1;
			cell0 = row0.createCell(indexValue);
			cell0.setCellValue(valueLanguage);
		}

		for (int i = 0; i < list.size(); i++) {
			Entry entry = list.get(i);
			XSSFRow row = xssfSheet.createRow(i + 1);
			XSSFCell cell = row.createCell(0);
			cell.setCellValue(entry.getName());
			cell = row.createCell(indexValue);
			cell.setCellValue(entry.getValue());
		}

		FileOutputStream fos = new FileOutputStream(path, false);
		fos.flush();
		xssfWorkbook.write(fos);
		if (fis != null) {
			fis.close();
		}
		fos.close();

		xssfWorkbook.close();
	}
	
	public static List<Entry> ReadExcel(String path, String sheetName,
			String valueLanguage)
			throws InvalidFormatException, IOException {
		if (sheetName == null || valueLanguage == null) {
			return null;
		}
		File f = new File(path);
		if (!f.exists()) {
			return null;
		}
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(f);

		XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
		XSSFRow row0 = xssfSheet.getRow(0);
		int cellEnd = row0.getLastCellNum();
		int indexValue = -1;
		for (int i = 0; i <= cellEnd; i ++) {
			XSSFCell xssfCell = row0.getCell(i);
			if (xssfCell != null && valueLanguage.equals(xssfCell.getStringCellValue())) {
				indexValue = i;
				break;
			}
		}
		if (indexValue <= 0) {
			return null;
		}
		List<Entry> list = new ArrayList<Entry>();
		int rowEnd = xssfSheet.getLastRowNum();
		for (int i = 1; i <= rowEnd; i ++) {
			XSSFRow row = xssfSheet.getRow(i);
			XSSFCell cellName = row.getCell(0);
			XSSFCell cellValue = row.getCell(indexValue);
			if (cellValue == null) {
				continue;
			}
			Entry entry = new Entry();
			entry.setName(cellName.getStringCellValue());
			entry.setValue(cellValue.getStringCellValue());
			list.add(entry);
		}
		
		xssfWorkbook.close();
		return list;
	}

	public static void test(String path) throws InvalidFormatException,
			IOException {
		File file = new File(path);

		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

		int rowstart = xssfSheet.getFirstRowNum();
		int rowEnd = xssfSheet.getLastRowNum();
		for (int i = rowstart; i <= rowEnd; i++) {
			XSSFRow row = xssfSheet.getRow(i);
			if (null == row)
				continue;
			int cellStart = row.getFirstCellNum();
			int cellEnd = row.getLastCellNum();

			for (int k = cellStart; k <= cellEnd; k++) {
				XSSFCell cell = row.getCell(k);
				if (null == cell)
					continue;

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC: // 数字
					System.out.print(cell.getNumericCellValue() + "   ");
					break;
				case Cell.CELL_TYPE_STRING: // 字符串
					System.out.print(cell.getStringCellValue() + "   ");
					break;
				case Cell.CELL_TYPE_BOOLEAN: // Boolean
					System.out.println(cell.getBooleanCellValue() + "   ");
					break;
				case Cell.CELL_TYPE_FORMULA: // 公式
					System.out.print(cell.getCellFormula() + "   ");
					break;
				case Cell.CELL_TYPE_BLANK: // 空值
					System.out.println(" ");
					break;
				case Cell.CELL_TYPE_ERROR: // 故障
					System.out.println(" ");
					break;
				default:
					System.out.print("未知类型   ");
					break;
				}

			}
			System.out.print("\n");
		}
	}

}
