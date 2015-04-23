/*
 * Copyright 2010
 * IBB-CEB - Institute for Biotechnology and Bioengineering - Centre of Biological Engineering
 * CCTC - Computer Science and Technology Center
 *
 * University of Minho 
 * 
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 * 
 * Created inside the SysBioPseg Research Group (http://sysbio.di.uminho.pt)
 */
package pt.uminho.ceb.biosystems.mew.utilities.regexp;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

public enum RegExpType {
	
	STARTS_WITH{		
		
		@Override
		public String toString(){
			return "Starts With:";
		}
		
		final public Method getEvaluationMethod() throws SecurityException, NoSuchMethodException{
			return String.class.getMethod("startsWith", String.class);
		}			
	},
	ENDS_WITH{		
		
		@Override
		public String toString(){
			return "Ends With:";
		}
		
		final public Method getEvaluationMethod() throws SecurityException, NoSuchMethodException{
			return String.class.getMethod("endsWith", String.class);
		}
	},
	CONTAINS{		
		
		@Override
		public String toString(){
			return "Contains:";
		}
		
		final public Method getEvaluationMethod() throws SecurityException, NoSuchMethodException{
			return String.class.getMethod("contains", CharSequence.class);
		}				
	},
	MATCHES{
		@Override
		public String toString(){
			return "Matches (regexp):";
		}
		
		final public Method getEvaluationMethod() throws SecurityException, NoSuchMethodException{
						
			return Pattern.class.getMethod("matches", String.class, CharSequence.class);
		}	
	};
	
	public Method getEvaluationMethod() throws SecurityException, NoSuchMethodException{
		return this.getEvaluationMethod();
	}
	

}
