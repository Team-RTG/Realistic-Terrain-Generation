package rtg.api.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import rtg.api.config.property.ConfigPropertyArray.ConfigPropertyArrayString;
import rtg.api.config.property.ConfigPropertyBoolean;
import rtg.api.config.property.ConfigPropertyFloat;
import rtg.api.config.property.ConfigPropertyInteger;
import rtg.api.config.property.ConfigPropertyString;


// TODO: [Clean-up] Rewrite this class to have final fields that hold primative values, and have a Factory and Serializer inner classes like ChunkProviderSettings has.
//                  Biome configs can be Serialize'd and written to files as JSON objects, and looded from files and Deserialize'd into BiomeConfig objects through the Factory.
//                  All implementation code (eg. creating config objects in the constructor) should be removed and default settings can be passed from the realistic biome
//                  classes (during init) to a Factory instance before attempting to loaded config file data.
//                  OPTIONAL: Dummy JSON objects can be used during serialization to create 'comments' for the config settings before writing to files.
//                  OPTIONAL: Add a GUI interface to change biome config settings from the config GUI screen.
// TODO: [Clean-up] All biome config settings should be assessed as to their usefulness and ones that are unlikely to be used should be removed to streamline the configs for less bloat
public class BiomeConfig extends Config {

    /* GLOBAL CONFIGS */
    public final ConfigPropertyBoolean ALLOW_RIVERS;
    public final ConfigPropertyBoolean ALLOW_SCENIC_LAKES;
    public final ConfigPropertyBoolean ALLOW_VILLAGES;
    public final ConfigPropertyBoolean ALLOW_VOLCANOES;
    public final ConfigPropertyInteger VOLCANO_CHANCE;
    public final ConfigPropertyBoolean DISABLE_RTG_DECORATIONS;
    public final ConfigPropertyBoolean DISABLE_RTG_SURFACES;
    public final ConfigPropertyBoolean DISABLE_RTG_TERRAIN;
    public final ConfigPropertyString SURFACE_TOP_BLOCK;
    public final ConfigPropertyString SURFACE_FILLER_BLOCK;
    public final ConfigPropertyString SURFACE_CLIFF_STONE_BLOCK;
    public final ConfigPropertyString SURFACE_CLIFF_COBBLE_BLOCK;
    public final ConfigPropertyInteger BEACH_BIOME;
    public final ConfigPropertyFloat TREE_DENSITY_MULTIPLIER;
    public final ConfigPropertyBoolean USE_CUSTOM_BIOME_TEMPERATURE;
    public final ConfigPropertyFloat BIOME_TEMPERATURE;
    public final ConfigPropertyBoolean SURFACE_BLEED_IN;
    public final ConfigPropertyBoolean SURFACE_BLEED_OUT;

    /* OPTIONAL CONFIGS */
    public final ConfigPropertyBoolean ALLOW_LOGS;
    public final ConfigPropertyString SURFACE_MIX_BLOCK;
    public final ConfigPropertyString SURFACE_MIX_FILLER_BLOCK;
    public final ConfigPropertyString SURFACE_MIX_2_BLOCK;
    public final ConfigPropertyString SURFACE_MIX_3_BLOCK;
    public final ConfigPropertyString SURFACE_MIX_4_BLOCK;
    public final ConfigPropertyBoolean ALLOW_PLATEAU_MODIFICATIONS;
    public final ConfigPropertyArrayString PLATEAU_GRADIENT_BLOCK_LIST;
// TODO: [1.12] Add biome specific config settings for boulders (will be multiplied by the gen setting multiplier)
//  public final ConfigPropertyBoolean BOULDERS_ENABLED;
//  public final ConfigPropertyString BOULDERS_BLOCK;
//  public final ConfigPropertyFloat BOULDERS_CHANCE;
    public final ConfigPropertyBoolean ALLOW_PALM_TREES;
    public final ConfigPropertyBoolean ALLOW_CACTUS;
    public final ConfigPropertyBoolean ALLOW_COBWEBS;
    public final ConfigPropertyBoolean ALLOW_WHEAT;
    public final ConfigPropertyBoolean ALLOW_PONDS_WATER;
    public final ConfigPropertyInteger WHEAT_CHANCE;
    public final ConfigPropertyInteger WHEAT_MIN_Y;
    public final ConfigPropertyInteger WHEAT_MAX_Y;
    public final ConfigPropertyBoolean USE_ARCTIC_SURFACE;
    public final ConfigPropertyBoolean ALLOW_ICE_TREES;
    public final ConfigPropertyFloat FALLEN_LOG_DENSITY_MULTIPLIER;
    public final ConfigPropertyBoolean ALLOW_SPONGE;
    public final ConfigPropertyBoolean ALLOW_OCEAN_WAVES;

