package rtg.config.rtg;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import org.apache.logging.log4j.Level;

import java.io.File;

public class ConfigRTG
{
	public static Configuration config;
	
    /* ==================== Bedrock ==================== */
	
    public static int flatBedrockLayers = 0;
    public static String bedrockBlockId = "minecraft:bedrock";
    public static int bedrockBlockByte = 0;
    
    /* ==================== Biomes ==================== */

	public static boolean enableRTGBiomeDecorations = true;
	public static boolean enableRTGBiomeSurfaces = true;
	public static int patchBiomeId = 1;
	public static int singleBiomeId = -1;
    
    /* ==================== Boulders ==================== */

    public static boolean enableCobblestoneBoulders = true;
    public static int cobblestoneBoulderChance = 1;
    
    /* ==================== Caves ==================== */

    public static boolean enableCaveModifications = true;
    public static boolean enableCaves = true;
    public static int caveDensity = 8;
    public static int caveFrequency = 16;

    /* ==================== Dunes ==================== */

    public static int duneHeight = 4;

    /* ==================== Debugging ==================== */

    public static boolean showDebugInfo = false;
    public static boolean enableDebugging = false;

    /* ==================== Dungeons ==================== */

    public static boolean generateDungeons = true;

    /* ==================== Lakes (Surface) ==================== */

    public static boolean enableWaterSurfaceLakes = true;
    public static int waterSurfaceLakeChance = 10;

    public static boolean enableLavaSurfaceLakes = true;
    public static int lavaSurfaceLakeChance = 10;
    
    /* ==================== Lakes (Underground) ==================== */

    public static boolean enableWaterUndergroundLakes = true;
    public static int waterUndergroundLakeChance = 10;

    public static boolean enableLavaUndergroundLakes = true;
    public static int lavaUndergroundLakeChance = 10;
    
    /* ==================== Mineshafts ==================== */

    public static boolean generateMineshafts = true;
    
    /* ==================== Ore Gen ==================== */

    public static boolean generateOreCoal = true;
    public static boolean generateOreIron = true;
    public static boolean generateOreGold = true;
    public static boolean generateOreRedstone = true;
    public static boolean generateOreLapis = true;
    public static boolean generateOreDiamond = true;
    public static boolean generateOreEmerald = true;

    /* ==================== Ravines ==================== */
    
    public static boolean enableRavineModifications = true;
    public static boolean enableRavines = false;
    public static int ravineFrequency = 50;

    /* ==================== Scattered Features ==================== */
    
    public static boolean generateScatteredFeatures = true;
    public static int minDistanceScatteredFeatures = 12; // Vanilla = 8
    public static int maxDistanceScatteredFeatures = 48; // Vanilla = 32
    public static boolean generateOceanMonuments = true;

    /* ==================== Snow ==================== */

    public static boolean enableSnowLayers = true;

    /* ==================== Strongholds ==================== */

    public static boolean generateStrongholds = true;

    /* ==================== Terrain Shadowing ==================== */

    public static String shadowStoneBlockId = "minecraft:stained_hardened_clay";
    public static int shadowStoneBlockByte = 9;

    public static String shadowDesertBlockId = "minecraft:stained_hardened_clay";
    public static int shadowDesertBlockByte = 0;

	public static boolean enableUBCStoneShadowing = true;
	public static boolean enableUBCDesertShadowing = true;

    /* ==================== Trees ==================== */

	public static boolean allowTreesToGenerateOnSand = true;
	public static boolean allowShrubsToGenerateBelowSurface = true;

    /* ==================== Villages ==================== */

    public static boolean generateVillages = true;

    public static boolean enableVillageModifications = enableVillageTweaks();
    public static int villageSize = 0;
    public static int minDistanceVillages = 12; // Vanilla = 8
    public static int maxDistanceVillages = 48; // Vanilla = 32

    public static boolean villageCrashFix = (Loader.isModLoaded("enviromine"));
    
    /* ==================== Volcanoes ==================== */

    public static String volcanoBlockId = "minecraft:obsidian";
    public static int volcanoBlockByte = 0;
    public static boolean enableVolcanoEruptions = true;


