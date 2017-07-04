package hu.itsh.gyakorlat.szotar.io.excel;

import java.io.IOException;
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
		String cellData;
		List<String> rows = new ArrayList<String>();
		
		
		while (rowIt.hasNext()) {
			Row nextRow = rowIt.next();
			Iterator<Cell> cellIt = nextRow.cellIterator();
			String rowData = "";
			while (cellIt.hasNext()) {
				Cell nextCell = cellIt.next();
				switch (nextCell.getCellTypeEnum()) {
				case BLANK:
					rowData += separator;
					break;
				case BOOLEAN:
					rowData += Boolean.toString(nextCell.getBooleanCellValue());
					rowData += separator;
					break;
				case ERROR:
					rowData += Byte.toString(nextCell.getErrorCellValue());
					rowData += separator;
					break;
				case FORMULA:
					rowData += nextCell.getCellFormula();
					rowData += separator;
					break;
				case NUMERIC: // we're assuming that NUMERIC cells are Dates
					if (DateUtil.isCellDateFormatted(nextCell)) {
						Date date = DateUtil.getJavaDate(nextCell.getNumericCellValue());
						String dateFormatted = SharedConstants.FORMAT_YYMMDD.format(date);
						rowData += dateFormatted;
						rowData += separator;
					} else {
						rowData += (int)nextCell.getNumericCellValue();
						rowData += separator;
					}
					
					break;
				case STRING:
					rowData += nextCell.getStringCellValue();
					rowData += separator;
					break;
				default:
					System.out.println("******* AJJAJJ *******");
					break;
				
				}
				
			}
			rows.add(rowData);
		}
		

		
		
		return rows;
	}

	/*
	 * 
	 * public void readWorksheet(String worksheet){
	 * 
	 * 
	 * 
	 * }
	 * 
	 * public void runTrough(int sheetNum){ try{ this.selectedSheet =
	 * this.workbook.getSheetAt(sheetNum); Iterator<Row> rowIterator =
	 * selectedSheet.iterator(); rowIterator.next();
	 * 
	 * while(rowIterator.hasNext()){
	 * 
	 * Row currentRow = rowIterator.next(); Iterator<Cell> cellIterator =
	 * currentRow.iterator();
	 * 
	 * 
	 * while(cellIterator.hasNext()){ Cell currentCell = cellIterator.next();
	 * 
	 * switch(currentCell.getCellTypeEnum()){ case STRING:break; case NUMERIC:
	 * if(DateUtil.isCellDateFormatted(currentCell)){ //Ha d�tum }else{ //Ha
	 * csak sz�m }
	 * 
	 * 
	 * break; case BOOLEAN:break; case BLANK: break; case FORMULA: break;
	 * default: System.out.println("nem szabadna lefutnom"); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * this.workbook.close();
	 * 
	 * 
	 * }catch(Exception e){ System.out.println(e); }
	 * 
	 * }
	 * 
	 * public ArrayList<String> readExcel(){ ArrayList<String> lines = new
	 * ArrayList<String>();
	 * 
	 * Iterator<Row> rowIterator = selectedSheet.iterator(); rowIterator.next();
	 * 
	 * while(rowIterator.hasNext()){ String s = "";
	 * 
	 * Row currentRow = rowIterator.next(); Iterator<Cell> cellIterator =
	 * currentRow.iterator();
	 * 
	 * while (cellIterator.hasNext()){
	 * 
	 * 
	 * Cell currentCell = cellIterator.next();
	 * 
	 * switch(currentCell.getCellTypeEnum()){ case STRING: s+=
	 * currentCell.getStringCellValue() + ";"; break; case NUMERIC:
	 * if(DateUtil.isCellDateFormatted(currentCell)){ //Ha d�tum s+=
	 * currentCell.getDateCellValue() + ";"; }else{ //Ha csak sz�m s+=
	 * currentCell.getNumericCellValue() + ";"; }
	 * 
	 * break; case BOOLEAN: s+= currentCell.getBooleanCellValue() + ";" ; break;
	 * case BLANK: s+= ";"; break; case FORMULA: s+=
	 * currentCell.getCellFormula() + ";"; break; default:
	 * System.out.println("nem szabadna lefutnom");
	 * 
	 * }
	 * 
	 * 
	 * } lines.add(s);
	 * 
	 * } return lines;
	 * 
	 * }
	 * 
	 * public Object getCellData(int row, int cell){ Row selectedRow =
	 * selectedSheet.getRow(row); Cell selectedCell = selectedRow.getCell(cell);
	 * 
	 * switch(selectedCell.getCellTypeEnum()){ case STRING: return
	 * selectedCell.getStringCellValue();
	 * 
	 * case NUMERIC: if(DateUtil.isCellDateFormatted(selectedCell)){ return
	 * selectedCell.getDateCellValue(); }else{ return
	 * selectedCell.getNumericCellValue(); } case BOOLEAN: return
	 * selectedCell.getBooleanCellValue(); case BLANK: return "" ; case FORMULA:
	 * return selectedCell.getCellFormula(); default: return "unknownData"; }
	 * 
	 * }
	 * 
	 * public void changeCellValue(int row, int cell, String newData){
	 * 
	 * Row selectedRow = selectedSheet.getRow(row); Cell selectedCell =
	 * selectedRow.getCell(cell);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 */
	public void deleteRow(int row) throws IOException {

		// Ha meglesz a sorokra az adat akkor be tudom fejezni

	}

	public int getRecordNumb() {
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
