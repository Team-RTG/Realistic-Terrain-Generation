package rtg.support;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.SurfaceGrassland;
import rtg.terrain.TerrainSmallSupport;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.support.Support.BiomeCategory;

public class SupportTC 
{
	/*
	THAUMCRAFT BIOMES
	
	118: "Tainted Land"
	119: "Magical Forest"
	*/
	
	public static void init()
	{
		BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
		
		for(int i = 0; i < 256; i++)
		{
			if(b[i] != null)
			{
				if(b[i].biomeName == "Tainted Land" || b[i].biomeName == "Magical Forest")
				{
					Support.addBiome(
						new RealisticBiomeSupport(
							b[i], VanillaBiomes.vanillaRiverTemperate,
							new TerrainSmallSupport(),
							new SurfaceGrassland(b[i].topBlock, b[i].fillerBlock, Blocks.stone, Blocks.cobblestone)
						),
						BiomeCategory.SMALL
					);
				}
			}
		}
	}
}
