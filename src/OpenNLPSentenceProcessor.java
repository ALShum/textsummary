import java.io.FileInputStream;
import java.io.InputStream;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Hashtable;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * Implementation of ISentenceProcessor that uses the OpenNLP library as a backbone.
 * @author Robert
 * @see https://opennlp.apache.org/documentation/1.5.3/manual/opennlp.html
 */
public class OpenNLPSentenceProcessor implements ISentenceProcessor {

	Hashtable<String, Boolean> tags;
	
	public OpenNLPSentenceProcessor() {
		tags= new Hashtable<String, Boolean>();
		for(String s : new String[]{"$", "``","''", "(", ")", ",", "--", ".", ":", "CC", "CD", "DT", "EX", "FW", "IN", "JJ", "JJR", "JJS", "LS", "MD", "NN", "NNP", "NNPS", "NNS", "PDT", "POS", "PRP", "PRP$", "RB", "RBR", "RBS", "RP", "SYM", "TO", "UH", "VB", "VBD", "VBG", "VBN", "VBP", "VBZ", "WDT", "WP", "WP$","WRB"}) {
			tags.put(s, true);
		}
	}
	
	@Override
	public AbstractList<String> SeparateSentences(String text) {
		// Use Sentence Detection API
		SentenceModel model = null;
		try{
			InputStream modelIn = new FileInputStream("en-sent.bin");
			model = new SentenceModel(modelIn);
		} catch(Exception e) {
			e.printStackTrace();
		}
		SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
		String[] sentences = sentenceDetector.sentDetect(text);
		ArrayList<String> list = new ArrayList<String>();
		for (String s : sentences) {
			list.add(s);
		}
		return list;
	}

	@Override
	public AbstractList<String> TokenizeAndStripSentence(String sentence) {
		// Use Tokenize API, then Part-of-Speech API, then remove unnecessary words based on tag
		ArrayList<String> list = new ArrayList<String>();
		TokenizerModel tmodel = null;
		POSModel pmodel=null;
		try{
			InputStream tmodelIn = new FileInputStream("en-token.bin");
			InputStream pmodelIn = new FileInputStream("en-pos-maxent.bin");
			tmodel = new TokenizerModel(tmodelIn);
			pmodel= new POSModel(pmodelIn);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Tokenizer tokenizer = new TokenizerME(tmodel);
		POSTaggerME tagger = new POSTaggerME(pmodel);
		
		String[] sent = tokenizer.tokenize(sentence);
		String[] tag =tagger.tag(sent);
		for(int i=0; i<sent.length;i++) {
			if(tags.containsKey(tag[i]) && tags.get(tag[i])) list.add(sent[i]);
		}
		return list;
	}

	/**
	 * Sets the token tags that are relevant in each sentence. If not set, the default is all tags are relevant.
	 * @param relevantTokenTags An array of the tags from the Penn Treebank tag set (see link) that represent types of tokens that should be kept.
	 * @see http://www.cis.upenn.edu/~treebank/
	 */
	public void SetRelevantTokenTags(String[] relevantTokenTags) {
		tags.clear();
		for(String s : relevantTokenTags) {
			tags.put(s, true);
		}
	}

}
