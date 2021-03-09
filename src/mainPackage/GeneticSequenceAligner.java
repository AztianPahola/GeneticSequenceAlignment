package mainPackage;

public class GeneticSequenceAligner {

	private static final int MISMATCH_PENALTY = 1;
	private static final int GAP_PENALTY= 2;
	

	
	public static void main(String[] args) {
		
		char[] testGene1x = { 'C', 'A', 'G', 'C', 'A', 'C', 'T', 'T', 'G', 'G', 'A', 'T', 'T', 'C', 'T', 'C', 'G','G' };
		char[] testGene1y = { 'C', 'A', 'G', 'C', 'G', 'T', 'G', 'G' };
		
		char[] testGene2x = { 'T', 'C', 'C', 'C', 'A', 'G', 'T', 'T', 'A', 'T', 'G', 'T', 'C', 'A', 'G', 'G', 'G', 'G',
								'A', 'C', 'A', 'C', 'G', 'A', 'G', 'C', 'A', 'T', 'G', 'C', 'A', 'G', 'A', 'G', 'A', 'C' };
		char[] testGene2Y = { 'A', 'A', 'T', 'T', 'G', 'C', 'C', 'G', 'C', 'C', 'G', 'T', 'C', 'G', 'T', 'T', 'T', 'T',
								'C', 'A', 'G', 'C', 'A', 'G', 'T', 'T', 'A', 'T', 'G', 'T', 'C', 'A', 'G', 'A', 'T', 'C' };
		
		char[] myTestGeneX = {'A','A','C','A','G','T','T','A','C','C'};
		char[] myTestGeneY = {'T','A','A','G','G','T','C','A'};
		
		Cost[][] testResult = generateCostMatrix(myTestGeneX,10,myTestGeneY,8);
		
		String[] optimalAlignement = getOptimalAlignment(testResult,myTestGeneX,myTestGeneY);
		
		for (Cost[] is : testResult) {
			for (Cost is2 : is) {
				System.out.printf("%4d",is2.value);
			}
			System.out.println();
		}
		
	}

	private static Cost[][] generateCostMatrix(char[] geneX, int n, char[] geneY, int m) {
		
		Cost[][] costMatrix = new Cost[n + 1][m + 1];

		if (n > m) { // Matrix is fridge shaped (standing rectangle)
			
			int diagonal = 1; // Length of the current diagonal
			int vOffset = 0; // Vertical offset to determine starting point for diagonal iteration
			int hOffset = 0; // Horizontal offset to determine starting point for diagonal iteration
			
			// Iterates through the bottom right corner of the fridge matrix
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < diagonal; j++) {
					costMatrix[n - vOffset + j][m - hOffset - j] = getOptimalCost((n-vOffset+j),geneX,(m-hOffset-j),geneY,costMatrix);
				}
				vOffset++;
				diagonal++;
			}

			// Iterates through the middle section of the fridge matrix
			for (int i = 0; i < n + 1 - m; i++) {
				for (int j = 0; j < diagonal; j++) {
					costMatrix[n - vOffset + j][m - hOffset - j] = getOptimalCost((n-vOffset+j),geneX,(m-hOffset-j),geneY,costMatrix);
				}
				vOffset++;
			}

