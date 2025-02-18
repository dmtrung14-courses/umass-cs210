/*
 * Copyright 2021 Marc Liberatore.
 */

package similarity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sets.SetUtilities;

import org.junit.Test.None;

public class SimilarityUtilities {
	/**
	 * Returns the set of non-empty lines contained in a text, trimmed of
	 * leading and trailing whitespace.
	 * 
	 * @param text
	 * @return the trimmed set of lines
	 */
	public static Set<String> trimmedLines(String text) {
		String[] lines = text.split("\n");
		Set<String> results = new HashSet<>();
		for (String i: lines){
			if (!i.isBlank()){
				results.add(i.strip());
			}			
		}
		return results;
	}

	/**
	 * Returns a list of words in the text, in the order they appeared in the text, 
	 * converted to lowercase.
	 * 
	 * Words are defined as a contiguous, non-empty sequence of letters and numbers.
	 *
	 * @param text
	 * @return a list of lowercase words
	 */
	public static List<String> asLowercaseWords(String text) {
		String[] words = text.split("\\W+");
		List<String> results = new ArrayList<>();
		for (String i: words){
			if (!i.isBlank()){
				results.add(i.toLowerCase().strip());
			}
			
		}
		return results;
	}

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line
	 * set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines.
	 * 
	 * @param text1
	 *            a text
	 * @param text2
	 *            another text
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2) {
		return SetUtilities.jaccardIndex(trimmedLines(text1), trimmedLines(text2));
	}

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line
	 * set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines, less the set of trimmed lines from the templateText. Removes
	 * the template text from consideration after trimming lines, not before.
	 * 
	 * @param text1
	 *            a text
	 * @param text2
	 *            another text
	 * @param templateText
	 *            a template, representing things the two texts have in common
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2, String templateText) {
		return SetUtilities.jaccardIndex(SetUtilities.setDifference(trimmedLines(text1), trimmedLines(templateText)),SetUtilities.setDifference(trimmedLines(text2), trimmedLines(templateText)));
	}

	/**
	 * Returns a set of strings representing the shingling of the given length
	 * of a list of words.
	 * 
	 * A shingling of length k of a list of words is the set of all k-shingles
	 * of that list.
	 * 
	 * A k-shingle is the concatenation of k adjacent words.
	 * 
	 * For example, a 3-shingle of the list: ["a" "very" "fine" "young" "man"
	 * "I" "know"] is the set: {"averyfine" "veryfineyoung" "fineyoungman"
	 * "youngmanI" "manIknow"}.
	 * 
	 * @param words
	 * @param shingleLength
	 * @return 
	 */
	public static Set<String> shingle(List<String> words, int shingleLength) {
		Set<String> results = new HashSet<String>();
		for (int i = 0; i < words.size() -shingleLength +1; i ++){
			String temp = new String();
			for (int j = 0; j < shingleLength; j++){
				temp += words.get(i+j);
			}
			results.add(temp);
		}
		return results;
	}

	/**
	 * Returns the shingled word similarity of two texts.
	 * 
	 * The shingled word similarity is the Jaccard index between each text's
	 * shingle set.
	 * 
	 * A text's shingle set is the set of shingles (of the given length) for the
	 * entire text, as defined by shingle and asLowercaseWords, 
	 * less the shingle set of the templateText. Removes the templateText 
	 * from consideration after shingling, not before.
	 * 
	 * @param text1
	 * @param text2
	 * @param templateText
	 * @param shingleLength
	 * @return
	 */
	public static double shingleSimilarity(String text1, String text2, String templateText, int shingleLength) {
		
		return SetUtilities.jaccardIndex(SetUtilities.setDifference(shingle(asLowercaseWords(text1), shingleLength), shingle(asLowercaseWords(templateText), shingleLength)), SetUtilities.setDifference(shingle(asLowercaseWords(text2), shingleLength), shingle(asLowercaseWords(templateText), shingleLength)));
	}
}
