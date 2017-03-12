package by.sardyka.tour.filter;

import java.util.ArrayList;

import by.sardyka.tour.entity.AbstractTour;

public class FindByType extends AbstractFinder {

	@Override
	public ArrayList<AbstractTour> handleRequest(AbstractTour tour, ArrayList<AbstractTour> tours) {
		int i = 0;
		while (i < tours.size()) {
			AbstractTour t = tours.get(i);
			if (!t.getType().equals(tour.getType())) {
				tours.remove(i);
				i--;
			}
			i++;
		}
		return tours;
	}
}
