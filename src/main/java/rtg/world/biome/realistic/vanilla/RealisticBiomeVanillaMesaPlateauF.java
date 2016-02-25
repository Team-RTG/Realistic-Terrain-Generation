package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.feature.WorldGenCacti;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMesaPlateauF;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMesaPlateauF;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaMesaPlateauF extends RealisticBiomeVanillaBase
{	
    
    public static Block topBlock = BiomeGenBase.mesaPlateau_F.topBlock;
	public static Block fillerBlock = BiomeGenBase.mesaPlateau_F.fillerBlock;
	
	public RealisticBiomeVanillaMesaPlateauF(BiomeConfig config)
	{
		super(config, 
			BiomeGenBase.mesaPlateau_F,
			BiomeGenBase.river,
			new TerrainVanillaMesaPlateauF(true, 35f, 160f, 60f, 40f, 69f),
			new SurfaceVanillaMesaPlateauF(config, Blocks.sand, (byte)1, Blocks.sand, (byte)1, 0)
		);
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        for (int l = 0; l < 1; ++l)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            if(k1 < 70)
            {
                (new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
            }
        }
        
        if(river > 0.8f)
        {
            for (int b1 = 0; b1 < 10; b1++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
    
                WorldGenerator worldgenerator;
                worldgenerator = new WorldGenShrub(0, 0);
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
        }
        else
        {
            for (int b1 = 0; b1 < 5; b1++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
    
                WorldGenerator worldgenerator;
                worldgenerator = new WorldGenShrub(0, 0);
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
            
            if(rand.nextInt(3) == 0) 
            {
                int i18 = chunkX + rand.nextInt(16) + 8;
                int i23 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenReed()).generate(world, rand, i18, 60 + rand.nextInt(8), i23);
            }
            
            if(rand.nextInt(28) == 0)
            {
                int j16 = chunkX + rand.nextInt(16) + 8;
                int j18 = rand.nextInt(128);
                int j21 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
            }
            
            for(int i15 = 0; i15 < 5; i15++)
            {
                int i17 = chunkX + rand.nextInt(16) + 8;
                int i20 = rand.nextInt(160);
                int l22 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
            }
            
            for(int k18 = 0; k18 < 25; k18++)
            {
                int k21 = chunkX + rand.nextInt(16) + 8;
                int j23 = rand.nextInt(160);
                int k24 = chunkY + rand.nextInt(16) + 8;
                (new WorldGenCacti(true)).generate(world, rand, k21, j23, k24);
            }
        }
    }
}
