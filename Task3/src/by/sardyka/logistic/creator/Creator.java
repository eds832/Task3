package by.sardyka.logistic.creator;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.logistic.base.LogisticBase;
import by.sardyka.logistic.track.Track;

public class Creator {
	private static final String REG = "\\s*,\\s+";
	private static final int  SIZE = 4;
	private static final Logger LOG = LogManager.getLogger(Creator.class);
	private static final String LOAD = "load";
	private static final String UNLOAD = "unload";
	private static final String URGENT = "urgent";
	private static final String NOT_URGENT = "not urgent";
	private static final int MINIMUM_SPACE = 1;
	private static final int MAXIMUM_SPACE = 499;

	public static ArrayList<Track> createTrackList(ArrayList<String> strList, LogisticBase base) {

		if (strList == null || strList.isEmpty()) {
			LOG.log(Level.ERROR, "\nThis string list doesn't exist or empty");
			throw new RuntimeException("\nThis string list doesn't exist or empty");
		}
		ArrayList<Track> tracks = new ArrayList<>();
		for (int i = 0; i < strList.size(); i++) {
			String strMas[] = strList.get(i).split(REG);
			boolean isAccepted = true;
			if (strMas.length != SIZE) {
				isAccepted = false;
				LOG.log(Level.ERROR, "\nThis data file contains the wrong String: " + strList.get(i));
			}
			if (isAccepted) {
				String trackName = strMas[0];
				Integer space = null;
				try {
					space = Integer.parseInt(strMas[2]);
				} catch (NumberFormatException e) {
					isAccepted = false;
				}
				if (space != null && space >= MINIMUM_SPACE && space <= MAXIMUM_SPACE) {
					switch (strMas[1].toLowerCase()) {
					case LOAD: {
						space = -space;
						break;
					}
					case UNLOAD: {
						break;
					}
					default: {
						isAccepted = false;
						LOG.log(Level.ERROR, "\nThis data file contains the wrong String: " + strMas[1]);
					}
					}
				} else {
					isAccepted = false;
					LOG.log(Level.ERROR, "\nThis data file contains the wrong space info: " + strMas[2]);
				}
				boolean isUrgent = false;
				switch (strMas[3].toLowerCase()) {
				case URGENT: {
					isUrgent = true;
					break;
				}
				case NOT_URGENT: {
					isUrgent = false;
					break;
				}
				default: {
					isAccepted = false;
					LOG.log(Level.ERROR, "\nThis data file contains the wrong String: " + strMas[3]);
				}
				}
				if (isAccepted) {
					tracks.add(new Track(base, i, trackName, space, isUrgent));
				}
			}
		}
		if (!tracks.isEmpty()) {
			LOG.log(Level.INFO, "\nThis data file contains " + tracks.size() + " correct track info");
		} else {
			LOG.log(Level.ERROR, "\nThis track info list is empty");
			throw new RuntimeException("\nThis track info list is empty");
		}
		return tracks;
	}
}
