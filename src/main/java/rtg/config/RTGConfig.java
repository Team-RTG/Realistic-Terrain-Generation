package rtg.config;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import net.minecraftforge.common.config.Configuration;

import rtg.config.property.ConfigProperty.Type;
import rtg.config.property.ConfigPropertyBoolean;
import rtg.config.property.ConfigPropertyFloat;
import rtg.config.property.ConfigPropertyInt;
import rtg.config.property.ConfigPropertyString;


public class RTGConfig extends Config {

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Bedrock
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyInt FLAT_BEDROCK_LAYERS;
    public final ConfigPropertyString BEDROCK_BLOCK_ID;
    public final ConfigPropertyInt BEDROCK_BLOCK_BYTE;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Biomes
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableRTGBiomeDecorations;
    public final ConfigPropertyBoolean enableRTGBiomeSurfaces;
    public final ConfigPropertyInt patchBiomeId;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Boulders
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableCobblestoneBoulders = true;
    public final ConfigPropertyInt cobblestoneBoulderChance = 1;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Caves
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableCaveModifications = true;
    public final ConfigPropertyBoolean enableCaves = true;
    public final ConfigPropertyInt caveDensity = 8;
    public final ConfigPropertyInt caveFrequency = 16;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Debugging
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableDebugging = false;
    public final ConfigPropertyBoolean crashOnStructureExceptions = false;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Dunes
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyInt duneHeight = 4;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Dungeons
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean generateDungeons = true;
    public final ConfigPropertyInt dungeonFrequency = 8;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Flowing liquids
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableFlowingLiquidModifications = true;
    public final ConfigPropertyInt flowingWaterChance = 200;
    public final ConfigPropertyInt flowingLavaChance = 200;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // GUI
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableWorldTypeNotificationScreen = true;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Lakes (Scenic)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final ConfigPropertyInt lakeSizeMultiplier = 1f; // same deal with lakes
    public final ConfigPropertyInt lakeFrequencyMultiplier = 1f;
    public final ConfigPropertyInt lakeShoreBendinessMultiplier = 1f;
    public final ConfigPropertyInt scenicLakeBiome = 7;
    public final ConfigPropertyInt scenicFrozenLakeBiome = 11;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Lakes (Surface)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableWaterSurfaceLakes = true;
    public final ConfigPropertyInt waterSurfaceLakeChance = 10;

    public final ConfigPropertyBoolean enableLavaSurfaceLakes = true;
    public final ConfigPropertyInt lavaSurfaceLakeChance = 10;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Lakes (Underground)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableWaterUndergroundLakes = true;
    public final ConfigPropertyInt waterUndergroundLakeChance = 10;

    public final ConfigPropertyBoolean enableLavaUndergroundLakes = true;
    public final ConfigPropertyInt lavaUndergroundLakeChance = 10;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Mineshafts
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean generateMineshafts = true;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Ocean monuments
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableOceanMonumentModifications = true;
    public final ConfigPropertyBoolean generateOceanMonuments = true;
    public final ConfigPropertyInt oceanMonumentSpacing = 32; // Vanilla = 32 (Minimum = 1; must be greater than separation)
    public final ConfigPropertyInt oceanMonumentSeparation = 5; // Vanilla = 5 (Minimum = 1)

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Ore gen
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean generateOreAndesite = true;
    public final ConfigPropertyBoolean generateOreCoal = true;
    public final ConfigPropertyBoolean generateOreDiamond = true;
    public final ConfigPropertyBoolean generateOreDiorite = true;
    public final ConfigPropertyBoolean generateOreDirt = true;
    public final ConfigPropertyBoolean generateOreEmerald = true;
    public final ConfigPropertyBoolean generateOreGold = true;
    public final ConfigPropertyBoolean generateOreGranite = true;
    public final ConfigPropertyBoolean generateOreGravel = true;
    public final ConfigPropertyBoolean generateOreIron = true;
    public final ConfigPropertyBoolean generateOreLapis = true;
    public final ConfigPropertyBoolean generateOreRedstone = true;
    public final ConfigPropertyBoolean generateOreSilverfish = true;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Plateaus
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final String plateauGradientBlockId = "minecraft:stained_hardened_clay";

    public final String plateauBlockId = "minecraft:hardened_clay";
    public final ConfigPropertyInt plateauBlockByte = 0;

