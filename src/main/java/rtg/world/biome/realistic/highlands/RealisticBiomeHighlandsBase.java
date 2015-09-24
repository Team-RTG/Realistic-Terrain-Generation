package rtg.world.biome.realistic.highlands;

import cpw.mods.fml.common.Loader;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeHighlandsBase extends RealisticBiomeBase
{	
	public RealisticBiomeHighlandsBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("Highlands"))
		{
			
		}
	}
}
