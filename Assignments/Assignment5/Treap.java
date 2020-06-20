//Name:Randheer Vennapureddy
//Homework Assignment 5
//CWID:10457392
package Treap;
import java.util.*;

class Treap<E extends Comparable<E>>{	
	private static class Node<E>{
		public E data; 
		public int priority; // random heap priority
		public Node<E> left; 
		public Node<E> right; 
		
		public Node(E data,int priority) {
			this.priority=priority;
			if(data==null)
				throw new IllegalArgumentException("your iput is not valid(null), enter other value ");
			this.left=this.right=null; //Node with no childs
			this.data=data;
		}
		Node<E> rotateRight(){ //Right Rotation
			Node<E> temp1=this.left;
			Node<E> temp2=temp1.right;
			temp1.right=this;
			this.left=temp2;
			return temp1;
			
		}
		Node<E> rotateLeft(){ //Left Rotation
			Node<E> temp1=this.right;
			Node<E> temp2=temp1.left;
			temp1.left=this;
			this.right=temp2;
			return temp1;			
		}
	}
	
	private Random priorityGenerator; 
	private Node<E> root; 
	
	public Treap(long seed) {
		this.priorityGenerator=new Random(seed);
		this.root=null;
	}
	public Treap() {
		this.priorityGenerator=new Random();
		this.root=null;
	}
	
	
	boolean add(E key) {
		boolean temp=add(key,priorityGenerator.nextInt());
		 return temp;
	}
	
	boolean add(E key, int priority) {
		Stack<Node<E>> route=new Stack<Node<E>>();
		Node<E> cur=root;
		if(cur==null) {
			Node<E> n=new Node<E>(key,priority);
			root=n;
			return true;
		}
		while(cur!=null) {
			if(cur.data.compareTo(key)<0) {
				route.push(cur);
				cur=cur.right;
				}
			else if(cur.data.compareTo(key)>0){
				route.push(cur);
				cur=cur.left;
				}
			else {
				return false;
			}
		}
		Node<E> a=route.peek();
		Node<E> ele=new Node<E>(key,priority);
		if(a.data.compareTo(key)<0) {
			a.right=ele;
			route.push(ele);
		}
		else {
			a.left=ele;
			route.push(ele);
		}
		reheap(route);//to adjust the new element properly in the treap
		return true;	
	}
	
	void reheap(Stack<Node<E>> route) {
		Node<E> JI=route.peek(); //Just Inserted element is stored in the JI 
		route.pop();        //Newly Inserted element is pop out of the stack 
		while(!route.isEmpty()) {
			Node<E> anc=route.peek();
			route.pop();
			if(anc.priority < JI.priority)
			{
				if(JI.data.compareTo(anc.data) >0)//have to perform left rotation
				{
						JI = anc.rotateLeft();
						if(route.isEmpty())
							root = JI;
						else {
							Node<E> par = route.peek();
							if(JI.data.compareTo(par.data)<0)
								par.left = JI;
							else
								par.right = JI;	
						}
					}
				else //have to perform right rotation
				{
					JI = anc.rotateRight();
					if(route.isEmpty())
						root = JI;
					else {
						Node<E> par = route.peek();
						if(JI.data.compareTo(par.data)<0)
							par.left = JI;
						else
							par.right = JI;
					}
				}
			}
		}
	}
	
	Node<E> delete(Node<E> root,E key) {
		if(key==null) {
			throw new IllegalArgumentException("your iput is not valid(null), enter other value "); 
		}
		if(root==null)
			return root;
		else {//initially if current root node matches the key value
			if((root.data.compareTo(key)==0) && root.right==null && root.left==null) {
			root=null;//leaf position 
			}
			else if((root.data.compareTo(key)==0) && root.right==null && root.left!=null) {//if only right child node is null
			root=root.rotateRight();
			root.right=delete(root.right,key);
			}
			else if((root.data.compareTo(key)==0) && root.left==null && root.right!=null) {//if only left child node is null
			root=root.rotateLeft();
			root.left=delete(root.left,key);
			}
			else if((root.data.compareTo(key)==0) && root.left!=null && root.right!=null) {//node has both child nodes
			if(root.left.priority>root.right.priority) {
				root=root.rotateRight();
				root.right=delete(root.right,key);
			}
			else {
				root=root.rotateLeft();
				root.left=delete(root.left,key);
			}
		}
			else if(root.data.compareTo(key)<0) {//comparing the current data with the given key
				root.right=delete(root.right,key);
			}
			else {
				root.left=delete(root.left,key);
			}
		}
		return root;
	}
		
	boolean delete(E key) {
		if(!find(root,key)) {
			return false;
		}
		else if(root==null) {
			return false;
		}
		else {
			root=delete(root,key);
			return true;
	}
	}
	
	
	private boolean find(Node<E> root, E key) {
		if(root==null) {
			return false;
		}
		else if(root.data.compareTo(key)==0) {
			return true;
		}
		else if(root.data.compareTo(key)<0) {
			return find(root.right,key);
		}
		else {
			return find(root.left,key);
	}
	}

	boolean find(E key)
	{
		return find(root,key);
	}
	
	//PreOrder traversal 
	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) 
	{ 
		for (int i = 1; i < depth; i++) { 
			sb.append("  "); 
	} 
		if (node == null) 
	{ 
			sb.append("null\n"); 
			}
	else { 
		sb.append("(key="+node.data);
		sb.append(",priority="+node.priority+")");
		sb.append("\n"); 
		preOrderTraverse(node.left, depth + 1, sb); 
		preOrderTraverse(node.right, depth + 1, sb); 
		} 
	}
	
	public String toString() 
	{ 
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb); 
		return sb.toString(); 
	}


	public static void main(String[] args) {
		Treap<Integer> testTree;
		testTree= new Treap<Integer>();
		testTree.add(4,19);
		testTree.add(2,31);
		testTree.add(6,70);
		testTree.add(1,84);
		testTree.add(3,12);
		testTree.delete(1);
		testTree.add(5,83);
		testTree.add(7,26);
		testTree.add(50,73);
		System.out.println(testTree.toString());
		testTree.add(30,48); 
		testTree.add(20,92); 
		testTree.add(40,21); 
		System.out.println(testTree.find(9));
		testTree.add(70,50); 
		testTree.delete(3);
		testTree.add(60,55); 
		testTree.add(80,44); 
		System.out.println(testTree.delete(8));
		testTree.add(7 ,26);
		testTree.add(17 ,26);
		testTree.add(17 ,26);
		testTree.add(17 ,26);
		System.out.println(testTree.find(4));
		System.out.println(testTree.find(7));
		testTree.delete(4);
		testTree.delete(2);
		testTree.delete(7);
		System.out.println(testTree.find(17));
		System.out.println(testTree.find(70));
		System.out.println(testTree.toString());
		
		
	}
}










