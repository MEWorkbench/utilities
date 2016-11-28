package pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.OtherValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.Intersection;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.Variable;

public class IntersectionTest {

	@Test
	public void testSet() {
		
		AbstractSyntaxTree rootNode = new AbstractSyntaxTree<AbstractSyntaxTreeNode,  OtherValue<Collection<?>>>();
		
		Variable v1 = new Variable("A", DataTypeEnum.OTHER);
		Variable v2 = new Variable("B", DataTypeEnum.OTHER);
		Intersection andNode = new Intersection(v1, v2);
		rootNode.setRootNode(andNode);	
		
		Environment<OtherValue<Collection<?>>> env = new Environment<>();
		env.associate("A", new OtherValue<Collection<?>>(Arrays.asList(1,2,3,4,5)));
		env.associate("B", new OtherValue<Collection<?>>(Arrays.asList(4,5,6,7,8)));	
		System.out.println(rootNode.evaluate(env));
	}
	
	
}
