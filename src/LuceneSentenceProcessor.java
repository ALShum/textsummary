import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class LuceneSentenceProcessor implements ISentenceProcessor {
	public static void main(String[] args) {

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
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(sentences));
		return list;
	}

	@Override
	public AbstractList<String> TokenizeAndStripSentence(String sentence) {
		AbstractList<String> split_sentence = new ArrayList<String>();
		
		Tokenizer tokenizer = new StandardTokenizer(new StringReader(sentence));
	    final StandardFilter standardFilter = new StandardFilter(tokenizer);
	    final StopFilter stopFilter = new StopFilter(standardFilter, StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	    final CharTermAttribute charTermAttribute = tokenizer.addAttribute(CharTermAttribute.class);
	    
	    try {
			stopFilter.reset();
		    while(stopFilter.incrementToken()) {
		    	split_sentence.add(charTermAttribute.toString().toString());
		    }
		    stopFilter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		return(split_sentence);
	}
}
