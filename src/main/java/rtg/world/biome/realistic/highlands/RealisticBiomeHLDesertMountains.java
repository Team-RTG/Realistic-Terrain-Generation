package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.highlands.SurfaceHLDesertMountains;
import rtg.world.gen.terrain.highlands.TerrainHLDesertMountains;

import java.util.Random;

public class RealisticBiomeHLDesertMountains extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.desertMountains;
    
    public static Block topBlock = hlBiome.topBlock.getBlock();
    public static Block fillerBlock = hlBiome.fillerBlock.getBlock();
    
    public RealisticBiomeHLDesertMountains(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLDesertMountains(230f, 100f, 0f),
            new SurfaceHLDesertMountains(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 90f, 30f, 1.5f));
    }

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
        // nothing to suppress the highlands inverted sandstone parabolas
    }
}
