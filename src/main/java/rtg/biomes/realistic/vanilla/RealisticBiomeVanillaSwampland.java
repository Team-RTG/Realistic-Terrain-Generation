package rtg.biomes.realistic.vanilla;

import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.coast.vanilla.CoastVanillaSwampland;
import rtg.surface.vanilla.SurfaceVanillaSwampland;
import rtg.terrain.vanilla.TerrainVanillaSwampland;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaSwampland extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.swampland.topBlock;
	public static Block fillerBlock = BiomeGenBase.swampland.fillerBlock;
	
	public RealisticBiomeVanillaSwampland()
	{
		super(
			BiomeGenBase.swampland,
			VanillaBiomes.vanillaRiverWet,
			new CoastVanillaSwampland(),
			new TerrainVanillaSwampland(),
			new SurfaceVanillaSwampland(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone)
		);
	}	
}
