import java.util.AbstractList;

/**
 * Interface used to wrap any NLP library into a more easily used class
 * @author Robert
 *
 */
public interface ISentenceProcessor {
	
	/**
	 * Takes in a block of text and separates it into a list of sentences.
	 * @param text The input block of text.
	 * @return A List of sentences contained in text. If concatenated back together it should be the same as text
	 */
	AbstractList<String> SeparateSentences(String text);
	
	/**
	 * Takes a sentence, separates it into tokens, removes the irrelevant tokens and returns the rest.
	 * @param sentence The sentence that should be transformed into just the relevant tokens.
	 * @return Returns a List of the tokens that were chosen as relevant.
	 */
	AbstractList<String> TokenizeAndStripSentence(String sentence);
}
