public class Task3Symmetry {
    private final int[][] matrix;

    public Task3Symmetry(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] replaceLocalMinimalWithZero() {
        int n = matrix.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[i][j];
            }
        }

        boolean[][] isMin = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isLocalMinimum(i, j)) {
                    isMin[i][j] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isMin[i][j]) {
                    result[i][j] = 0;
                }
            }
        }

        return result;
    }

    private boolean isLocalMinimum(int row, int col) {
        int n = matrix.length;
        int current = matrix[row][col];

        for (int toUp = -1; toUp <= 1; toUp++) {
            for (int toward = -1; toward <= 1; toward++) {
                if (toUp == 0 && toward == 0) continue;

                int neib_row = row + toUp;
                int neib_col = col + toward;

                if (neib_row >= 0 && neib_row < n && neib_col >= 0 && neib_col < n) {
                    if (current >= matrix[neib_row][neib_col]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isSymmetric(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
}