package rtg.biomes.realistic.vanilla;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.support.RealisticBiomeSupport;
import rtg.surface.SurfaceBase;
import rtg.terrain.TerrainBase;

public class RealisticBiomeVanilla extends RealisticBiomeSupport
{	
	public BiomeGenBase vanillaBiomeBase;
	public BiomeGenBase vanillaRiver;
	public RealisticBiomeBase vanillaCoast;
	public TerrainBase vanillaTerrain;
	public SurfaceBase vanillaSurface;

	public RealisticBiomeVanilla(BiomeGenBase b, BiomeGenBase riverbiome, RealisticBiomeBase coastbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, coastbiome, t, s);
		
		vanillaBiomeBase = b;
		vanillaRiver = riverbiome;
		vanillaCoast = coastbiome;
		vanillaTerrain = t;
		vanillaSurface = s;
	}	
}