	public static void init(File configFile)
	{
		config = new Configuration(configFile);
		
		try 
		{
			config.load();
			
			/* ==================== Bedrock ==================== */
			
            flatBedrockLayers = config.getInt("Number of flat bedrock layers", "Bedrock", flatBedrockLayers, 0, 5, "0 = Normal bedrock (rough pattern); 1-5 = Number of flat bedrock layers to generate" + Configuration.NEW_LINE);
            
            bedrockBlockId = config.getString(
                "Bedrock block ID",
                "Bedrock", 
                bedrockBlockId,
                "The block to use for the bottom of the Overworld." +
                Configuration.NEW_LINE
            );
            
            bedrockBlockByte = config.getInt("Bedrock block meta value", "Bedrock", bedrockBlockByte, 0, 15, "The meta value of the bedrock block." + Configuration.NEW_LINE);
            
            /* ==================== Biomes ==================== */
            
            enableRTGBiomeDecorations = config.getBoolean(
                "Enable RTG Biome Decorations",
                "Biomes",
                enableRTGBiomeDecorations,
                "If TRUE, uses the individual biome settings in the biome config files. If FALSE, disables all RTG decorations and uses vanilla decorations instead."
                + Configuration.NEW_LINE
            );
            
            enableRTGBiomeSurfaces = config.getBoolean(
                "Enable RTG Biome Surfaces",
                "Biomes",
                enableRTGBiomeSurfaces,
                "If TRUE, uses the individual biome settings in the biome config files. If FALSE, disables all RTG surfaces and uses vanilla surfaces instead."
                + Configuration.NEW_LINE
            );
            
            patchBiomeId = config.getInt(
                "Patch Biome ID",
                "Biomes",
                patchBiomeId,
                -1,
                255,
                "If RTG tries to generate an unsupported biome or a biome that has an ID conflict, it will generate this biome instead."
                + Configuration.NEW_LINE +
                "If set to -1, RTG will crash instead of generating the patch biome. You might want to do this if you're making a mod pack"
                + Configuration.NEW_LINE +
                "and want to make sure all biomes are generating correctly."
                + Configuration.NEW_LINE +
                "Default = Vanilla Plains"
                + Configuration.NEW_LINE
            );
            
            singleBiomeId = config.getInt(
                "Generate only this biome ID", 
                "Biomes", 
                singleBiomeId, 
                -1, 255, 
                "If you enter a biome ID here, the whole world will consist of only that biome (and rivers). Set to -1 to generate the world normally." +
                Configuration.NEW_LINE +
                "Vanilla biome IDs can be found here: http://goo.gl/WqlAfV" +
                Configuration.NEW_LINE +
                "For modded biome IDs, use NEI and go [Options] > [Tools] > [Data Dumps] > Biomes > [Dump], and then refer to the 'biome.csv' file which can be found in your '/.minecraft/dumps' folder." +
                Configuration.NEW_LINE
            );
            
            /* ==================== Boulders ==================== */
            
            enableCobblestoneBoulders = config.getBoolean("Enable Cobblestone Boulders", "Boulders", enableCobblestoneBoulders, "");
            cobblestoneBoulderChance = config.getInt("1/x chance that Cobblestone Boulders will generate if given the opportunity to do so during world gen", "Boulders", cobblestoneBoulderChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance" + Configuration.NEW_LINE);
            
            /* ==================== Caves ==================== */
            
            enableCaveModifications = config.getBoolean(
                "Enable Cave Modifications",
                "Caves",
                enableCaveModifications,
                "Must be set to TRUE for the other cave settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with cave generation at all."
                + Configuration.NEW_LINE
            );
            
            enableCaves = config.getBoolean("Enable Caves", "Caves", enableCaves, "");
            caveDensity = config.getInt("Cave Density", "Caves", caveDensity, 1, 40, "This setting controls the size of caves." + Configuration.NEW_LINE + "HIGHER values = BIGGER caves & MORE lag. (14 = vanilla cave density)" + Configuration.NEW_LINE);
            caveFrequency = config.getInt("Cave Frequency", "Caves", caveFrequency, 1, 40, "This setting controls the number of caves that generate." + Configuration.NEW_LINE + "LOWER values = MORE caves & MORE lag. (6 = vanilla cave frequency)" + Configuration.NEW_LINE);
            
            /* ==================== Dunes ==================== */
            
            duneHeight = config.getInt("Height of Dunes", "Dunes", duneHeight, 1, 12, "This setting controls the height of both sand dunes and snow dunes." + Configuration.NEW_LINE + "Higher values = taller dunes." + Configuration.NEW_LINE);
            
            /* ==================== Debugging ==================== */
            
            showDebugInfo = config.getBoolean("Show Debug Info in F3 Screen", "Debugging", showDebugInfo, "");
            enableDebugging = config.getBoolean("Enable Debugging", "Debugging", enableDebugging, "WARNING: This should only be enabled if you know what you're doing." + Configuration.NEW_LINE);
            
            /* ==================== Dungeons ==================== */
            
            generateDungeons = config.getBoolean("Generate Dungeons", "Dungeons", generateDungeons, "");
            
            /* ==================== Lakes (Surface) ==================== */
            
            enableWaterSurfaceLakes = config.getBoolean("Enable Water Surface Lakes", "Lakes (Surface)", enableWaterSurfaceLakes, "");
            waterSurfaceLakeChance = config.getInt("1/x chance that Water Surface Lakes will generate if given the opportunity to do so during world gen", "Lakes (Surface)", waterSurfaceLakeChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance" + Configuration.NEW_LINE);
            
            enableLavaSurfaceLakes = config.getBoolean("Enable Lava Surface Lakes", "Lakes (Surface)", enableLavaSurfaceLakes, "");
            lavaSurfaceLakeChance = config.getInt("1/x chance that Lava Surface Lakes will generate if given the opportunity to do so during world gen", "Lakes (Surface)", lavaSurfaceLakeChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance" + Configuration.NEW_LINE);
            
            /* ==================== Lakes (Underground) ==================== */
            
            enableWaterUndergroundLakes = config.getBoolean("Enable Water Underground Lakes", "Lakes (Underground)", enableWaterUndergroundLakes, "");
            waterUndergroundLakeChance = config.getInt("1/x chance that Water Underground Lakes will generate if given the opportunity to do so during world gen", "Lakes (Underground)", waterUndergroundLakeChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance" + Configuration.NEW_LINE);
            
            enableLavaUndergroundLakes = config.getBoolean("Enable Lava Underground Lakes", "Lakes (Underground)", enableLavaUndergroundLakes, "");
            lavaUndergroundLakeChance = config.getInt("1/x chance that Lava Underground Lakes will generate if given the opportunity to do so during world gen", "Lakes (Underground)", lavaUndergroundLakeChance, 1, 100, "1 = Always generate if possible; 2 = 50% chance; 4 = 25% chance" + Configuration.NEW_LINE);
            
            /* ==================== Mineshafts ==================== */
            
            generateMineshafts = config.getBoolean("Generate Mineshafts", "Mineshafts", generateMineshafts, "");
            
            /* ==================== Ore Gen ==================== */
            
            generateOreCoal = config.getBoolean("Generate Coal Ore", "Ore Gen", generateOreCoal, "");
            generateOreIron = config.getBoolean("Generate Iron Ore", "Ore Gen", generateOreIron, "");
            generateOreGold = config.getBoolean("Generate Gold Ore", "Ore Gen", generateOreGold, "");
            generateOreRedstone = config.getBoolean("Generate Redstone Ore", "Ore Gen", generateOreRedstone, "");
            generateOreLapis = config.getBoolean("Generate Lapis Lazuli Ore", "Ore Gen", generateOreLapis, "");
            generateOreDiamond = config.getBoolean("Generate Diamond Ore", "Ore Gen", generateOreDiamond, "");
            generateOreEmerald = config.getBoolean("Generate Emerald Ore", "Ore Gen", generateOreEmerald, "");
            
            /* ==================== Ravines ==================== */
            
            enableRavineModifications = config.getBoolean(
                "Enable Ravine Modifications",
                "Ravines",
                enableRavineModifications,
                "Must be set to TRUE for the other ravine settings to have any effect."
                + Configuration.NEW_LINE +
                "If FALSE, RTG won't interfere with ravine generation at all."
                + Configuration.NEW_LINE
            );
            
            enableRavines = config.getBoolean("Enable Ravines", "Ravines", enableRavines, "");
            ravineFrequency = config.getInt("Ravine Frequency", "Ravines", ravineFrequency, 1, 100, "This setting controls the number of ravines that generate." + Configuration.NEW_LINE + "LOWER values = MORE ravines & MORE lag. (50 = vanilla ravine frequency)" + Configuration.NEW_LINE);
            
            /* ==================== Scattered Features ==================== */
            
            generateScatteredFeatures = config.getBoolean("Generate Scattered Features", "Scattered Features", generateScatteredFeatures, "");
            
            minDistanceScatteredFeatures = config.getInt("Minimum distance between scattered features", "Scattered Features", minDistanceScatteredFeatures, 1, Integer.MAX_VALUE, "Scattered features = desert temples, jungle temples, and witch huts; 8 = Vanilla" + Configuration.NEW_LINE);
            maxDistanceScatteredFeatures = config.getInt("Maximum distance between scattered features", "Scattered Features", maxDistanceScatteredFeatures, 1, Integer.MAX_VALUE, "Scattered features = desert temples, jungle temples, and witch huts; 32 = Vanilla" + Configuration.NEW_LINE);
            
            /* ==================== Snow ==================== */
            
            enableSnowLayers = config.getBoolean("Enable Snow Layers", "Snow", enableSnowLayers, "This applies to newly-generated chunks only. Snow layers will still appear in cold/snowy biomes after it snows." + Configuration.NEW_LINE);
            
            /* ==================== Strongholds ==================== */
            
            generateStrongholds = config.getBoolean("Generate Strongholds", "Strongholds", generateStrongholds, "");

            /* ==================== Ocean Monuments ==================== */

            generateOceanMonuments = config.getBoolean("Generate Ocean Monuments", "Ocean Monuments", generateOceanMonuments, "");

            /* ==================== Terrain Shadowing ==================== */
            
            shadowStoneBlockId = config.getString(
                "Stone shadow block ID",
                "Terrain shadowing", 
                shadowStoneBlockId,
                "The block to use for stone terrain shadowing, typically seen on the cliffs of stone mountains. Defaults to stained hardened clay." +
                Configuration.NEW_LINE
            );
            
            shadowStoneBlockByte = config.getInt("Stone shadow block meta value", "Terrain shadowing", shadowStoneBlockByte, 0, 15, "The meta value of the shadow block for stone cliffs. Defaults to " + shadowStoneBlockByte +  " (cyan)." + Configuration.NEW_LINE);
            
            shadowDesertBlockId = config.getString(
                "Desert shadow block ID",
                "Terrain shadowing", 
                shadowDesertBlockId,
                "The block to use for desert terrain shadowing, typically seen on the cliffs of desert mountains. Defaults to stained hardened clay." +
                Configuration.NEW_LINE
            );
            
            shadowDesertBlockByte = config.getInt("Desert shadow block meta value", "Terrain shadowing", shadowDesertBlockByte, 0, 15, "The meta value of the shadow block for desert cliffs. Defaults to " + shadowDesertBlockByte +  " (white)." + Configuration.NEW_LINE);
            
            enableUBCStoneShadowing = config.getBoolean(
                "UBC Mode (Stone)",
                "Terrain shadowing",
                enableUBCStoneShadowing,
                "Set this to TRUE to allow UBC to override stone shadowing."
                + Configuration.NEW_LINE +
                "This setting doesn't have any effect if UBC is not installed."
                + Configuration.NEW_LINE
            );
            
            enableUBCDesertShadowing = config.getBoolean(
                "UBC Mode (Desert)",
                "Terrain shadowing",
                enableUBCDesertShadowing,
                "Set this to TRUE to allow UBC to override desert shadowing."
                + Configuration.NEW_LINE +
                "This setting doesn't have any effect if UBC is not installed."
                + Configuration.NEW_LINE
            );

            /* ==================== Trees ==================== */
            
            allowTreesToGenerateOnSand = config.getBoolean(
                "Allow Trees to Generate on Sand",
                "Trees",
                allowTreesToGenerateOnSand,
                "Set this to FALSE to prevent trees from generating on sand."
                + Configuration.NEW_LINE +
                "This setting only affects trees generated by RTG. Trees generated by a biome's decorator"
                + Configuration.NEW_LINE +
                "will adhere to their own generation rules. (RTG's Palm Trees ignore this setting.)"
                + Configuration.NEW_LINE
            );
            
            allowShrubsToGenerateBelowSurface = config.getBoolean(
                "Allow Shrubs to Generate Below Surface",
                "Trees",
                allowShrubsToGenerateBelowSurface,
                "Set this to FALSE to prevent shrub trunks from generating below the surface."
                + Configuration.NEW_LINE
            );

            /* ==================== Villages ==================== */
            
            enableVillageModifications = config.getBoolean(
                "Enable village modifications",
                "Villages",
                enableVillageModifications,
                "Set this to FALSE to resolve issues with mods that also modify villages."
                + Configuration.NEW_LINE +
                "If set to FALSE, the 'Minimum distance between villages', 'Maximum distance between villages' & 'Size of villages' settings will have no effect."
                + Configuration.NEW_LINE
            );
            
            generateVillages = config.getBoolean("Generate Villages", "Villages", generateVillages, "");
            villageSize = config.getInt("Size of villages", "Villages", villageSize, 0, 10, "Higher values = bigger villages; 0 = Vanilla" + Configuration.NEW_LINE);
            minDistanceVillages = config.getInt("Minimum distance between villages", "Villages", minDistanceVillages, 1, Integer.MAX_VALUE, "Higher values = villages further apart; 8 = Vanilla" + Configuration.NEW_LINE);
            maxDistanceVillages = config.getInt("Maximum distance between villages", "Villages", maxDistanceVillages, 1, Integer.MAX_VALUE, "Lower values = villages closer together; 32 = Vanilla" + Configuration.NEW_LINE);
            
            villageCrashFix = config.getBoolean(
                "Village Crash Fix",
                "Villages",
                villageCrashFix,
                "Set this to TRUE to if you are experiencing 'java.util.ConcurrentModificationException' crashes related to village generation."
                + Configuration.NEW_LINE +
                "Defaults to FALSE unless EnviroMine is installed, in which case it defaults to TRUE."
                + Configuration.NEW_LINE
            );

            /* ==================== Volcanoes ==================== */
            
            volcanoBlockId = config.getString(
                "Volcano block ID",
                "Volcanoes", 
                volcanoBlockId,
                "The block to use for the top of the volcano. Defaults to Obsidian." +
                Configuration.NEW_LINE
            );
            
            volcanoBlockByte = config.getInt("Volcano block meta value", "Volcanoes", volcanoBlockByte, 0, 15, "The meta value of the volcano block." + Configuration.NEW_LINE);
            
            enableVolcanoEruptions = config.getBoolean(
                "Enable volcano eruptions",
                "Volcanoes",
                enableVolcanoEruptions,
                "Set this to FALSE to prevent lava from flowing down the sides of volcanoes."
                + Configuration.NEW_LINE
            );
            
		}
		catch (Exception e) 
		{
		    Logger.error("RTG has had a problem loading RTG configuration.");
		}
		finally 
		{
			if (config.hasChanged())
			{
				config.save();
			}
		}
	}
	
	private static boolean enableVillageTweaks()
	{
	    boolean enableVillageModifications = true;
	    
	    //if (Loader.isModLoaded("GalacticraftMars")) { enableVillageModifications = false; }
	    //if (Loader.isModLoaded("GalaxySpace")) { enableVillageModifications = false; }
	    
	    return enableVillageModifications;
	}
}