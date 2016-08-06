package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPMountain;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPMountain;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPMountain;

import java.util.Random;

public class RealisticBiomeBOPMountain extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPBiomes.mountain.get();
	
	public static IBlockState topBlock = bopBiome.topBlock;
	public static IBlockState fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPMountain(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPMountain(120f, 100f),
			new SurfaceBOPMountain(config,
                topBlock, //Block top
                fillerBlock, //Block filler,
                topBlock, //IBlockState mixTop,
                fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		this.generatesEmeralds = true;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, new BlockPos(chunkX, 0, chunkY), simplex, cell, strength, river, baseBiome);
        
        rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
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
        
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
        
        if (this.config.getPropertyById(BiomeConfigBOPMountain.decorationLogsId).valueBoolean) {
        
            if (l > 0f && rand.nextInt(6) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();
                //TODO: used to be colorizedLeaves1
                (new WorldGenLog(BOPBlocks.log_4, (byte)0, BOPBlocks.leaves_0, -1, 3 + rand.nextInt(4))).generate(world, rand, new BlockPos(x22, y22, z22));
            }
        }
        
        for (int b = 0; b < 2f * strength; b++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();
            
            if (rand.nextInt(10) == 0)
            {
                (new WorldGenTreeRTGShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }

        for (int l14 = 0; l14 < 3f * strength; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            
            (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, new BlockPos(l19, k22, j24));
        }
    }
}
