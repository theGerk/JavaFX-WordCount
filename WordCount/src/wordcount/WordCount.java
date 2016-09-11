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
public class WordCount {

	private static WordCountTree makeTree(List<String> input) {
		WordCountTree root = new WordCountTree();

		for (String s : input) {
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

	public static List<String> toWordList(String str) {
		str = str.toLowerCase();
		StringBuilder wordBuilder = new StringBuilder();
		List<String> output = new ArrayList<>();
		for (char c : str.toCharArray()) {
			if (c >= 'a' && c <= 'z') {
				wordBuilder.append(c);
			} else if (wordBuilder.length() != 0) {
				output.add(wordBuilder.toString());
				wordBuilder = new StringBuilder();
			}
		}
		if (wordBuilder.length() != 0) {
			output.add(wordBuilder.toString());
		}
		return output;
	}
}
