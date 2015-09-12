package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaMegaTaiga;
import rtg.surface.vanilla.SurfaceVanillaMegaTaiga;
import rtg.terrain.vanilla.TerrainVanillaMegaTaiga;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaMegaTaiga extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.megaTaiga.topBlock;
	public static Block fillerBlock = BiomeGenBase.megaTaiga.fillerBlock;
	
	public RealisticBiomeVanillaMegaTaiga()
	{
		super(
			BiomeGenBase.megaTaiga,
			RTGBiomes.baseRiverCold,
			new CoastVanillaMegaTaiga(),
			new TerrainVanillaMegaTaiga(230f, 120f, 90f),
			new SurfaceVanillaMegaTaiga(topBlock, fillerBlock, true, Blocks.sand, 0.2f)
		);
	}	
}
