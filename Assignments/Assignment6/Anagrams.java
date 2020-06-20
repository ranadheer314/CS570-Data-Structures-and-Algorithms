//Name:Randheer Vennapureddy
//CWID:10457392
//Homework Assignment 6

package Anagrams;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Anagrams {
	final Integer primes[];
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	
	public Anagrams() { //Constructor
		letterTable=new HashMap<Character,Integer>();
		primes= new Integer[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101}; 
		anagramTable= new HashMap<Long,ArrayList<String>>();
		buildLetterTable();
	}
	
	private void buildLetterTable() {//Maps the characters to the represetive prime value
		Character temp[]= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		for(int i=0;i<26;i++) {
			this.letterTable.put(temp[i],primes[i]);
		}
	}
	
	private Long myHashCode(String s) {//Returns the Hashcode of the string
		Long key=1L;
		s=s.toLowerCase(); //converting into lowercase because we are mapping only lowercase characters
		for(int i=0;i<s.length();i++) {
			key*=letterTable.get(s.charAt(i));
		}
		return key;
	}
	
	private void addWord(String s) {// maps the string to its respective ArrayList using its HashCode
			Long hashKey=myHashCode(s);
			ArrayList<String> arr=new ArrayList<String>();
			if(!anagramTable.containsKey(hashKey)) 	{
				arr.add(s);
				anagramTable.put(hashKey,arr);}
			else {
				anagramTable.get(hashKey).add(s);	
			}
			
	}
	
	public void processFile(String s) throws IOException{
		 FileInputStream fstream = new FileInputStream(s); 
		 BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); 
		 String strLine;
		 while ((strLine = br.readLine()) != null) 
		 { 
			 this.addWord(strLine); 
		}
		 br.close(); 
	}
	
	private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries(){//returns the ArrayList which has Max Entries i.e maximum number of anagrams
		int length=0;
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxList=new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		Iterator<Entry<Long,ArrayList<String>>> i=anagramTable.entrySet().iterator(); //Iterator 
		while(i.hasNext()) {
			Entry<Long,ArrayList<String>> temp=i.next();
			if(temp.getValue().size()>length) {
				length=temp.getValue().size();		//Updates the maximum size of the ArrayList
				maxList.clear();
				maxList.add(temp); 				//updates the Max Entries ArrayList
			}
			else if(temp.getValue().size()==length) {
			maxList.add(temp);
			}
		}
		if(maxList.get(0).getValue().size()==0) {
			throw new  NullPointerException("No Elements in the hashTable");
		}
		return maxList;	
	}

	public static void main(String[] args) {
		Anagrams a=new Anagrams();
		System.out.println(a.myHashCode("alerts"));
		System.out.print(a.letterTable.toString());
		final long startTime = System.nanoTime(); 
		try {
			a.processFile("words_alpha.txt"); 
			} 
		catch (IOException e1){ 
				e1.printStackTrace(); 
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries(); 
		final long estimatedTime = System.nanoTime() - startTime; 
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds); 
		System.out.println("key of max anagrams: "+ maxEntries.get(0).getKey()); 
		System.out.println("List of max anagrams: "+ maxEntries.get(0).getValue());
		System.out.println("Length of list of max anagrams: "+ maxEntries.get(0).getValue().size()); 
		

	}

}
