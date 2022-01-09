package stroke;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * Created by Abdulrahman on 05-Jul-20.
 */

public class CsvSplitter {
    // array of commune codes
    String[] arrayOfCodes = {"1214", "1230", "1231", "1233", "1256", "1257", "1260", "1261", "1262", "1263", "1264", "1265", "1266", "1267", "1270", "1272", "1273", "1275", "1276", "1277", "1278", "1280", "1281", "1282", "1283", "1284", "1285", "1286", "1287", "1290", "1291", "1292", "1293"};

    // array of the indexes of age groups in the CSV file, 7 is 15-20 age group and so on.
    int ageIndex[] = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};

    public void gridToAgeCommune(String path) throws IOException {

        // joined.csv is the created file from QGIS with the joined layers

        for (int i = 0; i < arrayOfCodes.length; i++) {
            for (int j = 0; j < ageIndex.length; j++) {
                CSVReader reader = new CSVReader(new FileReader(path));
                String[] nextLine;
                FileWriter outfile = new FileWriter(arrayOfCodes[i] + "_" + ageIndex[j] + ".csv");
                CSVWriter writer = new CSVWriter(outfile, ',', CSVWriter.NO_QUOTE_CHARACTER);

                while ((nextLine = reader.readNext()) != null) {
                    if (nextLine[0].equals(arrayOfCodes[i])) {
                        for (int k = 0; k < Integer.parseInt(nextLine[ageIndex[j]]); k++) {

                            String[] data1 = {nextLine[0], String.format("%02d", ageIndex[j]), nextLine[3], String.format("%06d", k)};
                            writer.writeNext(data1);
                        }
                    }
                }
                reader.close();
                writer.close();
            }
        }
    }

    public void travelToAgeGroupsSplit() throws IOException {
        for (int i = 0; i < arrayOfCodes.length; i++) {
            for (int j = 0; j < ageIndex.length; j++) {
                CSVReader reader = new CSVReader(new FileReader("TravelIndividuals_" + arrayOfCodes[i] + ".csv"));
                String[] nextLine;
                FileWriter outfile = new FileWriter("travel_" + arrayOfCodes[i] + "_" + ageIndex[j] + ".csv");
                CSVWriter writer = new CSVWriter(outfile, ',', CSVWriter.NO_QUOTE_CHARACTER);

                while ((nextLine = reader.readNext()) != null) {
                    if (getAgeGroup(nextLine[2]) == ageIndex[j]) {

                        String[] data1 = {nextLine[0], nextLine[1], nextLine[2], String.format("%02d", ageIndex[j])};
                        writer.writeNext(data1);

                        /*System.out.println(nextLine[0] + nextLine[1] + nextLine[2] +
                                nextLine[3] + nextLine[4] + nextLine[5] + nextLine[6] + nextLine[7] +
                                nextLine[8] + String.format("%02d", ageIndex[j]));
                        */
                    }
                }
                reader.close();
                writer.close();
            }
        }
    }


    public void compareCsvLineNumbers() throws IOException {

        for (int i = 0; i < arrayOfCodes.length; i++) {
            for (int j = 0; j < ageIndex.length; j++) {
                int count1 = 0;
                int count2 = 0;
                CSVReader reader1 = new CSVReader(new FileReader(arrayOfCodes[i] + "_" + ageIndex[j] + ".csv"));
                String[] nextLine1;
                CSVReader reader2 = new CSVReader(new FileReader("travel_" + arrayOfCodes[i] + "_" + ageIndex[j] + ".csv"));
                String[] nextLine2;
                while ((nextLine1 = reader1.readNext()) != null) {
                    count1++;
                }
                while ((nextLine2 = reader2.readNext()) != null) {
                    count2++;
                }
                System.out.println(arrayOfCodes[i] + ": " + count1 + " " + count2 + " -----> " + (count1-count2));
                reader1.close();
                reader2.close();
            }
        }
    }

    // helper function to return age group according to 5years age groups from SCB 
    public int getAgeGroup(String age) {
        if (!isNumeric(age)) {
            return -1;
        } else {
            int ageInt = Integer.parseInt(age);

            if (ageInt >= 5 && ageInt < 10) {
                return 5;
            } else if (ageInt >= 10 && ageInt < 15) {
                return 6;
            } else if (ageInt >= 15 && ageInt < 20) {
                return 7;
            } else if (ageInt >= 20 && ageInt < 25) {
                return 8;
            } else if (ageInt >= 25 && ageInt < 30) {
                return 9;
            } else if (ageInt >= 30 && ageInt < 35) {
                return 10;
            } else if (ageInt >= 35 && ageInt < 40) {
                return 11;
            } else if (ageInt >= 40 && ageInt < 45) {
                return 12;
            } else if (ageInt >= 45 && ageInt < 50) {
                return 13;
            } else if (ageInt >= 50 && ageInt < 55) {
                return 14;
            } else if (ageInt >= 55 && ageInt < 60) {
                return 15;
            } else if (ageInt >= 60 && ageInt < 65) {
                return 16;
            } else if (ageInt >= 65 && ageInt < 70) {
                return 17;
            } else if (ageInt >= 70 && ageInt < 75) {
                return 18;
            } else if (ageInt >= 75 && ageInt < 80) {
                return 19;
            } else if (ageInt >= 80 && ageInt < 85) {
                return 20;
            } else if (ageInt >= 85 && ageInt < 90) {
                return 21;
            } else if (ageInt >= 90 && ageInt < 95) {
                return 22;
            } else if (ageInt >= 95 && ageInt < 100) {
                return 23;
            } else if (ageInt >= 100) {
                return 24;
            } else {
                return 0;
            }

        }
    }

    // helper function to tell whether a string is numeric or not
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

