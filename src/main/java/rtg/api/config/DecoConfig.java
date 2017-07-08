package rtg.api.config;

import net.minecraft.init.Blocks;

import rtg.api.config.property.ConfigPropertyBlockstate;
import rtg.api.config.property.ConfigPropertyBoolean;
import rtg.api.config.property.ConfigPropertyFloat;
import rtg.api.config.property.ConfigPropertyInt;


public class DecoConfig extends Config {

    /*
     * GLOBAL CONFIGS
     */

    public final ConfigPropertyBoolean ALLOW;
    public final ConfigPropertyBoolean CHECK_RIVER;
    public final ConfigPropertyFloat MIN_RIVER;
    public final ConfigPropertyFloat MAX_RIVER;

    /*
     * OPTIONAL CONFIGS
     */

    public final ConfigPropertyInt MIN_Y;
    public final ConfigPropertyInt MAX_Y;
    public final ConfigPropertyInt LOOPS;
    public final ConfigPropertyInt CHANCE;
    public final ConfigPropertyInt EQUALS_ZERO_CHANCE;
    public final ConfigPropertyInt NOT_EQUALS_ZERO_CHANCE;
    public final ConfigPropertyFloat STRENGTH_FACTOR;
    public final ConfigPropertyBoolean CHECK_WATER;
    public final ConfigPropertyBlockstate BOULDER_BLOCK;
    public final ConfigPropertyBoolean SAND_ONLY;
    public final ConfigPropertyBlockstate SOIL_BLOCK;
    public final ConfigPropertyBlockstate ADJACENT_BLOCK;
    public final ConfigPropertyInt MIN_ADJACENTS;
    public final ConfigPropertyInt CROP_TYPE;
    public final ConfigPropertyInt CROP_SIZE;
    public final ConfigPropertyInt CROP_DENSITY;
    public final ConfigPropertyInt CROP_HEIGHT;
    public final ConfigPropertyBoolean CROP_WATER;

    public DecoConfig() {

        /*
         * GLOBAL CONFIGS
         */

        /**
         * If false, the deco won't get generated during chunk decoration.
         * Currently, the only deco that uses allow=false is the DecoBaseBiomeDecorations deco, and it only gets
         * set to false when we need to generate ores in biomes that don't let the base biome handle decoration at all.
         */
        ALLOW = new ConfigPropertyBoolean(
            "Allow",
            "Decos",
            "Set to FALSE to prevent this deco from generating in this biome.",
            true
        );
        this.addProperty(ALLOW);

        CHECK_RIVER = new ConfigPropertyBoolean(
            "Check River",
            "Decos",
            "Set to TRUE to have this deco check the river.",
            false
        );
        this.addProperty(CHECK_RIVER).restrict();

        MIN_RIVER = new ConfigPropertyFloat("Min River", "Decos", "Minimum river value required to generate.", -2f, -2f, 2f);
        this.addProperty(MIN_RIVER).restrict();

        MAX_RIVER = new ConfigPropertyFloat("Min River", "Decos", "Maximum river value required to generate.", -2f, -2f, 2f);
        this.addProperty(MAX_RIVER).restrict();

        /*
         * OPTIONAL CONFIGS
         *
         * These properties get 'added' by the individual decos when relevant, so don't 'add' them here.
         */

        MIN_Y = new ConfigPropertyInt("Min Y", "Decos", "", 1, 1, 255);
        MAX_Y = new ConfigPropertyInt("Max Y", "Decos", "", 255, 1, 255);
        LOOPS = new ConfigPropertyInt("Loops", "Decos", "", 1, -1, Integer.MAX_VALUE);
        CHANCE = new ConfigPropertyInt("Chance", "Decos", "", 1, 1, Integer.MAX_VALUE);
        EQUALS_ZERO_CHANCE = new ConfigPropertyInt("Equals Zero Chance", "Decos", "", 1, 1, Integer.MAX_VALUE);
        NOT_EQUALS_ZERO_CHANCE = new ConfigPropertyInt("Not Equals Zero Chance", "Decos", "", 1, 1, Integer.MAX_VALUE);
        STRENGTH_FACTOR = new ConfigPropertyFloat("Strength Factor", "Decos", "", 2f, 0f, Float.MAX_VALUE);
        CHECK_WATER = new ConfigPropertyBoolean("Check Water", "Decos", "", true);
        BOULDER_BLOCK = new ConfigPropertyBlockstate("Boulder Block", "Decos", "", Blocks.COBBLESTONE.getDefaultState());
        SAND_ONLY = new ConfigPropertyBoolean("Sand Only", "Decos", "", false);
        SOIL_BLOCK = new ConfigPropertyBlockstate("Soil Block", "Decos", "", Blocks.GRASS.getDefaultState());
        ADJACENT_BLOCK = new ConfigPropertyBlockstate("Adjacent Block", "Decos", "", Blocks.AIR.getDefaultState());
        MIN_ADJACENTS = new ConfigPropertyInt("Min Adjacents", "Decos", "", 1, 1, Integer.MAX_VALUE);
        CROP_TYPE = new ConfigPropertyInt("Crop Type", "Decos", "0 = potatoes, 1 = carrots, 2 = beetroot, 3 = wheat", 3, 0, 3);
        CROP_SIZE = new ConfigPropertyInt("Crop Size", "Decos", "Higher = larger fields", 5, 1, 30);
        CROP_DENSITY = new ConfigPropertyInt("Crop Density", "Decos", "Higher = Crops in fields closer together.", 50, 1, 100);
        CROP_HEIGHT = new ConfigPropertyInt("Crop Height", "Decos", "Higher = Crops on more y levels, but crops tend to be less dense.", 2, 1, 5);
        CROP_WATER = new ConfigPropertyBoolean("Crop Water", "Decos", "Set to TRUE to generate water with crops.", true);
    }
}
