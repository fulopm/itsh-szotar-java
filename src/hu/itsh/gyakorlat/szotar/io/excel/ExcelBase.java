package hu.itsh.gyakorlat.szotar.io.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelBase {
	
	private XSSFSheet firstSheet;
	private XSSFWorkbook workbook;
	
	public ExcelBase(XSSFWorkbook wb) {
		this.workbook = wb;
		this.firstSheet = wb.getSheetAt(0);
		System.out.println("es");
		
		
	}
	
	
	
	public void readWorksheet(String worksheet){
		
		
		
	}
	
	public void runTrough(int sheetNum){
		try{
			this.firstSheet = this.workbook.getSheetAt(sheetNum);
			Iterator<Row> rowIterator = firstSheet.iterator();
			rowIterator.next();
			
			while(rowIterator.hasNext()){
				
				Row currentRow = rowIterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				
				
				while(cellIterator.hasNext()){
					Cell currentCell = cellIterator.next();
					
					switch(currentCell.getCellTypeEnum()){
					case STRING: /* Ha String a cella */ break;
					case NUMERIC: /* Ha szám a cella*/
						if(DateUtil.isCellDateFormatted(currentCell)){
							//Ha dátum
						}else{
							//Ha csak szám
						}
						
						
						break;
					case BOOLEAN: /* Ha logikai érték*/ break;
					case BLANK: /* Ha ürers a cella */ break;
					case FORMULA: /* Ha egy föggvény van a cellában */ break;
					default: System.out.println("nem szabadna lefutnom");
					}
					
				}
				
			}
			
			this.workbook.close();
			
			
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	public ArrayList<String> readExcel(){
		ArrayList<String> lines = new ArrayList<String>();
		
		Iterator<Row> rowIterator = firstSheet.iterator();
		rowIterator.next();
		
		while(rowIterator.hasNext()){
			String s = "";
			
			Row currentRow = rowIterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();
			
			while (cellIterator.hasNext()){
				
				
				Cell currentCell = cellIterator.next();
				
				switch(currentCell.getCellTypeEnum()){
				case STRING: s+= currentCell.getStringCellValue() + ";"; break;
				case NUMERIC: /* Ha szám a cella*/
					if(DateUtil.isCellDateFormatted(currentCell)){
						//Ha dátum
						s+= currentCell.getDateCellValue() + ";";
					}else{
						//Ha csak szám
						s+= currentCell.getNumericCellValue() +  ";";
					}
					
					break;
				case BOOLEAN: s+= currentCell.getBooleanCellValue() + ";" ; break;
				case BLANK: s+= ";"; break;
				case FORMULA: s+= currentCell.getCellFormula() + ";"; break;
				default: System.out.println("nem szabadna lefutnom");
				
				}
				
				
			}
			lines.add(s);
			
		}
		return lines;
		
	}
	
	public Object getCellData(int row, int cell){
		Row selectedRow = firstSheet.getRow(row);
		Cell selectedCell = selectedRow.getCell(cell);
		
			switch(selectedCell.getCellTypeEnum()){
				case STRING: return selectedCell.getStringCellValue();
						
				case NUMERIC:
					if(DateUtil.isCellDateFormatted(selectedCell)){
						return selectedCell.getDateCellValue();
					}else{
						return selectedCell.getNumericCellValue();
					}
				case BOOLEAN: return selectedCell.getBooleanCellValue();
				case BLANK: return "" ;
				case FORMULA: return selectedCell.getCellFormula();
				default: return "unknownData";
			}
		
	}
	
	public void changeCellValue(int row, int cell, String newData){
		
		Row selectedRow = firstSheet.getRow(row);
		Cell selectedCell = selectedRow.getCell(cell);
		
		/* Ha meglesz az sorokra az adat akkor tudom befejezn */
				
	}
	
	public void deleteRow(int row) throws IOException{
		
		//Ha meglesz a sorokra az adat akkor be tudom fejezni
		
		
	}
	
	
	public int getRecordNumb(){
		int records = 0;
		Iterator<Row> rowIterator = firstSheet.iterator();
		rowIterator.next();
		
		while(rowIterator.hasNext()){
			records++;
			rowIterator.next();
		}
		
		return records;
	}
	
}
