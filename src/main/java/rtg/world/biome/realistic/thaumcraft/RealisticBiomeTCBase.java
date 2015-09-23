package rtg.world.biome.realistic.thaumcraft;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceGrassland;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.TerrainSmallSupport;

public class RealisticBiomeTCBase extends RealisticBiomeBase
{	
	public RealisticBiomeTCBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
	}
	
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
					BiomeBase.addBiome(
						new RealisticBiomeBase(
							b[i], BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.TEMPERATE),
							new TerrainSmallSupport(),
							new SurfaceGrassland(b[i].topBlock, b[i].fillerBlock, Blocks.stone, Blocks.cobblestone)
						),
						BiomeBase.BiomeCategory.SMALL
					);
				}
			}
		}
	}
}
