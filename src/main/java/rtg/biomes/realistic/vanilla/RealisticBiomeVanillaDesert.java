package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaDesert;
import rtg.surface.vanilla.SurfaceVanillaDesert;
import rtg.terrain.vanilla.TerrainVanillaDesert;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaDesert extends RealisticBiomeVanilla
{	
	public RealisticBiomeVanillaDesert()
	{
		super(
			BiomeGenBase.desert,
			VanillaBiomes.vanillaRiverOasis,
			new CoastVanillaDesert(),
			new TerrainVanillaDesert(150f, 50f, 0f),
			new SurfaceVanillaDesert(BiomeGenBase.desert.topBlock, BiomeGenBase.desert.fillerBlock, Blocks.sandstone, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
