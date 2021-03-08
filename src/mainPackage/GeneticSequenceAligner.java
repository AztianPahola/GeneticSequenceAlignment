package mainPackage;

public class GeneticSequenceAligner {

	public static void main(String[] args) {
		char[] testGene1x = { 'C', 'A', 'G', 'C', 'A', 'C', 'T', 'T', 'G', 'G', 'A', 'T', 'T', 'C', 'T', 'C', 'G',
				'G' };
		char[] testGene1y = { 'C', 'A', 'G', 'C', 'G', 'T', 'G', 'G' };

		char[] testGene2x = { 'T', 'C', 'C', 'C', 'A', 'G', 'T', 'T', 'A', 'T', 'G', 'T', 'C', 'A', 'G', 'G', 'G', 'G',
				'A', 'C', 'A', 'C', 'G', 'A', 'G', 'C', 'A', 'T', 'G', 'C', 'A', 'G', 'A', 'G', 'A', 'C' };
		char[] testGene2Y = { 'A', 'A', 'T', 'T', 'G', 'C', 'C', 'G', 'C', 'C', 'G', 'T', 'C', 'G', 'T', 'T', 'T', 'T',
				'C', 'A', 'G', 'C', 'A', 'G', 'T', 'T', 'A', 'T', 'G', 'T', 'C', 'A', 'G', 'A', 'T', 'C' };

	}

	private static char[][] getOptimalSequences(char[] geneX, int n, char[] geneY, int m) {
		int[][] optimalMatrix = new int[n + 1][m + 1];

		if (n > m) { // Matrix is fridge shaped (standing rectangle)
			
			int diagonal = 1; // Length of the current diagonal
			int vOffset = 0; // Vertical offset to determine starting point for diagonal iteration
			int hOffset = 0; // Horizontal offset to determine starting point for diagonal iteration
			
			// Iterates through the bottom right corner of the fridge matrix
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < diagonal; j++) {
					optimalMatrix[n - vOffset + j][m - hOffset - j] = getOptimalAlignment((n-vOffset+j),(m-hOffset-j),optimalMatrix);
				}
				vOffset++;
				diagonal++;
			}

			// Iterates through the middle section of the fridge matrix
			for (int i = 0; i < n + 1 - m; i++) {
				for (int j = 0; j < diagonal; j++) {
					optimalMatrix[n - vOffset + j][m - hOffset - j] = getOptimalAlignment((n-vOffset+j),(m-hOffset-j),optimalMatrix);
				}
				vOffset++;
			}

			// Iterates through the upper left corner of the fridge matrix
			for (int i = 0; i < m; i++) {
				diagonal--;
				hOffset++;
				for (int j = 0; j < diagonal; j++) {
					optimalMatrix[j][m - hOffset - j] = getOptimalAlignment((j),(m-hOffset-j),optimalMatrix);
				}
				
			}
			
		} else if (m > n) { // Matrix is a rectangle
			
			int diagonal = 1; // Length of the current diagonal
			int vOffset = 0; // Vertical offset to determine starting point for diagonal iteration
			int hOffset = 0; // Horizontal offset to determine starting point for diagonal iteration
			
			// Iterates through the bottom right corner of the rectangular matrix
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < diagonal; j++) {
					optimalMatrix[n - vOffset + j][m - hOffset - j] = getOptimalAlignment((n-vOffset+j),(m-hOffset-j),optimalMatrix);
				}
				vOffset++;
				diagonal++;
			}

			// Iterates through the middle section of the rectangular matrix
			for (int i = 0; i < m + 1 - n; i++) {
				for (int j = 0; j < diagonal; j++) {
					optimalMatrix[n - vOffset + j][m - hOffset - j] = getOptimalAlignment((n-vOffset+j),(m-hOffset-j),optimalMatrix);
				}
				hOffset++;
			}

			// Iterates through the upper left corner of the rectangular matrix
			for (int i = 0; i < n; i++) {
				diagonal--;
				for (int j = 0; j < diagonal; j++) {
					optimalMatrix[n - vOffset + j][m - hOffset - j] = getOptimalAlignment((n-vOffset+j),(m-hOffset-j),optimalMatrix);
				}
				hOffset++;
			}
		} else { // The matrix is a square
			
			int diagonal = 1; // Length of the current diagonal
			int vOffset = 0; // Vertical offset to determine starting point for diagonal iteration
			int hOffset = 0; // Horizontal offset to determine starting point for diagonal iteration
			
			// Iterates through the bottom right corner of the square matrix
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < diagonal; j++) {
					optimalMatrix[n - vOffset + j][m - hOffset - j] = getOptimalAlignment((n-vOffset+j),(m-hOffset-j),optimalMatrix);
				}
				vOffset++;
				diagonal++;
			}

			// Iterates through the middle diagonal of the square matrix
			for (int j = 0; j < diagonal; j++) {
				optimalMatrix[n - vOffset + j][m - hOffset - j] = getOptimalAlignment((n-vOffset+j),(m-hOffset-j),optimalMatrix);
			}
			
			// Iterates through the upper left corner of the square matrix
			for (int i = 0; i < m; i++) {
				diagonal--;
				hOffset++;
				for (int j = 0; j < diagonal; j++) {
					optimalMatrix[n - vOffset + j][m - hOffset - j] = getOptimalAlignment((n-vOffset+j),(m-hOffset-j),optimalMatrix);
				}

			}
			
		}

	}
	
	private static int getOptimalAlignment(int nIndex, int mIndex, int[][] matrix) {
		
	}

}
