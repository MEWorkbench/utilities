package pt.uminho.ceb.biosystems.mew.utilities.math.normalization.exceptions;

public class OutOfRangeException extends RuntimeException {
	
	public OutOfRangeException(String message) {
		super(message);
	}
	
	public OutOfRangeException(Throwable e) {
		super(e);
	}
	
	public OutOfRangeException(String message, Throwable e) {
		super(message, e);
	}

}
