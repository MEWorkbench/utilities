package pt.uminho.ceb.biosystems.mew.utilities.datastructures.exceptions;

public class MapKeyAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Object key;
	protected Object value;
	
	public MapKeyAlreadyExistsException(Object key) {
		super("The map already has key " + key.toString());
		this.key = key;
		
		
	}

	public MapKeyAlreadyExistsException(Object key, Object value) {
		super("The map already has key " + key.toString() + " with value " + value);
		this.key = key;
		this.value = value;
	}

}
