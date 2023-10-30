/**
 * This class is used to represent a Vector. Recall that vectors are special kinds of matrices so this
 * class must inherit from the Matrix class.
 *  Must be horizontal, with one row
 *
 * Class: ICS4U1
 * Date: Jan 31 2023
 * @author James Wong
 */

public class Vector extends Matrix {

    /**
     * Name: Vector
     * @param c column
     */
    public Vector(int c){
        super(1, c);
    }

    /**
     * Name: Vector
     * @param c column
     * @param linArr horizontal array
     */
    public Vector(int c, double[] linArr){
        super(1, c, linArr);
    }

    /**
     * Name: getElement
     * @param c column key
     * @return the array value from the column key
     */
    public double getElement(int c){
        return this.getData()[0][c];
    }
}