    public final String mesaGradientString = "0,1,8,14,1,8";
    public final String mesaBryceGradientString = "-1,-1,0,1,0,0,0,14,0,8,0,1,8,0,-1,0,14,0,0,14,0,0,8";
    public final String savannaGradientString = "0,0,0,0,8,8,12,12,8,0,8,12,12,8,12,8,0,0,8,12,12";

    public final byte[] mesaPlateauBlockMetas = getPlateauGradientBlockMetasFromConfigString(mesaGradientString);
    public final byte[] mesaBrycePlateauBlockMetas = getPlateauGradientBlockMetasFromConfigString(mesaBryceGradientString);
    public final byte[] savannaPlateauBlockMetas = getPlateauGradientBlockMetasFromConfigString(savannaGradientString);

    public final ConfigPropertyBoolean stoneSavannas = true;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Ravines
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableRavineModifications = true;
    public final ConfigPropertyBoolean enableRavines = false;
    public final ConfigPropertyInt ravineFrequency = 50;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Rivers
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private final ConfigPropertyInt riverSizeMultiplier = 1f; // this is private because we want a transformed version
    public final ConfigPropertyInt riverFrequencyMultiplier = 1f;
    public final ConfigPropertyInt riverBendinessMultiplier = 1f;
    public final ConfigPropertyInt riverCutOffScale = 350f;
    public final ConfigPropertyInt riverCutOffAmplitude = 0.5f;
    public final ConfigPropertyBoolean enableLushRiverBankDecorationsInHotBiomes = true;
    public final ConfigPropertyBoolean enableLushRiverBankSurfacesInHotBiomes = true;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Saplings
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableRTGSaplings = true;
    public final ConfigPropertyInt rtgTreeChance = 2;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Scattered features
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableScatteredFeatureModifications = true;
    public final ConfigPropertyBoolean generateScatteredFeatures = true;
    public final ConfigPropertyInt minDistanceScatteredFeatures = 12; // Vanilla = 8
    public final ConfigPropertyInt maxDistanceScatteredFeatures = 48; // Vanilla = 32

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Snow
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableSnowLayers = true;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Strongholds
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableStrongholdModifications = true;
    public final ConfigPropertyBoolean generateStrongholds = true;
    public final ConfigPropertyInt strongholdCount = 128; // Vanilla = 128 (Minimum = 1)
    public final ConfigPropertyInt strongholdDistance = 32; // Vanilla = 32 (Minimum = 1)
    public final ConfigPropertyInt strongholdSpread = 3; // Vanilla = 3 (Minimum = 1)

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Terrain shadowing
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyString defaultShadowStoneBlockId = "minecraft:stained_hardened_clay";
    public final ConfigPropertyInt defaultShadowStoneBlockByte = 9;

    public final ConfigPropertyString shadowStoneBlockId = defaultShadowStoneBlockId;
    public final ConfigPropertyInt shadowStoneBlockByte = defaultShadowStoneBlockByte;

    public final ConfigPropertyString defaultShadowDesertBlockId = "minecraft:stained_hardened_clay";
    public final ConfigPropertyInt defaultShadowDesertBlockByte = 0;

    public final ConfigPropertyString shadowDesertBlockId = defaultShadowDesertBlockId;
    public final ConfigPropertyInt shadowDesertBlockByte = defaultShadowDesertBlockByte;

    public final ConfigPropertyBoolean enableUBCStoneShadowing = true;
    public final ConfigPropertyBoolean enableUBCDesertShadowing = true;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Trees
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean allowTreesToGenerateOnSand = true;
    public final ConfigPropertyBoolean allowShrubsToGenerateBelowSurface = true;
    public final ConfigPropertyBoolean allowBarkCoveredLogs = true;

    public final final ConfigPropertyInt MAX_TREE_DENSITY = 5f;
    public final ConfigPropertyInt treeDensityMultiplier = 1.0f;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Villages
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean generateVillages = true;

