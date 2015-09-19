package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.vanilla.VanillaBiomes.Climate;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaJungleEdge;
import rtg.terrain.vanilla.TerrainVanillaJungleEdge;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaJungleEdge extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.jungleEdge.topBlock;
	public static Block fillerBlock = BiomeGenBase.jungleEdge.fillerBlock;
	
	public RealisticBiomeVanillaJungleEdge()
	{
		super(
			BiomeGenBase.jungleEdge,
			VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainVanillaJungleEdge(),
			new SurfaceVanillaJungleEdge(topBlock, fillerBlock, false, null, 1.3f)
		);
	}	
}
