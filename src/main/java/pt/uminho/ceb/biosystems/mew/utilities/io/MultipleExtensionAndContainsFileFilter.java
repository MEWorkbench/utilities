package pt.uminho.ceb.biosystems.mew.utilities.io;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import pt.uminho.ceb.biosystems.mew.utilities.java.StringUtils;

public class MultipleExtensionAndContainsFileFilter implements FileFilter {
	protected List<String>	allowedExtensions;
	protected List<String>	containsList;
							
	public MultipleExtensionAndContainsFileFilter(List<String> extensions, String... contains) {
		allowedExtensions = extensions;
		containsList = new ArrayList<String>();
		for (String cont : contains)
			containsList.add(cont);
	}
	
	public MultipleExtensionAndContainsFileFilter(String extension, String contains) {
		allowedExtensions = new ArrayList<>();
		allowedExtensions.add(extension);
		
		containsList = new ArrayList<>();
		containsList.add(contains);
	}

	public MultipleExtensionAndContainsFileFilter(String extension) {
		allowedExtensions = new ArrayList<>();
		allowedExtensions.add(extension);
	}

	public boolean accept(File arg0) {
		if (arg0.isFile()) {
			for (String ext : allowedExtensions) {				
				String fileName = arg0.getName();
				if (fileName.endsWith(ext)) {
					for (String cont : containsList) {
						if(!fileName.contains(cont)){
							return false;
						}
					}
					return true;
				}
			}
			return false;
		}else{
			return false;			
		}
	}
	
	public String toString() {
		return "Ends with any of: [" + StringUtils.concat(",", allowedExtensions) + "] and contains all of ["+StringUtils.concat(",", containsList+"]");
	}
	
	public void addContains(List<String> contains) {
		if(containsList==null){
			containsList = new ArrayList<>();
		}
		for(String cont : contains){
			containsList.add(cont);
		}
	}
}