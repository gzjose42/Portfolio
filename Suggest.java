/*
 * Program: Suggest.java
 * Author: Jose Gonzalez
 * 
 * Purpose: This program draws inspiration from the suggestion feature in 
 * most apps and websites such as Spotify, Apple Music, YouTube, and so on.
 * The user types in a letter and the program creates a list of the 20 best
 * songs to play next. The songs are selected based on how often they appear
 * in a list together.
 * 
 * How It Works: The program generates 500 different lists that contains 
 * different letters and each list varies in size. Then it creates a matrix
 * based graph where each edge is weighted based on how often the letters 
 * appear together in a list. Once the matrix has all of the proper
 * information in it, the user is prompted to type in a letter. The letter
 * is then used as a starting point where the program checks what letter
 * has the highest score to the starting point then it uses that letter as
 * the new starting point. It repeats this until the list has 20 elements in
 * it without any repeats. The list is then returned and the program repeats
 * until the user types "0".
 * 
 * Future Updates: 
 * 		
 * 		* In reality not every song would appear together that often so
 * 		  a linked list based graph would save more space.
 * 		
 * 		* making this program Object Oriented would allow for easier updates
 * 		  in the algorithm so that the likeness between objects can change 
 *        to be based on something else.
 *        
 *      * overall this could be more efficient in some areas
 * 		   
 * 
 */


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Suggest {
	
	public static void main(String[] args) {
		Random r = new Random();
		String[] alph = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
		int[][] matrix = initMatrix();	

		// numRep shows what index a certain letter is 
		TreeMap<String, Integer> numRep = new TreeMap<String, Integer>();
		for(int i = 0; i < alph.length; i++) {
			numRep.put(alph[i], i);
		}
		// playlists generates a bunch of random lists of letters to
		// simmulate user created playlists
		TreeMap<Integer, ArrayList<String>> playlists = createPlaylists(r,alph);
		fillMatrix(playlists,alph, numRep, matrix);
		
		Scanner user = new Scanner(System.in);
		String input = user.nextLine();
	
		// continues to find suggestions until the user types "0"
		while(!input.equals("")) {
			ArrayList<String>  a = traverse(input, matrix, numRep, alph);
			System.out.println(a);
			input = user.nextLine();
		}
		
		

	}
	


	// this method takes the user's input and 
	public static ArrayList<String> traverse(String curr, int[][] matrix, TreeMap<String, Integer> numRep, String[] alph) {
		
		ArrayList<String> suggestions = new ArrayList<String>();
		suggestions.add(curr); //add starting letter to avoid repeat
		int newCurr = 1;
		while (suggestions.size() < 21) {
		
			int[] options = matrix[numRep.get(curr)];
			int best = 0;
			int bestAmount = 0;
	

			for (int i = 0; i < options.length; i++) {
				// determines if the current likness is the best likeness
				boolean newBest = bestAmount < matrix[numRep.get(curr)][i];
				// makes sure there isn't a repeat in the list
				boolean notInList = !suggestions.contains(alph[i]);
			
				if(newBest && notInList) {
					// updates the best letter and amount
					best = i;
					bestAmount = matrix[numRep.get(curr)][best]; 
				}
			}
			// adds the best letter and uses it as the new current
			suggestions.add(alph[best]);
			curr = suggestions.get(newCurr);
			newCurr++;
		}
		suggestions.remove(0); // removes the starting letter
		return suggestions;
		
	}



	public static void fillMatrix(TreeMap<Integer, ArrayList<String>> playlists, String[] alph,
			TreeMap<String, Integer> numRep, int[][] matrix) {
		
		for(int i = 0; i < alph.length; i++) {
			
			// goes through each playlist
			for(int playNum = 0; playNum < playlists.size(); playNum++) {
				ArrayList<String> curr = playlists.get(playNum);
				
				// looks if the letter is in the list
				if(curr.contains(alph[i])) {
					
					// goes through the playlist and adds a point
					// if the letter is in the playlist with the current
					// letter
					for(int num = 0; num < curr.size(); num++) {
						
						if(!curr.get(num).equals(alph[i])) {
							matrix[i][numRep.get(curr.get(num))]++;
						}
					}
					
				}
			}
		}
		
	}



	public static TreeMap<Integer, ArrayList<String>> createPlaylists(Random r, String[] alph) {
		TreeMap<Integer, ArrayList<String>> playlists = new TreeMap<Integer, ArrayList<String>>();
		for(int i = 0; i < 500; i++) { // switch to 200
			int len = r.nextInt(20); // random length
			ArrayList<String> songList = new ArrayList<String>();
			for(int j = 0; j < len; j++) {
				int numSelect = r.nextInt(alph.length);
				if (!songList.contains(alph[numSelect])) { //avoids repeats
					songList.add(alph[numSelect]);
				}
			}
			playlists.put(i,songList);
			
		}
		
		return playlists;
	}


	// prints out the matrix values
	private static void printMatrix(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				//
				System.out.print(matrix[i][j]);

				
			}
			System.out.println();

		}		
	}

	
	// creates the matric based on the sample size ( the alphabet) then
	// sets all of the values to 0 to start
	public static int[][] initMatrix(){
		
		String alph = "abcdefghijklmnopqrstuvwxyz";
		int[][] matrix = new int[alph.length()][];
		for(int i = 0; i < matrix.length; i++) {
			int[] row = new int[alph.length()];
			for(int j = 0; j < matrix.length; j++) {
				row[j] = 0;
			}
			matrix[i] = row;
		}
		
		return matrix;
	}

}
