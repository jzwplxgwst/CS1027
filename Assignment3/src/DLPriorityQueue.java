/**
 * DLPriorityQueue.java will
 * implement all the methods in the PriorityQueueADT.
 * java interface plus the method getRear and it will
 * store the data items of the priority queue in a doubly linked list.
 *
 * Class: CS1027B
 * Date: Mar 15 2023
 * @author James Wong
 */

public class DLPriorityQueue<T> implements PriorityQueueADT<T> {

    private DLinkedNode<T> front;   // This is a reference to the first node of the doubly linked list.
    private DLinkedNode<T> rear;    // This is a reference to the last node of the doubly linked list.
    private int count;  // The value of this variable is the number of data items in the priority queue


    /**
     * DLPriorityQueue
     */
    public DLPriorityQueue(){
        front = rear = null;
        count = 0;
    }

    /**
     * add
     * @param dataItem data item
     * @param priority priority number
     */
    public void add(T dataItem, double priority) {      // stored smallest at front to largest
        DLinkedNode<T> dp = new DLinkedNode<T>(dataItem, priority);     // create empty priority queue
        DLinkedNode<T> current = rear;      // a point node to rear
        boolean stop = false;               // set a boolean false to stop the code from running after the queue is changed

        if (front == null) {    // if the queue is empty
            front = dp;     // set the front to the added item
            rear = dp;      // set the rear to the added item
        } else {
            while (current != null && !stop) {      // loop until the current is null or the queue was changed

                if (current.getPriority() > priority && current == front) { // add front
                    current.setPrev(dp);    // set the current's previous to dp
                    dp.setNext(current);    // set dp's next to the current
                    front = dp;             // set dp's previous to front
                    stop = true;    // stop queue

                } else if (current.getPriority() < priority && current == rear) {    // add rear
                    current.setNext(dp);    // set the current's next to dp
                    dp.setPrev(current);    // set the dp's previous to the current
                    rear = dp;      // set the dp to rear
                    stop = true;    // stop queue

                } else if (current.getPriority() < priority) {  // add middle
                    current.getNext().setPrev(dp);  // set 22's previous to 20
                    dp.setNext(current.getNext());  // set 20's next to 22
                    current.setNext(dp);            // set 17's next to 20
                    dp.setPrev(current);            // set 20's previous to 17
                    stop = true;    // stop queue
                }
                current = current.getPrev();    // set the current to it's previous
            }
        }
        count++;        // add to the size

    }

    /**
     * updatePriority
     * @param dataItem value of the data item
     * @param newPriority  value of the new priority for this data item
     * @throws InvalidElementException thrown if Data item does not exist
     */
    public void updatePriority(T dataItem, double newPriority) throws InvalidElementException{

        DLinkedNode<T> current = front;     // a point a node to the front

        boolean stop = false;       // check for no data item in list

        while (current != null) {   // while the current is not null
            if (current.getDataItem().equals(dataItem)) {   // if the current's data and the inputted data are equal


                if (current == front && current == rear) {  // if the is only one data item
                    front = null;
                    rear = null;
                } else if (current == front) {  // if the current is pointing to the front
                    front = current.getNext();  // set front to the current's next
                    front.setPrev(null);        // set the new front's previous to null

                } else if (current == rear) {      // if the current is pointing to the rear
                    rear = current.getPrev();      // set rear to the current's previous
                    rear.setNext(null);            // set the new rear's next to null

                } else {
                    // remove data item from queue
                    current.getNext().setPrev(current.getPrev());   // set the current's next to the current's previous
                    current.getPrev().setNext(current.getNext());   // set the current's previous to the current's next
                }

                // remove the current from the queue
                current.setNext(null);      // set the current's next to null
                current.setPrev(null);      // set the current's prev to null

                count--;    // minus one bc the add operation will add one
                add(dataItem, newPriority);    // treat the current as a separate node being added to the queue

                stop = true;            // update stop
            }
            current = current.getNext();    // update the current
        }
        if (!stop) {           // if the priority was never changed
            throw new InvalidElementException("Data item does not exist");
        }
    }

    /**
     * removeMin
     * @return hold
     * @throws EmptyPriorityQueueException occur if the queue is empty
     */
    public T removeMin() throws EmptyPriorityQueueException {
        T hold;     // create return variable

        if (front == null) {    // if queue is empty
            throw new EmptyPriorityQueueException("Priority Queue is empty");
        }

        if (size() > 1) {
            hold = front.getDataItem(); // hold the value to return
            front = front.getNext();        // set the front to it's next
            front.getPrev().setNext(null);  // set the front's previous' next to null
            front.setPrev(null);            // set the previous to null
            count--;                        // minus from it`s size

        } else {
            hold = front.getDataItem(); // hold the value to return
            front = null;
            rear = null;
            count--;        // minus from the size
        }
        if (front == null) rear = null;     // if there was only one item in the queue
        return hold;
    }

    /**
     * isEmpty
     * @return boolean
     */
    public boolean isEmpty() {
        return front == null && rear == null;
    }

    /**
     * size
     * @return count
     */
    public int size() {
        return count;
    }

    /**
     * toString
     * @return output
     */
    public String toString() {
        String output = "";
        DLinkedNode<T> current = front;     // a point a node to the front

        while (current != null){    // loop until nothing more to add
            output += current.getDataItem();        // add each element without spaces
            current = current.getNext();    // update current
        }
        return output;  // return the output
    }

    /**
     * getRear
     * @return rear
     */
    public DLinkedNode<T> getRear(){
        return rear;
    }
}
