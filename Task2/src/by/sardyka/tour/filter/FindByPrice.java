package by.sardyka.tour.filter;

import java.util.ArrayList;
import java.util.Collections;

import by.sardyka.tour.comparator.TourPriceComparator;
import by.sardyka.tour.entity.AbstractTour;

public class FindByPrice extends AbstractFinder{

	@Override
	public ArrayList<AbstractTour> handleRequest(AbstractTour tour, ArrayList<AbstractTour> tours) {
		int i = 0;
		while (i < tours.size()) {
			AbstractTour t = tours.get(i);
			if (t.getPrice() > tour.getPrice()) {
				tours.remove(i);
				i--;
			}
			i++;
		}
		Collections.sort(tours, new TourPriceComparator());
		return tours;
	}

}
