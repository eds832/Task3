package by.sardyka.tour.filter;

import java.util.ArrayList;
import java.util.Collections;

import by.sardyka.tour.comparator.TourStartComparator;
import by.sardyka.tour.entity.AbstractTour;

public class FindByStart extends AbstractFinder {

	@Override
	public ArrayList<AbstractTour> handleRequest(AbstractTour tour, ArrayList<AbstractTour> tours) {
		int i = 0;
		while (i < tours.size()) {
			AbstractTour t = tours.get(i);
			if (t.getStartDate().isBefore(tour.getStartDate())) {
				tours.remove(i);
				i--;
			} 
			i++;
		}
		Collections.sort(tours, new TourStartComparator());
		return tours;
	}

}
