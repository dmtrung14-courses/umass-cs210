/*
 * Copyright 2023 Marc Liberatore.
 */

package index;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.print.Doc;
import javax.swing.text.Document;

import comparators.TfIdfComparator;
import documents.DocumentId;

/**
 * A simplified document indexer and search engine.
 * 
 * Documents are added to the engine one-by-one, and uniquely identified by a DocumentId.
 *
 * Documents are internally represented as "terms", which are lowercased versions of each word 
 * in the document. 
 * 
 * Queries for terms are also made on the lowercased version of the term. Terms are 
 * therefore case-insensitive.
 * 
 * Lookups for documents can be done by term, and the most relevant document(s) to a specific term 
 * (as computed by tf-idf) can also be retrieved.
 *
 * See:
 * - <https://en.wikipedia.org/wiki/Inverted_index>
 * - <https://en.wikipedia.org/wiki/Search_engine_(computing)> 
 * - <https://en.wikipedia.org/wiki/Tf%E2%80%93idf>
 * 
 * @author Marc Liberatore
 *
 */
public class SearchEngine {
	
	/**
	 * Inserts a document into the search engine for later analysis and retrieval.
	 * 
	 * The document is uniquely identified by a documentId; attempts to re-insert the same 
	 * document are ignored.
	 * 
	 * The document is supplied as a Reader; this method stores the document contents for 
	 * later analysis and retrieval.
	 * 
	 * @param documentId
	 * @param reader
	 * @throws IOException iff the reader throws an exception 
	 */
	public Map<String, Set<DocumentId>> map = new HashMap<String, Set<DocumentId>>();
	public Map<DocumentId, Map<String,Integer>> frequency = new HashMap<DocumentId, Map<String,Integer>>();
	public int size;
	public void addDocument(DocumentId documentId, Reader reader) throws IOException {
		Scanner s = new Scanner(reader);
		s.useDelimiter("\\W+");
		while (s.hasNext()){
			String tempWord = s.next().toLowerCase();
			Set<DocumentId> temp = map.getOrDefault(tempWord, new HashSet<>());
			temp.add(documentId);
			map.put(tempWord, temp);

			int tempFreq = frequency.getOrDefault(documentId, new HashMap<>()).getOrDefault(tempWord,0);
			Map<String, Integer> tempStringInt = frequency.getOrDefault(documentId, new HashMap<>());
			tempFreq ++;
			tempStringInt.put(tempWord, tempFreq);
			frequency.put(documentId, tempStringInt);
			
		}	
		size += 1;
	}
	
	/**
	 * Returns the set of DocumentIds contained within the search engine that contain a given term.
	 * 
	 * @param term
	 * @return the set of DocumentIds that contain a given term
	 */
	public Set<DocumentId> indexLookup(String term) {
		return map.getOrDefault(term.toLowerCase(), new HashSet<>());
	}
	
	/**
	 * Returns the term frequency of a term in a particular document.
	 * 
	 * The term frequency is number of times the term appears in a document.
	 * 
	 * See 
	 * @param documentId
	 * @param term
	 * @return the term frequency of a term in a particular document
	 * @throws IllegalArgumentException if the documentId has not been added to the engine
	 */
	public int termFrequency(DocumentId documentId, String term) throws IllegalArgumentException {
		if (!frequency.containsKey(documentId)){
			throw new IllegalArgumentException();
		}
		return frequency.get(documentId).getOrDefault(term, 0);
	}
	
	/**
	 * Returns the inverse document frequency of a term across all documents in the index.
	 * 
	 * For our purposes, IDF is defined as log ((1 + N) / (1 + M)) where 
	 * N is the number of documents in total, and M
	 * is the number of documents where the term appears.
	 * 
	 * @param term
	 * @return the inverse document frequency of term 
	 */
	public double inverseDocumentFrequency(String term) {
		double n = size;
		double m = indexLookup(term).size();
		return Math.log((1+n)/(1+m));
	}
	
	/**
	 * Returns the tfidf score of a particular term for a particular document.
	 * 
	 * tfidf is the product of term frequency and inverse document frequency for the given term and document.
	 * 
	 * @param documentId
	 * @param term
	 * @return the tfidf of the the term/document
	 * @throws IllegalArgumentException if the documentId has not been added to the engine
	 */
	public double tfIdf(DocumentId documentId, String term) throws IllegalArgumentException {
		return termFrequency(documentId, term)*inverseDocumentFrequency(term);
	}
	
	/**
	 * Returns a sorted list of documents, most relevant to least relevant, for the given term.
	 * 
	 * A document with a larger tfidf score is more relevant than a document with a lower tfidf score.
	 * 
	 * Each document in the returned list must contain the term.
	 * 
	 * @param term
	 * @return a list of documents sorted in descending order by tfidf
	 */
	public List<DocumentId> relevanceLookup(String term) {
		List<DocumentId> result = new ArrayList<>();
		for (DocumentId i: indexLookup(term)){
			result.add(i);
		}
		Comparator<DocumentId> something = new TfIdfComparator(this, term);
		result.sort(something);
		return result;
	}
}
