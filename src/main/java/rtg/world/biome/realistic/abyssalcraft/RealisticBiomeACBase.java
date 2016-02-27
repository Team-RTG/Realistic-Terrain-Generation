package rtg.world.biome.realistic.abyssalcraft;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.abyssalcraft.config.BiomeConfigAC;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import net.minecraftforge.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

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
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
            
            for (int i = 0; i < 256; i++)
            {
                if (b[i] != null)
                {
                    BiomeGenBase acBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();
                    
                    if (biomeName == "Coralium Infested Swamp" && biomeClass == "com.shinoow.abyssalcraft.common.world.biome.BiomeGenCorSwamp")
                    {
                        acCoraliumInfestedSwamp = new RealisticBiomeACCoraliumInfestedSwamp(acBiome, BiomeConfigAC.biomeConfigACCoraliumInfestedSwamp);
                    }
                    else if (biomeName == "Darklands" && biomeClass == "com.shinoow.abyssalcraft.common.world.biome.BiomeGenDarklands")
                    {
                        acDarklands = new RealisticBiomeACDarklands(acBiome, BiomeConfigAC.biomeConfigACDarklands);
                    }
                    else if (biomeName == "Darklands Forest" && biomeClass == "com.shinoow.abyssalcraft.common.world.biome.BiomeGenDarklandsForest")
                    {
                        acDarklandsForest = new RealisticBiomeACDarklandsForest(acBiome, BiomeConfigAC.biomeConfigACDarklandsForest);
                    }
                    else if (biomeName == "Darklands Highland" && biomeClass == "com.shinoow.abyssalcraft.common.world.biome.BiomeGenDarklandsHills")
                    {
                        acDarklandsHighland = new RealisticBiomeACDarklandsHighland(acBiome, BiomeConfigAC.biomeConfigACDarklandsHighland);
                    }
                    else if (biomeName == "Darklands Mountains" && biomeClass == "com.shinoow.abyssalcraft.common.world.biome.BiomeGenDarklandsMountains")
                    {
                        acDarklandsMountains = new RealisticBiomeACDarklandsMountains(acBiome, BiomeConfigAC.biomeConfigACDarklandsMountains);
                    }
                    else if (biomeName == "Darklands Plains" && biomeClass == "com.shinoow.abyssalcraft.common.world.biome.BiomeGenDarklandsPlains")
                    {
                        acDarklandsPlains = new RealisticBiomeACDarklandsPlains(acBiome, BiomeConfigAC.biomeConfigACDarklandsPlains);
                    }
                }
            }
        }
    }
}
