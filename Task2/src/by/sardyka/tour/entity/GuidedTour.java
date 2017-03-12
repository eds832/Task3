package by.sardyka.tour.entity;

import by.sardyka.tour.exception.WrongDataException;

public class GuidedTour extends AbstractTour {

	private static final String GUIDED = "GUIDED";
	private GuidedType subType;

	private enum GuidedType {
		CASTLE, PALACE, TEMPLE, LANDSCAPE, OVERALL
	}

	public GuidedTour() throws WrongDataException {
		super.setType(GUIDED);
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
			subType = GuidedType.valueOf(sub.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new WrongDataException(" The guided tour subtype info is incorrect " + sub);
		}
	}

	@Override
	public String toString() {
		return " Guided tour, subtype: " + this.getSubType() + super.toString();
	}
}
