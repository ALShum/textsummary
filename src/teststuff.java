import java.util.Arrays;

public class teststuff {

	public static void main(String[] args) {
		int[] A = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1};
		int[] B = Arrays.copyOfRange(A, 0, 5);
		System.out.println(Arrays.toString(B));
	}
}
