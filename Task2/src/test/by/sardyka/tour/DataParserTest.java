package test.by.sardyka.tour;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import by.sardyka.tour.exception.WrongDataException;
import by.sardyka.tour.temporary.TemporaryParsedData;
import by.sardyka.tour.dataparser.DataParser;

@RunWith(Parameterized.class)
public class DataParserTest {
	
	private String str1;
	private String str2;
	private String str3;
	private String tourType;
	private String tourSubType;
	private String tourTitle;
	private String start;
	private int duration;
	private String transport;
	private String meal;
	private double price;
	private ArrayList<String> ar;

	public DataParserTest(String str1, String str2, String str3, String tourType, String tourSubType, String tourTitle,
			String start, int duration, String transport, String meal, double price) {
		super();
		this.str1 = str1;
		this.str2 = str2;
		this.str3 = str3;
		this.tourType = tourType;
		this.tourSubType = tourSubType;
		this.tourTitle = tourTitle;
		this.start = start;
		this.duration = duration;
		this.transport = transport;
		this.meal = meal;
		this.price = price;
	}

	@Parameters
	public static Collection<Object[]> DataParserTestValue() {
		Object[][] obj = new Object[][] {
				{ "Guided, Palace, Russia Palaces of St. Petersburg with accommodation for 1 person, 2017-05-11, 5, Train, None, 340",
					"Guided, overall, Europe throughout tour Wars.-Dresd.-Paris with 5 nights in hotel for 1, 2017-04-20, 9, Bus, 799",
					"Guided, landscape, Europe sea views along Med sea shore with accommodation for 1 person, 2017-06-01, 12, Bus, b, $1590",
					"Guided", "Palace", "Russia Palaces of St. Petersburg with accommodation for 1 person", "2017-05-11", 5, "Train",
							"None", 340.0 },
				{ "Shopping, Overall, Turkey Istanbul with accommodation in 2 stars hotel per 1 person, 2017-04-27, 4 days, Plane, B, 650",
							"Guided, Temple, Israel Christian's Lord's Temples with accommodation for 1 person, 2017-04-22, 4, Plane, B, 670",
							"cruise, river, Egypt Nile tour with Aswan and Pyramids for 1, 2017-05-07, seven days, Plane, All, 1230USD",
							"Guided", "Temple", "Israel Christian's Lord's Temples with accommodation for 1 person", "2017-04-22", 4,
							"Plane", "B", 670.0 } };
		return Arrays.asList(obj);
	}

	@Before
	public void initDataParser() {
		ar = new ArrayList<>();
		ar.add(str1);
		ar.add(str2);
		ar.add(str3);
	}

	@Test
	public void parseDataTest() throws WrongDataException, IndexOutOfBoundsException, NullPointerException {
		ArrayList<TemporaryParsedData> list = DataParser.parseData(ar);
		boolean b1 = list.size() == 1;
		boolean b2 = list.get(0).getTourType().equals(tourType);
		boolean b3 = list.get(0).getTourSubType().equals(tourSubType);
		boolean b4 = list.get(0).getTourTitle().equals(tourTitle);
		boolean b5 = list.get(0).getStart().equals(start);
		boolean b6 = list.get(0).getDuration() == duration;
		boolean b7 = list.get(0).getTransport().equals(transport);
		boolean b8 = list.get(0).getMeal().equals(meal);
		boolean b9 = list.get(0).getPrice() == price;
		boolean actual = b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9;
		assertTrue("parseData works incorrectly", actual);
	}

	@Test
	public void parseDataException1Test() {
		ArrayList<String> strList = null;
		try {
			DataParser.parseData(strList);
			fail("parseDataExeptionTest for strList should have thrown a WrongDataException");
		} catch (WrongDataException e) {
			assertEquals(" This data file doesn't exist or empty", e.getMessage());
		}
	}

	@Test
	public void parseDataException2Test() {
		ArrayList<String> strList = new ArrayList<>();
		try {
			DataParser.parseData(strList);
			fail("parseDataExeptionTest for strList should have thrown a WrongDataException");
		} catch (WrongDataException e) {
			assertEquals(" This data file doesn't exist or empty", e.getMessage());
		}
	}
}
