package hu.itsh.gyakorlat.szotar.io.excel;

import static hu.itsh.gyakorlat.szotar.SharedConstants.FORMAT_YYMMDD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelBase {

	private XSSFSheet selectedSheet;
	private XSSFWorkbook workbook;

	public ExcelBase(XSSFWorkbook wb) {
		this.workbook = wb;
		this.selectedSheet = wb.getSheetAt(0);

	}

	public List<String> getRows(String separator) {
		if (workbook == null || selectedSheet == null)
			throw new NullPointerException("Either the workbook or the selectedSheet object is null!");

		Iterator<Row> rowIt = selectedSheet.iterator();
		List<String> rows = new ArrayList<String>();

		while (rowIt.hasNext()) {
			Row nextRow = rowIt.next();
			Iterator<Cell> cellIt = nextRow.cellIterator();
			StringBuilder rowData = new StringBuilder("");

			while (cellIt.hasNext()) {
				Cell nextCell = cellIt.next();

				switch (nextCell.getCellTypeEnum()) {

				case BOOLEAN:
					rowData.append(Boolean.toString(nextCell.getBooleanCellValue()));
					break;

				case ERROR:
					rowData.append(Byte.toString(nextCell.getErrorCellValue()));
					break;

				case FORMULA:
					rowData.append(nextCell.getCellFormula());
					break;

				case NUMERIC: // Date cells are read as NUMERIC. If the Cell has
								// the type Date, we are formatting them to a
								// YYYY-MM-DD format, otherwise it gets
								// converted to a int
					if (DateUtil.isCellDateFormatted(nextCell)) {
						rowData.append(FORMAT_YYMMDD.format(DateUtil.getJavaDate(nextCell.getNumericCellValue())));
					} else {
						rowData.append((int) nextCell.getNumericCellValue());
					}
					break;

				case STRING:
					rowData.append(nextCell.getStringCellValue());
					break;

				case BLANK:
					rowData.append(separator);
					break;

				default:
					throw new IllegalStateException("String is malformed");

				}

			}
			rowData.append(separator);
			rows.add(rowData.toString());
		}

		try {
			workbook.close();
		} catch (IOException e) {
		}

		return rows;
	}

	public int getRecordNumber() {
		int records = 0;
		Iterator<Row> rowIterator = selectedSheet.iterator();
		rowIterator.next();

		while (rowIterator.hasNext()) {
			records++;
			rowIterator.next();
		}

		return records;
	}

}
