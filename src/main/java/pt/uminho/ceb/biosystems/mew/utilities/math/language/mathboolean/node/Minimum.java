package pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node;

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

import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTreeNode;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.IEnvironment;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DoubleValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.IValue;

public class Minimum extends AbstractSyntaxTreeNode<DataTypeEnum, IValue> {

	private static final long serialVersionUID = 4915478769937391211L;

	public Minimum(AbstractSyntaxTreeNode<DataTypeEnum, IValue> left, AbstractSyntaxTreeNode<DataTypeEnum, IValue> right) {
		super(DataTypeEnum.DOUBLE);

		childNodeArray = new AbstractSyntaxTreeNode[2];
		childNodeArray[0] = left;
		childNodeArray[1] = right;

		childNodeArrayType = new DataTypeEnum[2];
		childNodeArrayType[0] = DataTypeEnum.DOUBLE;
		childNodeArrayType[1] = DataTypeEnum.DOUBLE;
	}

	@Override
	public IValue evaluate(IEnvironment<IValue> environment) {
		IValue leftTermResultValue = childNodeArray[0].evaluate(environment);
		IValue rightTermResultValue = childNodeArray[1].evaluate(environment);

		double resultValue = Double.NaN;
		if (Double.isNaN((Double)leftTermResultValue.getValue()))
			resultValue = (Double)rightTermResultValue.getValue();
		else if (Double.isNaN((Double)rightTermResultValue.getValue()))
			resultValue = (Double)leftTermResultValue.getValue();
		else
			resultValue = Math.min((Double)leftTermResultValue.getValue(), (Double)rightTermResultValue.getValue());
		return new DoubleValue(resultValue);
	}

	@Override
	public boolean typeCheck() {
		return true;
	}

	@Override
	public String toString() {
		String leftTermString = childNodeArray[0].toString();
		String rightTermString = childNodeArray[1].toString();

		return "min(" + leftTermString + ";" + rightTermString + ")";
	}

	@Override
	public AbstractSyntaxTreeNode<DataTypeEnum, IValue> newInstance() {
		AbstractSyntaxTreeNode<DataTypeEnum, IValue> leftAST = childNodeArray[0];
		AbstractSyntaxTreeNode<DataTypeEnum, IValue> rightAST = childNodeArray[1];
		return new Minimum(leftAST, rightAST);
	}
}
