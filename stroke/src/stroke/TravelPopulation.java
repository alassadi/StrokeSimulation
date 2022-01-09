package stroke;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * Created by Abdulrahman on 06-Jul-20.
 */

public class TravelPopulation {
	String[] arrayOfCodes = { "1214", "1230", "1231", "1233", "1256", "1257", "1260", "1261", "1262", "1263", "1264",
			"1265", "1266", "1267", "1270", "1272", "1273", "1275", "1276", "1277", "1278", "1280", "1281", "1282",
			"1283", "1284", "1285", "1286", "1287", "1290", "1291", "1292", "1293" };

	public void generateTrips(String path) throws IOException {
		/*
		 * indexes in the original csv file for: 0-> person id, 1->trip id, 7->day of
		 * travel, 10->home commune, 18->age, 65->if started the journey from home,
		 * 66->starting location, 71->starting time with minutes, 77-> destination
		 * commune, 82->arrival time with minutes, weighting value
		 */
		int desiredIndexes[] = { 0, 1, 7, 10, 18, 65, 66, 71, 77, 82, 95 };
		int counter = 0;

		for (int i = 0; i < arrayOfCodes.length; i++) {

			CSVReader reader = new CSVReader(new FileReader(path));
			String[] nextLine;
			FileWriter outfile = new FileWriter("TravelFrom" + arrayOfCodes[i] + ".csv");
			CSVWriter writer = new CSVWriter(outfile, ',', CSVWriter.NO_QUOTE_CHARACTER);

			while ((nextLine = reader.readNext()) != null) {
				if (nextLine[10].equals(arrayOfCodes[i])) {
					// this loop to duplicate a line by the value of weighting
					for (double k = 0; k < Double.parseDouble(nextLine[95]); k++) {

						String[] data1 = { nextLine[0], nextLine[1], nextLine[7], nextLine[10], nextLine[18],
								nextLine[65], nextLine[66], nextLine[71], nextLine[77], nextLine[82] }; 
																										
						writer.writeNext(data1);
						counter++;

						/*
						 * System.out.println(nextLine[1] + " " + nextLine[7] + " " + nextLine[10] + " "
						 * + nextLine[65] + " " + nextLine[66] + " " + nextLine[72] + " " + nextLine[77]
						 * + " " + nextLine[83]);
						 */

					}
				}
			}
			reader.close();
			writer.close();
		}
		System.out.println("The number of generated trips: " + counter);
	}

	public void generateIndividuals(String path) throws IOException {
		//indexes in the original csv file for:
		
		int desiredIndexes[] = { 0, 12, 25 };

		int counter = 0;
		for (int i = 0; i < arrayOfCodes.length; i++) {

			CSVReader reader = new CSVReader(new FileReader(path));
			String[] nextLine;
			FileWriter outfile = new FileWriter("TravelIndividuals_" + arrayOfCodes[i] + ".csv");
			CSVWriter writer = new CSVWriter(outfile, ',', CSVWriter.NO_QUOTE_CHARACTER);

			while ((nextLine = reader.readNext()) != null) {
				if (nextLine[12].equals(arrayOfCodes[i])) {
					// this loop to duplicate a line by the value of weighting
					for (double k = 0; k < Double.parseDouble(nextLine[71]); k++) {

						String[] data1 = { nextLine[0], nextLine[12], nextLine[23] }; // insert id here
						writer.writeNext(data1);
						counter++;
						/*
						 * System.out.println(nextLine[1] + " " + nextLine[7] + " " + nextLine[10] + " "
						 * + nextLine[65] + " " + nextLine[66] + " " + nextLine[72] + " " + nextLine[77]
						 * + " " + nextLine[83]);
						 */
					}
				}
			}
			reader.close();
			writer.close();
		}
		System.out.println("The number of generated travel individuals: " + counter);
	}
}
