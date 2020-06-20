/*		Student name:RANDHEER VENNAPUREDDY
  		        CWID:10457392
*/


import java.util.Arrays;


public class BinaryNumber 
{
	private int data[];
	private boolean overflow;
	
	//constructor with length as parameter
	public BinaryNumber(int length) 
	{
		if(length<=0)
			throw new IllegalArgumentException("entered invalid length ");
		int[] data=new int[length];
		this.data=data;
	
	}
	
	//constructor with string as parameter
	public BinaryNumber(String str)
	{
		
		int len=str.length();
		int[] data=new int[len];
		for(int i2=0;i2<len;i2++) {
			data[i2]=Character.getNumericValue(str.charAt(i2));
		}
		this.data=data;

	}

	//method to obtain the length of the Binary Number
	public int getLength() {
		return data.length;
	}
	
	
	//Method to get the digit at the specified index
	public int getDigit(int index) {
		if(index<0 && index>=data.length)
			throw new IllegalArgumentException("index is out of range");
		
		return data[index];
	}
	
	
	//shifting the Binary Number by specified amount to the right
	public void shiftR(int amount)
	{
		if(amount<0)
			throw new IllegalArgumentException("entered amount is invalid");
		int temp=this.data.length;
		int b[]=new int[temp];
		
		for(int i3=0;i3<temp;i3++) {
			b[i3]=this.data[i3];
		}
		
		int[] data=Arrays.copyOf(b, temp+amount);
		
		for(int i=0;i<amount;i++)
			data[i]=0;
		
		for(int i=amount;i<temp+amount;i++)
			data[i]=b[i-amount];
		
		this.data = data;

	}
	
	//Addition of two Binary Numbers
	
	public void add(BinaryNumber aBinaryNumber) {
		if(aBinaryNumber.data.length!=data.length) {
			throw new IllegalArgumentException("length must be same");	
		}
		
		int[] val=new int[data.length];
		boolean flag=false;
		for(int i=0;i<data.length;i++) {
			val[i]=data[i]+aBinaryNumber.data[i];

			if(val[i]==0 && flag==true) {
				val[i]=1;
				flag=false;
			}
			if(val[i]==1 && flag==false) {
				val[i]=1;
			}
			if(val[i]==1 && flag==true) {
				val[i]=0;
			}
			if((val[i]==2) && (flag==false)) {
				val[i]=0;
				flag=true;
			}
			if((val[i]==2) && (flag==true)) {
				val[i]=1;
				flag=true;
			}
		}
		
		if(flag==false) {
			int[] data1=new int[data.length];
			for(int p=0;p<data.length;p++) {
				data1[p]=val[p];
			}
			this.data=data1;
			
		}
		
		if(flag==true) {
			int[] data1=new int[data.length+1];
			for(int j=0;j<data.length;j++) {
				data1[j]=val[j];
			}
			data1[data.length]=1;
			this.data=data1;
			
			if(flag==true)
				overflow=true;
			else
				overflow=false;
		}
			
	} 
	
	//Method to get the string 
	public String toString() {
		String st="";
		if(overflow==true) {
			return "Overflow";
		}
		else {
			for(int it=0;it<data.length;it++)
				st= st+data[it];
		} 
		return st;
	}
	
	//method to get the decimal value of the binary number
	
	public int toDecimal() {
		
		double val=0;
		for(int i=0;i<data.length;i++)
			val=val+data[i]*Math.pow(2, i);   
		return (int)val;
	}
	
	//method to clear the overflow
	public void clearOverflow() {
		this.overflow=false;
	}
	
	

		

	public static void main(String[] args) {
		BinaryNumber bn1=new BinaryNumber(6);
		
		BinaryNumber bn2=new BinaryNumber("11011");
		System.out.println("length:"+ bn2.getLength());
		System.out.println("digit at specified index:"+ bn2.getDigit(3));
		bn2.shiftR(2);
		
		BinaryNumber bn3=new BinaryNumber("1100000");		
		bn2.add(bn3);
		
		System.out.println(bn2.toString());
		System.out.println("decimal value:" + bn2.toDecimal());
		System.out.println(bn2.overflow);
		bn2.clearOverflow();
		System.out.println(bn2.overflow);
		System.out.println(bn2.getDigit(4));		

	}

}
