package hu.itsh.gyakorlat.szotar.io.stats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class StatisticsReader {

	static final String typeFilename = "statsType.txt";
	static final String selectionFilename = "statsSelection.txt";
	
	
	public static int[] readTypeStatistics() {
		try (Scanner sc = new Scanner(new File(typeFilename))) {
			if (sc.hasNextLine()) {
				String line = sc.nextLine();
				int ret[] = new int[2];
				ret[0] = Integer.parseInt(line.split(";")[0]);
				ret[1] = Integer.parseInt(line.split(";")[1]);
				return ret;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static int[] readSelectionStatistics() {
		try (Scanner sc = new Scanner(new File(selectionFilename))) {
			if (sc.hasNextLine()) {
				String line = sc.nextLine();
				int ret[] = new int[2];
				ret[0] = Integer.parseInt(line.split(";")[0]);
				ret[1] = Integer.parseInt(line.split(";")[1]);
				return ret;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void reset() {
		try {

			FileWriter fw1 = new FileWriter(typeFilename, false);
			BufferedWriter bw1 = new BufferedWriter(fw1);

			bw1.write("0;0");

			bw1.close();
			fw1.close();

			FileWriter fw2 = new FileWriter(selectionFilename, false);
			BufferedWriter bw2 = new BufferedWriter(fw2);

			bw2.write("0;0");

			bw2.close();
			fw2.close();

		} catch (Exception e) {
			System.out.println("Hiba: " + e);
		}
	}
	
}
