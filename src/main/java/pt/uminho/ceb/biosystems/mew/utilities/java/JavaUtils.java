package pt.uminho.ceb.biosystems.mew.utilities.java;

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

}
