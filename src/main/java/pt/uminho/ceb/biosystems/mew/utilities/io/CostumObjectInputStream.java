package pt.uminho.ceb.biosystems.mew.utilities.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class CostumObjectInputStream extends ObjectInputStream {

	protected ClassLoader classLoader; 
	
	public CostumObjectInputStream(InputStream inputStream, ClassLoader classLoader) throws IOException, SecurityException {
		super(inputStream);
		this.classLoader = classLoader;
	}
	
	@Override
	protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException,	ClassNotFoundException {
		try {
			String name = desc.getName();
			return classLoader.loadClass(name);
		}
		catch(ClassNotFoundException notFound) {
			return super.resolveClass(desc);
		}
	}

}
