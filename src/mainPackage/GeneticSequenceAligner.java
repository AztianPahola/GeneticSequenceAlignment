package mainPackage;

public class GeneticSequenceAligner {

	public static void main(String[] args) {
		char[] testGene1x = {'C','A','G','C','A','C','T','T','G','G','A','T','T','C','T','C','G','G'};
		char[] testGene1y = {'C','A','G','C','G','T','G','G'};
		
		char[] testGene2x = {'T','C','C','C','A','G','T','T','A','T','G','T','C','A','G','G','G',
							'G','A','C','A','C','G','A','G','C','A','T','G','C','A','G','A','G','A','C'};
		char[] testGene2Y = {'A','A','T','T','G','C','C','G','C','C','G','T','C','G','T','T','T',
							'T','C','A','G','C','A','G','T','T','A','T','G','T','C','A','G','A','T','C'};

	}
	
	private static char[][] getOptimalSequences(char[] geneX, int n, char[] geneY, int m){
		int[][] optimalMatrix = new int[n+1][m+1];
	}

}
