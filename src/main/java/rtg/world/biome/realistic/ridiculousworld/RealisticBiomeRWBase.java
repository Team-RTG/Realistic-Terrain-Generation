package rtg.world.biome.realistic.ridiculousworld;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.ridiculousworld.config.BiomeConfigRW;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeRWBase extends RealisticBiomeBase
{
    
    public static RealisticBiomeBase rwBotanicalGarden;
    public static RealisticBiomeBase rwMountainOfMadness;
    public static RealisticBiomeBase rwMurica;
    public static RealisticBiomeBase rwOssuary;
    public static RealisticBiomeBase rwRockCandyMountain;
    public static RealisticBiomeBase rwShadowFen;
    public static RealisticBiomeBase rwSpookyForest;
    
    public RealisticBiomeRWBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
    
        super(config, b, riverbiome, t, s);
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
    
    public static void addBiomes()
    {
    
        if (Loader.isModLoaded("RidiculousWorld"))
        {
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
            
            for (int i = 0; i < 256; i++)
            {
                if (b[i] != null)
                {
                    if (b[i].biomeName == null) {
                        Logger.warn("Biome ID %d has no name.", b[i].biomeID);
                        continue;
                    }
                    
                    BiomeGenBase rwBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();
                    
                    if (biomeName == "Botanical Garden" && biomeClass == "fox.spiteful.ridiculous.biomes.BiomeGenBotania")
                    {
                        rwBotanicalGarden = new RealisticBiomeRWBotanicalGarden(rwBiome, BiomeConfigRW.biomeConfigRWBotanicalGarden);
                    }
                    else if (biomeName == "Murica" && biomeClass == "fox.spiteful.ridiculous.biomes.BiomeGenMurica")
                    {
                        rwMurica = new RealisticBiomeRWMurica(rwBiome, BiomeConfigRW.biomeConfigRWMurica);
                    }
                    else if (biomeName == "Mountain of Madness" && biomeClass == "fox.spiteful.ridiculous.biomes.BiomeGenMadness")
                    {
                        rwMountainOfMadness = new RealisticBiomeRWMountainOfMadness(rwBiome, BiomeConfigRW.biomeConfigRWMountainOfMadness);
                    }
                    else if (biomeName == "Ossuary" && biomeClass == "fox.spiteful.ridiculous.biomes.BiomeGenOssuary")
                    {
                        rwOssuary = new RealisticBiomeRWOssuary(rwBiome, BiomeConfigRW.biomeConfigRWOssuary);
                    }
                    else if (biomeName == "Rock Candy Mountain" && biomeClass == "fox.spiteful.ridiculous.biomes.BiomeGenCandy")
                    {
                        rwRockCandyMountain = new RealisticBiomeRWRockCandyMountain(rwBiome, BiomeConfigRW.biomeConfigRWRockCandyMountain);
                    }
                    else if (biomeName == "Shadow Fen" && biomeClass == "fox.spiteful.ridiculous.biomes.BiomeGenShadow")
                    {
                        rwShadowFen = new RealisticBiomeRWShadowFen(rwBiome, BiomeConfigRW.biomeConfigRWShadowFen);
                    }
                    else if (biomeName == "Spooky Forest" && biomeClass == "fox.spiteful.ridiculous.biomes.BiomeGenSpooky")
                    {
                        rwSpookyForest = new RealisticBiomeRWSpookyForest(rwBiome, BiomeConfigRW.biomeConfigRWSpookyForest);
                    }
                }
            }
        }
    }
}
