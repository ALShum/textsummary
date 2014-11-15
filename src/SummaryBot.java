import java.io.File;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;


/**
 * A bot for the unsupervised summarizing of text articles 
 * @author Robert
 *
 */
public class SummaryBot {

	private ISentenceProcessor _sentProc;
	private IRanker _ranker;
	
	/**
	 * Constructs a new bot using the given sentence processor and item ranker
	 * @param sentProc The Sentence Processor for turning a block of text into sentences and removing irrelevant types of words
	 * @param ranker The item ranker for ranking sentences based on how similar they are.
	 */
	public SummaryBot(ISentenceProcessor sentProc, IRanker ranker) {
		_sentProc=sentProc;
		_ranker=ranker;
	}
	
	/**
	 * Summarizes the article in the given file.
	 * @param text The text of the article.
	 * @param numberOfSummarySentences The number of sentences summarizing the article that should be returned.
	 * @return The most important sentences in the article.
	 */
	public String[] SummarizeArticle(int numberOfSummarySentences, String text) {
		/*
		 * Strategy for unsupervised summarization of Articles
		 * 
		 * 1. Takes a text file and processes it into separate sentences.
		 * 2. Removes unimportant words from each sentence (could happen in 1st step).
		 * 3. Builds a matrix that describes how similar each sentence is to each other.
		 * 4. Applies PageRank (or similar) algorithm to Matrix to order them by importance.
		 * 5. Prints the top X most important sentences.
		 */
		
		AbstractList<String> sentences= _sentProc.SeparateSentences(text);
		ArrayList<AbstractList<String>> strippedSentences= new ArrayList<AbstractList<String>>();
		Iterator<String> iter = sentences.iterator();
		while(iter.hasNext()) {
			strippedSentences.add(_sentProc.TokenizeAndStripSentence(iter.next()));
		}
		double[][] commonMatrix = MiscUtils.CreateCommonMatrix(strippedSentences);
		int[] bestSentenceIndex= _ranker.RankItems(commonMatrix);
		String[] bestSentences= new String[Math.min(bestSentenceIndex.length, numberOfSummarySentences)];
		for(int i=0;i<bestSentences.length;i++) {
			bestSentences[i]=sentences.get(bestSentenceIndex[i]);
		}
		return bestSentences;
	}
	
	/**
	 * Summarizes the article in the given file.
	 * @param inputFile The file containing the text only article.
	 * @param numberOfSummarySentences The number of sentences summarizing the article that should be returned.
	 * @return The most important sentences in the article.
	 * @throws IOException 
	 */
	public String[] SummarizeArticle(int numberOfSummarySentences, File inputFile) throws IOException {
		
		return SummarizeArticle(numberOfSummarySentences, FileUtils.readFileToString(inputFile, "utf-8"));
	}

}
