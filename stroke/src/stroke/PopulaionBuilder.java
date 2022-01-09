package stroke;

import java.io.IOException;
import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;

/**
 * Created by Abdulrahman on 06-Jul-20.
 */

public class PopulaionBuilder implements ContextBuilder<Object> {

	String[] arrayOfCodes = { "1214", "1230", "1231", "1233", "1256", "1257", "1260", "1261", "1262", "1263", "1264",
			"1265", "1266", "1267", "1270", "1272", "1273", "1275", "1276", "1277", "1278", "1280", "1281", "1282",
			"1283", "1284", "1285", "1286", "1287", "1290", "1291", "1292", "1293" };

	@Override
	public Context build(Context<Object> context) {
		context.setId("stroke");
		StrokePopulation.initializeStroke();
		System.out.println("Number of strokes: " + StrokePopulation.stroke.size());
		for (String code : arrayOfCodes) {
			CommuneAgent communeAgent = new CommuneAgent(code);
			try {
				communeAgent.getStrokesInCommune();
				communeAgent.initiate();
				communeAgent.setTravelIdsToPopulation();
				communeAgent.setStrokeTravelers();
				communeAgent.setStrokeTravelerTrip();

			} catch (IOException e) {
				e.printStackTrace();
			}
			context.add(communeAgent);
		}
		return context;
	}
}
