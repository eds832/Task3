package by.sardyka.tour.filter;

import java.util.ArrayList;

import by.sardyka.tour.entity.AbstractTour;

public abstract class AbstractFinder {
	
	protected AbstractFinder finder = DefaultHandleRequest.getHandleRequest();


	public AbstractFinder() {
	}

	public void setFinder(AbstractFinder finder) {
		this.finder = finder;
	}

	abstract public ArrayList<AbstractTour> handleRequest(AbstractTour tour, ArrayList<AbstractTour> tours);

	public void chain(AbstractTour tour, ArrayList<AbstractTour> tours) {		
			handleRequest(tour, tours);
			finder.chain(tour, tours);
	}

	private static class DefaultHandleRequest extends AbstractFinder {
		private static DefaultHandleRequest handler = new DefaultHandleRequest();

		private DefaultHandleRequest() {
		}

		public static DefaultHandleRequest getHandleRequest() {
			return handler;
		}

		@Override
		public void chain(AbstractTour tour, ArrayList<AbstractTour> tours) {
		}

		@Override
		public ArrayList<AbstractTour> handleRequest(AbstractTour tour, ArrayList<AbstractTour> tours) {
			return tours;
		}
	}
}
