

public class ComplexMatrix {
    private ComplexNumber[][] matrix;

    public ComplexMatrix(int rows, int cols) {
        matrix = new ComplexNumber[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new ComplexNumber(i, j);
            }
        }

    }

    public ComplexMatrix transpose() {
        int rows = matrix.length;
        int cols = matrix[0].length;
        ComplexMatrix transposedMatrix = new ComplexMatrix(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedMatrix.matrix[j][i] = matrix[i][j];
            }
        }

        return transposedMatrix;
    }

    public ComplexMatrix plusMatrix(ComplexMatrix other) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        ComplexMatrix resultMatrix = new ComplexMatrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultMatrix.matrix[i][j] = matrix[i][j].plusComplexNumbers(other.matrix[i][j]);
            }
        }
        return resultMatrix;
    }


    public ComplexMatrix multiplyMatrix(ComplexMatrix other) {
        int rows1 = matrix.length;
        int cols1 = matrix[0].length;
        int cols2 = other.matrix[0].length;

        ComplexMatrix resultMatrix = new ComplexMatrix(rows1, cols2);

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                ComplexNumber sum = new ComplexNumber(0, 0);
                for (int k = 0; k < cols1; k++) {
                    sum = sum.plusComplexNumbers(matrix[i][k].multiply(other.matrix[k][j]));
                }
                resultMatrix.matrix[i][j] = sum;
            }
        }

        return resultMatrix;
    }

    public ComplexNumber determinant() {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        } else if (n == 2) {
            return matrix[0][0].multiply(matrix[1][1]).minusComplexNumbers(matrix[1][0].multiply(matrix[0][1]));
        } else {
            ComplexNumber det = new ComplexNumber(0, 0);
            ComplexNumber sign = new ComplexNumber(1, 0);
            for (int j1 = 0; j1 < n; j1++) {
                ComplexMatrix subMatrix = createSubMatrix(j1);
                det = det.plusComplexNumbers(matrix[0][j1].multiply(sign).multiply(subMatrix.determinant()));
                sign = sign.multiply(new ComplexNumber(-1, 0));
            }
            return det;
        }
    }


    private ComplexMatrix createSubMatrix(int j) {
        int n = matrix.length;
        ComplexMatrix subMatrix = new ComplexMatrix(n - 1, n - 1);
        for (int i = 1; i < n; i++) {
            for (int k = 0, l = 0; k < n; k++) {
                if (k != j) {
                    subMatrix.matrix[i - 1][l++] = matrix[i][k];
                }
            }
        }
        return subMatrix;
    }


}