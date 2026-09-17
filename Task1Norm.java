public class Task1Norm {
    private final int[][] matrix;

    public Task1Norm(int[][] matrix) {
        this.matrix = matrix;
    }

    public int calculateRowNorm() {
        int maxNorm = 0;
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            int currentRowSum = 0;
            for (int j = 0; j < n; j++) {
                currentRowSum += Math.abs(matrix[i][j]);
            }

            if (i == 0 || currentRowSum > maxNorm) {
                maxNorm = currentRowSum;
            }
        }

        return maxNorm;
    }
}