package by.sardyka.logistic.base;

import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

import by.sardyka.logistic.base.Terminal;
import by.sardyka.logistic.exception.TerminalException;

public class LogisticBase {

	private static final Logger LOG = LogManager.getLogger(LogisticBase.class);
	private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
	private static LogisticBase INSTANCE = null;
	private static final int NUM_TERMINAL = 5;
	private static final int WAREHOUSE_SPACE = 1_000;
	private static AtomicInteger availableSpace = new AtomicInteger(WAREHOUSE_SPACE);
	private final Semaphore semaphore = new Semaphore(NUM_TERMINAL, true);
	private static ReentrantLock lockSpace = new ReentrantLock();
	private static ReentrantLock lockInstance = new ReentrantLock();
	private static ReentrantLock lockTerminal = new ReentrantLock();
	private static ReentrantLock lockReturnTerminal = new ReentrantLock();
	private final Queue<Terminal> terminalQueue = new LinkedList<Terminal>();
	private static int  numberOfChange = 0;

	private LogisticBase() {
		for (int i = 0; i < NUM_TERMINAL; i++) {
			terminalQueue.add(new Terminal(i));
		}
		LOG.log(Level.INFO, "\nThe Logistic base was created with warehouse space of " + WAREHOUSE_SPACE
				+ " cubic meters and " + NUM_TERMINAL + " terminals");
	}

	public static LogisticBase getInstance() {
		if (!instanceCreated.get()) {
			lockInstance.lock();
			try {
				if (INSTANCE == null) {
					INSTANCE = new LogisticBase();
					instanceCreated.set(true);
				}
			} finally {
				lockInstance.unlock();
			}
		}
		return INSTANCE;
	}

	public boolean changeAvailableSpace(int space, int trackId) {
		lockSpace.lock();
		boolean result = false;
		try {
			if (availableSpace.intValue() >= space && availableSpace.intValue() - space <= WAREHOUSE_SPACE) {
				availableSpace.addAndGet(-space);
				result = true;
				LOG.log(Level.INFO, "\nChange number: #" + (++numberOfChange) + " Available space: " + availableSpace
						+ " Changed by Track: #" + trackId);
			}
		} finally {
			lockSpace.unlock();
		}
		return result;
	}

	public Terminal getTerminal(long maxWait) throws TerminalException {
		lockTerminal.lock();
		try {
			if (semaphore.tryAcquire(maxWait, TimeUnit.SECONDS)) {

				Terminal terminal = terminalQueue.poll();
				return terminal;
			}
		} catch (InterruptedException e) {
			throw new TerminalException(e);
		} finally {
			lockTerminal.unlock();
		}
		throw new TerminalException(" waiting time is over");
	}

	public void returnTerminal(Terminal terminal) {
		lockReturnTerminal.lock();
		try {
			terminalQueue.add(terminal);
			semaphore.release();
		} finally {
			lockReturnTerminal.unlock();
		}
	}

}
