package hu.itsh.gyakorlat.szotar.io.stats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StatisticsReader {

	static final String typeFilename = "statsType.txt";
	static final String selectionFilename = "statsSelection.txt";

	public static int[] readTypeStatistics() {
		int ret[] = new int[2];
		try (Scanner sc = new Scanner(new File(typeFilename))) {
				if (!sc.hasNextLine()) return new int[] {0,0};
				String line = sc.nextLine();
				ret[0] = Integer.parseInt(line.split(";")[0]);
				ret[1] = Integer.parseInt(line.split(";")[1]);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new int[] {0,0};
	}

	public static int[] readSelectionStatistics() {
		int ret[] = new int[2];
		try (Scanner sc = new Scanner(new File(selectionFilename))) {
			if (sc.hasNextLine()) {
				if (!sc.hasNextLine()) return new int[] {0,0};
				String line = sc.nextLine();
				ret[0] = Integer.parseInt(line.split(";")[0]);
				ret[1] = Integer.parseInt(line.split(";")[1]);
				return ret;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		return new int[] {0,0};
	}


	public static void writeSelectionStatistics(int right, int wrong) {
		try (BufferedWriter bw1 = new BufferedWriter(new FileWriter(selectionFilename, false))) {
			bw1.write(right + ";" + wrong);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeTypeStatistics(int right, int wrong) {
		try (BufferedWriter bw1 = new BufferedWriter(new FileWriter(typeFilename, false))) {
			bw1.write(right + ";" + wrong);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void reset() {
		try (BufferedWriter bw1 = new BufferedWriter(new FileWriter(typeFilename, false))) {
			bw1.write("0;0");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(selectionFilename, false))) {
			bw2.write("0;0");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
