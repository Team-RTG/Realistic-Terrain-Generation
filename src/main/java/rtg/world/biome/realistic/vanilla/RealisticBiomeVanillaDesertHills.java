package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.config.vanilla.ConfigVanilla;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenCacti;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.tree.WorldGenTreeRTGSavanna;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDesertHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDesertHills;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaDesertHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.desertHills.topBlock.getBlock();
	public static Block fillerBlock = BiomeGenBase.desertHills.fillerBlock.getBlock();
	
    private static SurfaceBase surface = new SurfaceVanillaDesertHills(Blocks.sand, Blocks.sandstone, false, null, 0f, 1.5f, 60f, 65f, 1.5f);
    private static SurfaceBase riverSurface = new SurfaceRiverOasis();
    
	public RealisticBiomeVanillaDesertHills(BiomeConfig config)
	{
		super(
			BiomeGenBase.desertHills,
			BiomeGenBase.river,
			new TerrainVanillaDesertHills(600f, 50f, 0f),
			surface
		);
		
		this.config = config;
		
        this.waterSurfaceLakeChance = 0;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        if(rand.nextInt((int)(2f / strength)) == 0)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeight(new BlockPos(i1,1,j1)).getY();
            
            if (k1 < 85 && rand.nextInt(16) == 0) {
                (new WorldGenBlob(Blocks.cobblestone, 0, rand)).generate(world, rand, new BlockPos(i1, k1, j1));
            }
        }
        
        if(river > 0.7f)
        {
            if(river > 0.86f)
            {
                for(int b33 = 0; b33 < 10f * strength; b33++)
                {
                    int j6 = chunkX + rand.nextInt(16) + 8;
                    int k10 = chunkY + rand.nextInt(16) + 8;
                    int z52 = world.getHeight(new BlockPos(j6,1,k10)).getY();
    
                    if(z52 < 100f || (z52 < 120f && rand.nextInt(10) == 0))
                    {
                        WorldGenerator worldgenerator = rand.nextInt(4) != 0 ? new WorldGenShrub(Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()) : new WorldGenTreeRTGSavanna(1);

                        worldgenerator.generate(world, rand, new BlockPos(j6, z52, k10));
                    }
                }
            }
            
            for(int k18 = 0; k18 < 12f * strength; k18++)
            {
                int k21 = chunkX + rand.nextInt(16) + 8;
                int j23 = rand.nextInt(160);
                int k24 = chunkY + rand.nextInt(16) + 8;
                if(j23 < 120f)
                {
                    (new WorldGenCacti(false)).generate(world, rand, new BlockPos(k21, j23, k24));
                }
            }

            for(int f25 = 0; f25 < 2f * strength; f25++)
            {
                int i18 = chunkX + rand.nextInt(16) + 8;
                int i23 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenReed()).generate(world, rand, new BlockPos(i18, 60 + rand.nextInt(8), i23));
            }
            
            if(rand.nextInt(28) == 0)
            {
                int j16 = chunkX + rand.nextInt(16) + 8;
                int j18 = rand.nextInt(128);
                int j21 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenPumpkin()).generate(world, rand, new BlockPos(j16, j18, j21));
            }
            
            for(int f23 = 0; f23 < 3; f23++)
            {
                int j15 = chunkX + rand.nextInt(16) + 8;
                int j17 = rand.nextInt(128);
                int j20 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenFlowers(new int[]{9,9,9,9,3,3,3,3,3,2,2,2,11,11,11})).generate(world, rand, new BlockPos(j15, j17, j20));
            }
            
            for(int l14 = 0; l14 < 15; l14++)
            {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;

                if(rand.nextInt(6) == 0)
                {
                    (new WorldGenGrass(Blocks.double_plant, 2)).generate(world, rand, new BlockPos(l19, k22, j24));
                }
                else
                {
                    (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, new BlockPos(l19, k22, j24));
                }
            }
        }
        
        for(int k18 = 0; k18 < 12; k18++)
        {
            int k21 = chunkX + rand.nextInt(16) + 8;
            int j23 = rand.nextInt(160);
            int k24 = chunkY + rand.nextInt(16) + 8;
            if(j23 < 120f)
            {
                (new WorldGenCacti(false)).generate(world, rand, new BlockPos(k21, j23, k24));
            }
        }
        
        for(int i15 = 0; i15 < 3f * strength; i15++)
        {
            int i17 = chunkX + rand.nextInt(16) + 8;
            int i20 = rand.nextInt(160);
            int l22 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenDeadBush()).generate(world, rand, new BlockPos(i17, i20, l22));
        }
    }
    
    public void rReplace(ChunkPrimer primer, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
        surface.paintTerrain(primer, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
        riverSurface.paintTerrain(primer, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}