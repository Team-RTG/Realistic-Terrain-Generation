package rtg.world.biome.realistic.eccentricbiomes;

import net.minecraft.world.biome.BiomeGenBase;

import cpw.mods.fml.common.Loader;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.eccentricbiomes.config.*;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeECCBase extends RealisticBiomeBase
{
    public static RealisticBiomeBase eccAmethyst;
    public static RealisticBiomeBase eccAmethystGrove;
    public static RealisticBiomeBase eccArctic;
    public static RealisticBiomeBase eccAutilField;
    public static RealisticBiomeBase eccBone;
    public static RealisticBiomeBase eccDeadLand;
    public static RealisticBiomeBase eccDeathForest;
    public static RealisticBiomeBase eccFertile;
    public static RealisticBiomeBase eccFlamage;
    public static RealisticBiomeBase eccGlassland;
    public static RealisticBiomeBase eccHighLands;
    public static RealisticBiomeBase eccHive;
    public static RealisticBiomeBase eccObsidianForest;
    public static RealisticBiomeBase eccOcher;
    public static RealisticBiomeBase eccSnowyDesert;
    public static RealisticBiomeBase eccVoid;
    
    public RealisticBiomeECCBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
    
        super(config, b, riverbiome, t, s);
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
    
    public static void addBiomes()
    {
    
        if (Loader.isModLoaded("mod_ebm"))
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
                    
                    BiomeGenBase eccBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();

                    if (biomeName.equals("Amethyst") && biomeClass.equals("ebm.com.mce.gen.biomes.Amethyst")) {
                        eccAmethyst = new RealisticBiomeECCAmethyst(eccBiome, BiomeConfigECC.biomeConfigECCAmethyst);
                    }
                    else if (biomeName.equals("Amethyst Grove") && biomeClass.equals("ebm.com.mce.gen.biomes.AmethystGrove")) {
                        eccAmethystGrove = new RealisticBiomeECCAmethystGrove(eccBiome, BiomeConfigECC.biomeConfigECCAmethystGrove);
                    }
                    else if (biomeName.equals("Arctic") && biomeClass.equals("ebm.com.mce.gen.biomes.Arctic")) {
                        eccArctic = new RealisticBiomeECCArctic(eccBiome, BiomeConfigECC.biomeConfigECCArctic);
                    }
                    else if (biomeName.equals("Autil Field") && biomeClass.equals("ebm.com.mce.gen.biomes.AutilField")) {
                        eccAutilField = new RealisticBiomeECCAutilField(eccBiome, BiomeConfigECC.biomeConfigECCAutilField);
                    }
                    else if (biomeName.equals("Bone") && biomeClass.equals("ebm.com.mce.gen.biomes.Bone")) {
                        eccBone = new RealisticBiomeECCBone(eccBiome, BiomeConfigECC.biomeConfigECCBone);
                    }
                    else if (biomeName.equals("Dead Land") && biomeClass.equals("ebm.com.mce.gen.biomes.DeadLand")) {
                        eccDeadLand = new RealisticBiomeECCDeadLand(eccBiome, BiomeConfigECC.biomeConfigECCDeadLand);
                    }
                    else if (biomeName.equals("Death Forest") && biomeClass.equals("ebm.com.mce.gen.biomes.DeathForest")) {
                        eccDeathForest = new RealisticBiomeECCDeathForest(eccBiome, BiomeConfigECC.biomeConfigECCDeathForest);
                    }
                    else if (biomeName.equals("Fertile") && biomeClass.equals("ebm.com.mce.gen.biomes.Fertile")) {
                        eccFertile = new RealisticBiomeECCFertile(eccBiome, BiomeConfigECC.biomeConfigECCFertile);
                    }
                    else if (biomeName.equals("Flamage") && biomeClass.equals("ebm.com.mce.gen.biomes.Flamage")) {
                        eccFlamage = new RealisticBiomeECCFlamage(eccBiome, BiomeConfigECC.biomeConfigECCFlamage);
                    }
                    else if (biomeName.equals("Glassland") && biomeClass.equals("ebm.com.mce.gen.biomes.GlassLand")) {
                        eccGlassland = new RealisticBiomeECCGlassland(eccBiome, BiomeConfigECC.biomeConfigECCGlassland);
                    }
                    else if (biomeName.equals("High Lands") && biomeClass.equals("ebm.com.mce.gen.biomes.HighLand")) {
                        eccHighLands = new RealisticBiomeECCHighLands(eccBiome, BiomeConfigECC.biomeConfigECCHighLands);
                    }
                    else if (biomeName.equals("Hive") && biomeClass.equals("ebm.com.mce.gen.biomes.Hive")) {
                        eccHive = new RealisticBiomeECCHive(eccBiome, BiomeConfigECC.biomeConfigECCHive);
                    }
                    else if (biomeName.equals("Obsidian Forest") && biomeClass.equals("ebm.com.mce.gen.biomes.ObsidianForest")) {
                        eccObsidianForest = new RealisticBiomeECCObsidianForest(eccBiome, BiomeConfigECC.biomeConfigECCObsidianForest);
                    }
                    else if (biomeName.equals("Ocher") && biomeClass.equals("ebm.com.mce.gen.biomes.Ocher")) {
                        eccOcher = new RealisticBiomeECCOcher(eccBiome, BiomeConfigECC.biomeConfigECCOcher);
                    }
                    else if (biomeName.equals("Snowy Desert") && biomeClass.equals("ebm.com.mce.gen.biomes.SnowyDesert")) {
                        eccSnowyDesert = new RealisticBiomeECCSnowyDesert(eccBiome, BiomeConfigECC.biomeConfigECCSnowyDesert);
                    }
                    else if (biomeName.equals("Void") && biomeClass.equals("ebm.com.mce.gen.biomes.Void")) {
                        eccVoid = new RealisticBiomeECCVoid(eccBiome, BiomeConfigECC.biomeConfigECCVoid);
                    }
                }
            }
        }
    }
}
