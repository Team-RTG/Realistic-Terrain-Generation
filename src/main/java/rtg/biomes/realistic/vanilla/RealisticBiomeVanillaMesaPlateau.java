package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaMesaPlateau;
import rtg.terrain.vanilla.TerrainVanillaMesaPlateau;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaMesaPlateau extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.mesaPlateau.topBlock;
	public static Block fillerBlock = BiomeGenBase.mesaPlateau.fillerBlock;
	
	public RealisticBiomeVanillaMesaPlateau()
	{
		super(
			BiomeGenBase.mesaPlateau,
			VanillaBiomes.vanillaRiverOasis,
			new TerrainVanillaMesaPlateau(true, 35f, 160f, 60f, 40f, 69f),
			new SurfaceVanillaMesaPlateau(topBlock, fillerBlock, (byte)1, 0)
		);
	}	
}
