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

public class TreeNode<E>{
	
	protected E element;
	protected ArrayList<TreeNode<E>> childNodeList;
	protected TreeNode<E> parent = null;
	
	public TreeNode(){}
	
	public TreeNode(TreeNode<E> node){
		this.element = node.getElement();
		this.childNodeList = node.getChildNodeList();
		this.parent = node.getParent();
	}
	
	public void setParent(TreeNode<E> parentNode){
		parent = parentNode;
	}
	
	public TreeNode<E> getParent(){
		return parent;
	}
	
	public int getNumberOfChildren(){
		return getChildNodeList().size();
	}
	
	public void addChildNode(TreeNode<E> newNode){
		getChildNodeList().add(newNode);
	}
	
	public void insertChildAt(int index, TreeNode<E> child){
		getChildNodeList().set(index,child);
	} 
	
	public void removeChildAt(int index) throws IndexOutOfBoundsException {
        getChildNodeList().remove(index);
    }
	
	public E getElement(){
		return element;
	}
	
	public void setElement(E newElement){
		element = newElement;
	}

	public TreeNode<E> getChildAt(int index) {
		return getChildNodeList().get(index);
	}
	
	public boolean isLeaf(){
		return getChildNodeList().size() == 0;
	}
	
	public boolean isRoot(){
		return parent == null;
	}

	/**
	 * @return the childNodeList
	 */
	public ArrayList<TreeNode<E>> getChildNodeList() {
		if(childNodeList==null)
			childNodeList = new ArrayList<TreeNode<E>>();
		return childNodeList;
	}
	
	

}
