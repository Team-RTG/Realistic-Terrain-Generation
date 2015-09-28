package rtg.world.biome.realistic.Addon;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.config.ConfigRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeAddonBase extends RealisticBiomeBase
{

	/** Test for genlayer also, so far these biomes will do */
	
	//Snow
	public static RealisticBiomePolar polar = new RealisticBiomePolar();
	
	//Cold
	public static RealisticBiomeDarkRedwoodPlains darkRedwoodPlains = new RealisticBiomeDarkRedwoodPlains();
	
	//Hot
	public static RealisticBiomeHotForest hotForest = new RealisticBiomeHotForest();
	public static RealisticBiomeRedDesertMountains redDesertMountains = new RealisticBiomeRedDesertMountains();
	public static RealisticBiomeRedOasis redDesertOasis = new RealisticBiomeRedOasis();
	public static RealisticBiomeSavannaForest savannaForest = new RealisticBiomeSavannaForest();
	public static RealisticBiomeDuneValleyForest duneValleyForest = new RealisticBiomeDuneValleyForest();	
	
	//Wet
	public static RealisticBiomeCanyonForest canyonForest = new RealisticBiomeCanyonForest();
	public static RealisticBiomeJungleCanyon jungleCanyon = new RealisticBiomeJungleCanyon();
	public static RealisticBiomeMesaPlains mesaPlains = new RealisticBiomeMesaPlains();
	
	
	public RealisticBiomeAddonBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
	}
	
}