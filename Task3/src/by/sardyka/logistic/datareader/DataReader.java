package by.sardyka.logistic.datareader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class DataReader {
	private static final Logger LOG = LogManager.getLogger(DataReader.class);

	public static ArrayList<String> readData(String source) {
		ArrayList<String> list = new ArrayList<String>();
		try (Scanner sc = new Scanner(new File(source))) {
			while (sc.hasNextLine()) {
				list.add(sc.nextLine());
			}
		} catch (IOException e) {
			LOG.log(Level.FATAL, "\nThere is a input-output problem with " + source);
			throw new RuntimeException("\nThere is a input-output problem with " + source);
		}
		LOG.log(Level.INFO, "\nThere are " + list.size() + " strings in this file");
		return list;
	}

}
