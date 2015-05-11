package pt.uminho.ceb.biosystems.mew.utilities.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtils {
	
	public static Properties readPropertiesFromFile(String filePath){
		Properties prop = new Properties();
		InputStream input = null;
		
		try{
			input = new FileInputStream(filePath);
			prop.load(input);
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return prop;
	}
	
	public static void writePropertiesFromFile(Properties prop, String filePath){
		OutputStream output = null;
		
		try{
			output = new FileOutputStream(filePath);
			prop.store(output, null);
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
