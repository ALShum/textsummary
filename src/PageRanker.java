import java.util.Arrays;

//http://ejml.org/javadoc/ for potential matrix math
/**
 * Implements the IRanker interface using the PageRank algorithm
 * @author Alex
 *
 */
public class PageRanker implements IRanker {
	final double damping_factor = 0.85;
	final int numIter = 500;
	
	
	@Override
	public int[] RankItems(double[][] commonalityMatrix) {
		commonalityMatrix = normalize(commonalityMatrix);
		double[] result = new double[commonalityMatrix.length];
		Arrays.fill(result, 1.0/commonalityMatrix.length);
		
		for(int i = 0; i < numIter; i++) {
			result = mult(commonalityMatrix, result);
			result = scalar_mult(damping_factor, result);
			result = scalar_add(1 - damping_factor, result);
		}
		
		//indices: each sentences rank
		Pair[] sortedResults = new Pair[result.length];
		for(int i=0; i<result.length;i++) {
			sortedResults[i] = new Pair(i, result[i]);
		}
		Arrays.sort(sortedResults);
		
		//rank: index of the smallest page-rank to the largest
		int[] ranks = new int[result.length];
		for(int i = 0; i < sortedResults.length; i++) {
			//System.out.println(sortedResults[i].index + "," + i);
			ranks[i] = sortedResults[i].index;
			
		}
		
		//System.out.println(Arrays.toString(commonalityMatrix[0])+"\n");
		
		//System.out.println(Arrays.toString(sortedResults));
		//System.out.println(Arrays.toString(result));
		//System.out.println(Arrays.toString(ranks));
		
		return(ranks);
	}
	
	/**
	 * Matrix-vector multiplication
	 * @param mtx Square Matrix
	 * @param vec Vector
	 * @return vector result
	 */
	public double[] mult(double[][] mtx, double[] vec) {
		if(mtx.length != vec.length || mtx[0].length != vec.length) {
			throw new IllegalArgumentException("Invalid dimensions for multiplication");
		}
		
		double[] ans = new double[vec.length];
		double rowSum;
		for(int i = 0; i < mtx.length; i++) {
			rowSum = 0;
			for(int j = 0; j < mtx.length; j++) {
				rowSum += mtx[i][j] * vec[j];
			}
			
			ans[i] = rowSum;
		}
		
		return(ans);
	}
	
	/**
	 * Column-wise normalization for square matrices
	 * Columns must sum to 1
	 * @param mtx
	 * @return
	 */
	public double[][] normalize(double[][] mtx) {
		if(mtx.length != mtx[0].length) {
			throw new IllegalArgumentException("Not a square matrix");
		}
		
		double[] colSum = new double[mtx.length];
		Arrays.fill(colSum, 0);
		
		//column sums
		for(int i = 0; i < mtx.length; i++) {
			for(int j = 0; j < mtx.length; j++) {
				colSum[j] += mtx[i][j];
			}
		}
		
		//normalize
		for(int i = 0; i < mtx.length; i++) {
			for(int j = 0; j < mtx.length; j++) {
				double div = colSum[j] == 0.0 ? 1 : colSum[j];
				mtx[i][j] = mtx[i][j]/div;
			}
		}
		
		return(mtx);
	}
	
	public double[] scalar_mult(double scalar, double[] vec) {
		for(int i = 0; i < vec.length; i++) {
			vec[i] = vec[i] * scalar;
		}
		
		return(vec);
	}
	
	public double[] scalar_add(double scalar, double[] vec) {
		for(int i = 0; i < vec.length; i++) {
			vec[i] = vec[i] + scalar;
		}
		
		return(vec);
	}
	
	private class Pair implements Comparable{
		public double val;
		public int index;
		
		public Pair(int i, double v) {
			val=v;
			index=i;
		}
		
		@Override
		public int compareTo(Object arg0) {
			Pair p= (Pair)arg0;
			if(p.val==val) return 0;
			if(p.val>val) return 1;
			else return -1;
		}
		
	}
}
