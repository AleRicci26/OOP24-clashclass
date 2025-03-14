package clashclass.commons;

import clashclass.ecs.BaseComponent;

public class Transform2D extends BaseComponent {
    private Vector2 position;
    private Vector2 rotation;
    private Vector2 scale;

    public Vector2 getPosition() {
        return this.position;
    }

    public Vector2 getRotation() {
        return this.rotation;
    }

    public Vector2 getScale() {
        return this.scale;
    }

    public void setPosition(final Vector2 position) {
        this.position = position;
    }

    public void setRotation(final Vector2 rotation) {
        this.rotation = rotation;
    }

    public void setScale(final Vector2 scale) {
        this.scale = scale;
    }
}
