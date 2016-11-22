package PathFinding;

/**
 * Interface for GraphNodes to be used with the search algorithms in this package. In order to use these searches, the nodes of the graph to be searched must implement this interface
 */
public interface GraphNode {

    /**
     * Return the successors of this GraphNode. The successors are the nodes connected directly to this node with a single edge
     * @return The successors of this GraphNode
     */
    public GraphNode[] getSuccessors();

    /**
     * Calculates this distance between this GraphNode and the given GraphNode
     * @param g The GraphNode to calculate the distance to
     * @return The distance between this GraphNode and GraphNode g
     */
    public float distance(GraphNode g);

    /**
     * Estimates the cost to reach the goal state from this node. If a specific goal node is not necessary it can be ignored. Returning a constant value will result in a UCS when using heuristic based searches
     * @param g The goal GraphNode
     * @return The estimated distance to the goal GraphNode
     */
    public float heuristic(GraphNode... g);

}
