/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcount;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Benji
 */
public class Word {

	public static List<String> toWordList(List<String> strs) {
		List<String> output = new ArrayList();
		for (String s : strs) {
			output.addAll(toWordList(s));
		}
		return output;
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

	public static List<Pair<String, Integer>> toWordCount(String input) {
		return new WordCountTree(toWordList(input)).toList();
	}

	public static List<Pair<String, Integer>> toWordCount(List<String> input) {
		return new WordCountTree(toWordList(input)).toList();
	}
}
