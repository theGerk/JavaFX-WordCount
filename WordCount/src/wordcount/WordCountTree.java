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
 *
 * @author Benji
 */
public class WordCountTree {

	public int count;
	public WordCountTree[] children;

	public WordCountTree() {
		count = 0;
		children = new WordCountTree[26];
	}

	public WordCountTree(List<String> words) {
		count = 0;
		children = new WordCountTree[26];

		for (String s : words) {
			WordCountTree current = this;

			for (char c : s.toCharArray()) {
				int cValue = (int) (c - 'a');
				if (current.children[cValue] == null) {
					current.children[cValue] = new WordCountTree();
				}
				current = current.children[cValue];
			}

			current.count++;
		}

	}

	public static WordCountTree makeTree(List<String> words) {
		WordCountTree root = new WordCountTree();

		for (String s : words) {
			WordCountTree current = root;

			for (char c : s.toCharArray()) {
				int cValue = (int) (c - 'a');
				if (current.children[cValue] == null) {
					current.children[cValue] = new WordCountTree();
				}
				current = current.children[cValue];
			}

			current.count++;
		}

		return root;
	}

	/**
	 * Recursive, non-static converter
	 *
	 * @return
	 */
	public ArrayList<Pair<String, Integer>> toList() {
		return toList("");
	}

	public ArrayList<Pair<String, Integer>> toList(String prefix) {
		ArrayList<Pair<String, Integer>> output = new ArrayList<>();
		makeList(prefix, output);
		return output;
	}

	private void makeList(String prefix, ArrayList<Pair<String, Integer>> completed) {
		if (count != 0) {
			completed.add(new Pair<>(prefix, count));
		}
		char currentChar = 'a';
		for (WordCountTree c : children) {
			if (c != null) {
				c.makeList(prefix + currentChar, completed);
			}
			currentChar++;
		}
	}
}