    public final ConfigPropertyBoolean enableVillageModifications = enableVillageTweaks();
    public final ConfigPropertyInt villageSize = 0;
    public final ConfigPropertyInt minDistanceVillages = 12; // Vanilla = 8
    public final ConfigPropertyInt maxDistanceVillages = 48; // Vanilla = 32

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Volcanoes
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // These constants are used as fallbacks when generating volcanoes, in case the user enters an invalid block ID.
    public final final IBlockState volcanoBlock = Blocks.OBSIDIAN.getDefaultState();
    public final final IBlockState volcanoMix1Block = Blocks.COBBLESTONE.getDefaultState();
    public final final IBlockState volcanoMix2Block = Blocks.GRAVEL.getDefaultState();
    public final final IBlockState volcanoMix3Block = Blocks.COAL_BLOCK.getDefaultState();

    public final ConfigPropertyString volcanoBlockId = "minecraft:obsidian";
    public final ConfigPropertyInt volcanoBlockMeta = 0;
    public final ConfigPropertyString volcanoMix1BlockId = "minecraft:cobblestone";
    public final ConfigPropertyInt volcanoMix1BlockMeta = 0;
    public final ConfigPropertyString volcanoMix2BlockId = "minecraft:gravel";
    public final ConfigPropertyInt volcanoMix2BlockMeta = 0;
    public final ConfigPropertyString volcanoMix3BlockId = "minecraft:coal_block";
    public final ConfigPropertyInt volcanoMix3BlockMeta = 0;

