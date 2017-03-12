package by.sardyka.tour.creator;

import by.sardyka.tour.entity.GuidedTour;
import by.sardyka.tour.exception.WrongDataException;

public class GuidedTourFactory extends AbstractTourFactory<GuidedTour> {
	
	@Override
	public GuidedTour createInstance() throws WrongDataException {
		return new GuidedTour();
	}
}
