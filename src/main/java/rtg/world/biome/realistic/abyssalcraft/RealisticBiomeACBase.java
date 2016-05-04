package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.abyssalcraft.config.BiomeConfigAC;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;

import cpw.mods.fml.common.Loader;

public class RealisticBiomeACBase extends RealisticBiomeBase
{

    public static RealisticBiomeBase acCoraliumInfestedSwamp;
    public static RealisticBiomeBase acDarklands;
    public static RealisticBiomeBase acDarklandsForest;
    public static RealisticBiomeBase acDarklandsHighland;
    public static RealisticBiomeBase acDarklandsMountains;
    public static RealisticBiomeBase acDarklandsPlains;

    public RealisticBiomeACBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
        super(config, b, riverbiome, t, s);
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
    
    public static void addBiomes()
    {
        if (Loader.isModLoaded("abyssalcraft"))
        {
        	if (ACBiomes.coralium_infested_swamp != null) acCoraliumInfestedSwamp = new RealisticBiomeACCoraliumInfestedSwamp(BiomeConfigAC.biomeConfigACCoraliumInfestedSwamp);
        	if (ACBiomes.darklands != null) acDarklands = new RealisticBiomeACDarklands(BiomeConfigAC.biomeConfigACDarklands);
        	if (ACBiomes.darklands_forest != null) acDarklandsForest = new RealisticBiomeACDarklandsForest(BiomeConfigAC.biomeConfigACDarklandsForest);
        	if (ACBiomes.darklands_hills != null) acDarklandsHighland = new RealisticBiomeACDarklandsHighland(BiomeConfigAC.biomeConfigACDarklandsHighland);
        	if (ACBiomes.darklands_mountains != null) acDarklandsMountains = new RealisticBiomeACDarklandsMountains(BiomeConfigAC.biomeConfigACDarklandsMountains);
        	if (ACBiomes.darklands_plains != null) acDarklandsPlains = new RealisticBiomeACDarklandsPlains(BiomeConfigAC.biomeConfigACDarklandsPlains);
        }
    }
}
