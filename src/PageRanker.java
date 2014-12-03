import java.util.Arrays;
import java.util.Collections;

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
		
		int[] ranks = new int[commonalityMatrix.length];
		double[] sortedResults = Arrays.copyOf(result, result.length);
		Arrays.sort(sortedResults);
		

		for(int i = 0; i < ranks.length; i++) {
			ranks[i] = Arrays.binarySearch(sortedResults, result[i]);
		}
		return ranks;
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
				mtx[i][j] = mtx[i][j]/colSum[j];
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
}
