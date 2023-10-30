import java.io.IOException;

/**
 * This class will implement the algorithm to compute a shortest path from the initial chamber to the exit.
 * A description of this algorithm is given below.
 *
 * Class: CS1027B
 * Date: Mar 15 2023
 * @author James Wong
 */

public class FindShortestPath {
    public static void main(String[] args){

        try {
            if (args.length < 1) throw new Exception("No input file specified");
            String d1 = args[0];

            Dungeon dungeon = new Dungeon(d1);      // create new dungeon
            DLPriorityQueue<Hexagon> dp = new DLPriorityQueue<Hexagon>();   // First, create an empty priority queue
            Hexagon current = dungeon.getStart();   // create a hexagon object that is the dungeon's start
            boolean exit = false;

            dp.add(current, 0);     // add to the priority queue
            current.markEnqueued();      // Mark the chamber as enqueued

            while (!dp.isEmpty() && !exit) { // if exit is false
                boolean change = false;     // set a boolean to false - true if there is a dragon near
                current = dp.removeMin();   // remove the minimum item from the queue to the current

                current.markDequeued(); // Mark the chamber as dequeued

                if (current.isExit()) {     // if the current is the exit
                    exit = true;
                } else {
                    if (!current.isDragon()) {   // if the current is not a dragon
                        for (int i = 0; i < 6; i++) {
                            if (current.getNeighbour(i) != null && current.getNeighbour(i).isDragon()) {   // if the neighbor is a dragon
                                change = true;  // dragon near
                            }
                        }
                    }
                    if (current.isDragon()) {    // if current is a dragon
                        change = true;      // dragon near
                    }

                    if (!change) {  // if a dragon was not near
                        for (int i = 0; i < 6; i++) {
                            if ((current.getNeighbour(i) != null) && !current.getNeighbour(i).isWall() && (!current.getNeighbour(i).isMarkedDequeued())) {
                                int d = 1 + current.getDistanceToStart(); // Let D be equal to 1 + distance from current to the initial chamber.
                                Hexagon neighbor = current.getNeighbour(i);

                                // If distance of neighbour to initial chamber is larger than D then set the distance of neighbour to the initial chamber to D
                                if (neighbor.getDistanceToStart() > d) {
                                    neighbor.setDistanceToStart(d);
                                    neighbor.setPredecessor(current); // set current as the predecessor of neighbour
                                }

                                // If neighbour is marked as enqueued and the distance from neighbour to the initial chamber was modified
                                if (neighbor.isMarkedEnqueued() && neighbor.getDistanceToStart() == d) {
                                    double newPrio = neighbor.getDistanceToStart() + neighbor.getDistanceToExit(dungeon);
                                    dp.updatePriority(neighbor, newPrio);   // update the priority that neighbour has in the priority queue
                                } else if (!neighbor.isMarkedEnqueued()) {  // if neighbour is not marked as enqueued,
                                    dp.add(neighbor, neighbor.getDistanceToStart() + neighbor.getDistanceToExit(dungeon));  //  add it to the queue with priority equal to its distance to the initial chamber plus its distance to the exit
                                    neighbor.markEnqueued();    // mark neighbour as enqueued.
                                }
                            }
                        }
                    }
                }
            }
            Hexagon paths = dungeon.getExit();  // initialize a variable to hold the exit
            int count = 0;  // counter

            while (!paths.isStart()) {
                count++;    // add to the count
                paths = paths.getPredecessor(); // go through all the predecessors
            }
            if (count > 0) {
                count++;    // count one more
                System.out.println("Path of length " + count + " found");
            } else {
                System.out.println("No path found");
            }

        } catch (IOException e) {
            System.out.println("File not found " + e.getMessage());
        } catch (InvalidDungeonCharacterException e) {
            System.out.println("Invalid Dungeon Character " + e.getMessage());
        } catch (InvalidElementException e) {
            System.out.println("Invalid Element " + e.getMessage());
        } catch (InvalidNeighbourIndexException e) {
            System.out.println("Invalid Neighbour Index" + e.getMessage());
        } catch (EmptyPriorityQueueException e) {
            System.out.println("Empty Priority Queue " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
