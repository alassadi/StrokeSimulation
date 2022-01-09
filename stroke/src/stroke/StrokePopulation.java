package stroke;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

/**
 * Created by Abdulrahman on 12-Jul-20.
 */

public class StrokePopulation {

	public static ArrayList<Stroke> stroke = new ArrayList<Stroke>();

	public static ArrayList<Stroke> getStroke() {
		return stroke;
	}

	public static void add(Stroke strokeIncident) {
		stroke.add(strokeIncident);

	}

	// this is to mess with the day so no other agent get the next day stroke if we
	// simply removed it from the arraylist
	public static void remove(int index) {
		stroke.get(index).setDay(index - 365);
	}

	public static void initializeStroke() {

		CSVReader readerStroke;
		int strokeDay = 0;
		try {
			readerStroke = new CSVReader(new FileReader("events10.csv"), CSVParser.DEFAULT_SEPARATOR,
					CSVParser.DEFAULT_QUOTE_CHARACTER, 0);
			String[] nextLine;
			// the first line in csv is the first day and second line is the second day...
			while ((nextLine = readerStroke.readNext()) != null) {

				for (int i = 0; i < nextLine.length; i++) {
					if (nextLine[i].equals("")) {
						break;
					}
					Stroke strokeIncident = new Stroke(strokeDay, nextLine[i]);
					stroke.add(strokeIncident);
					System.out.println(strokeDay + " " + nextLine[i]);
				}
				strokeDay++;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
