
/**
 * Interface for ranking a set of items based on how many common elements they share
 * @author Robert
 *
 */
public interface IRanker {
	
	/**
	 * Returns an array giving the ranking of each item. So the item # at index 0 of the returned array is the highest ranked item
	 * @param commonalityMatrix A matrix of the similarity between two objects, so that commonalityMatrix[i][j] shows how similar the ith and jth items are.
	 * @return An array of the item numbers starting from most highly ranked to lowest based on similarity with all the otehr items.
	 */
	public int[] RankItems(double[][] commonalityMatrix); 
}
