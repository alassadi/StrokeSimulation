package stroke;

/**
 * Created by Abdulrahman on 02-Jul-20.
 */

public class Person {
    String homeCommune;
    int age;
    String originalLocation;
    String currentLocation;
    String id;
    String travelId;
    boolean hasStroke = false;


	public Person(String homeCommune, int age, String originalLocation, String id) {
        this.homeCommune = homeCommune;
        this.age = age;
        this.originalLocation = originalLocation;
        this.currentLocation = originalLocation;
        this.id = id;
        
    }

    public String getTravelId() {
		return travelId;
	}

	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}
	
    public String getHomeCommune() {
        return homeCommune;
    }

    public int getAge() {
        return age;
    }

    public String getOriginalLocation() {
        return originalLocation;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public String getId() {
        return id;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
    
	public boolean isHasStroke() {
		return hasStroke;
	}

	public void setHasStroke(boolean hasStroke) {
		this.hasStroke = hasStroke;
	}

	public String ageGroupToAge() {
        
        if (age ==5 ) {
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
        } else if (age== 12) {
            return "40-44";
        } else if (age ==13) {
            return "45-49";
        } else if (age == 14) {
            return "50-54";
        } else if (age == 15) {
            return "55-59";
        } else if (age ==16) {
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
