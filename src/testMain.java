import java.io.File;
import java.io.IOException;
import java.util.AbstractList;

import org.apache.commons.io.FileUtils;


public class testMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		OpenNLPSentenceProcessor nlpProc = new OpenNLPSentenceProcessor();
		//nlpProc.SetRelevantTokenTags(new String[]{"CD", "FW", "JJ", "JJR", "JJS", "LS", "MD", "NN", "NNP", "NNPS", "NNS", "PDT", "PRP$", "RB", "RBR", "RBS", "RP", "SYM", "VB", "VBD", "VBG", "VBN", "VBP", "VBZ"});
		PageRanker pageRanker = new PageRanker();
		SummaryBot summaryBot = new SummaryBot(nlpProc, pageRanker);
		File testFile = new File("testArticle.txt");
		//AbstractList<String> sent=nlpProc.SeparateSentences(FileUtils.readFileToString(testFile, "utf-8"));
		//for(String s : sent) {
		//	System.out.println(s);
		//	AbstractList<String> words = nlpProc.TokenizeAndStripSentence(s);
		//	for(String w : words) {
		//		System.out.print(w +" ");
		//	}
		//	System.out.println();
		//	System.out.println();
		//}
		for(String s: summaryBot.SummarizeArticle(37, testFile)) {
			System.out.println(s);
		}
	}

}
