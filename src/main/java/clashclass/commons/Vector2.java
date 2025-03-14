package clashclass.commons;

public record Vector2(double x, double y) {

    public double distance(final Vector2 other) {
        final double deltaX = x - other.x;
        final double deltaY = y - other.y;

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
