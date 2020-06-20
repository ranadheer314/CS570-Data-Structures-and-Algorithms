//Name:Randheer Vennapureddy
//CWID:10457392
package linkedlistAssg;
import java.util.ArrayList;

public class IDLList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	//Create an Empty Double-Linked List
	public IDLList() {
		head=null;
		tail=null;
		size=0;
		indices=new ArrayList<Node<E>>();
	}
	
	//Inner Class
	private class Node<E>{
		E data;
		Node<E> next;
		Node<E> prev;
		Node(E ele){
			this.data=ele;
			this.next=null;
			this.prev=null;
		}
		
		@SuppressWarnings("unused")
		Node(E ele,Node<E> prev,Node<E> next){
			this.data=ele;
			this.next=next;
			this.prev=prev;
		}
	}
		
	//Add the element at the specified position	
	public boolean add(int index,E elem) {
		if(index<0 || index>size)
			throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
		
		if(index==0) {
			add(elem);
		}
		
		else if(index==size-1) {
			append(elem);
		}
		else {
			Node<E> new_node=new Node<E>(elem);
			Node<E> temp1=indices.get(index);
			temp1.prev.next=new_node;
			new_node.prev=temp1.prev;
			new_node.next=temp1;
			temp1.prev=new_node;
			size++;
			indices.add(index, new_node);
		}
		return true;	
		}
	
	//Add element at the starting of the double-linked List 
	public boolean add(E ele) {
		Node<E> new_node=new Node<E>(ele);
		if(head==null) {
			head=new_node;
			tail=new_node;
			size++;
		}
		else {
		head.prev=new_node;
		new_node.next=head;
		head=new_node;
		size++;
		}
		indices.add(0,new_node);
		return true;
	}
	
	//Add element at the end of the Doubly Linked List
	public boolean append(E elem) {
		if(head==null) {
			add(elem);
		}
		else 
		   {
			Node<E> new_node=new Node<E>(elem);
			tail.next=new_node;
			new_node.prev=tail;
			tail=new_node;
			size++;
			indices.add(new_node);
			
		  }
		return true;
	}
	
	//return the element at the index
	public E get(int index) 
	{
		if(index<0 || index>=size)
			throw new ArrayIndexOutOfBoundsException();
		Node<E> temp=indices.get(index);
		System.out.println("Element at the index is"+temp.data);
		return temp.data;
	}
	
	//returns the object at the head of the DLL
	public E getHead() {
		if(size==0)
			throw new ArrayIndexOutOfBoundsException();
		return indices.get(0).data;
	}
	
	//Returns the object at the last of DLL
	public E getLast() {
		if(size==0)
			throw new ArrayIndexOutOfBoundsException("there are no elements");
		return indices.get(size-1).data;
	}
	
	//returns the size of the DLL
	public int size() {
		return size;
	}
	
	 //removes the front element in DLL
	public E remove()
	{
		if(size==0)
		{
			throw new ArrayIndexOutOfBoundsException("there are no elements");
		}
		
		if(head==tail) {
			head=null;
			tail=null;
			size--;
			indices.remove(0);
			return head.data;
		}
		else
		{
		head.next.prev=null;
		head=head.next;
		indices.remove(0);
		size--;
		return head.data;
	}
	}
	
	//Method to remove the element at the last of the DLL
	public E removeLast() {
		if(size==0)
			throw new ArrayIndexOutOfBoundsException("there are no elements");
		if(head==tail)
		{
			remove();
			size=0;
			return head.data;
			}
		else 
		{
		tail.prev.next=null;
		tail=tail.prev;
		size--;
		indices.remove(indices.size()-1);
		return tail.data;
	}
	}
	
	//Method to Remove the element at the specified index 
	public E removeAt(int index) {
		if(index<0 || index>=size)
			throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
		Node<E> temp1=indices.get(index);
		if(index==0)
			return remove();
		if(index==size-1) {
			return removeLast();
		}
		else 
		{
		temp1.next.prev=temp1.prev;
		temp1.prev.next=temp1.next;
		size--;
		}
		indices.remove(index);
		return indices.get(index).data;
		}
	
	//Method to remove the element given as the parameter
	public boolean remove(E elem) {
		int place=-1;
		boolean found=false;
		Node<E> search=head;
		while(search!=null) {
			place++;
			if(search.data.equals(elem)) {
				removeAt(place);		
				found=true;	
				break;
			}
			search=search.next;	
		}
		
		return found;
	}
	
	//String representation of the DLL
	public String toString() {
		Node<E> ref=head;
		StringBuilder result=new StringBuilder();
		while(ref!=null) {
			result.append(ref.data);
			if(ref.next!=null) {
				result.append("-->");
			}
			ref=ref.next;
		}
		return result.toString();
	}
	 
}

