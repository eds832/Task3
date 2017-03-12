package by.sardyka.tour.temporary;

public class TemporaryParsedData {
	
	private String tourType;
	private String tourSubType;
	private String tourTitle;
	private String start;
	private int duration;
	private String transport;
	private String meal;
	private double price;

	public TemporaryParsedData() {
	}

	public String getTourType() {
		return tourType;
	}

	public void setTourType(String tourType) {
		this.tourType = tourType;
	}

	public String getTourSubType() {
		return tourSubType;
	}

	public void setTourSubType(String tourSubType) {
		this.tourSubType = tourSubType;
	}

	public String getTourTitle() {
		return tourTitle;
	}

	public void setTourTitle(String tourTitle) {
		this.tourTitle = tourTitle;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
