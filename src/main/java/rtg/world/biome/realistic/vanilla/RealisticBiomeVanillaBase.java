package rtg.world.biome.realistic.vanilla;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.support.RealisticBiomeSupport;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaBase extends RealisticBiomeSupport
{	
	public RealisticBiomeVanillaBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
	}	
}
