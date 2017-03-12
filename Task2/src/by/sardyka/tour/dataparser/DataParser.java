package by.sardyka.tour.dataparser;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.tour.exception.WrongDataException;
import by.sardyka.tour.temporary.TemporaryParsedData;

public class DataParser {
	private static final String REG = "\\s*,\\s+";
	private static final int SIZE = 8;
	private static final Logger LOG = LogManager.getLogger(DataParser.class);

	public static ArrayList<TemporaryParsedData> parseData(ArrayList<String> strList) throws WrongDataException {

		if (strList == null || strList.isEmpty()) {
			throw new WrongDataException(" This data file doesn't exist or empty");
		}
		ArrayList<TemporaryParsedData> parsedDataList = new ArrayList<>();
		for (String str : strList) {
			String strMas[] = str.split(REG);
			boolean isAccepted = true;
			if (strMas.length != SIZE) {
				isAccepted = false;
				LOG.log(Level.ERROR, "This data file contains the wrong String = " + str);
			}
			if (isAccepted) {
				TemporaryParsedData temporaryParsedData = new TemporaryParsedData();
				temporaryParsedData.setTourType(strMas[0]);
				temporaryParsedData.setTourSubType(strMas[1]);
				temporaryParsedData.setTourTitle(strMas[2]);
				temporaryParsedData.setStart(strMas[3]);
				Integer duration = null;
				try {
					duration = Integer.parseInt(strMas[4]);
				} catch (NumberFormatException e) {
					isAccepted = false;
					LOG.log(Level.ERROR, " This data file contains the wrong duration = " + strMas[4]);
				}
				if (duration != null) {
					temporaryParsedData.setDuration(duration);
				}
				temporaryParsedData.setTransport(strMas[5]);
				temporaryParsedData.setMeal(strMas[6]);
				Double price = null;
				try {
					price = Double.parseDouble(strMas[7]);
				} catch (NumberFormatException e) {
					isAccepted = false;
					LOG.log(Level.ERROR, "This data file contains the wrong price = " + strMas[7]);
				}
				if (price != null) {
					temporaryParsedData.setPrice(price);
				}
				if (isAccepted) {
					parsedDataList.add(temporaryParsedData);
				}
			}
		}
		LOG.log(Level.INFO, "This data file contains " + parsedDataList.size() + " tour info");
		return parsedDataList;
	}
}
