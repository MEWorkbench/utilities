package pt.uminho.ceb.biosystems.mew.utilities.math.collection;

import java.util.Collection;

/**
 * Interface used to do mathematical calculations with a Collection.
 * <br>The Mean and the Sum are some of the examples for this interface implementation
 * @author hgiesteira
 *
 * @param <T> type of element
 */
public interface IMathCollection<T> {
	
	public T calculeCollection(Collection<T> collection);

}
