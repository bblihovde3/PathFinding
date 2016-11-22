package PathFinding;

/**
 * A test node class to be used for testing the search algorithms in this package
 */
public class TestNode implements GraphNode {

    /**
     * The X-Position of this node
     */
    private int posX;

    /**
     * The Y-Position of this node
     */
    private int posY;

    /**
     * The successors of this node. The successors are the nodes connected directly to this node with a single edge
     */
    private TestNode[] successors;

    /**
     * Instantiates a TestNode instance at position (x,y)
     * @param x The x-position of this node
     * @param y The y-position of this node
     */
    public TestNode(int x, int y) {
        posX = x;
        posY = y;
    }

    /**
     * Sets the successors of this node (for testing). The successors are the nodes connected directly to this node with a single edge
     * @param s The successors of this node
     */
    public void setSuccessors(TestNode[] s) {
        successors = s;
    }

    /**
     * Returns the successors of this node. The successors are the nodes connected directly to this node with a single edge
     * @return The successors of this node
     */
    @Override
    public GraphNode[] getSuccessors() {
        return successors;
    }

    /**
     * Calculates the distance Between this graph node and another graph node
     * @param g another graph node
     * @return The distance between this node and the given node
     */
    @Override
    public float distance(GraphNode g) {
        TestNode t = (TestNode) g;
        return (float) Math.sqrt(Math.pow(posX - t.getPosX(),2) + Math.pow(posY - t.getPosY(),2));
    }

    /**
     * Estimates the distance from this node to the goal node. If no goal node is supplied, this returns a constant value and heuristic based searches will not function as such (A* becomes UCS etc.).
     * If multiple goal nodes are supplied, the first is used as the goal
     * @param g The goal node(s)
     * @return The Estimated distance to the goal node
     */
    @Override
    public float heuristic(GraphNode... g) {
        return (g.length > 0) ? distance(g[0]) : 0;
    }

    /**
     * Get the x-position of this node
     * @return The x-position of this node
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Get the y-position of this node
     * @return The y-position of this node
     */
    public int getPosY() {
        return posY;
    }
}
