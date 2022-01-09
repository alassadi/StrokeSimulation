 package stroke;

 import java.io.FileReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Collections;
 import java.util.Date;
 import org.apache.commons.lang3.StringUtils;
 import au.com.bytecode.opencsv.CSVReader;
 import repast.simphony.engine.environment.RunEnvironment;
 import repast.simphony.engine.schedule.ScheduledMethod;

/**
 * Created by Abdulrahman on 08-Jul-20.
 */
 
public class CommuneAgent {
	private int numOfAgeGroups = 20;
	private int numberOfHours = 24;
	private ArrayList<Person> communePopulation = new ArrayList<Person>();
	private ArrayList<Trip> tripHourList = new ArrayList<Trip>();
	private ArrayList<StrokeTraveler> strokeTraveler = new ArrayList<StrokeTraveler>();
	private ArrayList<Trip> strokeTravelerTrip = new ArrayList<Trip>();
	private ArrayList<Stroke> strokesInCommune = new ArrayList<Stroke>();
	private ArrayList<String> strokeInfo = new ArrayList<String>();
	private String communeCode;
	int traveled = 0;
	int strokeIncidents = 0;

	CommuneAgent(String communeCode) {
		this.communeCode = communeCode;

	}

	public void initiate() throws IOException {
		System.out.println(communeCode);

		// fill the arraylists with population
		for (int i = 0; i < numOfAgeGroups; i++) {
			CSVReader reader = new CSVReader(new FileReader(communeCode + "_" + String.valueOf(i + 5) + ".csv"));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Person person = new Person(nextLine[0], Integer.parseInt(nextLine[1]), nextLine[2], nextLine[3]);
				communePopulation.add(person);
			}
			reader.close();
		}
		System.out.println("size pop " + communePopulation.size());

		for (int i = 0; i < numberOfHours; i++) {
			CSVReader reader = new CSVReader(new FileReader("TravelFrom" + communeCode + ".csv"));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				if (nextLine[7].trim().length() > 1) {
					if (Integer.parseInt(extractHours(nextLine[7]).trim()) == i) {
						tripHourList.add(new Trip(nextLine[0], nextLine[3], nextLine[8], nextLine[2],
								extractHours(nextLine[7]).trim(), extractMinutes(nextLine[7]).trim()));
					}
				}

			}

