package stroke;

/**
 * Created by Abdulrahman on 08-Jul-20.
 */

public class StrokeTraveler {
	Stroke stroke;
	Person person;

	public StrokeTraveler(Person person, Stroke stroke) {
		this.stroke = stroke;
		this.person = person;
	}

	public int getStrokeHour() {
		return this.stroke.hour;
	}

	public int getStrokeMinute() {
		return this.stroke.minute;
	}

	public int getStrokeDay() {
		return this.stroke.day;
	}

	public String getStrokeLocation() {
		return this.stroke.location;
	}

	public String patientCommune() {
		return this.person.homeCommune;
	}

	public String patientAge() {
		return ageGroupToAge(this.person.age);
	}

	public String patientOriginalLocation() {
		return this.person.originalLocation;
	}

	public String patientCurrentLocatuon() {
		return this.person.currentLocation;
	}

	public String patientId() {
		return this.person.id;
	}

	// helper function to return age group according to 5years age groups from SCB
	public String ageGroupToAge(int age) {

		if (age == 5) {
			return "5-9";
		} else if (age == 6) {
			return "10-14";
		} else if (age == 7) {
			return "15-19";
		} else if (age == 8) {
			return "20-24";
		} else if (age == 9) {
			return "25-29";
		} else if (age == 10) {
			return "30-34";
		} else if (age == 11) {
			return "35-39";
		} else if (age == 12) {
			return "40-44";
		} else if (age == 13) {
			return "45-49";
		} else if (age == 14) {
			return "50-54";
		} else if (age == 15) {
			return "55-59";
		} else if (age == 16) {
			return "60-64";
		} else if (age == 17) {
			return "65-69";
		} else if (age == 18) {
			return "70-74";
		} else if (age == 19) {
			return "75-79";
		} else if (age == 20) {
			return "80-84";
		} else if (age == 21) {
			return "85-89";
		} else if (age == 22) {
			return "90-94";
		} else if (age == 23) {
			return "95-99";
		} else if (age == 24) {
			return "100+";
		} else {
			return "";
		}

	}

}
