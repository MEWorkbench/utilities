package pt.uminho.ceb.biosystems.mew.utilities.datastructures.map.listenermap;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

/**
 * 
 * 
 * 
 * @author pmaia
 * @date Jun 25, 2014
 * @version 1.0 
 * @since 
 * @param <K> key
 * @param <V> value
 */
public class ListenerHashMap<K, V> extends HashMap<K, V> {
	
	private static final long			serialVersionUID	= 1L;
	
	public static final String			PROP_PUT			= "add";
	public static final String			PROP_REM			= "remove";
	public static final String			PROP_UPDATE			= "update";
	
	private PropertyChangeSupport	propertySupport;
	
	public ListenerHashMap() {
		super();
		propertySupport = new PropertyChangeSupport(this);
	}
	
	@Override
	public V put(K k, V v) {
		V old = super.put(k, v);
		if (old != null)
			propertySupport.firePropertyChange(new KeyPropertyChangeEvent(this, PROP_UPDATE, k, old, v));
		else
			propertySupport.firePropertyChange(new KeyPropertyChangeEvent(this, PROP_PUT, k, old, v));
		
		return old;
	}
	
	@Override
	public V remove(Object k) {
		V old = super.remove(k);
		if (old != null) propertySupport.firePropertyChange(new KeyPropertyChangeEvent(this, PROP_REM, k, old, null));
		return old;
	}
	
	public V putQuietly(K k, V v){
		V old = super.put(k, v);
		return old;
	}
	
	public V removeQuietly(Object k){
		V old = super.remove(k);
		return old;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.removePropertyChangeListener(listener);
	}
	
	public void removeAllPropertyChangeListeners(){
		PropertyChangeListener[] listeners = propertySupport.getPropertyChangeListeners();
		for(PropertyChangeListener l : listeners)
			propertySupport.removePropertyChangeListener(l);
	}
	
}
