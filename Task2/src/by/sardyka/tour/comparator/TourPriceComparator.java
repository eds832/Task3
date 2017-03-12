package by.sardyka.tour.comparator;

import java.util.Comparator;

import by.sardyka.tour.entity.AbstractTour;

public class TourPriceComparator implements Comparator<AbstractTour> {

	@Override
	public int compare(AbstractTour arg0, AbstractTour arg1) {
		if (arg0.getPrice() - arg1.getPrice() > 0) {
			return 1;
		}
		if (arg0.getPrice() - arg1.getPrice() < 0) {
			return -1;
		}
		return 0;
	}

}
