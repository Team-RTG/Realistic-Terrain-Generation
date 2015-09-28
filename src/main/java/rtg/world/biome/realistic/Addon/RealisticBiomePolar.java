package rwg.biomes.realistic.land;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import rwg.api.RWGBiomes;
import rwg.biomes.realistic.RealisticBiomeBase;
import rwg.deco.DecoBlob;
import rwg.deco.trees.DecoPineTree;
import rwg.deco.trees.DecoSmallPine;
import rwg.deco.trees.DecoSmallSpruce;
import rwg.surface.SurfaceBase;
import rwg.surface.SurfacePolar;
import rwg.terrain.TerrainBase;
import rwg.terrain.TerrainPolar;
import rwg.util.CellNoise;
import rwg.util.PerlinNoise;

public class RealisticBiomePolar extends RealisticBiomeBase
{	
	private TerrainBase terrain;
	private SurfaceBase surface;
	
	public RealisticBiomePolar() 
	{
		super(0, RWGBiomes.baseSnowDesert, RealisticBiomeBase.coastIce, RWGBiomes.baseRiverIce);
		terrain = new TerrainPolar();
		surface = new SurfacePolar(Blocks.snow, Blocks.snow);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
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
					(new DecoBlob(Blocks.packed_ice, 0)).generate(world, rand, i1, k1, j1);
				}
			}
			
			if(rand.nextInt((int)(2f / strength)) == 0)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
				
				WorldGenerator worldgenerator = new DecoSmallSpruce(rand.nextInt(2));
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
    }
    
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
    	return terrain.generateNoise(perlin, cell, x, y, ocean, border, river);
    }
    
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    	surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise, river, base);
    }
}
