/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcount;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 * A 26-ary tree used to count words. It works by having a root which represents
 * the empty string "" The first child of that is "a", the second is "b", the
 * third is "c", and so on till "z". The first child "a", has 26 children which
 * are "aa", "ab", "ac", ... "az". Each of these has children and so on... In
 * each one of these nodes we can a count integer that can be incremented to say
 * how many of that word we have.
 *
 * @author Benji
 */
public class WordCountTree {

	//the number of whichever word this node represents there are.
	public int count;

	//an array of the 26 children, 0 = 'a', 1 = 'b' ... 25 = 'z'
	public WordCountTree[] children;

	/**
	 * Default constructor
	 */
	public WordCountTree() {
		count = 0;
		children = new WordCountTree[26];
	}

	/**
	 * Constructs taking in a list of words, this is only used to create the
	 * root of the tree.
	 *
	 * @param words a list of strings each representing a word, if there is any
	 * non-letter character in any of these Strings you will run into an error.
	 *
	 * @throws Exception if there is a non-letter character, it will throw an
	 * error
	 */
	public WordCountTree(List<String> words) throws Exception {
		count = 0;
		children = new WordCountTree[26];

		//iterates through all words
		for (String currentWord : words) {
			WordCountTree current = this;

			//puts each letter of the current word into the correct child node.
			for (char currentChar : currentWord.toCharArray()) {
				int cValue = (int) (currentChar - 'a');
				if (cValue >= 26 || cValue < 0) {
					throw new Exception("your word had a non-letter character in it, and is thus invalid.");
				}
				if (current.children[cValue] == null) {
					current.children[cValue] = new WordCountTree();
				}
				current = current.children[cValue];
			}

			current.count++;
		}

	}

	/**
	 * Member function that starts the recursive process that is makeList, See
	 * makeList for details.
	 *
	 * @return ArrayList of String, Integer pairs. Each pair's Integer
	 * represents how many times a string occurred.
	 */
	public ArrayList<Pair<String, Integer>> toList() {
		ArrayList<Pair<String, Integer>> output = new ArrayList<>();
		makeList("", output);
		return output;
	}

	/**
	 * Recursive function that turns tree into ArrayList of Word, Occurrence
	 * Pairs.
	 *
	 * @param currentWord the current word that has been constructed
	 * @param output a reference to the output
	 */
	private void makeList(String currentWord, ArrayList<Pair<String, Integer>> output) {
		if (count != 0) {
			output.add(new Pair<>(currentWord, count));
		}
		char currentChar = 'a';
		for (WordCountTree c : children) {
			if (c != null) {
				c.makeList(currentWord + currentChar, output);
			}
			currentChar++;
		}
	}
}
