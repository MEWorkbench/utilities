package pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree;

import org.junit.Assert;
import org.junit.Test;

import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.BooleanValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.IValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.And;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.Not;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.Or;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.Variable;

public class BooleanTest {
	
	@Test
	public void testAnd(){
		
		AbstractSyntaxTree<DataTypeEnum, IValue> b = new AbstractSyntaxTree<DataTypeEnum,  IValue>();
		AbstractSyntaxTreeNode left = new Variable("A");
		AbstractSyntaxTreeNode right= new Variable("B");
		AbstractSyntaxTreeNode<DataTypeEnum,IValue> and = new And(left, right);
		b.setRootNode(and);
		
		Environment<IValue> env = new Environment<>();
		env.associate("A", new BooleanValue(true));
		env.associate("B", new BooleanValue(false));	
		
		Assert.assertTrue(b.evaluate(env).getValue().equals(Boolean.FALSE));
		
		env.associate("B", new BooleanValue(true));
		Assert.assertTrue(b.evaluate(env).getValue().equals(Boolean.TRUE));
		
		env.associate("A", new BooleanValue(false));
		Assert.assertTrue(b.evaluate(env).getValue().equals(Boolean.FALSE));
		
		env.associate("B", new BooleanValue(false));
		Assert.assertTrue( b.evaluate(env).getValue().equals(Boolean.FALSE));
	}
	
	@Test
	public void testOr(){
		
		AbstractSyntaxTree<DataTypeEnum, IValue> b = new AbstractSyntaxTree<DataTypeEnum,  IValue>();
		AbstractSyntaxTreeNode left = new Variable("A");
		AbstractSyntaxTreeNode right= new Variable("B");
		AbstractSyntaxTreeNode<DataTypeEnum,IValue> and = new Or(left, right);
		b.setRootNode(and);
		
		Environment<IValue> env = new Environment<>();
		env.associate("A", new BooleanValue(true));
		env.associate("B", new BooleanValue(false));	
		
		Assert.assertTrue(b.evaluate(env).getValue().equals(Boolean.TRUE));
		
		env.associate("B", new BooleanValue(true));
		Assert.assertTrue(b.evaluate(env).getValue().equals(Boolean.TRUE));
		
		env.associate("A", new BooleanValue(false));
		Assert.assertTrue(b.evaluate(env).getValue().equals(Boolean.TRUE));
		
		env.associate("B", new BooleanValue(false));
		Assert.assertTrue(b.evaluate(env).getValue().equals(Boolean.FALSE));
		
	}
	
	@Test
	public void testNot(){
		
		AbstractSyntaxTree<DataTypeEnum, IValue> b = new AbstractSyntaxTree<DataTypeEnum,  IValue>();
		AbstractSyntaxTreeNode left = new Variable("A");
		AbstractSyntaxTreeNode<DataTypeEnum,IValue> not = new Not(left);
		b.setRootNode(not);
		
		Environment<IValue> env = new Environment<>();
		env.associate("A", new BooleanValue(true));
		Assert.assertTrue(b.evaluate(env).getValue().equals(Boolean.FALSE));
		
		env.associate("A", new BooleanValue(false));
		Assert.assertTrue(b.evaluate(env).getValue().equals(Boolean.TRUE));
		
	}

//	@Test
//	public void testComplex1(){
//		
//		AbstractSyntaxTree<DataTypeEnum, IValue> tree = new AbstractSyntaxTree<DataTypeEnum,  IValue>();
//		AbstractSyntaxTreeNode a = new Variable("A");
//		AbstractSyntaxTreeNode b= new Variable("B");
//		AbstractSyntaxTreeNode c= new Variable("C");
//		
//		AbstractSyntaxTreeNode<DataTypeEnum,IValue> or = new Or(a, b);
//		AbstractSyntaxTreeNode<DataTypeEnum,IValue> and = new And(or, c);
//		tree.setRootNode(and);
//		
//		Environment<IValue> env = new Environment<>();
//		env.associate("A", new BooleanValue(true));
//		env.associate("B", new BooleanValue(false));
//		env.associate("C", new BooleanValue(true));
//		
//		Assert.assertTrue(tree.evaluate(env).getValue().equals(Boolean.TRUE));
//		
////		env.associate("B", new BooleanValue(true));
////		Assert.assertTrue(tree.evaluate(env).getValue().equals(Boolean.TRUE));
////		
////		env.associate("A", new BooleanValue(false));
////		Assert.assertTrue(tree.evaluate(env).getValue().equals(Boolean.TRUE));
////		
////		env.associate("B", new BooleanValue(false));
////		Assert.assertTrue(tree.evaluate(env).getValue().equals(Boolean.FALSE));
//		
//	}
}
