package by.sardyka.tour.exception;

public class WrongDataException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongDataException() {
		super();
	}

	public WrongDataException(String message, Throwable e) {
		super(message, e);
	}

	public WrongDataException(String message) {
		super(message);
	}

	public WrongDataException(Throwable e) {
		super(e);
	}
}
