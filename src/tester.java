import java.io.File;
import java.io.IOException;
import java.util.AbstractList;

import org.apache.commons.io.FileUtils;

//tests new sentence processor
public class tester {
	public static void main(String[] args) throws IOException {
		File inputFile = new File("testArticle.txt");
		String testString = FileUtils.readFileToString(inputFile, "utf-8");
		
		
		LuceneSentenceProcessor proc = new LuceneSentenceProcessor();
		//AbstractList<String> s = proc.SeparateSentences(testString);
		AbstractList<String> s = proc.TokenizeAndStripSentence(testString);
		for(int i = 0; i < s.size(); i++) {
			System.out.println(s.get(i));
		}
	}
}
