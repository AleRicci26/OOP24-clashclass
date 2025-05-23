package clashclass.battle.battlereport;

/**
 * Interface for the Battle Report Controller.
 * Manages the battle report data including destruction percentage and stolen resources.
 */
public interface BattleReportController {

    /**
     * Increases the destruction percentage of the village.
     * This is called when a building is destroyed.
     */
    void increaseDestructionPercentage();

    /**
     * Increases the amount of stolen resources.
     *
     * @param resourceManager The resource manager containing the resources to be added
     */
    void increaseStolenResources(ResourceManager resourceManager);

    /**
     * Sets the total number of troops used in the battle.
     *
     * @param count The total number of troops used
     */
    void setTroopCount(int count);
}
