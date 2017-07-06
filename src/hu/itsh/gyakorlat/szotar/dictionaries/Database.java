package hu.itsh.gyakorlat.szotar.dictionaries;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.Util;
import hu.itsh.gyakorlat.szotar.io.excel.ExcelBase;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;

public class Database {
	public static final Dictionary dict;

	static {
		dict = new Dictionary();
	}

	public static XSSFWorkbook loadWorkbook(String filename) {
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(new File(filename));
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

	public static void loadDictionary(XSSFWorkbook workbook) {
		
		dict.clear();
		ExcelBase reader = new ExcelBase(workbook);
		List<String> rowsRaw = null;
		try {
		 rowsRaw = reader.getRows(SharedConstants.SEPARATOR_WAVE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		for (String rowRaw : rowsRaw) {
			String[] tokens = rowRaw.split(SharedConstants.SEPARATOR_WAVE);

			Row parsedRow = new Row();

				parsedRow.setId(Util.parseInt(tokens[0]));
				parsedRow.setTimestamp(tokens[1]);
				parsedRow.setPrefix(tokens[2].toLowerCase());
				parsedRow.setWord(tokens[3].toLowerCase());
				parsedRow.setSuffix(tokens[4].toLowerCase());
				parsedRow.setEngExplain(tokens[5]);
				parsedRow.setEngExample(tokens[6]);
				parsedRow.setHun0(tokens[7]);
				parsedRow.setHun1(tokens[8]);
				parsedRow.setHunExplain(tokens[9]);
				parsedRow.setHunExample(tokens[10]);
				parsedRow.setLevel(Util.parseInt(tokens[11]));
				parsedRow.setLang(tokens[12].charAt(0));
				parsedRow.setWordClass(tokens[13]);
				switch (tokens.length) {
				case 14:
					parsedRow.setForm0(null);
					parsedRow.setForm1(null);
					parsedRow.setForm2(null);
					parsedRow.setForm3(null);
					break;
				case 15:
					parsedRow.setForm0(tokens[14]);
					parsedRow.setForm1(null);
					parsedRow.setForm2(null);
					parsedRow.setForm3(null);
					break;
				case 16:
					parsedRow.setForm0(tokens[14]);
					parsedRow.setForm1(tokens[15]);
					parsedRow.setForm2(null);
					parsedRow.setForm3(null);
					break;
				case 17:
					parsedRow.setForm0(tokens[14]);
					parsedRow.setForm1(tokens[15]);
					parsedRow.setForm2(tokens[16]);
					parsedRow.setForm3(null);
					break;
				case 18:
					parsedRow.setForm0(tokens[14]);
					parsedRow.setForm1(tokens[15]);
					parsedRow.setForm2(tokens[16]);
					parsedRow.setForm3(tokens[17]);
				}

			dict.addRow(parsedRow);

		}
		
		dict.sort();

	}
	
	public static boolean isNull() {
		return dict == null || dict.isEmpty();
	}
	
	
}
