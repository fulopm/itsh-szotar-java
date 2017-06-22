package hu.itsh.gyakorlat.szotar.io.excel;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelBase {
	
	private XSSFSheet firstSheet;
	private XSSFWorkbook workbook;
	
	public ExcelBase(XSSFWorkbook wb) {
		this.workbook = wb;
		System.out.println("es");
		runTrough(0);
		
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
				
				//Dolgok csinálása az oszlopokkal.
				
				if(currentRow.getCell(3).getCellTypeEnum() == CellType.STRING){
					System.out.println(currentRow.getCell(3).getStringCellValue());
				}
				
				while(cellIterator.hasNext()){
					Cell currentCell = cellIterator.next();
					if(currentCell.getCellTypeEnum() == CellType.STRING){
					//Dolgok csinálása a cellákkal.
						System.out.println(currentCell.getStringCellValue());
					}
				}
				
			}
			
			this.workbook.close();
			
			
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
}
