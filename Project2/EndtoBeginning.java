//*******************************************************************
//  Programmer(s): Rocio Salguero
//				   
//  Class: CPSC 335-02
// 	Project 2 - Sub sequence End-to-Beginning 
// 	Created: 09/30/16
// 	Due Date: 10/10/2016
//	Description: Given a sequence of elements the program finds a subsequence of it in which the subsequence's
// elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible.                         
// The program reads the number of elements in the sequence, then the elements and outputs the sorted
// sequence and the running time.    
//
// INPUT: a positive integer n and a list of n elements                         
// OUTPUT: a longest non-decreasing subsequence of the initial sequence    
//*******************************************************************

import java.util.*;


public class EndtoBeginning
{
	 
	// function to print a sequence, given the number of elements and           	
	// the actual sequence stored as an array                                   	
	// n represents the number of elements in the sequence                      	
	// seq represents the actual sequence                       
	public static void print_sequence(float[] mainArray) {
		for(int i=0; i< mainArray.length ; i++)
			System.out.printf("%.0f ", mainArray[i]);
		System.out.print("\n");
		
	}
	
	public static void main(String[] args) {
		//Display header 
		System.out.println("CPSC 335-02 - Programming Assignment #2 \n" +
						"Longest non-decreasing subsequence problem, end-to-beginning algorithm\n");
		
		//Populate array 
		SubSq newS = new SubSq(); 
		
		// print the sequence                                                        
		System.out.print("Input sequence: \n");
		print_sequence(newS.getMainArray());
		
		String input; 
		Scanner in = new Scanner(System.in);
		System.out.print("Choose one:\n\t(A) non-decreasing subseqence\n\t(B) non-increasing subsequence\nChoice: ");
		input = in.nextLine();
		
		

		long start_time = 0, end_time = 0;
		//Calculate the longest Sub sequence of non-decreasing order
		if(input.charAt(0) == 'a' || input.charAt(0) == 'A') {
			// Start time of the algorithm           	
			start_time = System.nanoTime();
			
			newS.nonDecSq();
			
			//End time and calculate time passed in milliseconds
			end_time = System.nanoTime();
			
			// write the output                                                      
			System.out.println("The longest non-decreasing subsequence has length: " + 
					newS.getLongSubSize() + "\nThe longest non-decreasing subsequence is");
			print_sequence(newS.getLongSubSq());
		}
		else if(input.charAt(0) == 'b' || input.charAt(0) == 'B') {
			// Start time of the algorithm           	
			start_time = System.nanoTime();
			
			newS.nonIncSq();
			
			//End time and calculate time passed in milliseconds
			end_time = System.nanoTime();
			
			// write the output                                                      
			System.out.println("The longest non-increasing subsequence has length: " + 
					newS.getLongSubSize() + "\nThe longest non-increasing subsequence is");
			print_sequence(newS.getLongSubSq());
		}
		else {
			System.out.println("Choice not valid. Program ending.");
			System.exit(0); 			
		}
		
		double finalTime = (end_time - start_time)/1e6;
		 
		//print the elapsed time in seconds and fractions of seconds
		System.out.println("elapsed time: " + finalTime + " seconds");
		
		//de-allocate the dynamic memory space                                   	
		newS = null;

	} // end main
	
} //end class 
   

//Class that handles creating the longest sub string  of non-decreasing order 
class SubSq {
	//Declare variables 
	private float [] A, R;
	int[] H; 
	private int n, maxSubSq; 
	private Scanner read = new Scanner(System.in); 
	
	
	//Default constructor asks user for main Array A
	SubSq() {
		 //populate array
		System.out.print("Enter the number of elements in the sequence: ");
			
		// read the number of elements  
		n = read.nextInt(); 
			
		// allocate space for the input sequence and array H                          
		A = new float[n];
		  
		// read the sequence                                                     
		System.out.print("Enter the elements in the sequence: ");
		for(int i=0; i < n; i++)
			A[i] = read.nextInt(); 	
	}
	
	SubSq(float[] B) {
		System.arraycopy(B, 0, A, 0, B.length);
		n = B.length;
	}
	
	//getters 
	public float[] getMainArray() {
		return A; 		
	}
	public float[] getLongSubSq() {
		return R; 
	}
	public int getMainArraySize() {
		return n;
	}
	public int getLongSubSize() {
		return maxSubSq;
	}
	
	//Find the longest nondecreasing subsequence using end to beginning algorithm 
	public void nonDecSq() {
		H = new int[n];
		 
		// loop to populate the array H with 0 values                             	
		for(int i=0; i < n; i++)
			H[i] = 0;
						
		// loop to calculate the values of array H                                	
		for (int index = n-2;  index >= 0; index--) {
			for (int counter = index + 1; counter < n ; counter++)
				if (A[index] <= A[counter] && H[index] <= H[counter])
					H[index] = H[counter] + 1; 
		}
		  
		
		  
		// calculate in max the length of the longest subsequence by adding 1     	
		// to the maximum value in H	
		maxSubSq = H[0];
		for(int i=1; i< n; i++)
			if (maxSubSq < H[i])
				maxSubSq = H[i];
		maxSubSq++;
		  
		  
		// allocate space for the subsequence R                                   	
		R = new float[maxSubSq];
		  
		//add elements to R by whose H's values are in decreasing order, starting	
		//with maxSubSq-1                                                                
		//store in index the H values sought                                     	
		int value = maxSubSq - 1;
		// store in indexR the index of the element appended to R                      	
		int indexR = 0;
		for(int i=0; i< n; i++) {
			if (H[i] == value) {
		  		R[indexR] = A[i];
		  		value--; 
		  		indexR++; 
			}
		}
			
	}//end of non-decreasing sub sequence
	
	//find the longest non-increasing subsequence using end to beginning algorithm
	public void nonIncSq() {
		H = new int[n];
		 
		// loop to populate the array H with 0 values                             	
		for(int i=0; i < n; i++)
			H[i] = 0;
						
		// loop to calculate the values of array H                                	
		for (int index = n-2;  index >= 0; index--) {
			for (int counter = index + 1; counter < n ; counter++)
				if (A[index] >= A[counter] && H[index] <= H[counter])
					H[index] = H[counter] + 1; 
		}
		  
		// calculate in max the length of the longest subsequence by adding 1     	
		// to the maximum value in H	
		maxSubSq = H[0];
		for(int i=1; i< n; i++)
			if (maxSubSq < H[i])
				maxSubSq = H[i];
		maxSubSq++;
		  
		// allocate space for the subsequence R                                   	
		R = new float[maxSubSq];
		  
		//add elements to R by whose H's values are in decreasing order, starting	
		//with maxSubSq-1                                                                
		//store in index the H values sought                                     	
		int value = maxSubSq - 1;
		// store in indexR the index of the element appended to R                      	
		int indexR = 0;
		for(int i=0; i< n; i++) {
			if (H[i] == value) {
		  		R[indexR] = A[i];
		  		value--; 
		  		indexR++; 
			}
		}
		
	}//end non increasing longest subsequence 
	
}//end class SubSq