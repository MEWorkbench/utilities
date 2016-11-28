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

import java.util.Collection;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.collection.CollectionUtils;

/**
 * Created by IntelliJ IDEA.
 * User: pedro
 * Date: Mar 13, 2009
 * Time: 4:31:20 PM
 * To change this template use File | Settings | File Templates.
 */

import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTreeNode;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.IEnvironment;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.IValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.OtherValue;

public class Reunion extends AbstractSyntaxTreeNode<DataTypeEnum,IValue> {

	private static final long serialVersionUID = 1L;

	public Reunion(AbstractSyntaxTreeNode left, AbstractSyntaxTreeNode right){
    	super(DataTypeEnum.OTHER);
        childNodeArray = new AbstractSyntaxTreeNode[2];
        childNodeArray[0] = left;
        childNodeArray[1] =right;
        childNodeArrayType = new DataTypeEnum[2];
        childNodeArrayType[0] = DataTypeEnum.OTHER;
        childNodeArrayType[1] = DataTypeEnum.OTHER;
    	
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment) {
        IValue leftTermResultValue = childNodeArray[0].evaluate(environment);
        IValue rightTermResultValue = childNodeArray[1].evaluate(environment);
        return new OtherValue<Collection>(CollectionUtils.getReunionValues((Collection)leftTermResultValue.getValue(), (Collection)rightTermResultValue.getValue()));
    }

    @Override
    public boolean typeCheck() {
        return true;
    }

    @Override
    public String toString() {
        String leftTermString = childNodeArray[0].toString();
        String rightTermString = childNodeArray[1].toString();
        return  "( " +leftTermString + " and " +rightTermString + " )";
    }
    
    @Override
    public AbstractSyntaxTreeNode<DataTypeEnum,IValue> newInstance() {
    	AbstractSyntaxTreeNode<DataTypeEnum,IValue> leftAST = childNodeArray[0];
    	AbstractSyntaxTreeNode<DataTypeEnum,IValue> rightAST = childNodeArray[1];
        return new Reunion(leftAST,rightAST);
    }

}
