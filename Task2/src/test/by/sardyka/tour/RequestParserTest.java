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
import by.sardyka.tour.requestparser.RequestParser;

@RunWith(Parameterized.class)
public class RequestParserTest {

	private String str1;
	private String str2;
	private String str3;
	private String[][] mas1;
	private String[][] mas2;
	private ArrayList<String> ar;

	public RequestParserTest(String str1, String str2, String str3, String[][] mas1, String[][] mas2) {
		super();
		this.str1 = str1;
		this.str2 = str2;
		this.str3 = str3;
		this.mas1 = mas1;
		this.mas2 = mas2;
	}

	@Parameters
	public static Collection<Object[]> RequestParserTestValue() {
		Object[][] obj = new Object[][] {
				{ "Type: Family, Minimum duration: 3, Maximum price: 1000, Earliest data: 2017-03-07",
						"Maximum price: 1100, Minimum duration: 1, Type:", "Minimum duration: 5, Maximum price: 1100",
						new String[][] { { "Type", "Family" }, { "Minimum duration", "3" }, { "Maximum price", "1000" },
								{ "Earliest data", "2017-03-07" } },
						new String[][] { { "Minimum duration", "5" }, { "Maximum price", "1100" } } },
				{ "Earliest data: 2017-03-07, Maximum price: 1100, Minimum duration: 5, TYPE: guided",
						"Type: shopping, Earliest data: 2017-03-37",
						"Earliest data: 2017-03-17, Type: cruise - subtype: sea",
						new String[][] { { "Earliest data", "2017-03-07" }, { "Maximum price", "1100" },
								{ "Minimum duration", "5" }, { "TYPE", "guided" } },
						new String[][] { { "Type", "shopping" }, { "Earliest data", "2017-03-37" } } } };
		return Arrays.asList(obj);
	}

	@Before
	public void initRequestParser() {
		ar = new ArrayList<>();
		ar.add(str1);
		ar.add(str2);
		ar.add(str3);
	}

	@Test
	public void parseRequestTest() throws WrongDataException, IndexOutOfBoundsException, NullPointerException {
		ArrayList<String[][]> list = RequestParser.parseRequest(ar);
		boolean b1 = list.size() == 2;
		boolean b2 = list.get(0)[0][0].equals(mas1[0][0]);
		boolean b3 = list.get(0)[0][1].equals(mas1[0][1]);
		boolean b4 = list.get(0)[1][0].equals(mas1[1][0]);
		boolean b5 = list.get(0)[1][1].equals(mas1[1][1]);
		boolean b6 = list.get(0)[2][0].equals(mas1[2][0]);
		boolean b7 = list.get(0)[2][1].equals(mas1[2][1]);
		boolean b8 = list.get(0)[3][0].equals(mas1[3][0]);
		boolean b9 = list.get(0)[3][1].equals(mas1[3][1]);
		boolean b10 = list.get(1)[0][0].equals(mas2[0][0]);
		boolean b11 = list.get(1)[0][1].equals(mas2[0][1]);
		boolean b12 = list.get(1)[1][0].equals(mas2[1][0]);
		boolean b13 = list.get(1)[1][1].equals(mas2[1][1]);
		boolean actual = b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9 && b10 && b11 && b12 && b13;
		assertTrue("parseRequest works incorrectly", actual);
	}

	@Test
	public void parseDataException1Test() {
		ArrayList<String> strList = null;
		try {
			RequestParser.parseRequest(strList);
			fail("parseDataExeptionTest for strList should have thrown a WrongDataException");
		} catch (WrongDataException e) {
			assertEquals(" This request file doesn't exist or empty ", e.getMessage());
		}
	}

	@Test
	public void parseDataException2Test() {
		ArrayList<String> strList = new ArrayList<>();
		try {
			RequestParser.parseRequest(strList);
			fail("parseDataExeptionTest for strList should have thrown a WrongDataException");
		} catch (WrongDataException e) {
			assertEquals(" This request file doesn't exist or empty ", e.getMessage());
		}
	}
}
