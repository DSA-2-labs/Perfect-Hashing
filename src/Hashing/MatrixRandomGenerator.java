package Hashing;

import Hashing.Matrix;

public class MatrixRandomGenerator {
    public static Matrix generate(int rows, int cols) {
        Matrix ans = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
                ans.data[i][j] = (int) (2 * Math.random()); // only 0's and 1's
        return ans;
    }
}
