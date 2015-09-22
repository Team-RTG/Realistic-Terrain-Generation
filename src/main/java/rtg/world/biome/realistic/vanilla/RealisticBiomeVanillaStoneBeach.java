package rtg.world.biome.realistic.vanilla;

import rtg.surface.vanilla.SurfaceVanillaStoneBeach;
import rtg.terrain.vanilla.TerrainVanillaStoneBeach;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaStoneBeach extends RealisticBiomeVanillaBase
{	
	public RealisticBiomeVanillaStoneBeach()
	{
		super(
			BiomeGenBase.stoneBeach,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
			new TerrainVanillaStoneBeach(0f, 100f, 63f, 80f),
			new SurfaceVanillaStoneBeach(BiomeGenBase.stoneBeach.topBlock, BiomeGenBase.stoneBeach.fillerBlock, false, null, 1f, 1.5f, 85f, 20f, 4f)
		);
	}	
}
