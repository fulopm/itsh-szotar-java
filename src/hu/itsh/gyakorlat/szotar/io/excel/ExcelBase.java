package hu.itsh.gyakorlat.szotar.io.excel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import hu.itsh.gyakorlat.szotar.SharedConstants;

public class ExcelBase {

	private XSSFWorkbook workbook;
	private XSSFSheet selectedSheet;

	public ExcelBase(XSSFWorkbook wb) {
		this.workbook = wb;
		this.selectedSheet = wb.getSheetAt(0);

	}

	public List<String> getRows(String separator) {
		if (workbook == null || selectedSheet == null)
			throw new NullPointerException("Either the workbook or the selectedSheet object is null!");

		Iterator<Row> rowIterator = selectedSheet.iterator();

		List<String> rows = new ArrayList<String>();

		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			Iterator<Cell> cellsInRowIterator = nextRow.cellIterator();

			StringBuilder rowStringBuilder = new StringBuilder();

			while (cellsInRowIterator.hasNext()) {
				Cell nextCell = cellsInRowIterator.next();
				switch (nextCell.getCellTypeEnum()) {
				case BLANK:
					rowStringBuilder.append(separator);
					break;
				case BOOLEAN:
					rowStringBuilder.append(Boolean.toString(nextCell.getBooleanCellValue()));
					rowStringBuilder.append(separator);
					break;
				case ERROR:
					rowStringBuilder.append(Byte.toString(nextCell.getErrorCellValue()));
					rowStringBuilder.append(separator);
					break;
				case FORMULA:
					rowStringBuilder.append(nextCell.getCellFormula());
					rowStringBuilder.append(separator);
					break;
				case NUMERIC:
					if (DateUtil.isCellDateFormatted(nextCell)) {
						Date date = DateUtil.getJavaDate(nextCell.getNumericCellValue());
						rowStringBuilder.append(new SimpleDateFormat(SharedConstants.FORMAT_YYMMDD).format(date));
						rowStringBuilder.append(separator);
					} else {
						rowStringBuilder.append((int) nextCell.getNumericCellValue());
						rowStringBuilder.append(separator);
					}

					break;
				case STRING:
					rowStringBuilder.append(nextCell.getStringCellValue());
					rowStringBuilder.append(separator);
					break;
				default:
					break;

				}

			}
			rows.add(rowStringBuilder.toString());
		}

		return rows;
	}

}
