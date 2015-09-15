package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaTaiga;
import rtg.surface.vanilla.SurfaceVanillaTaiga;
import rtg.terrain.vanilla.TerrainVanillaTaiga;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaTaiga extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.taiga.topBlock;
	public static Block fillerBlock = BiomeGenBase.taiga.fillerBlock;
	
	public RealisticBiomeVanillaTaiga()
	{
		super(
			BiomeGenBase.taiga,
			VanillaBiomes.vanillaRiverCold,
			new CoastVanillaTaiga(),
			new TerrainVanillaTaiga(),
			new SurfaceVanillaTaiga(topBlock, fillerBlock)
		);
	}	
}
