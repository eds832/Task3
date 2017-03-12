package by.sardyka.tour.entity;

import by.sardyka.tour.exception.WrongDataException;

public class DefaultTour extends AbstractTour {
	
	private static final String DEFAULT_SUB_TYPE = "DEFAULT";
	private static final String DEFAULT_TOUR_TITLE = "DEFAULT";
	private static final String DEFAULT_DATE = "2030-01-01";
	private static final int DEFAULT_DURATION = 30;
	private static final String DEFAULT_TRANSPORT = "NONE";
	private static final String DEFAULT_MEAL = "NONE";
	private static final double DEFAULT_PRICE = 0.0;

	public DefaultTour() throws WrongDataException {
		setTourTitle(DEFAULT_TOUR_TITLE);
		setStartDate(DEFAULT_DATE);
		setDuration(DEFAULT_DURATION);
		setTransport(DEFAULT_TRANSPORT);
		setMeal(DEFAULT_MEAL);
		setPrice(DEFAULT_PRICE);
	}

	@Override
	public String getSubType() {
		return DEFAULT_SUB_TYPE;
	}

	@Override
	public void setSubType(String sub) throws WrongDataException {
	}

	@Override
	public String toString() {
		return " Default tour, subtype: " + this.getSubType() + super.toString();
	}
}
