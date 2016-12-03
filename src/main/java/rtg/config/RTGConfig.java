package rtg.config;

import java.util.ArrayList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import net.minecraftforge.common.config.Configuration;

import org.apache.commons.lang3.ArrayUtils;

import rtg.config.property.*;

public class RTGConfig extends Config {

    public static String DEFAULT_SHADOW_STONE_BLOCK_ID = "minecraft:stained_hardened_clay";
    public static int DEFAULT_SHADOW_STONE_BLOCK_META = 9;
    public static String DEFAULT_SHADOW_DESERT_BLOCK_ID = "minecraft:stained_hardened_clay";
    public static int DEFAULT_SHADOW_DESERT_BLOCK_META = 0;

    public static final float MAX_TREE_DENSITY = 5f;

    // These constants are used as fallbacks when generating volcanoes, in case the user enters an invalid block ID.
    public static final IBlockState DEFAULT_VOLCANO_BLOCK = Blocks.OBSIDIAN.getDefaultState();
    public static final IBlockState DEFAULT_VOLCANO_MIX1_BLOCK = Blocks.COBBLESTONE.getDefaultState();
    public static final IBlockState DEFAULT_VOLCANO_MIX2_BLOCK = Blocks.GRAVEL.getDefaultState();
    public static final IBlockState DEFAULT_VOLCANO_MIX3_BLOCK = Blocks.COAL_BLOCK.getDefaultState();

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Bedrock
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyInt flatBedrockLayers;
    public final ConfigPropertyString bedrockBlockId;
    public final ConfigPropertyInt bedrockBlockByte;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Biomes
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableRTGBiomeDecorations;
    public final ConfigPropertyBoolean enableRTGBiomeSurfaces;
    public final ConfigPropertyInt patchBiomeId;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Boulders
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableCobblestoneBoulders;
    public final ConfigPropertyInt cobblestoneBoulderChance;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Caves
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableCaveModifications;
    public final ConfigPropertyBoolean enableCaves;
    public final ConfigPropertyInt caveDensity;
    public final ConfigPropertyInt caveFrequency;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Debugging
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableDebugging;
    public final ConfigPropertyBoolean crashOnStructureExceptions;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Dunes
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyInt duneHeight;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Dungeons
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean generateDungeons;
    public final ConfigPropertyInt dungeonFrequency;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Flowing liquids
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableFlowingLiquidModifications;
    public final ConfigPropertyInt flowingLavaChance;
    public final ConfigPropertyInt flowingWaterChance;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // GUI
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableWorldTypeNotificationScreen;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Lakes (Scenic)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyFloat lakeSizeMultiplier;
    public final ConfigPropertyFloat lakeFrequencyMultiplier;
    public final ConfigPropertyFloat lakeShoreBendinessMultiplier;
    public final ConfigPropertyInt scenicLakeBiome;
    public final ConfigPropertyInt scenicFrozenLakeBiome;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Lakes (Surface)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableWaterSurfaceLakes;
    public final ConfigPropertyInt waterSurfaceLakeChance;
    public final ConfigPropertyBoolean enableLavaSurfaceLakes;
    public final ConfigPropertyInt lavaSurfaceLakeChance;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Lakes (Underground)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableWaterUndergroundLakes;
    public final ConfigPropertyInt waterUndergroundLakeChance;
    public final ConfigPropertyBoolean enableLavaUndergroundLakes;
    public final ConfigPropertyInt lavaUndergroundLakeChance;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Mineshafts
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean generateMineshafts;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Ocean monuments
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableOceanMonumentModifications;
    public final ConfigPropertyBoolean generateOceanMonuments;
    public final ConfigPropertyInt oceanMonumentSpacing;
    public final ConfigPropertyInt oceanMonumentSeparation;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Ore gen
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean generateOreAndesite;
    public final ConfigPropertyBoolean generateOreCoal;
    public final ConfigPropertyBoolean generateOreDiamond;
    public final ConfigPropertyBoolean generateOreDiorite;
    public final ConfigPropertyBoolean generateOreDirt;
    public final ConfigPropertyBoolean generateOreEmerald;
    public final ConfigPropertyBoolean generateOreGold;
    public final ConfigPropertyBoolean generateOreGranite;
    public final ConfigPropertyBoolean generateOreGravel;
    public final ConfigPropertyBoolean generateOreIron;
    public final ConfigPropertyBoolean generateOreLapis;
    public final ConfigPropertyBoolean generateOreRedstone;
    public final ConfigPropertyBoolean generateOreSilverfish;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Plateaus
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyString plateauGradientBlockId;
    public final ConfigPropertyString mesaBryceGradientString;
    public final ConfigPropertyString mesaGradientString;
    public final ConfigPropertyString savannaGradientString;
    public final ConfigPropertyString plateauBlockId;
    public final ConfigPropertyInt plateauBlockByte;
    public final ConfigPropertyBoolean stoneSavannas;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Ravines
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableRavineModifications;
    public final ConfigPropertyBoolean enableRavines;
    public final ConfigPropertyInt ravineFrequency;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Rivers
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyFloat riverSizeMultiplier;
    public final ConfigPropertyFloat riverFrequencyMultiplier;
    public final ConfigPropertyFloat riverBendinessMultiplier;
    public final ConfigPropertyFloat riverCutOffScale;
    public final ConfigPropertyFloat riverCutOffAmplitude;
    public final ConfigPropertyBoolean enableLushRiverBankDecorationsInHotBiomes;
    public final ConfigPropertyBoolean enableLushRiverBankSurfacesInHotBiomes;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Saplings
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableRTGSaplings;
    public final ConfigPropertyInt rtgTreeChance;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Scattered features
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableScatteredFeatureModifications;
    public final ConfigPropertyBoolean generateScatteredFeatures;
    public final ConfigPropertyInt minDistanceScatteredFeatures;
    public final ConfigPropertyInt maxDistanceScatteredFeatures;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Snow
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableSnowLayers;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Strongholds
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableStrongholdModifications;
    public final ConfigPropertyBoolean generateStrongholds;
    public final ConfigPropertyInt strongholdCount;
    public final ConfigPropertyInt strongholdDistance;
    public final ConfigPropertyInt strongholdSpread;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Terrain shadowing
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyString shadowStoneBlockId;
    public final ConfigPropertyInt shadowStoneBlockByte;
    public final ConfigPropertyString shadowDesertBlockId;
    public final ConfigPropertyInt shadowDesertBlockByte;
    public final ConfigPropertyBoolean enableUBCStoneShadowing;
    public final ConfigPropertyBoolean enableUBCDesertShadowing;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Trees
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean allowTreesToGenerateOnSand;
    public final ConfigPropertyBoolean allowShrubsToGenerateBelowSurface;
    public final ConfigPropertyBoolean allowBarkCoveredLogs;
    public final ConfigPropertyFloat treeDensityMultiplier;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Villages
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyBoolean enableVillageModifications;
    public final ConfigPropertyBoolean generateVillages;
    public final ConfigPropertyInt villageSize;
    public final ConfigPropertyInt minDistanceVillages;
    public final ConfigPropertyInt maxDistanceVillages;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Volcanoes
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final ConfigPropertyString volcanoBlockId;
    public final ConfigPropertyInt volcanoBlockMeta;
    public final ConfigPropertyString volcanoMix1BlockId;
    public final ConfigPropertyInt volcanoMix1BlockMeta;
    public final ConfigPropertyString volcanoMix2BlockId;
    public final ConfigPropertyInt volcanoMix2BlockMeta;
    public final ConfigPropertyString volcanoMix3BlockId;
    public final ConfigPropertyInt volcanoMix3BlockMeta;
    public final ConfigPropertyBoolean enableVolcanoes;
    public final ConfigPropertyBoolean enableVolcanoEruptions;
    public final ConfigPropertyInt volcanoChance;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public RTGConfig() {

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Bedrock
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        flatBedrockLayers = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Number of flat bedrock layers",
            "Bedrock",
            "0 = Normal bedrock (rough pattern); 1-5 = Number of flat bedrock layers to generate",
            0, 0, 5
        );
        this.addProperty(flatBedrockLayers);