    public final ConfigPropertyBoolean enableVolcanoes = true;
    public final ConfigPropertyBoolean enableVolcanoEruptions = true;
    public final ConfigPropertyInt volcanoChance = 48;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public RTGConfig(String modID) {

        this.modID = modID;

        /*
         * GLOBAL CONFIGS
         */

        ALLOW_VILLAGES = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Villages", "", true);
        this.addProperty(ALLOW_VILLAGES);

        ALLOW_VOLCANOES = new ConfigPropertyBoolean(Type.BOOLEAN, "Allow Volcanoes", "", false);
        this.addProperty(ALLOW_VOLCANOES);

        VOLCANO_CHANCE = new ConfigPropertyInt(Type.INTEGER, "Volcano Chance", "1/x chance that a volcano will generate if this biome has volcanoes enabled." + Configuration.NEW_LINE + "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable volcanoes for this biome." + Configuration.NEW_LINE, -1, -1, Integer.MAX_VALUE);
        this.addProperty(VOLCANO_CHANCE);

        USE_RTG_DECORATIONS = new ConfigPropertyBoolean(Type.BOOLEAN, "Use RTG Decorations", "", true);
        this.addProperty(USE_RTG_DECORATIONS);

        USE_RTG_SURFACES = new ConfigPropertyBoolean(Type.BOOLEAN, "Use RTG Surfaces", "", true);
        this.addProperty(USE_RTG_SURFACES);

        SURFACE_TOP_BLOCK = new ConfigPropertyString(Type.STRING, "RTG Surface: Top Block", "", "");
        this.addProperty(SURFACE_TOP_BLOCK);

        SURFACE_TOP_BLOCK_META = new ConfigPropertyInt(Type.INTEGER, "RTG Surface: Top Block Meta", "", 0, 0, 15);
        this.addProperty(SURFACE_TOP_BLOCK_META);

        SURFACE_FILLER_BLOCK = new ConfigPropertyString(Type.STRING, "RTG Surface: Filler Block", "", "");
        this.addProperty(SURFACE_FILLER_BLOCK);

        SURFACE_FILLER_BLOCK_META = new ConfigPropertyInt(Type.INTEGER, "RTG Surface: Filler Block Meta", "", 0, 0, 15);
        this.addProperty(SURFACE_FILLER_BLOCK_META);

        CAVE_DENSITY = new ConfigPropertyInt(Type.INTEGER, "Cave Density", "This setting controls the size of caves." + Configuration.NEW_LINE + "HIGHER values = BIGGER caves & MORE lag. (14 = vanilla cave density)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome." + Configuration.NEW_LINE, -1, -1, 40);
        this.addProperty(CAVE_DENSITY);

        CAVE_FREQUENCY = new ConfigPropertyInt(Type.INTEGER, "Cave Frequency", "This setting controls the number of caves that generate." + Configuration.NEW_LINE + "LOWER values = MORE caves & MORE lag. (6 = vanilla cave frequency)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable caves for this biome." + Configuration.NEW_LINE, -1, -1, 40);
        this.addProperty(CAVE_FREQUENCY);

        RAVINE_FREQUENCY = new ConfigPropertyInt(Type.INTEGER, "Ravine Frequency", "This setting controls the number of ravines that generate." + Configuration.NEW_LINE + "LOWER values = MORE ravines & MORE lag. (50 = vanilla ravine frequency)" + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable ravines for this biome." + Configuration.NEW_LINE, -1, -1, 100);
        this.addProperty(RAVINE_FREQUENCY);

        BEACH_BIOME = new ConfigPropertyInt(
            Type.INTEGER, "Beach Biome",
            "Biome ID to use for this biome's beach."
                + Configuration.NEW_LINE
                + "The only 'officially supported' values for this setting are:"
                + Configuration.NEW_LINE
                + "-1 = Automatic beach generation (RECOMMENDED)"
                + Configuration.NEW_LINE
                + "16 = Vanilla Beach"
                + Configuration.NEW_LINE
                + "26 = Vanilla Cold Beach"
                + Configuration.NEW_LINE
                + "25 = Vanilla Stone Beach"
                + Configuration.NEW_LINE
                + "The ID of this biome = No beach"
                + Configuration.NEW_LINE
                + "Other biome IDs are allowed, but they have not been tested, may yield undesirable results, and will not be supported."
                + Configuration.NEW_LINE
                + "Note: If this biome has been hardcoded by RTG to use a specific beach, this setting will have no effect."
                + Configuration.NEW_LINE,
            -1, -1, 255
        );
        this.addProperty(BEACH_BIOME);

        TREE_DENSITY_MULTIPLIER = new ConfigPropertyFloat(
            Type.FLOAT,
            "RTG Tree Density Multiplier",
            "This setting allows you to increase/decrease the number of RTG trees that generate in this biome."
                + Configuration.NEW_LINE +
                "This setting overrides the global setting (see /.minecraft/config/RTG/rtg.cfg) and only affects trees generated by RTG."
                + Configuration.NEW_LINE +
                "Trees generated by this biome's decorator will adhere to their own density rules."
                + Configuration.NEW_LINE +
                "Set to -1.0 to use the global setting."
                + Configuration.NEW_LINE +
                "1.0 = Default tree generation; 2.0 = Twice as many trees; 0.5 = half as many trees; 0 = No trees"
                + Configuration.NEW_LINE,
            -1.0f, -1.0f, 5.0f
        );
        this.addProperty(TREE_DENSITY_MULTIPLIER);

        /*
         * OPTIONAL CONFIGS
         */

        ALLOW_LOGS = new ConfigPropertyBoolean(Type.BOOLEAN, "RTG Decoration: Logs", "", true);
        SURFACE_MIX_BLOCK = new ConfigPropertyString(Type.STRING, "RTG Surface: Mix Block", "", "");
        SURFACE_MIX_BLOCK_META = new ConfigPropertyInt(Type.INTEGER, "RTG Surface: Mix Block Meta", "", 0, 0, 15);
        SURFACE_MIX_FILLER_BLOCK = new ConfigPropertyString(Type.STRING, "RTG Surface: Mix Filler Block", "", "");
        SURFACE_MIX_FILLER_BLOCK_META = new ConfigPropertyInt(Type.INTEGER, "RTG Surface: Mix Filler Block Meta", "", 0, 0, 15);
        ALLOW_PALM_TREES = new ConfigPropertyBoolean(Type.BOOLEAN, "RTG Decoration: Palm Trees", "", true);
        ALLOW_CACTUS = new ConfigPropertyBoolean(Type.BOOLEAN, "RTG Decoration: Cactus", "", true);
        ALLOW_COBWEBS = new ConfigPropertyBoolean(Type.BOOLEAN, "RTG Decoration: Cobwebs", "", true);
        ALLOW_WHEAT = new ConfigPropertyBoolean(Type.BOOLEAN, "RTG Decoration: Wheat", "", true);
        WHEAT_CHANCE = new ConfigPropertyInt(Type.INTEGER, "RTG Decoration: Wheat (Chance)", "", 0, 0, Integer.MAX_VALUE);
        WHEAT_MIN_Y = new ConfigPropertyInt(Type.INTEGER, "RTG Decoration: Wheat (Min Y)", "", 0, 0, Integer.MAX_VALUE);
        WHEAT_MAX_Y = new ConfigPropertyInt(Type.INTEGER, "RTG Decoration: Wheat (Max Y)", "", 0, 0, Integer.MAX_VALUE);
    }
}
