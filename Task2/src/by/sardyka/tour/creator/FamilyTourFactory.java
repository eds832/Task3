package by.sardyka.tour.creator;

import by.sardyka.tour.entity.FamilyTour;
import by.sardyka.tour.exception.WrongDataException;

public class FamilyTourFactory extends AbstractTourFactory<FamilyTour> {

	@Override
	public FamilyTour createInstance() throws WrongDataException {

		return new FamilyTour();
	}
}
