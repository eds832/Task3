package by.sardyka.tour.creator;

import by.sardyka.tour.entity.ShoppingTour;
import by.sardyka.tour.exception.WrongDataException;

public class ShoppingTourFactory extends AbstractTourFactory<ShoppingTour> {

	@Override
	public ShoppingTour createInstance() throws WrongDataException {

		return new ShoppingTour();
	}
}
