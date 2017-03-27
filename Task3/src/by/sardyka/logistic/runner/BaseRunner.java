package by.sardyka.logistic.runner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.logistic.base.LogisticBase;
import by.sardyka.logistic.creator.Creator;
import by.sardyka.logistic.datareader.DataReader;
import by.sardyka.logistic.track.Track;

public class BaseRunner {

	private static final String USER_DIR = "user.dir";
	private static final String INPUT = "\\data\\input.txt";
	private static final Logger LOG = LogManager.getLogger(BaseRunner.class);

	public static void main(String[] args) {
		ArrayList<String> strList = DataReader.readData(System.getProperty(USER_DIR) + INPUT);
		LogisticBase base = LogisticBase.getInstance();
		ArrayList<Track> trackList = Creator.createTrackList(strList, base);
		Collections.sort(trackList);
		LOG.log(Level.INFO, "\n" + trackList.size() + " tracks are about to enter to the target logistic base");
		for (Track thread : trackList) {
			thread.start();
		}
		boolean isOver = false;
		while (!isOver) {
			isOver = true;
			try {
				TimeUnit.MILLISECONDS.sleep(20 + 20 * trackList.size());
			} catch (InterruptedException e) {
				LOG.log(Level.ERROR, e.getMessage());
			}
			for (Track thread : trackList) {
				if (!thread.getState().equals(Thread.State.TERMINATED)) {
					isOver = false;
				}
			}
		}
		if (Track.getNumProcessed() == trackList.size()) {
			LOG.log(Level.INFO, "\nResult: all " + trackList.size() + " tracks was processed successfully");
		} else {
			LOG.log(Level.ERROR,
					"\nResult: " + (trackList.size() - Track.getNumProcessed()) + " tracks WASN'T processed");
		}
	}
}
