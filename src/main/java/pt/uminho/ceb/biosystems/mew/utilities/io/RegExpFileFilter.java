package pt.uminho.ceb.biosystems.mew.utilities.io;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

public class RegExpFileFilter implements FileFilter{

	protected Pattern p;
	
	public RegExpFileFilter(Pattern regExp){
		p = regExp;
	}
	
	@Override
	public boolean accept(File file) {
		
		
		String f = file.getName();
		System.out.println(f);
		return p.matcher(f).matches();
	}

}
