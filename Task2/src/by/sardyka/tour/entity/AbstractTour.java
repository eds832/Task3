package by.sardyka.tour.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import by.sardyka.tour.exception.WrongDataException;

public abstract class AbstractTour {

	private TourType type;
	private String tourTitle;
	private LocalDate startDate;
	private int duration;
	private Transport transport;
	private Meal meal;
	private double price;

	public AbstractTour() {
	}

	public abstract String getSubType();

	public abstract void setSubType(String sub) throws WrongDataException;

	public final String getType() {
		return type.name();
	}

	public void setType(String type) throws WrongDataException {
		try {
			this.type = TourType.valueOf(type.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new WrongDataException(" The tour type info is incorrect " + type);
		}
	}

	public final String getTourTitle() {
		return tourTitle;
	}

	public final void setTourTitle(String tourTitle) {
		this.tourTitle = tourTitle;
	}

	public final LocalDate getStartDate() {
		return startDate;
	}

	public final void setStartDate(String start) throws WrongDataException {
		LocalDate data = null;
		try {
			data = LocalDate.parse(start);
		} catch (DateTimeParseException e) {
			throw new WrongDataException(" The data is incorrect " + start);
		}
		if (data != null) {
			if (!data.isBefore(LocalDate.now())) {
				startDate = data;
			} else {
			throw new WrongDataException(" The data is old " + data);
			}
		}
	}

	public final int getDuration() {
		return duration;
	}

	public final void setDuration(int duration) throws WrongDataException {
		if (duration > 0 && duration < 31) {
			this.duration = duration;
		} else {
			throw new WrongDataException(" The duration is wrong " + duration);
		}
	}

	public final String getTransport() {
		return transport.name();
	}

	public final void setTransport(String transp) throws WrongDataException {
		try {
			transport = Transport.valueOf(transp.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new WrongDataException(" The transport info is incorrect " + transp);
		}
	}

	public final String getMeal() {
		return meal.name();
	}

	public final void setMeal(String m) throws WrongDataException {
		try {
			meal = Meal.valueOf(m.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new WrongDataException(" The meal info is incorrect " + m);
		}
	}

	public final double getPrice() {
		return price;
	}

	public final void setPrice(double price) throws WrongDataException {
		if (price >= 0 && price <= 20000) {
			this.price = price;
		} else {
			throw new WrongDataException(" Wrong amount of price " + price);
		}
	}

	@Override
	public String toString() {
		return ", tour title: " + tourTitle + ",\nstart date: " + startDate + ", duration: " + duration
				+ ", transport: " + this.getTransport() + ", meal: " + this.getMeal() + ", price: " + price + "USD";
	}

}
