package by.sardyka.tour.creator;

import by.sardyka.tour.entity.CruiseTour;
import by.sardyka.tour.exception.WrongDataException;

public class CruiseTourFactory extends AbstractTourFactory<CruiseTour> {

	@Override
	public CruiseTour createInstance() throws WrongDataException {

		return new CruiseTour();
	}
}
