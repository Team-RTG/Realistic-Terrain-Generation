package rtg.world.biome.realistic.arsmagica;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.arsmagica.config.BiomeConfigAM;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameData;

public class RealisticBiomeAMBase extends RealisticBiomeBase
{
    public static RealisticBiomeBase amWitchwoodForest;
    
    protected Block witchwoodLogBlock = GameData.getBlockRegistry().getObject("arsmagica2:WitchwoodLog");
    protected byte witchwoodLogMeta = (byte)0;
    protected Block witchwoodLeavesBlock = GameData.getBlockRegistry().getObject("arsmagica2:WitchwoodLeaves");
    protected byte witchwoodLeavesMeta = (byte)0;
    
    public RealisticBiomeAMBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
    
        super(config, b, riverbiome, t, s);
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
    
    public static void addBiomes()
    {
    
        if (Loader.isModLoaded("arsmagica2"))
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
                    
                    BiomeGenBase amBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();
                    
                    if (biomeName == "WitchwoodForest" && biomeClass == "am2.worldgen.BiomeWitchwoodForest")
                    {
                        amWitchwoodForest = new RealisticBiomeAMWitchwoodForest(amBiome, BiomeConfigAM.biomeConfigAMWitchwoodForest);
                    }
                }
            }
        }
    }
}
