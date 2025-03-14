package clashclass.ecs;

import java.util.Optional;
import java.util.Set;

/**
 * Represents an entity, which acts like an "empty object" with only a unique id.
 */
public interface GameObject {
    /**
     * Gets the unique id.
     * @return unique id
     */
    int getUniqueId();

    /**
     * Adds a component to the set of components.
     * @param component component to add
     */
    void addComponent(Component component);

    /**
     * Tries to the get a component of a specific type.
     * @param componentType type of the component to get, can be either a class or an interface
     * @return component of the desired type, if exists, otherwise returns an empty Optional
     * @param <T> the type of the component to get
     */
    <T extends Component> Optional<T> getComponentOfType(Class<T> componentType);

    /**
     * Gets all the components
     * @return all the components
     */
    Set<Component> getComponents();
}
