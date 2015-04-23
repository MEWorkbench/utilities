package pt.uminho.ceb.biosystems.mew.utilities.io;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class ContainsFileFilter implements FileFilter{
	protected ArrayList<String> mustContain;

	public ContainsFileFilter(String... strings){
		mustContain = new ArrayList<String>();
		for(String ext: strings)
			mustContain.add(ext);
	}

	public boolean accept(File arg0) {
		boolean ret = true;
		if(arg0.isFile()){
			for(String ext: mustContain)
				if(!arg0.getName().contains(ext)){
					return false;
				}
		}			
		return ret; 
	}

}