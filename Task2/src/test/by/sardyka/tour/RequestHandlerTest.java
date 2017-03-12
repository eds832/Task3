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

import by.sardyka.tour.creator.TourCreator;
import by.sardyka.tour.entity.AbstractTour;
import by.sardyka.tour.exception.WrongDataException;
import by.sardyka.tour.performer.Performer;
import by.sardyka.tour.requesthandler.RequestHandler;
import by.sardyka.tour.temporary.TemporaryParsedData;

@RunWith(Parameterized.class)
public class RequestHandlerTest {

	private String[][] mas1;
	private String[][] mas2;

	private String type1;
	private String subType1;
	private String tourTitle1;
	private String startDate1;
	private int duration1;
	private String transport1;
	private String meal1;
	private double price1;

	private String type2;
	private String subType2;
	private String tourTitle2;
	private String startDate2;
	private int duration2;
	private String transport2;
	private String meal2;
	private double price2;

	private String type3;
	private String subType3;
	private String tourTitle3;
	private String startDate3;
	private int duration3;
	private String transport3;
	private String meal3;
	private double price3;

	private ArrayList<String[][]> ar;

	public RequestHandlerTest(String[][] mas1, String[][] mas2, String type1, String subType1, String tourTitle1,
			String startDate1, int duration1, String transport1, String meal1, double price1, String type2,
			String subType2, String tourTitle2, String startDate2, int duration2, String transport2, String meal2,
			double price2, String type3, String subType3, String tourTitle3, String startDate3, int duration3,
			String transport3, String meal3, double price3) {
		super();
		this.mas1 = mas1;
		this.mas2 = mas2;
		this.type1 = type1;
		this.subType1 = subType1;
		this.tourTitle1 = tourTitle1;
		this.startDate1 = startDate1;
		this.duration1 = duration1;
		this.transport1 = transport1;
		this.meal1 = meal1;
		this.price1 = price1;
		this.type2 = type2;
		this.subType2 = subType2;
		this.tourTitle2 = tourTitle2;
		this.startDate2 = startDate2;
		this.duration2 = duration2;
		this.transport2 = transport2;
		this.meal2 = meal2;
		this.price2 = price2;
		this.type3 = type3;
		this.subType3 = subType3;
		this.tourTitle3 = tourTitle3;
		this.startDate3 = startDate3;
		this.duration3 = duration3;
		this.transport3 = transport3;
		this.meal3 = meal3;
		this.price3 = price3;
	}

	@Parameters
	public static Collection<Object[]> RequestHandlerTestValue() {
		Object[][] obj = new Object[][] { {
				new String[][] { { "Type", "Family" }, { "Minimum duration", "3" }, { "Maximum price", "3000" },
						{ "Earliest date", "2018-03-07" } },
				new String[][] { { "Minimum duration", "31" }, { "Maximum price", "1100" } },
				"family", "budget", "Egypt 3 stars Hurgada Lazy Dad rum for 3 persons", "2018-04-04", 8, "Plane",
				"All", 1780.0,
				"family", "delux", "Spain 4 stars Costa Brava Casa Cavallo 4 stars suit for 2 p.", "2018-04-03",
				9, "Plane", "HB", 2720.0,
				"Guided", "Castle", "UK Castles of England 2 stars accommodation for 1 person", "2017-05-09", 7,
				"Plane", "B", 2070.0 }, {
						new String[][] { { "Earliest date", "2018-03-07" }, { "Maximum price", "1100" },
								{ "TYPE", "guided" }, { "Minimum duration", "4" } },
						new String[][] { { "Type", "shopping" }, { "Earliest date", "2018-03-37" } },
						"Guided", "Temple", "Israel Christian's Lord's Temples with accommodation for 1",
						"2018-04-22", 4, "Plane", "B", 670,
						"Guided", "overall", "Europe throughout tour Wars.-Dresd.-Paris with 5 nights in hotel for 1",
						"2018-04-20", 9, "Bus", "None", 799.0,
						"Shopping", "mall", "Italy Milano malls with accommodation in 2 stars hotel per 1 person",
						"2018-05-07" , 5, "Plane", "B", 980.0 } };
		return Arrays.asList(obj);
	}

