package rtg.world.biome.realistic.vanilla;

import rtg.surface.vanilla.SurfaceVanillaColdTaiga;
import rtg.terrain.vanilla.TerrainVanillaColdTaiga;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaColdTaiga extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.coldTaiga.topBlock;
	public static Block fillerBlock = BiomeGenBase.coldTaiga.fillerBlock;
	
	public RealisticBiomeVanillaColdTaiga()
	{
		super(
			BiomeGenBase.coldTaiga,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainVanillaColdTaiga(),
			new SurfaceVanillaColdTaiga(topBlock, fillerBlock)
		);
	}	
}
