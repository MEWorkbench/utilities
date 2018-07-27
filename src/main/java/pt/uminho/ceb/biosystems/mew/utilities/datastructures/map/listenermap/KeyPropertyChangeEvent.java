package pt.uminho.ceb.biosystems.mew.utilities.datastructures.map.listenermap;

import java.beans.PropertyChangeEvent;

/**
 * 
 * @author pmaia
 */
public class KeyPropertyChangeEvent extends PropertyChangeEvent {
	
	private static final long	serialVersionUID	= -7549129375083529752L;
	
	private Object key = null;
	
	public KeyPropertyChangeEvent(Object source, String propertyName, Object key, Object oldValue, Object newValue) {
		super(source, propertyName, oldValue, newValue);
		this.key = key;
	}

	public Object getKey() {
		return key;
	}
	
}
