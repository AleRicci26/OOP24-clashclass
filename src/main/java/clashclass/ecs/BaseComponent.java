package clashclass.ecs;

public abstract class BaseComponent implements Component {
    private GameObject gameObject;

    protected final GameObject getGameObject() {
        return this.gameObject;
    }

    @Override
    public final void setGameObjectReference(final GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void update(float deltaTime) {

    }
}