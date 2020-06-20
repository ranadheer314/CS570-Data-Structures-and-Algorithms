//Name:Randheer Vennapureddy
//CWID:10457392
package linkedlistAssg;

public class IDLListTest {

	public static void main(String[] args) {
		IDLList<Integer> list = new IDLList<Integer>();
		list.add(87);
		list.append(31);
		list.add(0,12);//adding the element at given index 
		list.append(24);//adding the element at the end of the DLL
		list.add(36);//adding the element at the head of the DLL
		list.add(1, 48);//adding the element at given index
		list.add(60);//adding the element at the head of the DLL
		
		try 
		{
			list.add(78,589);//this will throw the exception because the given index is out of range
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("given Index is out of bounds");
		}
		
		System.out.println(list.toString());
		list.append(72);//adding the element at the end of the DLL
		
		try {
			list.add(-5,35);//this will throw the exception because the given index is in negative
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("given index is out of bounds");
		}
		
		System.out.println(list.toString());
		
		list.add(0, 120);//adding the element at given index
		list.add(3, 240);//adding the element at given index
		list.append(600);//adding the element at the end
		list.add(2, 150);//adding the element at given index
		list.add(4, 900);//adding the element at given index
		System.out.println(list.toString());
		
		System.out.println(list.remove());
		System.out.println("After removing the element at the head");
		System.out.println(list.toString());
		
		System.out.println(list.remove(72)); // Removes 1st occurrance and returns true if not present returns false
		
		System.out.println(list.remove(200));
		System.out.println(list.toString());
		try {
			System.out.println(list.get(-2)); //this will throw an exception because of the negative index
		}
		catch(ArrayIndexOutOfBoundsException e) 
		{
			System.out.println("given Index out of bounds");
		}
		
		System.out.println("size of the DLLlist: "+list.size());
		
		System.out.println("Element at head is "+list.getHead());
	    
	    System.out.println("Element at tail is "+list.getLast());
	  
		System.out.println("Size of DLLList is "+list.size());
		System.out.println(list.toString());
		
		System.out.println("Element at the index 3 is "+ list.get(3));
		System.out.println(list.remove());//removes the element and returns the element at the end
		
		System.out.println(list.remove());
		System.out.println(list.toString());
		
		System.out.println(list.removeLast());
		
		System.out.println("After removing the element at the end of the end: ");
		System.out.println(list.toString());
	
		System.out.println(list.removeAt(2));
		System.out.println("After removing the element at the index 2: ");
		System.out.println(list.toString());

	}

}

