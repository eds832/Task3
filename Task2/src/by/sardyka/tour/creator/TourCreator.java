package by.sardyka.tour.creator;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.tour.entity.AbstractTour;
import by.sardyka.tour.entity.CruiseTour;
import by.sardyka.tour.entity.FamilyTour;
import by.sardyka.tour.entity.GuidedTour;
import by.sardyka.tour.entity.ShoppingTour;
import by.sardyka.tour.exception.WrongDataException;
import by.sardyka.tour.performer.Performer;
import by.sardyka.tour.temporary.TemporaryParsedData;

public class TourCreator {

	private static final String FAMILY = "FAMILY";
	private static final String GUIDED = "GUIDED";
	private static final String SHOPPING = "SHOPPING";
	private static final String CRUISE = "CRUISE";
	private static final Logger LOG = LogManager.getLogger(TourCreator.class);

	public static void createTourList(ArrayList<TemporaryParsedData> parsedDataList) throws WrongDataException {
		Performer performer = Performer.getInstance();
		ArrayList<AbstractTour> tourList = performer.getTourList();
		if ((parsedDataList == null) || (parsedDataList.isEmpty())) {
			throw new WrongDataException("This data file doesn't contain tours");
		}
		for (TemporaryParsedData p : parsedDataList) {
			AbstractTour tour = null;
			switch (p.getTourType().toUpperCase()) {
			case FAMILY: {
				AbstractTourFactory<FamilyTour> abstractFactory = new FamilyTourFactory();
				tour = abstractFactory.createInstance();
				break;
			}
			case GUIDED: {
				AbstractTourFactory<GuidedTour> abstractFactory = new GuidedTourFactory();
				tour = abstractFactory.createInstance();
				break;
			}
			case SHOPPING: {
				AbstractTourFactory<ShoppingTour> abstractFactory = new ShoppingTourFactory();
				tour = abstractFactory.createInstance();
				break;
			}
			case CRUISE: {
				AbstractTourFactory<CruiseTour> abstractFactory = new CruiseTourFactory();
				tour = abstractFactory.createInstance();
				break;
			}
			default:
				LOG.log(Level.ERROR, " This type of tour doesn't exist " + p.getTourType());
			}
			boolean isAccepted = tour != null;
			if (isAccepted) {
				tour.setTourTitle(p.getTourTitle());
				try {
					tour.setSubType(p.getTourSubType());
				} catch (WrongDataException e) {
					isAccepted = false;
					LOG.log(Level.ERROR, e);
				}
				try {
					tour.setStartDate(p.getStart());
				} catch (WrongDataException e) {
					isAccepted = false;
					LOG.log(Level.ERROR, e);
				}
				try {
					tour.setDuration(p.getDuration());
				} catch (WrongDataException e) {
					isAccepted = false;
					LOG.log(Level.ERROR, e);
				}
				try {
					tour.setTransport(p.getTransport());
				} catch (WrongDataException e) {
					isAccepted = false;
					LOG.log(Level.ERROR, e);
				}
				try {
					tour.setMeal(p.getMeal());
				} catch (WrongDataException e) {
					isAccepted = false;
					LOG.log(Level.ERROR, e);
				}
				try {
					tour.setPrice(p.getPrice());
				} catch (WrongDataException e) {
					isAccepted = false;
					LOG.log(Level.ERROR, e);
				}
			}
			if (isAccepted) {
				tourList.add(tour);
			}
		}
		LOG.log(Level.INFO, "There are " + tourList.size() + " tours");
	}

}
