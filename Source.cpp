#include <stdio.h>
#include <math.h>
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

void printPowerSet(char *set, int set_size)
{
	ofstream power("powerset.txt");
	/*set_size of power set of a set with set_size
	n is (2**n -1)*/
	unsigned int pow_set_size = pow(2, set_size);
	int counter, j;

	/*Run from counter 000..0 to 111..1*/
	for (counter = 0; counter < pow_set_size; counter++)
	{
		for (j = 0; j < set_size; j++)
		{
			/* Check if jth bit in the counter is set
			If set then pront jth element from set */
			if (counter & (1 << j))
				power << set[j];
		}
		power << endl;
	}
}


bool validRange(string set) {
	int length = set.length(); 
	//valid is more than 2 nodes and less than 9 nodes
	if (length < 2 )
		return false;
	else
		return true;
}

bool decreasing(string set) {
	int length = set.length(); 

	bool valid = true;
	for (int i = 0; i < length - 1; i++) {
		if (set[i] > set[i + 1]) {
			valid = false;
			break;
		}
	}

	return valid;

}

bool startingZero(string set) {
	if (set[0] == '0')
		return true;
	else return false;

}

int pathLength(int aj[][9], string path) {
	int totalPath=0; 
	
	for (int i = 0; i < path.length() - 1; i++) {
		
		int j = (int)path[i] - 48;
		int k = (int)path[i + 1] - 48 - 1;
		//cout << aj[j][k] << endl;
		totalPath += aj[j][k];
	}
	return totalPath;
}

bool checkSimilar(string one, string two, string three) {
	bool similar = false; 
	int i, j;

	//check one two
	for (i = 1; i < one.length(); i++) {
		for (j = 1; j < two.length(); j++) {
			if (one[i] == two[j])
				similar = true; 
		}
	}

	//check two three
	for (i = 1; i < two.length(); i++) {
		for (j = 1; j < three.length(); j++) {
			if (two[i] == three[j])
				similar = true;
		}
	}

	//check one three
	for (i = 1; i < one.length(); i++) {
		for (j = 1; j < three.length(); j++) {
			if (one[i] == three[j])
				similar = true;
		}
	}
	
	return similar; 
}

/*Driver progr am to test printPowerSet*/
int main()
{
	
	char set[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8' };
	printPowerSet(set, 9);


	//Step 1-------------------power sets of all valid paths------------------
	//open file read values 
	// reading a text file
	string line;
	ifstream myfile("powerset.txt");
	ofstream file2("validSets.txt");
	int validNum = 0;
	if (myfile.is_open() && file2.is_open())
	{
		while (getline(myfile, line))
		{
			if (validRange(line) && startingZero(line) && decreasing(line)) {
				file2 << line << endl;
				validNum++;
			}
		}
		myfile.close();
		file2.close();
	}
	else cout << "Unable to open file";

	cout << validNum << endl;


	//Step 2----------Combination sets of 3-----------------
	//get all the valid sets in a string. 
	ifstream getPaths("validSets.txt");

	string validSets[300]; //String to keep all the valid paths
	int lengths[300];	//length array to keep the path of validSets[i];

	//make the ajacency matrix 
	int ajMatrix[8][9] = {

		{ 1, 3, 2, 1, 2, 1, 2, 3 },
		{ NULL, 4, 1, 0, 1, 0, 1, 2 },
		{ NULL, NULL, 3, 4, 3, 4, 3, 2 },
		{ NULL, NULL, NULL, 1, 0, 1, 0, 3 },
		{ NULL, NULL, NULL, NULL, 1, 0, 1, 2 },
		{ NULL, NULL, NULL, NULL, NULL, 1, 0, 3 },
		{ NULL, NULL, NULL, NULL, NULL, NULL, 1, 2 },
		{ NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3 }
	};


	int i = 0;
	if (getPaths.is_open())
	{
		while (getline(getPaths, line))
		{
			validSets[i] = line;
			i++;

		}
		getPaths.close(); 
	}
	else cout << "Unable to open file";
	
	//get path length for each path 
	for (int i = 0; i < validNum; i++) {
		lengths[i] = pathLength(ajMatrix, validSets[i]);
	}
	

	ofstream sets("combinations.txt");
	
	//find all combinations that have all nodes
	int n = 0, len = 0, p1 = -1, p2 = -1, p3 = -1, bestPath = 999; 
	for (int i = 0; i < validNum; i++) {
		for (int j = 0; j < validNum; j++) {
			for (int k = 0; k < validNum; k++) {
				n = validSets[i].length() + validSets[j].length() + validSets[k].length();
				len = lengths[i] + lengths[j] + lengths[k]; 
				if (!checkSimilar(validSets[i], validSets[j], validSets[k]) && n == 11) {
					sets << "Path 1: " << validSets[i] << " Path 2: " << validSets[j] << " Path 3: " << validSets[k] << " Total: " << len <<  endl;

					if (bestPath > len) {
						sets << "^^^Best Path!!!!" << endl;
						bestPath = len; 
						p1 = i; 
						p2 = j; 
						p3 = k; 
					}
				}
			}
		}
	}

	cout << "\n" << "best path "  << validSets[p1] << " two " << validSets[p2] << " three " << validSets[p3] << " total " << bestPath << endl;


	getchar();
	return 0;
}
