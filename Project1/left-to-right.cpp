/*
** Assignment 1: Alternating disks problem, left-to-right algorithm
** Rocio Salguero
** Given 2n alternating disks disks (light-dark), starting with light
** the program reads the number of single color disks (light or dark),
** arranges the disks in the correct order and outputs the number of swaps
**
** INPUT: an even positive integer n and a list of 2n disks of alternating
** colors light-dark, starting with light
** OUTPUT: a list of 2n disks, the first n disks are dark and the next n
** disks are light, and an integer m representing the number of moves necessary
** to move the dark ones before the light ones
*/

#include <iostream>
#include <iomanip>
#include <cstdlib>

using namespace std;

void print_disks(int n, int *disks);
void swap(int* a, int* b);

int main() {
	//n is the number one one color disks, moves is the amount of swaps made
	int n, moves = 0;
	int *disks;

	// display the header
	cout << endl << "CPSC 335-02 - Programming Assignment #1\n" << 
			"The alternating disks problem: left-to-right algorithm\n" << 
			"Enter an even number of single color disks (light or dark)" << endl;

	// read the number of disks
	cin >> n;

	// allocate space for the disks
	disks = new int[2 * n];

	// set the initial configurations for the disks to alternate
	for (int i = 0; i < n; i++) {
		disks[2 * i] = 1;
		disks[2 * i + 1] = 0;
	}

	// print the initial configuration of the list of disks
	cout << "Initial configuration" << endl;
	print_disks(n, disks);

	// loop to push dark ones before light ones 
	for (int k = 0; k < n; k++) {
		for (int j = k; j < 2 * n - 1; j++) {
			if (disks[j] > disks[j + 1]) {
				swap(disks[j], disks[j + 1]);
				moves++;
			}
		}
	}



	// after shuffling them                                                                                                                                                                                
	cout << "\nAfter moving darker ones to the left" << endl;
	print_disks(n, disks);
	// print the total number of moves                                                                                                                                                                     
	cout << "\nNumber of swaps is " << moves << endl;


	// de-allocate the dynamic memory space                                                                                                                                                                
	delete[] disks;
	return EXIT_SUCCESS;
}

void swap(int* a, int* b) {
	int* temp = a;
	a = b;
	b = temp;
}


// function to print the list of disks, given the number of single color disks 
// and the actual list                                                         
// n represents the number of single color disks                               
// disks represents the list of disks (index 0 being the first disk) where    
// 0 = a dark color disk                                                    
// 1 = a light color disk 
void print_disks(int n, int *disks) {
	for (int i = 0; i < 2 * n; i++) {
		if (disks[i] == 1)
			cout << "l";
		else if (disks[i] == 0)
			cout << "d";
	}
}