			reader.close();
		}
		System.out.println("size trip " + tripHourList.size());
	}

	@ScheduledMethod(start = 1, interval = 1)
	public void move() {
		// the -1 is to assume that the first tick is: 00:00, instead of 00:01
		int minute = ticksToDate((long) RunEnvironment.getInstance().getCurrentSchedule().getTickCount() - 1, 1);

		int hour = ticksToDate((long) RunEnvironment.getInstance().getCurrentSchedule().getTickCount() - 1, 2);
		int dayOfTheWeek = ticksToDate((long) RunEnvironment.getInstance().getCurrentSchedule().getTickCount() - 1, 3);

		for (int k = 0; k < tripHourList.size(); k++) {
			if (dayOfTheWeek == Integer.parseInt(tripHourList.get(k).getDayOfTheWeek())) {
				if (hour == Integer.parseInt(tripHourList.get(k).getHour())) {
					if (minute == Integer.parseInt(tripHourList.get(k).getMinute())) {
						{
							for (int j = 0; j < communePopulation.size(); j++) {

								if (communePopulation.get(j).getTravelId() != null) {

									if ((tripHourList.get(k).getTravelerId())
											.equals((communePopulation.get(j).getTravelId()))) {

										communePopulation.get(j)
										.setCurrentLocation(tripHourList.get(k).getDestination());
										traveled++;
										break;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// return all travelers home before midnight
	@ScheduledMethod(start = 1440, interval = 1440)
	public void returnHome() {
		for (int i = 0; i < communePopulation.size(); i++) {
			communePopulation.get(i).setCurrentLocation(communePopulation.get(i).getOriginalLocation());
		}
	}

	// initialization function, to set travel IDs to the individuals in the population arraylist
	public void setTravelIdsToPopulation() throws IOException {
		int c = 0;
		int size = communePopulation.size();
		for (int i = 0; i < numOfAgeGroups; i++) {
			CSVReader reader = new CSVReader(new FileReader("travel_" + communeCode + "_" + (i + 5) + ".csv"));
			String[] nextLine;

			while (((nextLine = reader.readNext()) != null) && c < size) {
				communePopulation.get(c).setTravelId(nextLine[0]);
				c++;
			}
			reader.close();
		}
	}

	public void getStrokesInCommune() {
		for (int i = 0; i < StrokePopulation.stroke.size(); i++) {
			if (StrokePopulation.stroke.get(i).location.equals(communeCode)) {
				strokesInCommune.add(StrokePopulation.stroke.get(i));
			}
		}
	}

	public void setStrokeTravelers() {
		// shuffling to randomize the order of people inside communePopulation
		Collections.shuffle(communePopulation);

		Stroke[] strokes = new Stroke[strokesInCommune.size()];
		for (int index = 0; index < strokesInCommune.size(); index++) {
			strokes[index] = strokesInCommune.get(index);
		}

		Person[] population = new Person[communePopulation.size()];
		for (int index = 0; index < communePopulation.size(); index++) {
			population[index] = communePopulation.get(index);
		}

		for (int j = 0; j < strokes.length; j++) {
			for (int i = 0; i < population.length; i++) {
				if ((strokes[j].ageGroup == population[i].age)) {

					StrokeTraveler strokePatient = new StrokeTraveler(population[i], strokes[j]);
					strokeTraveler.add(strokePatient);
					break;
				}
			}
		}
	}

	public void setStrokeTravelerTrip() {

		for (int k = 0; k < tripHourList.size(); k++) {
			for (int j = 0; j < strokeTraveler.size(); j++) {
				if ((tripHourList.get(k).travelerId).equals((strokeTraveler.get(j).person.travelId))) {
					strokeTravelerTrip.add(tripHourList.get(k));
				}
			}
		}
	}

	@ScheduledMethod(start = 1, interval = 1)
	public void assignStroke() {
		// the -1 is  to assume that the first tick is: 00:00, instead of 00:01
		int minute = ticksToDate((long) RunEnvironment.getInstance().getCurrentSchedule().getTickCount() - 1, 1); 
		int hour = ticksToDate((long) RunEnvironment.getInstance().getCurrentSchedule().getTickCount() - 1, 2);
		int day = ticksToDate((long) RunEnvironment.getInstance().getCurrentSchedule().getTickCount() - 1, 6);

		for (int i = 0; i < strokeTraveler.size(); i++) {
			if (strokeTraveler.get(i).stroke.getDay() == day && strokeTraveler.get(i).stroke.getHour() == hour && strokeTraveler.get(i).stroke.getMinute() == minute) {
				strokeTraveler.get(i).person.setHasStroke(true);
				// Here I specify what I want to print out from the simulation to the console.
				System.out.println( strokeTraveler.get(i).person.getOriginalLocation() + ", " + strokeTraveler.get(i).person.getCurrentLocation() );
				strokeIncidents++;
				// Alternative options for console output
				//System.out.println("Stroke incident happened to: ID: " + communePopulation.get(i).id + ", Home Commune: " + communePopulation.get(i).getHomeCommune() + ", Original Location: " + communePopulation.get(i).getOriginalLocation() + ", Age: "+ communePopulation.get(i).age);
				//System.out.println("Stroke incident happened in: " + communePopulation.get(i).getCurrentLocation() + ", Time of incident: Day Number: " + day + ", Time:" + hour + ":"+ minute  );
				//String s = communePopulation.get(i).id + ", " + communePopulation.get(i).getHomeCommune() + ", " + communePopulation.get(i).getOriginalLocation() + ", "+ communePopulation.get(i).getCurrentLocation() + ", " + day + ", " + hour + ":"+ minute;
			}
		}
	}

	// Helper function to convert current tick to date, the starting date is Jan 01, 2018, time zone is central eu.
	// pass 1 to get minutes, 2 to get hour, 3 to get day of the week, 4 for month, 5 for year, 6 for the day number in a year.
	public int ticksToDate(long ticks, int choice) {
		// the number of milliseconds between 1970 and 2018, minus 1hour because of the
		// timezone --> the starting time is 00:00:00 now.
		long year2018InMilliseconds = 1514761200000L;

		// every tick is one minute, therefore we multiply minutes to be in milliseconds
		long ticksToMilliseconds = ticks * 1000 * 60;
		Date date = new Date(year2018InMilliseconds + ticksToMilliseconds);

		// first day of the week in java.util is sunday, i did this to make it monday
		// instead (To match the dataset).
		int[] strDays = new int[] { 7, 1, 2, 3, 4, 5, 6 };

		// System.out.println(date.toString());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (choice == 1) {
			return cal.get(Calendar.MINUTE);
		} else if (choice == 2) {
			return cal.get(Calendar.HOUR_OF_DAY);
		} else if (choice == 3) {
			return strDays[cal.get(Calendar.DAY_OF_WEEK) - 1];
		} else if (choice == 4) {
			return cal.get(Calendar.MONTH);
		} else if (choice == 5) {
			return cal.get(Calendar.YEAR);
		} else if (choice == 6) {
			return cal.get(Calendar.DAY_OF_YEAR) - 1;
		} else {
			return -1;
		}
	}

	// helper function to return the hours segment from a time string
	public String extractHours(String time) {

		return StringUtils.substringBefore(time, ":");
	}

	// helper function to return the minutes segment from a time string
	public String extractMinutes(String time) {
		return StringUtils.substringAfter(time, ":");
	}

	public ArrayList<Person> getKommunAgeList() {
		return communePopulation;
	}

	public String getCommuneCode() {
		return communeCode;
	}

	// helper function used for the sake of validation
	public int countCSVlines() throws IOException {
		int counter = 0;
		CSVReader reader;
		reader = new CSVReader(new FileReader("TravelFrom" + communeCode + ".csv"));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			counter++;
		}
		reader.close();
		return counter;
	}

	// the function that will be used to provide number of travelers each minute to
	// the data sets in the GUI, which later will be plotted in the charts of time
	// series
	public int getTraveled() {
		int temp = traveled;
		traveled = 0;
		return temp;
	}

	// the function that will be used to provide the number of stroke incidents per
	// minute data to the data sets in the GUI, which later will be plotted in the
	// charts of time series
	public int getStrokeIncidents() {
		int temp = strokeIncidents;
		strokeIncidents = 0;
		return temp;
	}
	
}