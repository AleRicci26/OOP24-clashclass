package clashclass.ecs;

public interface Component {
    void setGameObjectReference(GameObject gameObject);

    void initialize();

    void update(float deltaTime);
}
