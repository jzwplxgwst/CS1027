/**
 * This class is used to represent a matrix containing double (i.e. decimal) numbers
 *
 * Class: ICS4U1
 * Date: Jan 31 2023
 * @author James Wong
 */

public class Matrix {

    /** number of rows */
    private int numRows;

    /** number of columns */
    private int numCols;

    /** array of matrix */
    private double[][] data;

    /**
     * Name: Matrix
     * @param r row key
     * @param c column key
     */
    public Matrix(int r, int c){
        this.numRows = r;
        this.numCols = c;
        data = new double[this.numRows][this.numCols];
    }

    /**
     * Name: Matrix
     * @param r row key
     * @param c column key
     * @param linArr 1-dimensional array
     */
    public Matrix(int r, int c, double[] linArr){
        this.numRows = r;
        this.numCols = c;
        data = new double[this.numRows][this.numCols];

        // Loop to fill the array with the values of linArr
        int hold = 0;
        for (int x=0; x<this.numRows; x++){
            for (int i=0; i<this.numCols; i++){
                this.data[x][i] = linArr[hold];
                hold++;
            }
        }
    }

    /**
     * Name: getNumRows
     * Description: return the number of rows
     * @return the number of rows
     */
    public int getNumRows(){
        return this.numRows;
    }

    /**
     * Name: getNumCols
     * Description: return the number of columns
     * @return the number of columns
     */
    public int getNumCols(){
        return this.numCols;
    }

    /**
     * Name: getData
     * Description: return the array of the matrix
     * @return the matrix
     */
    public double[][] getData(){
        return this.data;
    }

    /**
     * Name: getElement
     * Description: return the element of the array
     * @return the element of the array
     */
    public double getElement(int r, int c){
        return this.data[r][c];
    }

    /**
     * Name: setElement
     * Description: set the element value
     * @param r new possible row key
     * @param c new possible column key
     * @param value new possible value
     */
    public void setElement(int r, int c, double value){
        this.data[r][c] = value;
    }

    /**
     * Name: transpose
     * Description: Transpose the matrix by swapping the rows and columns
     */
    public void transpose(){
        // Temporarily hold the numRows and numCols values
        int tempRows = numRows;
        numRows = numCols;
        numCols = tempRows;

        // Create a second 2D array with the values of the original array
        double[][] hold = this.data.clone();
        this.data = new double[numRows][numCols];

        // fill the original array with the new values, rows = cols, cols = rows
        for (int i = 0; i < hold.length; i++) {
            for (int j = 0; j < hold[i].length; j++) {
                data[j][i] = hold[i][j];
            }
        }
    }

    /**
     * Name: multiply
     * @param scalar single number
     * @return The new matrix
     */
    public Matrix multiply(double scalar) {
        // Make a new matrix
        Matrix newMat = new Matrix(numRows, numCols);

        // multiply each individual value in the matrix by the scalar
        for(int i=0; i<numRows; i++){
            for(int j=0; j<numCols; j++){
                data[i][j] = data[i][j]*scalar;
                newMat.data[i][j] = data[i][j];
            }
        }
        return newMat;
    }

    /**
     * Name: multiply
     * @param other second matrix
     * @return the new matrix
     */
    public Matrix multiply(Matrix other){
        // return null if the number of columns is not equal to the number of rows in the inputted matrix
        if (this.numCols != other.numRows) {
            return null;
        } else {
            // create a new matrix
            Matrix newMat = new Matrix(this.numRows, other.numCols);

            // fill the new matrix with its new values
            // I had to use three for loops
            // for i represents the rows
            // for j represents the columns
            // for k represents the rows and columns depending on what i was calling at the time
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < other.numCols; j++) {
                    for (int k = 0; k < numCols; k++)
                        newMat.data[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
            return newMat;
        }
    }

    /**
     * name: toString
     * @return endPhrase
     */
    public String toString(){
        // create an empty string phrase
        String endPhrase = "";

        // if the array is empty then return "Empty matrix"
        if (this.data.length == 0){
            endPhrase = "Empty matrix";
        } else {
            // add each value from the array to the string phrase in it's proper format
            for(int i=0; i<numRows; i++){
                for(int j=0; j<numCols; j++){
                    String s = String.format("%8.3f", this.data[i][j]);
                    endPhrase = endPhrase + s;
                }
                endPhrase = endPhrase + "\n";   // add next line
            }
        }
        return endPhrase;
    }
}
