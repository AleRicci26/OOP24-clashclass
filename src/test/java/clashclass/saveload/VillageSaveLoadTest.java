package clashclass.saveload;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.ComponentFactory;
import clashclass.elements.ComponentFactoryImpl;
import clashclass.elements.buildings.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VillageSaveLoadTest {

    @TempDir
    Path tempDir;

    private VillageSaveLoadManager saveLoadManager;
    private ComponentFactory componentFactory;
    private PlayerBuildingFactoryImpl playerBuildingFactory;
    private BattleBuildingFactoryImpl battleBuildingFactory;
    private PlayerVillageDecoderImpl playerDecoder;
    private BattleVillageDecoderImpl battleDecoder;
    private BuildingFactoryMapper<PlayerBuildingFactoryImpl> playerMapper;
    private BuildingFactoryMapper<BattleBuildingFactoryImpl> battleMapper;
    private VillageEncoder encoder;

    @BeforeEach
    void setUp() {
        componentFactory = new ComponentFactoryImpl();
        playerBuildingFactory = new PlayerBuildingFactoryImpl();
        battleBuildingFactory = new BattleBuildingFactoryImpl();

        playerDecoder = new PlayerVillageDecoderImpl(playerBuildingFactory);
        battleDecoder = new BattleVillageDecoderImpl(battleBuildingFactory);

        playerDecoder.setComponentFactory(componentFactory);
        battleDecoder.setComponentFactory(componentFactory);

        playerMapper = new BuildingFactoryMapper<>(playerBuildingFactory);
        battleMapper = new BuildingFactoryMapper<>(battleBuildingFactory);

        encoder = new VillageEncoderImpl();

        saveLoadManager = new VillageSaveLoadManager(
                encoder,
                playerDecoder,
                battleDecoder,
                new SimpleFileWriterImpl(),
                tempDir
        );
    }
    @Test
    void testSaveLoadVillage() throws IOException {
        Set<GameObject> originalObjects = createTestGameObjects(playerMapper);
        String villageName = "default_village";

        // Save the village
        saveLoadManager.saveVillage(originalObjects, villageName);

        // Verify save file exists
        Path savePath = tempDir.resolve(villageName + ".csv");
        assertTrue(Files.exists(savePath));

        // Load and verify the village
        Set<GameObject> loadedObjects = saveLoadManager.loadPlayerVillage(villageName);
        assertEquals(originalObjects.size(), loadedObjects.size());
    }

    @Test
    void testBattleVillageLoading() throws IOException {
        // Create some game objects
        Set<GameObject> originalObjects = createTestGameObjects(battleMapper);

        // Save the village
        String fileName = "campaign_village";
        saveLoadManager.saveVillage(originalObjects, fileName);

        // Load as battle village
        Set<GameObject> loadedObjects = saveLoadManager.loadBattleVillage(fileName);

        // Check that we have the same number of objects
        assertEquals(originalObjects.size(), loadedObjects.size());
    }

    @Test
    void testFileNotFound() {
        String nonExistentFile = "does_not_exist";

        // Test both loading methods
        assertThrows(IOException.class, () ->
                saveLoadManager.loadPlayerVillage(nonExistentFile));

        assertThrows(IOException.class, () ->
                saveLoadManager.loadBattleVillage(nonExistentFile));
    }

    @Test
    void loadVillageFromResources() throws IOException {
        Path resourcePath = Path.of("src/main/resources/villages/village01.csv");
        String csv = Files.readString(resourcePath);

        Set<GameObject> objs = playerDecoder.decode(csv);

        assertFalse(objs.isEmpty(), "Il villaggio caricato dal file non deve essere vuoto");

        long townHallCount = objs.stream()
                .filter(go -> go.getComponents().stream()
                        .anyMatch(c -> c.getClass().getSimpleName().equals("TownHallComponent")
                                || c.getClass().getSimpleName().equals("TOWN_HALLComponent"))) // dipende da nome componente
                .count();

        assertTrue(townHallCount >= 1, "Almeno un Town Hall deve essere presente nel villaggio caricato");
    }
    private Set<GameObject> createTestGameObjects(BuildingFactoryMapper<?> factoryMapper) {
        Set<GameObject> objects = new HashSet<>();

        // Create buildings using the factory mapper
        objects.add(factoryMapper.getFactoryFor(VillageElementData.ARCHER_TOWER)
                .apply(new Vector2D(50, 55)));
        objects.add(factoryMapper.getFactoryFor(VillageElementData.GOLD_STORAGE)
                .apply(new Vector2D(60, 65)));
        objects.add(factoryMapper.getFactoryFor(VillageElementData.WALL)
                .apply(new Vector2D(70, 75)));
        objects.add(factoryMapper.getFactoryFor(VillageElementData.ELIXIR_EXTRACTOR)
                .apply(new Vector2D(80, 85)));

        return objects;
    }

}
