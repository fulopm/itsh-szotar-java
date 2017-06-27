package hu.itsh.gyakorlat.szotar.io.excel;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Dictionary;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;

public class Database {
	public static Dictionary dict;

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
		ExcelBase reader = new ExcelBase(workbook);
		List<String> rowsRaw = reader.getRows(SharedConstants.SEPARATOR);
		System.out.println(rowsRaw.size());

		for (String rowRaw : rowsRaw) {
			String[] cut = rowRaw.split(SharedConstants.SEPARATOR);

			//System.out.println(Arrays.toString(cut));
			Row row = new Row();

			try {
			row.setId(Integer.parseInt(cut[0]));
			row.setTimestamp(cut[1]);
			row.setPrefix(cut[2]);
			row.setWord(cut[3]);
			row.setSuffix(cut[4]);
			row.setEngExplain(cut[5]);
			row.setEngExample(cut[6]);
			row.setHun0(cut[7]);
			row.setHun1(cut[8]);
			row.setHunExplain(cut[9]);
			row.setHunExample(cut[10]);
			row.setLevel(Integer.parseInt(cut[11]));
			row.setLang(cut[12].charAt(0));
			row.setWordClass(cut[13]);
			/*
			 * Ez egy katasztrófa, majd újra kell írni. De legalább működik. FM
			 */
			switch (cut.length) {
			case 14:
				row.setForm0(null);
				row.setForm1(null);
				row.setForm2(null);
				row.setForm3(null);
				break;
			case 15:
				row.setForm0(cut[14]);
				row.setForm1(null);
				row.setForm2(null);
				row.setForm3(null);
				break;
			case 16:
				row.setForm0(cut[14]);
				row.setForm1(cut[15]);
				row.setForm2(null);
				row.setForm3(null);
				break;
			case 17:
				row.setForm0(cut[14]);
				row.setForm1(cut[15]);
				row.setForm2(cut[16]);
				row.setForm3(null);
				break;
			case 18:
				row.setForm0(cut[14]);
				row.setForm1(cut[15]);
				row.setForm2(cut[16]);
				row.setForm3(cut[17]);
			}
		
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
			dict.addRow(row);

		}

	}
}
