package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
<<<<<<< HEAD
import highlands.api.HighlandsBlocks;
=======
>>>>>>> FETCH_HEAD
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLOutback;
import rtg.world.gen.terrain.highlands.TerrainHLOutback;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLOutback extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.outback;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLOutback(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
            new TerrainHLOutback(300f),
<<<<<<< HEAD
            new SurfaceHLOutback(topBlock, fillerBlock, (byte) 1, 1));
=======
            new SurfaceHLOutback(topBlock, fillerBlock, (byte) 0, 1));
        
>>>>>>> FETCH_HEAD
        this.config = config;
    }
}
