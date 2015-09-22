package rtg.world.biome.realistic.vanilla;

import rtg.surface.vanilla.SurfaceVanillaMesaPlateau;
import rtg.terrain.vanilla.TerrainVanillaMesaPlateau;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaMesaPlateau extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.mesaPlateau.topBlock;
	public static Block fillerBlock = BiomeGenBase.mesaPlateau.fillerBlock;
	
	public RealisticBiomeVanillaMesaPlateau()
	{
		super(
			BiomeGenBase.mesaPlateau,
			BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.OASIS),
			new TerrainVanillaMesaPlateau(true, 35f, 160f, 60f, 40f, 69f),
			new SurfaceVanillaMesaPlateau(topBlock, fillerBlock, (byte)1, 0)
		);
	}	
}
