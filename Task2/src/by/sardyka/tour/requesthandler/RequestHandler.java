package by.sardyka.tour.requesthandler;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.tour.entity.AbstractTour;
import by.sardyka.tour.entity.DefaultTour;
import by.sardyka.tour.exception.WrongDataException;
import by.sardyka.tour.filter.AbstractFinder;
import by.sardyka.tour.filter.FindByDuration;
import by.sardyka.tour.filter.FindByPrice;
import by.sardyka.tour.filter.FindByStart;
import by.sardyka.tour.filter.FindByType;
import by.sardyka.tour.performer.Performer;

public class RequestHandler {

	private static final String TYPE = "TYPE";
	private static final String MINIMUM_DURATION = "MINIMUM DURATION";
	private static final String MAXIMUM_PRICE = "MAXIMUM PRICE";
	private static final String EARLIEST_DATE = "EARLIEST DATE";
	private static final Logger LOG = LogManager.getLogger(RequestHandler.class);

	public static ArrayList<ArrayList<AbstractTour>> handleRequest(ArrayList<String[][]> parsedRequest)
			throws WrongDataException {

		if ((parsedRequest == null) || (parsedRequest.isEmpty())) {
			throw new WrongDataException("There aren't requests");
		}
		ArrayList<ArrayList<AbstractTour>> responseList = new ArrayList<>();
		for (String[][] mas : parsedRequest) {
			Performer performer = Performer.getInstance();
			ArrayList<AbstractTour> response = performer.getCopyBD();
			boolean isAccepted = mas.length > 0;
			if (isAccepted) {
				AbstractTour tour = new DefaultTour();
				AbstractFinder[] masFinder = new AbstractFinder[mas.length];
				for (int i = 0; i < mas.length; i++) {
					switch (mas[i][0].toUpperCase()) {
					case TYPE: {
						try {
							tour.setType(mas[i][1]);
						} catch (WrongDataException e) {
							isAccepted = false;
							LOG.log(Level.ERROR, e);
						}
						masFinder[i] = new FindByType();
						break;
					}
					case MINIMUM_DURATION: {
						try {
							tour.setDuration(Integer.valueOf(mas[i][1]));
						} catch (NumberFormatException | WrongDataException e) {
							isAccepted = false;
							LOG.log(Level.ERROR, e);
						}
						masFinder[i] = new FindByDuration();
						break;
					}
					case MAXIMUM_PRICE: {
						try {
							tour.setPrice(Double.valueOf(mas[i][1]));
						} catch (NumberFormatException | WrongDataException e) {
							isAccepted = false;
							LOG.log(Level.ERROR, e);
						}
						masFinder[i] = new FindByPrice();
						break;
					}
					case EARLIEST_DATE: {
						try {
							tour.setStartDate(mas[i][1]);
						} catch (WrongDataException e) {
							isAccepted = false;
							LOG.log(Level.ERROR, e);
						}
						masFinder[i] = new FindByStart();
						break;
					}
					default: {
						isAccepted = false;
						LOG.log(Level.ERROR, " The wrong data in the request " + mas[i][0]);
					}
					}
				}
				for (int j = 0; j < masFinder.length - 1; j++) {
					if (masFinder[j] != null && masFinder[j + 1] != null) {
						masFinder[j].setFinder(masFinder[j + 1]);
					} else {
						isAccepted = false;
					}
				}
				if (isAccepted) {
					masFinder[0].chain(tour, response);
					responseList.add(response);
				}
			}
		}
		LOG.log(Level.INFO, "There are " + responseList.size() + " tour responses");
		return responseList;
	}

}