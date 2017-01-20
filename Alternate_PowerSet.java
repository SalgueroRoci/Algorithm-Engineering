//*******************************************************************
//  Programmer(s): Rocio Salguero
//				   
//  Class: CPSC 335-02
// 	Project 2 - Sub sequence Powerset 
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


public class Alternate_PowerSet
{
	 
	public static void print_sequence(float[] mainArray) {
		for(int i=0; i< mainArray.length ; i++)
			System.out.printf("%.0f ", mainArray[i]);
		System.out.print("\n");
		
	}
	
	public static int powerset(float[] mainArray, float[] subSqofA) {
		int index = 0, maxSq = 0, n = mainArray.length; 
		
		float [] temp = new float[n]; 
		
		temp[index] = mainArray[0]; 
		
		for(int j = 0; j < n; j++, index =0) {
			for(int k = 1; k < n; k++) {
				if(temp[index] <= mainArray[k]) {
					index++; 
					temp[index] = mainArray[k]; 
				}
			}
			
			if (maxSq < (index+1)) {
				//big O of aray copy is temp.length = n
				System.arraycopy(temp, 0, subSqofA, 0, temp.length); 
				maxSq = (index+1); 
			}
			
		}
		
		return maxSq; 
	}

	
	public static void main(String[] args) {
		float[] A, R;
		int n,i;
		
		// display the header                                                        
		System.out.print("CPSC 335-x - Programming Assignment #2\n"
	  		+ "Longest non-decreasing subsequence problem, powerset algorithm\n"
	  		+ "Enter the number of elements in the sequence: ");
		// read the number of elements  
		Scanner read = new Scanner(System.in);
		n = read.nextInt(); 
	  
		// allocate space for the input sequence and array R                          	
		A = new float[n];
		R = new float[n];
		  
		// read the sequence  
		System.out.println("Enter the elements in the sequence: ");
	    for(i=0; i < n; i++)
	    	A[i] = read.nextInt();
	    
	    // print the sequence                                                        
		System.out.println("Input sequence");
		print_sequence(A);
		
		//Start timer for algorithm 
		long start_time = System.nanoTime();
		
		//Allocate bestSubSq and max of number in subSq
		int maxSubSq;
		float [] bestSubSq = new float[n];
		
		//Power set returns number of longest subset and inserts to bestSubSq
		maxSubSq = powerset(A, bestSubSq);
		
		//Copy bestsubSq to R
		R = new float[maxSubSq];
		for(i = 0;i < maxSubSq; i++ )
			R[i] = bestSubSq[i];
		
		//'Deallocate' bestSubSq aka throw to garbage
		bestSubSq = null;
		
		//End time and calculate time passed in milliseconds
		long end_time = System.nanoTime();
		double finalTime = (end_time - start_time)/1e6;
		
		// display the output
		System.out.println("The longest non-decreasing subsequence has length " +  maxSubSq +
				"The longest non-decreasing subsequence is"); 
		print_sequence(R);
		
		// print the elapsed time in seconds and fractions of seconds                 
		System.out.println("elapsed time: " + finalTime + " seconds");
		
		//deallocate arrays 
		A = null;
		R = null; 
	} // end main
	
} //end class 
    