package com.carl.java.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

	public static void writeExcel(String path, List<Entry> list) throws InvalidFormatException, IOException {
		if (list == null || list.size() <= 0) {
			return;
		}
		String sheetName = "Picture";
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		xssfWorkbook.createSheet(sheetName);
		XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
		XSSFRow row0 = xssfSheet.createRow(0);
		XSSFCell cell0 = row0.createCell(0);
		cell0.setCellValue("name");
		cell0 = row0.createCell(1);
		cell0.setCellValue("zh-rCN");
		for (int i = 0; i < list.size(); i++) {
			Entry entry = list.get(i);
			XSSFRow row = xssfSheet.createRow(i + 1);
			XSSFCell cell = row.createCell(0);
			cell.setCellValue(entry.getName());
			cell = row.createCell(1);
			cell.setCellValue(entry.getValue());
		}
		
		xssfWorkbook.write(new FileOutputStream(path));
		xssfWorkbook.close();
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
