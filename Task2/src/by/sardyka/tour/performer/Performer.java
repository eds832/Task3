package by.sardyka.tour.performer;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.tour.exception.WrongDataException;
import by.sardyka.tour.requesthandler.RequestHandler;
import by.sardyka.tour.requestparser.RequestParser;
import by.sardyka.tour.temporary.TemporaryParsedData;
import by.sardyka.tour.creator.TourCreator;
import by.sardyka.tour.dataparser.DataParser;
import by.sardyka.tour.datareader.DataReader;
import by.sardyka.tour.datawriter.DataWriter;
import by.sardyka.tour.entity.AbstractTour;

public class Performer {
	private ArrayList<AbstractTour> tourList = new ArrayList<>();
	private static final Performer INSTANCE = new Performer();
	private static final String USER_DIR = "user.dir";
	private static final String INPUT = "\\data\\input.txt";
	private static final String OUTPUT = "\\data\\output.txt";
	private static final String REQUEST = "\\data\\request.txt";
	private static final Logger LOG = LogManager.getLogger(Performer.class);

	private Performer() {
	}

	public ArrayList<AbstractTour> getTourList() {
		return tourList;
	}

	public static Performer getInstance() {
		return INSTANCE;
	}

	public ArrayList<AbstractTour> getCopyBD() {
		return new ArrayList<AbstractTour>(tourList);
	}

	public void doAction() {
		LOG.log(Level.INFO, "Start");
		String sys = System.getProperty(USER_DIR);
		ArrayList<String> strList = DataReader.readData(sys + INPUT);
		ArrayList<TemporaryParsedData> parsedData = null;
		ArrayList<String[][]> parsedRequest = null;
		ArrayList<ArrayList<AbstractTour>> responses = null;
		try {
			parsedData = DataParser.parseData(strList);
		} catch (WrongDataException e) {
			LOG.log(Level.ERROR, e);
		}
		boolean err = parsedData == null;
		if (!err) {
			try {
				TourCreator.createTourList(parsedData);
			} catch (WrongDataException e) {
				LOG.log(Level.ERROR, e);
			}
			err = tourList == null;
			if (!err) {
				err = tourList.isEmpty();
			}
			if (!err) {
				strList = DataReader.readData(sys + REQUEST);
				try {
					parsedRequest = RequestParser.parseRequest(strList);
				} catch (WrongDataException e) {
					LOG.log(Level.ERROR, e);
				}
				err = parsedRequest == null;
				if (!err) {
					err = parsedRequest.isEmpty();
				}
				if (!err) {
					try {
						responses = RequestHandler.handleRequest(parsedRequest);
					} catch (WrongDataException e) {
						LOG.log(Level.ERROR, e);
					}
				}
				err = responses == null;
				if (!err) {
					err = responses.isEmpty();
				}
				if (!err) {
					DataWriter.writeData(responses, sys + OUTPUT);
				}
			}
		}
		if (err) {
			DataWriter.writeData("Input or request is incorrect", sys + OUTPUT);
		}
		LOG.log(Level.INFO, "Stop");
	}
}
