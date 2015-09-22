package rtg.world.biome.realistic.vanilla;

import rtg.surface.vanilla.SurfaceVanillaDeepOcean;
import rtg.terrain.vanilla.TerrainVanillaDeepOcean;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaDeepOcean extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.deepOcean.topBlock;
	public static Block fillerBlock = BiomeGenBase.deepOcean.fillerBlock;
	
	public RealisticBiomeVanillaDeepOcean()
	{
		super(
			BiomeGenBase.deepOcean,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
			new TerrainVanillaDeepOcean(),
			new SurfaceVanillaDeepOcean(Blocks.sand, Blocks.sand, (byte)0, 0)
		);
	}	
}
