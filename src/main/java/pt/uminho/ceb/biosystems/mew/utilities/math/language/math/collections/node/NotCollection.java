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
package pt.uminho.ceb.biosystems.mew.utilities.math.language.math.collections.node;

import java.util.ArrayList;
import java.util.Collection;

import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTreeNode;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.IEnvironment;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.IValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.OtherValue;

public class NotCollection extends AbstractSyntaxTreeNode<DataTypeEnum,IValue>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotCollection(AbstractSyntaxTreeNode node){
    	super(DataTypeEnum.OTHER);
        childNodeArray = new ArrayList<>();
        childNodeArray.add(0,node);
        childNodeArrayType = new ArrayList<>();
        childNodeArrayType.add(0,DataTypeEnum.OTHER);
	}

	@Override // return same because is not possible calculate the collection opposite 
	public IValue evaluate(IEnvironment<IValue> environment) {
        IValue based = childNodeArray.get(0).evaluate(environment);
        Collection basedcollection = (Collection)based.getValue();
		return new OtherValue<Collection>(basedcollection);
	}

	@Override
	public AbstractSyntaxTreeNode<DataTypeEnum,IValue> newInstance() {
    	AbstractSyntaxTreeNode<DataTypeEnum,IValue> leftAST = childNodeArray.get(0);
        return new NotCollection(leftAST);

	}

	@Override
	public String toString() {
		String node = childNodeArray.get(0).toString();
		return " ¬ ( "+ node + ")";
	}

	@Override
	public boolean typeCheck() {
		return true;
	}

/*	@Override
	public String toString(String andString, String orString) {
		return toString();
	}*/

}
