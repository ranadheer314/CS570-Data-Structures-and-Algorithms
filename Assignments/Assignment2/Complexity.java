//Student Name: Randheer Vennapureddy
//CWID:10457392


package assignment_2;
import java.lang.Math;

public class Complexity {
	
	// Method which has of O(n^2) complexity
	public static void method1(int n) {
		int counter=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				counter++;
		System.out.println("Operation "+counter);
	}
	}
		System.out.println("the total number of operation for input "+n+" is: "+ counter);
	}
	
	//Method which has of O(n^3) Complexity
	public static void method2(int n) {
			int counter=0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++){
					for(int k=0;k<n;k++) {
						counter++;
							System.out.println("Operation "+counter);
							
					}
				}
			}
			System.out.println("the total number of operation for input "+n+" is: "+ counter);
			
		}
	//Method which has O(logn) Complexity
	public static void method3(int n) {
		int counter=0;
		for(int i=1;i<n;i*=2) {
			counter++;
			System.out.println("Operation "+counter);
			
		}
		System.out.println("the total number of operation for input "+n+" is: "+ counter);
	}
	
	//Method which has O(nlogn) Complexity
	public static void method4(int n) {
		int counter=0;
		for(int i=0;i<n;i++)
			for(int j=1;j<n;j*=2) {
				counter++;
				System.out.println("Operation "+ counter);	
			}
		System.out.println("the total number of operation for input "+n+" is: "+ counter);
	}
	
	//Method which has O(loglogn) Complexity
	public static void method5(int n) {
		
		int counter=0;
		for(int i=n;i>1;i=(int)Math.sqrt(i)) {
			if(i==n)
				continue;
			counter++;
				System.out.println("Operation "+counter);
				
			}
		System.out.println("the total number of operation for input "+n+" is: "+ counter);
	}
	
	//Method which has 2^n Complexity
	static int a=0;
	public static int method6(int n) {
		if(n==1) {
			a++;
			System.out.println(a);
			return 1;
		}	
		if(n==0) {
			a++;
			System.out.println(a);
			return 0;
		}
		a++;
		System.out.println(a);
		int b=method6(n-1)+method6(n-2);
		return b;
	}
	
	public static void main(String[] args) {
		
		Complexity.method1(4);
		
		Complexity.method2(8);
		Complexity.method3(1024);
		Complexity.method4(5);
		Complexity.method5(1024);
		Complexity.method6(4);
	}

}
