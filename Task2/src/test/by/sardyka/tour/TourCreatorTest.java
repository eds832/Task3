package test.by.sardyka.tour;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.sardyka.tour.exception.WrongDataException;
import by.sardyka.tour.performer.Performer;
import by.sardyka.tour.temporary.TemporaryParsedData;
import by.sardyka.tour.creator.TourCreator;
import by.sardyka.tour.entity.AbstractTour;

@RunWith(Parameterized.class)
public class TourCreatorTest {
	private String tourType1;
	private String tourSubType1;
	private String tourTitle1;
	private String start1;
	private int duration1;
	private String transport1;
	private String meal1;
	private double price1;

	private String tourType2;
	private String tourSubType2;
	private String tourTitle2;
	private String start2;
	private int duration2;
	private String transport2;
	private String meal2;
	private double price2;
	
	private String type;
	private String subType;
	private String tourTitle;
	private String startDate;
	private int duration;
	private String transport;
	private String meal;
	private double price;
	
	private ArrayList<AbstractTour> tours;
	private ArrayList<TemporaryParsedData> parsedDataList;

	
	public TourCreatorTest(String tourType1, String tourSubType1, String tourTitle1, String start1, int duration1,
			String transport1, String meal1, double price1, String tourType2, String tourSubType2, String tourTitle2,
			String start2, int duration2, String transport2, String meal2, double price2, String type, String subType,
			String tourTitle, String startDate, int duration, String transport, String meal, double price) {
		super();
		this.tourType1 = tourType1;
		this.tourSubType1 = tourSubType1;
		this.tourTitle1 = tourTitle1;
		this.start1 = start1;
		this.duration1 = duration1;
		this.transport1 = transport1;
		this.meal1 = meal1;
		this.price1 = price1;
		this.tourType2 = tourType2;
		this.tourSubType2 = tourSubType2;
		this.tourTitle2 = tourTitle2;
		this.start2 = start2;
		this.duration2 = duration2;
		this.transport2 = transport2;
		this.meal2 = meal2;
		this.price2 = price2;
		this.type = type;
		this.subType = subType;
		this.tourTitle = tourTitle;
		this.startDate = startDate;
		this.duration = duration;
		this.transport = transport;
		this.meal = meal;
		this.price = price;
	}

	@Parameters
	public static Collection<Object[]> TourCreatorTestValue() {
		Object[][] obj = new Object[][] {
				{"Shopping", "market", "Ukraine Hmelnitsky market per 1", "2018-07-29", 2, "bus", "none", 55.0,
					"Guided", "sea", "Europe sea views along Med sea shore with accommodation for 1 person",
					"2017-01-03", 31, "Bus", "b", 20000.01,
					"SHOPPING", "MARKET", "Ukraine Hmelnitsky market per 1", "2018-07-29", 2, "BUS", "NONE", 55.0 },
				{"family", "delux", "Spain 4 stars Costa Brava Casa Cavallo 4 stars suit for 2 persons", "2018-04-28", 9,
						"Plane", "HB", 2720.0, 
						"Shopping", "market", "Minsk markets tour for free", "2017-05-32", 30, "play", "fed up", 1,
						"FAMILY", "DELUX", "Spain 4 stars Costa Brava Casa Cavallo 4 stars suit for 2 persons",
						"2018-04-28", 9, "PLANE", "HB", 2720.0 }};
		return Arrays.asList(obj);
	}

	@Before
	public void initTourCreator() {
		Performer instance = Performer.getInstance();
		tours = instance.getTourList();
		parsedDataList = new ArrayList<>();
		TemporaryParsedData d1 = new TemporaryParsedData();
		d1.setTourType(tourType1);
		d1.setTourSubType(tourSubType1);
		d1.setTourTitle(tourTitle1);
		d1.setStart(start1);
		d1.setDuration(duration1);
		d1.setTransport(transport1);
		d1.setMeal(meal1);
		d1.setPrice(price1);
		parsedDataList.add(d1);
		TemporaryParsedData d2 = new TemporaryParsedData();
		d2.setTourType(tourType2);
		d2.setTourSubType(tourSubType2);
		d2.setTourTitle(tourTitle2);
		d2.setStart(start2);
		d2.setDuration(duration2);
		d2.setTransport(transport2);
		d2.setMeal(meal2);
		d2.setPrice(price2);
		parsedDataList.add(d2);
		tours.clear();
	}

	@After
	public void closeTourCreator() {
		tours.clear();
	}

	@Test
	public void createTourListTest() throws WrongDataException, IndexOutOfBoundsException, NullPointerException {
		TourCreator.createTourList(parsedDataList);
		boolean b1 = tours.size() == 1;
		boolean b2 = tours.get(0).getType().equals(type);
		boolean b3 = tours.get(0).getSubType().equals(subType);
		boolean b4 = tours.get(0).getTourTitle().equals(tourTitle);
		boolean b5 = tours.get(0).getStartDate().toString().equals(startDate);
		boolean b6 = tours.get(0).getDuration() == duration;
		boolean b7 = tours.get(0).getTransport().equals(transport);
		boolean b8 = tours.get(0).getMeal().equals(meal);
		boolean b9 = tours.get(0).getPrice() == price;
		boolean actual = b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9;
		assertTrue("createTourList works incorrectly", actual);
	}

	@Test
	public void createTourListException1Test() {
		ArrayList<TemporaryParsedData> pars = null;
		try {
			TourCreator.createTourList(pars);
			fail("createTourListException1Test for pars should have thrown a WrongDataException");
		} catch (WrongDataException e) {
			assertEquals("This data file doesn't contain tours", e.getMessage());
		}
	}

	@Test
	public void createTourListException2Test() {
		ArrayList<TemporaryParsedData> pars = new ArrayList<>();
		try {
			TourCreator.createTourList(pars);
			fail("createTourListException2Test for pars should have thrown a WrongDataException");
		} catch (WrongDataException e) {
			assertEquals("This data file doesn't contain tours", e.getMessage());
		}
	}

}
