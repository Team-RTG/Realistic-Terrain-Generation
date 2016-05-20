package rtg.world.biome.realistic.forgottennature;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.forgottennature.config.BiomeConfigFN;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

public class RealisticBiomeFNBase extends RealisticBiomeBase
{

    public static RealisticBiomeBase fnCherryBlossomWoodland;
    public static RealisticBiomeBase fnCrystalForest;
    public static RealisticBiomeBase fnEucalyptusForest;
    public static RealisticBiomeBase fnGreatwoodForest;
    public static RealisticBiomeBase fnMapleForest;
    public static RealisticBiomeBase fnRedwoodForest;
    public static RealisticBiomeBase fnRedwoodForestHills;
    public static RealisticBiomeBase fnTropicalForest;
    public static RealisticBiomeBase fnTropicalForestHills;
    
    public RealisticBiomeFNBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
    
        super(config, b, riverbiome, t, s);
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
    
    public static void addBiomes()
    {
    
        if (Loader.isModLoaded("ForgottenNature"))
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
                    
                    BiomeGenBase fnBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();
                    
                    if (biomeName == "Cherry Blossom Woodland" && biomeClass == "ForgottenNature.Biomes.BiomeGenCherryBlossomWoodland")
                    {
                        fnCherryBlossomWoodland = new RealisticBiomeFNCherryBlossomWoodland(fnBiome, BiomeConfigFN.biomeConfigFNCherryBlossomWoodland);
                    }
                    else if (biomeName == "Crystal Forest" && biomeClass == "ForgottenNature.Biomes.BiomeGenCrystalForest")
                    {
                        fnCrystalForest = new RealisticBiomeFNCrystalForest(fnBiome, BiomeConfigFN.biomeConfigFNCrystalForest);
                    }
                    else if (biomeName == "Eucalyptus Forest" && biomeClass == "ForgottenNature.Biomes.BiomeGenEucalyptusForest")
                    {
                        fnEucalyptusForest = new RealisticBiomeFNEucalyptusForest(fnBiome, BiomeConfigFN.biomeConfigFNEucalyptusForest);
                    }
                    else if (biomeName == "Greatwood Forest" && biomeClass == "ForgottenNature.Biomes.BiomeGenGreatwoodForest")
                    {
                        fnGreatwoodForest = new RealisticBiomeFNGreatwoodForest(fnBiome, BiomeConfigFN.biomeConfigFNGreatwoodForest);
                    }
                    else if (biomeName == "Maple Forest" && biomeClass == "ForgottenNature.Biomes.BiomeGenMapleForest")
                    {
                        fnMapleForest = new RealisticBiomeFNMapleForest(fnBiome, BiomeConfigFN.biomeConfigFNMapleForest);
                    }
                    else if (biomeName == "Redwood Forest" && biomeClass == "ForgottenNature.Biomes.BiomeGenRedwoodForest")
                    {
                        fnRedwoodForest = new RealisticBiomeFNRedwoodForest(fnBiome, BiomeConfigFN.biomeConfigFNRedwoodForest);
                    }
                    else if (biomeName == "Redwood Forest Hills" && biomeClass == "ForgottenNature.Biomes.BiomeGenRedwoodForestHills")
                    {
                        fnRedwoodForestHills = new RealisticBiomeFNRedwoodForestHills(fnBiome, BiomeConfigFN.biomeConfigFNRedwoodForestHills);
                    }
                    else if (biomeName == "Tropical Forest" && biomeClass == "ForgottenNature.Biomes.BiomeGenTropicalForest")
                    {
                        fnTropicalForest = new RealisticBiomeFNTropicalForest(fnBiome, BiomeConfigFN.biomeConfigFNTropicalForest);
                    }
                    else if (biomeName == "Tropical Forest Hills" && biomeClass == "ForgottenNature.Biomes.BiomeGenTropicalForestHills")
                    {
                        fnTropicalForestHills = new RealisticBiomeFNTropicalForestHills(fnBiome, BiomeConfigFN.biomeConfigFNTropicalForestHills);
                    }
                }
            }
        }
    }
}
