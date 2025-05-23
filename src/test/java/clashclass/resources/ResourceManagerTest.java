package java.clashclass.resources;

import clashclass.resources.ResourceManagerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceManagerTest {
    private static final int GENERIC_VALUE = 10;
    private ResourceManagerImpl resource;
     @BeforeEach
    public void setUp() {
         resource = new ResourceManagerImpl(GENERIC_VALUE);
     }

     @Test
    public void testResourceInitialized() {
         assertEquals(GENERIC_VALUE, resource.getCurrentValue());
     }

     @Test
    public void testResourceIncrease() {
         resource.increase(GENERIC_VALUE + 1);
         assertEquals(GENERIC_VALUE, resource.getCurrentValue());
     }

     @Test
    public void testResourceDecrease() {
         resource.decrease(GENERIC_VALUE + 1);
         assertEquals(0, resource.getCurrentValue());
     }
}