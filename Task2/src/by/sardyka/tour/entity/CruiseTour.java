package by.sardyka.tour.entity;

import by.sardyka.tour.exception.WrongDataException;

public class CruiseTour extends AbstractTour {

	private static final String CRUISE = "CRUISE";
	private CruiseType subType;

	private enum CruiseType {
		RIVER, SEA
	}

	public CruiseTour() throws WrongDataException {
		super.setType(CRUISE);
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
			subType = CruiseType.valueOf(sub.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new WrongDataException(" The cruise tour subtype info is incorrect " + sub);
		}
	}

	@Override
	public String toString() {
		return " Cruise tour, subtype: " + this.getSubType() + super.toString();
	}

}
