public class Task2Expansion {
    private final int[][] matrix;

    public Task2Expansion(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] expandMatrix() {
        int n = matrix.length;
        int minVal = matrix[0][0];
        int minRow = 0;
        int minCol = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < minVal) {
                    minVal = matrix[i][j];
                    minRow = i;
                    minCol = j;
                }
            }
        }

        System.out.println("Минимальный элемент: " + minVal + " на позиция (" + minRow + ", " + minCol + ")");

        int[][] expandedMatrix = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                expandedMatrix[i][j] = matrix[i][j];
            }
        }

        for (int j = 0; j < n; j++) {
            expandedMatrix[n][j] = matrix[minRow][j];
        }

        for (int i = 0; i < n; i++) {
            expandedMatrix[i][n] = matrix[i][minCol];
        }

        expandedMatrix[n][n] = minVal;

        return expandedMatrix;
    }
}