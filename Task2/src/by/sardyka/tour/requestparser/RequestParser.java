package by.sardyka.tour.requestparser;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.tour.exception.WrongDataException;

public class RequestParser {
	private static final String REG_1 = "\\s*,\\s+";
	private static final String REG_2 = "\\s*:\\s+";
	private static final int MIN_SIZE = 1;
	private static final int MAX_SIZE = 4;
	private static final int INNER_SIZE = 2;
	private static final Logger LOG = LogManager.getLogger(RequestParser.class);

	public static ArrayList<String[][]> parseRequest(ArrayList<String> strList) throws WrongDataException {

		if (strList == null || strList.isEmpty()) {
			throw new WrongDataException(" This request file doesn't exist or empty ");
		}
		ArrayList<String[][]> parsedRequestList = new ArrayList<>();
		for (String str : strList) {
			String strMas[] = str.split(REG_1);
			String parsed[][] = new String[strMas.length][2];
			boolean isAccepted = true;
			if (strMas.length <= MAX_SIZE && strMas.length >= MIN_SIZE) {
				for (int i = 0; i < strMas.length; i++) {
					String strInner[] = strMas[i].split(REG_2);
					if (strInner.length == INNER_SIZE) {
						parsed[i][0] = strInner[0];
						parsed[i][1] = strInner[1];
					} else {
						isAccepted = false;
						LOG.log(Level.ERROR, "This request file contains the wrong String = " + str);
					}
				}
			} else {
				isAccepted = false;
				LOG.log(Level.ERROR, "This request file contains the wrong String = " + str);
			}
			if (isAccepted) {
				parsedRequestList.add(parsed);
			}
		}
		LOG.log(Level.INFO, "There are " + parsedRequestList.size() + " requests");
		return parsedRequestList;
	}
}
