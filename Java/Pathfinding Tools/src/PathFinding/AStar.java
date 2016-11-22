package PathFinding;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * A* search algorithm. An instance of this class must be used to perform a search, and nodes of the graph must implement the GraphNode interface
 * @param <E> The node class of the graph that is implementing the GraphNode interface
 */
public class AStar<E extends GraphNode> {

    /**
     * True if using a heuristic (A*), False if not (UCS)
     */
    private boolean usingHeuristic;

    /**
     * Instantiates an A* object using a heuristic
     */
    public AStar(){
        this(true);
    }

    /**
     * Instantiates an A* Object and sets the heuristic flag
     */
    public AStar(boolean usingHeuristic){
        super();
        this.usingHeuristic = usingHeuristic;
    }

    /**
     * Searches for an optimal path from the start node to the goal node
     * @param start The node to start the search from
     * @param goal The desired goal node of the search
     * @return The optimal path from the start to goal node, or null if a path is not found
     */
    public LinkedList<E> search(E start, E goal) {

        // DATA

        // Nodes that have already been expanded
        LinkedList<E> closed = new LinkedList<E>();

        // Nodes that have been found but have not been expanded
        PriorityQueue<E> open = new PriorityQueue<E>();
        open.push(start,0.0f);

        // Map of previous nodes, holds node which provides the shortest found path back to the start
        Hashtable<E, E> from = new Hashtable<E,E>();
        from.put(start,start);



        // SCORES

        // current low cost from start to node E
        Hashtable<E, Float> gScore = new Hashtable<E, Float>();
        gScore.put(start, 0.0f);


        // ALGORITHM

        // main loop (runs until open set is empty or goal is found)
        while (!open.isEmpty()) {

            // Pull the node from open with the highest priority
            E current = open.pop();

            // If the goal is found return the reconstructed path
            if (current.equals(goal)) {
                return reconstructPath(from, goal);
            }

            // add the current node to the closed set
            closed.add(current);

            // Nodes connected to the current node
            E[] successors;

            // Cast from generic GraphNode array to E type array
            try {
                successors = (E[]) current.getSuccessors();
            } catch(ClassCastException e) {
                System.out.println("Can't cast " + current.getSuccessors()[0].getClass() + " to " + start.getClass());
                System.out.println(e.getStackTrace());
                return null;
            }

            // Check the neighbors of the current node for new or shorter paths
            for (E neighbor : successors) {

                // skip nodes that have been visited already
                if (closed.contains(neighbor)) {
                    continue;
                }

                // current path length from start to neighbor
                float tempScore = gScore.get(current) + current.distance(neighbor);

                float costEstimate = tempScore;
                // cost estimate from start to goal through neighbor
                if (usingHeuristic) {
                    costEstimate += neighbor.heuristic(goal);
                }

                // add neighbor to the open list if it isn't already there
                // if using a heuristic use the cost estimate as the priority, otherwise use the current path length
                if(!open.contains(neighbor)){
                    open.push(neighbor, costEstimate);
                }

                // skip nodes that have a shorter path to them already
                else if(tempScore >= gScore.get(neighbor)) {
                    continue;
                }

                // update the backtracking hashtable(from) and the gScore
                from.put(neighbor,current);
                gScore.put(neighbor,tempScore);

            }

        }

        // No Path was found
        return null;

    }


    /**
     * Reconstructs the path by backtracking from the goal to start node
     * @param from Hashtable linking nodes top their parents with the shortest path back to the start
     * @param goal The goal node of the search from which to start the backtracking
     * @return The optimal path reconstructed from the given Hashtable
     */
    private LinkedList<E> reconstructPath(Hashtable<E,E> from, E goal) {

        // Final Path
        LinkedList<E> path = new LinkedList<E>();

        // add goal
        path.add(goal);
        E current = goal;

        // Backtrack from goal to start
        while (from.get(current) != current) {
            current = from.get(current);
            path.addFirst(current);
        }

        return path;

    }

}
