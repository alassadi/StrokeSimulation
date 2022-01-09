package stroke;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		CsvSplitter splitter = new CsvSplitter();
		splitter.gridToAgeCommune("joined.csv");

		TravelPopulation travelPopulation = new TravelPopulation();
		travelPopulation.generateTrips("Resfil_2013__JohanH.csv");
		travelPopulation.generateIndividuals("Individfil_2013__JohanH.csv");

        splitter.travelToAgeGroupsSplit();
        System.out.println("Due to the difference between the dates of datasets, the difference in generated persons and generated travel IDs is: ");
        splitter.compareCsvLineNumbers();
        System.out.println("Initializing done.");
        
	}
}
