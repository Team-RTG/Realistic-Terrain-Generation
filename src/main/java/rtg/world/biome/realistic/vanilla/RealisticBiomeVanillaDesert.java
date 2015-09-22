package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.surface.vanilla.SurfaceVanillaDesert;
import rtg.terrain.vanilla.TerrainVanillaDesert;
import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenCacti;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.tree.WorldGenTreeSavanna;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaDesert extends RealisticBiomeVanillaBase
{	
	public RealisticBiomeVanillaDesert()
	{
		super(
			BiomeGenBase.desert,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
			new TerrainVanillaDesert(),
			new SurfaceVanillaDesert(BiomeGenBase.desert.topBlock, BiomeGenBase.desert.fillerBlock)
		);
	}	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {

		if(rand.nextInt((int)(4f / strength)) == 0)
	       {
	           int k = chunkX + rand.nextInt(360) + 8;
	           int l = chunkY + rand.nextInt(360) + 8;
	           WorldGenDesertWells worldgendesertwells = new WorldGenDesertWells();
	           worldgendesertwells.generate(world, rand, k, world.getHeightValue(k, l) + 1, l);
	        }	
		if(river > 0.7f)
		{
			if(river > 0.86f)
			{
				for(int b33 = 0; b33 < 10f * strength; b33++)
				{
					int j6 = chunkX + rand.nextInt(16) + 8;
					int k10 = chunkY + rand.nextInt(16) + 8;
					int z52 = world.getHeightValue(j6, k10);
	
					if(z52 < 100f || (z52 < 120f && rand.nextInt(10) == 0))
					{
						WorldGenerator worldgenerator = rand.nextInt(4) != 0 ? new WorldGenShrub(0, 0) : new WorldGenTreeSavanna(1);
						worldgenerator.setScale(1.0D, 1.0D, 1.0D);
						worldgenerator.generate(world, rand, j6, z52, k10);
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
					(new WorldGenCacti(false)).generate(world, rand, k21, j23, k24);
				}
			}
		}
		
		
		for(int i15 = 0; i15 < 1f * strength; i15++)
		{
			int i17 = chunkX + rand.nextInt(16) + 8;
			int i20 = 64 + rand.nextInt(64);
			int l22 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
		}
    }
}