        bedrockBlockId = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Bedrock block ID",
            "Bedrock",
            "The block to use for the bottom of the Overworld.",
            "minecraft:bedrock"
        );
        this.addProperty(bedrockBlockId);

        bedrockBlockByte = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Bedrock block meta value",
            "Bedrock",
            "The meta value of the bedrock block.",
            0, 0, 15
        );
        this.addProperty(bedrockBlockByte);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Biomes
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableRTGBiomeDecorations = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable RTG Biome Decorations",
            "Biomes",
            "If TRUE, uses the individual biome settings in the biome config files. If FALSE, disables all RTG decorations and uses vanilla decorations instead.",
            true
        );
        this.addProperty(enableRTGBiomeDecorations);

        enableRTGBiomeSurfaces = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable RTG Biome Surfaces",
            "Biomes",
            "If TRUE, uses the individual biome settings in the biome config files. If FALSE, disables all RTG surfaces and uses vanilla surfaces instead.",
            true
        );
        this.addProperty(enableRTGBiomeSurfaces);

        patchBiomeId = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Patch Biome ID",
            "Biomes",
            "If RTG tries to generate an unsupported biome or a biome that has an ID conflict, it will generate this biome instead."
                + Configuration.NEW_LINE +
                "If set to -1, RTG will crash instead of generating the patch biome. You might want to do this if you're making a mod pack"
                + Configuration.NEW_LINE +
                "and want to make sure all biomes are generating correctly."
                + Configuration.NEW_LINE +
                "Default = Vanilla Plains",
            1, -1, 255
        );
        this.addProperty(patchBiomeId);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Boulders
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableCobblestoneBoulders = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN, "Enable Cobblestone Boulders", "Boulders", "", true
        );
        this.addProperty(enableCobblestoneBoulders);

        cobblestoneBoulderChance = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "1/x chance that Cobblestone Boulders will generate if given the opportunity to do so during world gen",
            "Boulders",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            1, 1, 100
        );
        this.addProperty(cobblestoneBoulderChance);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Caves
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableCaveModifications = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Cave Modifications",
            "Caves",
            "Must be set to TRUE for the other cave settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with cave generation at all."
                + Configuration.NEW_LINE +
                "WARNING! Setting this to FALSE may result in unpredictable cave generation.",
            true
        );
        this.addProperty(enableCaveModifications);

        enableCaves = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Enable Caves", "Caves", "", true);
        this.addProperty(enableCaves);

        caveDensity = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Cave Density",
            "Caves",
            "This setting controls the size of caves."
                + Configuration.NEW_LINE + "HIGHER values = BIGGER caves & MORE lag. (14 = vanilla cave density)",
            8, 1, 40
        );
        this.addProperty(caveDensity);

        caveFrequency = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Cave Frequency",
            "Caves",
            "This setting controls the number of caves that generate."
                + Configuration.NEW_LINE + "LOWER values = MORE caves & MORE lag. (6 = vanilla cave frequency)",
            16, 1, 40
        );
        this.addProperty(caveFrequency);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Debugging
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableDebugging = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Debugging",
            "Debugging",
            "WARNING: This should only be enabled if you know what you're doing.",
            false
        );
        this.addProperty(enableDebugging);

        crashOnStructureExceptions = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Crash on Structure Exceptions",
            "Debugging",
            "Instead of crashing when it experiences 'java.util.ConcurrentModificationException' (or any other exception)"
                + Configuration.NEW_LINE +
                "during structure generation, RTG will stop trying to generate that structure and continue generating the world."
                + Configuration.NEW_LINE +
                "You should only set this to TRUE if you have been instructed to do so by an RTG developer, or if you know what you're doing.",
            false
        );
        this.addProperty(crashOnStructureExceptions);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Dunes
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        duneHeight = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Height of Dunes",
            "Dunes",
            "This setting controls the height of both sand dunes and snow dunes."
                + Configuration.NEW_LINE + "Higher values = taller dunes.",
            4, 1, 12
        );
        this.addProperty(duneHeight);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Dungeons
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        generateDungeons = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Dungeons", "Dungeons", "", true);
        this.addProperty(generateDungeons);

        dungeonFrequency = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Dungeon Frequency",
            "Dungeons",
            "This setting controls the number of dungeons that generate."
                + Configuration.NEW_LINE + "HIGHER values = MORE dungeons & MORE lag. (8 = vanilla dungeon frequency)",
            8, 1, 200
        );
        this.addProperty(dungeonFrequency);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Flowing liquids
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableFlowingLiquidModifications = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Flowing Liquid Modifications",
            "Flowing Liquids",
            "Must be set to TRUE for the other flowing liquid settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with flowing liquid generation at all."
                + Configuration.NEW_LINE +
                "(Flowing liquids are the water/lava streams that generate on the sides of hills and mountains.)",
            true
        );
        this.addProperty(enableFlowingLiquidModifications);

        flowingLavaChance = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Flowing Lava Chance",
            "Flowing Liquids",
            "1/x chance that a lava stream will generate on the side of a hill or mountain."
                + Configuration.NEW_LINE +
                "0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            200, 0, Integer.MAX_VALUE
        );
        this.addProperty(flowingLavaChance);

        flowingWaterChance = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Flowing Water Chance",
            "Flowing Liquids",
            "1/x chance that a water stream will generate on the side of a hill or mountain."
                + Configuration.NEW_LINE +
                "0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            200, 0, Integer.MAX_VALUE
        );
        this.addProperty(flowingWaterChance);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // GUI
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableWorldTypeNotificationScreen = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable World Type Notification Screen",
            "GUI",
            "",
            true
        );
        this.addProperty(enableWorldTypeNotificationScreen);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Lakes (Scenic)
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        lakeSizeMultiplier = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "Lake Size Multiplier",
            "Lakes (Scenic)",
            "Defaults to 1 (standard size)",
            1, 0, 10
        );
        this.addProperty(lakeSizeMultiplier);

        lakeFrequencyMultiplier = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "Lake Frequency Multiplier",
            "Lakes (Scenic)",
            "Defaults to 1 (standard frequency)",
            1, 0, 10
        );
        this.addProperty(lakeFrequencyMultiplier);

        lakeShoreBendinessMultiplier = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "Lake Shore Irregularity",
            "Lakes (Scenic)",
            "Makes scenic lake shores bend and curve more. Defaults to 1",
            1, 0, 2
        );
        this.addProperty(lakeShoreBendinessMultiplier);

        scenicLakeBiome = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Biome for scenic lakes",
            "Lakes (Scenic)",
            "Biome ID for scenic lakes when not frozen (default 7 = River)",
            7, 0, 254
        );
        this.addProperty(scenicLakeBiome);

        scenicFrozenLakeBiome = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Biome for frozen scenic lakes",
            "Lakes (Scenic)",
            "Biome ID for scenic lakes when frozen (default 11 = Frozen River)",
            11, 0, 254
        );
        this.addProperty(scenicFrozenLakeBiome);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Lakes (Surface)
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableWaterSurfaceLakes = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Water Surface Lakes",
            "Lakes (Surface)",
            "",
            true
        );
        this.addProperty(enableWaterSurfaceLakes);

        waterSurfaceLakeChance = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "1/x chance that Water Surface Lakes will generate if given the opportunity to do so during world gen",
            "Lakes (Surface)",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            10, 1, 100
        );
        this.addProperty(waterSurfaceLakeChance);

        enableLavaSurfaceLakes = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Lava Surface Lakes",
            "Lakes (Surface)",
            "",
            true
        );
        this.addProperty(enableLavaSurfaceLakes);

        lavaSurfaceLakeChance = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "1/x chance that Lava Surface Lakes will generate if given the opportunity to do so during world gen",
            "Lakes (Surface)",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            10, 1, 100
        );
        this.addProperty(lavaSurfaceLakeChance);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Lakes (Underground)
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableWaterUndergroundLakes = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Water Underground Lakes",
            "Lakes (Underground)",
            "",
            true
        );
        this.addProperty(enableWaterUndergroundLakes);

        waterUndergroundLakeChance = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "1/x chance that Water Underground Lakes will generate if given the opportunity to do so during world gen",
            "Lakes (Underground)",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            10, 1, 100
        );
        this.addProperty(waterUndergroundLakeChance);

        enableLavaUndergroundLakes = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Lava Underground Lakes",
            "Lakes (Underground)",
            "",
            true
        );
        this.addProperty(enableLavaUndergroundLakes);

        lavaUndergroundLakeChance = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "1/x chance that Lava Underground Lakes will generate if given the opportunity to do so during world gen",
            "Lakes (Underground)",
            "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            10, 1, 100
        );
        this.addProperty(lavaUndergroundLakeChance);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Mineshafts
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        generateMineshafts = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN, "Generate Mineshafts", "Mineshafts", "", true
        );
        this.addProperty(generateMineshafts);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Ocean monuments
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableOceanMonumentModifications = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Ocean Monument Modifications",
            "Ocean Monuments",
            "Must be set to TRUE for the other ocean monument settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with ocean monument generation at all."
                + Configuration.NEW_LINE +
                "WARNING! Setting this to FALSE may result in ocean monuments generating in unpredictable locations, including those outside of oceanic biomes.",
            true
        );
        this.addProperty(enableOceanMonumentModifications);

        generateOceanMonuments = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN, "Generate Ocean Monuments", "Ocean Monuments", "", true
        );
        this.addProperty(generateOceanMonuments);

        oceanMonumentSpacing = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Ocean Monument Spacing",
            "Ocean Monuments",
            "This setting determines the size of the grid, in chunks, on which ocean monuments are generated."
                + Configuration.NEW_LINE +
                "LOWER values = MORE monuments & MORE lag. (32 = Vanilla spacing)"
                + Configuration.NEW_LINE +
                "This value MUST be greater than the 'separation' value.",
            32, 1, 1024
        );
        this.addProperty(oceanMonumentSpacing);

        oceanMonumentSeparation = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Ocean Monument Separation",
            "Ocean Monuments",
            "This setting determines the minimum distance, in chunks, between ocean monuments."
                + Configuration.NEW_LINE +
                "LOWER values = MORE monuments & MORE lag. (5 = Vanilla separation)"
                + Configuration.NEW_LINE +
                "This value MUST be less than the 'spacing' value.",
            5, 1, Integer.MAX_VALUE
        );
        this.addProperty(oceanMonumentSeparation);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Ore gen
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        generateOreAndesite = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Andesite Ore", "Ore Gen", "", true);
        this.addProperty(generateOreAndesite);

        generateOreCoal = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Coal Ore", "Ore Gen", "", true);
        this.addProperty(generateOreCoal);

        generateOreDiamond = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Diamond Ore", "Ore Gen", "", true);
        this.addProperty(generateOreDiamond);

        generateOreDiorite = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Diorite Ore", "Ore Gen", "", true);
        this.addProperty(generateOreDiorite);

        generateOreDirt = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Dirt Ore", "Ore Gen", "", true);
        this.addProperty(generateOreDirt);

        generateOreEmerald = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Emerald Ore", "Ore Gen", "", true);
        this.addProperty(generateOreEmerald);

        generateOreGold = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Gold Ore", "Ore Gen", "", true);
        this.addProperty(generateOreGold);

        generateOreGranite = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Granite Ore", "Ore Gen", "", true);
        this.addProperty(generateOreGranite);

        generateOreGravel = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Gravel Ore", "Ore Gen", "", true);
        this.addProperty(generateOreGravel);

        generateOreIron = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Iron Ore", "Ore Gen", "", true);
        this.addProperty(generateOreIron);

        generateOreLapis = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Lapis Ore", "Ore Gen", "", true);
        this.addProperty(generateOreLapis);

        generateOreRedstone = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Redstone Ore", "Ore Gen", "", true);
        this.addProperty(generateOreRedstone);

        generateOreSilverfish = new ConfigPropertyBoolean(ConfigProperty.Type.BOOLEAN, "Generate Silverfish Ore", "Ore Gen", "", true);
        this.addProperty(generateOreSilverfish);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Plateaus
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        plateauGradientBlockId = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Gradient Plateau Block ID",
            "Plateaus",
            "The block to use for Mesa & Savanna plateau gradients. Defaults to stained hardened clay." +
                Configuration.NEW_LINE +
                "This can be any block, but it works best with blocks that have multiple colours, such as stained hardened clay." +
                Configuration.NEW_LINE +
                "The various 'meta' options in this section will use this block to configure the plateau gradients.",
            "minecraft:stained_hardened_clay"
        );
        this.addProperty(plateauGradientBlockId);

        mesaBryceGradientString = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Gradient Plateau Block Meta Values (Mesa Bryce)",
            "Plateaus",
            getPlateauGradientBlockMetasComment("Mesa Bryce biome"),
            "-1,-1,0,1,0,0,0,14,0,8,0,1,8,0,-1,0,14,0,0,14,0,0,8"
        );
        this.addProperty(mesaBryceGradientString);

        mesaGradientString = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Gradient Plateau Block Meta Values (Mesa)",
            "Plateaus",
            getPlateauGradientBlockMetasComment("Mesa biome variants (doesn't include Mesa Bryce)"),
            "0,1,8,14,1,8"
        );
        this.addProperty(mesaGradientString);

        savannaGradientString = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Gradient Plateau Block Meta Values (Savanna)",
            "Plateaus",
            getPlateauGradientBlockMetasComment("Savanna biome variants"),
            "0,0,0,0,8,8,12,12,8,0,8,12,12,8,12,8,0,0,8,12,12"
        );
        this.addProperty(savannaGradientString);

        plateauBlockId = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Plateau Block ID",
            "Plateaus",
            "An extra block to use for Mesa & Savanna plateau gradients. Defaults to hardened clay." +
                Configuration.NEW_LINE +
                "When configuring the various 'meta' options in this section, use a value of '-1' to reference this block.",
            "minecraft:hardened_clay"
        );
        this.addProperty(plateauBlockId);

        plateauBlockByte = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Plateau Block Meta Value",
            "Plateaus",
            "The meta value of the plateau block.",
            0, 0, 15
        );
        this.addProperty(plateauBlockByte);

        stoneSavannas = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Use stone for most Savanna biome variants",
            "Plateaus",
            "If set to TRUE, Savanna biome variants will mostly use stone/cobblestone instead of gradient blocks for cliffs and plateaus."
                + Configuration.NEW_LINE +
                "Savanna Plateau M will always use gradient blocks.",
            true
        );
        this.addProperty(stoneSavannas);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Ravines
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableRavineModifications = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Ravine Modifications",
            "Ravines",
            "Must be set to TRUE for the other ravine settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with ravine generation at all."
                + Configuration.NEW_LINE +
                "WARNING! Setting this to FALSE may result in unpredictable ravine generation.",
            true
        );
        this.addProperty(enableRavineModifications);

        enableRavines = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Ravines",
            "Ravines",
            "",
            false
        );
        this.addProperty(enableRavines);

        ravineFrequency = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Ravine Frequency",
            "Ravines",
            "This setting controls the number of ravines that generate."
                + Configuration.NEW_LINE + "LOWER values = MORE ravines & MORE lag. (50 = vanilla ravine frequency)",
            50, 1, 100
        );
        this.addProperty(ravineFrequency);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Rivers
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        riverSizeMultiplier = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "River Width Multiplier",
            "Rivers",
            "Defaults to 1 (standard width)",
            1, 0, 10
        );
        this.addProperty(riverSizeMultiplier);

        riverFrequencyMultiplier = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "River Frequency Multiplier",
            "Rivers",
            "Multiplier to river widths. Defaults to 1",
            1, 0, 10
        );
        this.addProperty(riverFrequencyMultiplier);

        riverBendinessMultiplier = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "Multiplier to River Bending",
            "Rivers",
            "Higher numbers make rivers bend more. Defaults to 1",
            1, 0, 2
        );
        this.addProperty(riverBendinessMultiplier);

        riverCutOffScale = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "Scale of Large-Scale River Cut Off",
            "Rivers",
            "Higher numbers make grassy areas near rivers bigger, but also more rare. Defaults to 350",
            350, 50, 5000
        );
        this.addProperty(riverCutOffScale);

        riverCutOffAmplitude = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "Amplitude of Large-Scale River Cut Off",
            "Rivers",
            "Higher numbers make the large-scale cut-off noise have a greater effect. Defaults to 0.5",
            0.5f, 0, 2
        );
        this.addProperty(riverCutOffAmplitude);

        enableLushRiverBankDecorationsInHotBiomes = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Lush River Bank Decorations in Hot Biomes",
            "Rivers",
            "Set this to FALSE to prevent RTG from generating lush river bank decorations in hot biomes, like Desert and Mesa."
                + Configuration.NEW_LINE +
                "Lush decorations consist of tallgrass, trees, shrubs, and other flora.",
            true
        );
        this.addProperty(enableLushRiverBankDecorationsInHotBiomes);

        enableLushRiverBankSurfacesInHotBiomes = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Lush River Bank Surfaces in Hot Biomes",
            "Rivers",
            "Set this to FALSE to prevent RTG from generating lush river bank surfaces in hot biomes, like Desert and Mesa."
                + Configuration.NEW_LINE +
                "Lush surfaces consist (almost exclusively) of grass blocks.",
            true
        );
        this.addProperty(enableLushRiverBankSurfacesInHotBiomes);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Saplings
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableRTGSaplings = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable RTG Saplings",
            "Saplings",
            "Set this to TRUE to allow RTG's custom trees to grow from vanilla saplings."
                + Configuration.NEW_LINE +
                "RTG's custom trees can be grown only from the saplings that their leaves would drop naturally, and only in the biomes where they naturally generate."
                + Configuration.NEW_LINE +
                "For example, you can only grow a Swamp Willow in a Swamp biome, and only with an Oak sapling (because Swamp Willows have Oak leaves).",
            true
        );
        this.addProperty(enableRTGSaplings);

        rtgTreeChance = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "RTG Tree from Vanilla Sapling Chance",
            "Saplings",
            "1/x chance that a vanilla sapling will grow one of RTG's custom trees."
                + Configuration.NEW_LINE +
                "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            2, 1, Integer.MAX_VALUE
        );
        this.addProperty(rtgTreeChance);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Scattered features
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableScatteredFeatureModifications = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Scattered Feature Modifications",
            "Scattered Features",
            "Must be set to TRUE for the other scattered feature settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with scattered feature generation at all."
                + Configuration.NEW_LINE +
                "WARNING! Setting this to FALSE may result in unpredictable scattered feature generation.",
            true
        );
        this.addProperty(enableScatteredFeatureModifications);

        generateScatteredFeatures = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Generate Scattered Features",
            "Scattered Features",
            "",
            true
        );
        this.addProperty(generateScatteredFeatures);

        minDistanceScatteredFeatures = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Minimum distance between scattered features",
            "Scattered Features",
            "Scattered features = desert temples, jungle temples, and witch huts; 8 = Vanilla",
            12, 2, Integer.MAX_VALUE
        );
        this.addProperty(minDistanceScatteredFeatures);

        maxDistanceScatteredFeatures = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Maximum distance between scattered features",
            "Scattered Features",
            "Scattered features = desert temples, jungle temples, and witch huts; 32 = Vanilla",
            48, 4, Integer.MAX_VALUE
        );
        this.addProperty(maxDistanceScatteredFeatures);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Snow
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableSnowLayers = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Snow Layers",
            "Snow",
            "This applies to newly-generated chunks only. Snow layers will still appear in cold/snowy biomes after it snows.",
            true
        );
        this.addProperty(enableSnowLayers);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Strongholds
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableStrongholdModifications = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable Stronghold Modifications",
            "Strongholds",
            "Must be set to TRUE for the other stronghold settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with stronghold generation at all."
                + Configuration.NEW_LINE +
                "WARNING! Setting this to FALSE may result in unpredictable stronghold generation.",
            true
        );
        this.addProperty(enableStrongholdModifications);

        generateStrongholds = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Generate Strongholds",
            "Strongholds",
            "",
            true
        );
        this.addProperty(generateStrongholds);

        strongholdCount = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Stronghold Count",
            "Strongholds",
            "This setting is the number of strongholds that exist per world."
                + Configuration.NEW_LINE +
                "HIGHER values = MORE strongholds & MORE lag. (3 = Vanilla)",
            128, 1, Integer.MAX_VALUE
        );
        this.addProperty(strongholdCount);

        strongholdDistance = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Stronghold Distance",
            "Strongholds",
            "This setting determines how far strongholds are from the spawn and other strongholds."
                + Configuration.NEW_LINE +
                "LOWER values = MORE strongholds & MORE lag. (32 = Vanilla)",
            32, 1, Integer.MAX_VALUE
        );
        this.addProperty(strongholdDistance);

        strongholdSpread = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Stronghold Spread",
            "Strongholds",
            "This setting determines how concentrated strongholds are around the spawn."
                + Configuration.NEW_LINE +
                "LOWER values = LOWER concentration around spawn. (3 = Vanilla)",
            3, 1, Integer.MAX_VALUE
        );
        this.addProperty(strongholdSpread);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Terrain shadowing
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        shadowStoneBlockId = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Stone shadow block ID",
            "Terrain shadowing",
            "The block to use for stone terrain shadowing, typically seen on the cliffs of stone mountains. Defaults to stained hardened clay.",
            DEFAULT_SHADOW_STONE_BLOCK_ID
        );
        this.addProperty(shadowStoneBlockId);

        shadowStoneBlockByte = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Stone shadow block meta value",
            "Terrain shadowing",
            "The meta value of the shadow block for stone cliffs. Defaults to " + DEFAULT_SHADOW_STONE_BLOCK_META + " (cyan).",
            DEFAULT_SHADOW_STONE_BLOCK_META, 0, 15
        );
        this.addProperty(shadowStoneBlockByte);

        shadowDesertBlockId = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Desert shadow block ID",
            "Terrain shadowing",
            "The block to use for desert terrain shadowing, typically seen on the cliffs of desert mountains. Defaults to stained hardened clay.",
            DEFAULT_SHADOW_DESERT_BLOCK_ID
        );
        this.addProperty(shadowDesertBlockId);

        shadowDesertBlockByte = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Desert shadow block meta value",
            "Terrain shadowing",
            "The meta value of the shadow block for desert cliffs. Defaults to " + DEFAULT_SHADOW_DESERT_BLOCK_META + " (white).",
            DEFAULT_SHADOW_DESERT_BLOCK_META, 0, 15
        );
        this.addProperty(shadowDesertBlockByte);

        enableUBCStoneShadowing = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "UBC Mode (Stone)",
            "Terrain shadowing",
            "Set this to TRUE to allow UBC to override stone shadowing."
                + Configuration.NEW_LINE +
                "This setting doesn't have any effect if UBC is not installed.",
            true
        );
        this.addProperty(enableUBCStoneShadowing);

        enableUBCDesertShadowing = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "UBC Mode (Desert)",
            "Terrain shadowing",
            "Set this to TRUE to allow UBC to override desert shadowing."
                + Configuration.NEW_LINE +
                "This setting doesn't have any effect if UBC is not installed.",
            true
        );
        this.addProperty(enableUBCDesertShadowing);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Trees
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        allowTreesToGenerateOnSand = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Allow Trees to Generate on Sand",
            "Trees",
            "Set this to FALSE to prevent trees from generating on sand."
                + Configuration.NEW_LINE +
                "This setting only affects trees generated by RTG. Trees generated by a biome's decorator"
                + Configuration.NEW_LINE +
                "will adhere to their own generation rules. (RTG's Palm Trees ignore this setting.)",
            true
        );
        this.addProperty(allowTreesToGenerateOnSand);

        allowShrubsToGenerateBelowSurface = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Allow Shrubs to Generate Below Surface",
            "Trees",
            "Set this to FALSE to prevent shrub trunks from generating below the surface.",
            true
        );
        this.addProperty(allowShrubsToGenerateBelowSurface);

        allowBarkCoveredLogs = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Allow bark-covered logs",
            "Trees",
            "Set this to FALSE to prevent the trunks of RTG trees from using the 'all-bark' texture model."
                + Configuration.NEW_LINE +
                "For more information, visit http://minecraft.gamepedia.com/Wood#Block_data",
            true
        );
        this.addProperty(allowBarkCoveredLogs);

        treeDensityMultiplier = new ConfigPropertyFloat(
            ConfigProperty.Type.FLOAT,
            "RTG Tree Density Multiplier",
            "Trees",
            "This setting allows you to increase/decrease the number of RTG trees that generate."
                + Configuration.NEW_LINE +
                "This setting only affects trees generated by RTG. Trees generated by a biome's decorator"
                + Configuration.NEW_LINE +
                "will adhere to their own density rules."
                + Configuration.NEW_LINE +
                "You can override this setting on a per-biome basis by using the biome configs."
                + Configuration.NEW_LINE +
                "1.0 = Default tree generation; 2.0 = Twice as many trees; 0.5 = half as many trees",
            1, 0, MAX_TREE_DENSITY
        );
        this.addProperty(treeDensityMultiplier);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Villages
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        enableVillageModifications = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable village modifications",
            "Villages",
            "Set this to FALSE to resolve issues with mods that also modify villages."
                + Configuration.NEW_LINE +
                "If set to FALSE, the 'Minimum distance between villages', 'Maximum distance between villages' & 'Size of villages' settings will have no effect."
                + Configuration.NEW_LINE +
                "WARNING! Setting this to FALSE may result in unpredictable village generation.",
            true
        );
        this.addProperty(enableVillageModifications);

        generateVillages = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Generate Villages",
            "Villages",
            "",
            true
        );
        this.addProperty(generateVillages);

        villageSize = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Size of villages",
            "Villages",
            "Higher values = bigger villages; 0 = Vanilla",
            0, 0, 10
        );
        this.addProperty(villageSize);

        minDistanceVillages = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Minimum distance between villages",
            "Villages",
            "Higher values = villages further apart; 8 = Vanilla",
            12, 1, Integer.MAX_VALUE
        );
        this.addProperty(minDistanceVillages);

        maxDistanceVillages = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Maximum distance between villages",
            "Villages",
            "Lower values = villages closer together; 32 = Vanilla",
            48, 1, Integer.MAX_VALUE
        );
        this.addProperty(maxDistanceVillages);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Volcanoes
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        volcanoBlockId = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Volcano block ID",
            "Volcanoes",
            "The main block to use for the surface of the volcano.",
            "minecraft:obsidian"
        );
        this.addProperty(volcanoBlockId);

        volcanoBlockMeta = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Volcano block meta value",
            "Volcanoes",
            "The meta value of the volcano block.",
            0, 0, 15
        );
        this.addProperty(volcanoBlockMeta);

        volcanoMix1BlockId = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Volcano mix 1 block ID",
            "Volcanoes",
            "The block ID of the 1st volcano mix block.",
            "minecraft:cobblestone"
        );
        this.addProperty(volcanoMix1BlockId);

        volcanoMix1BlockMeta = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Volcano mix 1 block meta value",
            "Volcanoes",
            "The meta value of the 1st volcano mix block.",
            0, 0, 15
        );
        this.addProperty(volcanoMix1BlockMeta);

        volcanoMix2BlockId = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Volcano mix 2 block ID",
            "Volcanoes",
            "The block ID of the 2nd volcano mix block.",
            "minecraft:gravel"
        );
        this.addProperty(volcanoMix2BlockId);

        volcanoMix2BlockMeta = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Volcano mix 2 block meta value",
            "Volcanoes",
            "The meta value of the 2nd volcano mix block.",
            0, 0, 15
        );
        this.addProperty(volcanoMix2BlockMeta);

        volcanoMix3BlockId = new ConfigPropertyString(
            ConfigProperty.Type.STRING,
            "Volcano mix 3 block ID",
            "Volcanoes",
            "The block ID of the 3rd volcano mix block.",
            "minecraft:coal_block"
        );
        this.addProperty(volcanoMix3BlockId);

        volcanoMix3BlockMeta = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Volcano mix 3 block meta value",
            "Volcanoes",
            "The meta value of the 3rd volcano mix block.",
            0, 0, 15
        );
        this.addProperty(volcanoMix3BlockMeta);

        enableVolcanoes = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable volcanoes",
            "Volcanoes",
            "Set this to FALSE to prevent volcanoes from generating.",
            true
        );
        this.addProperty(enableVolcanoes);

        enableVolcanoEruptions = new ConfigPropertyBoolean(
            ConfigProperty.Type.BOOLEAN,
            "Enable volcano eruptions",
            "Volcanoes",
            "Set this to FALSE to prevent lava from flowing down the sides of volcanoes.",
            true
        );
        this.addProperty(enableVolcanoEruptions);

        volcanoChance = new ConfigPropertyInt(
            ConfigProperty.Type.INTEGER,
            "Volcano Chance",
            "Volcanoes",
            "1/x chance that a volcano will generate in a biome that has volcanoes enabled."
                + Configuration.NEW_LINE +
                "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance",
            48, 1, Integer.MAX_VALUE
        );
        this.addProperty(volcanoChance);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    }

    public static byte[] getPlateauGradientBlockMetasFromConfigString(String configString)
    {
        String[] strings = configString.split(",");
        ArrayList<Byte> byteList = new ArrayList<Byte>(){};

        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].trim();
            if (strings[i].matches("-1|0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15")) {
                byteList.add(Byte.valueOf(strings[i]));
            }
        }

        Byte[] bytes = byteList.toArray(new Byte[byteList.size()]);
        return ArrayUtils.toPrimitive(bytes);
    }

    public static String getPlateauGradientBlockMetasComment(String biomeName)
    {
        String comment =
            "Comma-separated list of meta values for the gradient plateau blocks used in the " + biomeName + "."
                + Configuration.NEW_LINE +
                "-1 = Plateau block; 0-15 = Plateau gradient block"
                + Configuration.NEW_LINE +
                "0 = White; 1 = Orange; 2 = Magenta; 3 = Light Blue; 4 = Yellow; 5 = Lime; 6 = Pink; 7 = Gray"
                + Configuration.NEW_LINE +
                "8 = Light Gray; 9 = Cyan; 10 = Purple; 11 = Blue; 12 = Brown; 13 = Green; 14 = Red; 15 = Black";

        return comment;
    }

    public float lakeSizeMultiplier() {
        // with the river system changing frequency also shinks size and that will
        // confuse the heck out of users.
        return lakeSizeMultiplier.get() * lakeFrequencyMultiplier.get();
    }

    public float riverSizeMultiplier() {
        // with the river system changing frequency also shinks size and that will
        // confuse the heck out of users.
        return riverSizeMultiplier.get() * riverFrequencyMultiplier.get();
    }
}
