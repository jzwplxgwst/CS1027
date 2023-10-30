/**
 * This class represents a Markov Chain to produce a matrix of predicted future states using a
 * state vector and a transition matrix
 *
 * Class: ICS4U1
 * Date: Jan 31 2023
 * @author James Wong
 */
public class MarkovChain extends Matrix  {

    /** Vector class */
    private Vector stateVector;

    /** Matrix class */
    private Matrix transitionMatrix;

    /**
     * Name: MarkovChain
     * @param sVector vector object
     * @param tMatrix matrix object
    */
    public MarkovChain(Vector sVector, Matrix tMatrix){
        super(tMatrix.getNumRows(), tMatrix.getNumCols());      // call from the super class
        this.stateVector = sVector;
        this.transitionMatrix = tMatrix;
    }

    /**
     * Check if the instance variables are valid for a Markov chain problem and return a
     * Boolean to indicate its validity (true if valid, false if invalid):x
     * @return true or false
    */
    public boolean isValid(){
        double sum = 0;     // set the sum to zero

        // if the matrix row, columns and vector's columns are not equal then return false
        if (!(this.transitionMatrix.getNumRows() == this.transitionMatrix.getNumCols()) && !(this.transitionMatrix.getNumCols() == this.stateVector.getNumCols())) {
            return false;
        }

        // add each value of the matrix to each other by row
        for(int i=0; i<this.transitionMatrix.getNumRows(); i++){
            for(int j=0; j<this.transitionMatrix.getNumCols(); j++){
                sum = (sum + this.transitionMatrix.getData()[i][j]);
            }

            // check if the sum of each row is not between 0.99 and 1.01
            if (!(sum>0.99 && sum<1.01)) {
                return false;
            }
            sum = 0;    // set the sum back to 0 for the next row
        }

        // add up all the values of the vector
        for(int i=0; i<this.stateVector.getNumCols(); i++){
            sum += this.stateVector.getElement(i);
        }

        // check if the sum of the vector is between 0.99 and 1.01
        return sum > 0.99 && sum < 1.01;
    }

    /**
     * Name: computeProbabilityMatrix
     * @param numSteps number of steps
     * @return true or null
     */
    public Matrix computeProbabilityMatrix(int numSteps){

        // check if its valid first
        if (!isValid()){
            return null;
        } else {
            // create a new matrix with the original matrix's values
            Matrix newMat = this.transitionMatrix;

            // multiply the transition matrix by itself numSteps-1 times
            for (int i = 0; i < numSteps - 1; i++) {
                newMat = newMat.multiply(this.transitionMatrix);
            }

            // multiply the state vector by the resulting matrix
            return this.stateVector.multiply(newMat);
        }
    }
}
