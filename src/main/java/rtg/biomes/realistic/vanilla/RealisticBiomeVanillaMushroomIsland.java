package rtg.biomes.realistic.vanilla;

import rtg.api.RTGBiomes;
import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.surface.vanilla.SurfaceVanillaMushroomIsland;
import rtg.terrain.vanilla.TerrainVanillaMushroomIsland;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaMushroomIsland extends RealisticBiomeVanilla
{	
	public static Block topBlock = BiomeGenBase.mushroomIsland.topBlock;
	public static Block fillerBlock = BiomeGenBase.mushroomIsland.fillerBlock;
	
	public RealisticBiomeVanillaMushroomIsland()
	{
		super(
			BiomeGenBase.mushroomIsland,
			VanillaBiomes.vanillaOceanCold,
			new TerrainVanillaMushroomIsland(),
			new SurfaceVanillaMushroomIsland(topBlock, fillerBlock, 67, topBlock, 0f)
		);
	}	
}
