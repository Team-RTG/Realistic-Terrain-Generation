package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaStoneBeach;
import rtg.surface.vanilla.SurfaceVanillaStoneBeach;
import rtg.terrain.vanilla.TerrainVanillaStoneBeach;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaStoneBeach extends RealisticBiomeVanilla
{	
	public RealisticBiomeVanillaStoneBeach()
	{
		super(
			BiomeGenBase.stoneBeach,
			RTGBiomes.baseRiverOasis,
			new CoastVanillaStoneBeach(),
			new TerrainVanillaStoneBeach(0f, 100f, 63f, 80f),
			new SurfaceVanillaStoneBeach(BiomeGenBase.stoneBeach.topBlock, BiomeGenBase.stoneBeach.fillerBlock, false, null, 1f, 1.5f, 85f, 20f, 4f)
		);
	}	
}
