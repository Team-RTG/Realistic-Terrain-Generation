package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.tree.WorldGenTreePalm;
import rtg.world.gen.surface.vanilla.SurfaceVanillaBeach;
import rtg.world.gen.terrain.vanilla.TerrainVanillaBeach;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeVanillaBeach extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.beach.topBlock;
	public static Block fillerBlock = BiomeGenBase.beach.fillerBlock;
	
	public RealisticBiomeVanillaBeach()
	{
		super(
			BiomeGenBase.beach,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
			new TerrainVanillaBeach(),
			new SurfaceVanillaBeach(topBlock, fillerBlock, topBlock, fillerBlock, (byte)0, 1)
		);
	}
	
	    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
	    {
	    	
	    	if(rand.nextInt((int)(4f / strength)) == 0)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);

				if(z52 < 80)
				{
					WorldGenerator worldgenerator = new WorldGenTreePalm();
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
            }
	
}
