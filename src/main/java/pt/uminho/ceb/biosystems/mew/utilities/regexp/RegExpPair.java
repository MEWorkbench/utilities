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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.pair.Pair;


public class RegExpPair extends Pair<RegExpType,String>{

	private static final long serialVersionUID = 1L;

	public RegExpPair(RegExpType value, String pairValue) {
		super(value, pairValue);
	}
	
	public boolean evaluate(String toMatch) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
		Method method = value.getEvaluationMethod();
		Boolean result = null;
		if(method.getReturnType().isAssignableFrom(boolean.class)){
			if(method.getParameterTypes().length==1)
				result = (Boolean) method.invoke(toMatch, pairValue);
			else
				result = (Boolean) method.invoke(null, pairValue,toMatch);
		}
		else throw new IllegalArgumentException(
				"Return type for method ["+method.getDeclaringClass().getName()+": "+method.getName()+"] is not boolean"
				);
		
		return result;
	}

}
