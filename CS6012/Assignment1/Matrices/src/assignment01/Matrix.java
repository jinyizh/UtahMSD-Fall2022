package assignment01;

public class Matrix {
  int numRows;
  int numColumns;
  int data[][];

  // Constructor with data for new matrix (automatically determines dimensions)
  public Matrix(int data[][]) {
    numRows = data.length; // d.length is the number of 1D arrays in the 2D array
    if (numRows == 0) {
      numColumns = 0;
    } else {
      numColumns = data[0].length; // d[0] is the first 1D array
    }
    this.data = new int[numRows][numColumns]; // create a new matrix to hold the data
    // copy the data over
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        this.data[i][j] = data[i][j];
      }
    }
  }

  @Override // instruct the compiler that we do indeed intend for this method to override
            // the superclass' (Object) version
  public boolean equals(Object other) {
    if (!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
      return false;
    }
    Matrix matrix = (Matrix) other; // if the above was not true, we know it's safe to treat 'o' as a Matrix

    // test if matrices have same dimensions
    if (this.numRows != matrix.numRows || this.numColumns != matrix.numColumns) return false;
    for (int i = 0; i < this.data.length; i++) { // loop through input's rows
      for (int j = 0; j < this.data[i].length; j++) { // loop through elements in every row
        if (this.data[i][j] != matrix.data[i][j]) return false; // if elements at same position don't equal
      }
    }
    return true; // all elements equal, get out of for loop
  }

  @Override // instruct the compiler that we do indeed intend for this method to override
            // the superclass' (Object) version
  public String toString() {
    /*
     * TODO: replace the below return statement with the correct code, you must
     * return a String that represents this matrix, as specified on the assignment
     * page
     */
    String result = ""; // initialize result as empty string
    for (int i = 0; i < data.length; i++) { // loop through rows
      for (int j = 0; j < data[i].length; j++) { // loop through elements in every row
        if (j == data[i].length - 1) {
          result += data[i][j] + " \n"; // for the last element of every row, add new line
        } else {
          result += data[i][j] + " "; // elements that are not last element of every row
        }
      }
    }
    return result;
  }

  public Matrix times(Matrix matrix) {
    /*
     * TODO: replace the below return statement with the correct code, This function
     * must check if the two matrices are compatible for multiplication, if not,
     * return null. If they are compatible, determine the dimensions of the
     * resulting matrix, and fill it in with the correct values for matrix
     * multiplication
     */
    if (this.numColumns == matrix.numRows) {
      // Cij = Aix * Bxj - for reference
      int[][] data = new int[this.numRows][matrix.numColumns];
      for (int i = 0; i < data.length; i ++) { // loop through rows (same as lhs)
        for (int j = 0; j < data[i].length; j++) { // loop through elements in row
          for (int k = 0; k < matrix.data.length; k++) {
            data[i][j] += this.data[i][k] * matrix.data[k][j];
          }
        }
      }
      return new Matrix(data);
    }
    return null; // placeholder
  }

  public Matrix plus(Matrix matrix) {
    /*
     * TODO: replace the below return statement with the correct code, This function
     * must check if the two matrices are compatible for addition, if not, return
     * null. If they are compatible, create a new matrix and fill it in with the
     * correct values for matrix addition
     */
    if (this.numRows == matrix.numRows && this.numColumns == matrix.numColumns) {
      int[][] data = new int[this.numRows][this.numColumns];
      for (int i = 0; i < data.length; i++) { // loop through rows
        for (int j = 0; j < data[i].length; j++) { // loop through elements in every row
          data[i][j] = this.data[i][j] + matrix.data[i][j]; // set data of result matrix
        }
      }
      return new Matrix(data);
    }
    return null; // placeholder
  }
}
