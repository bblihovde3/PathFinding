package PathFinding;

import java.util.LinkedList;

/**
 * Priority Queue for items that don't have an implicit priority
 * @param <E> The type of data to be held in the PriorityQueue
 */
public class PriorityQueue<E> {

    /**
     * Holds The data for the PriorityQueue
     */
    private LinkedList<pItem<E>> data;

    /**
     * Instantiates a new, empty priorityQueue
     */
    public PriorityQueue() {
        data = new LinkedList<>();
    }

    /**
     * Push an item onto the PriorityQueue
     * @param item The item to be pushed onto the PriorityQueue
     * @param priority The priority of the item to be pushed onto the PriorityQueue
     */
    public void push(E item, float priority) {
        pItem dataNode = new pItem(item,priority);

        if (data.isEmpty()) {
            data.add(dataNode);
        }

        else {
            int i = 0;
            int size = data.size();
            while(i < size && data.get(i).getPriority() < priority){
                i++;
            }
            data.add(i,dataNode);
        }

    }

    /**
     * Removes and returns the item with the highest priority (low value)
     * @return The item with the highest priority (low value)
     */
    public E pop() {
        return data.removeFirst().getData();
    }

    /**
     * Checks for an item in the PriorityQueue
     * @param item The item to look for in the PriorityQueue
     * @return True if the item is in the PriorityQueue, False if it is not
     */
    public boolean contains(E item) {
        for (pItem<E> o : data) {
            if (item.equals(o.getData())) {
                return true;
            }
        }

        return false;

    }

    /**
     * Checks if the PriorityQueue is empty
     * @return True if the PriorityQueue is empty, False if not
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Internal Class to hold the data and priority for a single entry in the PriorityQueue
     * @param <E> The type of data held in the Priority Queue
     */
    private class pItem<E> {

        /**
         * The data
         */
        private E data;

        /**
         * The priority. A lower value is given higher priority
         */
        private float priority;

        /**
         * Instantiates a pItem object
         * @param data The data to be stored
         * @param priority The priority of the data
         */
        pItem(E data, float priority) {
            this.data = data;
            this.priority = priority;
        }

        /**
         * Get the data of the pItem
         * @return The stored data
         */
        E getData() {
            return data;
        }

        /**
         * Get the priority of the pItem
         * @return The stored priority
         */
        float getPriority() {
            return priority;
        }

    }

}
