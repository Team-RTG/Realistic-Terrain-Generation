package teamrtg.rtg.api.config;

import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import teamrtg.rtg.api.config.ConfigProperty.PropertyBlock;
import teamrtg.rtg.api.config.ConfigProperty.PropertyBool;
import teamrtg.rtg.api.config.ConfigProperty.PropertyFloat;
import teamrtg.rtg.api.config.ConfigProperty.PropertyInt;

public class ConfigRTG extends ModConfig {
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Bedrock
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyInt FLAT_BEDROCK_LAYERS = addInt("Number of flat bedrock layers", "bedrock");
    public final PropertyBlock BEDROCK_BLOCK = addBlock("BEDROCK_BLOCK", "bedrock");
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Biomes
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ENABLE_RTG_BIOME_DECORATIONS = addBool("Enable RTG Biome Decorations", "biomes");
    public final PropertyBool ENABLE_RTG_SURFACES = addBool("Enable RTG Biome Surfaces", "biomes");
    public final PropertyBool USE_BOP_LAYOUT = addBool("Use BOP biome layout if avaliable", "biomes");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Boulders
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ENABLE_COUBLESTONE_BOULDERS = addBool("Enable Cobblestone Boulders", "boulders");
    public final PropertyInt COBBLESTONE_BOULDER_CHANCE = addInt("1/x chance that Cobblestone Boulders will generate if given the opportunity to do so during world gen", "boulders");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Caves
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ENABLE_CAVE_MODIFICATIONS = addBool("Enable Cave Modifications", "caves");
    public final PropertyBool ENABLE_CAVES = addBool("Enable Caves", "caves");
    public final PropertyInt CAVE_DENSITY = addInt("Cave Density", "caves");
    public final PropertyInt CAVE_FREQUENCY = addInt("Cave Frequency", "caves");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Debugging
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool SHOW_DEBUG_INFO = addBool("Show Debug Info in F3 Screen", "debug");
    public final PropertyBool DEBUG_LOGGING = addBool("Enable Debug logging", "debug");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Dunes
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyInt DUNE_HEIGHT = addInt("Height of Dunes", "dunes");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Dungeons
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool GENERATE_DUNGEONS = addBool("Generate Dungeons", "dungeons");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Lakes (Scenic)
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyFloat LAKE_FREQUENCY_MULTIPLIER = addFloat("Lake Frequency Multipler", "water systems");
    public final PropertyFloat LAKE_SIZE_MULTIPLIER = addFloat("Lake Size Multipler", "water systems");
    public final PropertyFloat LAKE_BENDINESS_MULTIPLIER = addFloat("Lake Shore Irregularity", "water systems");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Lakes (Surface)
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ENABLE_SURFACE_WATER_LAKES = addBool("Enable Water Surface Lakes", "surface lakes");
    public final PropertyInt SURFACE_WATER_LAKE_CHANCE = addInt("1/x chance that Water Surface Lakes will generate if given the opportunity to do so during world gen", "surface lakes");
    
    public final PropertyBool ENABLE_SURFACE_LAVA_LAKES = addBool("Enable Lava Surface Lakes", "surface lakes");
    public final PropertyInt SURFACE_LAVA_LAKE_CHANCE = addInt("1/x chance that Lava Surface Lakes will generate if given the opportunity to do so during world gen", "surface lakes");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Lakes (Underground)
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ENABLE_UNDERGROUND_WATER_LAKES = addBool("Enable Water Underground Lakes", "underground lakes");
    public final PropertyInt UNDERGROUND_WATER_LAKE_CHANCE = addInt("1/x chance that Water Underground Lakes will generate if given the opportunity to do so during world gen", "underground lakes");
    
    public final PropertyBool ENABLE_UNDERGROUND_LAVA_LAKES = addBool("Enable Lava Underground Lakes", "underground lakes");
    public final PropertyInt UNDERGROUND_LAVA_LAKE_CHANCE = addInt("1/x chance that Lava Underground Lakes will generate if given the opportunity to do so during world gen", "underground lakes");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Mineshafts
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool GENERATE_MINESHAFTS = addBool("Generate Mineshafts", "mineshafts");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Ocean monuments
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool GENERATE_OCEAN_MONUMENTS = addBool("Generate Ocean Monuments", "ocean monuments");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Ore gen
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool GENERATE_ORES = addBool("Generate ores", "ores");
    