	@Before
	public void initRequestHandler() throws WrongDataException {
		Performer instance = Performer.getInstance();
		ArrayList<AbstractTour> tours = instance.getTourList();
		tours.clear();
		ArrayList<TemporaryParsedData> parsedDataList = new ArrayList<>();
		TemporaryParsedData d1 = new TemporaryParsedData();
		d1.setTourType(type1);
		d1.setTourSubType(subType1);
		d1.setTourTitle(tourTitle1);
		d1.setStart(startDate1);
		d1.setDuration(duration1);
		d1.setTransport(transport1);
		d1.setMeal(meal1);
		d1.setPrice(price1);
		parsedDataList.add(d1);
		TemporaryParsedData d2 = new TemporaryParsedData();
		d2.setTourType(type2);
		d2.setTourSubType(subType2);
		d2.setTourTitle(tourTitle2);
		d2.setStart(startDate2);
		d2.setDuration(duration2);
		d2.setTransport(transport2);
		d2.setMeal(meal2);
		d2.setPrice(price2);
		parsedDataList.add(d2);
		TemporaryParsedData d3 = new TemporaryParsedData();
		d3.setTourType(type3);
		d3.setTourSubType(subType3);
		d3.setTourTitle(tourTitle3);
		d3.setStart(startDate3);
		d3.setDuration(duration3);
		d3.setTransport(transport3);
		d3.setMeal(meal3);
		d3.setPrice(price3);
		parsedDataList.add(d3);
		TourCreator.createTourList(parsedDataList);
		ar = new ArrayList<>();
		ar.add(mas1);
		ar.add(mas2);
	}

	@After
	public void closeTemporaryFolder() {
		Performer instance = Performer.getInstance();
		ArrayList<AbstractTour> tours = instance.getTourList();
		tours.clear();
	}

	@Test
	public void handleRequestTest() throws WrongDataException, IndexOutOfBoundsException, NullPointerException {
		ArrayList<ArrayList<AbstractTour>> list = RequestHandler.handleRequest(ar);
		boolean b1 = list.size() == 1;
		boolean b2 = list.get(0).get(0).getType().equals(type2.toUpperCase());
		boolean b3 = list.get(0).get(0).getSubType().equals(subType2.toUpperCase());
		boolean b4 = list.get(0).get(0).getTourTitle().equals(tourTitle2);
		boolean b5 = list.get(0).get(0).getStartDate().toString().equals(startDate2);
		boolean b6 = list.get(0).get(0).getDuration() == duration2;
		boolean b7 = list.get(0).get(0).getTransport().equals(transport2.toUpperCase());
		boolean b8 = list.get(0).get(0).getMeal().equals(meal2.toUpperCase());
		boolean b9 = list.get(0).get(0).getPrice() == price2;
		boolean b10 = list.get(0).get(1).getType().equals(type1.toUpperCase());
		boolean b11 = list.get(0).get(1).getSubType().equals(subType1.toUpperCase());
		boolean b12 = list.get(0).get(1).getTourTitle().equals(tourTitle1);
		boolean b13 = list.get(0).get(1).getStartDate().toString().equals(startDate1);
		boolean b14 = list.get(0).get(1).getDuration() == duration1;
		boolean b15 = list.get(0).get(1).getTransport().equals(transport1.toUpperCase());
		boolean b16 = list.get(0).get(1).getMeal().equals(meal1.toUpperCase());
		boolean b17 = list.get(0).get(1).getPrice() == price1;
		boolean b18 = list.get(0).size() == 2;
		boolean actual = b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9 && b10 && b11 && b12 && b13 && b14 && b15
				&& b16 && b17 && b18;
		assertTrue("handleRequest works incorrectly", actual);
	}

	@Test
	public void handleRequestException1Test() {
		ArrayList<String[][]> parsedRequest = null;
		try {
			RequestHandler.handleRequest(parsedRequest);
			fail("handleRequestExeption1Test for parsedRequest should have thrown a WrongDataException");
		} catch (WrongDataException e) {
			assertEquals("There aren't requests", e.getMessage());
		}
	}

	@Test
	public void handleRequestException2Test() {
		ArrayList<String[][]> parsedRequest = new ArrayList<>();
		try {
			RequestHandler.handleRequest(parsedRequest);
			fail("handleRequestExeption2Test for parsedRequest should have thrown a WrongDataException");
		} catch (WrongDataException e) {
			assertEquals("There aren't requests", e.getMessage());
		}
	}
}
