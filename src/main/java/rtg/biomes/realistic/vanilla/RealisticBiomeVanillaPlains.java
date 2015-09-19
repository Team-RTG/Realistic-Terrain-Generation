package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.vanilla.VanillaBiomes.Climate;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaPlains;
import rtg.terrain.vanilla.TerrainVanillaPlains;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaPlains extends RealisticBiomeVanilla
{	
	public RealisticBiomeVanillaPlains()
	{
		super(
			BiomeGenBase.plains,
			VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainVanillaPlains(),
			new SurfaceVanillaPlains(BiomeGenBase.plains.topBlock, BiomeGenBase.plains.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
