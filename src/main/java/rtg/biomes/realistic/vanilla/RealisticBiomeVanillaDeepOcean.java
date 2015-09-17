package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaDeepOcean;
import rtg.terrain.vanilla.TerrainVanillaDeepOcean;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaDeepOcean extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.deepOcean.topBlock;
	public static Block fillerBlock = BiomeGenBase.deepOcean.fillerBlock;
	
	public RealisticBiomeVanillaDeepOcean()
	{
		super(
			BiomeGenBase.deepOcean,
			VanillaBiomes.vanillaRiverWet,
			new TerrainVanillaDeepOcean(),
			new SurfaceVanillaDeepOcean(Blocks.sand, Blocks.sand, (byte)0, 0)
		);
	}	
}
