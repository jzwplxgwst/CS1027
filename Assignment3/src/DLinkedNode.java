/**
 * This class represents the nodes of a doubly linked list that you will use to implement the priority queue
 *
 * Class: CS1027B
 * Date: Mar 15 2023
 * @author James Wong
 */

/**
 * DLinkedNode
 * @param <T> a generic type
 */
public class DLinkedNode<T> {
    private T dataItem;     // A reference to the data item stored in this node
    private double priority;    // This is the priority of the data item stored in this node
    private DLinkedNode<T>next;     // A reference to the next node in the linked list
    private DLinkedNode<T>prev;     // A reference to the previous node in the linked list

    /**
     * DLinkedNode
     * Creates a node storing the given data item and priority.
     * @param data data
     * @param prio priority
     */
    public DLinkedNode (T data, double prio){
    this.dataItem = data;
    this.priority = prio;
    }

    /**
     * DlinkedNode
     * Creates an empty node, with null dataItem and zero priority
     */
    public DLinkedNode() {
        this.dataItem = null;
        this.priority = Double.parseDouble(null);
    }

    /**
     * getPriority
     * @return priority
     */
    public double getPriority(){
        return priority;
    }

    /**
     * getDoubleItem
     * @return dataItem
     */
    public T getDataItem(){
        return dataItem;
    }

    /**
     * getNext
     * @return next
     */
    public DLinkedNode<T> getNext() {
        return next;
    }

    /**
     * getPrev
     * @return prev
     */
    public DLinkedNode<T> getPrev(){
        return prev;
    }

    /**
     * setDataItem
     * @param dataItem setting the data item
     */
    public void setDataItem(T dataItem) {
        this.dataItem = dataItem;
    }

    /**
     * setNext
     * @param next setting the next
     */
    public void setNext(DLinkedNode<T> next) {
        this.next = next;
    }

    /**
     * setPrev
     * @param prev setting the previous
     */
    public void setPrev(DLinkedNode<T> prev) {
        this.prev = prev;
    }

    /**
     * setPriority
     * @param priority setting the priority
     */
    public void setPriority(double priority) {
        this.priority = priority;
    }
}
