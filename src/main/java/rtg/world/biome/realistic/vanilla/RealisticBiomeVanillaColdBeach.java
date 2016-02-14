package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.util.BlockPos;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdBeach;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaColdBeach extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.coldBeach.topBlock.getBlock();
	public static Block fillerBlock = BiomeGenBase.coldBeach.fillerBlock.getBlock();
	
	public RealisticBiomeVanillaColdBeach(BiomeConfig config)
	{
		super(
			BiomeGenBase.coldBeach,
			BiomeGenBase.river,
			new TerrainVanillaColdBeach(),
			new SurfaceVanillaColdBeach(topBlock, fillerBlock, topBlock, fillerBlock, (byte)0, 1)
		);
		
		this.config = config;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        // boulders
        for (int l = 0; l < 3f * strength; ++l)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getChunkFromBlockCoords(new BlockPos(i1, 1, j1)).getHeightValue(i1,j1);
            
            if (k1 < 95 && rand.nextInt(16) == 0) {
                (new WorldGenBlob(Blocks.cobblestone, 0, rand)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }
    }
}
