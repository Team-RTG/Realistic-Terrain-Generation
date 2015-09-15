package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaColdTaigaHills;
import rtg.surface.vanilla.SurfaceVanillaColdTaigaHills;
import rtg.terrain.vanilla.TerrainVanillaColdTaigaHills;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaColdTaigaHills extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.coldTaigaHills.topBlock;
	public static Block fillerBlock = BiomeGenBase.coldTaigaHills.fillerBlock;
	
	public RealisticBiomeVanillaColdTaigaHills()
	{
		super(
			BiomeGenBase.coldTaigaHills,
			VanillaBiomes.vanillaRiverCold,
			new CoastVanillaColdTaigaHills(),
			new TerrainVanillaColdTaigaHills(),
			new SurfaceVanillaColdTaigaHills(topBlock, fillerBlock)
		);
	}	
}
