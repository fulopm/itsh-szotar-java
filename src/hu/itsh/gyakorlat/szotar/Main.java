package hu.itsh.gyakorlat.szotar;

//import hu.itsh.gyakorlat.szotar.io.excel.ExcelBase;
import hu.itsh.gyakorlat.szotar.io.excel.*;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Main {

	public static void main(String[] args) {
		System.out.println("teszt");
		System.out.println("test2");
		
		
		try {
			ExcelBase e1 = new ExcelBase(new XSSFWorkbook(new File("NGP.xlsx")));
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
