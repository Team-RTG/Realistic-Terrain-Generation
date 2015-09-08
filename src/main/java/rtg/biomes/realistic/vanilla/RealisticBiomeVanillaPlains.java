package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
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
			RTGBiomes.baseRiverTemperate,
			RealisticBiomeBase.coastDunes,
			new TerrainVanillaPlains(),
			new SurfaceVanillaPlains(BiomeGenBase.plains.topBlock, BiomeGenBase.plains.fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
