package by.sardyka.tour.entity;

import by.sardyka.tour.exception.WrongDataException;

public class ShoppingTour extends AbstractTour {

	private static final String SHOPPING = "SHOPPING";
	private ShoppingType subType;

	private enum ShoppingType {
		MALL, MARKET, OVERALL
	}
	
	public ShoppingTour() throws WrongDataException {
		super.setType(SHOPPING);
	}

	@Override
	public void setType(String type) {
	}
	
	@Override
	public String getSubType() {
		return subType.name();
	}

	@Override
	public void setSubType(String sub) throws WrongDataException {
		try {
			subType = ShoppingType.valueOf(sub.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new WrongDataException(" The shopping tour subtype info is incorrect " + sub);
		}
	}

	@Override
	public String toString() {
		return " Shopping tour, subtype: " + this.getSubType() + super.toString();
	}

}
