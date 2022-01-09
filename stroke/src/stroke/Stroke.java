package stroke;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;

public class Stroke {
	int day;
	int hour;
	int minute;
	String location;
	int ageGroup;

	public Stroke(int day, String time) {
		this.day = day;
		this.hour = setHour(time);
		this.minute = setMinute(time);
		this.location = getRandomCommune();
		this.ageGroup = getRandomeAgeGroup();
	}

	public String getRandomCommune() {
		int[] arrayOfCodes = { 1214, 1230, 1231, 1233, 1256, 1257, 1260, 1261, 1262, 1263, 1264, 1265, 1266, 1267, 1270,
				1272, 1273, 1275, 1276, 1277, 1278, 1280, 1281, 1282, 1283, 1284, 1285, 1286, 1287, 1290, 1291, 1292,
				1293 };

		double[] discreteProbabilities = new double[] { 0.010508356, 0.01781717, 0.01332211, 0.026617795, 0.010876023,
				0.00751794, 0.011476975, 0.023050586, 0.018033845, 0.015681375, 0.011632498, 0.014149551, 0.011538128,
				0.012224391, 0.010063681, 0.009531431, 0.009927033, 0.005539932, 0.01299974, 0.011723094, 0.011033056,
				0.248001419, 0.089495042, 0.033679736, 0.106108043, 0.019513576, 0.024821734, 0.022232205, 0.033152771,
				0.06280628, 0.01471049, 0.031207227, 0.039006768 };

		EnumeratedIntegerDistribution distribution = new EnumeratedIntegerDistribution(arrayOfCodes,
				discreteProbabilities);
		return String.valueOf(distribution.sample());
	}

	public int getRandomeAgeGroup() {
		int[] numsToGenerate = new int[] { 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24 };
		double[] discreteProbabilities = new double[] { 0.00140746, 0.00070373, 0.000879662, 0.000351865, 0.001759324,
				0.003694581, 0.004046446, 0.008796622, 0.019704433, 0.027973258, 0.046622097, 0.06703026, 0.096762843,
				0.154468684, 0.164672766, 0.15059817, 0.140921886, 0.085679099, 0.019352569, 0.001583392 };

		EnumeratedIntegerDistribution distribution = new EnumeratedIntegerDistribution(numsToGenerate,
				discreteProbabilities);
		return distribution.sample();

	}

	public int setHour(String time) {
		String temp = StringUtils.substringBefore(time, ".");
		int hour = Integer.parseInt(temp);
		return (int) hour;

	}

	public int setMinute(String time) {
		// take the numbers after the floating point of time and make it to minutes
		// value
		String temp = StringUtils.substringAfter(time, ".");
		String minute = "0." + temp;
		double minuteInt = Double.parseDouble(minute.substring(0, 4)) * 60;
		return (int) minuteInt;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public String getLocation() {
		return location;
	}

	public int getAgeGroup() {
		return ageGroup;
	}

}
