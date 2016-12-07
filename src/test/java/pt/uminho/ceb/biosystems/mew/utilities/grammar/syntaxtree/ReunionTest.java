package pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.OtherValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.Reunion;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.Variable;

public class ReunionTest {

	@Test
	public void testSetSimple() {
		
		AbstractSyntaxTree rootNode = new AbstractSyntaxTree<AbstractSyntaxTreeNode,  OtherValue<Collection<?>>>();
		
		Variable v1 = new Variable("A", DataTypeEnum.OTHER);
		Variable v2 = new Variable("B", DataTypeEnum.OTHER);
		Reunion andNode = new Reunion(v1, v2);
		rootNode.setRootNode(andNode);
		
		Environment<OtherValue<Collection<?>>> env = new Environment<>();
		env.associate("A", new OtherValue<Collection<?>>(Arrays.asList(1,2,3,4,5)));
		env.associate("B", new OtherValue<Collection<?>>(Arrays.asList(4,5,6,7,8)));
		
		System.out.println(rootNode.evaluate(env));
	}
	
	@Test
	public void testSetList() {
		
		AbstractSyntaxTree rootNode = new AbstractSyntaxTree<AbstractSyntaxTreeNode,  OtherValue<Collection<?>>>();
		
		List cooleaction = new ArrayList<>();
		Variable v1 = new Variable("A", DataTypeEnum.OTHER);
		Variable v2 = new Variable("B", DataTypeEnum.OTHER);
		Variable v3 = new Variable("C", DataTypeEnum.OTHER);
		Variable v4 = new Variable("D", DataTypeEnum.OTHER);
		cooleaction.add(v1);
		cooleaction.add(v2);
		cooleaction.add(v3);
		cooleaction.add(v4);
		
		Reunion andNode = new Reunion(cooleaction);
		rootNode.setRootNode(andNode);	
		
		Environment<OtherValue<Collection<?>>> env = new Environment<>();
		env.associate("A", new OtherValue<Collection<?>>(Arrays.asList(1,2,3,4,5)));
		env.associate("B", new OtherValue<Collection<?>>(Arrays.asList(4,5,6,7,8)));	
		env.associate("C", new OtherValue<Collection<?>>(Arrays.asList(5,10,4,1)));	
		env.associate("D", new OtherValue<Collection<?>>(Arrays.asList(5,13,132,32)));	

		System.out.println(rootNode.evaluate(env));
	}
	
}
