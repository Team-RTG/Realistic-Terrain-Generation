package rtg.world.biome.realistic.addon;

import java.util.Random;

import rtg.config.addon.ConfigAddon;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenCacti;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.WorldGenWaterGrass;
import rtg.world.gen.feature.tree.WorldGenTreeBirch;
import rtg.world.gen.feature.tree.WorldGenTreeJungleSmall;
import rtg.world.gen.feature.tree.WorldGenTreePineSmall;
import rtg.world.gen.feature.tree.WorldGenTreeSavanna;
import rtg.world.gen.feature.tree.WorldGenTreeSpruceSmall;
import rtg.world.gen.surface.*;
import rtg.world.gen.terrain.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeAddonPolar extends RealisticBiomeBase
{	
	private SurfaceBase surface;
	
	public RealisticBiomeAddonPolar() 
	{
		super(
				RealisticBiomeAddonBase.polar, 
				BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.ICE),
				new TerrainPolar(),
				new SurfacePolar(Blocks.snow, Blocks.snow)
				);
		
		this.setRealisticBiomeName("Polar");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigAddon.weightPolar;
		
		this.waterLakeFrequency = 0;
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise perlin, CellNoise cell, float strength, float river)
    {
		if(river > 0.86f)
		{
			for(int j = 0; j < 5f * strength; j++)
			{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
				if(k1 < 64)
				{
				//	(new WorldGenBlob(Blocks.packed_ice, 0)).generate(world, rand, i1, k1, j1);
				}
			}
			
			if(rand.nextInt((int)(2f / strength)) == 0)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
				
				WorldGenerator worldgenerator = new WorldGenTreeSpruceSmall(rand.nextInt(2));
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
    }
    
    public float rNoise(OpenSimplexNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
    	return terrain.generateNoise(perlin, cell, x, y, ocean, border);
    }
    
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    	surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise, river, base);
    }
}
