package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaSavanna;
import rtg.surface.vanilla.SurfaceVanillaSavanna;
import rtg.terrain.vanilla.TerrainVanillaSavanna;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaSavanna extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.savanna.topBlock;
	public static Block fillerBlock = BiomeGenBase.savanna.fillerBlock;
	
	public RealisticBiomeVanillaSavanna()
	{
		super(
			BiomeGenBase.savanna,
			VanillaBiomes.vanillaRiverHot,
			new CoastVanillaSavanna(),
			new TerrainVanillaSavanna(),
			new SurfaceVanillaSavanna(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