    public final PropertyBool GENERATE_ORES_COAL = addBool("Generate coal", "ores");
    public final PropertyBool GENERATE_ORES_IRON = addBool("Generate iron", "ores");
    public final PropertyBool GENERATE_ORES_GOLD = addBool("Generate gold", "ores");
    public final PropertyBool GENERATE_ORES_REDSTONE = addBool("Generate redstone", "ores");
    public final PropertyBool GENERATE_ORES_LAPIS = addBool("Generate lapis", "ores");
    public final PropertyBool GENERATE_ORES_DIAMOND = addBool("Generate diamond", "ores");
    public final PropertyBool GENERATE_ORES_EMERALD = addBool("Generate emerald", "ores");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Ravines
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ENABLE_RAVINE_MODIFICATIONS = addBool("Enable Ravine Modifications", "ravines");
    public final PropertyBool ENABLE_RAVINES = addBool("Enable Ravines", "ravines");
    public final PropertyInt RAVINE_FREQUENCY = addInt("Ravine Frequency", "ravines");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Rivers
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyFloat RIVER_FREQUENCY_MULTIPLIER = addFloat("River frequency multiplier", "water systems");
    public final PropertyFloat RIVER_SIZE_MULTIPLIER = addFloat("River width multiplier", "water systems");
    public final PropertyFloat RIVER_BENDINESS_MULTIPLIER = addFloat("Multiplier to River Bending", "water systems");
    public final PropertyInt SURFACE_BLEED_RADIUS = addInt("Surface bleeding radius", "surface bleeding");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Saplings
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public final PropertyBool ENABLE_SAPLINGS = addBool("Enable RTG Saplings", "Saplings");
    public final PropertyInt RTG_TREE_CHANCE = addInt("RTG Tree from Vanilla Sapling Chance", "Saplings");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Scattered features
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool GENERATE_SCATTERED_FEATURES = addBool("Generate Scattered Features", "scattered features");
    public final PropertyInt MIN_DISTANCE_SCATTERED_FEATURES = addInt("Minimum distance between scattered features", "scattered features");
    public final PropertyInt MAX_DISTANCE_SCATTERED_FEATURES = addInt("Maximum distance between scattered features", "scattered features");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Strongholds
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool GENERATE_STRONGHOLDS = addBool("Generate Strongholds", "strongholds");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Snow
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ENABLE_SNOW_LAYERS = addBool("Enable Snow Layers", "snow");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Terrain shadowing
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBlock SHADOW_STONE_BLOCK = addBlock("Stone shadow block", "terrain shadowing");
    public final PropertyBlock SHADOW_DESERT_BLOCK = addBlock("Desert shadow block", "terrain shadowing");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Trees
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ALLOW_TREES_ON_SAND = addBool("Allow Trees to Generate on Sand", "trees");
    public final PropertyBool ALLOW_SHRUBS_UNDERGROUND = addBool("Allow Shrubs to Generate Below Surface", "trees");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Villages
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
    public final PropertyBool GENERATE_VILLAGES = addBool("Generate Villages", "villages");
    public final PropertyBool ENABLE_VILLAGE_MODIFICATIONS = addBool("Enable village modifications", "villages");
    public final PropertyInt VILLAGE_SIZE = addInt("Size of villages", "villages");
    public final PropertyInt MIN_DISTANCE_VILLAGES = addInt("Minimum distance between villages", "villages");
    public final PropertyInt MAX_DISTANCE_VILLAGES = addInt("Maximum distance between villages", "villages");
    public final PropertyBool VILLAGE_CRASH_FIX = addBool("Village Crash Fix", "villages");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Volcanoes
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public final PropertyBool ENABLE_VOLCANOES = addBool("Enable volcanoes", "Volcanoes");
    public final PropertyBool ENABLE_VOLCANO_ERUPTIONS = addBool("Enable volcano eruptions", "Volcanoes");
    public final PropertyInt VOLCANO_CHANCE = addInt("Volcano chance", "Volcanoes");
    public final PropertyBlock VOLCANO_BLOCK = addBlock("Volcano block", "Volcanoes");
    public final PropertyBlock VOLCANO_BLOCK_MIX_1 = addBlock("Volcano mix block 1", "Volcanoes");
    public final PropertyBlock VOLCANO_BLOCK_MIX_2 = addBlock("Volcano mix block 2", "Volcanoes");
    public final PropertyBlock VOLCANO_BLOCK_MIX_3 = addBlock("Volcano mix block 3", "Volcanoes");

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public ConfigRTG() {
        super("RTG");
    }

