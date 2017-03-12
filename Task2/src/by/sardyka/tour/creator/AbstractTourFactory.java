package by.sardyka.tour.creator;

import by.sardyka.tour.entity.AbstractTour;
import by.sardyka.tour.exception.WrongDataException;

public abstract class AbstractTourFactory<T extends AbstractTour> {

	public abstract T createInstance() throws WrongDataException;

	
}

