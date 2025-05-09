package clashclass.ai.pathfinding;

import java.util.List;

/**
 * Represents an algorithm used to find a valid path to a target.
 */
public interface PathfindingAlgorithm {
    /**
     * Finds a path from the start node to the given target, traversing the nodes.
     *
     * @param nodeGrid the grid which contains all the nodes
     * @param start the start node
     * @param end the end node
     *
     * @return the ordered list of nodes representing the path to the target
     */
    List<PathNode> findPath(PathNodeGrid nodeGrid, PathNode start, PathNode end);
}
