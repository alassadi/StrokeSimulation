package stroke;

public class Trip {
	String travelerId;
	String homeCommune;
	String hour;
	String minute;
	String destination;
	String dayOfTheWeek;

	public Trip(String travelerId, String homeCommune, String destination, String dayOfTheWeek, String hour,
			String minute) {
		this.travelerId = travelerId;
		this.homeCommune = homeCommune;
		this.dayOfTheWeek = dayOfTheWeek;
		this.hour = hour;
		this.minute = minute;
		this.destination = destination;
	}

	public String getTravelerId() {
		return travelerId;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCommuneCode() {
		return homeCommune;
	}

	public String getMinute() {
		return minute;
	}

	public String getDayOfTheWeek() {
		return dayOfTheWeek;
	}

}
