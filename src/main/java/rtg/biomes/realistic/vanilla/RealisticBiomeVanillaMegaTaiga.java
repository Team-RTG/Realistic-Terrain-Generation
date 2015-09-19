package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.vanilla.VanillaBiomes.Climate;
import rtg.biomes.realistic.RealisticBiomeBase;
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
			VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainVanillaMegaTaiga(230f, 120f, 90f),
			new SurfaceVanillaMegaTaiga(topBlock, fillerBlock, true, Blocks.sand, 0.2f)
		);
	}	
}
