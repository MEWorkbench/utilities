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

public class Or extends AbstractSyntaxTreeNode<DataTypeEnum,IValue>{

	public Or(){
		super(DataTypeEnum.BOOLEAN);
        childNodeArray = new AbstractSyntaxTreeNode[2];
        childNodeArrayType = new DataTypeEnum[2];
        childNodeArrayType[0] = DataTypeEnum.BOOLEAN;
        childNodeArrayType[1] = DataTypeEnum.BOOLEAN;
	}
	
    public Or(AbstractSyntaxTreeNode left, AbstractSyntaxTreeNode right){
    	super(DataTypeEnum.BOOLEAN);
        childNodeArray = new AbstractSyntaxTreeNode[2];
        childNodeArray[0] = left;
        childNodeArray[1] =right;
        childNodeArrayType = new DataTypeEnum[2];
        childNodeArrayType[0] = DataTypeEnum.BOOLEAN;
        childNodeArrayType[1] = DataTypeEnum.BOOLEAN;
    	
    }
	
	@Override
	public IValue evaluate(IEnvironment<IValue> environment) {
		IValue leftTermResult = childNodeArray[0].evaluate(environment);
        IValue rightTermResult = childNodeArray[1].evaluate(environment);
        boolean resultValue = leftTermResult.getBooleanValue() || rightTermResult.getBooleanValue();
        return new BooleanValue(resultValue);
	}

	@Override
	public AbstractSyntaxTreeNode<DataTypeEnum,IValue> newInstance() {
		return new Or();
	}

//	@OverrideandString
//	public String toString() {
//		String leftTermString = childNodeArray[0].toString();
//        String rightTermString = childNodeArray[1].toString();
//        return leftTermString + " or " +rightTermString;
//	}
	
	public String toString(){
    	String leftTermString = childNodeArray[0].toString();
        String rightTermString = childNodeArray[1].toString();
        return " ("+ leftTermString + " or " +rightTermString + ")";
    }

	@Override
	public boolean typeCheck() {
		return true;
	}

/*	@Override
	public String toString(String andString, String orString) {
		String leftTermString = childNodeArray[0].toString();
        String rightTermString = childNodeArray[1].toString();
        return leftTermString + " "+ orString +" " +rightTermString;
	}*/

}
