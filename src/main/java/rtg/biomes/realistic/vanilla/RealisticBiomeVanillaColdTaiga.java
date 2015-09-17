package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaColdTaiga;
import rtg.terrain.vanilla.TerrainVanillaColdTaiga;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaColdTaiga extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.coldTaiga.topBlock;
	public static Block fillerBlock = BiomeGenBase.coldTaiga.fillerBlock;
	
	public RealisticBiomeVanillaColdTaiga()
	{
		super(
			BiomeGenBase.coldTaiga,
			VanillaBiomes.vanillaRiverCold,
			new TerrainVanillaColdTaiga(),
			new SurfaceVanillaColdTaiga(topBlock, fillerBlock)
		);
	}	
}
