package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.vanilla.VanillaBiomes.Climate;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaOcean;
import rtg.terrain.vanilla.TerrainVanillaOcean;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaOcean extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.ocean.topBlock;
	public static Block fillerBlock = BiomeGenBase.ocean.fillerBlock;
	
	public RealisticBiomeVanillaOcean()
	{
		super(
			BiomeGenBase.ocean,
			VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.WET),
			new TerrainVanillaOcean(),
			new SurfaceVanillaOcean(Blocks.sand, Blocks.sand, (byte)0, 0)
		);
	}	
}
