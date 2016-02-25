package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.VillageMaterial;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.surface.vanilla.SurfaceVanillaColdBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaColdBeach;

import java.util.Random;

public class RealisticBiomeVanillaColdBeach extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.coldBeach.topBlock.getBlock();
	public static Block fillerBlock = BiomeGenBase.coldBeach.fillerBlock.getBlock();
	
	public RealisticBiomeVanillaColdBeach(BiomeConfig config)
	{
		super(config, 
			BiomeGenBase.coldBeach,
			BiomeGenBase.river,
			new TerrainVanillaColdBeach(),
			new SurfaceVanillaColdBeach(config, topBlock, fillerBlock, topBlock, fillerBlock, (byte)0, 1)
		);
        config.setVillageMaterial(VillageMaterial.Preset.ICE);
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
        
        // boulders
        for (int l = 0; l < 3f * strength; ++l)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();
            
            if (k1 < 95 && rand.nextInt(16) == 0) {
                (new WorldGenBlob(Blocks.cobblestone, 0, rand)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }
    }
}
