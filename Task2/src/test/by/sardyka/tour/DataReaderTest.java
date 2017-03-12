package test.by.sardyka.tour;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.sardyka.tour.datareader.DataReader;

@RunWith(Parameterized.class)
public class DataReaderTest {
	private String input;
	private static File file1;
	private static File file2;
	private static File file3;

	public DataReaderTest(String input) {
		this.input = input;
	}

	@Parameters
	public static Collection<Object[]> DataReaderTestValue() {
		Object[][] obj = new Object[][] { { "Family, budget, Egypt 3 stars Hurgada Lazy Dad rum"
				  + " for 3 persons, 2017-04-04, 8, Plane, All, 1780\nfamily, delux, Spain 4"
				  + " stars Costa Brava Casa Cavallo 4 stars suit for 2 persons, 2017-04-28, 9,"
				  + " Plane, HB, 2720\nFamily, Delux,  Portugal Riva Comrad shelter for 10 "
				  + "persons, 2017-02-02, 30, none, None, 100" },
				{ "Family,  Luxury,    Italy 5 stars Corsica Rich Brothers bungalow for 4 persons,"
						+ "  2017-05-01, 10, Plane, HB, 6950\nfamily, Luxury, Monaco 6 stars Prince"
						+ " Palace for 20 person, 2017-07-07, 5, Plane, all, 2222000\nGuided,"
						+ " overal, EU for free for everyone, 2017-05-01, 30, None, None, 0" } };
		return Arrays.asList(obj);
	}

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void initDataReader() throws IOException {
		file1 = folder.newFile("inp.txt");
		FileWriter fw = new FileWriter(file1);
		fw.write(input);
		fw.flush();
		fw.close();
		file2 = folder.newFile("empty.txt");
		file3 = folder.newFile("del.txt");
	}

	@Test
	public void readDataTest()  throws IndexOutOfBoundsException, NullPointerException{
		ArrayList<String> list = DataReader.readData(file1.getAbsolutePath());
		String s = list.get(0) + "\n" + list.get(1) + "\n" + list.get(2);
		boolean actual = s.equals(input);
		assertTrue("readData reads incorrectly", actual);
	}

	@Test
	public void readEmptyDataTest() throws NullPointerException{
		ArrayList<String> list = DataReader.readData(file2.getAbsolutePath());
		boolean actual = list.isEmpty();
		assertTrue("readData reads empty file incorrectly", actual);
	}
	
	@Test( expected = RuntimeException.class )
	public void readNotExistingDataTest() throws RuntimeException {
		String fileName = file3.getAbsolutePath();
		file3.delete();
		Object expected = null;
		Object actual = DataReader.readData(fileName);
		assertEquals("For deleted file there aren't RuntimException", expected, actual);
	}
}
