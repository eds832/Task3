package by.sardyka.tour.entity;

import by.sardyka.tour.exception.WrongDataException;

public class FamilyTour extends AbstractTour {

	private static final String FAMILY = "FAMILY";
	private FamilyType subType;

	private enum FamilyType {
		BUDGET, DELUX, LUXURY
	}

	public FamilyTour() throws WrongDataException {
		super.setType(FAMILY);
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
			subType = FamilyType.valueOf(sub.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new WrongDataException(" The family type subtype info is incorrect " + sub);
		}
	}

	@Override
	public String toString() {
		return " Family tour, subtype: " + this.getSubType() + super.toString();
	}
}
