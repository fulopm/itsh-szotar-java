package hu.itsh.gyakorlat.szotar.io.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;

import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.dictionaries.Dictionary;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;
public class SaveActions {
	
	Dictionary dict;
	
	public SaveActions(){
		this.dict = Database.dict;
	}
	
	public void saveAsCSV() throws IOException{
		PrintWriter pw = new PrintWriter(new FileWriter(new File("table.csv")));
		for (int i = 0; i < dict.getRowCount(); i++) {
			String s = "";
			Row r = dict.getRow(i);
			
			
			
			s += 	  r.getId() + "$"
					+ r.getTimestamp() + "$"
					+ r.getPrefix() + "$"
					+ r.getWord() + "$"
					+ r.getSuffix() + "$"
					+ r.getEngExplain() + "$"
					+ r.getEngExample() + "$"
					+ r.getHun0() + "$"
					+ r.getHun1() + "$"
					+ r.getHunExplain() + "$"
					+ r.getHunExample() + "$"
					+ r.getLevel() + "$"
					+ r.getLang() + "$"
					+ r.getWordClass() + "$";
			
			if(r.getForm0() == null){
				s+= "$";
			}else{
				s+= r.getForm0() + "$";
			}
			
			if(r.getForm1()== null){
				s += "$";
			}else{
				s += r.getForm1() + "$";
			}
			
			if(r.getForm2() == null){
				s += "$";
			}else{
				s += r.getForm2() + "$";
			}
			
			if(r.getForm3() == null){
				s+= "$";
			}else{
				s += r.getForm3() + "$";
			}
			
//					+ r.getForm0() + "$"
//					+ r.getForm1() + "$"
//					+ r.getForm2() + "$"
//					+ r.getForm3();
			pw.println(s);
		}
		pw.close();
	}
	
	public void cvsToExcel() throws IOException, InvalidFormatException{
		CSVReader reader = new CSVReader(new FileReader("table.csv"),'$');
		String[] line;
		CreationHelper helper = ExcelBase.workbook.getCreationHelper();
		Sheet firstSheet = ExcelBase.workbook.getSheetAt(0);
		CellStyle cellStyle = ExcelBase.workbook.createCellStyle();
		cellStyle.setDataFormat(helper.createDataFormat().getFormat("yyyy.mm.dd"));
		
		int r = 0;
		line = reader.readNext();
		while(line != null){
			org.apache.poi.ss.usermodel.Row row = firstSheet.createRow(r++);
			
			for(int i = 0; i < line.length; i++){
				 if(i == 0 || i == 11){
					try{
						Cell c = row.createCell(i);
						c.setCellType(CellType.NUMERIC);
						c.setCellValue(Integer.parseInt(line[i]));
						//row.createCell(i).setCellValue(Integer.parseInt(line[i]));
					}catch(Exception e){
						System.out.println(r);
						System.out.println(e);
					}
				}else if(i == 1){
					try{
						
						Cell c = row.createCell(i);
						c.setCellValue(new Date());
						c.setCellStyle(cellStyle);
						
						
					}catch(Exception e){
						System.out.println(e);
					}
				}
				
				else{
					row.createCell(i).setCellValue(helper.createRichTextString(line[i]));
				}
			}
			line = reader.readNext();
			
		}
		saveToXLSX();
		
	}
	
	public void saveToXLSX() throws InvalidFormatException, IOException{
		System.out.println("XLSX save");
		try{
			XSSFWorkbook saveWB = ExcelBase.workbook;
			FileOutputStream fos = new FileOutputStream("NGP.xlsx",true);
			
			saveWB.write(fos);
			saveWB.close();
			fos.flush();
			fos.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		

		
	}
	
	public void fullSave() throws IOException, InvalidFormatException{
		this.saveAsCSV();
		this.cvsToExcel();
	}
	
}

