package by.sardyka.tour.comparator;

import java.util.Comparator;

import by.sardyka.tour.entity.AbstractTour;

public class TourStartComparator implements Comparator<AbstractTour> {

	@Override
	public int compare(AbstractTour arg0, AbstractTour arg1) {
		if (arg0.getStartDate().isAfter(arg1.getStartDate())) {
			return 1;
		}
		if (arg0.getStartDate().isBefore(arg1.getStartDate())) {
			return -1;
		}
		return 0;
	}

}
