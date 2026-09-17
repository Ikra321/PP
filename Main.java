import java.io.*;

public class Main {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        try {
            System.out.print("Введите размерность квадратной матрицы n: ");
            String line = br.readLine();
            int n = Integer.parseInt(line);

            int[][] matrix = new int[n][n];
            System.out.println("Введите элементы матрицы:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.parseInt(br.readLine());
                }
            }

            System.out.println("\nИсходная матрица:");
            printMatrix(matrix);

            System.out.println("\nЗадание 1:");
            Task1Norm task1 = new Task1Norm(matrix);
            System.out.println("Строчная норма матрицы: " + task1.calculateRowNorm());

            System.out.println("\nЗадание 2:");
            Task2Expansion task2 = new Task2Expansion(matrix);
            int[][] expandedMatrix = task2.expandMatrix();
            System.out.println("Расширенная матрица:");
            printMatrix(expandedMatrix);

            System.out.println("\nЗадание 3:");
            Task3Symmetry task3 = new Task3Symmetry(matrix);
            int[][] modifiedMatrix = task3.replaceLocalMinimalWithZero();
            System.out.println("Матрица после замены локальных минимумов на 0:");
            printMatrix(modifiedMatrix);

            if (task3.isSymmetric(modifiedMatrix)) {
                System.out.println("Матрица симметрична относительно главной диагонали.");
            } else {
                System.out.println("Матрица не симметрична относительно главной диагонали.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Не целое число");
        } catch (IOException e) {
            System.out.println("Ошибка чтения с клавиатуры");
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}