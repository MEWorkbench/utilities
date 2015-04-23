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
package pt.uminho.ceb.biosystems.mew.utilities.datastructures.vector.orderedcircularvector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



public class OrderedCircularVector<E> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected List<E> dataArray;
	protected Comparator<? super E> comparator;
	protected int head;
	protected int tail;
	
	public OrderedCircularVector(Comparator<? super E> comparator,int size){
		dataArray = new ArrayList<E>(size);
		for(int i = 0; i < size;i++)
			dataArray.add(null);
		head = -1;
		tail = -1;
		this.comparator = comparator;
	}
	
	public boolean isEmpty(){
		return (head == -1) && (tail == -1);
	}
	
	public boolean isFull(){
		return size() == length();	
	}
	
	public int length(){
		return dataArray.size();
	}
	
	public int size(){
		if(head == -1)
			return 0;
		
		if(head <= tail)
			return (tail - head)+1;
		
		if(head > tail)
			return (length()-head)+tail+1;
		
		return -1;
	}
	
	public E removeMinimum(){
		if((head == tail) && (head != -1)){
			E objectToReturn = dataArray.get(head);
			head = -1;
			tail = -1;
			return objectToReturn;
		}
		
		if(head != -1){
			E objectToReturn = dataArray.get(head);
			
			if(head == length()-1)
				head = 0;
			else
				head++;
			
			return objectToReturn;
		}
		return null;
	}
	
	public E removeMaximum(){
		if((head == tail) && (head != -1)){
			E objectToReturn = dataArray.get(tail);
			head = -1;
			tail = -1;
			return objectToReturn;
		}
		
		if(head != -1){
			E objectToReturn = dataArray.get(tail);
			if(tail == 0)
				tail = length()-1;
			else
				tail--;
			return objectToReturn;
		}
		return null;
		
	}
	
	public void insert(E object) throws Exception{
		if(isFull())
			throw new Exception("OrderedCircularVector is full");
		
		if(head == -1){
			head = 0;
			tail = 0;
			dataArray.set(0,object);
		}else{
			int insertPosition = calculateInsertPosition(object);
			insertObjectInPosition(insertPosition,object);
		}
	}

	protected void insertObjectInPosition(int insertPosition,E object){
		if(tail == length()-1){
			moveObjects(insertPosition);
			tail = 0;
		}else{
			moveObjects(insertPosition);
			tail++;
		}
		
		dataArray.set(insertPosition,object);
	}
		
	protected void moveObjects(int insertPosition){
		int tailPosition = tail+1;
		
		if(tail == length()-1){
			dataArray.set(0,dataArray.get(tail));
			tailPosition = tail;
		}
		
		
		if(head <= tail)
			for(int i = tailPosition;i > insertPosition;i--)
				dataArray.set(i,dataArray.get(i-1));
		else {
			for(int i = tailPosition; i < head;i++)
				dataArray.set(i,dataArray.get(i-1));
			dataArray.set(0,dataArray.get(length()-1));
			for(int j = length()-1; j > insertPosition;j--)
				dataArray.set(j,dataArray.get(j-1));
		}
	}

	protected int calculateInsertPosition(E object){
		int currentPosition = head;
		
		while(!((currentPosition > tail) && (tail-head >= 0)) || ((currentPosition > tail) && (currentPosition < head))){
			E objectInArray = dataArray.get(currentPosition);
			
			if(comparator.compare(object,objectInArray) == -1)
				return currentPosition;
			
			if(currentPosition == tail)
				return currentPosition+1;
			
			if(currentPosition == (length()-1))
				currentPosition = 0;
			else
				currentPosition++;
			
		}
		
		return currentPosition;
	}

	public E getMaximum(){
		if(head != -1)
			return dataArray.get(tail);
		return null;
	}
	
	public E getMinimum(){
		if(head != -1)
			return dataArray.get(head);
		return null;
	}

	
	public E getElementInPosition(int position){
		int realPosition = head + position;
		
		if((head - tail > 0) && (head + position > length() -1))
			realPosition = position-(length()-head-1);
			
		return dataArray.get(realPosition);
	}
	
	public Object getRandomElement(){
		int randomNumber = (int)(Math.random()*size());
		return getElementInPosition(randomNumber);
	}

	public int getHeadValue(){
		return head;
	}
	
	public int getTailValue(){
		return tail;
	}
	
	public void clear(){
		head = -1;
		tail = -1;	
	}
	
	
	
	
}
