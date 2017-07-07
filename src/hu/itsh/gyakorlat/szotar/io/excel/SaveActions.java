package hu.itsh.gyakorlat.szotar.io.excel;

import static hu.itsh.gyakorlat.szotar.SharedConstants.SEPARATOR_DOLLAR;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;

import hu.itsh.gyakorlat.szotar.SharedConstants;
import hu.itsh.gyakorlat.szotar.dictionaries.Database;
import hu.itsh.gyakorlat.szotar.dictionaries.Dictionary;
import hu.itsh.gyakorlat.szotar.io.excel.ds.Row;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionMenuDbOpen;
import hu.itsh.gyakorlat.szotar.ui.actions.ActionSaveDb;

public class SaveActions {

	Dictionary dict;
	XSSFWorkbook wb;

	public SaveActions() throws InvalidFormatException, IOException {
		this.dict = Database.dict;
		wb = new XSSFWorkbook(ActionMenuDbOpen.f);
	}

	public void saveAsCSV() throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(new File("table.csv")));
		for (int i = 0; i < dict.getRowCount(); i++) {
			StringBuilder rowStringBuilder = new StringBuilder();
			Row r = dict.getRow(i);

			rowStringBuilder.append(r.getId()).append(SEPARATOR_DOLLAR).append(r.getTimestamp())
					.append(SEPARATOR_DOLLAR).append(r.getPrefix()).append(SEPARATOR_DOLLAR).append(r.getWord())
					.append(SEPARATOR_DOLLAR).append(r.getSuffix()).append(SEPARATOR_DOLLAR).append(r.getEngExplain())
					.append(SEPARATOR_DOLLAR).append(r.getEngExample()).append(SEPARATOR_DOLLAR).append(r.getHun0())
					.append(SEPARATOR_DOLLAR).append(r.getHun1()).append(SEPARATOR_DOLLAR).append(r.getHunExplain())
					.append(SEPARATOR_DOLLAR).append(r.getHunExample()).append(SEPARATOR_DOLLAR).append(r.getLevel())
					.append(SEPARATOR_DOLLAR).append(r.getLang()).append(SEPARATOR_DOLLAR).append(r.getWordClass())
					.append(SEPARATOR_DOLLAR);

			if (r.getForm0() == null) {
				rowStringBuilder.append(SEPARATOR_DOLLAR);
			} else {
				rowStringBuilder.append(r.getForm0()).append(SEPARATOR_DOLLAR);
			}

			if (r.getForm1() == null) {
				rowStringBuilder.append(SEPARATOR_DOLLAR);
			} else {
				rowStringBuilder.append(r.getForm1()).append(SEPARATOR_DOLLAR);
			}

			if (r.getForm2() == null) {
				rowStringBuilder.append(SEPARATOR_DOLLAR);
			} else {
				rowStringBuilder.append(r.getForm2()).append(SEPARATOR_DOLLAR);
			}

			if (r.getForm3() == null) {
				rowStringBuilder.append(SEPARATOR_DOLLAR);
			} else {
				rowStringBuilder.append(r.getForm3()).append(SEPARATOR_DOLLAR);
			}
			//
			pw.println(rowStringBuilder.toString());
		}
		pw.close();
	}

	public void csvToExcel() throws IOException, InvalidFormatException {

		try {

			CSVReader reader = new CSVReader(new FileReader(SharedConstants.FILENAME_TEMP_CSV),
					SEPARATOR_DOLLAR.charAt(0));
			String[] line;
			CreationHelper helper = wb.getCreationHelper();
			Sheet firstSheet = wb.getSheetAt(0);
			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setDataFormat(helper.createDataFormat().getFormat("yyyy.mm.dd"));

			int r = 0;
			line = reader.readNext();
			while (line != null) {
				org.apache.poi.ss.usermodel.Row row = firstSheet.createRow(r++);

				for (int i = 0; i < line.length; i++) {
					if (i == 0 || i == 11) {
						try {
							Cell c = row.createCell(i);
							c.setCellType(CellType.NUMERIC);
							c.setCellValue(Integer.parseInt(line[i]));
							// row.createCell(i).setCellValue(Integer.parseInt(line[i]));
						} catch (Exception e) {
							System.out.println(e);
						}
					} else if (i == 1) {
						try {
							LocalDate ld = LocalDate.parse(line[i], DateTimeFormatter.ofPattern("uuuu-MM-dd"));

							Cell c = row.createCell(i);
							c.setCellValue(DateUtil
									.getExcelDate(Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant())));

							c.setCellStyle(cellStyle);

						} catch (Exception e) {
							System.out.println(e);
						}
					}

					else {
						System.out.println("Row: " + row.getRowNum() + " Cell:" + i);
						Cell c = row.createCell(i);
						c.setCellType(CellType.STRING);
						c.setCellValue(helper.createRichTextString(line[i]));
						// row.createCell(i).setCellValue(helper.createRichTextString(line[i]));
					}
				}

				line = reader.readNext();

			}

			reader.close();
			new File(SharedConstants.FILENAME_TEMP_CSV).delete();

			saveToXLSX();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveToXLSX() throws InvalidFormatException, IOException {
		XSSFWorkbook saveWB = wb;
		try (FileOutputStream fos = new FileOutputStream(ActionMenuDbOpen.f, true)) {
			saveWB.write(fos);
			saveWB.close();
		}

	}

	public void fullSave() throws IOException, InvalidFormatException {
		saveAsCSV();
		csvToExcel();
	}

}