			// Iterates through the upper left corner of the fridge matrix
			for (int i = 0; i < m; i++) {
				diagonal--;
				hOffset++;
				for (int j = 0; j < diagonal; j++) {
					costMatrix[j][m - hOffset - j] = getOptimalCost((j),geneX,(m-hOffset-j),geneY,costMatrix);
				}
				
			}
			
		} else if (m > n) { // Matrix is a rectangle
			
			int diagonal = 1; // Length of the current diagonal
			int vOffset = 0; // Vertical offset to determine starting point for diagonal iteration
			int hOffset = 0; // Horizontal offset to determine starting point for diagonal iteration
			
			// Iterates through the bottom right corner of the rectangular matrix
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < diagonal; j++) {
					costMatrix[n - vOffset + j][m - hOffset - j] = getOptimalCost((n-vOffset+j),geneX,(m-hOffset-j),geneY,costMatrix);
				}
				vOffset++;
				diagonal++;
			}

			// Iterates through the middle section of the rectangular matrix
			for (int i = 0; i < m + 1 - n; i++) {
				for (int j = 0; j < diagonal; j++) {
					costMatrix[n - vOffset + j][m - hOffset - j] = getOptimalCost((n-vOffset+j),geneX,(m-hOffset-j),geneY,costMatrix);
				}
				hOffset++;
			}

			// Iterates through the upper left corner of the rectangular matrix
			for (int i = 0; i < n; i++) {
				diagonal--;
				for (int j = 0; j < diagonal; j++) {
					costMatrix[n - vOffset + j][m - hOffset - j] = getOptimalCost((n-vOffset+j),geneX,(m-hOffset-j),geneY,costMatrix);
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
					costMatrix[n - vOffset + j][m - hOffset - j] = getOptimalCost((n-vOffset+j),geneX,(m-hOffset-j),geneY,costMatrix);
				}
				vOffset++;
				diagonal++;
			}

			// Iterates through the middle diagonal of the square matrix
			for (int j = 0; j < diagonal; j++) {
				costMatrix[n - vOffset + j][m - hOffset - j] = getOptimalCost((n-vOffset+j),geneX,(m-hOffset-j),geneY,costMatrix);
			}
			
			// Iterates through the upper left corner of the square matrix
			for (int i = 0; i < m; i++) {
				diagonal--;
				hOffset++;
				for (int j = 0; j < diagonal; j++) {
					costMatrix[n - vOffset + j][m - hOffset - j] = getOptimalCost((n-vOffset+j),geneX,(m-hOffset-j),geneY,costMatrix);
				}

			}
			
		}
		
		return costMatrix;

	}
	
	private static Cost getOptimalCost(int xIndex,char[] geneX, int yIndex,char[] geneY, Cost[][] matrix) {
		Cost optimalCost = new Cost();
		
		if(xIndex == geneX.length) { // If we are on the bottom row
			optimalCost.value = 2*(geneY.length-yIndex);
		}else if (yIndex == geneY.length) { // If we are on the right column
			optimalCost.value = 2*(geneX.length-xIndex);
		}else {
			int putGapInXCost = matrix[xIndex][yIndex+1].value + GAP_PENALTY;
			int putGapInYCost = matrix[xIndex+1][yIndex].value + GAP_PENALTY;
			int alignGenesCost = matrix[xIndex+1][yIndex+1].value;
			if(geneX[xIndex] != geneY[yIndex])
				alignGenesCost += MISMATCH_PENALTY;
			
			if(alignGenesCost < putGapInXCost && alignGenesCost < putGapInYCost) 
				optimalCost = new Cost(alignGenesCost, new int[] {xIndex+1,yIndex+1});
			
			else if(putGapInXCost < alignGenesCost && putGapInXCost < putGapInYCost) 
				optimalCost = new Cost(putGapInXCost, new int[] {xIndex,yIndex+1});
			
			else 
				optimalCost = new Cost(putGapInYCost, new int[] {xIndex+1,yIndex});
		}
		return optimalCost; 
	}

	
	private static String[] getOptimalAlignment(Cost[][] costMatrix, char[] geneX, char[] geneY) {
		int x  = 0;
		int y = 0;
		int[] nextIndex;
		String[] genes = new String[] {"",""};
		
		nextIndex =  costMatrix[x][y].getPointer();
		
		while(x < costMatrix.length || y < costMatrix[0].length){
			
			if(nextIndex[0] == x+1 && nextIndex[1] == y+1) { // The previous index is diagonal
				
				genes[0] += geneX[x];
				genes[1] += geneY[y];
				x++;
				y++;
				nextIndex = costMatrix[x][y].getPointer();
				
			}else if(nextIndex[0] == x && nextIndex[1] == y+1) { // The previous index is to the right
				
				genes[0] += "-";
				genes[1] += geneY[y];
				y++;
				nextIndex = costMatrix[x][y].getPointer();
				
			}else if(nextIndex[0] == x+1 && nextIndex[1] == y) { // The previous index is below
				
				genes[0] += geneX[x];
				genes[1] += "-";
				x++;
				nextIndex = costMatrix[x][y].getPointer();
				
			}else { // Theoretically this should never be the case
				
				System.out.println("There was an error in getting the optimal Alignment");
				
			}
		}
		
		return genes;
	}
}
