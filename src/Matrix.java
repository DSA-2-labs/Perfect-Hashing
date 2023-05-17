public class Matrix {
    int rows;
    int cols;
    int[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    public Matrix multiply(Matrix other) {
        assert(this.cols == other.rows);

        Matrix ans = new Matrix(this.rows, other.cols);
        for(int i = 0; i < this.rows; i++)
            for(int j = 0; j < other.cols; j++)
                for(int k = 0; k < this.cols; k++)
                    ans.data[i][j] += this.data[i][k] * other.data[k][j];

        for(int i = 0; i < this.rows; i++)
            for(int j = 0; j < other.cols; j++)
                ans.data[i][j] &= 1; // same as %2, but bitwise is more efficient

        return ans;
    }

    /*
    this function converts the key to 32 bits in matrix
     */
    public Matrix convertToMatrix(int key) {
        Matrix ans = new Matrix(32, 1);
        for(int i = 0; i < 32; i++){
            ans.data[i][0] = key & 1; // same as %2
            key >>= 1; // same as /2
        }
        return ans;
    }

    /*
    this function converts the binary matrix into integer
     */
    public int convertMatrixToIndex(Matrix a) {
        int ans = 0;
        for(int i = a.rows - 1; i >= 0; i--){
            ans <<= 1; // same as *2
            ans += a.data[i][0];
        }
        return ans;
    }

    public void print(){
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                System.out.print(this.data[i][j]);
            }
            System.out.println();
        }
    }
}
