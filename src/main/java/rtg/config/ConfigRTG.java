package rtg.config;

import rtg.api.config.ConfigProperty;
import rtg.api.config.ModConfig;
import rtg.util.SupportedMod;

import static rtg.api.config.ConfigProperty.Type.*;

public class ConfigRTG extends ModConfig {
    public static final ConfigProperty FLAT_BEDROCK_LAYERS = new ConfigProperty("Number of flat bedrock layers", "bedrock", INTEGER);
    public static final ConfigProperty BEDROCK_BLOCK = new ConfigProperty("BEDROCK_BLOCK", "bedrock", BLOCK);
    public static final ConfigProperty ENABLE_RTG_BIOME_DECORATIONS = new ConfigProperty("ENABLE_RTG_BIOME_DECORATIONS", "biomes", BOOLEAN);
    public static final ConfigProperty ENABLE_RTG_SURFACES = new ConfigProperty("ENABLE_RTG_SURFACES", "biomes", BOOLEAN);
    public static final ConfigProperty PATCH_BIOME_ID = new ConfigProperty("PATCH_BIOME_ID", "biomes", INTEGER);
    public static final ConfigProperty USE_BOP_LAYOUT = new ConfigProperty("USE_BOP_LAYOUT", "biomes", BOOLEAN);
    public static final ConfigProperty ENABLE_COUBLESTONE_BOULDERS = new ConfigProperty("ENABLE_COUBLESTONE_BOULDERS", "boulders", BOOLEAN);
    public static final ConfigProperty COBBLESTONE_BOULDER_CHANCE = new ConfigProperty("COBBLESTONE_BOULDER_CHANCE", "boulders", INTEGER);
    public static final ConfigProperty ENABLE_CAVE_MODIFICATIONS = new ConfigProperty("ENABLE_CAVE_MODIFICATIONS", "caves", BOOLEAN);
    public static final ConfigProperty ENABLE_CAVES = new ConfigProperty("ENABLE_CAVES", "caves", BOOLEAN);
    public static final ConfigProperty CAVE_DENSITY = new ConfigProperty("CAVE_DENSITY", "caves", INTEGER);
    public static final ConfigProperty CAVE_FREQUENCY = new ConfigProperty("CAVE_FREQUENCY", "caves", INTEGER);
    public static final ConfigProperty DUNE_HEIGHT = new ConfigProperty("DUNE_HEIGHT", "dunes", INTEGER);
    public static final ConfigProperty SHOW_DEBUG_INFO = new ConfigProperty("SHOW_DEBUG_INFO", "debug", BOOLEAN);
    public static final ConfigProperty DEBUG_LOGGING = new ConfigProperty("DEBUG_LOGGING", "debug", BOOLEAN);
    public static final ConfigProperty GENERATE_DUNGEONS = new ConfigProperty("GENERATE_DUNGEONS", "dungeons", BOOLEAN);
    public static final ConfigProperty ENABLE_SURFACE_WATER_LAKES = new ConfigProperty("ENABLE_SURFACE_WATER_LAKES", "surface lakes", BOOLEAN);
    public static final ConfigProperty SURFACE_WATER_LAKE_CHANCE = new ConfigProperty("SURFACE_WATER_LAKE_CHANCE", "surface lakes", BOOLEAN);
    public static final ConfigProperty ENABLE_SURFACE_LAVA_LAKES = new ConfigProperty("ENABLE_SURFACE_LAVA_LAKES", "surface lakes", BOOLEAN);
    public static final ConfigProperty SURFACE_LAVA_LAKE_CHANCE = new ConfigProperty("SURFACE_LAVA_LAKE_CHANCE", "surface lakes", BOOLEAN);
    public static final ConfigProperty ENABLE_UNDERGROUND_WATER_LAKES = new ConfigProperty("ENABLE_UNDERGROUND_WATER_LAKES", "underground lakes", BOOLEAN);
    public static final ConfigProperty UNDERGROUND_WATER_LAKE_CHANCE = new ConfigProperty("UNDERGROUND_WATER_LAKE_CHANCE", "underground lakes", BOOLEAN);
    public static final ConfigProperty ENABLE_UNDERGROUND_LAVA_LAKES = new ConfigProperty("ENABLE_UNDERGROUND_LAVA_LAKES", "underground lakes", BOOLEAN);
    public static final ConfigProperty UNDERGROUND_LAVA_LAKE_CHANCE = new ConfigProperty("UNDERGROUND_LAVA_LAKE_CHANCE", "underground lakes", BOOLEAN);
    public static final ConfigProperty GENERATE_MINESHAFTS = new ConfigProperty("GENERATE_MINESHAFTS", "mineshafts", BOOLEAN);
    public static final ConfigProperty GENERATE_ORE_COAL = new ConfigProperty("GENERATE_ORE_COAL", "ores", BOOLEAN);
    public static final ConfigProperty GENERATE_ORE_IRON = new ConfigProperty("GENERATE_ORE_IRON", "ores", BOOLEAN);
    public static final ConfigProperty GENERATE_ORE_GOLD = new ConfigProperty("GENERATE_ORE_GOLD", "ores", BOOLEAN);
    public static final ConfigProperty GENERATE_ORE_REDSTONE = new ConfigProperty("GENERATE_ORE_REDSTONE", "ores", BOOLEAN);
    public static final ConfigProperty GENERATE_ORE_LAPIS = new ConfigProperty("GENERATE_ORE_LAPIS", "ores", BOOLEAN);
    public static final ConfigProperty GENERATE_ORE_DIAMOND = new ConfigProperty("GENERATE_ORE_DIAMOND", "ores", BOOLEAN);
    public static final ConfigProperty GENERATE_ORE_EMERALD = new ConfigProperty("GENERATE_ORE_EMERALD", "ores", BOOLEAN);
    public static final ConfigProperty ENABLE_RAVINE_MODIFICATIONS = new ConfigProperty("ENABLE_RAVINE_MODIFICATIONS", "ravines", BOOLEAN);
    public static final ConfigProperty ENABLE_RAVINES = new ConfigProperty("ENABLE_RAVINES", "ravines", BOOLEAN);
    public static final ConfigProperty RAVINE_FREQUENCY = new ConfigProperty("RAVINE_FREQUENCY", "ravines", INTEGER);
    public static final ConfigProperty GENERATE_SCATTERED_FEATURES = new ConfigProperty("GENERATE_SCATTERED_FEATURES", "scattered features", BOOLEAN);
    public static final ConfigProperty MIN_DISTANCE_SCATTERED_FEATURES = new ConfigProperty("MIN_DISTANCE_SCATTERED_FEATURES", "scattered features", INTEGER);
    public static final ConfigProperty MAX_DISTANCE_SCATTERED_FEATURES = new ConfigProperty("MAX_DISTANCE_SCATTERED_FEATURES", "scattered features", INTEGER);
    public static final ConfigProperty GENERATE_OCEAN_MONUMENTS = new ConfigProperty("GENERATE_OCEAN_MONUMENTS", "ocean monuments", BOOLEAN);
    public static final ConfigProperty GENERATE_STRONGHOLDS = new ConfigProperty("GENERATE_STRONGHOLDS", "strongholds", BOOLEAN);
    public static final ConfigProperty GENERATE_VILLAGES = new ConfigProperty("GENERATE_VILLAGES", "villages", BOOLEAN);
    public static final ConfigProperty ENABLE_VILLAGE_MODIFICATIONS = new ConfigProperty("ENABLE_VILLAGE_MODIFICATIONS", "villages", BOOLEAN);
    public static final ConfigProperty VILLAGE_SIZE = new ConfigProperty("VILLAGE_SIZE", "villages", INTEGER);
    public static final ConfigProperty MIN_DISTANCE_VILLAGES = new ConfigProperty("MIN_DISTANCE_VILLAGES", "villages", INTEGER);
    public static final ConfigProperty MAX_DISTANCE_VILLAGES = new ConfigProperty("MAX_DISTANCE_VILLAGES", "villages", INTEGER);
    public static final ConfigProperty VILLAGE_CRASH_FIX = new ConfigProperty("VILLAGE_CRASH_FIX", "villages", BOOLEAN);
    public static final ConfigProperty ENABLE_SNOW_LAYERS = new ConfigProperty("ENABLE_SNOW_LAYERS", "snow", BOOLEAN);
    public static final ConfigProperty SHADOW_STONE_BLOCK = new ConfigProperty("SHADOW_STONE_BLOCK", "terrain shadowing", BLOCK);
    public static final ConfigProperty SHADOW_DESERT_BLOCK = new ConfigProperty("SHADOW_DESERT_BLOCK", "terrain shadowing", BLOCK);
    public static final ConfigProperty ALLOW_TREES_ON_SAND = new ConfigProperty("ALLOW_TREES_ON_SAND", "trees", BOOLEAN);
    public static final ConfigProperty ALLOW_SHRUBS_UNDERGROUND = new ConfigProperty("ALLOW_SHRUBS_UNDERGROUND", "trees", BOOLEAN);

    public ConfigRTG() {
        super(SupportedMod.RTG);
    }

    @Override
    public void initDefaults() {
        FLAT_BEDROCK_LAYERS.setDefault(0);
        FLAT_BEDROCK_LAYERS.setComment("0 = Normal bedrock (rough pattern); 1-5 = Number of flat bedrock layers to generate");
    }
}