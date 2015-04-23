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
package pt.uminho.ceb.biosystems.mew.utilities.datastructures.tree.generictree;

import java.util.ArrayList;
import java.util.Stack;

public class GenericTree<E> {
	
	protected TreeNode<E>	root;
	protected int			size;
	
	public GenericTree() {
		root = null;
	}
	
	public GenericTree(
			TreeNode<E> node) {
		root = node;
		size = getNumberOfNodes();
	}
	
	public void setRootNode(TreeNode<E> node) {
		root = node;
		size = getNumberOfNodes();
	}
	
	public TreeNode<E> getRootNode() {
		return root;
	}
	
	public void setParent(TreeNode<E> node, TreeNode<E> parentNode) {
		node.setParent(parentNode);
	}
	
	public TreeNode<E> getParent(TreeNode<E> node) {
		return node.getParent();
	}
	
	public TreeNode<E> getChildAt(TreeNode<E> node, int index) {
		return node.getChildAt(index);
	}
	
	public int getNumberOfChildren(TreeNode<E> node) {
		return node.getNumberOfChildren();
	}
	
	public void addChildNode(TreeNode<E> node, TreeNode<E> newNode) {
		newNode.setParent(node);
		node.addChildNode(newNode);
		size += countNodes(newNode);
	}
	
	public void addChildNode(TreeNode<E> node, GenericTree<E> tree) {
		TreeNode<E> newNode = tree.getRootNode();
		newNode.setParent(node);
		node.addChildNode(newNode);
		size += tree.size();
	}
	
	public void insertChildAt(TreeNode<E> node, int index, TreeNode<E> child) {
		child.setParent(node);
		node.insertChildAt(index, child);
		size += countNodes(child);
	}
	
	public void insertChildAt(TreeNode<E> node, int index, GenericTree<E> tree) {
		TreeNode<E> child = tree.getRootNode();
		child.setParent(node);
		node.insertChildAt(index, child);
		size += tree.size();
	}
	
	public void removeChildAt(TreeNode<E> node, int index) {
		node.removeChildAt(index);
		size -= countNodes(node);
	}
	
	public E getElement(TreeNode<E> node) {
		return node.getElement();
	}
	
	public void setElement(TreeNode<E> node, E element) {
		node.setElement(element);
	}
	
	public boolean isEmpty() {
		return (root == null) ? true : false;
	}
	
	public boolean exists(E key) {
		return find(root, key);
	}
	
	public int getTotalNumberOfLeafs() {
		int n = 0;
		for (TreeNode<E> child : root.getChildNodeList()) {
			if (!child.isLeaf())
				n += getNumberOfLeafs(child);
			else
				n++;
		}
		
		return n;
	}
	
	public int getNumberOfLeafs(TreeNode<E> node) {
		int n = 0;
		if (node.isLeaf())
			n++;
		else
			for (TreeNode<E> child : node.getChildNodeList())
				n += getNumberOfLeafs(child);
		
		return n;
	}
	
	public int getNumberOfNodes() {
		return getNumberOfDescendants(root) + 1;
	}
	
	public int getNumberOfDescendants(TreeNode<E> node) {
		int n = node.getChildNodeList().size();
		for (TreeNode<E> child : node.getChildNodeList())
			n += getNumberOfDescendants(child);
		
		return n;
		
	}
	
	public ArrayList<TreeNode<E>> getPreOrderTraversal() {
		ArrayList<TreeNode<E>> preOrder = new ArrayList<TreeNode<E>>();
		buildPreOrder(root, preOrder);
		return preOrder;
	}
	
	public ArrayList<TreeNode<E>> getPostOrderTraversal() {
		ArrayList<TreeNode<E>> postOrder = new ArrayList<TreeNode<E>>();
		buildPostOrder(root, postOrder);
		return postOrder;
	}
	
	private void buildPreOrder(TreeNode<E> node, ArrayList<TreeNode<E>> preOrder) {
		preOrder.add(node);
		for (TreeNode<E> child : node.getChildNodeList()) {
			buildPreOrder(child, preOrder);
		}
	}
	
	private void buildPostOrder(TreeNode<E> node, ArrayList<TreeNode<E>> preOrder) {
		for (TreeNode<E> child : node.getChildNodeList()) {
			buildPreOrder(child, preOrder);
		}
		preOrder.add(node);
	}
	
	public ArrayList<TreeNode<E>> getLongestPathFromRootToAnyLeaf() {
		ArrayList<TreeNode<E>> longestPath = null;
		int max = 0;
		for (ArrayList<TreeNode<E>> path : getPathsFromRootToAnyLeaf()) {
			if (path.size() > max) {
				max = path.size();
				longestPath = path;
			}
		}
		return longestPath;
	}
	
