package rtg.world.biome.realistic.vanilla;

import rtg.surface.vanilla.SurfaceVanillaTaigaHills;
import rtg.terrain.vanilla.TerrainVanillaTaigaHills;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaTaigaHills extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.taigaHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.taigaHills.fillerBlock;
	
	public RealisticBiomeVanillaTaigaHills()
	{
		super(
			BiomeGenBase.taigaHills,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.COLD),
			new TerrainVanillaTaigaHills(),
			new SurfaceVanillaTaigaHills(topBlock, fillerBlock, true, Blocks.sand, 0.2f)
		);
	}	
}
