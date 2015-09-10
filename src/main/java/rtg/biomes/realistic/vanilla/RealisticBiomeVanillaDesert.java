package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaBeach;
import rtg.coast.vanilla.CoastVanillaDesert;
import rtg.surface.vanilla.SurfaceVanillaBeach;
import rtg.surface.vanilla.SurfaceVanillaDesert;
import rtg.terrain.vanilla.TerrainVanillaBeach;
import rtg.terrain.vanilla.TerrainVanillaDesert;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaDesert extends RealisticBiomeVanilla
{	
	public RealisticBiomeVanillaDesert()
	{
		super(
			BiomeGenBase.desert,
			RTGBiomes.baseRiverOasis,
			new CoastVanillaDesert(),
			new TerrainVanillaDesert(150f, 50f, 0f),
			new SurfaceVanillaDesert(BiomeGenBase.desert.topBlock, BiomeGenBase.desert.fillerBlock, Blocks.sandstone, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
