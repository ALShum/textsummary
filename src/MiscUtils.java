import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Misc. Utilities class
 * @author Robert
 *
 */
public class MiscUtils {
	
	/**
	 * Creates a 2d array matrix of how similar each sentence in the input array is to each other based on how many shared words they have
	 * @param sentences A list of sentences, where each sentence is itself a list of words.
	 * @return A matrix made form a 2d array where the value of each location [i][j] is the number of words the ith and jth sentences have in common.
	 */
	public static double[][] CreateCommonMatrix(AbstractList<AbstractList<String>> sentences) {
		double[][] result= new double[sentences.size()][sentences.size()];
		Hashtable<String, ArrayList<Integer>> words = new Hashtable<String, ArrayList<Integer>>();
		Iterator<AbstractList<String>> iter = sentences.iterator();
		for(int i=0;i<sentences.size();i++) {
			AbstractList<String> s =iter.next();
			for(String word : s) {
				if(words.containsKey(word)) {
					for(int j : words.get(word)) {
						result[i][j]++;
						result[j][i]++;
					}
					words.get(word).add(i);
				} else {
					ArrayList<Integer> n = new ArrayList<Integer>();
					n.add(i);
					words.put(word, n);
				}
			}
			//result[i][i]=0; //Or whatever we want to set how similar a sentence is to itself
			results[i][i] = sentences.get(i).size() / 2 * Math.log(sentences.get(i).size());
		}
		for(int i=0;i<sentences.size();i++) {
			for(int j=0;j<sentences.size();j++) {
				result[i][j] = result[i][j]/(Math.log(sentences.get(i).size())+Math.log(sentences.get(j).size()));
			}
		}
		return result;
	}
}
