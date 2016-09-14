/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcount;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 * A class containing only static functions to be used to do things with words.
 * Words are being defined as any string of letters (capital or lowercase) with
 * no other characters between them. Casing is ignored so "baCdk" is the same as
 * "bAcDK".
 *
 * @author Benji
 */
public final class Word {

	/**
	 * This is a private constructor so the class can not be instantiated, it
	 * serves no other function.
	 */
	private Word() {
		//does nothing
	}

	/**
	 *
	 * @param strs a list of Strings, each on of which is going to be split into
	 * words to be returned.
	 *
	 * @return A List of words, each word is a String
	 */
	public static List<String> toWordList(List<String> strs) {
		List<String> output = new ArrayList();
		for (String s : strs) {
			output.addAll(toWordList(s));
		}
		return output;
	}

	/**
	 * Turns a string into a list of words
	 *
	 * @param str the String to be split up into words
	 * @return the list of words
	 */
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

	/**
	 * Counts the number of each word there is in a string.
	 *
	 * @param input a string to have each word within it counted.
	 *
	 * @return A List of String, Integer pairs. The String representing a word
	 * and the Integer representing the number of time the corresponding String
	 * occurs in the input.
	 */
	public static List<Pair<String, Integer>> toWordCount(String input) {
		try {
			return new WordCountTree(toWordList(input)).toList();
		} catch (Exception ex) {
			Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	/**
	 * Counts the number of each word there is in each string in a list.
	 *
	 * @param input a list of strings to have each word within each string
	 * within it counted.
	 *
	 * @return A List of String, Integer pairs. The String representing a word
	 * and the Integer representing the number of time the corresponding String
	 * occurs in the input.
	 */
	public static List<Pair<String, Integer>> toWordCount(List<String> input) {
		try {
			return new WordCountTree(toWordList(input)).toList();
		} catch (Exception ex) {
			Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
}
