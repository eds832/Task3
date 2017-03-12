package by.sardyka.tour.datawriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.sardyka.tour.entity.AbstractTour;

public class DataWriter {
	private static final Logger LOG = LogManager.getLogger(DataWriter.class);

	public static void writeData(ArrayList<ArrayList<AbstractTour>> responses, String fileName) {
		String str = "";
		Iterator<ArrayList<AbstractTour>> it = responses.iterator();
		while (it.hasNext()) {
			str += "----------------------------------- START OF RESPONSE --------------------------------------------------\n";
			ArrayList<AbstractTour> response = it.next();
			str += "                     ******* There are " + response.size() + " tours for your request *******\n\n";
			for (int i = 0; i < response.size(); i++) {
				str += i + 1 + " -" + response.get(i) + "\n\n";
			}
			str += "------------------------------------ END OF RESPONSE ---------------------------------------------------\n\n";
		}
		writeData(str, fileName);
	}

	public static void writeData(String stringData, String fileName) {
		File output = new File(fileName);
		FileWriter fw = null;
		try {
			fw = new FileWriter(output);
			fw.write(stringData);
		} catch (IOException e) {
			LOG.log(Level.FATAL, "There is a input-output problem");
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					LOG.log(Level.DEBUG, "There is a input-output problem");
				}
			}
		}
	}
}
