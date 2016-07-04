package teamrtg.rtg.api.config;

import java.util.ArrayList;

import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;

import org.apache.commons.lang3.ArrayUtils;

import teamrtg.rtg.api.config.ConfigProperty.PropertyBlock;
import teamrtg.rtg.api.config.ConfigProperty.PropertyBool;
import teamrtg.rtg.api.config.ConfigProperty.PropertyFloat;
import teamrtg.rtg.api.config.ConfigProperty.PropertyInt;
import teamrtg.rtg.api.config.ConfigProperty.PropertyString;

public class ConfigRTG extends ModConfig {
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Bedrock
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyInt FLAT_BEDROCK_LAYERS = addInt("Number of flat bedrock layers", "bedrock");
    public final PropertyBlock BEDROCK_BLOCK = addBlock("Bedrock block", "bedrock");
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Biomes
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ENABLE_RTG_BIOME_DECORATIONS = addBool("Enable RTG biome decorations", "biomes");
    public final PropertyBool ENABLE_RTG_SURFACES = addBool("Enable RTG biome surfaces", "biomes");
    public final PropertyBool USE_BOP_LAYOUT = addBool("Use BOP biome layout if available", "biomes");
    public final PropertyInt SURFACE_BLEED_RADIUS = addInt("Surface bleeding radius", "biomes");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Boulders
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ENABLE_COUBLESTONE_BOULDERS = addBool("Enable cobblestone boulders", "boulders");
    public final PropertyInt COBBLESTONE_BOULDER_CHANCE = addInt("Cobblestone boulder chance", "boulders");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Caves
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ENABLE_CAVE_MODIFICATIONS = addBool("Enable cave modifications", "caves");
    public final PropertyBool ENABLE_CAVES = addBool("Enable caves", "caves");
    public final PropertyInt CAVE_DENSITY = addInt("Cave density", "caves");
    public final PropertyInt CAVE_FREQUENCY = addInt("Cave frequency", "caves");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Debugging
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool SHOW_DEBUG_INFO = addBool("Show debug info on F3 screen", "debug");
    public final PropertyBool DEBUG_LOGGING = addBool("Enable debug logging", "debug");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Dunes
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyInt DUNE_HEIGHT = addInt("Height of dunes", "dunes");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Dungeons
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool GENERATE_DUNGEONS = addBool("Generate dungeons", "dungeons");
    public final PropertyInt DUNGEON_FREQUENCY = addInt("Dungeon frequency", "dungeons");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Emeralds
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool GENERATE_EMERALDS = addBool("Generate emeralds", "emeralds");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Flowing liquids
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public final PropertyInt FLOWING_LAVA_CHANCE = addInt("Flowing lava chance", "flowing liquids");
    public final PropertyInt FLOWING_WATER_CHANCE = addInt("Flowing water chance", "flowing liquids");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Lakes (Scenic)
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyFloat SCENIC_LAKE_FREQUENCY_MULTIPLIER = addFloat("Lake Frequency Multipler", "scenic lakes");
    public final PropertyFloat SCENIC_LAKE_SIZE_MULTIPLIER = addFloat("Lake Size Multipler", "scenic lakes");
    public final PropertyFloat SCENIC_LAKE_BENDINESS_MULTIPLIER = addFloat("Lake Shore Irregularity", "scenic lakes");
    public final PropertyInt SCENIC_LAKE_BIOME = addInt("Biome for scenic lakes", "scenic lakes");
    public final PropertyInt SCENIC_LAKE_FROZEN_BIOME = addInt("Biome for frozen scenic lakes", "scenic lakes");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Lakes (Surface)
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ENABLE_SURFACE_WATER_LAKES = addBool("Enable water surface lakes", "surface lakes");
    public final PropertyInt SURFACE_WATER_LAKE_CHANCE = addInt("1/x chance that Water Surface Lakes will generate if given the opportunity to do so during world gen", "surface lakes");
    
