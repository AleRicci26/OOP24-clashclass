package clashclass.ai.behaviourtree;

import clashclass.commons.Transform2D;
import clashclass.ecs.GameObject;

public class GoToTargetNode extends AbstractBehaviourNode {
    private final float distanceToTargetTolerance;
    private GameObject actor;
    private GameObject target;

    public GoToTargetNode(final float distanceToTargetTolerance) {
        this.distanceToTargetTolerance = distanceToTargetTolerance;
    }

    @Override
    public void onEnter() {
//        this.actor =
//        this.target =
    }

    @Override
    public State onUpdate(final float deltaTime) {
        final var actorPosition = actor.getComponentOfType(Transform2D.class).get().getPosition();
        final var targetPosition = target.getComponentOfType(Transform2D.class).get().getPosition();

        final var distanceToTarget = actorPosition.distance(targetPosition);

        if (distanceToTarget <= distanceToTargetTolerance) {
            return State.SUCCESS;
        }

        final var actorTransform = actor.getComponentOfType(Transform2D.class).get();
        final var speed = 1.0; // TODO: get della speed corrente (può variare eventualmente ad ogni frame)

        final var newPosition = targetPosition.Subtract(actorPosition).Multiply(speed * deltaTime);
        actorTransform.setPosition(newPosition);

        return State.RUNNING;
    }
}
