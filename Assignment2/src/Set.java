/**
 * This class represents a simple collection that must be implemented with a singly-linked list. This
 * class must work for the generic type T.
 *
 * Class: ICS4U1
 * Date: Feb 27 2023
 * @author James Wong
 */

public class Set <T> {

    /** setStart class */
    private LinearNode<T> setStart;

    /**
     * Name: Set
     * set setStart to null
     */
    public Set(){
        this.setStart = null;
    }

    /**
     * Name: add
     * @param element element object
     */
    public void add(T element){
        LinearNode<T> newNode = new LinearNode<T>(element);

        if (setStart != null){
            newNode.setNext(setStart);
            setStart = newNode;
        } else {
            setStart = newNode;
        }
    }

    /**
     * Name: getLength
     * @return count
     */
    public int getLength(){
        int count = 0;
        LinearNode<T> current = setStart;
        while (current!= null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    /**
     * Name: getElement
     * @param i the ith key
     * @return null or the current element
     */
    public T getElement(int i){
        LinearNode<T> current = setStart;
        int count = 0;      // initialize variable
        while (current != null) {   // while its reading null
            if (count!=i){
                count ++;
                current = current.getNext();
            } else {
                return current.getElement();
            }
        }
        return null;        // return null
    }

    /**
     * Name: contains
     * @param element checks if it contains this element
     * @return true or false
     */
    public boolean contains(T element){
        if (setStart != null){
            LinearNode<T> current = setStart;

            while (current != null) {

                if (current.getElement() == element){
                    return true;
                }
                current = current.getNext();
            }
        }
        return false;
    }

    /**
     * Name: toString
     * @return toString.toString() the string value
     */
    public String toString(){
        StringBuilder toString = new StringBuilder();
        LinearNode<T> current = setStart;
        while (current != null) {
            toString.append(current.getElement().toString());
            toString.append(" ");   // add space
            current = current.getNext();
        }
        return toString.toString();
    }
}
