package by.sardyka.logistic.track;

import by.sardyka.logistic.base.LogisticBase;
import by.sardyka.logistic.base.Terminal;
import by.sardyka.logistic.exception.TerminalException;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Track extends Thread implements Comparable<Track> {
	private int  trackId;
	private String trackName;
	private int space;
	private boolean isUrgent;
	private boolean processed = false;
	private LogisticBase targetBase;
	private static int numProcessed = 0;
	private static final Logger LOG = LogManager.getLogger(Track.class);

	public Track(LogisticBase targetBase, int trackId, String trackName, int space, boolean isUrgent) {
		this.targetBase = targetBase;
		this.trackId = trackId;
		this.trackName = trackName;
		this.space = space;
		this.isUrgent = isUrgent;
	}

	public static int getNumProcessed() {
		return numProcessed;
	}

	public void run() {
		Terminal terminal = null;
		try {
			while (!processed) {
				terminal = targetBase.getTerminal(2);
				processed = terminal.process(space, trackId);
				LOG.log(Level.INFO,
						"\nTrack #" + trackId + " " + trackName + " took terminal #" + terminal.getTerminalId()
								+ ". It was " + (processed ? "" : "NOT ") + "processed for " + (space < 0 ? "" : "un")
								+ "loading " + Math.abs(space) + " cubic meters. It was "
								+ (isUrgent ? "URGENT" : "not urgent"));
				if (processed) {
					numProcessed++;
				} else {
					if (terminal != null) {
						targetBase.returnTerminal(terminal);
						LOG.log(Level.INFO, "\nTrack #" + trackId + " reported: terminal #" + terminal.getTerminalId()
								+ " released");
						terminal = null;
						try {
							TimeUnit.MICROSECONDS.sleep(isUrgent ? 1 : 10000);
						} catch (InterruptedException e) {
							LOG.log(Level.ERROR, e.getMessage());
						}
					}
				}
			}
		} catch (TerminalException e) {
			LOG.log(Level.ERROR, "\nTrack #" + trackId + " lost ->" + e.getMessage());
		} finally {
			if (terminal != null) {
				targetBase.returnTerminal(terminal);
				LOG.log(Level.INFO,
						"\nTrack #" + trackId + " reported: terminal #" + terminal.getTerminalId() + " released");
			}
		}
	}

	@Override
	public int compareTo(Track track) {
		return track.isUrgent ? 1 : -1;
	}
}