    public final PropertyBool ENABLE_SURFACE_LAVA_LAKES = addBool("Enable lava surface lakes", "surface lakes");
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

    public final PropertyBool GENERATE_MINESHAFTS = addBool("Generate mineshafts", "mineshafts");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Ocean monuments
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool GENERATE_OCEAN_MONUMENTS = addBool("Generate ocean monuments", "ocean monuments");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Ore gen
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool GENERATE_ORES = addBool("Enable ore generation", "ores");
    
    public final PropertyBool GENERATE_ORES_DIRT = addBool("Generate dirt", "ores");
    public final PropertyBool GENERATE_ORES_GRAVEL = addBool("Generate gravel", "ores");
    public final PropertyBool GENERATE_ORES_GRANITE = addBool("Generate granite", "ores");
    public final PropertyBool GENERATE_ORES_DIORITE = addBool("Generate diorite", "ores");
    public final PropertyBool GENERATE_ORES_ANDESITE = addBool("Generate andesite", "ores");
    public final PropertyBool GENERATE_ORES_COAL = addBool("Generate coal", "ores");
    public final PropertyBool GENERATE_ORES_IRON = addBool("Generate iron", "ores");
    public final PropertyBool GENERATE_ORES_GOLD = addBool("Generate gold", "ores");
    public final PropertyBool GENERATE_ORES_DIAMOND = addBool("Generate diamond", "ores");
    public final PropertyBool GENERATE_ORES_REDSTONE = addBool("Generate redstone", "ores");
    public final PropertyBool GENERATE_ORES_LAPIS = addBool("Generate lapis", "ores");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Plateaus
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyString MESA_CLAY_COLOURS = addString("Mesa clay colours", "plateaus");
    public final PropertyString MESA_BRYCE_CLAY_COLOURS = addString("Mesa Bryce clay colours", "plateaus");
    public final PropertyString SAVANNA_CLAY_COLOURS = addString("Savanna clay colours", "plateaus");
    public final PropertyBool STONE_SAVANNAS = addBool("Use stone instead of clay for most Savanna biome variants", "plateaus");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Ravines
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ENABLE_RAVINE_MODIFICATIONS = addBool("Enable ravine modifications", "ravines");
    public final PropertyBool ENABLE_RAVINES = addBool("Enable ravines", "ravines");
    public final PropertyInt RAVINE_FREQUENCY = addInt("Ravine frequency", "ravines");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Rivers
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyFloat RIVER_FREQUENCY_MULTIPLIER = addFloat("River frequency multiplier", "rivers");
    public final PropertyFloat RIVER_SIZE_MULTIPLIER = addFloat("River width multiplier", "rivers");
    public final PropertyFloat RIVER_BENDINESS_MULTIPLIER = addFloat("River bending multiplier", "rivers");
    public final PropertyFloat RIVER_CUTOFF_SCALE = addFloat("Scale of large-scale river cut-off", "rivers");
    public final PropertyFloat RIVER_CUTOFF_AMPLITUDE = addFloat("Amplitude of large-scale river cut-off", "rivers");

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Saplings
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public final PropertyBool ENABLE_SAPLINGS = addBool("Enable RTG saplings", "Saplings");
    public final PropertyInt RTG_TREE_CHANCE = addInt("RTG tree from vanilla sapling chance", "Saplings");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Scattered features
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool GENERATE_SCATTERED_FEATURES = addBool("Generate scattered features", "scattered features");
    public final PropertyInt MIN_DISTANCE_SCATTERED_FEATURES = addInt("Minimum distance between scattered features", "scattered features");
    public final PropertyInt MAX_DISTANCE_SCATTERED_FEATURES = addInt("Maximum distance between scattered features", "scattered features");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Strongholds
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool GENERATE_STRONGHOLDS = addBool("Generate strongholds", "strongholds");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Snow
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ENABLE_SNOW_LAYERS = addBool("Enable snow layers", "snow");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Terrain shadowing
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBlock SHADOW_STONE_BLOCK = addBlock("Stone shadow block", "terrain shadowing");
    public final PropertyBlock SHADOW_DESERT_BLOCK = addBlock("Desert shadow block", "terrain shadowing");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Trees
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public final PropertyBool ALLOW_TREES_ON_SAND = addBool("Allow trees to generate on sand", "trees");
    public final PropertyBool ALLOW_SHRUBS_UNDERGROUND = addBool("Allow shrubs to generate below the surface", "trees");
    
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Villages
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
    public final PropertyBool GENERATE_VILLAGES = addBool("Generate villages", "villages");
    public final PropertyBool ENABLE_VILLAGE_MODIFICATIONS = addBool("Enable village modifications", "villages");
    public final PropertyInt VILLAGE_SIZE = addInt("Size of villages", "villages");
    public final PropertyInt MIN_DISTANCE_VILLAGES = addInt("Minimum distance between villages", "villages");
    public final PropertyInt MAX_DISTANCE_VILLAGES = addInt("Maximum distance between villages", "villages");
    public final PropertyBool VILLAGE_CRASH_FIX = addBool("Village crash fix", "villages");
    
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
    	
