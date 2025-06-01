package clashclass.shop;

import clashclass.resources.RESOURCE_TYPE;
import clashclass.resources.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class ShopManagerTest {
    private Player player;
    private ShopManagerImpl shopManager;
    private ShopItemImpl affordableItem;
    private ShopItemImpl expensiveItem;
    private final double AFFORDABLE_PRICE = 10.0;
    private final double EXPENSIVE_PRICE = 100.0;

    @BeforeEach
    void setUp() {
        this.player = new Player();
        this.affordableItem = new ShopItemImpl(RESOURCE_TYPE.GOLD, AFFORDABLE_PRICE, player);  // can afford
        this.expensiveItem = new ShopItemImpl(RESOURCE_TYPE.GOLD, EXPENSIVE_PRICE, player);  // cannot afford
        this.shopManager = new ShopManagerImpl(List.of(affordableItem, expensiveItem));
    }

    @Test
    void testGetBalance() {
        assertEquals(AFFORDABLE_PRICE, shopManager.getBalance(affordableItem));
    }

    @Test
    void testCanAffordTrue() {
        assertTrue(shopManager.canAfford(affordableItem));
    }

    @Test
    void testCanAffordFalse() {
        assertFalse(shopManager.canAfford(expensiveItem));
    }

    @Test
    void testBuyItemSuccess() {
        shopManager.buyItem(affordableItem);
        assertEquals(0, affordableItem.getResourceManager().getCurrentValue());
    }

    @Test
    void testFindItemsByResourceType() {
        List<ShopItem> items = shopManager.findItemsByResourceType(RESOURCE_TYPE.GOLD);
        assertEquals(2, items.size());
        assertTrue(items.stream().allMatch(item -> item.getResourceType() == RESOURCE_TYPE.GOLD));
    }
}
