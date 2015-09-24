package rtg.world.biome.realistic.vanilla;

import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaTaiga;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaTaiga extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.taiga.topBlock;
	public static Block fillerBlock = BiomeGenBase.taiga.fillerBlock;
	
	public RealisticBiomeVanillaTaiga()
	{
		super(
			BiomeGenBase.taiga,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainVanillaTaiga(),
			new SurfaceVanillaTaiga(topBlock, fillerBlock)
		);
	}	
}