    public BiomeConfig(File file) {
        super(file);

        /* GLOBAL CONFIGS */
        ALLOW_RIVERS = new ConfigPropertyBoolean(
            "Allow Rivers",
            "Terrain Features",
            "Set this to FALSE to prevent rivers from generating in this biome.",
            true
        );
        this.addProperty(ALLOW_RIVERS);

        ALLOW_SCENIC_LAKES = new ConfigPropertyBoolean(
            "Allow Scenic Lakes",
            "Terrain Features",
            "Set this to FALSE to prevent scenic lakes from generating in this biome.",
            true
        );
        this.addProperty(ALLOW_SCENIC_LAKES);

        ALLOW_VILLAGES = new ConfigPropertyBoolean(
            "Allow Villages",
            "Villages",
            "Set this to FALSE to prevent villages from generating in this biome.",
            true
        );
        this.addProperty(ALLOW_VILLAGES);

        ALLOW_VOLCANOES = new ConfigPropertyBoolean(
            "Allow volcanoes",
            "Volcanoes",
            "Set this to TRUE to allow volcanoes to generate in this biome.",
            false
        );
        this.addProperty(ALLOW_VOLCANOES);

        VOLCANO_CHANCE = new ConfigPropertyInteger(
            "Volcano Chance",
            "Volcanoes",
            "1/x chance that a volcano will generate if this biome has volcanoes enabled."
                + Configuration.NEW_LINE + "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance"
                + Configuration.NEW_LINE + "Set to -1 to use global setting. Set to 0 to disable volcanoes for this biome.",
            -1, -1, Integer.MAX_VALUE
        );
        this.addProperty(VOLCANO_CHANCE);

        DISABLE_RTG_DECORATIONS = new ConfigPropertyBoolean(
            "Diesable RTG Decorations",
            "Decorations",
            "If TRUE, RTG decorations will disabled in this biome and only vanilla decorations will generate."
                + Configuration.NEW_LINE + "RTG decorations include custom trees, shrubs, boulders, etc.",
            false
        );
        this.addProperty(DISABLE_RTG_DECORATIONS);

        DISABLE_RTG_SURFACES = new ConfigPropertyBoolean(
            "Disable RTG Surfaces",
            "Surfaces",
            "If TRUE, RTG surfaces will be disabled in this biome and only vanilla surfaces will be used."
                + Configuration.NEW_LINE + "RTG surfaces include custom top & filler blocks, and 'mix' blocks like podzol in Forests.",
            false
        );
        this.addProperty(DISABLE_RTG_SURFACES);

        DISABLE_RTG_TERRAIN = new ConfigPropertyBoolean(
            "Disable RTG Terrain",
            "Terrain",
            "If TRUE, RTG terrain will be disabled in this biome and only vanilla terrain will be generated.",
            false
        );
        this.addProperty(DISABLE_RTG_TERRAIN);

        SURFACE_TOP_BLOCK = new ConfigPropertyString(
            "Top Block ID",
            "Surfaces.Top Block",
            "Set this to change this biome's top block." + BLOCKSTATE_HELP,
            ""
        );
        this.addProperty(SURFACE_TOP_BLOCK);

        SURFACE_FILLER_BLOCK = new ConfigPropertyString(
            "Filler Block ID",
            "Surfaces.Filler Block",
            "Set this to change this biome's filler block (the block underneath the top block)." + BLOCKSTATE_HELP,
            ""
        );
        this.addProperty(SURFACE_FILLER_BLOCK);

        SURFACE_CLIFF_STONE_BLOCK = new ConfigPropertyString(
            "Cliff Stone Block ID",
            "Surfaces.Cliff Stone Block",
            "Cliff blocks are the blocks that are used on the cliffs of mountains (usually a blend of stone & cobblestone)."
                + Configuration.NEW_LINE + "Set this to change this biome's cliff stone block." + BLOCKSTATE_HELP,
            ""
        );
        this.addProperty(SURFACE_CLIFF_STONE_BLOCK);

        SURFACE_CLIFF_COBBLE_BLOCK = new ConfigPropertyString(
            "Cliff Cobble Block ID",
            "Surfaces.Cliff Cobble Block",
            "Cliff blocks are the blocks that are used on the cliffs of mountains (usually a blend of stone & cobblestone)."
                + Configuration.NEW_LINE + "Set this to change this biome's cliff cobble block." + BLOCKSTATE_HELP,
            ""
        );
        this.addProperty(SURFACE_CLIFF_COBBLE_BLOCK);

        BEACH_BIOME = new ConfigPropertyInteger(
            "Beach Biome",
            "Beaches",
            "Biome ID to use for this biome's beach."
                + Configuration.NEW_LINE + "The only 'officially supported' values for this setting are:"
                + Configuration.NEW_LINE + "-1 = Automatic beach generation (RECOMMENDED), 16 = Vanilla Beach, 25 = Vanilla Stone Beach, 26 = Vanilla Cold Beach"
                + Configuration.NEW_LINE + "The ID of this biome = No beach"
                + Configuration.NEW_LINE + "Other biome IDs are allowed, but they have not been tested, may yield undesirable results, and will not be supported."
                + Configuration.NEW_LINE + "Note: If this biome has been hardcoded by RTG to use a specific beach, this setting will have no effect.",
            -1, -1, 255
        );
        this.addProperty(BEACH_BIOME);

        TREE_DENSITY_MULTIPLIER = new ConfigPropertyFloat(
            "RTG Tree Density Multiplier",
            "Decorations.Trees",
            "This setting allows you to increase/decrease the number of RTG trees that generate in this biome."
                + Configuration.NEW_LINE + "This setting overrides the global setting (see /.minecraft/config/RTG/rtg.cfg) and only affects trees generated by RTG."
                + Configuration.NEW_LINE + "Trees generated by this biome's decorator will adhere to their own density rules."
                + Configuration.NEW_LINE + "Set to -1.0 to use the global setting."
                + Configuration.NEW_LINE + "1.0 = Default tree generation; 2.0 = Twice as many trees; 0.5 = half as many trees; 0 = No trees",
            -1.0f, -1.0f, 5.0f
        );
        this.addProperty(TREE_DENSITY_MULTIPLIER);

        USE_CUSTOM_BIOME_TEMPERATURE = new ConfigPropertyBoolean(
            "Use Custom Temperature",
            "Biome Properties",
            "Set this to true if you want to change this biome's temperature",
            false
        );
        this.addProperty(USE_CUSTOM_BIOME_TEMPERATURE);

        BIOME_TEMPERATURE = new ConfigPropertyFloat(
            "Custom Temperature",
            "Biome Properties",
            "If you want to change this biome's temperature, enter a valid value here. [range: -2.0 ~ 2.0]"
                + Configuration.NEW_LINE + "Values in the range of 0.1 to 0.2 (non-inclusive) are INVALID and will result in a crash on startup."
                + Configuration.NEW_LINE + "If this value is empty, the biome's default temperature will be used."
                + Configuration.NEW_LINE + "Please note that changing a biome's temperature does NOT affect its climate type (DESERT, WARM, COOL, ICY)."
                + Configuration.NEW_LINE + "For more info, visit http://minecraft.gamepedia.com/Biome#Temperature",
            1f, -2f, 2f
        );
        this.addProperty(BIOME_TEMPERATURE);

        SURFACE_BLEED_IN = new ConfigPropertyBoolean(
            "Surface Bleed In",
            "Surfaces.Surface Bleed",
            "Set to false if other biomes shouldn't bleed into this one",
            false
        );
        this.addProperty(SURFACE_BLEED_IN);

        SURFACE_BLEED_OUT = new ConfigPropertyBoolean(
            "Surface Bleed Out",
            "Surfaces.Surface Bleed",
            "Set to false if this biome shouldn't bleed into other biomes",
            false
        );
        this.addProperty(SURFACE_BLEED_OUT);


        /* OPTIONAL CONFIGS - These properties get 'added' by the individual biomes when relevant, so don't 'add' them here.*/

        SURFACE_MIX_BLOCK = new ConfigPropertyString(
            "Mix Block ID",
            "Surfaces.Mix Top Block",
            "Set this to change this biome's mix block" + BLOCKSTATE_HELP,
            ""
        );

        SURFACE_MIX_FILLER_BLOCK = new ConfigPropertyString(
            "Mix Filler Block ID",
            "Surfaces.Mix Filler Block",
            "Set this to change this biome's mix filler block (the block underneath the mix block)." + BLOCKSTATE_HELP,
            ""
        );

        SURFACE_MIX_2_BLOCK = new ConfigPropertyString(
            "Mix 2 Block ID",
            "Surfaces.Mix 2 Top Block",
            "Set this to change this biome's 2nd mix block." + BLOCKSTATE_HELP,
            ""
        );

        SURFACE_MIX_3_BLOCK = new ConfigPropertyString(
            "Mix 3 Block ID",
            "Surfaces.Mix 3 Top Block",
            "Set this to change this biome's 3rd mix block." + BLOCKSTATE_HELP,
            ""
        );

        SURFACE_MIX_4_BLOCK = new ConfigPropertyString(
            "Mix 4 Block ID",
            "Surfaces.Mix 4 Top Block",
            "Set this to change this biome's 4th mix block." + BLOCKSTATE_HELP,
            ""
        );

        ALLOW_PLATEAU_MODIFICATIONS = new ConfigPropertyBoolean(
            "Allow Plateau Modifications",
            "Plateaus",
            "",
            false
        );

        PLATEAU_GRADIENT_BLOCK_LIST = new ConfigPropertyArrayString(
            "Plateau Gradient Block List",
            "Plateaus.Gradient Blocks",
            "A list of block states to use for this biome's plateau gradients." + BLOCKSTATE_HELP,
            new String[0]
        );

        FALLEN_LOG_DENSITY_MULTIPLIER = new ConfigPropertyFloat(
            "Fallen Log Density Multiplier",
            "Decorations.Logs",
            "This setting allows you to increase/decrease the number of fallen logs that generate in this biome."
                + Configuration.NEW_LINE + "1.0 = Default density; 2.0 = Twice as many fallen logs; 0.5 = half as many fallen logs; 0 = No fallen logs",
            1f, 0f, 5.0f
        );

        ALLOW_LOGS         = new ConfigPropertyBoolean("Allow Logs",         "Decorations.Logs", "", true);
        ALLOW_PALM_TREES   = new ConfigPropertyBoolean("Allow Palm Trees",   "Decorations.Palm Trees", "", true);
        ALLOW_CACTUS       = new ConfigPropertyBoolean("Allow Cactus",       "Decorations.Cactus", "", true);
        ALLOW_COBWEBS      = new ConfigPropertyBoolean("Allow Cobwebs",      "Decorations.Cobwebs", "", true);
        ALLOW_WHEAT        = new ConfigPropertyBoolean("Allow Wheat",        "Decorations.Wheat", "", true);
        ALLOW_PONDS_WATER  = new ConfigPropertyBoolean("Allow Ponds (Water)","Decorations.Ponds", "", true);
        WHEAT_CHANCE       = new ConfigPropertyInteger("Wheat (Chance)",     "Decorations.Wheat", "", 0, 0, Integer.MAX_VALUE);
        WHEAT_MIN_Y        = new ConfigPropertyInteger("Wheat (Min Y)",      "Decorations.Wheat", "", 0, 0, Integer.MAX_VALUE);
        WHEAT_MAX_Y        = new ConfigPropertyInteger("Wheat (Max Y)",      "Decorations.Wheat", "", 0, 0, Integer.MAX_VALUE);
        USE_ARCTIC_SURFACE = new ConfigPropertyBoolean("Use Arctic Surface", "Surfaces.Arctic Surface", "", true);
        ALLOW_ICE_TREES    = new ConfigPropertyBoolean("Allow Ice Trees",    "Decorations.Ice Trees", "", true);
        ALLOW_SPONGE       = new ConfigPropertyBoolean("Allow Sponge",       "Decorations.Sponge", "", true);
        ALLOW_OCEAN_WAVES  = new ConfigPropertyBoolean("Allow Ocean Waves",  "Decorations.Waves", "", true);
    }

// TODO: [1.12] Remove this after updating IRealisticBiome#biomeSlug
    public static String formatSlug(String s) {

        s = s.toLowerCase();
        s = s.replaceAll("\\+", "plus");
        s = s.replaceAll("\\W", "");

        return s;
    }
}
