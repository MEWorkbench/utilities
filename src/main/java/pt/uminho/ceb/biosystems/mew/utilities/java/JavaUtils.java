package pt.uminho.ceb.biosystems.mew.utilities.java;

import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.Field;

public class JavaUtils {
	
	public static PlatformEnum getRunningPlatform(){
		
		String os = System.getProperty("os.name");
		
		System.out.println("\n\n\n\n\n\n\n ole");
		System.out.println("os.name: " + os);
		
		PlatformEnum ret = null;
		if (os.startsWith("Mac OS"))
			ret = PlatformEnum.MAC;
	    else if (os.startsWith("Windows"))
	    	ret = PlatformEnum.WIN;
	    else
	    	ret = PlatformEnum.LIN;
	    
		
		return ret;
	}
	
	public static void changeSerialVersionUID(Class<? extends Serializable> clazz, Long newUid) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		 ObjectStreamClass o = ObjectStreamClass.lookup(clazz);
		 long old = o.getSerialVersionUID();
		 
		 Field f = o.getClass().getDeclaredField("suid");
		
		 f.setAccessible(true);
		 f.set(o, newUid);
		 
		 System.out.println("Serialized UID changed from " + old + " to " + newUid + " in class " +  clazz);
	}

}
