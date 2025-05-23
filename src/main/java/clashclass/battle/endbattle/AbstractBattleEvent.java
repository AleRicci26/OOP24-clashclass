package clashclass.battle.endbattle;

import clashclass.ecs.GameObject;

import java.util.Optional;

/**
 * Abstract class for battle events that can end a battle.
 * This class provides a base implementation for ending battles
 * and should be extended by concrete battle event classes.
 */
public abstract class AbstractBattleEvent implements BattleEvent {
    
    /**
     * Protected method to end the battle.
     * This method is called by concrete implementations to perform
     * the actual battle ending logic.
     * 
     * @param gameObject the game object associated with the battle
     */
    protected void EndBattle(GameObject gameObject) {
        // Perform battle ending logic
        if (Optional.ofNullable(gameObject).isPresent()) {
            gameObject.destroy();
        }
        
        // Additional battle ending logic can be added here
        // such as updating game state, showing results, etc.
    }
    
    /**
     * {@inheritDoc}
     * This method should be implemented by concrete subclasses to define
     * when and how to end the battle.
     */
    @Override
    public abstract void endBattle();
}