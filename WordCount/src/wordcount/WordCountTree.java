/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcount;

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
}
