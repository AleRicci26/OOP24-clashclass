package clashclass.ecs;

import java.util.Optional;
import java.util.Set;

/**
 * Represents an entity, which acts like an "empty object" with only a unique id.
 */
public interface GameObject {
    /**
     * Gets the unique id.
     *
     * @return unique id
     */
    int getUniqueId();

    /**
     * Adds a component to the set of components.
     *
     * @param component component to add
     */
    void addComponent(Component component);

    /**
     * Tries to the get a component of a specific type.
     *
     * @param componentType type of the component to get, can be either a class or an interface
     * @param <T> the type of the component to get
     *
     * @return component of the desired type, if exists, otherwise returns an empty Optional*
     */
    <T extends Component> Optional<T> getComponentOfType(Class<T> componentType);

    /**
     * Gets all the components.
     *
     * @return all the components
     */
    Set<Component> getComponents();

    /**
     * Destroys the GameObject and all its components.
     */
    void destroy();

    /**
     * Returns whether the GameObject has been marked to be destroyed.
     *
     * @return true if the GameObject is marked as destroyed, false otherwise
     */
    boolean isMarkedAsDestroyed();

    /**
     * Represents a fluent Builder for a GameObject.
     */
    interface Builder {
        /**
         * Adds a component to the Builder.
         *
         * @param component the component to add
         *
         * @return the same instance of this builder
         */
        Builder addComponent(Component component);

        /**
         * Builds the GameObject.
         *
         * @return the GameObject that has been built.
         */
        GameObject build();
    }
}
