package clashclass.ecs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Represents an implementation of GameObject
 */
public class GameObjectImpl implements GameObject {
    private final int uniqueId;
    private final Set<Component> components;

    private static int uniqueIdProgression;

    /**
     * Constructs the GameObject
     */
    public GameObjectImpl() {
        this.uniqueId = uniqueIdProgression++;
        this.components = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getUniqueId() {
        return this.uniqueId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addComponent(final Component component) {
        this.components.add(component);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <T extends Component> Optional<T> getComponentOfType(final Class<T> componentType) {
        return this.components.stream()
                .filter(componentType::isInstance)
                .map(componentType::cast)
                .findFirst();
    }

    /**
     * Gets an immutable representation of all the components
     * @return an immutable representation of all the components
     */
    @Override
    public final Set<Component> getComponents() {
        return Collections.unmodifiableSet(components);
    }
}
