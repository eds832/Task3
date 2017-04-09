package by.sardyka.logistic.base;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Terminal {
	private int terminalId;
	private static final Logger  LOG = LogManager.getLogger(Terminal.class);

	public Terminal(int terminalId) {
		super();
		this.terminalId = terminalId;
	}

	public int getTerminalId() {
		return terminalId;
	}

	public boolean process(int space, int trackId) {
		boolean result = false;
		try {
			if (LogisticBase.getInstance().changeAvailableSpace(space, trackId)) {
				TimeUnit.MILLISECONDS.sleep(1 + Math.abs(space)/10 + new Random().nextInt(10));
				result = true;
			}
		} catch (InterruptedException e) {
			LOG.log(Level.ERROR, e.getMessage());
		}
		return result;
	}

}