	public boolean find(TreeNode<E> node, E keyNode) {
		boolean res = false;
		if (node.getElement() != null && node.getElement().equals(keyNode))
			return true;
		
		else {
			for (TreeNode<E> child : node.getChildNodeList())
				if (find(child, keyNode))
					res = true;
		}
		
		return res;
	}
	
	public TreeNode<E> searchNodeByElement(TreeNode<E> node, E keyNode) {
		TreeNode<E> ret = null;
		if (node.getElement() != null && node.getElement().equals(keyNode)) {
			ret = node;
		} else
			for (TreeNode<E> child : node.getChildNodeList()) {
				TreeNode<E> sub = searchNodeByElement(child, keyNode);
				if (sub != null && sub.getElement() != null && sub.getElement().equals(keyNode))
					ret = child;
			}
		
		return ret;
	}
	
	public int depth(TreeNode<E> node) {
		int currentDepth = 0;
		TreeNode<E> currentNode = node;
		
		while (currentNode != null) {
			currentNode = currentNode.getParent();
			currentDepth++;
		}
		
		return currentDepth;
	}
	
	// Return -1 - Empty Tree
	public int height() {
		int currentHeight = -1;
		TreeNode<E> currentNode = root;
		Stack<TreeNode<E>> nextLevelStackNode = new Stack<TreeNode<E>>();
		
		if (currentNode != null) {
			nextLevelStackNode.push(currentNode);
			while (nextLevelStackNode.size() != 0) {
				currentHeight++;
				Stack<TreeNode<E>> newLevelStackNode = createNextLevelNodeStack(nextLevelStackNode);
				nextLevelStackNode = newLevelStackNode;
			}
			
		}
		return currentHeight;
	}
	
	protected Stack<TreeNode<E>> createNextLevelNodeStack(Stack<TreeNode<E>> nextLevelStackNode) {
		Stack<TreeNode<E>> newLevelStackNode = new Stack<TreeNode<E>>();
		
		while (nextLevelStackNode.size() != 0) {
			TreeNode<E> node = nextLevelStackNode.pop();
			insertNewNodes(newLevelStackNode, node);
		}
		
		return newLevelStackNode;
	}
	
	protected void insertNewNodes(Stack<TreeNode<E>> nodeStack, TreeNode<E> node) {
		for (int i = 0; i < node.getNumberOfChildren(); i++) {
			TreeNode<E> childNode = node.getChildAt(i);
			nodeStack.add(childNode);
		}
	}
	
	public int size() {
		return size;
	}
	
	protected int countNodes(TreeNode<E> node) {
		int currentNumberOfNodes = 0;
		Stack<TreeNode<E>> nodeStack = new Stack<TreeNode<E>>();
		
		while (nodeStack.size() != 0) {
			TreeNode<E> currentNode = nodeStack.pop();
			insertNewNodes(nodeStack, currentNode);
			currentNumberOfNodes++;
		}
		
		return currentNumberOfNodes;
	}
	
	public ArrayList<ArrayList<TreeNode<E>>> getPathsFromRootToAnyLeaf() {
		ArrayList<ArrayList<TreeNode<E>>> paths = new ArrayList<ArrayList<TreeNode<E>>>();
		ArrayList<TreeNode<E>> currentPath = new ArrayList<TreeNode<E>>();
		getPath(root, currentPath, paths);
		
		return paths;
	}
	
	private void getPath(TreeNode<E> node, ArrayList<TreeNode<E>> currentPath, ArrayList<ArrayList<TreeNode<E>>> paths) {
		if (currentPath == null)
			return;
		
		currentPath.add(node);
		
		if (node.getChildNodeList().size() == 0) {
			paths.add(clone(currentPath));
		}
		for (TreeNode<E> child : node.getChildNodeList())
			getPath(child, currentPath, paths);
		
		int index = currentPath.indexOf(node);
		for (int i = index; i < currentPath.size(); i++)
			currentPath.remove(index);
	}
	
	private ArrayList<TreeNode<E>> clone(ArrayList<TreeNode<E>> list) {
		ArrayList<TreeNode<E>> newList = new ArrayList<TreeNode<E>>();
		for (TreeNode<E> node : list)
			newList.add(new TreeNode<E>(node));
		
		return newList;
	}
	
	public void printTree(TreeNode<E> node) {
		if (node == null)
			node = root;
		int depth = this.depth(node);
		for (int i = 1; i < depth; i++)
			System.out.print("\t");
		String elem = (node.getElement() == null) ? "ROOT (depth=" + depth + ")" : node.getElement().toString()
				+ " (depth=" + depth + ")";
		System.out.println(elem);
		
		for (TreeNode<E> child : node.getChildNodeList()) {
			printTree(child);
		}
	}
	
}
