import java.util.AbstractList;

/**
 * Implementation of ISentenceProcessor that uses the OpenNLP library as a backbone.
 * @author Robert
 * @see https://opennlp.apache.org/documentation/1.5.3/manual/opennlp.html
 */
public class OpenNLPSentenceProcessor implements ISentenceProcessor {

	@Override
	public AbstractList<String> SeparateSentences(String text) {
		// Use Sentence Detection API
		return null;
	}

	@Override
	public AbstractList<String> TokenizeAndStripSentence(String sentence) {
		// Use Tokenize API, then Part-of-Speech API, then remove unnecessary words based on tag
		return null;
	}

	/**
	 * Sets the token tags that are relevant in each sentence. If not set, the default is all tags are relevant.
	 * @param relevantTokenTags An array of the tags from the Penn Treebank tag set (see link) that represent types of tokens that should be kept.
	 * @see http://www.cis.upenn.edu/~treebank/
	 */
	public void SetRelevantTokenTags(String[] relevantTokenTags) {
		// TODO Auto-generated method stub
		
	}

}
