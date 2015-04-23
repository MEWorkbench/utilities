package pt.uminho.ceb.biosystems.mew.utilities.io;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class MultipleExtensionFileFilter implements FileFilter{
	protected ArrayList<String> allowedExtensions;

	public MultipleExtensionFileFilter(String... extensions){
		allowedExtensions = new ArrayList<String>();
		for(String ext: extensions)
			allowedExtensions.add(ext);
	}

	public boolean accept(File arg0) {
		boolean ret = false;
		if(arg0.isFile()){
			for(String ext: allowedExtensions)
				if(arg0.getName().endsWith(ext)){
					return true;
				}
		}			
		return ret; 
	}

}