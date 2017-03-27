package by.sardyka.logistic.exception;

public class TerminalException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public TerminalException() {
		super();
	}

	public TerminalException(String message, Throwable cause) {
		super(message, cause);
	}

	public TerminalException(String message) {
		super(message);
	}

	public TerminalException(Throwable cause) {
		super(cause);
	}
}