    @Override
    public void initDefaults() {
    	
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Bedrock
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	
        FLAT_BEDROCK_LAYERS.setDefault(0).setComment("0 = Normal bedrock (rough pattern); 1-5 = Number of flat bedrock layers to generate");
        BEDROCK_BLOCK.setDefault(Blocks.BEDROCK.getDefaultState()).setComment("The block to use for the bottom of the Overworld");
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Biomes
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_RTG_BIOME_DECORATIONS.setDefault(true).setComment("If TRUE, uses the individual biome settings in the biome forgeConfig files. If FALSE, disables all RTG decorations and uses biomes decorations instead.");
        ENABLE_RTG_SURFACES.setDefault(true).setComment("If TRUE, uses the individual biome settings in the biome forgeConfig files. If FALSE, disables all RTG surfaces and uses biomes surfaces instead.");
        USE_BOP_LAYOUT.setDefault(false).setComment("If FALSE, RTG will use biomes biome layout, even if BOP is installed. This means no BOP biomes.");
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Boulders
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_COUBLESTONE_BOULDERS.setDefault(true);
        COBBLESTONE_BOULDER_CHANCE.setDefault(1).setComment("1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Caves
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_CAVE_MODIFICATIONS.setDefault(true).setComment("Must be set to TRUE for the other cave settings to have any effect." +
                "If FALSE, RTG won't interfere with cave generation at all.");
        ENABLE_CAVES.setDefault(true);
        CAVE_DENSITY.setDefault(8).setRange(1, 40).setComment("This setting controls the size of caves." + NEW_LINE +
                "HIGHER values = BIGGER caves & MORE lag. (14 = biomes cave density)");
        CAVE_FREQUENCY.setDefault(16).setRange(1, 40).setComment("This setting controls the number of caves that generate." + NEW_LINE +
                "LOWER values = MORE caves & MORE lag. (6 = biomes cave frequency)");
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Debugging
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        SHOW_DEBUG_INFO.setDefault(false);
        DEBUG_LOGGING.setDefault(false).setComment("WARNING: This should only be enabled if you know what you're doing.");
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Dunes
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        DUNE_HEIGHT.setDefault(4).setRange(1, 12).setComment("This setting controls the height of both sand dunes and snow dunes.");
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Dungeons
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_DUNGEONS.setDefault(true);
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Lakes (Scenic)
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        LAKE_SIZE_MULTIPLIER.setDefault(1).setRange(0, 10).setComment("Defaults to 1 (standard size)");
        LAKE_FREQUENCY_MULTIPLIER.setDefault(1).setRange(0, 10).setComment("Defaults to 1 (standard frequency)");
        LAKE_BENDINESS_MULTIPLIER.setDefault(1).setRange(0, 2).setComment("Makes scenic lake shores bend and curve more. Defaults to 1");
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Lakes (Surface)
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_SURFACE_WATER_LAKES.setDefault(true);
        SURFACE_WATER_LAKE_CHANCE.setDefault(10).setRange(1, 100).setComment("1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
        
        ENABLE_SURFACE_LAVA_LAKES.setDefault(true);
        SURFACE_LAVA_LAKE_CHANCE.setDefault(10).setRange(1, 100).setComment("1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Lakes (Underground)
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_UNDERGROUND_WATER_LAKES.setDefault(true);
        UNDERGROUND_WATER_LAKE_CHANCE.setDefault(10).setRange(1, 100).setComment("1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance");
        
        ENABLE_UNDERGROUND_LAVA_LAKES.setDefault(true);
        UNDERGROUND_LAVA_LAKE_CHANCE.setDefault(10);
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Mineshafts
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_MINESHAFTS.setDefault(true);
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Ocean monuments
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_OCEAN_MONUMENTS.setDefault(true);
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Ore gen
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_ORES.setDefault(true).setComment("If FALSE, RTG will not generate ores. This may be required for some mod compatibility, as you would otherwise get double the ores");
        GENERATE_ORES_COAL.setDefault(true).setComment("");
        GENERATE_ORES_IRON.setDefault(true).setComment("");
        GENERATE_ORES_GOLD.setDefault(true).setComment("");
        GENERATE_ORES_REDSTONE.setDefault(true).setComment("");
        GENERATE_ORES_LAPIS.setDefault(true).setComment("");
        GENERATE_ORES_DIAMOND.setDefault(true).setComment("");
        GENERATE_ORES_EMERALD.setDefault(true).setComment("");
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Ravines
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_RAVINE_MODIFICATIONS.setDefault(true);
        ENABLE_RAVINES.setDefault(false);
        RAVINE_FREQUENCY.setDefault(2);
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Rivers
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        RIVER_SIZE_MULTIPLIER.setDefault(1).setRange(0, 10).setComment("Defaults to 1 (standard width)");
        RIVER_FREQUENCY_MULTIPLIER.setDefault(1).setRange(0, 10).setComment("Defaults to 1 (standard frequency)");
        RIVER_BENDINESS_MULTIPLIER.setDefault(1).setRange(0, 2).setComment("Higher numbers make rivers bend more. Defaults to 1");
        SURFACE_BLEED_RADIUS.setDefault(16).setRange(0, 32).setComment("The maximum number of blocks a surface can bleed away from its biome." + NEW_LINE +
        	"Set to 0 to disable");
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Saplings
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_SAPLINGS.setDefault(true).setComment(
			"Set this to TRUE to allow RTG's custom trees to grow from vanilla saplings."
			+ NEW_LINE +
			"RTG's custom trees can be grown only from the saplings that their leaves would drop naturally, and only in the biomes where they naturally generate."
			+ NEW_LINE +
			"For example, you can only grow a Swamp Willow in a Swamp biome, and only with an Oak sapling (because Swamp Willows have Oak leaves)."
			+ NEW_LINE
        );
        
        RTG_TREE_CHANCE.setDefault(2).setComment(
        	"1/x chance that a vanilla sapling will grow one of RTG's custom trees."
			+ NEW_LINE +
			"1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
			+ NEW_LINE
        );

    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Scattered features
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_SCATTERED_FEATURES.setDefault(true);
        MIN_DISTANCE_SCATTERED_FEATURES.setDefault(12);
        MAX_DISTANCE_SCATTERED_FEATURES.setDefault(48);

    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Strongholds
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_STRONGHOLDS.setDefault(true);

    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Snow
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_SNOW_LAYERS.setDefault(true);
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Terrain shadowing
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        SHADOW_STONE_BLOCK.setDefault(Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.CYAN));
        SHADOW_DESERT_BLOCK.setDefault(Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.WHITE));
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Trees
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ALLOW_TREES_ON_SAND.setDefault(true);
        ALLOW_SHRUBS_UNDERGROUND.setDefault(true);
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Villages
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_VILLAGES.setDefault(true);
        ENABLE_VILLAGE_MODIFICATIONS.setDefault(true);
        VILLAGE_SIZE.setDefault(3);
        MIN_DISTANCE_VILLAGES.setDefault(12);
        MAX_DISTANCE_VILLAGES.setDefault(48);
        VILLAGE_CRASH_FIX.setDefault(true);
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Volcanoes
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_VOLCANOES.setDefault(true);
        ENABLE_VOLCANO_ERUPTIONS.setDefault(true);
        VOLCANO_CHANCE.setDefault(36);
        VOLCANO_BLOCK.setDefault(Blocks.OBSIDIAN.getDefaultState());
        VOLCANO_BLOCK_MIX_1.setDefault(Blocks.COBBLESTONE.getDefaultState());
        VOLCANO_BLOCK_MIX_2.setDefault(Blocks.GRAVEL.getDefaultState());
        VOLCANO_BLOCK_MIX_3.setDefault(Blocks.COAL_BLOCK.getDefaultState());

    }
}