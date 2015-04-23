package pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.IValue;

public class TreeUtils {
	
	public static ArrayList<String> withdrawVariablesInRule(AbstractSyntaxTree<DataTypeEnum, IValue> booleanRule){

		ArrayList<String> ret = new ArrayList<String>();
		AbstractSyntaxTreeNode<DataTypeEnum, IValue> root = null;

		if(booleanRule!=null && booleanRule.getRootNode() != null)
			root = booleanRule.getRootNode();
		else
			return ret;

		Queue<AbstractSyntaxTreeNode<DataTypeEnum, IValue>> nodeQueue = new LinkedList<AbstractSyntaxTreeNode<DataTypeEnum, IValue>>();
		nodeQueue.offer(root);

		while(!nodeQueue.isEmpty()){
			AbstractSyntaxTreeNode<DataTypeEnum, IValue> currentNode = nodeQueue.poll();

			for(int i = 0; i < currentNode.getNumberOfChildren(); i++)
				nodeQueue.offer(currentNode.getChildAt(i));

			if(currentNode.isLeaf())
				ret.add(currentNode.toString());
		}

		return ret;
	}

}