        FLAT_BEDROCK_LAYERS.setDefault(0).setRange(0, 5).setComment(
    		"0 = Normal bedrock (rough pattern);"
    		+ NEW_LINE +
    		"1-5 = Number of flat bedrock layers to generate."
        );
        
        BEDROCK_BLOCK.setDefault(Blocks.BEDROCK.getDefaultState()).setComment(
        	"The block to use for the bottom of the Overworld."
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Biomes
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_RTG_BIOME_DECORATIONS.setDefault(true).setComment(
        	"If true, uses the individual biome settings in the biome config files."
        	+ NEW_LINE +
        	"If false, disables all RTG decorations and uses vanilla decorations instead."
        );
        
        ENABLE_RTG_SURFACES.setDefault(true).setComment(
        	"If true, uses the individual biome settings in the biome config files."
        	+ NEW_LINE +
        	"If false, disables all RTG surfaces and uses vanilla surfaces instead."
        );
        
        USE_BOP_LAYOUT.setDefault(false).setComment(
        	"If false, RTG will use the same biome layout that vanilla Minecraft uses, even if BOP is installed."
        );
        
        SURFACE_BLEED_RADIUS.setDefault(16).setRange(0, 32).setComment(
        	"The maximum number of blocks that surfaces can bleed into other biomes."
        	+ NEW_LINE +
            "Set to 0 to disable."
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Boulders
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_COUBLESTONE_BOULDERS.setDefault(true).setComment(
        	"Set to false to prevent cobblestone boulders from generating."
        );
        
        COBBLESTONE_BOULDER_CHANCE.setDefault(1).setRange(1, Integer.MAX_VALUE).setComment(
        	"1/x chance that Cobblestone Boulders will generate if given the opportunity to do so during world gen."
        	+ NEW_LINE +
        	"1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Caves
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_CAVE_MODIFICATIONS.setDefault(true).setComment(
        	"Must be set to true for the other cave settings to have any effect."
            + NEW_LINE +
            "If false, RTG won't interfere with cave generation at all."
        );
        
        ENABLE_CAVES.setDefault(true).setComment(
        	"Set to false to disable cave generation."
        	+ NEW_LINE +
        	"For this setting to have any effect, \"" + ENABLE_CAVE_MODIFICATIONS.getID() + "\" must be set to true."
        );
        
        CAVE_DENSITY.setDefault(7).setRange(1, 40).setComment(
        	"This setting controls the size of caves."
        	+ NEW_LINE +
            "Higher values = bigger caves & more lag. (14 = vanilla cave density)"
        );
        
        CAVE_FREQUENCY.setDefault(12).setRange(1, 40).setComment(
        	"This setting controls the number of caves that generate."
        	+ NEW_LINE +
            "LOWER values = MORE caves & more lag. (6 = vanilla cave frequency)"
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Debugging
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        SHOW_DEBUG_INFO.setDefault(false).setComment(
        	"Set to true to display helpful debug information on the F3 screen."
        );
        
        DEBUG_LOGGING.setDefault(false).setComment(
        	"Logs advanced debug information."
        	+ NEW_LINE +
        	"WARNING: This should only be enabled if you know what you're doing."
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Dunes
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        DUNE_HEIGHT.setDefault(4).setRange(1, 12).setComment(
        	"This setting controls the height of both sand dunes and snow dunes."
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Dungeons
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_DUNGEONS.setDefault(true).setComment(
        	"Set to false to disable dungeon generation."
        );
        
        DUNGEON_FREQUENCY.setDefault(8).setRange(1, 200).setComment(
        	"This setting controls the number of dungeons that generate."
        	+ NEW_LINE +
            "Higher values = more dungeons & more lag. (8 = vanilla dungeon frequency)"
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Emeralds
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_EMERALDS.setDefault(true).setComment(
        	"Set to false to disable emerald generation in all vanilla biomes."
        	+ NEW_LINE +
        	"If you only want to prevent emeralds from generating in specific vanilla biomes,"
        	+ NEW_LINE +
        	"you can do so in the biome configs on a per-biome basis."
        	+ NEW_LINE +
        	"If you want to prevent emeralds from generating in mod biomes,"
        	+ NEW_LINE +
        	"you'll have to either disable them in the configs of the mod to which the emerald-generating biomes belong,"
        	+ NEW_LINE +
        	"or you'll have to use another mod to disable them."
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Flowing liquids
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        FLOWING_LAVA_CHANCE.setDefault(200).setRange(0, Integer.MAX_VALUE).setComment(
        	"1/x chance that a lava stream will generate on the side of a hill or mountain."
        	+ NEW_LINE +
        	"0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
        );
        
        FLOWING_WATER_CHANCE.setDefault(200).setRange(0, Integer.MAX_VALUE).setComment(
        	"1/x chance that a water stream will generate on the side of a hill or mountain."
        	+ NEW_LINE +
        	"0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Lakes (Scenic)
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        SCENIC_LAKE_SIZE_MULTIPLIER.setDefault(1f).setRange(0f, 10f).setComment(
        	"Higher values = bigger lakes; 1 = normal size"
        );
        
        SCENIC_LAKE_FREQUENCY_MULTIPLIER.setDefault(1f).setRange(0f, 10f).setComment(
        	"Higher values = more lakes; 1 = normal frequency"
        );
        
        SCENIC_LAKE_BENDINESS_MULTIPLIER.setDefault(1f).setRange(0f, 2f).setComment(
        	"Higher values make scenic lakes bend and curve more; Lower values produce smoother, rounder lakes."
        );
        
        SCENIC_LAKE_BIOME.setDefault(7).setRange(0, 254).setComment(
        	"Biome ID to use for scenic lakes when not frozen; Defaults to the vanilla River biome (7)"
        );
        
        SCENIC_LAKE_FROZEN_BIOME.setDefault(11).setRange(0, 254).setComment(
        	"Biome ID to use for scenic lakes when frozen; Defaults to the vanilla Frozen River biome (11)"
        );

    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Lakes (Surface)
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_SURFACE_WATER_LAKES.setDefault(true).setComment(
        	"Set to false to disable water ponds on the surface."
        );
        
        SURFACE_WATER_LAKE_CHANCE.setDefault(10).setRange(0, Integer.MAX_VALUE).setComment(
        	"0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
        );
        
        ENABLE_SURFACE_LAVA_LAKES.setDefault(true).setComment(
        	"Set to false to disable lava ponds on the surface."
        );
        
        SURFACE_LAVA_LAKE_CHANCE.setDefault(10).setRange(0, Integer.MAX_VALUE).setComment(
        	"0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Lakes (Underground)
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_UNDERGROUND_WATER_LAKES.setDefault(true).setComment(
        	"Set to false to disable underground water ponds."
        );
        
        UNDERGROUND_WATER_LAKE_CHANCE.setDefault(10).setRange(0, Integer.MAX_VALUE).setComment(
        	"0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
        );
        
        ENABLE_UNDERGROUND_LAVA_LAKES.setDefault(true).setComment(
        	"Set to false to disable underground lava ponds."
        );
        
        UNDERGROUND_LAVA_LAKE_CHANCE.setDefault(10).setRange(0, Integer.MAX_VALUE).setComment(
        	"0 = Never generate; 1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Mineshafts
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_MINESHAFTS.setDefault(true).setComment(
        	"Set to false to disable mineshaft generation."
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Ocean monuments
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_OCEAN_MONUMENTS.setDefault(true).setComment(
        	"Set to false to disable ocean monument generation."
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Ore gen
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_ORES.setDefault(true).setComment(
        	"If false, RTG will not generate ores, even if individual ores have been enabled below."
        	+ NEW_LINE +
        	"This does not prevent emerald generation because emerald isn't technically an 'ore' according to Minecraft."
        	+ NEW_LINE +
        	"If you want to prevent emeralds from generating in your world, you'll have to use another mod to do it."
        );
        
        GENERATE_ORES_DIRT.setDefault(true).setComment("If false, RTG will not generate dirt ore.");
        GENERATE_ORES_GRAVEL.setDefault(true).setComment("If false, RTG will not generate gravel ore.");
        GENERATE_ORES_GRANITE.setDefault(true).setComment("If false, RTG will not generate granite ore.");
        GENERATE_ORES_DIORITE.setDefault(true).setComment("If false, RTG will not generate diorite ore.");
        GENERATE_ORES_ANDESITE.setDefault(true).setComment("If false, RTG will not generate andesite ore.");
        GENERATE_ORES_COAL.setDefault(true).setComment("If false, RTG will not generate coal ore.");
        GENERATE_ORES_IRON.setDefault(true).setComment("If false, RTG will not generate iron ore.");
        GENERATE_ORES_GOLD.setDefault(true).setComment("If false, RTG will not generate gold ore.");
        GENERATE_ORES_DIAMOND.setDefault(true).setComment("If false, RTG will not generate diamond ore.");
        GENERATE_ORES_REDSTONE.setDefault(true).setComment("If false, RTG will not generate redstone ore.");
        GENERATE_ORES_LAPIS.setDefault(true).setComment("If false, RTG will not generate lapis lazuli ore.");
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Plateaus
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        MESA_CLAY_COLOURS.setDefault("-1,-1,-1,1,1,1,0,-1,-1,6,1,1,8,0,-1,-1,14,-1,-1,6,1,1,4")
        	.setComment(getPlateauClayColourComment("Mesa biome variants (doesn't include Mesa Bryce)"));
        
        MESA_BRYCE_CLAY_COLOURS.setDefault("-1,-1,0,1,0,0,0,14,0,8,0,1,8,0,-1,0,14,0,0,14,0,0,8")
        	.setComment(getPlateauClayColourComment("Mesa Bryce biome"));
        
        SAVANNA_CLAY_COLOURS.setDefault("0,0,0,0,8,8,12,12,8,0,8,12,12,8,12,8,0,0,8,12,12")
        	.setComment(getPlateauClayColourComment("Savanna biome variants"));
        
        STONE_SAVANNAS.setDefault(true).setComment(
	    	"If set to true, Savanna biome variants will mostly use stone/cobblestone instead of stained hardened clay for cliffs and plateaus."
            + NEW_LINE +
            "Savanna Plateau M will always use stained hardened clay."
	    );

    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Ravines
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_RAVINE_MODIFICATIONS.setDefault(true).setDefault(true).setComment(
        	"Must be set to true for the other ravine settings to have any effect."
            + NEW_LINE +
            "If false, RTG won't interfere with ravine generation at all."
        );
        
        ENABLE_RAVINES.setDefault(false).setComment(
        	"Set to false to disable ravine generation."
        );
        
        RAVINE_FREQUENCY.setDefault(50).setRange(1, 200).setComment(
        	"This setting controls the number of ravines that generate."
        	+ NEW_LINE +
            "LOWER values = MORE ravines & more lag. (50 = vanilla ravine frequency)"
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Rivers
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        RIVER_SIZE_MULTIPLIER.setDefault(1).setRange(0, 10).setComment("Defaults to 1 (standard width)");
        RIVER_FREQUENCY_MULTIPLIER.setDefault(1).setRange(0, 10).setComment("Defaults to 1 (standard frequency)");
        RIVER_BENDINESS_MULTIPLIER.setDefault(1).setRange(0, 2).setComment("Higher numbers make rivers bend more. Defaults to 1");
        RIVER_CUTOFF_SCALE.setDefault(350f).setRange(50f, 5000f).setComment("Higher numbers make grassy areas near rivers bigger, but also more rare. Defaults to 350" + NEW_LINE);
        RIVER_CUTOFF_AMPLITUDE.setDefault(0.5f).setRange(0f, 2f).setComment("Higher numbers make the large-scale cut-off noise have a greater effect. Defaults to 0.5" + NEW_LINE);
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Saplings
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_SAPLINGS.setDefault(true).setComment(
			"Set this to true to allow RTG's custom trees to grow from vanilla saplings."
			+ NEW_LINE +
			"RTG's custom trees can be grown only from the saplings that their leaves would drop naturally, and only in the biomes where they naturally generate."
			+ NEW_LINE +
			"For example, you can only grow a Swamp Willow in a Swamp biome, and only with an Oak sapling (because Swamp Willows have Oak leaves)."
        );
        
        RTG_TREE_CHANCE.setDefault(2).setRange(1, Integer.MAX_VALUE).setComment(
        	"1/x chance that a vanilla sapling will grow one of RTG's custom trees."
			+ NEW_LINE +
			"1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
        );

    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Scattered features
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_SCATTERED_FEATURES.setDefault(true).setComment(
        	"Set to false to disable scattered feature generation."
        	+ NEW_LINE +
        	"Scattered features include desert temples, jungle temples, and witch huts."
        );
        
        MIN_DISTANCE_SCATTERED_FEATURES.setDefault(12).setRange(1, Integer.MAX_VALUE).setComment(
        	"LOWER values = MORE scattered features & more lag (8 = Vanilla)"
        );
        
        MAX_DISTANCE_SCATTERED_FEATURES.setDefault(48).setRange(1, Integer.MAX_VALUE).setComment(
        	"LOWER values = MORE scattered features & more lag (32 = Vanilla)"
        );

    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Snow
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_SNOW_LAYERS.setDefault(true).setComment(
        	"Set to false to prevent most (but not all) snow layers from generating."
        	+ NEW_LINE +
        	"This applies to newly-generated chunks only. Snow layers will still appear in cold/snowy biomes after it snows."
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Strongholds
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        GENERATE_STRONGHOLDS.setDefault(true).setComment(
        	"Set to false to disable stronghold generation."
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Terrain shadowing
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        SHADOW_STONE_BLOCK.setDefault(Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.CYAN)).setComment(
        	"The block to use for stone terrain shadowing, typically seen on the cliffs of stone mountains."
        );
        
        SHADOW_DESERT_BLOCK.setDefault(Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.WHITE)).setComment(
        	"The block to use for desert terrain shadowing, typically seen on the cliffs of desert mountains."
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Trees
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ALLOW_TREES_ON_SAND.setDefault(true).setComment(
            "Set to false to prevent trees from generating on sand."
            + NEW_LINE +
            "This setting only affects trees generated by RTG. Trees generated by a biome's decorator will adhere to their own generation rules."
            + NEW_LINE +
            "(RTG's beach palm trees ignore this setting.)"
        );
        
        ALLOW_SHRUBS_UNDERGROUND.setDefault(true).setComment(
	        "Set to false to prevent shrub trunks from generating below the surface."
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Villages
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_VILLAGE_MODIFICATIONS.setDefault(true).setComment(
            "Set to false to resolve issues with mods that also modify villages."
            + NEW_LINE +
            "If set to false, RTG will not interfere with village generation at all, and"
            + NEW_LINE +
            "the '" + MIN_DISTANCE_VILLAGES.getID() + "', '" + MAX_DISTANCE_VILLAGES.getID() + "' & '" + VILLAGE_SIZE.getID() + "' settings will have no effect."
        );
        
        GENERATE_VILLAGES.setDefault(true).setComment(
        	"Set to false to disable village generation."
        );
        
        VILLAGE_SIZE.setDefault(3).setRange(0, 10).setComment(
        	"Higher values = bigger villages (0 = Vanilla)"
        );
        
        MIN_DISTANCE_VILLAGES.setDefault(12).setRange(1, Integer.MAX_VALUE).setComment(
        	"Higher values = villages further apart (8 = Vanilla)"
        );
        
        MAX_DISTANCE_VILLAGES.setDefault(48).setRange(1, Integer.MAX_VALUE).setComment(
        	"Lower values = villages closer together (32 = Vanilla)"
        );
        
        VILLAGE_CRASH_FIX.setDefault(true).setComment(
            "Set to true to if you are experiencing 'java.util.ConcurrentModificationException' crashes related to village generation."
        );
        
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	// Volcanoes
    	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        ENABLE_VOLCANOES.setDefault(true).setComment(
        	"Set to false to prevent volcanoes from generating."
        );
        
        ENABLE_VOLCANO_ERUPTIONS.setDefault(true).setComment(
        	"Set to false to prevent lava from flowing down the sides of volcanoes."
        );
        
        VOLCANO_CHANCE.setDefault(36).setRange(1, Integer.MAX_VALUE).setComment(
			"1/x chance that a volcano will generate in a biome that has volcanoes enabled."
	        + NEW_LINE +
	        "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
        );
        
        VOLCANO_BLOCK.setDefault(Blocks.OBSIDIAN.getDefaultState()).setComment("The main block to use for the surface of the volcano.");
        VOLCANO_BLOCK_MIX_1.setDefault(Blocks.COBBLESTONE.getDefaultState()).setComment("The block ID of the 1st volcano mix block.");
        VOLCANO_BLOCK_MIX_2.setDefault(Blocks.GRAVEL.getDefaultState()).setComment("The block ID of the 2nd volcano mix block.");
        VOLCANO_BLOCK_MIX_3.setDefault(Blocks.COAL_BLOCK.getDefaultState()).setComment("The block ID of the 3rd volcano mix block.");

    }
    
	public byte[] getClayColourMetasFromConfigString(String configString)
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
	
	private String getPlateauClayColourComment(String biomeName)
	{
		String comment =
			"Comma-separated list of meta values for the clay blocks used in the " + biomeName + "."
			+ NEW_LINE +
			"-1 = Hardened Clay; 0-15 = Stained Clay"
			+ NEW_LINE +
			"0 = White; 1 = Orange; 2 = Magenta; 3 = Light Blue; 4 = Yellow; 5 = Lime; 6 = Pink; 7 = Gray"
			+ NEW_LINE +
			"8 = Light Gray; 9 = Cyan; 10 = Purple; 11 = Blue; 12 = Brown; 13 = Green; 14 = Red; 15 = Black";
		
		return comment;
	}
}