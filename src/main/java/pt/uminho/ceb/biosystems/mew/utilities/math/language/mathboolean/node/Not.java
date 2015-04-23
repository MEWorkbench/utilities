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
package pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node;

import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTreeNode;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.IEnvironment;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.BooleanValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.IValue;

public class Not extends AbstractSyntaxTreeNode<DataTypeEnum,IValue>{
	
	public Not(){
		super(DataTypeEnum.BOOLEAN);
        childNodeArray = new AbstractSyntaxTreeNode[1];
        childNodeArrayType = new DataTypeEnum[1];
        childNodeArrayType[0] = DataTypeEnum.BOOLEAN;
	}
	
	public Not(AbstractSyntaxTreeNode node){
		super(DataTypeEnum.BOOLEAN);
        childNodeArray = new AbstractSyntaxTreeNode[1];
        childNodeArray[0] = node;
        childNodeArrayType = new DataTypeEnum[1];
        childNodeArrayType[0] = DataTypeEnum.BOOLEAN;
	}

	@Override
	public IValue evaluate(IEnvironment<IValue> environment) {
		IValue node = childNodeArray[0].evaluate(environment);
		boolean resultValue = !node.getBooleanValue();
		return new BooleanValue(resultValue);
	}

	@Override
	public AbstractSyntaxTreeNode<DataTypeEnum,IValue> newInstance() {
		return new Not();
	}

	@Override
	public String toString() {
		String node = childNodeArray[0].toString();
		return " not( "+ node + ")";
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
