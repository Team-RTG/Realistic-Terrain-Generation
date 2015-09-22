package rtg.biomes.realistic.vanilla;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.support.RealisticBiomeSupport;
import rtg.surface.SurfaceBase;
import rtg.terrain.TerrainBase;

public class RealisticBiomeVanilla extends RealisticBiomeSupport
{	
	public RealisticBiomeVanilla(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
	}	
}
