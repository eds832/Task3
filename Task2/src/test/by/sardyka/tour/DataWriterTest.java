package test.by.sardyka.tour;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.sardyka.tour.creator.TourCreator;
import by.sardyka.tour.datawriter.DataWriter;
import by.sardyka.tour.entity.AbstractTour;
import by.sardyka.tour.exception.WrongDataException;
import by.sardyka.tour.performer.Performer;
import by.sardyka.tour.temporary.TemporaryParsedData;

@RunWith(Parameterized.class)
public class DataWriterTest {

	private String input;
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
	private String str;
	private File file;
	ArrayList<ArrayList<AbstractTour>> responses;

	public DataWriterTest(String input, String type1, String subType1, String tourTitle1, String startDate1,
			int duration1, String transport1, String meal1, double price1, String type2, String subType2,
			String tourTitle2, String startDate2, int duration2, String transport2, String meal2, double price2,
			String str) {
		super();
		this.input = input;
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
		this.str = str;
	}

	@Parameters
	public static Collection<Object[]> DataWriterTestValue() {
		Object[][] obj = new Object[][] {
				{ "Input is correct", "Guided", "overall",
					"Europe throughout tour Wars.-Dresd.-Paris with 5 nights in hotel for 1", 
					"2018-04-20", 9, "Bus", "None", 799.0,
					"Shopping", "mall", "Italy Milano malls with accommodation in 2 stars hotel per 1 person",
					"2018-05-07" , 5, "Plane", "B", 980.0 ,
					"----------------------------------- START OF RESPONSE --------------------------------------------------\n" +
							"                     ******* There are 2 tours for your request *******\n\n" +
							"1 - Guided tour, subtype: OVERALL, tour title: Europe throughout tour Wars.-Dresd.-Paris with 5 " + 
							"nights in hotel for 1,\n" +
							"start date: 2018-04-20, duration: 9, transport: BUS, meal: NONE, price: 799.0USD\n\n" +
							"2 - Shopping tour, subtype: MALL, tour title: Italy Milano malls with accommodation in 2 stars " +
							"hotel per 1 person,\n" + 
							"start date: 2018-05-07, duration: 5, transport: PLANE, meal: B, price: 980.0USD\n\n" +
							"------------------------------------ END OF RESPONSE --------------------------------------------" + 
							"-------"},
				{ "Input is incorrect", "family", "budget",
						"Egypt 3 stars Hurgada Lazy Dad rum for 3 persons", "2018-04-04", 8, "Plane",
						"All", 1780.0,
						"family", "delux", "Spain 4 stars Costa Brava Casa Cavallo 4 stars suit for 2 p.",
						"2018-04-03", 9, "Plane", "HB", 2720.0,
						"----------------------------------- START OF RESPONSE --------------------------------------------------\n" +
								"                     ******* There are 2 tours for your request *******\n\n" +
								"1 - Family tour, subtype: BUDGET, tour title: Egypt 3 stars Hurgada Lazy Dad rum for 3 persons,\n" +
								"start date: 2018-04-04, duration: 8, transport: PLANE, meal: ALL, price: 1780.0USD\n\n" +
								"2 - Family tour, subtype: DELUX, tour title: Spain 4 stars Costa Brava Casa Cavallo 4 stars suit " +
								"for 2 p.,\n" +
								"start date: 2018-04-03, duration: 9, transport: PLANE, meal: HB, price: 2720.0USD\n\n" +
								"------------------------------------ END OF RESPONSE ---------------------------------------------" +
								"------" } };
		return Arrays.asList(obj);
	}

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void initDataWriter() throws IOException, WrongDataException {
		file = folder.newFile("out.txt");
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
		TourCreator.createTourList(parsedDataList);
		responses = new ArrayList<>();
		responses.add(tours);
	}

	@After
	public void closeTemporaryFolder() {
		Performer instance = Performer.getInstance();
		ArrayList<AbstractTour> tours = instance.getTourList();
		tours.clear();
	}

	@Test
	public void writeStringDataTest() throws FileNotFoundException, NoSuchElementException {
		DataWriter.writeData(input, file.getAbsolutePath());
		Scanner sc = new Scanner(file);
		String s = sc.nextLine();
		sc.close();
		boolean actual = s.equals(input);
		assertTrue("writeData writes a string incorrectly", actual);
	}

	@Test
	public void writeEmptyDataTest() throws FileNotFoundException {
		DataWriter.writeData("", file.getAbsolutePath());
		Scanner sc = new Scanner(file);
		boolean actual = !sc.hasNextLine();
		sc.close();
		assertTrue("writeData writes empty data incorrectly", actual);
	}

	@Test
	public void writeTourDataTest() throws FileNotFoundException, NoSuchElementException {
		DataWriter.writeData(responses, file.getAbsolutePath());
		Scanner sc = new Scanner(file);
		String strActual = sc.nextLine() + "\n" + sc.nextLine() + "\n" + sc.nextLine() + "\n" + sc.nextLine()
			+ "\n" + sc.nextLine() + "\n" + sc.nextLine() + "\n" + sc.nextLine()  + "\n" + sc.nextLine() + "\n" 
				+ sc.nextLine() + "\n" + sc.nextLine();
		sc.close();
		boolean actual = strActual.equals(str);
		assertTrue("writeData writes tour data incorrectly", actual);
	}

}
